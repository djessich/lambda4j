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

package at.gridtec.lambda4j.consumer.primitives.tri;

import at.gridtec.lambda4j.consumer.TriConsumer;
import at.gridtec.lambda4j.consumer.primitives.CharConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.CharBiConsumer;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts three {@code char}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link TriConsumer} for {@code char}. Unlike most other functional interfaces, {@code
 * CharTriConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(char, char, char)}.
 *
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharTriConsumer {

    /**
     * Calls the given {@link CharTriConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    static void call(@Nonnull final CharTriConsumer consumer, char value1, char value2, char value3) {
        Objects.requireNonNull(consumer);
        consumer.accept(value1, value2, value3);
    }

    /**
     * Creates a {@link CharTriConsumer} which uses the {@code first} parameter as argument for the given {@link
     * CharConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code CharTriConsumer} which uses the {@code first} parameter as argument for the given {@code
     * CharConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharTriConsumer onlyFirst(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link CharTriConsumer} which uses the {@code second} parameter as argument for the given {@link
     * CharConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code CharTriConsumer} which uses the {@code second} parameter as argument for the given
     * {@code CharConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharTriConsumer onlySecond(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value2);
    }

    /**
     * Creates a {@link CharTriConsumer} which uses the {@code third} parameter as argument for the given {@link
     * CharConsumer}.
     *
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code CharTriConsumer} which uses the {@code third} parameter as argument for the given {@code
     * CharConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharTriConsumer onlyThird(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value3);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     */
    void accept(char value1, char value2, char value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link CharTriConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @param before3 The third operation to apply before this operation is applied
     * @return A composed {@link CharTriConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code char}.
     * @see #andThen(CharTriConsumer)
     */
    @Nonnull
    default CharTriConsumer compose(@Nonnull final CharUnaryOperator before1, @Nonnull final CharUnaryOperator before2,
            @Nonnull final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsChar(value1), before2.applyAsChar(value2),
                                                  before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @param before3 The third operation to apply before this operation is applied
     * @return A composed {@link TriConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(CharTriConsumer)
     */
    @Nonnull
    default <T, U, V> TriConsumer<T, U, V> compose(@Nonnull final ToCharFunction<? super T> before1,
            @Nonnull final ToCharFunction<? super U> before2, @Nonnull final ToCharFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> accept(before1.applyAsChar(t), before2.applyAsChar(u), before3.applyAsChar(v));
    }

    /**
     * Returns a composed {@link CharTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link CharTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator, CharUnaryOperator)
     * @see #compose(ToCharFunction, ToCharFunction, ToCharFunction)
     */
    @Nonnull
    default CharTriConsumer andThen(@Nonnull final CharTriConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> {
            accept(value1, value2, value3);
            after.accept(value1, value2, value3);
        };
    }

    /**
     * Applies this operation partially to one argument. The result is an operation of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default CharBiConsumer partial(char value1) {
        return (value2, value3) -> accept(value1, value2, value3);
    }

    /**
     * Applies this operation partially to two arguments. The result is an operation of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the operation
     * @param value2 The second argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default CharConsumer partial(char value1, char value2) {
        return value3 -> accept(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link CharTriConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed.
     *
     * @return A composed {@code TriConsumer} which represents this {@code CharTriConsumer}.
     */
    @Nonnull
    default TriConsumer<Character, Character, Character> boxed() {
        return this::accept;
    }
}
