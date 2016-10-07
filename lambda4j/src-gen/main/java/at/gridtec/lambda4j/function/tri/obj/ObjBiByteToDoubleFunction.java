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
import at.gridtec.lambda4j.consumer.tri.obj.ObjBiByteConsumer;
import at.gridtec.lambda4j.function.BooleanFunction;
import at.gridtec.lambda4j.function.ByteFunction;
import at.gridtec.lambda4j.function.CharFunction;
import at.gridtec.lambda4j.function.FloatFunction;
import at.gridtec.lambda4j.function.ShortFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.CharToByteFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToByteFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToCharFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToFloatFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToShortFunction;
import at.gridtec.lambda4j.function.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.function.conversion.IntToByteFunction;
import at.gridtec.lambda4j.function.conversion.LongToByteFunction;
import at.gridtec.lambda4j.function.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.function.to.ToByteFunction;
import at.gridtec.lambda4j.function.tri.TriFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriByteToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriCharToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriFloatToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriIntToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriLongToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.TriShortToDoubleFunction;
import at.gridtec.lambda4j.function.tri.to.ToDoubleTriFunction;
import at.gridtec.lambda4j.operator.ternary.DoubleTernaryOperator;
import at.gridtec.lambda4j.operator.unary.ByteUnaryOperator;
import at.gridtec.lambda4j.predicate.tri.obj.ObjBiBytePredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.ToDoubleFunction;

/**
 * Represents an operation that accepts one object-valued and two {@code byte}-valued input arguments and produces a
 * {@code double}-valued result.
 * This is a (reference, byte, byte) specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(Object, byte, byte)}.
 *
 * @param <T> The type of the first argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjBiByteToDoubleFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjBiByteToDoubleFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjBiByteToDoubleFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T> ObjBiByteToDoubleFunction<T> of(@Nullable final ObjBiByteToDoubleFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjBiByteToDoubleFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The result from the given {@code ObjBiByteToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> double call(@Nonnull final ObjBiByteToDoubleFunction<? super T> function, T t, byte value1,
            byte value2) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(t, value1, value2);
    }

    /**
     * Creates a {@link ObjBiByteToDoubleFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@link ToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjBiByteToDoubleFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiByteToDoubleFunction<T> onlyFirst(@Nonnull final ToDoubleFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsDouble(t);
    }

    /**
     * Creates a {@link ObjBiByteToDoubleFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ByteToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjBiByteToDoubleFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ByteToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiByteToDoubleFunction<T> onlySecond(@Nonnull final ByteToDoubleFunction function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsDouble(value1);
    }

    /**
     * Creates a {@link ObjBiByteToDoubleFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@link ByteToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ObjBiByteToDoubleFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code ByteToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiByteToDoubleFunction<T> onlyThird(@Nonnull final ByteToDoubleFunction function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsDouble(value2);
    }

    /**
     * Creates a {@link ObjBiByteToDoubleFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjBiByteToDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjBiByteToDoubleFunction<T> constant(double ret) {
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
    double applyAsDouble(T t, byte value1, byte value2);

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
     * Returns a composed {@link ToDoubleTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToDoubleTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToDoubleTriFunction<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final ToByteFunction<? super B> before2, @Nonnull final ToByteFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsDouble(before1.apply(a), before2.applyAsByte(b), before3.applyAsByte(c));
    }

    /**
     * Returns a composed {@link TriBooleanToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToDoubleFunction composeFromBoolean(@Nonnull final BooleanFunction<? extends T> before1,
            @Nonnull final BooleanToByteFunction before2, @Nonnull final BooleanToByteFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.applyAsByte(value2),
                                                         before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link TriByteToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriByteToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToDoubleFunction composeFromByte(@Nonnull final ByteFunction<? extends T> before1,
            @Nonnull final ByteUnaryOperator before2, @Nonnull final ByteUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.applyAsByte(value2),
                                                         before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link TriCharToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriCharToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToDoubleFunction composeFromChar(@Nonnull final CharFunction<? extends T> before1,
            @Nonnull final CharToByteFunction before2, @Nonnull final CharToByteFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.applyAsByte(value2),
                                                         before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code DoubleTernaryOperator} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleTernaryOperator composeFromDouble(@Nonnull final DoubleFunction<? extends T> before1,
            @Nonnull final DoubleToByteFunction before2, @Nonnull final DoubleToByteFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.applyAsByte(value2),
                                                         before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link TriFloatToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFloatToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToDoubleFunction composeFromFloat(@Nonnull final FloatFunction<? extends T> before1,
            @Nonnull final FloatToByteFunction before2, @Nonnull final FloatToByteFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.applyAsByte(value2),
                                                         before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link TriIntToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToDoubleFunction composeFromInt(@Nonnull final IntFunction<? extends T> before1,
            @Nonnull final IntToByteFunction before2, @Nonnull final IntToByteFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.applyAsByte(value2),
                                                         before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link TriLongToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToDoubleFunction composeFromLong(@Nonnull final LongFunction<? extends T> before1,
            @Nonnull final LongToByteFunction before2, @Nonnull final LongToByteFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.applyAsByte(value2),
                                                         before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link TriShortToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToDoubleFunction composeFromShort(@Nonnull final ShortFunction<? extends T> before1,
            @Nonnull final ShortToByteFunction before2, @Nonnull final ShortToByteFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.applyAsByte(value2),
                                                         before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link ObjBiByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjBiByteFunction<T, S> andThen(@Nonnull final DoubleFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.apply(applyAsDouble(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiBytePredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjBiBytePredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjBiBytePredicate<T> andThenToBoolean(@Nonnull final DoublePredicate after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.test(applyAsDouble(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiByteToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiByteToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjBiByteToByteFunction<T> andThenToByte(@Nonnull final DoubleToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsByte(applyAsDouble(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiByteToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiByteToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjBiByteToCharFunction<T> andThenToChar(@Nonnull final DoubleToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsChar(applyAsDouble(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiByteToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjBiByteToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjBiByteToDoubleFunction<T> andThenToDouble(@Nonnull final DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsDouble(applyAsDouble(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiByteToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiByteToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjBiByteToFloatFunction<T> andThenToFloat(@Nonnull final DoubleToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsFloat(applyAsDouble(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiByteToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiByteToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjBiByteToIntFunction<T> andThenToInt(@Nonnull final DoubleToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsInt(applyAsDouble(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiByteToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiByteToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjBiByteToLongFunction<T> andThenToLong(@Nonnull final DoubleToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsLong(applyAsDouble(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiByteToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiByteToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjBiByteToShortFunction<T> andThenToShort(@Nonnull final DoubleToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsShort(applyAsDouble(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiByteConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link DoubleConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjBiByteConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjBiByteConsumer<T> consume(@Nonnull final DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.accept(applyAsDouble(t, value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjBiByteToDoubleFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjBiByteToDoubleFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjBiByteToDoubleFunction<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Triple<T, Byte, Byte>, Double> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ObjBiByteToDoubleFunction<T> & Memoized) (t, value1, value2) -> {
                final double returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, value1, value2),
                                                        key -> applyAsDouble(key.getLeft(), key.getMiddle(),
                                                                             key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjBiByteToDoubleFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method is just convenience to provide the ability
     * to use this {@code ObjBiByteToDoubleFunction} with JDK specific methods, only accepting {@code TriFunction}.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjBiByteToDoubleFunction}.
     */
    @Nonnull
    default TriFunction<T, Byte, Byte, Double> boxed() {
        return this::applyAsDouble;
    }

}