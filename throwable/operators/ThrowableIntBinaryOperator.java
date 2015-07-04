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
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

/**
 * This functional interface implements a {@link IntBinaryOperator} which is able to throw any {@link Exception}.
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
 * @see java.util.function.BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableIntBinaryOperator extends IntBinaryOperator {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableIntBinaryOperator}. This is a
     * convenience method in case the given {@link ThrowableIntBinaryOperator} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableIntBinaryOperator} is returned as-is.
     *
     * @param lambda The {@code ThrowableIntBinaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableIntBinaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableIntBinaryOperator wrap(final ThrowableIntBinaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableIntBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableIntBinaryOperator} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableIntBinaryOperator constant(int ret) {
        Objects.requireNonNull(ret);
        return (left, right) -> ret;
    }

    /**
     * The apply method for this {@link IntBinaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    int applyAsIntThrows(int left, int right) throws Exception;

    /**
     * Overrides the {@link IntBinaryOperator#applyAsInt(int, int)} method by using a redefinition as default method.
     * It calls the {@link #applyAsIntThrows(int, int)} method of this interface and catches the thrown {@link
     * Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other throwable types
     * are sneakily thrown.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default int applyAsInt(int left, int right) {
        try {
            return applyAsIntThrows(left, right);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableIntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator} to
     * its input, and if an  error occurred, applies the given one. The exception from this {@code
     * ThrowableIntBinaryOperator} is ignored.
     *
     * @param other A {@code ThrowableIntBinaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableIntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and
     * if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableIntBinaryOperator orElse(final ThrowableIntBinaryOperator other) {
        Objects.requireNonNull(other);
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return other.applyAsIntThrows(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableIntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator} to
     * its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableIntBinaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableIntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and
     * if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableIntBinaryOperator orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that applies this {@link ThrowableIntBinaryOperator} to its
     * input, and if an error occurred, applies the given {@code IntBinaryOperator} representing a fallback. The
     * exception from this {@code ThrowableIntBinaryOperator} is ignored.
     *
     * @param fallback A {@code IntBinaryOperator} to be applied if this one fails
     * @return A composed {@code IntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and if an
     * error occurred, applies the given {@code IntBinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default IntBinaryOperator fallbackTo(final IntBinaryOperator fallback) {
        Objects.requireNonNull(fallback);
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return fallback.applyAsInt(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableIntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator} to
     * its input, additionally performing the provided action to the resulting value. This method exists mainly to
     * support debugging.
     *
     * @param action A {@link IntConsumer} to be applied additionally to this {@code ThrowableIntBinaryOperator}
     * @return A composed {@code ThrowableIntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator},
     * additionally performing the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableIntBinaryOperator peek(final IntConsumer action) {
        Objects.requireNonNull(action);
        return (left, right) -> {
            final int ret = applyAsInt(left, right);
            action.accept(ret);
            return ret;
        };
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that applies this {@link ThrowableIntBinaryOperator} to its input,
     * and if an error occurred, returns the given value. The exception from this {@code ThrowableIntBinaryOperator} is
     * ignored.
     *
     * @param value The value to be returned if this {@code ThrowableIntBinaryOperator} fails
     * @return A composed {@code IntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and if an
     * error occurred, returns the given value.
     */
    default IntBinaryOperator orReturn(int value) {
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that applies this {@link ThrowableIntBinaryOperator} to its input,
     * and if an error occurred, returns the supplied value from the given {@link Supplier}. The exception from this
     * {@code ThrowableIntBinaryOperator} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableIntBinaryOperator} fails
     * @return A composed {@code IntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and if an
     * error occurred, the supplied value from the given {@code Supplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default IntBinaryOperator orReturn(final Supplier<? extends Integer> supplier) {
        Objects.requireNonNull(supplier);
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return supplier.get();
            }
        };
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that applies this {@link ThrowableIntBinaryOperator} to its input,
     * and if an error occurred, returns the left value from this operator. The exception from this {@code
     * ThrowableIntBinaryOperator} is ignored.
     *
     * @return A composed {@code IntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and if an
     * error occurred, returns the left value from this operator.
     */
    default IntBinaryOperator orReturnLeft() {
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return left;
            }
        };
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that applies this {@link ThrowableIntBinaryOperator} to its input,
     * and if an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableIntBinaryOperator} is ignored.
     *
     * @return A composed {@code IntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and if an
     * error occurred, returns the right value from this operator.
     */
    default IntBinaryOperator orReturnRight() {
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return right;
            }
        };
    }
}
