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

package org.lambda4j.consumer.tri;

import java.util.Objects;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.DoubleConsumer2;
import org.lambda4j.consumer.bi.BiDoubleConsumer;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.ByteToDoubleFunction;
import org.lambda4j.function.conversion.CharToDoubleFunction;
import org.lambda4j.function.conversion.FloatToDoubleFunction;
import org.lambda4j.function.conversion.ShortToDoubleFunction;

/**
 * Represents an operation that accepts three {@code double}-valued input arguments and returns no result. This is a
 * primitive specialization of {@link TriConsumer}. Unlike most other functional interfaces, {@code TriDoubleConsumer}
 * is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(double, double, double)}.
 *
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriDoubleConsumer extends Lambda {

    /**
     * Constructs a {@link TriDoubleConsumer} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriDoubleConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static TriDoubleConsumer of(@Nullable TriDoubleConsumer expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriDoubleConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the consumer
     * @param value2 The second argument to the consumer
     * @param value3 The third argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     */
    static void call(@Nonnull TriDoubleConsumer consumer, double value1, double value2, double value3) {
        Objects.requireNonNull(consumer);
        consumer.accept(value1, value2, value3);
    }

    /**
     * Creates a {@link TriDoubleConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link DoubleConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriDoubleConsumer} which uses the {@code first} parameter of this one as argument for
     * the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriDoubleConsumer onlyFirst(@Nonnull DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link TriDoubleConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link DoubleConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriDoubleConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriDoubleConsumer onlySecond(@Nonnull DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value2);
    }

    /**
     * Creates a {@link TriDoubleConsumer} which uses the {@code third} parameter of this one as argument for the given
     * {@link DoubleConsumer}.
     *
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriDoubleConsumer} which uses the {@code third} parameter of this one as argument for
     * the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriDoubleConsumer onlyThird(@Nonnull DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value3);
    }

    /**
     * Applies this consumer to the given arguments.
     *
     * @param value1 The first argument to the consumer
     * @param value2 The second argument to the consumer
     * @param value3 The third argument to the consumer
     */
    void accept(double value1, double value2, double value3);

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link BiDoubleConsumer} as result.
     *
     * @param value1 The first argument to this consumer used to partially apply this function
     * @return A {@code BiDoubleConsumer} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default BiDoubleConsumer acceptPartially(double value1) {
        return (value2, value3) -> accept(value1, value2, value3);
    }

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link DoubleConsumer2} as result.
     *
     * @param value1 The first argument to this consumer used to partially apply this function
     * @param value2 The second argument to this consumer used to partially apply this function
     * @return A {@code DoubleConsumer2} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default DoubleConsumer2 acceptPartially(double value1, double value2) {
        return value3 -> accept(value1, value2, value3);
    }

    /**
     * Returns the number of arguments for this consumer.
     *
     * @return The number of arguments for this consumer.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link TriConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed consumer
     * @param <B> The type of the argument to the second given function, and of composed consumer
     * @param <C> The type of the argument to the third given function, and of composed consumer
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code TriConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> TriConsumer<A, B, C> compose(@Nonnull ToDoubleFunction<? super A> before1,
            @Nonnull ToDoubleFunction<? super B> before2, @Nonnull ToDoubleFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> accept(before1.applyAsDouble(a), before2.applyAsDouble(b), before3.applyAsDouble(c));
    }

    /**
     * Returns a composed {@link TriBooleanConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code TriBooleanConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanConsumer composeFromBoolean(@Nonnull BooleanToDoubleFunction before1,
            @Nonnull BooleanToDoubleFunction before2, @Nonnull BooleanToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriByteConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code TriByteConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteConsumer composeFromByte(@Nonnull ByteToDoubleFunction before1,
            @Nonnull ByteToDoubleFunction before2, @Nonnull ByteToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriCharConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code TriCharConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharConsumer composeFromChar(@Nonnull CharToDoubleFunction before1,
            @Nonnull CharToDoubleFunction before2, @Nonnull CharToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoubleConsumer} that first applies the {@code before} operators to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive consumer is executed.
     *
     * @param before1 The first operator to apply before this consumer is applied
     * @param before2 The second operator to apply before this consumer is applied
     * @param before3 The third operator to apply before this consumer is applied
     * @return A composed {@code TriDoubleConsumer} that first applies the {@code before} operators to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleConsumer composeFromDouble(@Nonnull DoubleUnaryOperator before1,
            @Nonnull DoubleUnaryOperator before2, @Nonnull DoubleUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriFloatConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code TriFloatConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatConsumer composeFromFloat(@Nonnull FloatToDoubleFunction before1,
            @Nonnull FloatToDoubleFunction before2, @Nonnull FloatToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriIntConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code TriIntConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntConsumer composeFromInt(@Nonnull IntToDoubleFunction before1,
            @Nonnull IntToDoubleFunction before2, @Nonnull IntToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriLongConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code TriLongConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongConsumer composeFromLong(@Nonnull LongToDoubleFunction before1,
            @Nonnull LongToDoubleFunction before2, @Nonnull LongToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriShortConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code TriShortConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortConsumer composeFromShort(@Nonnull ShortToDoubleFunction before1,
            @Nonnull ShortToDoubleFunction before2, @Nonnull ShortToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoubleConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this consumer throws an exception, the {@code after} consumer will not be
     * performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link TriDoubleConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriDoubleConsumer andThen(@Nonnull TriDoubleConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> {
            accept(value1, value2, value3);
            after.accept(value1, value2, value3);
        };
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link TriDoubleConsumer}. Thereby the primitive
     * input argument for this consumer is autoboxed. This method provides the possibility to use this {@code
     * TriDoubleConsumer} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriConsumer} which represents this {@code TriDoubleConsumer}.
     */
    @Nonnull
    default TriConsumer<Double, Double, Double> boxed() {
        return this::accept;
    }

}
