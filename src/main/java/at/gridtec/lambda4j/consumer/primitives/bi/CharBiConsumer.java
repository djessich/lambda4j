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

import at.gridtec.lambda4j.consumer.primitives.CharConsumer;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two {@code char}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link BiConsumer} for {@code char}. Unlike most other functional interfaces, {@code
 * CharBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(char, char)}.
 *
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharBiConsumer {

    /**
     * Creates a {@link CharBiConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link CharConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code CharBiConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code CharConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharBiConsumer onlyFirst(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link CharBiConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link CharConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code CharBiConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@code CharConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharBiConsumer onlySecond(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value2);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     */
    void accept(char value1, char value2);

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
     * Returns a composed {@link CharBiConsumer} that applies the given {@code before} {@link CharUnaryOperator}s to its
     * input, and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param before1 The first before {@code CharUnaryOperator} to apply before this operation is applied
     * @param before2 The second before {@code CharUnaryOperator} to apply before this operation is applied
     * @return A composed {@code CharBiConsumer} that applies the given {@code before} {@code CharUnaryOperator}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(CharBiConsumer)
     */
    default CharBiConsumer compose(final CharUnaryOperator before1, final CharUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsChar(value1), before2.applyAsChar(value2));
    }

    /**
     * Returns a composed {@link BiConsumer} that applies the given {@code before} {@link ToCharFunction}s to its input,
     * and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first before {@code ToCharFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToCharFunction} to apply before this operation is applied
     * @return A composed {@code BiConsumer} that applies the given {@code before} {@code ToCharFunction}s to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(CharBiConsumer)
     */
    default <T, U> BiConsumer<T, U> compose(final ToCharFunction<? super T> before1,
            final ToCharFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsChar(value1), before2.applyAsChar(value2));
    }

    /**
     * Returns a composed {@link CharBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link CharBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator)
     * @see #compose(ToCharFunction, ToCharFunction)
     */
    default CharBiConsumer andThen(final CharBiConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> {
            accept(value1, value2);
            after.accept(value1, value2);
        };
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link CharBiConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed. This method is just convenience to provide the ability to use this
     * {@code CharBiConsumer} with JRE specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code CharBiConsumer}.
     */
    @Nonnull
    default BiConsumer<Character, Character> boxed() {
        return this::accept;
    }
}
