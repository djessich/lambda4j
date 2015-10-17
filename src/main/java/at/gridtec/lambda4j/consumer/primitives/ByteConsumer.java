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
package at.gridtec.lambda4j.consumer.primitives;

import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts a single {@code byte}-valued argument and returns no result. This is the
 * primitive type specialization of {@link Consumer} for {@code byte}. Unlike most other functional interfaces, {@code
 * ByteConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(byte)}.
 *
 * @see java.util.function.Consumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value The argument to the operation to be consumed
     */
    void accept(byte value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ByteConsumer} that first applies the {@code before} operation to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param before The operation to apply before this operator is applied
     * @return A composed {@link ByteConsumer} that first applies the {@code before} operation to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is the primitive specialization of {@link UnaryOperator}. Therefore
     * the given operation handles primitive types. In this case this is {@code byte}.
     * @see #andThen(ByteConsumer)
     */
    @Nonnull
    default ByteConsumer compose(@Nonnull final ByteUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link Consumer} that first applies the {@code before} operation to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The operation to apply before this operation is applied
     * @return A composed {@link Consumer} that first applies the {@code before} operation to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ByteConsumer)
     */
    @Nonnull
    default <T> Consumer<T> compose(@Nonnull final ToByteFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> accept(before.applyAsByte(t));
    }

    /**
     * Returns a composed {@link ByteConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation. If evaluation of either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link ByteConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ByteUnaryOperator)
     * @see #compose(ToByteFunction)
     */
    @Nonnull
    default ByteConsumer andThen(@Nonnull final ByteConsumer after) {
        Objects.requireNonNull(after);
        return value -> {
            accept(value);
            after.accept(value);
        };
    }

    /**
     * Returns a composed {@link Consumer} which represents this {@link ByteConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ByteConsumer} with JRE specific methods, only accepting {@code Consumer}.
     *
     * @return A composed {@code Consumer} which represents this {@code ByteConsumer}.
     */
    @Nonnull
    default Consumer<Byte> boxed() {
        return this::accept;
    }
}
