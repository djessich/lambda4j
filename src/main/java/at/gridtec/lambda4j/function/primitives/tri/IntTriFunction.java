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
package at.gridtec.lambda4j.function.primitives.tri;

import at.gridtec.lambda4j.consumer.primitives.tri.IntTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.operators.ternary.IntTernaryOperator;
import at.gridtec.lambda4j.operators.ternary.TernaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts three int-valued argument and produces a result. This is the {@code int}-consuming
 * primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(int, int, int)}.
 *
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface IntTriFunction<R> {

    /**
     * Creates a {@link IntTriFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code IntTriFunction} which always returns a given value.
     */
    @Nonnull
    static <R> IntTriFunction<R> constant(R r) {
        return (value1, value2, value3) -> r;
    }

    /**
     * Creates a {@link IntTriFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link IntFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code IntTriFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code IntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <R> IntTriFunction<R> onlyFirst(@Nonnull final IntFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value1);
    }

    /**
     * Creates a {@link IntTriFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link IntFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code IntTriFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@code IntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <R> IntTriFunction<R> onlySecond(@Nonnull final IntFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value2);
    }

    /**
     * Creates a {@link IntTriFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@link IntFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code IntTriFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@code IntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <R> IntTriFunction<R> onlyThird(@Nonnull final IntFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value3);
    }

    /**
     * Applies this {@link IntTriFunction} to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(int value1, int value2, int value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link IntTriFunction} that first applies the {@code before} operations to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@link IntTriFunction} that first applies the {@code before} operations to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code int}.
     * @see #andThen(ToIntFunction)
     * @see #andThen(Function)
     */
    @Nonnull
    default IntTriFunction<R> compose(@Nonnull final IntUnaryOperator before1, @Nonnull final IntUnaryOperator before2,
            @Nonnull final IntUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2, value3) -> apply(before1.applyAsInt(value1), before2.applyAsInt(value2),
                                                 before3.applyAsInt(value3));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param <V> The type of the argument to the third before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@link TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ToIntFunction)
     * @see #andThen(Function)
     */
    @Nonnull
    default <T, U, V> TriFunction<T, U, V, R> compose(@Nonnull final ToIntFunction<? super T> before1,
            @Nonnull final ToIntFunction<? super U> before2, @Nonnull final ToIntFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u, v) -> apply(before1.applyAsInt(t), before2.applyAsInt(u), before3.applyAsInt(v));
    }

    /**
     * Returns a composed {@link IntTernaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link IntTernaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is the primitive specialization of {@link TernaryOperator}. Therefore the
     * returned operation handles primitive types. In this case this is {@code int}.
     * @see #compose(IntUnaryOperator, IntUnaryOperator, IntUnaryOperator)
     * @see #compose(ToIntFunction, ToIntFunction, ToIntFunction)
     */
    @Nonnull
    default IntTernaryOperator andThen(@Nonnull final ToIntFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link IntTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@link IntTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned function is able to handle every type.
     * @see #compose(IntUnaryOperator, IntUnaryOperator, IntUnaryOperator)
     * @see #compose(ToIntFunction, ToIntFunction, ToIntFunction)
     */
    @Nonnull
    default <S> IntTriFunction<S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link IntTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code IntTriConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default IntTriConsumer consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link IntTriFunction}. Thereby the primitive input
     * argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code IntTriFunction}.
     */
    @Nonnull
    default TriFunction<Integer, Integer, Integer, R> boxed() {
        return this::apply;
    }
}
