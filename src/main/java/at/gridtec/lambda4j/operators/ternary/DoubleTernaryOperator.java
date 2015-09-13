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
package at.gridtec.lambda4j.operators.ternary;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

/**
 * Represents an operation on a two {@code double}-valued operands and producing a {@code double}-valued result. This is
 * the primitive type specialization of {@link TernaryOperator} for {@code double}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(double, double, double)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleTernaryOperator {

    /**
     * Creates a {@link DoubleTernaryOperator} which always returns a given value.
     *
     * @param r The return value for the constant
     * @return A {@code DoubleTernaryOperator} which always returns a given value.
     */
    static <T> DoubleTernaryOperator constant(double r) {
        return (left, middle, right) -> r;
    }

    /**
     * Creates a {@link DoubleTernaryOperator} which uses the left parameter only from the given {@link
     * DoubleUnaryOperator}.
     *
     * @return Creates a {@code DoubleTernaryOperator} which uses the left parameter only from the given {@code
     * DoubleUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> DoubleTernaryOperator forLeft(final DoubleUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsDouble(left);
    }

    /**
     * Creates a {@link DoubleTernaryOperator} which uses the middle parameter only from the given {@link
     * DoubleUnaryOperator}.
     *
     * @return Creates a {@code DoubleTernaryOperator} which uses the middle parameter only from the given {@code
     * DoubleUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> DoubleTernaryOperator forMiddle(final DoubleUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsDouble(middle);
    }

    /**
     * Creates a {@link DoubleTernaryOperator} which uses the right parameter only from the given {@link
     * DoubleUnaryOperator}.
     *
     * @return Creates a {@code DoubleTernaryOperator} which uses the right parameter only from the given {@code
     * DoubleUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> DoubleTernaryOperator forRight(final DoubleUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsDouble(right);
    }

    /**
     * Applies this operator to the given operand arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    double applyAsDouble(double left, double middle, double right);

    /**
     * Returns a composed {@link DoubleTernaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code DoubleUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code DoubleUnaryOperator} to apply before this operator is applied
     * @param before3 The third {@code DoubleUnaryOperator} to apply before this operator is applied
     * @return A composed {@code DoubleTernaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(DoubleUnaryOperator)
     */
    default DoubleTernaryOperator compose(final DoubleUnaryOperator before1, final DoubleUnaryOperator before2,
            final DoubleUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsDouble(before1.applyAsDouble(left), before2.applyAsDouble(middle),
                                                      before3.applyAsDouble(right));
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code DoubleUnaryOperator} to apply after this operator is applied
     * @return A composed {@code DoubleTernaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(DoubleUnaryOperator, DoubleUnaryOperator, DoubleUnaryOperator)
     */
    default DoubleTernaryOperator andThen(DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsDouble(applyAsDouble(left, middle, right));
    }
}