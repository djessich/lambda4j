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

package org.lambda4j.function.bi.conversion;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoublePredicate;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.bi.BiBooleanConsumer;
import org.lambda4j.function.bi.BiBooleanFunction;
import org.lambda4j.function.bi.BiFunction2;
import org.lambda4j.function.bi.to.ToIntBiFunction2;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.IntToByteFunction;
import org.lambda4j.function.conversion.IntToCharFunction;
import org.lambda4j.function.conversion.IntToFloatFunction;
import org.lambda4j.function.conversion.IntToShortFunction;
import org.lambda4j.operator.binary.BooleanBinaryOperator;
import org.lambda4j.operator.binary.IntBinaryOperator2;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.ShortPredicate;

/**
 * Represents an operation that accepts two {@code boolean}-valued input arguments and produces a {@code int}-valued
 * result. This is a primitive specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(boolean, boolean)}.
 *
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiBooleanToIntFunction extends Lambda {

    /**
     * Constructs a {@link BiBooleanToIntFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiBooleanToIntFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static BiBooleanToIntFunction of(@Nullable BiBooleanToIntFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiBooleanToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiBooleanToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static int call(@Nonnull BiBooleanToIntFunction function, boolean value1, boolean value2) {
        Objects.requireNonNull(function);
        return function.applyAsInt(value1, value2);
    }

    /**
     * Creates a {@link BiBooleanToIntFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link BooleanToIntFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiBooleanToIntFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code BooleanToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BiBooleanToIntFunction onlyFirst(@Nonnull BooleanToIntFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsInt(value1);
    }

    /**
     * Creates a {@link BiBooleanToIntFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link BooleanToIntFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiBooleanToIntFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code BooleanToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BiBooleanToIntFunction onlySecond(@Nonnull BooleanToIntFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsInt(value2);
    }

    /**
     * Creates a {@link BiBooleanToIntFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiBooleanToIntFunction} which always returns a given value.
     */
    @Nonnull
    static BiBooleanToIntFunction constant(int ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    int applyAsInt(boolean value1, boolean value2);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BooleanToIntFunction} as
     * result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @return A {@code BooleanToIntFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BooleanToIntFunction applyAsIntPartially(boolean value1) {
        return value2 -> applyAsInt(value1, value2);
    }

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToIntBiFunction2} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given predicate, and of composed function
     * @param <B> The type of the argument to the second given predicate, and of composed function
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code ToIntBiFunction2} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToIntBiFunction2<A, B> compose(@Nonnull Predicate<? super A> before1,
            @Nonnull Predicate<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsInt(before1.test(a), before2.test(b));
    }

    /**
     * Returns a composed {@link BiBooleanToIntFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiBooleanToIntFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToIntFunction composeFromBoolean(@Nonnull BooleanUnaryOperator before1,
            @Nonnull BooleanUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.applyAsBoolean(value1), before2.applyAsBoolean(value2));
    }

    /**
     * Returns a composed {@link BiByteToIntFunction} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiByteToIntFunction} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteToIntFunction composeFromByte(@Nonnull BytePredicate before1,
            @Nonnull BytePredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiCharToIntFunction} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiCharToIntFunction} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToIntFunction composeFromChar(@Nonnull CharPredicate before1,
            @Nonnull CharPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiDoubleToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiDoubleToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleToIntFunction composeFromDouble(@Nonnull DoublePredicate before1,
            @Nonnull DoublePredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiFloatToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiFloatToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatToIntFunction composeFromFloat(@Nonnull FloatPredicate before1,
            @Nonnull FloatPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link IntBinaryOperator2} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code IntBinaryOperator2} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntBinaryOperator2 composeFromInt(@Nonnull IntPredicate before1,
            @Nonnull IntPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiLongToIntFunction} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiLongToIntFunction} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongToIntFunction composeFromLong(@Nonnull LongPredicate before1,
            @Nonnull LongPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiShortToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiShortToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToIntFunction composeFromShort(@Nonnull ShortPredicate before1,
            @Nonnull ShortPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiBooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiBooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiBooleanFunction<S> andThen(@Nonnull IntFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code BooleanBinaryOperator} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanBinaryOperator andThenToBoolean(@Nonnull IntPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.test(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiBooleanToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiBooleanToByteFunction andThenToByte(@Nonnull IntToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiBooleanToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiBooleanToCharFunction andThenToChar(@Nonnull IntToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiBooleanToDoubleFunction andThenToDouble(@Nonnull IntToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiBooleanToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiBooleanToFloatFunction andThenToFloat(@Nonnull IntToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code BiBooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiBooleanToIntFunction andThenToInt(@Nonnull IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiBooleanToLongFunction andThenToLong(@Nonnull IntToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiBooleanToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiBooleanToShortFunction andThenToShort(@Nonnull IntToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiBooleanConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiBooleanConsumer consume(@Nonnull IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsInt(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link BiBooleanToIntFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiBooleanToIntFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiBooleanToIntFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<Boolean, Boolean>, Integer> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (BiBooleanToIntFunction & Memoized) (value1, value2) -> {
                int returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2),
                            key -> applyAsInt(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link BiFunction2} which represents this {@link BiBooleanToIntFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * BiBooleanToIntFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BiFunction2} which represents this {@code BiBooleanToIntFunction}.
     */
    @Nonnull
    default BiFunction2<Boolean, Boolean, Integer> boxed() {
        return this::applyAsInt;
    }

}
