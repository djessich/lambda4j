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

import at.gridtec.lambda4j.consumer.primitives.tri.LongTriConsumer;
import at.gridtec.lambda4j.function.primitives.to.tri.ToLongTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.LongTriFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

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
     * @param ret The return value for the constant
     * @return A {@code LongTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static LongTernaryOperator constant(long ret) {
        return (left, middle, right) -> ret;
    }

    /**
     * Creates a {@link LongTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * LongUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code LongTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code LongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTernaryOperator onlyLeft(@Nonnull final LongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsLong(left);
    }

    /**
     * Creates a {@link LongTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * LongUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code LongTernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code LongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTernaryOperator onlyMiddle(@Nonnull final LongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsLong(middle);
    }

    /**
     * Creates a {@link LongTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * LongUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code LongTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code LongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTernaryOperator onlyRight(@Nonnull final LongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsLong(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    long applyAsLong(long left, long middle, long right);

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@link LongTernaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code long}.
     * @see #andThen(LongUnaryOperator)
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default LongTernaryOperator compose(@Nonnull final LongUnaryOperator before1,
            @Nonnull final LongUnaryOperator before2, @Nonnull final LongUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsLong(before1.applyAsLong(left), before2.applyAsLong(middle),
                                                    before3.applyAsLong(right));
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first operation to apply before this operator is applied
     * @param before2 The second operation to apply before this operator is applied
     * @param before3 The third operation to apply before this operator is applied
     * @return A composed {@link ToLongTriFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(LongUnaryOperator)
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default <T, U, V> ToLongTriFunction<T, U, V> compose(@Nonnull final ToLongFunction<? super T> before1,
            @Nonnull final ToLongFunction<? super U> before2, @Nonnull final ToLongFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> applyAsLong(before1.applyAsLong(t), before2.applyAsLong(u), before3.applyAsLong(v));
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@link LongTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is a primitive specialization of {@link TernaryOperator}. Therefore the
     * returned operation handles primitive types. In this case this is {@code long}.
     * @see #compose(LongUnaryOperator, LongUnaryOperator, LongUnaryOperator)
     * @see #compose(ToLongFunction, ToLongFunction, ToLongFunction)
     */
    @Nonnull
    default LongTernaryOperator andThen(@Nonnull final LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsLong(applyAsLong(left, middle, right));
    }

    /**
     * Returns a composed {@link LongTriFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code LongTriFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(LongUnaryOperator, LongUnaryOperator, LongUnaryOperator)
     * @see #compose(ToLongFunction, ToLongFunction, ToLongFunction)
     */
    @Nonnull
    default <R> LongTriFunction<R> andThen(@Nonnull final LongFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsLong(value1, value2, value3));
    }

    /**
     * Returns a composed {@link LongTriConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code LongConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code LongTriConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default LongTriConsumer consume(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsLong(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link LongTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code LongTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Long> boxed() {
        return this::applyAsLong;
    }
}