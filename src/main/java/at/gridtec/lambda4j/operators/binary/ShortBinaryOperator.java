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

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * Represents an operation on a two {@code short}-valued operands and producing a {@code short}-valued result. This is
 * the primitive type specialization of {@link BinaryOperator} for {@code short}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(short, short)}.
 *
 * @see java.util.function.BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortBinaryOperator {

    /**
     * Creates a {@link ShortBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortBinaryOperator} which always returns a given value.
     */
    static ShortBinaryOperator constant(short ret) {
        return (left, right) -> ret;
    }

    /**
     * Returns a {@link ShortBinaryOperator} which returns the lesser of two elements, according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ShortBinaryOperator} which returns the lesser of two elements, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortBinaryOperator minBy(final Comparator<? super Short> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link ShortBinaryOperator} which returns the greater of two elements, according to the specified
     * {@code Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ShortBinaryOperator} which returns the greater of two elements, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortBinaryOperator maxBy(final Comparator<? super Short> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    /**
     * Returns a {@link ShortBinaryOperator} which returns the lesser of two elements according to {@code left &lt;=
     * right} operation.
     *
     * @return A {@code ShortBinaryOperator} which returns the lesser of two elements.
     * @see BinaryOperator#minBy(Comparator)
     */
    static ShortBinaryOperator min() {
        return (left, right) -> (left <= right) ? left : right;
    }

    /**
     * Returns a {@link ShortBinaryOperator} which returns the greater of two elements according to {@code left &gt;=
     * right} operation.
     *
     * @return A {@code ShortBinaryOperator} which returns the greater of two elements.
     * @see BinaryOperator#maxBy(Comparator)
     */
    static ShortBinaryOperator max() {
        return (left, right) -> (left >= right) ? left : right;
    }

    /**
     * Creates a {@link ShortBinaryOperator} which uses the left parameter as argument for the given {@link
     * ShortUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code short} parameter of this one
     * @return Creates a {@code ShortBinaryOperator} which uses the left parameter as argument for the given {@code
     * ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortBinaryOperator onlyLeft(final ShortUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsShort(left);
    }

    /**
     * Creates a {@link ShortBinaryOperator} which uses the right parameter as argument for the given {@link
     * ShortUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code ShortBinaryOperator} which uses the right parameter as argument for the given {@code
     * ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortBinaryOperator onlyRight(final ShortUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsShort(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param right The second argument to the operator (right input)
     * @return The return value from the operator.
     */
    short applyAsShort(short left, short right);

    /**
     * Returns a composed {@link ShortBinaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code ShortUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code ShortUnaryOperator} to apply before this operator is applied
     * @return A composed {@code ShortBinaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(ShortUnaryOperator)
     */
    default ShortBinaryOperator compose(final ShortUnaryOperator before1, final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (left, right) -> applyAsShort(before1.applyAsShort(left), before2.applyAsShort(right));
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code ShortUnaryOperator} to apply after this operator is applied
     * @return A composed {@code ShortBinaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(ShortUnaryOperator, ShortUnaryOperator)
     */
    default ShortBinaryOperator andThen(ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, right) -> after.applyAsShort(applyAsShort(left, right));
    }

    /**
     * Returns a composed {@link BinaryOperator} which represents this {@link ShortBinaryOperator}. Thereby the
     * primitive input argument for this operation is autoboxed. This method is just convenience to provide the ability
     * to use this {@code ShortBinaryOperator} with JRE specific methods, only accepting {@code BinaryOperator}.
     *
     * @return A composed {@code BinaryOperator} which represents this {@code ShortBinaryOperator}.
     */
    default BinaryOperator<Short> boxed() {
        return this::applyAsShort;
    }
}
