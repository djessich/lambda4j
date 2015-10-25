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

import at.gridtec.lambda4j.consumer.primitives.FloatConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.FloatBiConsumer;
import at.gridtec.lambda4j.function.primitives.FloatFunction;
import at.gridtec.lambda4j.function.primitives.bi.FloatBiFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToFloatBiFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

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
     * Returns a {@link FloatBinaryOperator} which returns the lesser of two elements according to {@link
     * Float#min(float, float)} operation.
     *
     * @return A {@code FloatBinaryOperator} which returns the lesser of two elements.
     * @see Float#min(float, float)
     * @see Math#min(float, float)
     */
    @Nonnull
    static FloatBinaryOperator min() {
        return Float::min;
    }

    /**
     * Returns a {@link FloatBinaryOperator} which returns the greater of two elements according to {@link
     * Float#max(float, float)} operation.
     *
     * @return A {@code FloatBinaryOperator} which returns the greater of two elements.
     * @see Float#max(float, float)
     * @see Math#max(float, float)
     */
    @Nonnull
    static FloatBinaryOperator max() {
        return Float::max;
    }

    /**
     * Returns a {@link FloatBinaryOperator} which returns the lesser of two elements, according to the specified {@link
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code FloatBinaryOperator} which returns the lesser of two elements, according to the specified {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static FloatBinaryOperator minBy(@Nonnull final Comparator<? super Float> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link FloatBinaryOperator} which returns the greater of two elements, according to the specified
     * {@link Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code FloatBinaryOperator} which returns the greater of two elements, according to the specified
     * {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static FloatBinaryOperator maxBy(@Nonnull final Comparator<? super Float> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
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
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @return A composed {@link FloatBinaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code float}.
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default FloatBinaryOperator compose(@Nonnull final FloatUnaryOperator before1,
            @Nonnull final FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (left, right) -> applyAsFloat(before1.applyAsFloat(left), before2.applyAsFloat(right));
    }

    /**
     * Returns a composed {@link ToFloatBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this operator is applied
     * @param before2 The second operation to apply before this operator is applied
     * @return A composed {@link ToFloatBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default <T, U> ToFloatBiFunction<T, U> compose(@Nonnull final ToFloatFunction<? super T> before1,
            @Nonnull final ToFloatFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsFloat(before1.applyAsFloat(t), before2.applyAsFloat(u));
    }


    /**
     * Returns a composed {@link FloatBiFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code FloatBiFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(FloatUnaryOperator, FloatUnaryOperator)
     * @see #compose(ToFloatFunction, ToFloatFunction)
     */
    @Nonnull
    default <R> FloatBiFunction<R> andThen(@Nonnull final FloatFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsFloat(value1, value2));
    }

    /**
     * Returns a composed {@link FloatBiConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code FloatBiConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatBiConsumer consume(@Nonnull final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsFloat(value1, value2));
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
