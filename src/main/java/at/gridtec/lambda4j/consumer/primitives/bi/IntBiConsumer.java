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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts two {@code int}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link BiConsumer} for {@code int}. Unlike most other functional interfaces, {@code
 * IntBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(int, int)}.
 *
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface IntBiConsumer {

    /**
     * Creates a {@link IntBiConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link IntConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code IntBiConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code IntConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntBiConsumer onlyFirst(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link IntBiConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link IntConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code IntBiConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@code IntConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntBiConsumer onlySecond(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value2);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     */
    void accept(int value1, int value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link IntBiConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @return A composed {@link IntBiConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code int}.
     * @see #andThen(IntBiConsumer)
     */
    @Nonnull
    default IntBiConsumer compose(@Nonnull final IntUnaryOperator before1, @Nonnull final IntUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @return A composed {@link BiConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(IntBiConsumer)
     */
    @Nonnull
    default <T, U> BiConsumer<T, U> compose(@Nonnull final ToIntFunction<? super T> before1,
            @Nonnull final ToIntFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> accept(before1.applyAsInt(t), before2.applyAsInt(u));
    }

    /**
     * Returns a composed {@link IntBiConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation. If evaluation of either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link IntBiConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(IntUnaryOperator, IntUnaryOperator)
     * @see #compose(ToIntFunction, ToIntFunction)
     */
    @Nonnull
    default IntBiConsumer andThen(@Nonnull final IntBiConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> {
            accept(value1, value2);
            after.accept(value1, value2);
        };
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link IntBiConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed. This method is just convenience to provide the ability to use this
     * {@code IntBiConsumer} with JRE specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code IntBiConsumer}.
     */
    @Nonnull
    default BiConsumer<Integer, Integer> boxed() {
        return this::accept;
    }
}
