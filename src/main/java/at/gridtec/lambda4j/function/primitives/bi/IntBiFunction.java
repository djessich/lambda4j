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
package at.gridtec.lambda4j.function.primitives.bi;

import at.gridtec.lambda4j.consumer.primitives.bi.IntBiConsumer;

import java.util.Objects;
import java.util.function.*;

/**
 * Represents a function that accepts two int-valued arguments and produces a result. This is the {@code int}-consuming
 * primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(int, int)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface IntBiFunction<R> {

    /**
     * Creates a {@link IntBiFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code IntBiFunction} which always returns a given value.
     */
    static <R> IntBiFunction<R> constant(R r) {
        return (value1, value2) -> r;
    }

    /**
     * Creates a {@link IntBiFunction} which uses the first parameter of this one as argument for the given {@link
     * IntFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code IntBiFunction} which uses the first parameter of this one as argument for the given
     * {@code IntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> IntBiFunction<R> onlyFirst(final IntFunction<R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value1);
    }

    /**
     * Creates a {@link IntBiFunction} which uses the second parameter of this one as argument for the given {@link
     * IntFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code IntBiFunction} which uses the second parameter of this one as argument for the given
     * {@code IntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> IntBiFunction<R> onlySecond(final IntFunction<R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value2);
    }

    /**
     * Applies this {@link IntBiFunction} to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(int value1, int value2);

    /**
     * Returns a composed {@link IntBiFunction} that first applies the {@code before} {@link IntUnaryOperator}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code IntUnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code IntUnaryOperator} to apply before this operation is applied
     * @return A composed {@code IntBiFunction} that first applies the {@code before} {@code IntBiUnaryOperator}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default IntBiFunction<R> compose(final IntUnaryOperator before1, final IntUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiFunction} that applies the given {@code before} {@link ToIntFunction}s to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first before {@code ToIntFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToIntFunction} to apply before this operation is applied
     * @return A composed {@code BiFunction} that applies the given {@code before} {@code ToIntFunction}s to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T, V> BiFunction<T, V, R> compose(final ToIntFunction<? super T> before1,
            final ToIntFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link IntBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code IntBiFunction} to apply after this operation is applied
     * @return A composed {@code IntBiFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(IntUnaryOperator, IntUnaryOperator)
     * @see #compose(ToIntFunction, ToIntFunction)
     */
    default <S> IntBiFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(apply(value1, value2));
    }

    /**
     * Returns a composed {@link IntBiConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code IntBiConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default IntBiConsumer consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(this.apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link IntBiFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code IntBiFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code IntBiFunction}.
     */
    default BiFunction<Integer, Integer, R> boxed() {
        return this::apply;
    }
}
