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
package at.gridtec.internals.lang.function.throwable.operators;

import at.gridtec.internals.lang.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * This functional interface implements a {@link UnaryOperator} which is able to throw any {@link Exception}.
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
 * @param <T> The type of the argument and the return value for this operator
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableUnaryOperator<T> extends UnaryOperator<T> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableUnaryOperator}. This is a convenience
     * method in case the given {@link ThrowableUnaryOperator} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableUnaryOperator} is
     * returned as-is.
     *
     * @param <T> The type of argument for the function
     * @param lambda The {@code ThrowableUnaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableUnaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableUnaryOperator<T> wrap(final ThrowableUnaryOperator<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableUnaryOperator} which always returns a given value.
     *
     * @param <T> The type of argument for the function
     * @param r The return value for the constant
     * @return A {@code ThrowableUnaryOperator} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableUnaryOperator<T> constant(T r) {
        Objects.requireNonNull(r);
        return t -> r;
    }

    /**
     * The apply method for this {@link UnaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param t The first argument for the operator
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    T applyThrows(T t) throws Exception;

    /**
     * Overrides the {@link UnaryOperator#apply(Object)} method by using a redefinition as default method. It calls the
     * {@link #applyThrows(Object)} method of this interface and catches the thrown {@link Exception}s from it. If it
     * is of type {@link RuntimeException}, the exception is rethrown. Other throwable types are sneakily thrown.
     *
     * @param t The first argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default T apply(T t) {
        try {
            return applyThrows(t);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableUnaryOperator} that applies this {@code ThrowableUnaryOperator} to its input,
     * and if an  error occurred, applies the given one. The exception from this {@code ThrowableUnaryOperator} is
     * ignored.
     *
     * @param other A {@code ThrowableUnaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableUnaryOperator} that applies this {@code ThrowableUnaryOperator}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableUnaryOperator<T> orElse(final ThrowableUnaryOperator<T> other) {
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
     * Returns a composed {@link ThrowableUnaryOperator} that applies this {@code ThrowableUnaryOperator} to its input,
     * and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableUnaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableUnaryOperator} that applies this {@code ThrowableUnaryOperator}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableUnaryOperator<T> orThrow(Class<X> clazz) {
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
     * Returns a composed {@link UnaryOperator} that applies this {@link ThrowableUnaryOperator} to its input, and if
     * an error occurred, applies the given {@code UnaryOperator} representing a fallback. The exception from this
     * {@code ThrowableUnaryOperator} is ignored.
     *
     * @param fallback A {@code UnaryOperator} to be applied if this one fails
     * @return A composed {@code UnaryOperator} that applies this {@code ThrowableUnaryOperator}, and if an error
     * occurred, applies the given {@code UnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default UnaryOperator<T> fallbackTo(final UnaryOperator<T> fallback) {
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
     * Returns a composed {@link ThrowableUnaryOperator} that applies this {@code ThrowableUnaryOperator} to its input,
     * additionally performing the provided action to the resulting value. This method exists mainly to support
     * debugging.
     *
     * @param action A {@link Consumer} to be applied additionally to this {@code ThrowableUnaryOperator}
     * @return A composed {@code ThrowableUnaryOperator} that applies this {@code ThrowableUnaryOperator}, additionally
     * performing the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableUnaryOperator<T> peek(final Consumer<? super T> action) {
        Objects.requireNonNull(action);
        return t -> {
            final T ret = apply(t);
            action.accept(ret);
            return ret;
        };
    }

    /**
     * Returns a composed {@link UnaryOperator} that applies this {@link ThrowableUnaryOperator} to its input, and if
     * an error occurred, returns the given value. The exception from this {@code ThrowableUnaryOperator} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableUnaryOperator} fails
     * @return A composed {@code UnaryOperator} that applies this {@code ThrowableUnaryOperator}, and if an error
     * occurred, returns the given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default UnaryOperator<T> orReturn(final T value) {
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
     * Returns a composed {@link UnaryOperator} that applies this {@link ThrowableUnaryOperator} to its input, and if
     * an error occurred, returns the supplied value from the given {@link Supplier}. The exception from this {@code
     * ThrowableUnaryOperator} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableUnaryOperator} fails
     * @return A composed {@code UnaryOperator} that applies this {@code ThrowableUnaryOperator}, and if an error
     * occurred, the supplied value from the given {@code Supplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default UnaryOperator<T> orReturn(final Supplier<? extends T> supplier) {
        Objects.requireNonNull(supplier);
        return t -> {
            try {
                return applyThrows(t);
            } catch (Exception ignored) {
                return supplier.get();
            }
        };
    }

    /**
     * Returns a composed {@link UnaryOperator} that applies this {@link ThrowableUnaryOperator} to its input, and if
     * an error occurred, returns its value. The exception from this {@code ThrowableUnaryOperator} is ignored.
     *
     * @return A composed {@code UnaryOperator} that applies this {@code ThrowableUnaryOperator}, and if an error
     * occurred, returns its value.
     */
    default UnaryOperator<T> orReturnSelf() {
        return t -> {
            try {
                return applyThrows(t);
            } catch (Exception ignored) {
                return t;
            }
        };
    }
}
