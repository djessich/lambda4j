/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.consumer.tri;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.BooleanConsumer;
import at.gridtec.lambda4j.operator.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.predicate.BytePredicate;
import at.gridtec.lambda4j.predicate.CharPredicate;
import at.gridtec.lambda4j.predicate.FloatPredicate;
import at.gridtec.lambda4j.predicate.ShortPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * Represents an operation that accepts three {@code boolean}-valued input arguments and returns no result. This is a
 * primitive specialization of {@link TriConsumer}. Unlike most other functional interfaces, {@code TriBooleanConsumer}
 * is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(boolean, boolean, boolean)}.
 *
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriBooleanConsumer extends Lambda {

    /**
     * Constructs a {@link TriBooleanConsumer} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriBooleanConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static TriBooleanConsumer of(@Nonnull final TriBooleanConsumer expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriBooleanConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the consumer
     * @param value2 The second argument to the consumer
     * @param value3 The third argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     */
    static void call(@Nonnull final TriBooleanConsumer consumer, boolean value1, boolean value2, boolean value3) {
        Objects.requireNonNull(consumer);
        consumer.accept(value1, value2, value3);
    }

    /**
     * Creates a {@link TriBooleanConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link BooleanConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriBooleanConsumer} which uses the {@code first} parameter of this one as argument for
     * the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriBooleanConsumer onlyFirst(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link TriBooleanConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@link BooleanConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriBooleanConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriBooleanConsumer onlySecond(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value2);
    }

    /**
     * Creates a {@link TriBooleanConsumer} which uses the {@code third} parameter of this one as argument for the given
     * {@link BooleanConsumer}.
     *
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriBooleanConsumer} which uses the {@code third} parameter of this one as argument for
     * the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriBooleanConsumer onlyThird(@Nonnull final BooleanConsumer consumer) {
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
    void accept(boolean value1, boolean value2, boolean value3);

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
     * Returns a composed {@link TriConsumer} that first applies the {@code before} predicates to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given predicate, and of composed consumer
     * @param <B> The type of the argument to the second given predicate, and of composed consumer
     * @param <C> The type of the argument to the third given predicate, and of composed consumer
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code TriConsumer} that first applies the {@code before} predicates to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> TriConsumer<A, B, C> compose(@Nonnull final Predicate<? super A> before1,
            @Nonnull final Predicate<? super B> before2, @Nonnull final Predicate<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> accept(before1.test(a), before2.test(b), before3.test(c));
    }

    /**
     * Returns a composed {@link TriBooleanConsumer} that first applies the {@code before} operators to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive consumer is executed.
     *
     * @param before1 The first operator to apply before this consumer is applied
     * @param before2 The second operator to apply before this consumer is applied
     * @param before3 The third operator to apply before this consumer is applied
     * @return A composed {@code TriBooleanConsumer} that first applies the {@code before} operators to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanConsumer composeFromBoolean(@Nonnull final BooleanUnaryOperator before1,
            @Nonnull final BooleanUnaryOperator before2, @Nonnull final BooleanUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsBoolean(value1), before2.applyAsBoolean(value2),
                                                  before3.applyAsBoolean(value3));
    }

    /**
     * Returns a composed {@link TriByteConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code TriByteConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteConsumer composeFromByte(@Nonnull final BytePredicate before1, @Nonnull final BytePredicate before2,
            @Nonnull final BytePredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriCharConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code TriCharConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharConsumer composeFromChar(@Nonnull final CharPredicate before1, @Nonnull final CharPredicate before2,
            @Nonnull final CharPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriDoubleConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code TriDoubleConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleConsumer composeFromDouble(@Nonnull final DoublePredicate before1,
            @Nonnull final DoublePredicate before2, @Nonnull final DoublePredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriFloatConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code TriFloatConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatConsumer composeFromFloat(@Nonnull final FloatPredicate before1,
            @Nonnull final FloatPredicate before2, @Nonnull final FloatPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriIntConsumer} that first applies the {@code before} predicates to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code TriIntConsumer} that first applies the {@code before} predicates to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntConsumer composeFromInt(@Nonnull final IntPredicate before1, @Nonnull final IntPredicate before2,
            @Nonnull final IntPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriLongConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code TriLongConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongConsumer composeFromLong(@Nonnull final LongPredicate before1, @Nonnull final LongPredicate before2,
            @Nonnull final LongPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriShortConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive consumer is executed.
     *
     * @param before1 The first predicate to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code TriShortConsumer} that first applies the {@code before} predicates to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortConsumer composeFromShort(@Nonnull final ShortPredicate before1,
            @Nonnull final ShortPredicate before2, @Nonnull final ShortPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriBooleanConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this consumer throws an exception, the {@code after} consumer will not be
     * performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link TriBooleanConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriBooleanConsumer andThen(@Nonnull final TriBooleanConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> {
            accept(value1, value2, value3);
            after.accept(value1, value2, value3);
        };
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link TriBooleanConsumer}. Thereby the primitive
     * input argument for this consumer is autoboxed. This method is just convenience to provide the ability to use this
     * {@code TriBooleanConsumer} with JDK specific methods, only accepting {@code TriConsumer}.
     *
     * @return A composed {@code TriConsumer} which represents this {@code TriBooleanConsumer}.
     */
    @Nonnull
    default TriConsumer<Boolean, Boolean, Boolean> boxed() {
        return this::accept;
    }

}
