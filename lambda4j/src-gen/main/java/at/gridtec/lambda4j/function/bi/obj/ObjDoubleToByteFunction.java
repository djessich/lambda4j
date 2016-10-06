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
package at.gridtec.lambda4j.function.bi.obj;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ByteConsumer;
import at.gridtec.lambda4j.consumer.bi.obj.ObjDoubleConsumer2;
import at.gridtec.lambda4j.function.BooleanFunction;
import at.gridtec.lambda4j.function.ByteFunction;
import at.gridtec.lambda4j.function.CharFunction;
import at.gridtec.lambda4j.function.FloatFunction;
import at.gridtec.lambda4j.function.ShortFunction;
import at.gridtec.lambda4j.function.bi.BiFunction2;
import at.gridtec.lambda4j.function.bi.conversion.BiBooleanToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiCharToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiDoubleToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiFloatToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiIntToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiLongToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiShortToByteFunction;
import at.gridtec.lambda4j.function.bi.to.ToByteBiFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.function.conversion.ByteToLongFunction;
import at.gridtec.lambda4j.function.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.function.conversion.CharToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToByteFunction;
import at.gridtec.lambda4j.function.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.function.to.ToByteFunction;
import at.gridtec.lambda4j.operator.binary.ByteBinaryOperator;
import at.gridtec.lambda4j.operator.unary.ByteUnaryOperator;
import at.gridtec.lambda4j.predicate.BytePredicate;
import at.gridtec.lambda4j.predicate.bi.obj.ObjDoublePredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

/**
 * Represents an operation that accepts one object-valued and one {@code double}-valued input argument and produces a
 * {@code byte}-valued result. This is a (reference, double) specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(Object, double)}.
 *
 * @param <T> The type of the first argument to the function
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjDoubleToByteFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjDoubleToByteFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjDoubleToByteFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T> ObjDoubleToByteFunction<T> of(@Nullable final ObjDoubleToByteFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjDoubleToByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjDoubleToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> byte call(@Nonnull final ObjDoubleToByteFunction<? super T> function, T t, double value) {
        Objects.requireNonNull(function);
        return function.applyAsByte(t, value);
    }

    /**
     * Creates a {@link ObjDoubleToByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjDoubleToByteFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjDoubleToByteFunction<T> onlyFirst(@Nonnull final ToByteFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsByte(t);
    }

    /**
     * Creates a {@link ObjDoubleToByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link DoubleToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjDoubleToByteFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code DoubleToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjDoubleToByteFunction<T> onlySecond(@Nonnull final DoubleToByteFunction function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsByte(value);
    }

    /**
     * Creates a {@link ObjDoubleToByteFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjDoubleToByteFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjDoubleToByteFunction<T> constant(byte ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(T t, double value);

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
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToByteBiFunction<A, B> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final ToDoubleFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsByte(before1.apply(a), before2.applyAsDouble(b));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToByteFunction composeFromBoolean(@Nonnull final BooleanFunction<? extends T> before1,
            @Nonnull final BooleanToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsDouble(value2));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteBinaryOperator composeFromByte(@Nonnull final ByteFunction<? extends T> before1,
            @Nonnull final ByteToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsDouble(value2));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToByteFunction composeFromChar(@Nonnull final CharFunction<? extends T> before1,
            @Nonnull final CharToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiDoubleToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiDoubleToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleToByteFunction composeFromDouble(@Nonnull final DoubleFunction<? extends T> before1,
            @Nonnull final DoubleUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsDouble(value2));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatToByteFunction composeFromFloat(@Nonnull final FloatFunction<? extends T> before1,
            @Nonnull final FloatToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsDouble(value2));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntToByteFunction composeFromInt(@Nonnull final IntFunction<? extends T> before1,
            @Nonnull final IntToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsDouble(value2));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongToByteFunction composeFromLong(@Nonnull final LongFunction<? extends T> before1,
            @Nonnull final LongToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiShortToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiShortToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToByteFunction composeFromShort(@Nonnull final ShortFunction<? extends T> before1,
            @Nonnull final ShortToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.apply(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link ObjDoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjDoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjDoubleFunction<T, S> andThen(@Nonnull final ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjDoublePredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjDoublePredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjDoublePredicate<T> andThenToBoolean(@Nonnull final BytePredicate after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.test(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjDoubleToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjDoubleToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjDoubleToByteFunction<T> andThenToByte(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsByte(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjDoubleToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjDoubleToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjDoubleToCharFunction<T> andThenToChar(@Nonnull final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsChar(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjDoubleToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjDoubleToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjDoubleToDoubleFunction<T> andThenToDouble(@Nonnull final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsDouble(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjDoubleToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjDoubleToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjDoubleToFloatFunction<T> andThenToFloat(@Nonnull final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsFloat(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjDoubleToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjDoubleToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjDoubleToIntFunction<T> andThenToInt(@Nonnull final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsInt(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjDoubleToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjDoubleToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjDoubleToLongFunction<T> andThenToLong(@Nonnull final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsLong(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjDoubleToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjDoubleToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjDoubleToShortFunction<T> andThenToShort(@Nonnull final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsShort(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjDoubleConsumer2} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjDoubleConsumer2} that first applies this function to its input, and then consumes
     * the result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjDoubleConsumer2<T> consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsByte(t, value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjDoubleToByteFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjDoubleToByteFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjDoubleToByteFunction<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<T, Double>, Byte> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ObjDoubleToByteFunction<T> & Memoized) (t, value) -> {
                final byte returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(t, value),
                                                        key -> applyAsByte(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjDoubleToByteFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method is just convenience to provide the ability
     * to use this {@code ObjDoubleToByteFunction} with JDK specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjDoubleToByteFunction}.
     */
    @Nonnull
    default BiFunction<T, Double, Byte> boxed() {
        return this::applyAsByte;
    }

}