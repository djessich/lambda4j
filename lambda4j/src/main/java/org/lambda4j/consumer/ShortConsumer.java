/*
 * Copyright (c) 2021 The lambda4j authors.
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

package org.lambda4j.consumer;

import java.util.Objects;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.conversion.ByteToShortFunction;
import org.lambda4j.function.conversion.CharToShortFunction;
import org.lambda4j.function.conversion.DoubleToShortFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.conversion.IntToShortFunction;
import org.lambda4j.function.conversion.LongToShortFunction;
import org.lambda4j.function.to.ToShortFunction;
import org.lambda4j.operator.unary.ShortUnaryOperator;

/**
 * Represents an operation that accepts one {@code short}-valued input argument and returns no result. This is a
 * primitive specialization of {@link Consumer2}. Unlike most other functional interfaces, {@code ShortConsumer} is
 * expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(short)}.
 *
 * @see Consumer2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortConsumer extends Lambda {

    /**
     * Constructs a {@link ShortConsumer} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ShortConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static ShortConsumer of(@Nullable ShortConsumer expression) {
        return expression;
    }

    /**
     * Calls the given {@link ShortConsumer} with the given argument and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value The argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     */
    static void call(@Nonnull ShortConsumer consumer, short value) {
        Objects.requireNonNull(consumer);
        consumer.accept(value);
    }

    /**
     * Applies this consumer to the given argument.
     *
     * @param value The argument to the consumer
     */
    void accept(short value);

    /**
     * Returns the number of arguments for this consumer.
     *
     * @return The number of arguments for this consumer.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link Consumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed consumer
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code Consumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> Consumer2<A> compose(@Nonnull ToShortFunction<? super A> before) {
        Objects.requireNonNull(before);
        return a -> accept(before.applyAsShort(a));
    }

    /**
     * Returns a composed {@link BooleanConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code boolean} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code BooleanConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanConsumer composeFromBoolean(@Nonnull BooleanToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ByteConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ByteConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteConsumer composeFromByte(@Nonnull ByteToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link CharConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code CharConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharConsumer composeFromChar(@Nonnull CharToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link DoubleConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code double} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code DoubleConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleConsumer2 composeFromDouble(@Nonnull DoubleToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code FloatConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatConsumer composeFromFloat(@Nonnull FloatToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link IntConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code IntConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntConsumer2 composeFromInt(@Nonnull IntToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code LongConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongConsumer2 composeFromLong(@Nonnull LongToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortConsumer} that first applies the {@code before} operator to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this primitive consumer is executed.
     *
     * @param before The operator to apply before this consumer is applied
     * @return A composed {@code ShortConsumer} that first applies the {@code before} operator to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortConsumer composeFromShort(@Nonnull ShortUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> accept(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortConsumer} that performs, in sequence, this consumer followed by the {@code after}
     * consumer. If evaluation of either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this consumer throws an exception, the {@code after} consumer will not be performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link ShortConsumer} that performs, in sequence, this consumer followed by the {@code after}
     * consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortConsumer andThen(@Nonnull ShortConsumer after) {
        Objects.requireNonNull(after);
        return value -> {
            accept(value);
            after.accept(value);
        };
    }

    /**
     * Returns a reversed version of this consumer. This may be useful in recursive context.
     *
     * @return A reversed version of this consumer.
     */
    @Nonnull
    default ShortConsumer reversed() {
        return this;
    }

    /**
     * Returns a composed {@link Consumer2} which represents this {@link ShortConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed. This method provides the possibility to use this {@code ShortConsumer}
     * with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Consumer2} which represents this {@code ShortConsumer}.
     */
    @Nonnull
    default Consumer2<Short> boxed() {
        return this::accept;
    }
}
