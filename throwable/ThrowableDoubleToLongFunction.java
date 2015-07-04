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
import java.util.function.DoubleToLongFunction;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;

/**
 * This functional interface implements a {@link DoubleToLongFunction} which is able to throw any {@link Exception}.
 * <p>
 * The thrown {@link Exception} is sneakily thrown unless its a {@link RuntimeException}. This means that there is no
 * need to catch the thrown exception, nor to declare that you throw it using the <em>throws</em> keyword. The
 * exception is still thrown, but the Java compiler stops warning about it.
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
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableDoubleToLongFunction extends DoubleToLongFunction {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableDoubleToLongFunction}. This is a
     * convenience method in case the given {@link ThrowableDoubleToLongFunction} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableDoubleToLongFunction} is returned as-is.
     *
     * @param lambda The {@code ThrowableDoubleToLongFunction} which should be returned as-is.
     * @return The given {@code ThrowableDoubleToLongFunction} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableDoubleToLongFunction wrap(final ThrowableDoubleToLongFunction lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableDoubleToLongFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableDoubleToLongFunction} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableDoubleToLongFunction constant(long ret) {
        Objects.requireNonNull(ret);
        return value -> ret;
    }

    /**
     * The apply method for this {@link DoubleToLongFunction} which is able to throw any {@link Exception} type.
     *
     * @param value The argument for the function
     * @return The return value from the function, which is its result.
     * @throws Exception Any exception from this functions action
     */
    long applyAsLongThrows(double value) throws Exception;

    /**
     * Overrides the {@link DoubleToLongFunction#applyAsLong(double)} method by using a redefinition as default method.
     * It calls the {@link #applyAsLongThrows(double)} method of this interface and catches the thrown {@link
     * Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types
     * are sneakily thrown.
     *
     * @param value The argument for the function
     * @return The return value from the function, which is its result.
     * @see at.gridtec.internals.lang.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default long applyAsLong(double value) {
        try {
            return applyAsLongThrows(value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableDoubleToLongFunction} that applies this {@code ThrowableDoubleToLongFunction}
     * to its input, and if an error occurred, applies the given one. The exception from this {@code
     * ThrowableDoubleToLongFunction} is ignored.
     *
     * @param other A {@code ThrowableDoubleToLongFunction} to be applied if this one fails
     * @return A composed {@code ThrowableDoubleToLongFunction} that applies this {@code ThrowableDoubleToLongFunction},
     * and if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableDoubleToLongFunction orElse(final ThrowableDoubleToLongFunction other) {
        Objects.requireNonNull(other);
        return value -> {
            try {
                return applyAsLongThrows(value);
            } catch (Exception ignored) {
                return other.applyAsLongThrows(value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableDoubleToLongFunction} that applies this {@code ThrowableDoubleToLongFunction}
     * to its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableDoubleToLongFunction} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableDoubleToLongFunction} that applies this {@code ThrowableDoubleToLongFunction},
     * and if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableDoubleToLongFunction orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return value -> {
            try {
                return applyAsLongThrows(value);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link DoubleToLongFunction} that applies this {@link ThrowableDoubleToLongFunction} to its
     * input, and if an error occurred, applies the given {@code DoubleToLongFunction} representing a fallback. The
     * exception from this {@code ThrowableDoubleToLongFunction} is ignored.
     *
     * @param fallback A {@code DoubleToLongFunction} to be applied if this one fails
     * @return A composed {@code DoubleToLongFunction} that applies this {@code ThrowableDoubleToLongFunction}, and if
     * an error occurred, applies the given {@code DoubleToLongFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default DoubleToLongFunction fallbackTo(final DoubleToLongFunction fallback) {
        Objects.requireNonNull(fallback);
        return value -> {
            try {
                return applyAsLongThrows(value);
            } catch (Exception ignored) {
                return fallback.applyAsLong(value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableDoubleToLongFunction} that applies this {@code ThrowableDoubleToLongFunction}
     * to its input, additionally performing the provided action to the resulting value. This method exists mainly to
     * support debugging.
     *
     * @param action A {@link LongConsumer} to be applied additionally to this {@code ThrowableDoubleToLongFunction}
     * @return A composed {@code ThrowableDoubleToLongFunction} that applies this {@code ThrowableDoubleToLongFunction},
     * additionally performing the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableDoubleToLongFunction peek(final LongConsumer action) {
        Objects.requireNonNull(action);
        return value -> {
            final long ret = applyAsLong(value);
            action.accept(ret);
            return ret;
        };
    }

    /**
     * Returns a composed {@link DoubleToLongFunction} that applies this {@link ThrowableDoubleToLongFunction} to its
     * input, and if an error occurred, returns the given value. The exception from this {@code
     * ThrowableDoubleToLongFunction} is ignored.
     *
     * @param retVal The value to be returned if this {@code ThrowableDoubleToLongFunction} fails
     * @return A composed {@code DoubleToLongFunction} that applies this {@code ThrowableDoubleToLongFunction}, and if
     * an error occurred, returns the given value.
     */
    default DoubleToLongFunction orReturn(long retVal) {
        return value -> {
            try {
                return applyAsLongThrows(value);
            } catch (Exception ignored) {
                return retVal;
            }
        };
    }

    /**
     * Returns a composed {@link DoubleToLongFunction} that applies this {@link ThrowableDoubleToLongFunction} to its
     * input, and if an error occurred, returns the supplied value from the given {@link LongSupplier}. The exception
     * from this {@code ThrowableDoubleToLongFunction} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableDoubleToLongFunction}
     * fails
     * @return A composed {@code DoubleToLongFunction} that applies this {@code ThrowableDoubleToLongFunction}, and if
     * an error occurred, the supplied value from the given {@code LongSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default DoubleToLongFunction orReturn(final LongSupplier supplier) {
        Objects.requireNonNull(supplier);
        return value -> {
            try {
                return applyAsLongThrows(value);
            } catch (Exception ignored) {
                return supplier.getAsLong();
            }
        };
    }
}
