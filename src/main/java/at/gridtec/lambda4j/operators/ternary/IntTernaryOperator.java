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
import java.util.function.IntUnaryOperator;

/**
 * Represents an operation on a two {@code int}-valued operands and producing a {@code int}-valued result. This is the
 * primitive type specialization of {@link TernaryOperator} for {@code int}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(int, int, int)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface IntTernaryOperator {

    /**
     * Creates a {@link IntTernaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code IntTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static IntTernaryOperator constant(int ret) {
        return (left, middle, right) -> ret;
    }

    /**
     * Creates a {@link IntTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * IntUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code IntTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code IntUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntTernaryOperator onlyLeft(@Nonnull final IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsInt(left);
    }

    /**
     * Creates a {@link IntTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * IntUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code IntTernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code IntUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntTernaryOperator onlyMiddle(@Nonnull final IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsInt(middle);
    }

    /**
     * Creates a {@link IntTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * IntUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code IntTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code IntUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntTernaryOperator onlyRight(@Nonnull final IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsInt(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    int applyAsInt(int left, int middle, int right);

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
     * Returns a composed {@link IntTernaryOperator} that first applies the given {@code before} operators to its input,
     * and then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operator.
     *
     * @param before1 The first {@code IntUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code IntUnaryOperator} to apply before this operator is applied
     * @param before3 The third {@code IntUnaryOperator} to apply before this operator is applied
     * @return A composed {@code IntTernaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(IntUnaryOperator)
     */
    default IntTernaryOperator compose(final IntUnaryOperator before1, final IntUnaryOperator before2,
            final IntUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsInt(before1.applyAsInt(left), before2.applyAsInt(middle),
                                                   before3.applyAsInt(right));
    }

    /**
     * Returns a composed {@link IntTernaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The {@code IntUnaryOperator} to apply after this operator is applied
     * @return A composed {@code IntTernaryOperator} that first applies this operator and then applies the {@code after}
     * operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(IntUnaryOperator, IntUnaryOperator, IntUnaryOperator)
     */
    default IntTernaryOperator andThen(IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsInt(applyAsInt(left, middle, right));
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link IntTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code IntTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Integer> boxed() {
        return this::applyAsInt;
    }
}