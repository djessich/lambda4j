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
package at.gridtec.lambda4j.operators.binary;

import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.supplier.FloatSupplier;
import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * This functional interface implements a {@link FloatBinaryOperator} which is able to throw any {@link Exception}.
 * <p>
 * The thrown {@link Exception} is sneakily thrown unless its a {@link RuntimeException}. This means that there is no
 * need to catch the thrown exception, nor to declare that you throw it using the <em>throws</em> keyword. The exception
 * is still thrown, but the Java compiler stops warning about it.
 * <p>
 * However, when using this throwing lambda, be aware of the following consequences: <ol> <li>If the calling code is to
 * handle a thrown {@code Exception}, it MUST be declared in the methods <em>throws</em> clause which uses this lambda.
 * The compiler will not force you to add it.</li> <li>If the calling code already handles a thrown {@code Exception},
 * it needs to be declared in the methods <em>throws</em> clause which uses this lambda. If not the compiler prints an
 * error that the corresponding {@code try} block never throws the specific exception.</li> <li>In any case, there is no
 * way of explicitly catching the thrown {@code Exception} in the method which uses this lambda. If you try, the
 * compiler prints an error that the corresponding {@code try} block never throws the specific exception.</li> </ol>
 * <p>
 * When the calling code never throws the specific exception that it declares, you should omit it. For example: {@code
 * new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but UTF-8 is guaranteed by the Java specification
 * to be always present. The exception should therefore be omitted.
 * <p>
 * Moreover, if no checked exception should be used at all or its use is inappropriate for any reasons, omit the
 * declaration in the <em>throws</em> clause. The checked exception will behave just like a normal <b>unchecked</b>
 * exception due to sneaky throwing.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloatThrows(float, float)}.
 *
 * @apiNote This is a throwable JRE lambda
 * @see BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableFloatBinaryOperator extends FloatBinaryOperator {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableFloatBinaryOperator}. This is a
     * convenience method in case the given {@link ThrowableFloatBinaryOperator} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableFloatBinaryOperator} is returned as-is.
     *
     * @param lambda The {@code ThrowableFloatBinaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableFloatBinaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatBinaryOperator wrap(final ThrowableFloatBinaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableFloatBinaryOperator} from the given {@link FloatBinaryOperator}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code FloatBinaryOperator} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableFloatBinaryOperator} from the given {@code FloatBinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatBinaryOperator from(final FloatBinaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsFloat;
    }

    /**
     * Creates a {@link ThrowableFloatBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableFloatBinaryOperator} which always returns a given value.
     */
    static ThrowableFloatBinaryOperator constant(float ret) {
        return (left, right) -> ret;
    }

    /**
     * Returns a {@link ThrowableFloatBinaryOperator} which returns the lesser of two elements, according to the
     * specified {@code Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ThrowableFloatBinaryOperator} which returns the lesser of two elements, according to the
     * supplied {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatBinaryOperator minBy(final Comparator<? super Float> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link ThrowableFloatBinaryOperator} which returns the greater of two elements, according to the
     * specified {@code Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ThrowableFloatBinaryOperator} which returns the greater of two elements, according to the
     * supplied {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatBinaryOperator maxBy(final Comparator<? super Float> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    /**
     * Returns a {@link ThrowableFloatBinaryOperator} which returns the lesser of two elements according to {@code left
     * &lt;= right} operation.
     *
     * @return A {@code ThrowableFloatBinaryOperator} which returns the lesser of two elements.
     * @see BinaryOperator#minBy(Comparator)
     * @see Float#min(float, float)
     * @see Math#min(float, float)
     */
    static ThrowableFloatBinaryOperator min() {
        return Float::min;
    }

    /**
     * Returns a {@link ThrowableFloatBinaryOperator} which returns the greater of two elements according to {@code left
     * &gt;= right} operation.
     *
     * @return A {@code ThrowableFloatBinaryOperator} which returns the greater of two elements.
     * @see BinaryOperator#maxBy(Comparator)
     * @see Float#max(float, float)
     * @see Math#max(float, float)
     */
    static ThrowableFloatBinaryOperator max() {
        return Float::max;
    }

    /**
     * Creates a {@link FloatBinaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code FloatBinaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static FloatBinaryOperator onlyLeft(final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsFloat(left);
    }

    /**
     * Creates a {@link FloatBinaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code FloatBinaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static FloatBinaryOperator onlyRight(final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsFloat(right);
    }

    /**
     * The apply method for this {@link FloatBinaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    float applyAsFloatThrows(float left, float right) throws Exception;

    /**
     * Overrides the {@link FloatBinaryOperator#applyAsFloat(float, float)} method by using a redefinition as default
     * method. It calls the {@link #applyAsFloatThrows(float, float)} method of this interface and catches the thrown
     * {@link Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other throwable
     * types are sneakily thrown.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default float applyAsFloat(float left, float right) {
        try {
            return applyAsFloatThrows(left, right);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableFloatBinaryOperator} that applies this {@code ThrowableFloatBinaryOperator} to
     * its input, and if an error occurred, applies the given one. The exception from this {@code
     * ThrowableFloatBinaryOperator} is ignored.
     *
     * @param other A {@code ThrowableFloatBinaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableFloatBinaryOperator} that applies this {@code ThrowableFloatBinaryOperator},
     * and if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableFloatBinaryOperator orElse(final ThrowableFloatBinaryOperator other) {
        Objects.requireNonNull(other);
        return (left, right) -> {
            try {
                return applyAsFloatThrows(left, right);
            } catch (Exception ignored) {
                return other.applyAsFloatThrows(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableFloatBinaryOperator} that applies this {@code ThrowableFloatBinaryOperator} to
     * its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableFloatBinaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableFloatBinaryOperator} that applies this {@code ThrowableFloatBinaryOperator},
     * and if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableFloatBinaryOperator orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (left, right) -> {
            try {
                return applyAsFloatThrows(left, right);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that applies this {@link ThrowableFloatBinaryOperator} to its
     * input, and if an error occurred, applies the given {@code FloatBinaryOperator} representing a fallback. The
     * exception from this {@code ThrowableFloatBinaryOperator} is ignored.
     *
     * @param fallback A {@code FloatBinaryOperator} to be applied if this one fails
     * @return A composed {@code FloatBinaryOperator} that applies this {@code ThrowableFloatBinaryOperator}, and if an
     * error occurred, applies the given {@code FloatBinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatBinaryOperator fallbackTo(final FloatBinaryOperator fallback) {
        Objects.requireNonNull(fallback);
        return (left, right) -> {
            try {
                return applyAsFloatThrows(left, right);
            } catch (Exception ignored) {
                return fallback.applyAsFloat(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that applies this {@link ThrowableFloatBinaryOperator} to its
     * input, and if an error occurred, returns the given value. The exception from this {@code
     * ThrowableFloatBinaryOperator} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableFloatBinaryOperator} fails
     * @return A composed {@code FloatBinaryOperator} that applies this {@code ThrowableFloatBinaryOperator}, and if an
     * error occurred, returns the given value.
     */
    default FloatBinaryOperator orReturn(float value) {
        return (left, right) -> {
            try {
                return applyAsFloatThrows(left, right);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that applies this {@link ThrowableFloatBinaryOperator} to its
     * input, and if an error occurred, returns the supplied value from the given {@link FloatSupplier}. The exception
     * from this {@code ThrowableFloatBinaryOperator} is ignored.
     *
     * @param supplier A {@code FloatSupplier} to return a supplied value if this {@code ThrowableFloatBinaryOperator}
     * fails
     * @return A composed {@code FloatBinaryOperator} that applies this {@code ThrowableFloatBinaryOperator}, and if an
     * error occurred, the supplied value from the given {@code FloatSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatBinaryOperator orReturn(final FloatSupplier supplier) {
        Objects.requireNonNull(supplier);
        return (left, right) -> {
            try {
                return applyAsFloatThrows(left, right);
            } catch (Exception ignored) {
                return supplier.getAsFloat();
            }
        };
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that applies this {@link ThrowableFloatBinaryOperator} to its
     * input, and if an error occurred, returns the left value from this operator. The exception from this {@code
     * ThrowableFloatBinaryOperator} is ignored.
     *
     * @return A composed {@code FloatBinaryOperator} that applies this {@code ThrowableFloatBinaryOperator}, and if an
     * error occurred, returns the left value from this operator.
     */
    default FloatBinaryOperator orReturnLeft() {
        return (left, right) -> {
            try {
                return applyAsFloatThrows(left, right);
            } catch (Exception ignored) {
                return left;
            }
        };
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that applies this {@link ThrowableFloatBinaryOperator} to its
     * input, and if an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableFloatBinaryOperator} is ignored.
     *
     * @return A composed {@code FloatBinaryOperator} that applies this {@code ThrowableFloatBinaryOperator}, and if an
     * error occurred, returns the right value from this operator.
     */
    default FloatBinaryOperator orReturnRight() {
        return (left, right) -> {
            try {
                return applyAsFloatThrows(left, right);
            } catch (Exception ignored) {
                return right;
            }
        };
    }
}
