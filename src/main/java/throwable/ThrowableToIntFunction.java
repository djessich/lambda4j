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
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.ToIntFunction;

/**
 * This functional interface implements a {@link ToIntFunction} which is able to throw any {@link Exception}.
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
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsIntThrows(Object)}.
 *
 * @param <T> The type of argument for the function
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableToIntFunction<T> extends ToIntFunction<T> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableToIntFunction}. This is a convenience
     * method in case the given {@link ThrowableToIntFunction} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableToIntFunction} is
     * returned as-is.
     *
     * @param <T> The type of argument for the function
     * @param lambda The {@code ThrowableToIntFunction} which should be returned as-is.
     * @return The given {@code ThrowableToIntFunction} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableToIntFunction<T> wrap(final ThrowableToIntFunction<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableToIntFunction} from the given {@link ToIntFunction}. This method is just convenience
     * to provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param <T> The type of argument for the function
     * @param lambda A {@code ToIntFunction} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableToIntFunction} from the given {@code ToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableToIntFunction<T> from(final ToIntFunction<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsInt;
    }

    /**
     * Creates a {@link ThrowableToIntFunction} which always returns a given value.
     *
     * @param <T> The type of argument for the function
     * @param ret The return value for the constant
     * @return A {@code ThrowableToIntFunction} which always returns a given value.
     */
    static <T> ThrowableToIntFunction<T> constant(int ret) {
        return t -> ret;
    }

    /**
     * The apply method for this {@link ToIntFunction} which is able to throw any {@link Exception} type.
     *
     * @param t The argument for the function
     * @return The return value from the function, which is its result.
     * @throws Exception Any exception from this functions action
     */
    int applyAsIntThrows(T t) throws Exception;

    /**
     * Overrides the {@link ToIntFunction#applyAsInt(Object)} method by using a redefinition as default method. It
     * calls the {@link #applyAsIntThrows(Object)} method of this interface and catches the thrown {@link Exception}s
     * from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types are
     * sneakily thrown.
     *
     * @param t The argument for the function
     * @return The return value from the function, which is its result.
     * @see at.gridtec.internals.lang.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default int applyAsInt(T t) {
        try {
            return applyAsIntThrows(t);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableToIntFunction} that applies this {@code ThrowableToIntFunction} to its input,
     * and if an error occurred, applies the given one. The exception from this {@code ThrowableToIntFunction} is
     * ignored.
     *
     * @param other A {@code ThrowableToIntFunction} to be applied if this one fails
     * @return A composed {@code ThrowableToIntFunction} that applies this {@code ThrowableToIntFunction}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableToIntFunction<T> orElse(final ThrowableToIntFunction<? super T> other) {
        Objects.requireNonNull(other);
        return t -> {
            try {
                return applyAsIntThrows(t);
            } catch (Exception ignored) {
                return other.applyAsIntThrows(t);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableToIntFunction} that applies this {@code ThrowableToIntFunction} to its input,
     * and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableToIntFunction} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableToIntFunction} that applies this {@code ThrowableToIntFunction}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableToIntFunction<T> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return t -> {
            try {
                return applyAsIntThrows(t);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ToIntFunction} that applies this {@link ThrowableToIntFunction} to its input, and if
     * an error occurred, applies the given {@code ToIntFunction} representing a fallback. The exception from this
     * {@code ThrowableToIntFunction} is ignored.
     *
     * @param fallback A {@code ToIntFunction} to be applied if this one fails
     * @return A composed {@code ToIntFunction} that applies this {@code ThrowableToIntFunction}, and if an error
     * occurred, applies the given {@code ToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ToIntFunction<T> fallbackTo(final ToIntFunction<? super T> fallback) {
        Objects.requireNonNull(fallback);
        return t -> {
            try {
                return applyAsIntThrows(t);
            } catch (Exception ignored) {
                return fallback.applyAsInt(t);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableToIntFunction} that applies this {@code ThrowableToIntFunction} to its input,
     * additionally performing the provided action to the resulting value. This method exists mainly to support
     * debugging.
     *
     * @param action A {@link IntConsumer} to be applied additionally to this {@code ThrowableToIntFunction}
     * @return A composed {@code ThrowableToIntFunction} that applies this {@code ThrowableToIntFunction}, additionally
     * performing the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableToIntFunction<T> peek(final IntConsumer action) {
        Objects.requireNonNull(action);
        return t -> {
            final int ret = applyAsInt(t);
            action.accept(ret);
            return ret;
        };
    }

    /**
     * Returns a composed {@link ToIntFunction} that applies this {@link ThrowableToIntFunction} to its input, and if
     * an error occurred, returns the given value. The exception from this {@code ThrowableToIntFunction} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableToIntFunction} fails
     * @return A composed {@code ToIntFunction} that applies this {@code ThrowableToIntFunction}, and if an error
     * occurred, returns the given value.
     */
    default ToIntFunction<T> orReturn(int value) {
        return t -> {
            try {
                return applyAsIntThrows(t);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link ToIntFunction} that applies this {@link ThrowableToIntFunction} to its input, and if
     * an error occurred, returns the supplied value from the given {@link IntSupplier}. The exception from this {@code
     * ThrowableToIntFunction} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableToIntFunction} fails
     * @return A composed {@code ToIntFunction} that applies this {@code ThrowableToIntFunction}, and if an error
     * occurred, the supplied value from the given {@code IntSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ToIntFunction<T> orReturn(final IntSupplier supplier) {
        Objects.requireNonNull(supplier);
        return t -> {
            try {
                return applyAsIntThrows(t);
            } catch (Exception ignored) {
                return supplier.getAsInt();
            }
        };
    }
}
