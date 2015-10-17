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

import at.gridtec.lambda4j.consumer.primitives.tri.IntTriConsumer;
import at.gridtec.lambda4j.function.primitives.to.tri.ToIntTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.IntTriFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a two {@code int}-valued operands and producing a {@code int}-valued result. This is the
 * primitive type specialization of {@link TernaryOperator} for {@code int}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(int, int, int)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface IntTernaryOperator {

    /**
     * Creates a {@link IntTernaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code IntTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static IntTernaryOperator constant(int ret) {
        return (left, middle, right) -> ret;
    }

    /**
     * Creates a {@link IntTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * IntUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code IntTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code IntUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntTernaryOperator onlyLeft(@Nonnull final IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsInt(left);
    }

    /**
     * Creates a {@link IntTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * IntUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code IntTernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code IntUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntTernaryOperator onlyMiddle(@Nonnull final IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsInt(middle);
    }

    /**
     * Creates a {@link IntTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * IntUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code IntTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code IntUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntTernaryOperator onlyRight(@Nonnull final IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsInt(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    int applyAsInt(int left, int middle, int right);

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
     * Returns a composed {@link IntTernaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@link IntTernaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code int}.
     * @see #andThen(IntUnaryOperator)
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default IntTernaryOperator compose(@Nonnull final IntUnaryOperator before1, @Nonnull final IntUnaryOperator before2,
            @Nonnull final IntUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsInt(before1.applyAsInt(left), before2.applyAsInt(middle),
                                                   before3.applyAsInt(right));
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first operation to apply before this operator is applied
     * @param before2 The second operation to apply before this operator is applied
     * @param before3 The third operation to apply before this operator is applied
     * @return A composed {@link ToIntTriFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(IntUnaryOperator)
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default <T, U, V> ToIntTriFunction<T, U, V> compose(@Nonnull final ToIntFunction<? super T> before1,
            @Nonnull final ToIntFunction<? super U> before2, @Nonnull final ToIntFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> applyAsInt(before1.applyAsInt(t), before2.applyAsInt(u), before3.applyAsInt(v));
    }

    /**
     * Returns a composed {@link IntTernaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@link IntTernaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is the primitive specialization of {@link TernaryOperator}. Therefore the
     * returned operation handles primitive types. In this case this is {@code int}.
     * @see #compose(IntUnaryOperator, IntUnaryOperator, IntUnaryOperator)
     * @see #compose(ToIntFunction, ToIntFunction, ToIntFunction)
     */
    @Nonnull
    default IntTernaryOperator andThen(@Nonnull final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsInt(applyAsInt(left, middle, right));
    }

    /**
     * Returns a composed {@link IntTriFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code IntTriFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned operation is able to handle every type.
     * @see #compose(IntUnaryOperator, IntUnaryOperator, IntUnaryOperator)
     * @see #compose(ToIntFunction, ToIntFunction, ToIntFunction)
     */
    @Nonnull
    default <R> IntTriFunction<R> andThen(@Nonnull final IntFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link IntTriConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code IntTriConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default IntTriConsumer consume(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link IntTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code IntTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Integer> boxed() {
        return this::applyAsInt;
    }
}