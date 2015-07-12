/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.gridtec.lambda4j.function.throwable.operators.binary;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.Supplier;

/**
 * This functional interface implements a {@link DoubleBinaryOperator} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDoubleThrows(double, double)}.
 *
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableDoubleBinaryOperator extends DoubleBinaryOperator {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableDoubleBinaryOperator}. This is a
     * convenience method in case the given {@link ThrowableDoubleBinaryOperator} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableDoubleBinaryOperator} is returned as-is.
     *
     * @param lambda The {@code ThrowableDoubleBinaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableDoubleBinaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableDoubleBinaryOperator wrap(final ThrowableDoubleBinaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableDoubleBinaryOperator} from the given {@link DoubleBinaryOperator}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code DoubleBinaryOperator} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableDoubleBinaryOperator} from the given {@code DoubleBinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableDoubleBinaryOperator from(final DoubleBinaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsDouble;
    }

    /**
     * Creates a {@link ThrowableDoubleBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableDoubleBinaryOperator} which always returns a given value.
     */
    static ThrowableDoubleBinaryOperator constant(double ret) {
        return (left, right) -> ret;
    }

    /**
     * The apply method for this {@link DoubleBinaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    double applyAsDoubleThrows(double left, double right) throws Exception;

    /**
     * Overrides the {@link DoubleBinaryOperator#applyAsDouble(double, double)} method by using a redefinition as
     * default method. It calls the {@link #applyAsDoubleThrows(double, double)} method of this interface and catches
     * the thrown {@link Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown.
     * Other throwable types are sneakily thrown.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default double applyAsDouble(double left, double right) {
        try {
            return applyAsDoubleThrows(left, right);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableDoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator}
     * to its input, and if an  error occurred, applies the given one. The exception from this {@code
     * ThrowableDoubleBinaryOperator} is ignored.
     *
     * @param other A {@code ThrowableDoubleBinaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableDoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator},
     * and if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableDoubleBinaryOperator orElse(final ThrowableDoubleBinaryOperator other) {
        Objects.requireNonNull(other);
        return (left, right) -> {
            try {
                return applyAsDoubleThrows(left, right);
            } catch (Exception ignored) {
                return other.applyAsDoubleThrows(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableDoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator}
     * to its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableDoubleBinaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableDoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator},
     * and if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableDoubleBinaryOperator orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (left, right) -> {
            try {
                return applyAsDoubleThrows(left, right);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link DoubleBinaryOperator} that applies this {@link ThrowableDoubleBinaryOperator} to its
     * input, and if an error occurred, applies the given {@code DoubleBinaryOperator} representing a fallback. The
     * exception from this {@code ThrowableDoubleBinaryOperator} is ignored.
     *
     * @param fallback A {@code DoubleBinaryOperator} to be applied if this one fails
     * @return A composed {@code DoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator}, and if
     * an error occurred, applies the given {@code DoubleBinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default DoubleBinaryOperator fallbackTo(final DoubleBinaryOperator fallback) {
        Objects.requireNonNull(fallback);
        return (left, right) -> {
            try {
                return applyAsDoubleThrows(left, right);
            } catch (Exception ignored) {
                return fallback.applyAsDouble(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableDoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator}
     * to its input, additionally performing the provided action to the resulting value. This method exists mainly to
     * support debugging.
     *
     * @param action A {@link DoubleConsumer} to be applied additionally to this {@code ThrowableDoubleBinaryOperator}
     * @return A composed {@code ThrowableDoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator},
     * additionally performing the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableDoubleBinaryOperator peek(final DoubleConsumer action) {
        Objects.requireNonNull(action);
        return (left, right) -> {
            final double ret = applyAsDouble(left, right);
            action.accept(ret);
            return ret;
        };
    }

    /**
     * Returns a composed {@link DoubleBinaryOperator} that applies this {@link ThrowableDoubleBinaryOperator} to its
     * input, and if an error occurred, returns the given value. The exception from this {@code
     * ThrowableDoubleBinaryOperator} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableDoubleBinaryOperator} fails
     * @return A composed {@code DoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator}, and if
     * an error occurred, returns the given value.
     */
    default DoubleBinaryOperator orReturn(double value) {
        return (left, right) -> {
            try {
                return applyAsDoubleThrows(left, right);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link DoubleBinaryOperator} that applies this {@link ThrowableDoubleBinaryOperator} to its
     * input, and if an error occurred, returns the supplied value from the given {@link Supplier}. The exception from
     * this {@code ThrowableDoubleBinaryOperator} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableDoubleBinaryOperator}
     * fails
     * @return A composed {@code DoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator}, and if
     * an error occurred, the supplied value from the given {@code Supplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default DoubleBinaryOperator orReturn(final Supplier<? extends Double> supplier) {
        Objects.requireNonNull(supplier);
        return (left, right) -> {
            try {
                return applyAsDoubleThrows(left, right);
            } catch (Exception ignored) {
                return supplier.get();
            }
        };
    }

    /**
     * Returns a composed {@link DoubleBinaryOperator} that applies this {@link ThrowableDoubleBinaryOperator} to its
     * input, and if an error occurred, returns the left value from this operator. The exception from this {@code
     * ThrowableDoubleBinaryOperator} is ignored.
     *
     * @return A composed {@code DoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator}, and if
     * an error occurred, returns the left value from this operator.
     */
    default DoubleBinaryOperator orReturnLeft() {
        return (left, right) -> {
            try {
                return applyAsDoubleThrows(left, right);
            } catch (Exception ignored) {
                return left;
            }
        };
    }

    /**
     * Returns a composed {@link DoubleBinaryOperator} that applies this {@link ThrowableDoubleBinaryOperator} to its
     * input, and if an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableDoubleBinaryOperator} is ignored.
     *
     * @return A composed {@code DoubleBinaryOperator} that applies this {@code ThrowableDoubleBinaryOperator}, and if
     * an error occurred, returns the right value from this operator.
     */
    default DoubleBinaryOperator orReturnRight() {
        return (left, right) -> {
            try {
                return applyAsDoubleThrows(left, right);
            } catch (Exception ignored) {
                return right;
            }
        };
    }
}
