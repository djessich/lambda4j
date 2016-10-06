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
package at.gridtec.lambda4j.function.tri.obj;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ByteConsumer;
import at.gridtec.lambda4j.consumer.tri.obj.ObjBiCharConsumer;
import at.gridtec.lambda4j.function.BooleanFunction;
import at.gridtec.lambda4j.function.ByteFunction;
import at.gridtec.lambda4j.function.CharFunction;
import at.gridtec.lambda4j.function.FloatFunction;
import at.gridtec.lambda4j.function.ShortFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.function.conversion.ByteToLongFunction;
import at.gridtec.lambda4j.function.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.function.conversion.CharToByteFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToCharFunction;
import at.gridtec.lambda4j.function.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.function.conversion.IntToCharFunction;
import at.gridtec.lambda4j.function.conversion.LongToCharFunction;
import at.gridtec.lambda4j.function.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.function.to.ToByteFunction;
import at.gridtec.lambda4j.function.to.ToCharFunction;
import at.gridtec.lambda4j.function.tri.TriFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriBooleanToByteFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriCharToByteFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriDoubleToByteFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriFloatToByteFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriIntToByteFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriLongToByteFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriShortToByteFunction;
import at.gridtec.lambda4j.function.tri.to.ToByteTriFunction;
import at.gridtec.lambda4j.operator.ternary.ByteTernaryOperator;
import at.gridtec.lambda4j.operator.unary.ByteUnaryOperator;
import at.gridtec.lambda4j.operator.unary.CharUnaryOperator;
import at.gridtec.lambda4j.predicate.BytePredicate;
import at.gridtec.lambda4j.predicate.tri.obj.ObjBiCharPredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

/**
 * Represents an operation that accepts one object-valued and two {@code char}-valued input arguments and produces a
 * {@code byte}-valued result. This is a (reference, char, char) specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(Object, char, char)}.
 *
 * @param <T> The type of the first argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjBiCharToByteFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjBiCharToByteFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjBiCharToByteFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T> ObjBiCharToByteFunction<T> of(@Nullable final ObjBiCharToByteFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjBiCharToByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The result from the given {@code ObjBiCharToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> byte call(@Nonnull final ObjBiCharToByteFunction<? super T> function, T t, char value1, char value2) {
        Objects.requireNonNull(function);
        return function.applyAsByte(t, value1, value2);
    }

    /**
     * Creates a {@link ObjBiCharToByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjBiCharToByteFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiCharToByteFunction<T> onlyFirst(@Nonnull final ToByteFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsByte(t);
    }

    /**
     * Creates a {@link ObjBiCharToByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link CharToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjBiCharToByteFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code CharToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiCharToByteFunction<T> onlySecond(@Nonnull final CharToByteFunction function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsByte(value1);
    }

    /**
     * Creates a {@link ObjBiCharToByteFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link CharToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ObjBiCharToByteFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code CharToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiCharToByteFunction<T> onlyThird(@Nonnull final CharToByteFunction function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsByte(value2);
    }

    /**
     * Creates a {@link ObjBiCharToByteFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjBiCharToByteFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjBiCharToByteFunction<T> constant(byte ret) {
        return (t, value1, value2) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(T t, char value1, char value2);

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToByteTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToByteTriFunction<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final ToCharFunction<? super B> before2, @Nonnull final ToCharFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsByte(before1.apply(a), before2.applyAsChar(b), before3.applyAsChar(c));
    }

    /**
     * Returns a composed {@link TriBooleanToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToByteFunction composeFromBoolean(@Nonnull final BooleanFunction<? extends T> before1,
            @Nonnull final BooleanToCharFunction before2, @Nonnull final BooleanToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                                                       before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ByteTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteTernaryOperator composeFromByte(@Nonnull final ByteFunction<? extends T> before1,
            @Nonnull final ByteToCharFunction before2, @Nonnull final ByteToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                                                       before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriCharToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriCharToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToByteFunction composeFromChar(@Nonnull final CharFunction<? extends T> before1,
            @Nonnull final CharUnaryOperator before2, @Nonnull final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                                                       before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriDoubleToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToByteFunction composeFromDouble(@Nonnull final DoubleFunction<? extends T> before1,
            @Nonnull final DoubleToCharFunction before2, @Nonnull final DoubleToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                                                       before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriFloatToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFloatToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToByteFunction composeFromFloat(@Nonnull final FloatFunction<? extends T> before1,
            @Nonnull final FloatToCharFunction before2, @Nonnull final FloatToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                                                       before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriIntToByteFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntToByteFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToByteFunction composeFromInt(@Nonnull final IntFunction<? extends T> before1,
            @Nonnull final IntToCharFunction before2, @Nonnull final IntToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                                                       before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriLongToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToByteFunction composeFromLong(@Nonnull final LongFunction<? extends T> before1,
            @Nonnull final LongToCharFunction before2, @Nonnull final LongToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                                                       before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriShortToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToByteFunction composeFromShort(@Nonnull final ShortFunction<? extends T> before1,
            @Nonnull final ShortToCharFunction before2, @Nonnull final ShortToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                                                       before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link ObjBiCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjBiCharFunction<T, S> andThen(@Nonnull final ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.apply(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjBiCharPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjBiCharPredicate<T> andThenToBoolean(@Nonnull final BytePredicate after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.test(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjBiCharToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjBiCharToByteFunction<T> andThenToByte(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsByte(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjBiCharToCharFunction<T> andThenToChar(@Nonnull final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsChar(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjBiCharToDoubleFunction<T> andThenToDouble(@Nonnull final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsDouble(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjBiCharToFloatFunction<T> andThenToFloat(@Nonnull final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsFloat(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjBiCharToIntFunction<T> andThenToInt(@Nonnull final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsInt(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjBiCharToLongFunction<T> andThenToLong(@Nonnull final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsLong(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjBiCharToShortFunction<T> andThenToShort(@Nonnull final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsShort(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjBiCharConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjBiCharConsumer<T> consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.accept(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjBiCharToByteFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjBiCharToByteFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjBiCharToByteFunction<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Triple<T, Character, Character>, Byte> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ObjBiCharToByteFunction<T> & Memoized) (t, value1, value2) -> {
                final byte returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, value1, value2),
                                                        key -> applyAsByte(key.getLeft(), key.getMiddle(),
                                                                           key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjBiCharToByteFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method is just convenience to provide the ability
     * to use this {@code ObjBiCharToByteFunction} with JDK specific methods, only accepting {@code TriFunction}.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjBiCharToByteFunction}.
     */
    @Nonnull
    default TriFunction<T, Character, Character, Byte> boxed() {
        return this::applyAsByte;
    }

}