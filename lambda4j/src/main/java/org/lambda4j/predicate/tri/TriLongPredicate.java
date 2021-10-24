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

package org.lambda4j.predicate.tri;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.consumer.tri.TriLongConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.conversion.ByteToLongFunction;
import org.lambda4j.function.conversion.CharToLongFunction;
import org.lambda4j.function.conversion.FloatToLongFunction;
import org.lambda4j.function.conversion.ShortToLongFunction;
import org.lambda4j.function.tri.TriLongFunction;
import org.lambda4j.function.tri.conversion.TriLongToByteFunction;
import org.lambda4j.function.tri.conversion.TriLongToCharFunction;
import org.lambda4j.function.tri.conversion.TriLongToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriLongToFloatFunction;
import org.lambda4j.function.tri.conversion.TriLongToIntFunction;
import org.lambda4j.function.tri.conversion.TriLongToShortFunction;
import org.lambda4j.operator.ternary.BooleanTernaryOperator;
import org.lambda4j.operator.ternary.LongTernaryOperator;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.LongPredicate2;
import org.lambda4j.predicate.bi.BiLongPredicate;

/**
 * Represents an predicate (boolean-valued function) of three {@code long}-valued input arguments. This is a primitive
 * specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(long, long, long)}.
 *
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriLongPredicate extends Lambda {

    /**
     * Constructs a {@link TriLongPredicate} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriLongPredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static TriLongPredicate of(@Nullable TriLongPredicate expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriLongPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code TriLongPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static boolean call(@Nonnull TriLongPredicate predicate, long value1, long value2, long value3) {
        Objects.requireNonNull(predicate);
        return predicate.test(value1, value2, value3);
    }

    /**
     * Creates a {@link TriLongPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link LongPredicate}.
     *
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriLongPredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@code LongPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriLongPredicate onlyFirst(@Nonnull LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value1);
    }

    /**
     * Creates a {@link TriLongPredicate} which uses the {@code second} parameter of this one as argument for the given
     * {@link LongPredicate}.
     *
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriLongPredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@code LongPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriLongPredicate onlySecond(@Nonnull LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value2);
    }

    /**
     * Creates a {@link TriLongPredicate} which uses the {@code third} parameter of this one as argument for the given
     * {@link LongPredicate}.
     *
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriLongPredicate} which uses the {@code third} parameter of this one as argument for the
     * given {@code LongPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriLongPredicate onlyThird(@Nonnull LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value3);
    }

    /**
     * Creates a {@link TriLongPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriLongPredicate} which always returns a given value.
     */
    @Nonnull
    static TriLongPredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link TriLongPredicate} that always returns {@code true}.
     *
     * @return A {@link TriLongPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static TriLongPredicate alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link TriLongPredicate} that always returns {@code false}.
     *
     * @return A {@link TriLongPredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static TriLongPredicate alwaysFalse() {
        return (value1, value2, value3) -> false;
    }

    /**
     * Returns a {@link TriLongPredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     *
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @param target3 The third reference with which to compare for equality, which may be {@code null}
     * @return A {@code TriLongPredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static TriLongPredicate isEqual(long target1, long target2, long target3) {
        return (value1, value2, value3) -> value1 == target1 && value2 == target2 && value3 == target3;
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(long value1, long value2, long value3);

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link BiLongPredicate} as result.
     *
     * @param value1 The first argument to this predicate used to partially apply this function
     * @return A {@code BiLongPredicate} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default BiLongPredicate testPartially(long value1) {
        return (value2, value3) -> test(value1, value2, value3);
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link LongPredicate2} as result.
     *
     * @param value1 The first argument to this predicate used to partially apply this function
     * @param value2 The second argument to this predicate used to partially apply this function
     * @return A {@code LongPredicate2} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default LongPredicate2 testPartially(long value1, long value2) {
        return value3 -> test(value1, value2, value3);
    }

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed predicate
     * @param <B> The type of the argument to the second given function, and of composed predicate
     * @param <C> The type of the argument to the third given function, and of composed predicate
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> TriPredicate<A, B, C> compose(@Nonnull ToLongFunction<? super A> before1,
            @Nonnull ToLongFunction<? super B> before2, @Nonnull ToLongFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> test(before1.applyAsLong(a), before2.applyAsLong(b), before3.applyAsLong(c));
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code BooleanTernaryOperator} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanTernaryOperator composeFromBoolean(@Nonnull BooleanToLongFunction before1,
            @Nonnull BooleanToLongFunction before2, @Nonnull BooleanToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriBytePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriBytePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriBytePredicate composeFromByte(@Nonnull ByteToLongFunction before1,
            @Nonnull ByteToLongFunction before2, @Nonnull ByteToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriCharPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriCharPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharPredicate composeFromChar(@Nonnull CharToLongFunction before1,
            @Nonnull CharToLongFunction before2, @Nonnull CharToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriDoublePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoublePredicate composeFromDouble(@Nonnull DoubleToLongFunction before1,
            @Nonnull DoubleToLongFunction before2, @Nonnull DoubleToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriFloatPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriFloatPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatPredicate composeFromFloat(@Nonnull FloatToLongFunction before1,
            @Nonnull FloatToLongFunction before2, @Nonnull FloatToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriIntPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriIntPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntPredicate composeFromInt(@Nonnull IntToLongFunction before1,
            @Nonnull IntToLongFunction before2, @Nonnull IntToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriLongPredicate} that first applies the {@code before} operators to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive predicate is executed.
     *
     * @param before1 The first operator to apply before this predicate is applied
     * @param before2 The second operator to apply before this predicate is applied
     * @param before3 The third operator to apply before this predicate is applied
     * @return A composed {@code TriLongPredicate} that first applies the {@code before} operators to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongPredicate composeFromLong(@Nonnull LongUnaryOperator before1,
            @Nonnull LongUnaryOperator before2, @Nonnull LongUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriShortPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriShortPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortPredicate composeFromShort(@Nonnull ShortToLongFunction before1,
            @Nonnull ShortToLongFunction before2, @Nonnull ShortToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriLongFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriLongFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriLongFunction<S> andThen(@Nonnull BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongPredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code TriLongPredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriLongPredicate andThenToBoolean(@Nonnull BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsBoolean(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToByteFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriLongToByteFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriLongToByteFunction andThenToByte(@Nonnull BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToCharFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriLongToCharFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriLongToCharFunction andThenToChar(@Nonnull BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriLongToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriLongToDoubleFunction andThenToDouble(@Nonnull BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriLongToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriLongToFloatFunction andThenToFloat(@Nonnull BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToIntFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriLongToIntFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriLongToIntFunction andThenToInt(@Nonnull BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code LongTernaryOperator} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongTernaryOperator andThenToLong(@Nonnull BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriLongToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriLongToShortFunction andThenToShort(@Nonnull BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongConsumer} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriLongConsumer} that first applies this predicate to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriLongConsumer consume(@Nonnull BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(test(value1, value2, value3));
    }

    /**
     * Returns a {@link TriLongPredicate} that represents the logical negation of this one.
     *
     * @return A {@code TriLongPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default TriLongPredicate negate() {
        return (value1, value2, value3) -> !test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriLongPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code TriLongPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code TriLongPredicate} that will be logically-ANDed with this one
     * @return A composed {@code TriLongPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(TriLongPredicate)
     * @see #xor(TriLongPredicate)
     */
    @Nonnull
    default TriLongPredicate and(@Nonnull TriLongPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriLongPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code TriLongPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code TriLongPredicate} that will be logically-ORed with this one
     * @return A composed {@code TriLongPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(TriLongPredicate)
     * @see #xor(TriLongPredicate)
     */
    @Nonnull
    default TriLongPredicate or(@Nonnull TriLongPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) || other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriLongPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of
     * this {@code TriLongPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code TriLongPredicate} that will be logically-XORed with this one
     * @return A composed {@code TriLongPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(TriLongPredicate)
     * @see #or(TriLongPredicate)
     */
    @Nonnull
    default TriLongPredicate xor(@Nonnull TriLongPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) ^ other.test(value1, value2, value3);
    }

    /**
     * Returns a memoized (caching) version of this {@link TriLongPredicate}. Whenever it is called, the mapping between
     * the input parameters and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriLongPredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriLongPredicate memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Long, Long, Long>, Boolean> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (TriLongPredicate & Memoized) (value1, value2, value3) -> {
                boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3),
                            key -> test(key.getLeft(), key.getMiddle(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link TriLongPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method provides the possibility to use this {@code
     * TriLongPredicate} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriPredicate} which represents this {@code TriLongPredicate}.
     */
    @Nonnull
    default TriPredicate<Long, Long, Long> boxed() {
        return this::test;
    }

}
