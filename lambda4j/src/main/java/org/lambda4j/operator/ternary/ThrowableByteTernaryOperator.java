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
package org.lambda4j.operator.ternary;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableByteConsumer;
import org.lambda4j.consumer.tri.ThrowableTriByteConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableByteFunction;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToByteFunction;
import org.lambda4j.function.conversion.ThrowableByteToCharFunction;
import org.lambda4j.function.conversion.ThrowableByteToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableByteToFloatFunction;
import org.lambda4j.function.conversion.ThrowableByteToIntFunction;
import org.lambda4j.function.conversion.ThrowableByteToLongFunction;
import org.lambda4j.function.conversion.ThrowableByteToShortFunction;
import org.lambda4j.function.conversion.ThrowableCharToByteFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToByteFunction;
import org.lambda4j.function.conversion.ThrowableFloatToByteFunction;
import org.lambda4j.function.conversion.ThrowableIntToByteFunction;
import org.lambda4j.function.conversion.ThrowableLongToByteFunction;
import org.lambda4j.function.conversion.ThrowableShortToByteFunction;
import org.lambda4j.function.to.ThrowableToByteFunction;
import org.lambda4j.function.tri.ThrowableTriByteFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriBooleanToByteFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToCharFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToFloatFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToIntFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToLongFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriCharToByteFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToByteFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriFloatToByteFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriIntToByteFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToByteFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToByteFunction;
import org.lambda4j.function.tri.to.ThrowableToByteTriFunction;
import org.lambda4j.operator.binary.ThrowableByteBinaryOperator;
import org.lambda4j.operator.unary.ThrowableByteUnaryOperator;
import org.lambda4j.predicate.ThrowableBytePredicate;
import org.lambda4j.predicate.tri.ThrowableTriBytePredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents an operation that accepts three {@code byte}-valued input arguments and produces a
 * {@code byte}-valued result which is able to throw any {@link Throwable}.
 * This is a primitive specialization of {@link ThrowableTernaryOperator}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByteThrows(byte, byte, byte)}.
 *
 * @param <X> The type of the throwable to be thrown by this operator
 * @see ThrowableTernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableByteTernaryOperator<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableByteTernaryOperator} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableByteTernaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableByteTernaryOperator<X> of(
            @Nullable final ThrowableByteTernaryOperator<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableByteTernaryOperator} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @param value3 The third argument to the operator
     * @return The result from the given {@code ThrowableByteTernaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this operators action
     */
    static <X extends Throwable> byte call(@Nonnull final ThrowableByteTernaryOperator<? extends X> operator,
            byte value1, byte value2, byte value3) throws X {
        Objects.requireNonNull(operator);
        return operator.applyAsByteThrows(value1, value2, value3);
    }

    /**
     * Creates a {@link ThrowableByteTernaryOperator} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowableByteUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableByteTernaryOperator} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableByteUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableByteTernaryOperator<X> onlyFirst(
            @Nonnull final ThrowableByteUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsByteThrows(value1);
    }

    /**
     * Creates a {@link ThrowableByteTernaryOperator} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableByteUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableByteTernaryOperator} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableByteUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableByteTernaryOperator<X> onlySecond(
            @Nonnull final ThrowableByteUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsByteThrows(value2);
    }

    /**
     * Creates a {@link ThrowableByteTernaryOperator} which uses the {@code third} parameter of this one as argument for
     * the given {@link ThrowableByteUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableByteTernaryOperator} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableByteUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableByteTernaryOperator<X> onlyThird(
            @Nonnull final ThrowableByteUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsByteThrows(value3);
    }

    /**
     * Creates a {@link ThrowableByteTernaryOperator} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param ret The return value for the constant
     * @return A {@code ThrowableByteTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableByteTernaryOperator<X> constant(byte ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @param value3 The third argument to the operator
     * @return The return value from the operator, which is its result.
     * @throws X Any throwable from this operators action
     */
    byte applyAsByteThrows(byte value1, byte value2, byte value3) throws X;

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowableByteBinaryOperator} as
     * result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @return A {@code ThrowableByteBinaryOperator} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default ThrowableByteBinaryOperator<X> papplyAsByteThrows(byte value1) {
        return (value2, value3) -> this.applyAsByteThrows(value1, value2, value3);
    }

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowableByteUnaryOperator} as
     * result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @param value2 The second argument to this operator used to partially apply this function
     * @return A {@code ThrowableByteUnaryOperator} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default ThrowableByteUnaryOperator<X> papplyAsByteThrows(byte value1, byte value2) {
        return (value3) -> this.applyAsByteThrows(value1, value2, value3);
    }

    /**
     * Returns the number of arguments for this operator.
     *
     * @return The number of arguments for this operator.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ThrowableToByteTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableToByteTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToByteTriFunction<A, B, C, X> compose(
            @Nonnull final ThrowableToByteFunction<? super A, ? extends X> before1,
            @Nonnull final ThrowableToByteFunction<? super B, ? extends X> before2,
            @Nonnull final ThrowableToByteFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsByteThrows(before1.applyAsByteThrows(a), before2.applyAsByteThrows(b),
                                              before3.applyAsByteThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToByteFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriBooleanToByteFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToByteFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> before1,
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> before2,
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                                                             before2.applyAsByteThrows(value2),
                                                             before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableByteTernaryOperator} that first applies the {@code before} operators to
     * its input, and then applies this operator to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@code ThrowableByteTernaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteTernaryOperator<X> composeFromByte(
            @Nonnull final ThrowableByteUnaryOperator<? extends X> before1,
            @Nonnull final ThrowableByteUnaryOperator<? extends X> before2,
            @Nonnull final ThrowableByteUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                                                             before2.applyAsByteThrows(value2),
                                                             before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToByteFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriCharToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharToByteFunction<X> composeFromChar(
            @Nonnull final ThrowableCharToByteFunction<? extends X> before1,
            @Nonnull final ThrowableCharToByteFunction<? extends X> before2,
            @Nonnull final ThrowableCharToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                                                             before2.applyAsByteThrows(value2),
                                                             before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToByteFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriDoubleToByteFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleToByteFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoubleToByteFunction<? extends X> before1,
            @Nonnull final ThrowableDoubleToByteFunction<? extends X> before2,
            @Nonnull final ThrowableDoubleToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                                                             before2.applyAsByteThrows(value2),
                                                             before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriFloatToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatToByteFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatToByteFunction<? extends X> before1,
            @Nonnull final ThrowableFloatToByteFunction<? extends X> before2,
            @Nonnull final ThrowableFloatToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                                                             before2.applyAsByteThrows(value2),
                                                             before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToByteFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriIntToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToByteFunction<X> composeFromInt(
            @Nonnull final ThrowableIntToByteFunction<? extends X> before1,
            @Nonnull final ThrowableIntToByteFunction<? extends X> before2,
            @Nonnull final ThrowableIntToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                                                             before2.applyAsByteThrows(value2),
                                                             before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToByteFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriLongToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongToByteFunction<X> composeFromLong(
            @Nonnull final ThrowableLongToByteFunction<? extends X> before1,
            @Nonnull final ThrowableLongToByteFunction<? extends X> before2,
            @Nonnull final ThrowableLongToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                                                             before2.applyAsByteThrows(value2),
                                                             before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriShortToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortToByteFunction<X> composeFromShort(
            @Nonnull final ThrowableShortToByteFunction<? extends X> before1,
            @Nonnull final ThrowableShortToByteFunction<? extends X> before2,
            @Nonnull final ThrowableShortToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                                                             before2.applyAsByteThrows(value2),
                                                             before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableTriByteFunction<S, X> andThen(
            @Nonnull final ThrowableByteFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyThrows(applyAsByteThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBytePredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code ThrowableTriBytePredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBytePredicate<X> andThenToBoolean(@Nonnull final ThrowableBytePredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.testThrows(applyAsByteThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableByteTernaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ThrowableByteTernaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteTernaryOperator<X> andThenToByte(
            @Nonnull final ThrowableByteUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByteThrows(applyAsByteThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriByteToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriByteToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableByteToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsCharThrows(applyAsByteThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriByteToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriByteToDoubleFunction<X> andThenToDouble(
            @Nonnull final ThrowableByteToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDoubleThrows(applyAsByteThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToFloatFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriByteToFloatFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriByteToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableByteToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloatThrows(applyAsByteThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriByteToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriByteToIntFunction<X> andThenToInt(
            @Nonnull final ThrowableByteToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsIntThrows(applyAsByteThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriByteToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriByteToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableByteToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLongThrows(applyAsByteThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToShortFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriByteToShortFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriByteToShortFunction<X> andThenToShort(
            @Nonnull final ThrowableByteToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShortThrows(applyAsByteThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteConsumer} that fist applies this operator to its input, and then
     * consumes the result using the given {@link ThrowableByteConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriByteConsumer} that first applies this operator to its input, and then
     * consumes the result using the given {@code ThrowableByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriByteConsumer<X> consume(@Nonnull final ThrowableByteConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.acceptThrows(applyAsByteThrows(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableByteTernaryOperator}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableByteTernaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableByteTernaryOperator<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Triple<Byte, Byte, Byte>, Byte> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableByteTernaryOperator<X> & Memoized) (value1, value2, value3) -> {
                final byte returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3), ThrowableFunction.of(
                            key -> applyAsByteThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTernaryOperator} which represents this {@link ThrowableByteTernaryOperator}.
     * Thereby the primitive input argument for this operator is autoboxed. This method provides the possibility to use
     * this {@code ThrowableByteTernaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTernaryOperator} which represents this {@code ThrowableByteTernaryOperator}.
     */
    @Nonnull
    default ThrowableTernaryOperator<Byte, X> boxed() {
        return this::applyAsByteThrows;
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that applies this operator to its input and nests the thrown
     * {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link ByteTernaryOperator} that applies this operator to its input and nests the thrown
     * {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ByteTernaryOperator nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that applies this operator to its input and nests the thrown
     * {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ByteTernaryOperator} that applies this operator to its input and nests the thrown
     * {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ByteTernaryOperator nest(@Nonnull final Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * operator.
     *
     * @param recover The operation to apply if this operator throws a {@code Throwable}
     * @return A composed {@link ByteTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing operator is {@code null}
     * @implSpec The implementation checks that the returned enclosing operator from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ByteTernaryOperator recover(
            @Nonnull final Function<? super Throwable, ? extends ByteTernaryOperator> recover) {
        Objects.requireNonNull(recover);
        return (value1, value2, value3) -> {
            try {
                return this.applyAsByteThrows(value1, value2, value3);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                final ByteTernaryOperator operator = recover.apply(throwable);
                Objects.requireNonNull(operator, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return operator.applyAsByte(value1, value2, value3);
            }
        };
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed operator behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this operator in the returned composed
     * operator by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing operator.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing operator variant of this throwable operator, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed operator. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed operator. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed operator is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed operator, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed operator.
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
     * @return A composed {@link ByteTernaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ByteTernaryOperator sneakyThrow() {
        return (value1, value2, value3) -> {
            try {
                return this.applyAsByteThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}