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

package org.lambda4j.function.bi.obj;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.FloatConsumer;
import org.lambda4j.consumer.bi.obj.ObjBooleanConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.BiFunction2;
import org.lambda4j.function.bi.conversion.BiBooleanToFloatFunction;
import org.lambda4j.function.bi.conversion.BiByteToFloatFunction;
import org.lambda4j.function.bi.conversion.BiCharToFloatFunction;
import org.lambda4j.function.bi.conversion.BiDoubleToFloatFunction;
import org.lambda4j.function.bi.conversion.BiIntToFloatFunction;
import org.lambda4j.function.bi.conversion.BiLongToFloatFunction;
import org.lambda4j.function.bi.conversion.BiShortToFloatFunction;
import org.lambda4j.function.bi.to.ToFloatBiFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.FloatToByteFunction;
import org.lambda4j.function.conversion.FloatToCharFunction;
import org.lambda4j.function.conversion.FloatToDoubleFunction;
import org.lambda4j.function.conversion.FloatToIntFunction;
import org.lambda4j.function.conversion.FloatToLongFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.operator.binary.FloatBinaryOperator;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.operator.unary.FloatUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.ShortPredicate;
import org.lambda4j.predicate.bi.obj.ObjBooleanPredicate;

/**
 * Represents an operation that accepts one object-valued and one {@code boolean}-valued input argument and produces a
 * {@code float}-valued result. This is a (reference, boolean) specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(Object, boolean)}.
 *
 * @param <T> The type of the first argument to the function
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjBooleanToFloatFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjBooleanToFloatFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjBooleanToFloatFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T> ObjBooleanToFloatFunction<T> of(@Nullable ObjBooleanToFloatFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjBooleanToFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjBooleanToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> float call(@Nonnull ObjBooleanToFloatFunction<? super T> function, T t, boolean value) {
        Objects.requireNonNull(function);
        return function.applyAsFloat(t, value);
    }

    /**
     * Creates a {@link ObjBooleanToFloatFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@link ToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjBooleanToFloatFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBooleanToFloatFunction<T> onlyFirst(@Nonnull ToFloatFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsFloat(t);
    }

    /**
     * Creates a {@link ObjBooleanToFloatFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link BooleanToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjBooleanToFloatFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code BooleanToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBooleanToFloatFunction<T> onlySecond(@Nonnull BooleanToFloatFunction function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsFloat(value);
    }

    /**
     * Creates a {@link ObjBooleanToFloatFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjBooleanToFloatFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjBooleanToFloatFunction<T> constant(float ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(T t, boolean value);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BooleanToFloatFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code BooleanToFloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BooleanToFloatFunction applyAsFloatPartially(T t) {
        return value -> applyAsFloat(t, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToFloatFunction} as result.
     *
     * @param value The second argument to this function used to partially apply this function
     * @return A {@code ToFloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToFloatFunction<T> applyAsFloatPartially(boolean value) {
        return t -> applyAsFloat(t, value);
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
     * Returns a composed {@link ToFloatBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given predicate, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code ToFloatBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToFloatBiFunction<A, B> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull Predicate<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsFloat(before1.apply(a), before2.test(b));
    }

    /**
     * Returns a composed {@link BiBooleanToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiBooleanToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToFloatFunction composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.apply(value1), before2.applyAsBoolean(value2));
    }

    /**
     * Returns a composed {@link BiByteToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiByteToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteToFloatFunction composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull BytePredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.apply(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiCharToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiCharToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToFloatFunction composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.apply(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiDoubleToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiDoubleToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleToFloatFunction composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoublePredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.apply(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code FloatBinaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatBinaryOperator composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.apply(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiIntToFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiIntToFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntToFloatFunction composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.apply(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiLongToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiLongToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongToFloatFunction composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.apply(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiShortToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @return A composed {@code BiShortToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToFloatFunction composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.apply(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link ObjBooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjBooleanFunction<T, S> andThen(@Nonnull FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsFloat(t, value));
    }

    /**
     * Returns a composed {@link ObjBooleanPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjBooleanPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjBooleanPredicate<T> andThenToBoolean(@Nonnull FloatPredicate after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.test(applyAsFloat(t, value));
    }

    /**
     * Returns a composed {@link ObjBooleanToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBooleanToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjBooleanToByteFunction<T> andThenToByte(@Nonnull FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsByte(applyAsFloat(t, value));
    }

    /**
     * Returns a composed {@link ObjBooleanToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBooleanToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjBooleanToCharFunction<T> andThenToChar(@Nonnull FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsChar(applyAsFloat(t, value));
    }

    /**
     * Returns a composed {@link ObjBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjBooleanToDoubleFunction<T> andThenToDouble(@Nonnull FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsDouble(applyAsFloat(t, value));
    }

    /**
     * Returns a composed {@link ObjBooleanToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjBooleanToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjBooleanToFloatFunction<T> andThenToFloat(@Nonnull FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsFloat(applyAsFloat(t, value));
    }

    /**
     * Returns a composed {@link ObjBooleanToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBooleanToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjBooleanToIntFunction<T> andThenToInt(@Nonnull FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsInt(applyAsFloat(t, value));
    }

    /**
     * Returns a composed {@link ObjBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjBooleanToLongFunction<T> andThenToLong(@Nonnull FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsLong(applyAsFloat(t, value));
    }

    /**
     * Returns a composed {@link ObjBooleanToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBooleanToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjBooleanToShortFunction<T> andThenToShort(@Nonnull FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsShort(applyAsFloat(t, value));
    }

    /**
     * Returns a composed {@link ObjBooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjBooleanConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjBooleanConsumer<T> consume(@Nonnull FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsFloat(t, value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjBooleanToFloatFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjBooleanToFloatFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjBooleanToFloatFunction<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<T, Boolean>, Float> cache = new ConcurrentHashMap<>();
            return (ObjBooleanToFloatFunction<T> & Memoized) (t, value) -> {
                return cache.computeIfAbsent(Pair.of(t, value), key -> applyAsFloat(key.getLeft(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link BiFunction2} which represents this {@link ObjBooleanToFloatFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * ObjBooleanToFloatFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BiFunction2} which represents this {@code ObjBooleanToFloatFunction}.
     */
    @Nonnull
    default BiFunction2<T, Boolean, Float> boxed() {
        return this::applyAsFloat;
    }
}
