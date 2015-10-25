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

import at.gridtec.lambda4j.consumer.primitives.CharConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.CharBiConsumer;
import at.gridtec.lambda4j.function.primitives.CharFunction;
import at.gridtec.lambda4j.function.primitives.bi.CharBiFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToCharBiFunction;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a two {@code char}-valued operands and producing a {@code char}-valued result. This is the
 * primitive type specialization of {@link BinaryOperator} for {@code char}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(char, char)}.
 *
 * @see java.util.function.BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharBinaryOperator {

    /**
     * Creates a {@link CharBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharBinaryOperator} which always returns a given value.
     */
    @Nonnull
    static CharBinaryOperator constant(char ret) {
        return (left, right) -> ret;
    }

    /**
     * Creates a {@link CharBinaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * CharUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code CharBinaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharBinaryOperator onlyLeft(@Nonnull final CharUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsChar(left);
    }

    /**
     * Creates a {@link CharBinaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * CharUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code CharBinaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharBinaryOperator onlyRight(@Nonnull final CharUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsChar(right);
    }

    /**
     * Returns a {@link CharBinaryOperator} which returns the lesser of two elements according to {@code left &lt;=
     * right} operation.
     *
     * @return A {@code CharBinaryOperator} which returns the lesser of two elements.
     */
    @Nonnull
    static CharBinaryOperator min() {
        return (left, right) -> (left <= right) ? left : right;
    }

    /**
     * Returns a {@link CharBinaryOperator} which returns the greater of two elements according to {@code left &gt;=
     * right} operation.
     *
     * @return A {@code CharBinaryOperator} which returns the greater of two elements.
     */
    @Nonnull
    static CharBinaryOperator max() {
        return (left, right) -> (left >= right) ? left : right;
    }

    /**
     * Returns a {@link CharBinaryOperator} which returns the lesser of two elements, according to the specified {@link
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code CharBinaryOperator} which returns the lesser of two elements, according to the specified {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static CharBinaryOperator minBy(@Nonnull final Comparator<? super Character> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link CharBinaryOperator} which returns the greater of two elements, according to the specified {@link
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code CharBinaryOperator} which returns the greater of two elements, according to the specified {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static CharBinaryOperator maxBy(@Nonnull final Comparator<? super Character> comparator) {
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
    char applyAsChar(char left, char right);

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
     * Returns a composed {@link CharBinaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @return A composed {@link CharBinaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code char}.
     * @see #andThen(CharUnaryOperator)
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default CharBinaryOperator compose(@Nonnull final CharUnaryOperator before1,
            @Nonnull final CharUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (left, right) -> applyAsChar(before1.applyAsChar(left), before2.applyAsChar(right));
    }

    /**
     * Returns a composed {@link ToCharBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this operator is applied
     * @param before2 The second operation to apply before this operator is applied
     * @return A composed {@link ToCharBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(CharUnaryOperator)
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default <T, U> ToCharBiFunction<T, U> compose(@Nonnull final ToCharFunction<? super T> before1,
            @Nonnull final ToCharFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsChar(before1.applyAsChar(t), before2.applyAsChar(u));
    }

    /**
     * Returns a composed {@link CharBinaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code CharBinaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator)
     * @see #compose(ToCharFunction, ToCharFunction)
     */
    @Nonnull
    default CharBinaryOperator andThen(@Nonnull final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, right) -> after.applyAsChar(applyAsChar(left, right));
    }

    /**
     * Returns a composed {@link CharBiFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code CharBiFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator)
     * @see #compose(ToCharFunction, ToCharFunction)
     */
    @Nonnull
    default <R> CharBiFunction<R> andThen(@Nonnull final CharFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsChar(value1, value2));
    }

    /**
     * Returns a composed {@link CharBiConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code CharConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code CharBiConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharBiConsumer consume(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsChar(value1, value2));
    }

    /**
     * Returns a composed {@link BinaryOperator} which represents this {@link CharBinaryOperator}. Thereby the primitive
     * input argument for this operator is autoboxed. This method is just convenience to provide the ability to use this
     * {@code CharBinaryOperator} with JRE specific methods, only accepting {@code BinaryOperator}.
     *
     * @return A composed {@code BinaryOperator} which represents this {@code CharBinaryOperator}.
     */
    @Nonnull
    default BinaryOperator<Character> boxed() {
        return this::applyAsChar;
    }
}
