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
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This functional interface implements a {@link Function} which is able to throw any {@link Exception}.
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
 * @param <T> The type of argument for the function
 * @param <R> The type of return value from the function
 * @apiNote This is a throwable JRE lambda
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableFunction<T, R> extends Function<T, R> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableFunction}. This is a convenience method
     * in case the given {@link ThrowableFunction} is ambiguous for the compiler. This might happen for overloaded
     * methods accepting different functional interfaces. The given {@code ThrowableFunction} is returned as-is.
     *
     * @param <T> The type of argument for the function
     * @param <R> The type of return value from the function
     * @param lambda The {@code ThrowableFunction} which should be returned as-is.
     * @return The given {@code ThrowableFunction} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, R> ThrowableFunction<T, R> wrap(final ThrowableFunction<T, R> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableFunction} which always returns a given value.
     *
     * @param <T> The type of argument for the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ThrowableFunction} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, R> ThrowableFunction<T, R> constant(R r) {
        Objects.requireNonNull(r);
        return t -> r;
    }

    /**
     * The apply method for this {@link Function} which is able to throw any {@link Exception} type.
     *
     * @param t The argument for the function
     * @return The return value from the function, which is its result.
     * @throws Exception Any exception from this functions action
     */
    R applyThrows(T t) throws Exception;

    /**
     * Overrides the {@link Function#apply(Object)} method by using a redefinition as default method. It calls the
     * {@link #applyThrows(Object)} method of this interface and catches the thrown {@link Exception}s from it. If it
     * is of type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @param t The argument for the function
     * @return The return value from the function, which is its result.
     * @see at.gridtec.internals.lang.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default R apply(T t) {
        try {
            return applyThrows(t);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableFunction} that applies this {@code ThrowableFunction} to its input, and if an
     * error occurred, applies the given one. The exception from this {@code ThrowableFunction} is ignored.
     *
     * @param other A {@code ThrowableFunction} to be applied if this one fails
     * @return A composed {@code ThrowableFunction} that applies this {@code ThrowableFunction}, and if an error
     * occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableFunction<T, R> orElse(final ThrowableFunction<? super T, ? extends R> other) {
        Objects.requireNonNull(other);
        return t -> {
            try {
                return applyThrows(t);
            } catch (Exception ignored) {
                return other.applyThrows(t);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableFunction} that applies this {@code ThrowableFunction} to its input, and if an
     * error occurred, throws the given {@link Exception}. The exception from this {@code ThrowableFunction} is added
     * as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableFunction} that applies this {@code ThrowableFunction}, and if an error
     * occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableFunction<T, R> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return t -> {
            try {
                return applyThrows(t);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link Function} that applies this {@link ThrowableFunction} to its input, and if an error
     * occurred, applies the given {@code Function} representing a fallback. The exception from this {@code
     * ThrowableFunction} is ignored.
     *
     * @param fallback A {@code Function} to be applied if this one fails
     * @return A composed {@code Function} that applies this {@code ThrowableFunction}, and if an error occurred,
     * applies the given {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default Function<T, R> fallbackTo(final Function<? super T, ? extends R> fallback) {
        Objects.requireNonNull(fallback);
        return t -> {
            try {
                return applyThrows(t);
            } catch (Exception ignored) {
                return fallback.apply(t);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableFunction} that applies this {@code ThrowableFunction} to its input,
     * additionally performing the provided action to the resulting value. This method exists mainly to support
     * debugging.
     *
     * @param action A {@link Consumer} to be applied additionally to this {@code ThrowableFunction}
     * @return A composed {@code ThrowableFunction} that applies this {@code ThrowableFunction}, additionally performing
     * the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableFunction<T, R> peek(final Consumer<? super R> action) {
        Objects.requireNonNull(action);
        return t -> {
            final R r = apply(t);
            action.accept(r);
            return r;
        };
    }

    /**
     * Returns a composed {@link Function} that applies this {@link ThrowableFunction} to its input, and if an error
     * occurred, returns the given value. The exception from this {@code ThrowableFunction} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableFunction} fails
     * @return A composed {@code Function} that applies this {@code ThrowableFunction}, and if an error occurred,
     * returns the given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default Function<T, R> orReturn(final R value) {
        Objects.requireNonNull(value);
        return t -> {
            try {
                return applyThrows(t);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link Function} that applies this {@link ThrowableFunction} to its input, and if an
     * error occurred, returns the supplied value from the given {@link Supplier}. The exception from this {@code
     * ThrowableFunction} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableFunction} fails
     * @return A composed {@code Function} that applies this {@code ThrowableFunction}, and if an error occurred,
     * the supplied value from the given {@code Supplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default Function<T, R> orReturn(final Supplier<? extends R> supplier) {
        Objects.requireNonNull(supplier);
        return t -> {
            try {
                return applyThrows(t);
            } catch (Exception ignored) {
                return supplier.get();
            }
        };
    }
}