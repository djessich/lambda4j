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

package org.lambda4j.consumer.tri.obj;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.Consumer2;
import org.lambda4j.consumer.LongConsumer2;
import org.lambda4j.consumer.bi.BiLongConsumer;
import org.lambda4j.consumer.bi.obj.ObjLongConsumer2;
import org.lambda4j.consumer.tri.TriBooleanConsumer;
import org.lambda4j.consumer.tri.TriByteConsumer;
import org.lambda4j.consumer.tri.TriCharConsumer;
import org.lambda4j.consumer.tri.TriConsumer;
import org.lambda4j.consumer.tri.TriDoubleConsumer;
import org.lambda4j.consumer.tri.TriFloatConsumer;
import org.lambda4j.consumer.tri.TriIntConsumer;
import org.lambda4j.consumer.tri.TriLongConsumer;
import org.lambda4j.consumer.tri.TriShortConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.ByteToLongFunction;
import org.lambda4j.function.conversion.CharToLongFunction;
import org.lambda4j.function.conversion.FloatToLongFunction;
import org.lambda4j.function.conversion.ShortToLongFunction;

/**
 * Represents an operation that accepts one object-valued and two {@code long}-valued input arguments and returns no
 * result. This is a (reference, long, long) specialization of {@link TriConsumer}. Unlike most other functional
 * interfaces, {@code ObjBiLongConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, long, long)}.
 *
 * @param <T> The type of the first argument to the consumer
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjBiLongConsumer<T> extends Lambda {

    /**
     * Constructs a {@link ObjBiLongConsumer} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the consumer
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjBiLongConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T> ObjBiLongConsumer<T> of(@Nullable ObjBiLongConsumer<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjBiLongConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the consumer
     * @param consumer The consumer to be called
     * @param t The first argument to the consumer
     * @param value1 The second argument to the consumer
     * @param value2 The third argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> void call(@Nonnull ObjBiLongConsumer<? super T> consumer, T t, long value1, long value2) {
        Objects.requireNonNull(consumer);
        consumer.accept(t, value1, value2);
    }

    /**
     * Creates a {@link ObjBiLongConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link Consumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjBiLongConsumer} which uses the {@code first} parameter of this one as argument for
     * the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiLongConsumer<T> onlyFirst(@Nonnull Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.accept(t);
    }

    /**
     * Creates a {@link ObjBiLongConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link LongConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjBiLongConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiLongConsumer<T> onlySecond(@Nonnull LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link ObjBiLongConsumer} which uses the {@code third} parameter of this one as argument for the given
     * {@link LongConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code ObjBiLongConsumer} which uses the {@code third} parameter of this one as argument for
     * the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiLongConsumer<T> onlyThird(@Nonnull LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.accept(value2);
    }

    /**
     * Applies this consumer to the given arguments.
     *
     * @param t The first argument to the consumer
     * @param value1 The second argument to the consumer
     * @param value2 The third argument to the consumer
     */
    void accept(T t, long value1, long value2);

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link BiLongConsumer} as result.
     *
     * @param t The first argument to this consumer used to partially apply this function
     * @return A {@code BiLongConsumer} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default BiLongConsumer acceptPartially(T t) {
        return (value1, value2) -> accept(t, value1, value2);
    }

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link LongConsumer2} as result.
     *
     * @param t The first argument to this consumer used to partially apply this function
     * @param value1 The second argument to this consumer used to partially apply this function
     * @return A {@code LongConsumer2} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default LongConsumer2 acceptPartially(T t, long value1) {
        return value2 -> accept(t, value1, value2);
    }

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link ObjLongConsumer2} as result.
     *
     * @param value1 The second argument to this consumer used to partially apply this function
     * @return A {@code ObjLongConsumer2} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default ObjLongConsumer2<T> acceptPartially(long value1) {
        return (t, value2) -> accept(t, value1, value2);
    }

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link Consumer2} as result.
     *
     * @param value1 The second argument to this consumer used to partially apply this function
     * @param value2 The third argument to this consumer used to partially apply this function
     * @return A {@code Consumer2} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default Consumer2<T> acceptPartially(long value1, long value2) {
        return t -> accept(t, value1, value2);
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
    default <A, B, C> TriConsumer<A, B, C> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull ToLongFunction<? super B> before2, @Nonnull ToLongFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> accept(before1.apply(a), before2.applyAsLong(b), before3.applyAsLong(c));
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
    default TriBooleanConsumer composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanToLongFunction before2, @Nonnull BooleanToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.apply(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
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
    default TriByteConsumer composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteToLongFunction before2, @Nonnull ByteToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.apply(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
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
    default TriCharConsumer composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharToLongFunction before2, @Nonnull CharToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.apply(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriDoubleConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code TriDoubleConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleConsumer composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleToLongFunction before2, @Nonnull DoubleToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.apply(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
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
    default TriFloatConsumer composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatToLongFunction before2, @Nonnull FloatToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.apply(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
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
    default TriIntConsumer composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntToLongFunction before2, @Nonnull IntToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.apply(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriLongConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second operator to apply before this consumer is applied
     * @param before3 The third operator to apply before this consumer is applied
     * @return A composed {@code TriLongConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongConsumer composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongUnaryOperator before2, @Nonnull LongUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.apply(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
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
    default TriShortConsumer composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortToLongFunction before2, @Nonnull ShortToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.apply(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link ObjBiLongConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this consumer throws an exception, the {@code after} consumer will not be
     * performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link ObjBiLongConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjBiLongConsumer<T> andThen(@Nonnull ObjBiLongConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> {
            accept(t, value1, value2);
            after.accept(t, value1, value2);
        };
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link ObjBiLongConsumer}. Thereby the primitive
     * input argument for this consumer is autoboxed. This method provides the possibility to use this {@code
     * ObjBiLongConsumer} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriConsumer} which represents this {@code ObjBiLongConsumer}.
     */
    @Nonnull
    default TriConsumer<T, Long, Long> boxed() {
        return this::accept;
    }
}
