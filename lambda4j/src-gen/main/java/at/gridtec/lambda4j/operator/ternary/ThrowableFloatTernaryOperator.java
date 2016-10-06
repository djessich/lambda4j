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
package at.gridtec.lambda4j.operator.ternary;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableFloatConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriFloatConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableFloatFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableByteToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToIntFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToLongFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableLongToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToFloatFunction;
import at.gridtec.lambda4j.function.to.ThrowableToFloatFunction;
import at.gridtec.lambda4j.function.tri.ThrowableTriFloatFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriBooleanToFloatFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriByteToFloatFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriCharToFloatFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriDoubleToFloatFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriFloatToByteFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriFloatToCharFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriFloatToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriFloatToIntFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriFloatToLongFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriFloatToShortFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriIntToFloatFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriLongToFloatFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriShortToFloatFunction;
import at.gridtec.lambda4j.function.tri.to.ThrowableToFloatTriFunction;
import at.gridtec.lambda4j.operator.unary.ThrowableFloatUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableFloatPredicate;
import at.gridtec.lambda4j.predicate.tri.ThrowableTriFloatPredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an operation that accepts three {@code float}-valued input arguments and produces a {@code float}-valued
 * result which is able to throw any {@link Throwable}. This is a primitive specialization of {@link
 * ThrowableTernaryOperator}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloatThrows(float, float, float)}.
 *
 * @param <X> The type of the throwable to be thrown by this operator
 * @see ThrowableTernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableFloatTernaryOperator<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableFloatTernaryOperator} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableFloatTernaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableFloatTernaryOperator<X> of(
            @Nullable final ThrowableFloatTernaryOperator<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableFloatTernaryOperator} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @param value3 The third argument to the operator
     * @return The result from the given {@code ThrowableFloatTernaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this operators action
     */
    static <X extends Throwable> float call(@Nonnull final ThrowableFloatTernaryOperator<? extends X> operator,
            float value1, float value2, float value3) throws X {
        Objects.requireNonNull(operator);
        return operator.applyAsFloatThrows(value1, value2, value3);
    }

    /**
     * Creates a {@link ThrowableFloatTernaryOperator} which uses the {@code first} parameter of this one as argument
     * for the given {@link ThrowableFloatUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableFloatTernaryOperator} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableFloatUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableFloatTernaryOperator<X> onlyFirst(
            @Nonnull final ThrowableFloatUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsFloatThrows(value1);
    }

    /**
     * Creates a {@link ThrowableFloatTernaryOperator} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableFloatUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableFloatTernaryOperator} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableFloatUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableFloatTernaryOperator<X> onlySecond(
            @Nonnull final ThrowableFloatUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsFloatThrows(value2);
    }

    /**
     * Creates a {@link ThrowableFloatTernaryOperator} which uses the {@code third} parameter of this one as argument
     * for the given {@link ThrowableFloatUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableFloatTernaryOperator} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableFloatUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableFloatTernaryOperator<X> onlyThird(
            @Nonnull final ThrowableFloatUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsFloatThrows(value3);
    }

    /**
     * Creates a {@link ThrowableFloatTernaryOperator} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param ret The return value for the constant
     * @return A {@code ThrowableFloatTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableFloatTernaryOperator<X> constant(float ret) {
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
    float applyAsFloatThrows(float value1, float value2, float value3) throws X;

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
     * Returns a composed {@link ThrowableToFloatTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableToFloatTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToFloatTriFunction<A, B, C, X> compose(
            @Nonnull final ThrowableToFloatFunction<? super A, ? extends X> before1,
            @Nonnull final ThrowableToFloatFunction<? super B, ? extends X> before2,
            @Nonnull final ThrowableToFloatFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsFloatThrows(before1.applyAsFloatThrows(a), before2.applyAsFloatThrows(b),
                                               before3.applyAsFloatThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriBooleanToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToFloatFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToFloatFunction<? extends X> before1,
            @Nonnull final ThrowableBooleanToFloatFunction<? extends X> before2,
            @Nonnull final ThrowableBooleanToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyAsFloatThrows(value1),
                                                              before2.applyAsFloatThrows(value2),
                                                              before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriByteToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToFloatFunction<X> composeFromByte(
            @Nonnull final ThrowableByteToFloatFunction<? extends X> before1,
            @Nonnull final ThrowableByteToFloatFunction<? extends X> before2,
            @Nonnull final ThrowableByteToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyAsFloatThrows(value1),
                                                              before2.applyAsFloatThrows(value2),
                                                              before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriCharToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharToFloatFunction<X> composeFromChar(
            @Nonnull final ThrowableCharToFloatFunction<? extends X> before1,
            @Nonnull final ThrowableCharToFloatFunction<? extends X> before2,
            @Nonnull final ThrowableCharToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyAsFloatThrows(value1),
                                                              before2.applyAsFloatThrows(value2),
                                                              before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriDoubleToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleToFloatFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoubleToFloatFunction<? extends X> before1,
            @Nonnull final ThrowableDoubleToFloatFunction<? extends X> before2,
            @Nonnull final ThrowableDoubleToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyAsFloatThrows(value1),
                                                              before2.applyAsFloatThrows(value2),
                                                              before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableFloatTernaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@code ThrowableFloatTernaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatTernaryOperator<X> composeFromFloat(
            @Nonnull final ThrowableFloatUnaryOperator<? extends X> before1,
            @Nonnull final ThrowableFloatUnaryOperator<? extends X> before2,
            @Nonnull final ThrowableFloatUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyAsFloatThrows(value1),
                                                              before2.applyAsFloatThrows(value2),
                                                              before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriIntToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToFloatFunction<X> composeFromInt(
            @Nonnull final ThrowableIntToFloatFunction<? extends X> before1,
            @Nonnull final ThrowableIntToFloatFunction<? extends X> before2,
            @Nonnull final ThrowableIntToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyAsFloatThrows(value1),
                                                              before2.applyAsFloatThrows(value2),
                                                              before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriLongToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongToFloatFunction<X> composeFromLong(
            @Nonnull final ThrowableLongToFloatFunction<? extends X> before1,
            @Nonnull final ThrowableLongToFloatFunction<? extends X> before2,
            @Nonnull final ThrowableLongToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyAsFloatThrows(value1),
                                                              before2.applyAsFloatThrows(value2),
                                                              before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriShortToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortToFloatFunction<X> composeFromShort(
            @Nonnull final ThrowableShortToFloatFunction<? extends X> before1,
            @Nonnull final ThrowableShortToFloatFunction<? extends X> before2,
            @Nonnull final ThrowableShortToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyAsFloatThrows(value1),
                                                              before2.applyAsFloatThrows(value2),
                                                              before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableTriFloatFunction<S, X> andThen(
            @Nonnull final ThrowableFloatFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyThrows(applyAsFloatThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatPredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code ThrowableTriFloatPredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriFloatPredicate<X> andThenToBoolean(@Nonnull final ThrowableFloatPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.testThrows(applyAsFloatThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToByteFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriFloatToByteFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriFloatToByteFunction<X> andThenToByte(
            @Nonnull final ThrowableFloatToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByteThrows(applyAsFloatThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToCharFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriFloatToCharFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriFloatToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableFloatToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsCharThrows(applyAsFloatThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriFloatToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriFloatToDoubleFunction<X> andThenToDouble(
            @Nonnull final ThrowableFloatToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDoubleThrows(applyAsFloatThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableFloatTernaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ThrowableFloatTernaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatTernaryOperator<X> andThenToFloat(
            @Nonnull final ThrowableFloatUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloatThrows(applyAsFloatThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriFloatToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriFloatToIntFunction<X> andThenToInt(
            @Nonnull final ThrowableFloatToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsIntThrows(applyAsFloatThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToLongFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriFloatToLongFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriFloatToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableFloatToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLongThrows(applyAsFloatThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToShortFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriFloatToShortFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriFloatToShortFunction<X> andThenToShort(
            @Nonnull final ThrowableFloatToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShortThrows(applyAsFloatThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatConsumer} that fist applies this operator to its input, and then
     * consumes the result using the given {@link ThrowableFloatConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriFloatConsumer} that first applies this operator to its input, and then
     * consumes the result using the given {@code ThrowableFloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriFloatConsumer<X> consume(@Nonnull final ThrowableFloatConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.acceptThrows(applyAsFloatThrows(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableFloatTernaryOperator}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableFloatTernaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableFloatTernaryOperator<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Triple<Float, Float, Float>, Float> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableFloatTernaryOperator<X> & Memoized) (value1, value2, value3) -> {
                final float returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3), ThrowableFunction.of(
                            key -> applyAsFloatThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTernaryOperator} which represents this {@link ThrowableFloatTernaryOperator}.
     * Thereby the primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code ThrowableTernaryOperator} which represents this {@code ThrowableFloatTernaryOperator}.
     */
    @Nonnull
    default ThrowableTernaryOperator<Float, X> boxed() {
        return this::applyAsFloatThrows;
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that applies this operator to its input and nests the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is
     * nested (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown
     * throwables message and the thrown throwable itself.
     *
     * @return A composed {@code FloatTernaryOperator} that applies this operator to its input and nests the thrown
     * {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default FloatTernaryOperator nest() {
        return (value1, value2, value3) -> {
            try {
                return this.applyAsFloatThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
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
     * @return A composed {@link FloatTernaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default FloatTernaryOperator sneakyThrow() {
        return (value1, value2, value3) -> {
            try {
                return this.applyAsFloatThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}