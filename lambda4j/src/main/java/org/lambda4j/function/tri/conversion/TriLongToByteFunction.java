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

package org.lambda4j.function.tri.conversion;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ByteConsumer;
import org.lambda4j.consumer.tri.TriLongConsumer;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.bi.conversion.BiLongToByteFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.ByteToCharFunction;
import org.lambda4j.function.conversion.ByteToDoubleFunction;
import org.lambda4j.function.conversion.ByteToFloatFunction;
import org.lambda4j.function.conversion.ByteToIntFunction;
import org.lambda4j.function.conversion.ByteToLongFunction;
import org.lambda4j.function.conversion.ByteToShortFunction;
import org.lambda4j.function.conversion.CharToLongFunction;
import org.lambda4j.function.conversion.FloatToLongFunction;
import org.lambda4j.function.conversion.LongToByteFunction;
import org.lambda4j.function.conversion.ShortToLongFunction;
import org.lambda4j.function.tri.TriFunction;
import org.lambda4j.function.tri.TriLongFunction;
import org.lambda4j.function.tri.to.ToByteTriFunction;
import org.lambda4j.operator.ternary.ByteTernaryOperator;
import org.lambda4j.operator.ternary.LongTernaryOperator;
import org.lambda4j.operator.unary.ByteUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.tri.TriLongPredicate;

/**
 * Represents an operation that accepts three {@code long}-valued input arguments and produces a {@code byte}-valued
 * result. This is a primitive specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(long, long, long)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriLongToByteFunction extends Lambda {

    /**
     * Constructs a {@link TriLongToByteFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriLongToByteFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static TriLongToByteFunction of(@Nullable TriLongToByteFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriLongToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriLongToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static byte call(@Nonnull TriLongToByteFunction function, long value1, long value2, long value3) {
        Objects.requireNonNull(function);
        return function.applyAsByte(value1, value2, value3);
    }

    /**
     * Creates a {@link TriLongToByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link LongToByteFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriLongToByteFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code LongToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriLongToByteFunction onlyFirst(@Nonnull LongToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsByte(value1);
    }

    /**
     * Creates a {@link TriLongToByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link LongToByteFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriLongToByteFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code LongToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriLongToByteFunction onlySecond(@Nonnull LongToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsByte(value2);
    }

    /**
     * Creates a {@link TriLongToByteFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link LongToByteFunction}.
     *
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriLongToByteFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code LongToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriLongToByteFunction onlyThird(@Nonnull LongToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsByte(value3);
    }

    /**
     * Creates a {@link TriLongToByteFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriLongToByteFunction} which always returns a given value.
     */
    @Nonnull
    static TriLongToByteFunction constant(byte ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(long value1, long value2, long value3);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BiLongToByteFunction} as
     * result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @return A {@code BiLongToByteFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BiLongToByteFunction applyAsBytePartially(long value1) {
        return (value2, value3) -> applyAsByte(value1, value2, value3);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link LongToByteFunction} as result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @param value2 The second argument to this function used to partially apply this function
     * @return A {@code LongToByteFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default LongToByteFunction applyAsBytePartially(long value1, long value2) {
        return value3 -> applyAsByte(value1, value2, value3);
    }

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
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToByteTriFunction<A, B, C> compose(@Nonnull ToLongFunction<? super A> before1,
            @Nonnull ToLongFunction<? super B> before2, @Nonnull ToLongFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsByte(before1.applyAsLong(a), before2.applyAsLong(b), before3.applyAsLong(c));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToByteFunction composeFromBoolean(@Nonnull BooleanToLongFunction before1,
            @Nonnull BooleanToLongFunction before2, @Nonnull BooleanToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteTernaryOperator composeFromByte(@Nonnull ByteToLongFunction before1,
            @Nonnull ByteToLongFunction before2, @Nonnull ByteToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriCharToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriCharToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToByteFunction composeFromChar(@Nonnull CharToLongFunction before1,
            @Nonnull CharToLongFunction before2, @Nonnull CharToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToByteFunction composeFromDouble(@Nonnull DoubleToLongFunction before1,
            @Nonnull DoubleToLongFunction before2, @Nonnull DoubleToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToByteFunction composeFromFloat(@Nonnull FloatToLongFunction before1,
            @Nonnull FloatToLongFunction before2, @Nonnull FloatToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToByteFunction composeFromInt(@Nonnull IntToLongFunction before1,
            @Nonnull IntToLongFunction before2, @Nonnull IntToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriLongToByteFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriLongToByteFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToByteFunction composeFromLong(@Nonnull LongUnaryOperator before1,
            @Nonnull LongUnaryOperator before2, @Nonnull LongUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToByteFunction composeFromShort(@Nonnull ShortToLongFunction before1,
            @Nonnull ShortToLongFunction before2, @Nonnull ShortToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.applyAsLong(value1), before2.applyAsLong(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriLongFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriLongFunction<S> andThen(@Nonnull ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code TriLongPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriLongPredicate andThenToBoolean(@Nonnull BytePredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code TriLongToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriLongToByteFunction andThenToByte(@Nonnull ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriLongToCharFunction andThenToChar(@Nonnull ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriLongToDoubleFunction andThenToDouble(@Nonnull ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriLongToFloatFunction andThenToFloat(@Nonnull ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriLongToIntFunction andThenToInt(@Nonnull ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongTernaryOperator andThenToLong(@Nonnull ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriLongToShortFunction andThenToShort(@Nonnull ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriLongConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriLongConsumer consume(@Nonnull ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default TriLongToByteFunction reversed() {
        return (value3, value2, value1) -> applyAsByte(value1, value2, value3);
    }

    /**
     * Returns a memoized (caching) version of this {@link TriLongToByteFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriLongToByteFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriLongToByteFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Long, Long, Long>, Byte> cache = new ConcurrentHashMap<>();
            return (TriLongToByteFunction & Memoized) (value1, value2, value3) -> {
                return cache.computeIfAbsent(Triple.of(value1, value2, value3),
                        key -> applyAsByte(key.getLeft(), key.getMiddle(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriLongToByteFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * TriLongToByteFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriLongToByteFunction}.
     */
    @Nonnull
    default TriFunction<Long, Long, Long, Byte> boxed() {
        return this::applyAsByte;
    }
}
