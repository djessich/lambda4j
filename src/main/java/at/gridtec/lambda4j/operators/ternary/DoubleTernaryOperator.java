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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
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
     * @param ret The return value for the constant
     * @return A {@code DoubleTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static DoubleTernaryOperator constant(double ret) {
        return (left, middle, right) -> ret;
    }

    /**
     * Creates a {@link DoubleTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * DoubleUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code DoubleTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code DoubleUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static DoubleTernaryOperator onlyLeft(@Nonnull final DoubleUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsDouble(left);
    }

    /**
     * Creates a {@link DoubleTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * DoubleUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code DoubleTernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code DoubleUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static DoubleTernaryOperator onlyMiddle(@Nonnull final DoubleUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsDouble(middle);
    }

    /**
     * Creates a {@link DoubleTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * DoubleUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code DoubleTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code DoubleUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static DoubleTernaryOperator onlyRight(@Nonnull final DoubleUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsDouble(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    double applyAsDouble(double left, double middle, double right);

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

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

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link DoubleTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code DoubleTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Double> boxed() {
        return this::applyAsDouble;
    }
}