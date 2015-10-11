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
 * Represents an operation on a single {@code short}-valued operand that produces a {@code short}-valued result. This is
 * the primitive type specialization of {@link UnaryOperator} for {@code short}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(short)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortUnaryOperator {

    /**
     * Creates a {@link ShortUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortUnaryOperator} which always returns a given value.
     */
    static ShortUnaryOperator constant(short ret) {
        return operand -> ret;
    }

    /**
     * Returns a {@link ShortUnaryOperator} that always returns its input argument.
     *
     * @return A {@code ShortUnaryOperator} that always returns its input argument
     */
    static ShortUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    short applyAsShort(short operand);

    /**
     * Returns a composed {@link ShortUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before The {@code ShortUnaryOperator} to apply before this operator is applied
     * @return A composed {@code ShortUnaryOperator} that first applies the {@code before} operator and then applies
     * this operator.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortUnaryOperator)
     */
    default ShortUnaryOperator compose(final ShortUnaryOperator before) {
        Objects.requireNonNull(before);
        return operand -> applyAsShort(before.applyAsShort(operand));
    }

    /**
     * Returns a composed {@link ShortUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The {@code ShortUnaryOperator} to apply after this operator is applied
     * @return A composed {@code ShortUnaryOperator} that first applies this operator and then applies the {@code after}
     * operator.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ShortUnaryOperator)
     */
    default ShortUnaryOperator andThen(final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return operand -> after.applyAsShort(applyAsShort(operand));
    }

    /**
     * Returns a composed {@link UnaryOperator} which represents this {@link ShortUnaryOperator}. Thereby the primitive
     * input argument for this operation is autoboxed. This method is just convenience to provide the ability to use
     * this {@code ShortUnaryOperator} with JRE specific methods, only accepting {@code UnaryOperator}.
     *
     * @return A composed {@code UnaryOperator} which represents this {@code ShortUnaryOperator}.
     */
    default UnaryOperator<Short> boxed() {
        return this::applyAsShort;
    }
}