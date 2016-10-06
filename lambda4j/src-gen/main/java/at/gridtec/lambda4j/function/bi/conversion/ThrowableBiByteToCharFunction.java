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
package at.gridtec.lambda4j.function.bi.conversion;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableCharConsumer;
import at.gridtec.lambda4j.consumer.bi.ThrowableBiByteConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableCharFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.bi.ThrowableBiByteFunction;
import at.gridtec.lambda4j.function.bi.ThrowableBiFunction;
import at.gridtec.lambda4j.function.bi.to.ThrowableToCharBiFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableByteToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToIntFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToLongFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableLongToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToByteFunction;
import at.gridtec.lambda4j.function.to.ThrowableToByteFunction;
import at.gridtec.lambda4j.operator.binary.ThrowableByteBinaryOperator;
import at.gridtec.lambda4j.operator.binary.ThrowableCharBinaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableByteUnaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableCharUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableCharPredicate;
import at.gridtec.lambda4j.predicate.bi.ThrowableBiBytePredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an operation that accepts two {@code byte}-valued input arguments and produces a {@code char}-valued
 * result which is able to throw any {@link Throwable}. This is a primitive specialization of {@link
 * ThrowableBiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsCharThrows(byte, byte)}.
 *
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableBiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiByteToCharFunction<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableBiByteToCharFunction} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBiByteToCharFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableBiByteToCharFunction<X> of(
            @Nullable final ThrowableBiByteToCharFunction<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBiByteToCharFunction} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code ThrowableBiByteToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <X extends Throwable> char call(@Nonnull final ThrowableBiByteToCharFunction<? extends X> function,
            byte value1, byte value2) throws X {
        Objects.requireNonNull(function);
        return function.applyAsCharThrows(value1, value2);
    }

    /**
     * Creates a {@link ThrowableBiByteToCharFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@link ThrowableByteToCharFunction}.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiByteToCharFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableByteToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiByteToCharFunction<X> onlyFirst(
            @Nonnull final ThrowableByteToCharFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsCharThrows(value1);
    }

    /**
     * Creates a {@link ThrowableBiByteToCharFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableByteToCharFunction}.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiByteToCharFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableByteToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiByteToCharFunction<X> onlySecond(
            @Nonnull final ThrowableByteToCharFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsCharThrows(value2);
    }

    /**
     * Creates a {@link ThrowableBiByteToCharFunction} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableBiByteToCharFunction} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiByteToCharFunction<X> constant(char ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    char applyAsCharThrows(byte value1, byte value2) throws X;

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
     * Returns a composed {@link ThrowableToCharBiFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableToCharBiFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ThrowableToCharBiFunction<A, B, X> compose(
            @Nonnull final ThrowableToByteFunction<? super A, ? extends X> before1,
            @Nonnull final ThrowableToByteFunction<? super B, ? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsCharThrows(before1.applyAsByteThrows(a), before2.applyAsByteThrows(b));
    }

    /**
     * Returns a composed {@link ThrowableBiBooleanToCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiBooleanToCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiBooleanToCharFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> before1,
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsCharThrows(before1.applyAsByteThrows(value1),
                                                     before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToCharFunction} that first applies the {@code before} operators to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code ThrowableBiByteToCharFunction} that first applies the {@code before} operators to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiByteToCharFunction<X> composeFromByte(
            @Nonnull final ThrowableByteUnaryOperator<? extends X> before1,
            @Nonnull final ThrowableByteUnaryOperator<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsCharThrows(before1.applyAsByteThrows(value1),
                                                     before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableCharBinaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableCharBinaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharBinaryOperator<X> composeFromChar(
            @Nonnull final ThrowableCharToByteFunction<? extends X> before1,
            @Nonnull final ThrowableCharToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsCharThrows(before1.applyAsByteThrows(value1),
                                                     before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiDoubleToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiDoubleToCharFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoubleToByteFunction<? extends X> before1,
            @Nonnull final ThrowableDoubleToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsCharThrows(before1.applyAsByteThrows(value1),
                                                     before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiFloatToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiFloatToCharFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatToByteFunction<? extends X> before1,
            @Nonnull final ThrowableFloatToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsCharThrows(before1.applyAsByteThrows(value1),
                                                     before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiIntToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiIntToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiIntToCharFunction<X> composeFromInt(
            @Nonnull final ThrowableIntToByteFunction<? extends X> before1,
            @Nonnull final ThrowableIntToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsCharThrows(before1.applyAsByteThrows(value1),
                                                     before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiLongToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiLongToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiLongToCharFunction<X> composeFromLong(
            @Nonnull final ThrowableLongToByteFunction<? extends X> before1,
            @Nonnull final ThrowableLongToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsCharThrows(before1.applyAsByteThrows(value1),
                                                     before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiShortToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiShortToCharFunction<X> composeFromShort(
            @Nonnull final ThrowableShortToByteFunction<? extends X> before1,
            @Nonnull final ThrowableShortToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsCharThrows(before1.applyAsByteThrows(value1),
                                                     before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBiByteFunction<S, X> andThen(
            @Nonnull final ThrowableCharFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyThrows(applyAsCharThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiBytePredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableBiBytePredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiBytePredicate<X> andThenToBoolean(@Nonnull final ThrowableCharPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.testThrows(applyAsCharThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableByteBinaryOperator} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableByteBinaryOperator} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteBinaryOperator<X> andThenToByte(
            @Nonnull final ThrowableCharToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByteThrows(applyAsCharThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableBiByteToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiByteToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableCharUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsCharThrows(applyAsCharThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiByteToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiByteToDoubleFunction<X> andThenToDouble(
            @Nonnull final ThrowableCharToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDoubleThrows(applyAsCharThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiByteToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiByteToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableCharToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloatThrows(applyAsCharThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiByteToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiByteToIntFunction<X> andThenToInt(@Nonnull final ThrowableCharToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsIntThrows(applyAsCharThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiByteToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiByteToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableCharToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLongThrows(applyAsCharThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiByteToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiByteToShortFunction<X> andThenToShort(
            @Nonnull final ThrowableCharToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShortThrows(applyAsCharThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableCharConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiByteConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableCharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiByteConsumer<X> consume(@Nonnull final ThrowableCharConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.acceptThrows(applyAsCharThrows(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableBiByteToCharFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableBiByteToCharFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableBiByteToCharFunction<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<Byte, Byte>, Character> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableBiByteToCharFunction<X> & Memoized) (value1, value2) -> {
                final char returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2), ThrowableFunction.of(
                            key -> applyAsCharThrows(key.getLeft(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableBiFunction} which represents this {@link ThrowableBiByteToCharFunction}.
     * Thereby the primitive input argument for this function is autoboxed.
     *
     * @return A composed {@code ThrowableBiFunction} which represents this {@code ThrowableBiByteToCharFunction}.
     */
    @Nonnull
    default ThrowableBiFunction<Byte, Byte, Character, X> boxed() {
        return this::applyAsCharThrows;
    }

    /**
     * Returns a composed {@link BiByteToCharFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is
     * nested (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown
     * throwables message and the thrown throwable itself.
     *
     * @return A composed {@code BiByteToCharFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default BiByteToCharFunction nest() {
        return (value1, value2) -> {
            try {
                return this.applyAsCharThrows(value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link BiByteToCharFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed function behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this function in the returned composed
     * function by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing function.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing function variant of this throwable function, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed function. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed function. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed function is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed function, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed function.
     * <p>
     * With all that mentioned, the following example will demonstrate this methods correct use:
     * <pre>{@code
     * // when called with illegal value ClassNotFoundException is thrown
     * public Class<?> sneakyThrowingFunctionalInterface(final String className) throws ClassNotFoundException {
     *     return ThrowableFunction.of(Class::forName) // create the correct throwable functional interface
     *                .sneakyThrow() // create a non-throwable variant which is able to sneaky throw (this method)
     *                .apply(className); // apply non-throwable variant -> may sneaky throw a throwable
     * }
     *
     * // call the the method which surround the sneaky throwing functional interface
     * public void callingMethod() {
     *     try {
     *         final Class<?> clazz = sneakyThrowingFunctionalInterface("some illegal class name");
     *         // ... do something with clazz ...
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link BiByteToCharFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default BiByteToCharFunction sneakyThrow() {
        return (value1, value2) -> {
            try {
                return this.applyAsCharThrows(value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}