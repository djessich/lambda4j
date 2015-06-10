/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */
package at.gridtec.internals.lang.function.throwable;

import at.gridtec.internals.lang.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.DoubleToIntFunction;
import java.util.function.IntSupplier;

/**
 * This functional interface implements a {@link DoubleToIntFunction} which is able to throw any {@link Exception}.
 * <p>
 * The thrown {@link Exception} is sneakily thrown unless its a {@link RuntimeException}. This means that there is no
 * need to catch the thrown exception, nor to declare that you throw it using the throws keyword. The exception is
 * still
 * thrown, but the Java compiler stops warning about it.
 * <p>
 * However, when using this throwing lambda, be aware of the following consequences:
 * <ol>
 * <li>If the calling code is to handle a thrown {@code Exception}, it MUST be declared in the methods
 * <em>throws</em> clause which uses this lambda. The compiler will not force you to add it.</li>
 * <li>If the calling code already handles a thrown {@code Exception}, it needs to be declared in the methods
 * <em>throws</em> clause which uses this lambda. If not the compiler prints an error that the corresponding {@code
 * try} block never throws the specific exception.</li>
 * <li>In any case, there is no way of explicitly catching the thrown {@code Exception} in the method which uses this
 * lambda. If you try, the compiler prints an error that the corresponding {@code try} block never throws the specific
 * exception.</li>
 * </ol>
 * <p>
 * When the calling code never throws the specific exception that it declares, you should omit it. For example: {@code
 * new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but UTF-8 is guaranteed by the Java
 * specification to be always present. The exception should therefore be omitted.
 * <p>
 * Moreover, if no checked exception should be used at all or its use is inappropriate for any reasons, omit the
 * declaration in the <em>throws</em> clause. The checked exception will behave just like a normal <b>unchecked</b>
 * exception due to sneaky throwing.
 *
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface ThrowableDoubleToIntFunction extends DoubleToIntFunction {

    /**
     * The apply method for this {@link DoubleToIntFunction} which is able to throw any {@link Exception} type.
     *
     * @param value The argument for the function
     * @return The return value from the function, which is its result.
     * @throws Exception Any exception from this functions action
     */
    int applyAsIntThrows(double value) throws Exception;

    /**
     * Overrides the {@link DoubleToIntFunction#applyAsInt(double)} method by using a redefinition as default method.
     * It calls the {@link #applyAsIntThrows(double)} method of this interface and catches the thrown {@link
     * Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types
     * are sneakily thrown.
     *
     * @param value The argument for the function
     * @return The return value from the function, which is its result.
     * @see at.gridtec.internals.lang.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default int applyAsInt(double value) {
        try {
            return applyAsIntThrows(value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableDoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction}
     * to its input, and if an error occurred, applies the given one. The exception from this {@code
     * ThrowableDoubleToIntFunction} is ignored, unless it is an unchecked exception.
     *
     * @param other A {@code ThrowableDoubleToIntFunction} to be applied if this one fails
     * @return A composed {@code ThrowableDoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction},
     * and if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableDoubleToIntFunction orElse(final ThrowableDoubleToIntFunction other) {
        Objects.requireNonNull(other);
        return value -> {
            try {
                return applyAsIntThrows(value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception ignored) {
                return other.applyAsIntThrows(value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableDoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction}
     * to its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableDoubleToIntFunction} is added as suppressed to the given one, unless it is an unchecked exception.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableDoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction},
     * and if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableDoubleToIntFunction orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return value -> {
            try {
                return applyAsIntThrows(value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableDoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction}
     * to its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableDoubleToIntFunction} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableDoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction},
     * and if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableDoubleToIntFunction orThrowAlways(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return value -> {
            try {
                return applyAsIntThrows(value);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link DoubleToIntFunction} that applies this {@link ThrowableDoubleToIntFunction} to its
     * input, and if an error occurred, applies the given {@code DoubleToIntFunction} representing a fallback. The
     * exception from this {@code ThrowableDoubleToIntFunction} is ignored, unless it is an unchecked exception.
     *
     * @param fallback A {@code DoubleToIntFunction} to be applied if this one fails
     * @return A composed {@code DoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction}, and if an
     * error occurred, applies the given {@code DoubleToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default DoubleToIntFunction fallbackTo(final DoubleToIntFunction fallback) {
        Objects.requireNonNull(fallback);
        return value -> {
            try {
                return applyAsIntThrows(value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception ignored) {
                return fallback.applyAsInt(value);
            }
        };
    }

    /**
     * Returns a composed {@link DoubleToIntFunction} that applies this {@link ThrowableDoubleToIntFunction} to its
     * input, and if an error occurred, returns the given value. The exception from this {@code
     * ThrowableDoubleToIntFunction} is ignored, unless it is an unchecked exception.
     *
     * @param retVal The value to be returned if this {@code ThrowableDoubleToIntFunction} fails
     * @return A composed {@code DoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction}, and if an
     * error occurred, returns the given value.
     */
    default DoubleToIntFunction orReturn(int retVal) {
        return value -> {
            try {
                return applyAsIntThrows(value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception ignored) {
                return retVal;
            }
        };
    }

    /**
     * Returns a composed {@link DoubleToIntFunction} that applies this {@link ThrowableDoubleToIntFunction} to its
     * input, and if an error occurred, returns the supplied value from the given {@link IntSupplier}. The exception
     * from this {@code ThrowableDoubleToIntFunction} is ignored, unless it is an unchecked exception.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableDoubleToIntFunction} fails
     * @return A composed {@code DoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction}, and if an
     * error occurred, the supplied value from the given {@code IntSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default DoubleToIntFunction orReturn(final IntSupplier supplier) {
        Objects.requireNonNull(supplier);
        return value -> {
            try {
                return applyAsIntThrows(value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception ignored) {
                return supplier.getAsInt();
            }
        };
    }

    /**
     * Returns a composed {@link DoubleToIntFunction} that applies this {@link ThrowableDoubleToIntFunction} to its
     * input, and if an error occurred, returns the given value. The exception from this {@code
     * ThrowableDoubleToIntFunction} is ignored.
     *
     * @param retVal The value to be returned if this {@code ThrowableDoubleToIntFunction} fails
     * @return A composed {@code DoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction}, and if an
     * error occurred, returns the given value.
     */
    default DoubleToIntFunction orReturnAlways(int retVal) {
        return value -> {
            try {
                return applyAsIntThrows(value);
            } catch (Exception ignored) {
                return retVal;
            }
        };
    }

    /**
     * Returns a composed {@link DoubleToIntFunction} that applies this {@link ThrowableDoubleToIntFunction} to its
     * input, and if an error occurred, returns the supplied value from the given {@link IntSupplier}. The exception
     * from this {@code ThrowableDoubleToIntFunction} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableDoubleToIntFunction} fails
     * @return A composed {@code DoubleToIntFunction} that applies this {@code ThrowableDoubleToIntFunction}, and if an
     * error occurred, the supplied value from the given {@code IntSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default DoubleToIntFunction orReturnAlways(final IntSupplier supplier) {
        Objects.requireNonNull(supplier);
        return value -> {
            try {
                return applyAsIntThrows(value);
            } catch (Exception ignored) {
                return supplier.getAsInt();
            }
        };
    }
}
