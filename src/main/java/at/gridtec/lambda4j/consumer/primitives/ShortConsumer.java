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

import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts a single {@code short}-valued argument and returns no result. This is the
 * primitive type specialization of {@link Consumer} for {@code short}. Unlike most other functional interfaces, {@code
 * ShortConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(short)}.
 *
 * @see java.util.function.Consumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value The argument for the operation to be consumed
     */
    void accept(short value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ShortConsumer} that applies the given {@code before} {@link ShortUnaryOperator} to its
     * input, and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param before The before {@code ShortUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ShortConsumer} that applies the given {@code before} {@code ShortUnaryOperator} to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ShortConsumer)
     */
    default ShortConsumer compose(final ShortUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link Consumer} that applies the given {@code before} {@link ToShortFunction} to its input,
     * and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The before {@code ToShortFunction} to apply before this operation is applied
     * @return A composed {@code Consumer} that applies the given {@code before} {@code ToShortFunction} to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ShortConsumer)
     */
    default <T> Consumer<T> compose(final ToShortFunction<? super T> before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation. If evaluation of either operation throws an exception, it is relayed to the caller of the composed
     * function. If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link ShortConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(ShortUnaryOperator)
     * @see #compose(ToShortFunction)
     */
    default ShortConsumer andThen(final ShortConsumer after) {
        Objects.requireNonNull(after);
        return value -> {
            accept(value);
            after.accept(value);
        };
    }

    /**
     * Returns a composed {@link Consumer} which represents this {@link ShortConsumer}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortConsumer} with JRE specific methods, only accepting {@code Consumer}.
     *
     * @return A composed {@code Consumer} which represents this {@code ShortConsumer}.
     */
    default Consumer<Short> boxed() {
        return this::accept;
    }
}
