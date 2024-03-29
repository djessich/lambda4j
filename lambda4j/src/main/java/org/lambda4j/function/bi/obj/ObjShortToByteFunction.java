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
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ByteConsumer;
import org.lambda4j.consumer.bi.obj.ObjShortConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.BiFunction2;
import org.lambda4j.function.bi.conversion.BiBooleanToByteFunction;
import org.lambda4j.function.bi.conversion.BiCharToByteFunction;
import org.lambda4j.function.bi.conversion.BiDoubleToByteFunction;
import org.lambda4j.function.bi.conversion.BiFloatToByteFunction;
import org.lambda4j.function.bi.conversion.BiIntToByteFunction;
import org.lambda4j.function.bi.conversion.BiLongToByteFunction;
import org.lambda4j.function.bi.conversion.BiShortToByteFunction;
import org.lambda4j.function.bi.to.ToByteBiFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.conversion.ByteToCharFunction;
import org.lambda4j.function.conversion.ByteToDoubleFunction;
import org.lambda4j.function.conversion.ByteToFloatFunction;
import org.lambda4j.function.conversion.ByteToIntFunction;
import org.lambda4j.function.conversion.ByteToLongFunction;
import org.lambda4j.function.conversion.ByteToShortFunction;
import org.lambda4j.function.conversion.CharToShortFunction;
import org.lambda4j.function.conversion.DoubleToShortFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.conversion.IntToShortFunction;
import org.lambda4j.function.conversion.LongToShortFunction;
import org.lambda4j.function.conversion.ShortToByteFunction;
import org.lambda4j.function.to.ToByteFunction;
import org.lambda4j.function.to.ToShortFunction;
import org.lambda4j.operator.binary.ByteBinaryOperator;
import org.lambda4j.operator.unary.ByteUnaryOperator;
import org.lambda4j.operator.unary.ShortUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.bi.obj.ObjShortPredicate;

/**
 * Represents an operation that accepts one object-valued and one {@code short}-valued input argument and produces a
 * {@code byte}-valued result. This is a (reference, short) specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(Object, short)}.
 *
 * @param <T> The type of the first argument to the function
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjShortToByteFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjShortToByteFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjShortToByteFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T> ObjShortToByteFunction<T> of(@Nullable ObjShortToByteFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjShortToByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjShortToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> byte call(@Nonnull ObjShortToByteFunction<? super T> function, T t, short value) {
        Objects.requireNonNull(function);
        return function.applyAsByte(t, value);
    }

    /**
     * Creates a {@link ObjShortToByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjShortToByteFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjShortToByteFunction<T> onlyFirst(@Nonnull ToByteFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsByte(t);
    }

    /**
     * Creates a {@link ObjShortToByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ShortToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjShortToByteFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ShortToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjShortToByteFunction<T> onlySecond(@Nonnull ShortToByteFunction function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsByte(value);
    }

    /**
     * Creates a {@link ObjShortToByteFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjShortToByteFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjShortToByteFunction<T> constant(byte ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(T t, short value);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ShortToByteFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ShortToByteFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ShortToByteFunction applyAsBytePartially(T t) {
        return value -> applyAsByte(t, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToByteFunction} as result.
     *
     * @param value The second argument to this function used to partially apply this function
     * @return A {@code ToByteFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToByteFunction<T> applyAsBytePartially(short value) {
        return t -> applyAsByte(t, value);
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
     * Returns a composed {@link ToByteBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToByteBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToByteBiFunction<A, B> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull ToShortFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsByte(before1.apply(a), before2.applyAsShort(b));
    }

    /**
     * Returns a composed {@link BiBooleanToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiBooleanToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToByteFunction composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link ByteBinaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ByteBinaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteBinaryOperator composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiCharToByteFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiCharToByteFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToByteFunction composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiDoubleToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiDoubleToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleToByteFunction composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiFloatToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiFloatToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatToByteFunction composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiIntToByteFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiIntToByteFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntToByteFunction composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiLongToByteFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiLongToByteFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongToByteFunction composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiShortToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiShortToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToByteFunction composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link ObjShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjShortFunction<T, S> andThen(@Nonnull ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjShortPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjShortPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjShortPredicate<T> andThenToBoolean(@Nonnull BytePredicate after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.test(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjShortToByteFunction<T> andThenToByte(@Nonnull ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsByte(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjShortToCharFunction<T> andThenToChar(@Nonnull ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsChar(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjShortToDoubleFunction<T> andThenToDouble(@Nonnull ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsDouble(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjShortToFloatFunction<T> andThenToFloat(@Nonnull ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsFloat(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjShortToIntFunction<T> andThenToInt(@Nonnull ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsInt(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjShortToLongFunction<T> andThenToLong(@Nonnull ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsLong(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjShortToShortFunction<T> andThenToShort(@Nonnull ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsShort(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjShortConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjShortConsumer<T> consume(@Nonnull ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsByte(t, value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjShortToByteFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjShortToByteFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjShortToByteFunction<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<T, Short>, Byte> cache = new ConcurrentHashMap<>();
            return (ObjShortToByteFunction<T> & Memoized) (t, value) -> {
                return cache.computeIfAbsent(Pair.of(t, value), key -> applyAsByte(key.getLeft(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link BiFunction2} which represents this {@link ObjShortToByteFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * ObjShortToByteFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BiFunction2} which represents this {@code ObjShortToByteFunction}.
     */
    @Nonnull
    default BiFunction2<T, Short, Byte> boxed() {
        return this::applyAsByte;
    }
}
