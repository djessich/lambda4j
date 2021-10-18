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

package org.lambda4j.consumer.bi.obj;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.Consumer2;
import org.lambda4j.consumer.ShortConsumer;
import org.lambda4j.consumer.bi.BiBooleanConsumer;
import org.lambda4j.consumer.bi.BiByteConsumer;
import org.lambda4j.consumer.bi.BiCharConsumer;
import org.lambda4j.consumer.bi.BiConsumer2;
import org.lambda4j.consumer.bi.BiDoubleConsumer;
import org.lambda4j.consumer.bi.BiFloatConsumer;
import org.lambda4j.consumer.bi.BiIntConsumer;
import org.lambda4j.consumer.bi.BiLongConsumer;
import org.lambda4j.consumer.bi.BiShortConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
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
 * Represents an operation that accepts one object-valued and one {@code short}-valued input argument and returns no
 * result. This is a (reference, short) specialization of {@link BiConsumer2}. Unlike most other functional interfaces,
 * {@code ObjShortConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, short)}.
 *
 * @param <T> The type of the first argument to the consumer
 * @see BiConsumer2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjShortConsumer<T> extends Lambda {

    /**
     * Constructs a {@link ObjShortConsumer} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the consumer
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjShortConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T> ObjShortConsumer<T> of(@Nullable ObjShortConsumer<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjShortConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the consumer
     * @param consumer The consumer to be called
     * @param t The first argument to the consumer
     * @param value The second argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> void call(@Nonnull ObjShortConsumer<? super T> consumer, T t, short value) {
        Objects.requireNonNull(consumer);
        consumer.accept(t, value);
    }

    /**
     * Creates a {@link ObjShortConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link Consumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjShortConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjShortConsumer<T> onlyFirst(@Nonnull Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(t);
    }

    /**
     * Creates a {@link ObjShortConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link ShortConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjShortConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjShortConsumer<T> onlySecond(@Nonnull ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(value);
    }

    /**
     * Applies this consumer to the given arguments.
     *
     * @param t The first argument to the consumer
     * @param value The second argument to the consumer
     */
    void accept(T t, short value);

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link ShortConsumer} as result.
     *
     * @param t The first argument to this consumer used to partially apply this function
     * @return A {@code ShortConsumer} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default ShortConsumer acceptPartially(T t) {
        return value -> accept(t, value);
    }

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link Consumer2} as result.
     *
     * @param value The second argument to this consumer used to partially apply this function
     * @return A {@code Consumer2} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default Consumer2<T> acceptPartially(short value) {
        return t -> accept(t, value);
    }

    /**
     * Returns the number of arguments for this consumer.
     *
     * @return The number of arguments for this consumer.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiConsumer2} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed consumer
     * @param <B> The type of the argument to the second given function, and of composed consumer
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiConsumer2} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiConsumer2<A, B> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull ToShortFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> accept(before1.apply(a), before2.applyAsShort(b));
    }

    /**
     * Returns a composed {@link BiBooleanConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiBooleanConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanConsumer composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiByteConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiByteConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteConsumer composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiCharConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiCharConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharConsumer composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiDoubleConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiDoubleConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleConsumer composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiFloatConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiFloatConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatConsumer composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiIntConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiIntConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntConsumer composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiLongConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiLongConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongConsumer composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiShortConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second operator to apply before this consumer is applied
     * @return A composed {@code BiShortConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortConsumer composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this consumer throws an exception, the {@code after} consumer will not be
     * performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link ObjShortConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjShortConsumer<T> andThen(@Nonnull ObjShortConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (t, value) -> {
            accept(t, value);
            after.accept(t, value);
        };
    }

    /**
     * Returns a composed {@link BiConsumer2} which represents this {@link ObjShortConsumer}. Thereby the primitive
     * input argument for this consumer is autoboxed. This method provides the possibility to use this {@code
     * ObjShortConsumer} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BiConsumer2} which represents this {@code ObjShortConsumer}.
     */
    @Nonnull
    default BiConsumer2<T, Short> boxed() {
        return this::accept;
    }

}
