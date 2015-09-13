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

import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import java.util.Objects;

/**
 * Represents an operation on a two {@code byte}-valued operands and producing a {@code byte}-valued result. This is the
 * primitive type specialization of {@link TernaryOperator} for {@code byte}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(byte, byte, byte)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteTernaryOperator {

    /**
     * Creates a {@link ByteTernaryOperator} which always returns a given value.
     *
     * @param r The return value for the constant
     * @return A {@code ByteTernaryOperator} which always returns a given value.
     */
    static <T> ByteTernaryOperator constant(byte r) {
        return (left, middle, right) -> r;
    }

    /**
     * Creates a {@link ByteTernaryOperator} which uses the left parameter as argument for the given {@link
     * ByteUnaryOperator}.
     *
     * @return Creates a {@code ByteTernaryOperator} which uses the left parameter as argument for the given {@code
     * ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ByteTernaryOperator useLeft(final ByteUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsByte(left);
    }

    /**
     * Creates a {@link ByteTernaryOperator} which uses the middle parameter as argument for the given {@link
     * ByteUnaryOperator}.
     *
     * @return Creates a {@code ByteTernaryOperator} which uses the middle parameter as argument for the given {@code
     * ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ByteTernaryOperator useMiddle(final ByteUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsByte(middle);
    }

    /**
     * Creates a {@link ByteTernaryOperator} which uses the right parameter as argument for the given {@link
     * ByteUnaryOperator}.
     *
     * @return Creates a {@code ByteTernaryOperator} which uses the right parameter as argument for the given {@code
     * ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ByteTernaryOperator useRight(final ByteUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsByte(right);
    }

    /**
     * Applies this operator to the given operand arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    byte applyAsByte(byte left, byte middle, byte right);

    /**
     * Returns a composed {@link ByteTernaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code ByteUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code ByteUnaryOperator} to apply before this operator is applied
     * @param before3 The third {@code ByteUnaryOperator} to apply before this operator is applied
     * @return A composed {@code ByteTernaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(ByteUnaryOperator)
     */
    default ByteTernaryOperator compose(final ByteUnaryOperator before1, final ByteUnaryOperator before2,
            final ByteUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsByte(before1.applyAsByte(left), before2.applyAsByte(middle),
                                                    before3.applyAsByte(right));
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code ByteUnaryOperator} to apply after this operator is applied
     * @return A composed {@code ByteTernaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(ByteUnaryOperator, ByteUnaryOperator, ByteUnaryOperator)
     */
    default ByteTernaryOperator andThen(ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsByte(applyAsByte(left, middle, right));
    }
}