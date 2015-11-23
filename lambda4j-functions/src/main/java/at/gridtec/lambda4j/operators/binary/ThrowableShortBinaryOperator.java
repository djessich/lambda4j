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

import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;
import at.gridtec.lambda4j.supplier.ShortSupplier;
import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * This functional interface implements a {@link ShortBinaryOperator} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShortThrows(short, short)}.
 *
 * @apiNote This is a throwable JRE lambda
 * @see BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableShortBinaryOperator extends ShortBinaryOperator {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableShortBinaryOperator}. This is a
     * convenience method in case the given {@link ThrowableShortBinaryOperator} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableShortBinaryOperator} is returned as-is.
     *
     * @param lambda The {@code ThrowableShortBinaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableShortBinaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableShortBinaryOperator wrap(final ThrowableShortBinaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableShortBinaryOperator} from the given {@link ShortBinaryOperator}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code ShortBinaryOperator} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableShortBinaryOperator} from the given {@code ShortBinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableShortBinaryOperator from(final ShortBinaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsShort;
    }

    /**
     * Creates a {@link ThrowableShortBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableShortBinaryOperator} which always returns a given value.
     */
    static ThrowableShortBinaryOperator constant(short ret) {
        return (left, right) -> ret;
    }

    /**
     * Returns a {@link ThrowableShortBinaryOperator} which returns the lesser of two elements, according to the
     * specified {@code Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ThrowableShortBinaryOperator} which returns the lesser of two elements, according to the
     * supplied {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableShortBinaryOperator minBy(final Comparator<? super Short> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link ThrowableShortBinaryOperator} which returns the greater of two elements, according to the
     * specified {@code Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ThrowableShortBinaryOperator} which returns the greater of two elements, according to the
     * supplied {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableShortBinaryOperator maxBy(final Comparator<? super Short> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    /**
     * Returns a {@link ThrowableShortBinaryOperator} which returns the lesser of two elements according to {@code left
     * &lt;= right} operation.
     *
     * @return A {@code ThrowableShortBinaryOperator} which returns the lesser of two elements.
     * @see BinaryOperator#minBy(Comparator)
     */
    static ThrowableShortBinaryOperator min() {
        return (left, right) -> (left <= right) ? left : right;
    }

    /**
     * Returns a {@link ThrowableShortBinaryOperator} which returns the greater of two elements according to {@code left
     * &gt;= right} operation.
     *
     * @return A {@code ThrowableShortBinaryOperator} which returns the greater of two elements.
     * @see BinaryOperator#maxBy(Comparator)
     */
    static ThrowableShortBinaryOperator max() {
        return (left, right) -> (left >= right) ? left : right;
    }

    /**
     * Creates a {@link ShortBinaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * ShortUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code ShortBinaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortBinaryOperator onlyLeft(final ShortUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsShort(left);
    }

    /**
     * Creates a {@link ShortBinaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * ShortUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code ShortBinaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortBinaryOperator onlyRight(final ShortUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsShort(right);
    }

    /**
     * The apply method for this {@link ShortBinaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    short applyAsShortThrows(short left, short right) throws Exception;

    /**
     * Overrides the {@link ShortBinaryOperator#applyAsShort(short, short)} method by using a redefinition as default
     * method. It calls the {@link #applyAsShortThrows(short, short)} method of this interface and catches the thrown
     * {@link Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other throwable
     * types are sneakily thrown.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default short applyAsShort(short left, short right) {
        try {
            return applyAsShortThrows(left, right);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableShortBinaryOperator} that applies this {@code ThrowableShortBinaryOperator} to
     * its input, and if an error occurred, applies the given one. The exception from this {@code
     * ThrowableShortBinaryOperator} is ignored.
     *
     * @param other A {@code ThrowableShortBinaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableShortBinaryOperator} that applies this {@code ThrowableShortBinaryOperator},
     * and if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableShortBinaryOperator orElse(final ThrowableShortBinaryOperator other) {
        Objects.requireNonNull(other);
        return (left, right) -> {
            try {
                return applyAsShortThrows(left, right);
            } catch (Exception ignored) {
                return other.applyAsShortThrows(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableShortBinaryOperator} that applies this {@code ThrowableShortBinaryOperator} to
     * its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableShortBinaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableShortBinaryOperator} that applies this {@code ThrowableShortBinaryOperator},
     * and if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableShortBinaryOperator orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (left, right) -> {
            try {
                return applyAsShortThrows(left, right);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that applies this {@link ThrowableShortBinaryOperator} to its
     * input, and if an error occurred, applies the given {@code ShortBinaryOperator} representing a fallback. The
     * exception from this {@code ThrowableShortBinaryOperator} is ignored.
     *
     * @param fallback A {@code ShortBinaryOperator} to be applied if this one fails
     * @return A composed {@code ShortBinaryOperator} that applies this {@code ThrowableShortBinaryOperator}, and if an
     * error occurred, applies the given {@code ShortBinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ShortBinaryOperator fallbackTo(final ShortBinaryOperator fallback) {
        Objects.requireNonNull(fallback);
        return (left, right) -> {
            try {
                return applyAsShortThrows(left, right);
            } catch (Exception ignored) {
                return fallback.applyAsShort(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that applies this {@link ThrowableShortBinaryOperator} to its
     * input, and if an error occurred, returns the given value. The exception from this {@code
     * ThrowableShortBinaryOperator} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableShortBinaryOperator} fails
     * @return A composed {@code ShortBinaryOperator} that applies this {@code ThrowableShortBinaryOperator}, and if an
     * error occurred, returns the given value.
     */
    default ShortBinaryOperator orReturn(short value) {
        return (left, right) -> {
            try {
                return applyAsShortThrows(left, right);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that applies this {@link ThrowableShortBinaryOperator} to its
     * input, and if an error occurred, returns the supplied value from the given {@link ShortSupplier}. The exception
     * from this {@code ThrowableShortBinaryOperator} is ignored.
     *
     * @param supplier A {@code ShortSupplier} to return a supplied value if this {@code ThrowableShortBinaryOperator}
     * fails
     * @return A composed {@code ShortBinaryOperator} that applies this {@code ThrowableShortBinaryOperator}, and if an
     * error occurred, the supplied value from the given {@code ShortSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ShortBinaryOperator orReturn(final ShortSupplier supplier) {
        Objects.requireNonNull(supplier);
        return (left, right) -> {
            try {
                return applyAsShortThrows(left, right);
            } catch (Exception ignored) {
                return supplier.getAsShort();
            }
        };
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that applies this {@link ThrowableShortBinaryOperator} to its
     * input, and if an error occurred, returns the left value from this operator. The exception from this {@code
     * ThrowableShortBinaryOperator} is ignored.
     *
     * @return A composed {@code ShortBinaryOperator} that applies this {@code ThrowableShortBinaryOperator}, and if an
     * error occurred, returns the left value from this operator.
     */
    default ShortBinaryOperator orReturnLeft() {
        return (left, right) -> {
            try {
                return applyAsShortThrows(left, right);
            } catch (Exception ignored) {
                return left;
            }
        };
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that applies this {@link ThrowableShortBinaryOperator} to its
     * input, and if an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableShortBinaryOperator} is ignored.
     *
     * @return A composed {@code ShortBinaryOperator} that applies this {@code ThrowableShortBinaryOperator}, and if an
     * error occurred, returns the right value from this operator.
     */
    default ShortBinaryOperator orReturnRight() {
        return (left, right) -> {
            try {
                return applyAsShortThrows(left, right);
            } catch (Exception ignored) {
                return right;
            }
        };
    }
}
