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
package at.gridtec.lambda4j.consumer.primitives.bi;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.LongConsumer;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;

/**
 * Represents an operation that accepts two {@code long}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link BiConsumer} for {@code long}. Unlike most other functional interfaces, {@code
 * LongBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(long, long)}.
 *
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface LongBiConsumer {

    /**
     * Creates a {@link LongBiConsumer} which uses the {@code first} parameter of this one as argument for the given {@link
     * LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code LongBiConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@code LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static LongBiConsumer onlyFirst(final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link LongBiConsumer} which uses the {@code second} parameter of this one as argument for the given {@link
     * LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code LongBiConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@code LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static LongBiConsumer onlySecond(final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value2);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     */
    void accept(long value1, long value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link LongBiConsumer} that applies the given {@code before} {@link LongUnaryOperator}s to its
     * input, and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param before1 The first before {@code LongUnaryOperator} to apply before this operation is applied
     * @param before2 The second before {@code LongUnaryOperator} to apply before this operation is applied
     * @return A composed {@code LongBiConsumer} that applies the given {@code before} {@code LongUnaryOperator}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(LongBiConsumer)
     */
    default LongBiConsumer compose(final LongUnaryOperator before1, final LongUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link BiConsumer} that applies the given {@code before} {@link ToLongFunction}s to its input,
     * and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first before {@code ToLongFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToLongFunction} to apply before this operation is applied
     * @return A composed {@code BiConsumer} that applies the given {@code before} {@code ToLongFunction}s to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(LongBiConsumer)
     */
    default <T, U> BiConsumer<T, U> compose(final ToLongFunction<? super T> before1,
            final ToLongFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link LongBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link LongBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(LongUnaryOperator, LongUnaryOperator)
     * @see #compose(ToLongFunction, ToLongFunction)
     */
    default LongBiConsumer andThen(final LongBiConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> {
            accept(value1, value2);
            after.accept(value1, value2);
        };
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link LongBiConsumer}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code LongBiConsumer} with JRE specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code LongBiConsumer}.
     */
    default BiConsumer<Long, Long> boxed() {
        return this::accept;
    }
}
