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

package at.gridtec.lambda4j.consumer.primitives.tri;

import at.gridtec.lambda4j.consumer.TriConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.LongBiConsumer;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.LongConsumer;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts three {@code long}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link TriConsumer} for {@code long}. Unlike most other functional interfaces, {@code
 * LongTriConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(long, long, long)}.
 *
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface LongTriConsumer {

    /**
     * Calls the given {@link LongTriConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    static void call(@Nonnull final LongTriConsumer consumer, long value1, long value2, long value3) {
        Objects.requireNonNull(consumer);
        consumer.accept(value1, value2, value3);
    }

    /**
     * Creates a {@link LongTriConsumer} which uses the {@code first} parameter as argument for the given {@link
     * LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code LongTriConsumer} which uses the {@code first} parameter as argument for the given {@code
     * LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTriConsumer onlyFirst(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link LongTriConsumer} which uses the {@code second} parameter as argument for the given {@link
     * LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code LongTriConsumer} which uses the {@code second} parameter as argument for the given
     * {@code LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTriConsumer onlySecond(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value2);
    }

    /**
     * Creates a {@link LongTriConsumer} which uses the {@code third} parameter as argument for the given {@link
     * LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code LongTriConsumer} which uses the {@code third} parameter as argument for the given {@code
     * LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTriConsumer onlyThird(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value3);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     */
    void accept(long value1, long value2, long value3);

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
     * Returns a composed {@link LongTriConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @param before3 The third operation to apply before this operation is applied
     * @return A composed {@link LongTriConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code long}.
     * @see #andThen(LongTriConsumer)
     */
    @Nonnull
    default LongTriConsumer compose(@Nonnull final LongUnaryOperator before1, @Nonnull final LongUnaryOperator before2,
            @Nonnull final LongUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsLong(value1), before2.applyAsLong(value2),
                                                  before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @param before3 The third operation to apply before this operation is applied
     * @return A composed {@link TriConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(LongTriConsumer)
     */
    @Nonnull
    default <T, U, V> TriConsumer<T, U, V> compose(@Nonnull final ToLongFunction<? super T> before1,
            @Nonnull final ToLongFunction<? super U> before2, @Nonnull final ToLongFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> accept(before1.applyAsLong(t), before2.applyAsLong(u), before3.applyAsLong(v));
    }

    /**
     * Returns a composed {@link LongTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link LongTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(LongUnaryOperator, LongUnaryOperator, LongUnaryOperator)
     * @see #compose(ToLongFunction, ToLongFunction, ToLongFunction)
     */
    @Nonnull
    default LongTriConsumer andThen(@Nonnull final LongTriConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> {
            accept(value1, value2, value3);
            after.accept(value1, value2, value3);
        };
    }

    /**
     * Applies this operation partially to one argument. The result is an operation of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default LongBiConsumer partial(long value1) {
        return (value2, value3) -> accept(value1, value2, value3);
    }

    /**
     * Applies this operation partially to two arguments. The result is an operation of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the operation
     * @param value2 The second argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default LongConsumer partial(long value1, long value2) {
        return value3 -> accept(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link LongTriConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed.
     *
     * @return A composed {@code TriConsumer} which represents this {@code LongTriConsumer}.
     */
    @Nonnull
    default TriConsumer<Long, Long, Long> boxed() {
        return this::accept;
    }
}
