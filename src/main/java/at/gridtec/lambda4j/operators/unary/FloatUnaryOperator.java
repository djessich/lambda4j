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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single {@code float}-valued operand that produces a {@code float}-valued result. This is
 * the primitive type specialization of {@link UnaryOperator} for {@code float}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(float)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatUnaryOperator {

    /**
     * Creates a {@link FloatUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static FloatUnaryOperator constant(float ret) {
        return operand -> ret;
    }

    /**
     * Returns a {@link FloatUnaryOperator} that always returns its input argument.
     *
     * @return A {@code FloatUnaryOperator} that always returns its input argument
     */
    static FloatUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    float applyAsFloat(float operand);

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before The {@code FloatUnaryOperator} to apply before this operator is applied
     * @return A composed {@code FloatUnaryOperator} that first applies the {@code before} operator and then applies
     * this operator.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatUnaryOperator)
     */
    default FloatUnaryOperator compose(final FloatUnaryOperator before) {
        Objects.requireNonNull(before);
        return operand -> applyAsFloat(before.applyAsFloat(operand));
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The {@code FloatUnaryOperator} to apply after this operator is applied
     * @return A composed {@code FloatUnaryOperator} that first applies this operator and then applies the {@code after}
     * operator.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(FloatUnaryOperator)
     */
    default FloatUnaryOperator andThen(final FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return operand -> after.applyAsFloat(applyAsFloat(operand));
    }

    /**
     * Returns a composed {@link UnaryOperator} which represents this {@link FloatUnaryOperator}. Thereby the primitive
     * input argument for this operator is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatUnaryOperator} with JRE specific methods, only accepting {@code UnaryOperator}.
     *
     * @return A composed {@code UnaryOperator} which represents this {@code FloatUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator<Float> boxed() {
        return this::applyAsFloat;
    }
}