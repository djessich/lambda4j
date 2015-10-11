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
 * Represents an operation on a single {@code char}-valued operand that produces a {@code char}-valued result. This is
 * the primitive type specialization of {@link UnaryOperator} for {@code char}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(char)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharUnaryOperator {

    /**
     * Creates a {@link CharUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharUnaryOperator} which always returns a given value.
     */
    static CharUnaryOperator constant(char ret) {
        return operand -> ret;
    }

    /**
     * Returns a {@link CharUnaryOperator} that always returns its input argument.
     *
     * @return A {@code CharUnaryOperator} that always returns its input argument
     */
    static CharUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    char applyAsChar(char operand);

    /**
     * Returns a composed {@link CharUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before The {@code CharUnaryOperator} to apply before this operator is applied
     * @return A composed {@code CharUnaryOperator} that first applies the {@code before} operator and then applies this
     * operator.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharUnaryOperator)
     */
    default CharUnaryOperator compose(final CharUnaryOperator before) {
        Objects.requireNonNull(before);
        return operand -> applyAsChar(before.applyAsChar(operand));
    }

    /**
     * Returns a composed {@link CharUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The {@code CharUnaryOperator} to apply after this operator is applied
     * @return A composed {@code CharUnaryOperator} that first applies this operator and then applies the {@code after}
     * operator.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(CharUnaryOperator)
     */
    default CharUnaryOperator andThen(final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return operand -> after.applyAsChar(applyAsChar(operand));
    }

    /**
     * Returns a composed {@link UnaryOperator} which represents this {@link CharUnaryOperator}. Thereby the primitive
     * input argument for this operation is autoboxed. This method is just convenience to provide the ability to use
     * this {@code CharUnaryOperator} with JRE specific methods, only accepting {@code UnaryOperator}.
     *
     * @return A composed {@code UnaryOperator} which represents this {@code CharUnaryOperator}.
     */
    default UnaryOperator<Character> boxed() {
        return this::applyAsChar;
    }
}