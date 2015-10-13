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

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ToDoubleFunction;

/**
 * Represents an operation that accepts two {@code double}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link BiConsumer} for {@code double}. Unlike most other functional interfaces, {@code
 * DoubleBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(double, double)}.
 *
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleBiConsumer {

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
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link DoubleBiConsumer} that applies the given {@code before} {@link DoubleUnaryOperator}s to
     * its input, and then applies this operation to the result. If evaluation of either of the given operations throws
     * an exception, it is relayed to the caller of the composed function.
     *
     * @param before1 The first before {@code DoubleUnaryOperator} to apply before this operation is applied
     * @param before2 The second before {@code DoubleUnaryOperator} to apply before this operation is applied
     * @return A composed {@code DoubleBiConsumer} that applies the given {@code before} {@code DoubleUnaryOperator}s to
     * its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(DoubleBiConsumer)
     */
    default DoubleBiConsumer compose(final DoubleUnaryOperator before1, final DoubleUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiConsumer} that applies the given {@code before} {@link ToDoubleFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first before {@code ToDoubleFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToDoubleFunction} to apply before this operation is applied
     * @return A composed {@code BiConsumer} that applies the given {@code before} {@code ToDoubleFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(DoubleBiConsumer)
     */
    default <T, U> BiConsumer<T, U> compose(final ToDoubleFunction<? super T> before1,
            final ToDoubleFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link DoubleBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link DoubleBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(DoubleUnaryOperator, DoubleUnaryOperator)
     * @see #compose(ToDoubleFunction, ToDoubleFunction)
     */
    default DoubleBiConsumer andThen(final DoubleBiConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> {
            accept(value1, value2);
            after.accept(value1, value2);
        };
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
