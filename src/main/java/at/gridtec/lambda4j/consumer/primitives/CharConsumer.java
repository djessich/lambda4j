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

import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts a single {@code char}-valued argument and returns no result. This is the
 * primitive type specialization of {@link Consumer} for {@code char}. Unlike most other functional interfaces, {@code
 * CharConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(char)}.
 *
 * @see java.util.function.Consumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value The argument to the operation to be consumed
     */
    void accept(char value);

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
     * Returns a composed {@link CharConsumer} that first applies the {@code before} operation to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param before The operation to apply before this operator is applied
     * @return A composed {@link CharConsumer} that first applies the {@code before} operation to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is the primitive specialization of {@link UnaryOperator}. Therefore
     * the given operation handles primitive types. In this case this is {@code char}.
     * @see #andThen(CharConsumer)
     */
    @Nonnull
    default CharConsumer compose(@Nonnull final CharUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsChar(value));
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
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(CharConsumer)
     */
    @Nonnull
    default <T> Consumer<T> compose(@Nonnull final ToCharFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> accept(before.applyAsChar(t));
    }

    /**
     * Returns a composed {@link CharConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation. If evaluation of either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link CharConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(CharUnaryOperator)
     * @see #compose(ToCharFunction)
     */
    @Nonnull
    default CharConsumer andThen(@Nonnull final CharConsumer after) {
        Objects.requireNonNull(after);
        return value -> {
            accept(value);
            after.accept(value);
        };
    }

    /**
     * Returns a composed {@link Consumer} which represents this {@link CharConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed. This method is just convenience to provide the ability to use this
     * {@code CharConsumer} with JRE specific methods, only accepting {@code Consumer}.
     *
     * @return A composed {@code Consumer} which represents this {@code CharConsumer}.
     */
    @Nonnull
    default Consumer<Character> boxed() {
        return this::accept;
    }
}
