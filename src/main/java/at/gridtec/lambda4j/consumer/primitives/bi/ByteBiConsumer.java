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

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two {@code byte}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link BiConsumer} for {@code byte}. Unlike most other functional interfaces, {@code
 * ByteBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(byte, byte)}.
 *
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteBiConsumer {

    /**
     * Creates a {@link ByteBiConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link ByteConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ByteBiConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code ByteConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ByteBiConsumer onlyFirst(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link ByteBiConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link ByteConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ByteBiConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@code ByteConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ByteBiConsumer onlySecond(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value2);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     */
    void accept(byte value1, byte value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ByteBiConsumer} that applies the given {@code before} {@link ByteUnaryOperator}s to its
     * input, and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param before1 The first before {@code ByteUnaryOperator} to apply before this operation is applied
     * @param before2 The second before {@code ByteUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ByteBiConsumer} that applies the given {@code before} {@code ByteUnaryOperator}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ByteBiConsumer)
     */
    default ByteBiConsumer compose(final ByteUnaryOperator before1, final ByteUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsByte(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link BiConsumer} that applies the given {@code before} {@link ToByteFunction}s to its input,
     * and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first before {@code ToByteFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToByteFunction} to apply before this operation is applied
     * @return A composed {@code BiConsumer} that applies the given {@code before} {@code ToByteFunction}s to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ByteBiConsumer)
     */
    default <T, U> BiConsumer<T, U> compose(final ToByteFunction<? super T> before1,
            final ToByteFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsByte(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link ByteBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link ByteBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(ByteUnaryOperator, ByteUnaryOperator)
     * @see #compose(ToByteFunction, ToByteFunction)
     */
    default ByteBiConsumer andThen(final ByteBiConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> {
            accept(value1, value2);
            after.accept(value1, value2);
        };
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link ByteBiConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ByteBiConsumer} with JRE specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code ByteBiConsumer}.
     */
    @Nonnull
    default BiConsumer<Byte, Byte> boxed() {
        return this::accept;
    }
}
