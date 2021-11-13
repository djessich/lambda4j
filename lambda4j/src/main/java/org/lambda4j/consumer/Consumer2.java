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
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;

/**
 * Represents an operation that accepts one input argument and returns no result. Unlike most other functional
 * interfaces, {@code Consumer2} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object)}.
 *
 * @param <T> The type of the argument to the consumer
 * @apiNote This is a JDK lambda.
 * @see Consumer2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface Consumer2<T> extends Lambda, Consumer<T> {

    /**
     * Constructs a {@link Consumer2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the argument to the consumer
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code Consumer2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T> Consumer2<T> of(@Nullable Consumer2<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link Consumer} with the given argument and returns its result.
     *
     * @param <T> The type of the argument to the consumer
     * @param consumer The consumer to be called
     * @param t The argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> void call(@Nonnull Consumer<? super T> consumer, T t) {
        Objects.requireNonNull(consumer);
        consumer.accept(t);
    }

    /**
     * Applies this consumer to the given argument.
     *
     * @param t The argument to the consumer
     */
    @Override
    void accept(T t);

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
    default <A> Consumer2<A> compose(@Nonnull Function<? super A, ? extends T> before) {
        Objects.requireNonNull(before);
        return a -> accept(before.apply(a));
    }

    /**
     * Returns a composed {@link BooleanConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code boolean} input, before this consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code BooleanConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanConsumer composeFromBoolean(@Nonnull BooleanFunction<? extends T> before) {
        Objects.requireNonNull(before);
        return value -> accept(before.apply(value));
    }

    /**
     * Returns a composed {@link ByteConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ByteConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteConsumer composeFromByte(@Nonnull ByteFunction<? extends T> before) {
        Objects.requireNonNull(before);
        return value -> accept(before.apply(value));
    }

    /**
     * Returns a composed {@link CharConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code CharConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharConsumer composeFromChar(@Nonnull CharFunction<? extends T> before) {
        Objects.requireNonNull(before);
        return value -> accept(before.apply(value));
    }

    /**
     * Returns a composed {@link DoubleConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code double} input, before this consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code DoubleConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleConsumer2 composeFromDouble(@Nonnull DoubleFunction<? extends T> before) {
        Objects.requireNonNull(before);
        return value -> accept(before.apply(value));
    }

    /**
     * Returns a composed {@link FloatConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code FloatConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatConsumer composeFromFloat(@Nonnull FloatFunction<? extends T> before) {
        Objects.requireNonNull(before);
        return value -> accept(before.apply(value));
    }

    /**
     * Returns a composed {@link IntConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code IntConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntConsumer2 composeFromInt(@Nonnull IntFunction<? extends T> before) {
        Objects.requireNonNull(before);
        return value -> accept(before.apply(value));
    }

    /**
     * Returns a composed {@link LongConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code LongConsumer2} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongConsumer2 composeFromLong(@Nonnull LongFunction<? extends T> before) {
        Objects.requireNonNull(before);
        return value -> accept(before.apply(value));
    }

    /**
     * Returns a composed {@link ShortConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ShortConsumer} that first applies the {@code before} function to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortConsumer composeFromShort(@Nonnull ShortFunction<? extends T> before) {
        Objects.requireNonNull(before);
        return value -> accept(before.apply(value));
    }

    /**
     * Returns a composed {@link Consumer2} that performs, in sequence, this consumer followed by the {@code after}
     * consumer. If evaluation of either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this consumer throws an exception, the {@code after} consumer will not be performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link Consumer2} that performs, in sequence, this consumer followed by the {@code after}
     * consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Override
    @Nonnull
    default Consumer2<T> andThen(@Nonnull Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return t -> {
            accept(t);
            after.accept(t);
        };
    }

    /**
     * Returns a reversed version of this consumer. This may be useful in recursive context.
     *
     * @return A reversed version of this consumer.
     */
    @Nonnull
    default Consumer2<T> reversed() {
        return this;
    }
}
