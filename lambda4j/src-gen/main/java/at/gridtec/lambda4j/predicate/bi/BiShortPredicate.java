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
package at.gridtec.lambda4j.predicate.bi;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.BooleanConsumer;
import at.gridtec.lambda4j.consumer.bi.BiShortConsumer;
import at.gridtec.lambda4j.function.BooleanFunction;
import at.gridtec.lambda4j.function.bi.BiShortFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiShortToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiShortToCharFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiShortToDoubleFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiShortToFloatFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiShortToIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiShortToLongFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.function.conversion.CharToShortFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToShortFunction;
import at.gridtec.lambda4j.function.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.function.conversion.IntToShortFunction;
import at.gridtec.lambda4j.function.conversion.LongToShortFunction;
import at.gridtec.lambda4j.function.to.ToShortFunction;
import at.gridtec.lambda4j.operator.binary.BooleanBinaryOperator;
import at.gridtec.lambda4j.operator.binary.ShortBinaryOperator;
import at.gridtec.lambda4j.operator.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.operator.unary.ShortUnaryOperator;
import at.gridtec.lambda4j.predicate.ShortPredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiPredicate;

/**
 * Represents an predicate (boolean-valued function) of two {@code short}-valued input arguments. This is a primitive
 * specialization of {@link BiPredicate2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(short, short)}.
 *
 * @see BiPredicate2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiShortPredicate extends Lambda {

    /**
     * Constructs a {@link BiShortPredicate} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiShortPredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static BiShortPredicate of(@Nullable final BiShortPredicate expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiShortPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code BiShortPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static boolean call(@Nonnull final BiShortPredicate predicate, short value1, short value2) {
        Objects.requireNonNull(predicate);
        return predicate.test(value1, value2);
    }

    /**
     * Creates a {@link BiShortPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link ShortPredicate}.
     *
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiShortPredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@code ShortPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BiShortPredicate onlyFirst(@Nonnull final ShortPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2) -> predicate.test(value1);
    }

    /**
     * Creates a {@link BiShortPredicate} which uses the {@code second} parameter of this one as argument for the given
     * {@link ShortPredicate}.
     *
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiShortPredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@code ShortPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BiShortPredicate onlySecond(@Nonnull final ShortPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2) -> predicate.test(value2);
    }

    /**
     * Creates a {@link BiShortPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiShortPredicate} which always returns a given value.
     */
    @Nonnull
    static BiShortPredicate constant(boolean ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link BiShortPredicate} that always returns {@code true}.
     *
     * @return A {@link BiShortPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static BiShortPredicate alwaysTrue() {
        return (value1, value2) -> true;
    }

    /**
     * Returns a {@link BiShortPredicate} that always returns {@code false}.
     *
     * @return A {@link BiShortPredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static BiShortPredicate alwaysFalse() {
        return (value1, value2) -> false;
    }

    /**
     * Returns a {@link BiShortPredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     *
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @return A {@code BiShortPredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static BiShortPredicate isEqual(short target1, short target2) {
        return (value1, value2) -> (value1 == target1) && (value2 == target2);
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(short value1, short value2);

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiPredicate2} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed predicate
     * @param <B> The type of the argument to the second given function, and of composed predicate
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code BiPredicate2} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiPredicate2<A, B> compose(@Nonnull final ToShortFunction<? super A> before1,
            @Nonnull final ToShortFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> test(before1.applyAsShort(a), before2.applyAsShort(b));
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code BooleanBinaryOperator} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanBinaryOperator composeFromBoolean(@Nonnull final BooleanToShortFunction before1,
            @Nonnull final BooleanToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiBytePredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code BiBytePredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiBytePredicate composeFromByte(@Nonnull final ByteToShortFunction before1,
            @Nonnull final ByteToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiCharPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code BiCharPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharPredicate composeFromChar(@Nonnull final CharToShortFunction before1,
            @Nonnull final CharToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiDoublePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code BiDoublePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoublePredicate composeFromDouble(@Nonnull final DoubleToShortFunction before1,
            @Nonnull final DoubleToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiFloatPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code BiFloatPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatPredicate composeFromFloat(@Nonnull final FloatToShortFunction before1,
            @Nonnull final FloatToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiIntPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code BiIntPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntPredicate composeFromInt(@Nonnull final IntToShortFunction before1,
            @Nonnull final IntToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiLongPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code BiLongPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongPredicate composeFromLong(@Nonnull final LongToShortFunction before1,
            @Nonnull final LongToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiShortPredicate} that first applies the {@code before} operators to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive predicate is executed.
     *
     * @param before1 The first operator to apply before this predicate is applied
     * @param before2 The second operator to apply before this predicate is applied
     * @return A composed {@code BiShortPredicate} that first applies the {@code before} operators to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortPredicate composeFromShort(@Nonnull final ShortUnaryOperator before1,
            @Nonnull final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiShortFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiShortFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiShortFunction<S> andThen(@Nonnull final BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(test(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortPredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code BiShortPredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiShortPredicate andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsBoolean(test(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToByteFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiShortToByteFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiShortToByteFunction andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(test(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToCharFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiShortToCharFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiShortToCharFunction andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(test(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiShortToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiShortToDoubleFunction andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(test(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiShortToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiShortToFloatFunction andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(test(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToIntFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiShortToIntFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiShortToIntFunction andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(test(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToLongFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiShortToLongFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiShortToLongFunction andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(test(value1, value2));
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ShortBinaryOperator} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortBinaryOperator andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(test(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortConsumer} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiShortConsumer} that first applies this predicate to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiShortConsumer consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(test(value1, value2));
    }

    /**
     * Returns a {@link BiShortPredicate} that represents the logical negation of this one.
     *
     * @return A {@code BiShortPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default BiShortPredicate negate() {
        return (value1, value2) -> !test(value1, value2);
    }

    /**
     * Returns a composed {@link BiShortPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code BiShortPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiShortPredicate} that will be logically-ANDed with this one
     * @return A composed {@code BiShortPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(BiShortPredicate)
     * @see #xor(BiShortPredicate)
     */
    @Nonnull
    default BiShortPredicate and(@Nonnull final BiShortPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) && other.test(value1, value2);
    }

    /**
     * Returns a composed {@link BiShortPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code BiShortPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiShortPredicate} that will be logically-ORed with this one
     * @return A composed {@code BiShortPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(BiShortPredicate)
     * @see #xor(BiShortPredicate)
     */
    @Nonnull
    default BiShortPredicate or(@Nonnull final BiShortPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) || other.test(value1, value2);
    }

    /**
     * Returns a composed {@link BiShortPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of
     * this {@code BiShortPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiShortPredicate} that will be logically-XORed with this one
     * @return A composed {@code BiShortPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(BiShortPredicate)
     * @see #or(BiShortPredicate)
     */
    @Nonnull
    default BiShortPredicate xor(@Nonnull final BiShortPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) ^ other.test(value1, value2);
    }

    /**
     * Returns a memoized (caching) version of this {@link BiShortPredicate}. Whenever it is called, the mapping between
     * the input parameters and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiShortPredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiShortPredicate memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<Short, Short>, Boolean> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (BiShortPredicate & Memoized) (value1, value2) -> {
                final boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2),
                                                        key -> test(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link BiPredicate} which represents this {@link BiShortPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method is just convenience to provide the ability to use
     * this {@code BiShortPredicate} with JDK specific methods, only accepting {@code BiPredicate}.
     *
     * @return A composed {@code BiPredicate} which represents this {@code BiShortPredicate}.
     */
    @Nonnull
    default BiPredicate<Short, Short> boxed() {
        return this::test;
    }

}