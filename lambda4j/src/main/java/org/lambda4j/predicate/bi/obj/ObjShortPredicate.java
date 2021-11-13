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

package org.lambda4j.predicate.bi.obj;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.consumer.bi.obj.ObjShortConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.obj.ObjShortFunction;
import org.lambda4j.function.bi.obj.ObjShortToByteFunction;
import org.lambda4j.function.bi.obj.ObjShortToCharFunction;
import org.lambda4j.function.bi.obj.ObjShortToDoubleFunction;
import org.lambda4j.function.bi.obj.ObjShortToFloatFunction;
import org.lambda4j.function.bi.obj.ObjShortToIntFunction;
import org.lambda4j.function.bi.obj.ObjShortToLongFunction;
import org.lambda4j.function.bi.obj.ObjShortToShortFunction;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.conversion.ByteToShortFunction;
import org.lambda4j.function.conversion.CharToShortFunction;
import org.lambda4j.function.conversion.DoubleToShortFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.conversion.IntToShortFunction;
import org.lambda4j.function.conversion.LongToShortFunction;
import org.lambda4j.function.to.ToShortFunction;
import org.lambda4j.operator.binary.BooleanBinaryOperator;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.operator.unary.ShortUnaryOperator;
import org.lambda4j.predicate.Predicate2;
import org.lambda4j.predicate.ShortPredicate;
import org.lambda4j.predicate.bi.BiBytePredicate;
import org.lambda4j.predicate.bi.BiCharPredicate;
import org.lambda4j.predicate.bi.BiDoublePredicate;
import org.lambda4j.predicate.bi.BiFloatPredicate;
import org.lambda4j.predicate.bi.BiIntPredicate;
import org.lambda4j.predicate.bi.BiLongPredicate;
import org.lambda4j.predicate.bi.BiPredicate2;
import org.lambda4j.predicate.bi.BiShortPredicate;

/**
 * Represents an predicate (boolean-valued function) of one object-valued and one {@code short}-valued input argument.
 * This is a (reference, short) specialization of {@link BiPredicate2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, short)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @see BiPredicate2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjShortPredicate<T> extends Lambda {

    /**
     * Constructs a {@link ObjShortPredicate} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjShortPredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T> ObjShortPredicate<T> of(@Nullable ObjShortPredicate<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjShortPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The result from the given {@code ObjShortPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> boolean call(@Nonnull ObjShortPredicate<? super T> predicate, T t, short value) {
        Objects.requireNonNull(predicate);
        return predicate.test(t, value);
    }

    /**
     * Creates a {@link ObjShortPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjShortPredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@code Predicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjShortPredicate<T> onlyFirst(@Nonnull Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (t, value) -> predicate.test(t);
    }

    /**
     * Creates a {@link ObjShortPredicate} which uses the {@code second} parameter of this one as argument for the given
     * {@link ShortPredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjShortPredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@code ShortPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjShortPredicate<T> onlySecond(@Nonnull ShortPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (t, value) -> predicate.test(value);
    }

    /**
     * Creates a {@link ObjShortPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ObjShortPredicate} which always returns a given value.
     */
    @Nonnull
    static <T> ObjShortPredicate<T> constant(boolean ret) {
        return (t, value) -> ret;
    }

    /**
     * Returns a {@link ObjShortPredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @return A {@link ObjShortPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T> ObjShortPredicate<T> alwaysTrue() {
        return (t, value) -> true;
    }

    /**
     * Returns a {@link ObjShortPredicate} that always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @return A {@link ObjShortPredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T> ObjShortPredicate<T> alwaysFalse() {
        return (t, value) -> false;
    }

    /**
     * Returns a {@link ObjShortPredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     *
     * @param <T> The type of the first argument to the predicate
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @return A {@code ObjShortPredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <T> ObjShortPredicate<T> isEqual(@Nullable Object target1, short target2) {
        return (t, value) -> t == null ? target1 == null : t.equals(target1) && value == target2;
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(T t, short value);

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link ShortPredicate} as result.
     *
     * @param t The first argument to this predicate used to partially apply this function
     * @return A {@code ShortPredicate} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default ShortPredicate testPartially(T t) {
        return value -> test(t, value);
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link Predicate2} as result.
     *
     * @param value The second argument to this predicate used to partially apply this function
     * @return A {@code Predicate2} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default Predicate2<T> testPartially(short value) {
        return t -> test(t, value);
    }

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
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiPredicate2<A, B> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull ToShortFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> test(before1.apply(a), before2.applyAsShort(b));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanBinaryOperator composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.apply(value1), before2.applyAsShort(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiBytePredicate composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.apply(value1), before2.applyAsShort(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharPredicate composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.apply(value1), before2.applyAsShort(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoublePredicate composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.apply(value1), before2.applyAsShort(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatPredicate composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.apply(value1), before2.applyAsShort(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntPredicate composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.apply(value1), before2.applyAsShort(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongPredicate composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiShortPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second operator to apply before this predicate is applied
     * @return A composed {@code BiShortPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortPredicate composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> test(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link ObjShortFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjShortFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjShortFunction<T, S> andThen(@Nonnull BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(test(t, value));
    }

    /**
     * Returns a composed {@link ObjShortPredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code ObjShortPredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjShortPredicate<T> andThenToBoolean(@Nonnull BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsBoolean(test(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjShortToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjShortToByteFunction<T> andThenToByte(@Nonnull BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsByte(test(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjShortToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjShortToCharFunction<T> andThenToChar(@Nonnull BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsChar(test(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjShortToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjShortToDoubleFunction<T> andThenToDouble(@Nonnull BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsDouble(test(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjShortToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjShortToFloatFunction<T> andThenToFloat(@Nonnull BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsFloat(test(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToIntFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjShortToIntFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjShortToIntFunction<T> andThenToInt(@Nonnull BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsInt(test(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjShortToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjShortToLongFunction<T> andThenToLong(@Nonnull BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsLong(test(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjShortToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjShortToShortFunction<T> andThenToShort(@Nonnull BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsShort(test(t, value));
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjShortConsumer} that first applies this predicate to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjShortConsumer<T> consume(@Nonnull BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(test(t, value));
    }

    /**
     * Returns a {@link ObjShortPredicate} that represents the logical negation of this one.
     *
     * @return A {@code ObjShortPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default ObjShortPredicate<T> negate() {
        return (t, value) -> !test(t, value);
    }

    /**
     * Returns a composed {@link ObjShortPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ObjShortPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ObjShortPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ObjShortPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(ObjShortPredicate)
     * @see #xor(ObjShortPredicate)
     */
    @Nonnull
    default ObjShortPredicate<T> and(@Nonnull ObjShortPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) && other.test(t, value);
    }

    /**
     * Returns a composed {@link ObjShortPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ObjShortPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ObjShortPredicate} that will be logically-ORed with this one
     * @return A composed {@code ObjShortPredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ObjShortPredicate)
     * @see #xor(ObjShortPredicate)
     */
    @Nonnull
    default ObjShortPredicate<T> or(@Nonnull ObjShortPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) || other.test(t, value);
    }

    /**
     * Returns a composed {@link ObjShortPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of
     * this {@code ObjShortPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ObjShortPredicate} that will be logically-XORed with this one
     * @return A composed {@code ObjShortPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ObjShortPredicate)
     * @see #or(ObjShortPredicate)
     */
    @Nonnull
    default ObjShortPredicate<T> xor(@Nonnull ObjShortPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) ^ other.test(t, value);
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjShortPredicate}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjShortPredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjShortPredicate<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<T, Short>, Boolean> cache = new ConcurrentHashMap<>();
            return (ObjShortPredicate<T> & Memoized) (t, value) -> {
                return cache.computeIfAbsent(Pair.of(t, value), key -> test(key.getLeft(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link BiPredicate2} which represents this {@link ObjShortPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method provides the possibility to use this {@code
     * ObjShortPredicate} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BiPredicate2} which represents this {@code ObjShortPredicate}.
     */
    @Nonnull
    default BiPredicate2<T, Short> boxed() {
        return this::test;
    }
}
