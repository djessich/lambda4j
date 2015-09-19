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

import java.util.Objects;
import java.util.function.Consumer;

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
     * @param value The argument for the operation to be consumed
     */
    void accept(byte value);

    /**
     * Returns a composed {@link ByteConsumer} that applies the given {@code before} {@link ByteUnaryOperator} to its
     * input, and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param before The before {@code ByteUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ByteConsumer} that applies the given {@code before} {@code Function} to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ByteConsumer)
     */
    default ByteConsumer compose(final ByteUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link Consumer} that applies the given {@code before} {@link ToByteFunction} to its input,
     * and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The before {@code ToByteFunction} to apply before this operation is applied
     * @return A composed {@code Consumer} that applies the given {@code before} {@code ToByteFunction} to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ByteConsumer)
     */
    default <T> Consumer<T> compose(final ToByteFunction<? super T> before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation. If evaluation of either operation throws an exception, it is relayed to the caller of the composed
     * function. If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link ByteConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(ByteUnaryOperator)
     * @see #compose(ToByteFunction)
     */
    default ByteConsumer andThen(final ByteConsumer after) {
        Objects.requireNonNull(after);
        return value -> {
            accept(value);
            after.accept(value);
        };
    }

    /**
     * Returns a composed {@link Consumer} which represents this {@link ByteConsumer}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ByteConsumer} with JRE specific methods, only accepting {@code Consumer}.
     *
     * @return A composed {@code Consumer} which represents this {@code ByteConsumer}.
     */
    default Consumer<Byte> boxed() {
        return this::accept;
    }
}
