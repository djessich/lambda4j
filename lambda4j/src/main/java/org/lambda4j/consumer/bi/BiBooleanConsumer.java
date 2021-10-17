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
package org.lambda4j.consumer.bi;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.ShortPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * Represents an operation that accepts two {@code boolean}-valued input arguments and returns no result.
 * This is a primitive specialization of {@link BiConsumer2}.
 * Unlike most other functional interfaces, {@code BiBooleanConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(boolean, boolean)}.
 *
 * @see BiConsumer2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiBooleanConsumer extends Lambda {

    /**
     * Constructs a {@link BiBooleanConsumer} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiBooleanConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static BiBooleanConsumer of(@Nullable final BiBooleanConsumer expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiBooleanConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the consumer
     * @param value2 The second argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     */
    static void call(@Nonnull final BiBooleanConsumer consumer, boolean value1, boolean value2) {
        Objects.requireNonNull(consumer);
        consumer.accept(value1, value2);
    }

    /**
     * Creates a {@link BiBooleanConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link BooleanConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiBooleanConsumer} which uses the {@code first} parameter of this one as argument for
     * the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BiBooleanConsumer onlyFirst(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link BiBooleanConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link BooleanConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiBooleanConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BiBooleanConsumer onlySecond(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value2);
    }

    /**
     * Applies this consumer to the given arguments.
     *
     * @param value1 The first argument to the consumer
     * @param value2 The second argument to the consumer
     */
    void accept(boolean value1, boolean value2);

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link BooleanConsumer} as result.
     *
     * @param value1 The first argument to this consumer used to partially apply this function
     * @return A {@code BooleanConsumer} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default BooleanConsumer paccept(boolean value1) {
        return (value2) -> this.accept(value1, value2);
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
     * Returns a composed {@link BiConsumer2} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given predicate, and of composed consumer
     * @param <B> The type of the argument to the second given predicate, and of composed consumer
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @return A composed {@code BiConsumer2} that first applies the {@code before} predicates to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiConsumer2<A, B> compose(@Nonnull final Predicate<? super A> before1,
            @Nonnull final Predicate<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> accept(before1.test(a), before2.test(b));
    }

    /**
     * Returns a composed {@link BiBooleanConsumer} that first applies the {@code before} operators to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive consumer is executed.
     *
     * @param before1 The first operator to apply before this consumer is applied
     * @param before2 The second operator to apply before this consumer is applied
     * @return A composed {@code BiBooleanConsumer} that first applies the {@code before} operators to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanConsumer composeFromBoolean(@Nonnull final BooleanUnaryOperator before1,
            @Nonnull final BooleanUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsBoolean(value1), before2.applyAsBoolean(value2));
    }

    /**
     * Returns a composed {@link BiByteConsumer} that first applies the {@code before} predicates to
     * its input, and then applies this consumer to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @return A composed {@code BiByteConsumer} that first applies the {@code before} predicates to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteConsumer composeFromByte(@Nonnull final BytePredicate before1, @Nonnull final BytePredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiCharConsumer} that first applies the {@code before} predicates to
     * its input, and then applies this consumer to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @return A composed {@code BiCharConsumer} that first applies the {@code before} predicates to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharConsumer composeFromChar(@Nonnull final CharPredicate before1, @Nonnull final CharPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiDoubleConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @return A composed {@code BiDoubleConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleConsumer composeFromDouble(@Nonnull final DoublePredicate before1,
            @Nonnull final DoublePredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiFloatConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @return A composed {@code BiFloatConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatConsumer composeFromFloat(@Nonnull final FloatPredicate before1,
            @Nonnull final FloatPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiIntConsumer} that first applies the {@code before} predicates to
     * its input, and then applies this consumer to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @return A composed {@code BiIntConsumer} that first applies the {@code before} predicates to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntConsumer composeFromInt(@Nonnull final IntPredicate before1, @Nonnull final IntPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiLongConsumer} that first applies the {@code before} predicates to
     * its input, and then applies this consumer to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @return A composed {@code BiLongConsumer} that first applies the {@code before} predicates to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongConsumer composeFromLong(@Nonnull final LongPredicate before1, @Nonnull final LongPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiShortConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @return A composed {@code BiShortConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortConsumer composeFromShort(@Nonnull final ShortPredicate before1,
            @Nonnull final ShortPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiBooleanConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this consumer throws an exception, the {@code after} consumer will not be
     * performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link BiBooleanConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiBooleanConsumer andThen(@Nonnull final BiBooleanConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> {
            accept(value1, value2);
            after.accept(value1, value2);
        };
    }

    /**
     * Returns a composed {@link BiConsumer2} which represents this {@link BiBooleanConsumer}. Thereby the primitive
     * input argument for this consumer is autoboxed. This method provides the possibility to use this
     * {@code BiBooleanConsumer} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BiConsumer2} which represents this {@code BiBooleanConsumer}.
     */
    @Nonnull
    default BiConsumer2<Boolean, Boolean> boxed() {
        return this::accept;
    }

}