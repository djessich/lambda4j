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
import java.util.function.ToIntBiFunction;

/**
 * This functional interface implements a {@link ToIntBiFunction} which is able to throw any {@link Exception}.
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
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableToIntBiFunction<T, U> extends ToIntBiFunction<T, U> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableToIntBiFunction}. This is a convenience
     * method in case the given {@link ThrowableToIntBiFunction} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableToIntBiFunction} is
     * returned as-is.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param lambda The {@code ThrowableToIntBiFunction} which should be returned as-is.
     * @return The given {@code ThrowableToIntBiFunction} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableToIntBiFunction<T, U> wrap(final ThrowableToIntBiFunction<T, U> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableToIntBiFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ThrowableToIntBiFunction} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableToIntBiFunction<T, U> constant(int ret) {
        Objects.requireNonNull(ret);
        return (t, u) -> ret;
    }

    /**
     * The apply method for this {@link ToIntBiFunction} which is able to throw any {@link Exception} type.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     * @throws Exception Any exception from this functions action
     */
    int applyAsIntThrows(T t, U u) throws Exception;

    /**
     * Overrides the {@link ToIntBiFunction#applyAsInt(Object, Object)} method by using a redefinition as default
     * method. It calls the {@link #applyAsIntThrows(Object, Object)} method of this interface and catches the thrown
     * {@link Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other
     * exception types are sneakily thrown.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     * @see at.gridtec.internals.lang.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default int applyAsInt(T t, U u) {
        try {
            return applyAsIntThrows(t, u);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableToIntBiFunction} that applies this {@code ThrowableToIntBiFunction} to its
     * input, and if an error occurred, applies the given one. The exception from this {@code ThrowableToIntBiFunction}
     * is ignored.
     *
     * @param other A {@code ThrowableToIntBiFunction} to be applied if this one fails
     * @return A composed {@code ThrowableToIntBiFunction} that applies this {@code ThrowableToIntBiFunction}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableToIntBiFunction<T, U> orElse(ThrowableToIntBiFunction<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u) -> {
            try {
                return applyAsIntThrows(t, u);
            } catch (Exception ignored) {
                return other.applyAsIntThrows(t, u);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableToIntBiFunction} that applies this {@code ThrowableToIntBiFunction} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableToIntBiFunction} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableToIntBiFunction} that applies this {@code ThrowableToIntBiFunction}, and if
     * an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableToIntBiFunction<T, U> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (t, u) -> {
            try {
                return applyAsIntThrows(t, u);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ToIntBiFunction} that applies this {@link ThrowableToIntBiFunction} to its input, and
     * if an error occurred, applies the given {@code ToIntBiFunction} representing a fallback. The exception from this
     * {@code ThrowableToIntBiFunction} is ignored.
     *
     * @param fallback A {@code ToIntBiFunction} to be applied if this one fails
     * @return A composed {@code ToIntBiFunction} that applies this {@code ThrowableToIntBiFunction}, and if an error
     * occurred, applies the given {@code ToIntBiFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ToIntBiFunction<T, U> fallbackTo(final ToIntBiFunction<? super T, ? super U> fallback) {
        Objects.requireNonNull(fallback);
        return (t, u) -> {
            try {
                return applyAsIntThrows(t, u);
            } catch (Exception ignored) {
                return fallback.applyAsInt(t, u);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableToIntBiFunction} that applies this {@code ThrowableToIntBiFunction} to its
     * input, additionally performing the provided action to the resulting value. This method exists mainly to support
     * debugging.
     *
     * @param action A {@link IntConsumer} to be applied additionally to this {@code ThrowableToIntBiFunction}
     * @return A composed {@code ThrowableToIntBiFunction} that applies this {@code ThrowableToIntBiFunction},
     * additionally performing the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableToIntBiFunction<T, U> peek(final IntConsumer action) {
        Objects.requireNonNull(action);
        return (t, u) -> {
            final int ret = applyAsInt(t, u);
            action.accept(ret);
            return ret;
        };
    }

    /**
     * Returns a composed {@link ToIntBiFunction} that applies this {@link ThrowableToIntBiFunction} to its input, and
     * if an error occurred, returns the given value. The exception from this {@code ThrowableToIntBiFunction} is
     * ignored.
     *
     * @param value The value to be returned if this {@code ThrowableToIntBiFunction} fails
     * @return A composed {@code ToIntBiFunction} that applies this {@code ThrowableToIntBiFunction}, and if an error
     * occurred, returns the given value.
     */
    default ToIntBiFunction<T, U> orReturn(int value) {
        return (t, u) -> {
            try {
                return applyAsIntThrows(t, u);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link ToIntBiFunction} that applies this {@link ThrowableToIntBiFunction} to its input, and
     * if an error occurred, returns the supplied value from the given {@link IntSupplier}. The exception from this
     * {@code ThrowableToIntBiFunction} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableToIntBiFunction} fails
     * @return A composed {@code ToIntBiFunction} that applies this {@code ThrowableToIntBiFunction}, and if an error
     * occurred, the supplied value from the given {@code IntSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ToIntBiFunction<T, U> orReturn(final IntSupplier supplier) {
        Objects.requireNonNull(supplier);
        return (t, u) -> {
            try {
                return applyAsIntThrows(t, u);
            } catch (Exception ignored) {
                return supplier.getAsInt();
            }
        };
    }
}
