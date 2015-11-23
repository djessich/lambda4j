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
import java.util.function.DoubleConsumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ToDoubleFunction;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts two {@code double}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link BiConsumer} for {@code double}. Unlike most other functional interfaces, {@code
 * DoubleBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(double, double)}.
 *
 * @see BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleBiConsumer {

    /**
     * Calls the given {@link DoubleBiConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    static void call(@Nonnull final DoubleBiConsumer consumer, double value1, double value2) {
        Objects.requireNonNull(consumer);
        consumer.accept(value1, value2);
    }

    /**
     * Creates a {@link DoubleBiConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link DoubleConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code DoubleBiConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code DoubleConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static DoubleBiConsumer onlyFirst(@Nonnull final DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link DoubleBiConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link DoubleConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code DoubleBiConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@code DoubleConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static DoubleBiConsumer onlySecond(@Nonnull final DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value2);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     */
    void accept(double value1, double value2);

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
     * Returns a composed {@link DoubleBiConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @return A composed {@link DoubleBiConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code double}.
     * @see #andThen(DoubleBiConsumer)
     */
    @Nonnull
    default DoubleBiConsumer compose(@Nonnull final DoubleUnaryOperator before1,
            @Nonnull final DoubleUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
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
     * @see #andThen(DoubleBiConsumer)
     */
    @Nonnull
    default <T, U> BiConsumer<T, U> compose(@Nonnull final ToDoubleFunction<? super T> before1,
            @Nonnull final ToDoubleFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> accept(before1.applyAsDouble(t), before2.applyAsDouble(u));
    }

    /**
     * Returns a composed {@link DoubleBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link DoubleBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(DoubleUnaryOperator, DoubleUnaryOperator)
     * @see #compose(ToDoubleFunction, ToDoubleFunction)
     */
    @Nonnull
    default DoubleBiConsumer andThen(@Nonnull final DoubleBiConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> {
            accept(value1, value2);
            after.accept(value1, value2);
        };
    }

    /**
     * Applies this operation partially to one argument. The result is an operation of arity {@code 1}.
     *
     * @param value1 The argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default DoubleConsumer partial(double value1) {
        return value2 -> accept(value1, value2);
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link DoubleBiConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed. This method is just convenience to provide the ability to use this
     * {@code DoubleBiConsumer} with JRE specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code DoubleBiConsumer}.
     */
    @Nonnull
    default BiConsumer<Double, Double> boxed() {
        return this::accept;
    }
}
