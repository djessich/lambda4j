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
import java.util.function.LongUnaryOperator;

/**
 * Represents an operation on a two {@code long}-valued operands and producing a {@code long}-valued result. This is the
 * primitive type specialization of {@link TernaryOperator} for {@code long}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(long, long, long)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface LongTernaryOperator {

    /**
     * Creates a {@link LongTernaryOperator} which always returns a given value.
     *
     * @param r The return value for the constant
     * @return A {@code LongTernaryOperator} which always returns a given value.
     */
    static <T> LongTernaryOperator constant(long r) {
        return (left, middle, right) -> r;
    }

    /**
     * Creates a {@link LongTernaryOperator} which uses the left parameter only from the given {@link
     * LongUnaryOperator}.
     *
     * @return Creates a {@code LongTernaryOperator} which uses the left parameter only from the given {@code
     * LongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> LongTernaryOperator forLeft(final LongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsLong(left);
    }

    /**
     * Creates a {@link LongTernaryOperator} which uses the middle parameter only from the given {@link
     * LongUnaryOperator}.
     *
     * @return Creates a {@code LongTernaryOperator} which uses the middle parameter only from the given {@code
     * LongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> LongTernaryOperator forMiddle(final LongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsLong(middle);
    }

    /**
     * Creates a {@link LongTernaryOperator} which uses the right parameter only from the given {@link
     * LongUnaryOperator}.
     *
     * @return Creates a {@code LongTernaryOperator} which uses the right parameter only from the given {@code
     * LongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> LongTernaryOperator forRight(final LongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsLong(right);
    }

    /**
     * Applies this operator to the given operand arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    long applyAsLong(long left, long middle, long right);

    /**
     * Returns a composed {@link LongTernaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code LongUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code LongUnaryOperator} to apply before this operator is applied
     * @param before3 The third {@code LongUnaryOperator} to apply before this operator is applied
     * @return A composed {@code LongTernaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(LongUnaryOperator)
     */
    default LongTernaryOperator compose(final LongUnaryOperator before1, final LongUnaryOperator before2,
            final LongUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsLong(before1.applyAsLong(left), before2.applyAsLong(middle),
                                                    before3.applyAsLong(right));
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code LongUnaryOperator} to apply after this operator is applied
     * @return A composed {@code LongTernaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(LongUnaryOperator, LongUnaryOperator, LongUnaryOperator)
     */
    default LongTernaryOperator andThen(LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsLong(applyAsLong(left, middle, right));
    }
}