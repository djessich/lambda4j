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

import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * Represents an operation on a two {@code float}-valued operands and producing a {@code float}-valued result. This is
 * the primitive type specialization of {@link BinaryOperator} for {@code float}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(float, float)}.
 *
 * @see java.util.function.BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatBinaryOperator {

    /**
     * Creates a {@link FloatBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatBinaryOperator} which always returns a given value.
     */
    @Nonnull
    static FloatBinaryOperator constant(float ret) {
        return (left, right) -> ret;
    }

    /**
     * Returns a {@link FloatBinaryOperator} which returns the lesser of two elements, according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code FloatBinaryOperator} which returns the lesser of two elements, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static FloatBinaryOperator minBy(final Comparator<? super Float> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link FloatBinaryOperator} which returns the greater of two elements, according to the specified
     * {@code Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code FloatBinaryOperator} which returns the greater of two elements, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static FloatBinaryOperator maxBy(final Comparator<? super Float> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    /**
     * Returns a {@link FloatBinaryOperator} which returns the lesser of two elements according to {@link
     * Float#min(float, float)} operation.
     *
     * @return A {@code FloatBinaryOperator} which returns the lesser of two elements.
     * @see BinaryOperator#minBy(Comparator)
     * @see Float#min(float, float)
     * @see Math#min(float, float)
     */
    static FloatBinaryOperator min() {
        return Float::min;
    }

    /**
     * Returns a {@link FloatBinaryOperator} which returns the greater of two elements according to {@link
     * Float#max(float, float)} operation.
     *
     * @return A {@code FloatBinaryOperator} which returns the greater of two elements.
     * @see BinaryOperator#maxBy(Comparator)
     * @see Float#max(float, float)
     * @see Math#max(float, float)
     */
    static FloatBinaryOperator max() {
        return Float::max;
    }

    /**
     * Creates a {@link FloatBinaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code FloatBinaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatBinaryOperator onlyLeft(@Nonnull final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsFloat(left);
    }

    /**
     * Creates a {@link FloatBinaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code FloatBinaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatBinaryOperator onlyRight(@Nonnull final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsFloat(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param right The second argument to the operator (right input)
     * @return The return value from the operator.
     */
    float applyAsFloat(float left, float right);

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }
    /**
     * Returns a composed {@link FloatBinaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code FloatUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code FloatUnaryOperator} to apply before this operator is applied
     * @return A composed {@code FloatBinaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(FloatUnaryOperator)
     */
    default FloatBinaryOperator compose(final FloatUnaryOperator before1, final FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (left, right) -> applyAsFloat(before1.applyAsFloat(left), before2.applyAsFloat(right));
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code FloatUnaryOperator} to apply after this operator is applied
     * @return A composed {@code FloatBinaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(FloatUnaryOperator, FloatUnaryOperator)
     */
    default FloatBinaryOperator andThen(FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, right) -> after.applyAsFloat(applyAsFloat(left, right));
    }

    /**
     * Returns a composed {@link BinaryOperator} which represents this {@link FloatBinaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed. This method is just convenience to provide the ability
     * to use this {@code FloatBinaryOperator} with JRE specific methods, only accepting {@code BinaryOperator}.
     *
     * @return A composed {@code BinaryOperator} which represents this {@code FloatBinaryOperator}.
     */
    @Nonnull
    default BinaryOperator<Float> boxed() {
        return this::applyAsFloat;
    }
}
