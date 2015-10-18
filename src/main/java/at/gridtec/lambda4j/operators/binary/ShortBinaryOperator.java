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

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.ShortBiConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.bi.ShortBiFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToShortBiFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a two {@code short}-valued operands and producing a {@code short}-valued result. This is
 * the primitive type specialization of {@link BinaryOperator} for {@code short}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(short, short)}.
 *
 * @see java.util.function.BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortBinaryOperator {

    /**
     * Creates a {@link ShortBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortBinaryOperator} which always returns a given value.
     */
    @Nonnull
    static ShortBinaryOperator constant(short ret) {
        return (left, right) -> ret;
    }

    /**
     * Creates a {@link ShortBinaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * ShortUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code ShortBinaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ShortBinaryOperator onlyLeft(@Nonnull final ShortUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsShort(left);
    }

    /**
     * Creates a {@link ShortBinaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * ShortUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code ShortBinaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ShortBinaryOperator onlyRight(@Nonnull final ShortUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsShort(right);
    }

    /**
     * Returns a {@link ShortBinaryOperator} which returns the lesser of two elements according to {@code left &lt;=
     * right} operation.
     *
     * @return A {@code ShortBinaryOperator} which returns the lesser of two elements.
     */
    @Nonnull
    static ShortBinaryOperator min() {
        return (left, right) -> (left <= right) ? left : right;
    }

    /**
     * Returns a {@link ShortBinaryOperator} which returns the greater of two elements according to {@code left &gt;=
     * right} operation.
     *
     * @return A {@code ShortBinaryOperator} which returns the greater of two elements.
     */
    @Nonnull
    static ShortBinaryOperator max() {
        return (left, right) -> (left >= right) ? left : right;
    }

    /**
     * Returns a {@link ShortBinaryOperator} which returns the lesser of two elements, according to the specified {@link
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ShortBinaryOperator} which returns the lesser of two elements, according to the specified {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static ShortBinaryOperator minBy(@Nonnull final Comparator<? super Short> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link ShortBinaryOperator} which returns the greater of two elements, according to the specified
     * {@link Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ShortBinaryOperator} which returns the greater of two elements, according to the specified
     * {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static ShortBinaryOperator maxBy(@Nonnull final Comparator<? super Short> comparator) {
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
    short applyAsShort(short left, short right);

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
     * Returns a composed {@link ShortBinaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @return A composed {@link ShortBinaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code short}.
     * @see #andThen(ShortUnaryOperator)
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortBinaryOperator compose(@Nonnull final ShortUnaryOperator before1,
            @Nonnull final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (left, right) -> applyAsShort(before1.applyAsShort(left), before2.applyAsShort(right));
    }

    /**
     * Returns a composed {@link ToShortBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this operator is applied
     * @param before2 The second operation to apply before this operator is applied
     * @return A composed {@link ToShortBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ShortUnaryOperator)
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default <T, U> ToShortBiFunction<T, U> compose(@Nonnull final ToShortFunction<? super T> before1,
            @Nonnull final ToShortFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsShort(before1.applyAsShort(t), before2.applyAsShort(u));
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ShortBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is a primitive specialization of {@link BinaryOperator}. Therefore the
     * returned operation handles primitive types. In this case this is {@code short}.
     * @see #compose(ShortUnaryOperator, ShortUnaryOperator)
     * @see #compose(ToShortFunction, ToShortFunction)
     */
    @Nonnull
    default ShortBinaryOperator andThen(@Nonnull final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, right) -> after.applyAsShort(applyAsShort(left, right));
    }

    /**
     * Returns a composed {@link ShortBiFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code ShortBiFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned operation is able to handle every type.
     * @see #compose(ShortUnaryOperator, ShortUnaryOperator)
     * @see #compose(ToShortFunction, ToShortFunction)
     */
    @Nonnull
    default <R> ShortBiFunction<R> andThen(@Nonnull final ShortFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link ShortBiConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code ShortConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ShortBiConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortBiConsumer consume(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link BinaryOperator} which represents this {@link ShortBinaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed. This method is just convenience to provide the ability
     * to use this {@code ShortBinaryOperator} with JRE specific methods, only accepting {@code BinaryOperator}.
     *
     * @return A composed {@code BinaryOperator} which represents this {@code ShortBinaryOperator}.
     */
    @Nonnull
    default BinaryOperator<Short> boxed() {
        return this::applyAsShort;
    }
}
