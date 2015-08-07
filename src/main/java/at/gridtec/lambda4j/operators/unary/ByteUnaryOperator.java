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
package at.gridtec.lambda4j.operators.unary;

import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single {@code byte}-valued operand that produces a {@code byte}-valued result. This is
 * the primitive type specialization of {@link UnaryOperator} for {@code byte}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(byte)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteUnaryOperator {

    /**
     * Creates a {@link ByteUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteUnaryOperator} which always returns a given value.
     */
    static ByteUnaryOperator constant(byte ret) {
        return operand -> ret;
    }

    /**
     * Returns a {@link ByteUnaryOperator} that always returns its input argument.
     *
     * @return A {@code ByteUnaryOperator} that always returns its input argument
     */
    static ByteUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Applies this operator to the given operand argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    byte applyAsByte(byte operand);

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before The {@code ByteUnaryOperator} to apply before this operator is applied
     * @return A composed {@code ByteUnaryOperator} that first applies the {@code before} operator and then applies this
     * operator
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteUnaryOperator)
     */
    default ByteUnaryOperator compose(final ByteUnaryOperator before) {
        Objects.requireNonNull(before);
        return operand -> applyAsByte(before.applyAsByte(operand));
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The {@code ByteUnaryOperator} to apply after this operator is applied
     * @return A composed {@code ByteUnaryOperator} that first applies this operator and then applies the {@code after}
     * operator
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ByteUnaryOperator)
     */
    default ByteUnaryOperator andThen(final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return operand -> after.applyAsByte(applyAsByte(operand));
    }
}