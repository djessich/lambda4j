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

import org.lambda4j.Lambda;
import org.lambda4j.consumer.FloatConsumer;
import org.lambda4j.consumer.tri.TriDoubleConsumer;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.bi.conversion.BiDoubleToFloatFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.ByteToDoubleFunction;
import org.lambda4j.function.conversion.CharToDoubleFunction;
import org.lambda4j.function.conversion.DoubleToFloatFunction;
import org.lambda4j.function.conversion.FloatToByteFunction;
import org.lambda4j.function.conversion.FloatToCharFunction;
import org.lambda4j.function.conversion.FloatToDoubleFunction;
import org.lambda4j.function.conversion.FloatToIntFunction;
import org.lambda4j.function.conversion.FloatToLongFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.conversion.ShortToDoubleFunction;
import org.lambda4j.function.tri.TriDoubleFunction;
import org.lambda4j.function.tri.TriFunction;
import org.lambda4j.function.tri.to.ToFloatTriFunction;
import org.lambda4j.operator.ternary.DoubleTernaryOperator;
import org.lambda4j.operator.ternary.FloatTernaryOperator;
import org.lambda4j.operator.unary.FloatUnaryOperator;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.tri.TriDoublePredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

/**
 * Represents an operation that accepts three {@code double}-valued input arguments and produces a
 * {@code float}-valued result.
 * This is a primitive specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(double, double, double)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriDoubleToFloatFunction extends Lambda {

    /**
     * Constructs a {@link TriDoubleToFloatFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriDoubleToFloatFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static TriDoubleToFloatFunction of(@Nullable final TriDoubleToFloatFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriDoubleToFloatFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriDoubleToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static float call(@Nonnull final TriDoubleToFloatFunction function, double value1, double value2, double value3) {
        Objects.requireNonNull(function);
        return function.applyAsFloat(value1, value2, value3);
    }

    /**
     * Creates a {@link TriDoubleToFloatFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link DoubleToFloatFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriDoubleToFloatFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code DoubleToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriDoubleToFloatFunction onlyFirst(@Nonnull final DoubleToFloatFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsFloat(value1);
    }

    /**
     * Creates a {@link TriDoubleToFloatFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link DoubleToFloatFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriDoubleToFloatFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code DoubleToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriDoubleToFloatFunction onlySecond(@Nonnull final DoubleToFloatFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsFloat(value2);
    }

    /**
     * Creates a {@link TriDoubleToFloatFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link DoubleToFloatFunction}.
     *
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriDoubleToFloatFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code DoubleToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriDoubleToFloatFunction onlyThird(@Nonnull final DoubleToFloatFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsFloat(value3);
    }

    /**
     * Creates a {@link TriDoubleToFloatFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriDoubleToFloatFunction} which always returns a given value.
     */
    @Nonnull
    static TriDoubleToFloatFunction constant(float ret) {
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
    float applyAsFloat(double value1, double value2, double value3);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BiDoubleToFloatFunction} as
     * result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @return A {@code BiDoubleToFloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BiDoubleToFloatFunction papplyAsFloat(double value1) {
        return (value2, value3) -> this.applyAsFloat(value1, value2, value3);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link DoubleToFloatFunction} as
     * result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @param value2 The second argument to this function used to partially apply this function
     * @return A {@code DoubleToFloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default DoubleToFloatFunction papplyAsFloat(double value1, double value2) {
        return (value3) -> this.applyAsFloat(value1, value2, value3);
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
     * Returns a composed {@link ToFloatTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToFloatTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToFloatTriFunction<A, B, C> compose(@Nonnull final ToDoubleFunction<? super A> before1,
            @Nonnull final ToDoubleFunction<? super B> before2, @Nonnull final ToDoubleFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsFloat(before1.applyAsDouble(a), before2.applyAsDouble(b), before3.applyAsDouble(c));
    }

    /**
     * Returns a composed {@link TriBooleanToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToFloatFunction composeFromBoolean(@Nonnull final BooleanToDoubleFunction before1,
            @Nonnull final BooleanToDoubleFunction before2, @Nonnull final BooleanToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                        before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriByteToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToFloatFunction composeFromByte(@Nonnull final ByteToDoubleFunction before1,
            @Nonnull final ByteToDoubleFunction before2, @Nonnull final ByteToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                        before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriCharToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriCharToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToFloatFunction composeFromChar(@Nonnull final CharToDoubleFunction before1,
            @Nonnull final CharToDoubleFunction before2, @Nonnull final CharToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                        before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToFloatFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriDoubleToFloatFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToFloatFunction composeFromDouble(@Nonnull final DoubleUnaryOperator before1,
            @Nonnull final DoubleUnaryOperator before2, @Nonnull final DoubleUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                        before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code FloatTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatTernaryOperator composeFromFloat(@Nonnull final FloatToDoubleFunction before1,
            @Nonnull final FloatToDoubleFunction before2, @Nonnull final FloatToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                        before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriIntToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToFloatFunction composeFromInt(@Nonnull final IntToDoubleFunction before1,
            @Nonnull final IntToDoubleFunction before2, @Nonnull final IntToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                        before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriLongToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToFloatFunction composeFromLong(@Nonnull final LongToDoubleFunction before1,
            @Nonnull final LongToDoubleFunction before2, @Nonnull final LongToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                        before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriShortToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToFloatFunction composeFromShort(@Nonnull final ShortToDoubleFunction before1,
            @Nonnull final ShortToDoubleFunction before2, @Nonnull final ShortToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                        before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriDoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriDoubleFunction<S> andThen(@Nonnull final FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code TriDoublePredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriDoublePredicate andThenToBoolean(@Nonnull final FloatPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriDoubleToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriDoubleToByteFunction andThenToByte(@Nonnull final FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriDoubleToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriDoubleToCharFunction andThenToChar(@Nonnull final FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleTernaryOperator andThenToDouble(@Nonnull final FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code TriDoubleToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriDoubleToFloatFunction andThenToFloat(@Nonnull final FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriDoubleToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriDoubleToIntFunction andThenToInt(@Nonnull final FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriDoubleToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriDoubleToLongFunction andThenToLong(@Nonnull final FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriDoubleToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriDoubleToShortFunction andThenToShort(@Nonnull final FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriDoubleConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriDoubleConsumer consume(@Nonnull final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link TriDoubleToFloatFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriDoubleToFloatFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriDoubleToFloatFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Triple<Double, Double, Double>, Float> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (TriDoubleToFloatFunction & Memoized) (value1, value2, value3) -> {
                final float returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3),
                                                        key -> applyAsFloat(key.getLeft(), key.getMiddle(),
                                                                            key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriDoubleToFloatFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * TriDoubleToFloatFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriDoubleToFloatFunction}.
     */
    @Nonnull
    default TriFunction<Double, Double, Double, Float> boxed() {
        return this::applyAsFloat;
    }

}