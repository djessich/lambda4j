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
import org.lambda4j.consumer.ThrowableLongConsumer;
import org.lambda4j.consumer.tri.ThrowableTriBooleanConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.ThrowableLongFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiBooleanToLongFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import org.lambda4j.function.conversion.ThrowableLongToByteFunction;
import org.lambda4j.function.conversion.ThrowableLongToCharFunction;
import org.lambda4j.function.conversion.ThrowableLongToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableLongToFloatFunction;
import org.lambda4j.function.conversion.ThrowableLongToIntFunction;
import org.lambda4j.function.conversion.ThrowableLongToShortFunction;
import org.lambda4j.function.tri.ThrowableTriBooleanFunction;
import org.lambda4j.function.tri.ThrowableTriFunction;
import org.lambda4j.function.tri.to.ThrowableToLongTriFunction;
import org.lambda4j.operator.ternary.ThrowableBooleanTernaryOperator;
import org.lambda4j.operator.ternary.ThrowableLongTernaryOperator;
import org.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import org.lambda4j.operator.unary.ThrowableLongUnaryOperator;
import org.lambda4j.predicate.ThrowableBytePredicate;
import org.lambda4j.predicate.ThrowableCharPredicate;
import org.lambda4j.predicate.ThrowableDoublePredicate;
import org.lambda4j.predicate.ThrowableFloatPredicate;
import org.lambda4j.predicate.ThrowableIntPredicate;
import org.lambda4j.predicate.ThrowableLongPredicate;
import org.lambda4j.predicate.ThrowablePredicate;
import org.lambda4j.predicate.ThrowableShortPredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents an operation that accepts three {@code boolean}-valued input arguments and produces a {@code long}-valued
 * result which is able to throw any {@link Throwable}. This is a primitive specialization of {@link
 * ThrowableTriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLongThrows(boolean, boolean, *
 * boolean)}.
 *
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableTriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableTriBooleanToLongFunction<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableTriBooleanToLongFunction} based on a lambda expression or a method reference.
     * Thereby the given lambda expression or method reference is returned on an as-is basis to implicitly transform it
     * to the desired type. With this method, it is possible to ensure that correct type is used from lambda expression
     * or method reference.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableTriBooleanToLongFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableTriBooleanToLongFunction<X> of(
            @Nullable final ThrowableTriBooleanToLongFunction<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableTriBooleanToLongFunction} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code ThrowableTriBooleanToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <X extends Throwable> long call(@Nonnull final ThrowableTriBooleanToLongFunction<? extends X> function,
            boolean value1, boolean value2, boolean value3) throws X {
        Objects.requireNonNull(function);
        return function.applyAsLongThrows(value1, value2, value3);
    }

    /**
     * Creates a {@link ThrowableTriBooleanToLongFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@link ThrowableBooleanToLongFunction}.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableTriBooleanToLongFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableBooleanToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBooleanToLongFunction<X> onlyFirst(
            @Nonnull final ThrowableBooleanToLongFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsLongThrows(value1);
    }

    /**
     * Creates a {@link ThrowableTriBooleanToLongFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@link ThrowableBooleanToLongFunction}.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableTriBooleanToLongFunction} which uses the {@code second} parameter of this one
     * as argument for the given {@code ThrowableBooleanToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBooleanToLongFunction<X> onlySecond(
            @Nonnull final ThrowableBooleanToLongFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsLongThrows(value2);
    }

    /**
     * Creates a {@link ThrowableTriBooleanToLongFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@link ThrowableBooleanToLongFunction}.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableTriBooleanToLongFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableBooleanToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBooleanToLongFunction<X> onlyThird(
            @Nonnull final ThrowableBooleanToLongFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsLongThrows(value3);
    }

    /**
     * Creates a {@link ThrowableTriBooleanToLongFunction} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableTriBooleanToLongFunction} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBooleanToLongFunction<X> constant(long ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    long applyAsLongThrows(boolean value1, boolean value2, boolean value3) throws X;

    /**
     * Applies this function partially to some arguments of this one, producing a {@link
     * ThrowableBiBooleanToLongFunction} as result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @return A {@code ThrowableBiBooleanToLongFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableBiBooleanToLongFunction<X> papplyAsLongThrows(boolean value1) {
        return (value2, value3) -> this.applyAsLongThrows(value1, value2, value3);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableBooleanToLongFunction}
     * as result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @param value2 The second argument to this function used to partially apply this function
     * @return A {@code ThrowableBooleanToLongFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableBooleanToLongFunction<X> papplyAsLongThrows(boolean value1, boolean value2) {
        return (value3) -> this.applyAsLongThrows(value1, value2, value3);
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
     * Returns a composed {@link ThrowableToLongTriFunction} that first applies the {@code before} predicates to its
     * input, and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given predicate, and of composed function
     * @param <B> The type of the argument to the second given predicate, and of composed function
     * @param <C> The type of the argument to the third given predicate, and of composed function
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableToLongTriFunction} that first applies the {@code before} predicates to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToLongTriFunction<A, B, C, X> compose(
            @Nonnull final ThrowablePredicate<? super A, ? extends X> before1,
            @Nonnull final ThrowablePredicate<? super B, ? extends X> before2,
            @Nonnull final ThrowablePredicate<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsLongThrows(before1.testThrows(a), before2.testThrows(b), before3.testThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToLongFunction} that first applies the {@code before} operators to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code ThrowableTriBooleanToLongFunction} that first applies the {@code before} operators to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToLongFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> before1,
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> before2,
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.applyAsBooleanThrows(value1),
                                                             before2.applyAsBooleanThrows(value2),
                                                             before3.applyAsBooleanThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToLongFunction} that first applies the {@code before} predicates to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriByteToLongFunction} that first applies the {@code before} predicates to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToLongFunction<X> composeFromByte(
            @Nonnull final ThrowableBytePredicate<? extends X> before1,
            @Nonnull final ThrowableBytePredicate<? extends X> before2,
            @Nonnull final ThrowableBytePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.testThrows(value1), before2.testThrows(value2),
                                                             before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToLongFunction} that first applies the {@code before} predicates to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriCharToLongFunction} that first applies the {@code before} predicates to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharToLongFunction<X> composeFromChar(
            @Nonnull final ThrowableCharPredicate<? extends X> before1,
            @Nonnull final ThrowableCharPredicate<? extends X> before2,
            @Nonnull final ThrowableCharPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.testThrows(value1), before2.testThrows(value2),
                                                             before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToLongFunction} that first applies the {@code before} predicates to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriDoubleToLongFunction} that first applies the {@code before} predicates to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleToLongFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoublePredicate<? extends X> before1,
            @Nonnull final ThrowableDoublePredicate<? extends X> before2,
            @Nonnull final ThrowableDoublePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.testThrows(value1), before2.testThrows(value2),
                                                             before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToLongFunction} that first applies the {@code before} predicates to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriFloatToLongFunction} that first applies the {@code before} predicates to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatToLongFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatPredicate<? extends X> before1,
            @Nonnull final ThrowableFloatPredicate<? extends X> before2,
            @Nonnull final ThrowableFloatPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.testThrows(value1), before2.testThrows(value2),
                                                             before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToLongFunction} that first applies the {@code before} predicates to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriIntToLongFunction} that first applies the {@code before} predicates to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToLongFunction<X> composeFromInt(@Nonnull final ThrowableIntPredicate<? extends X> before1,
            @Nonnull final ThrowableIntPredicate<? extends X> before2,
            @Nonnull final ThrowableIntPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.testThrows(value1), before2.testThrows(value2),
                                                             before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableLongTernaryOperator} that first applies the {@code before} predicates to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableLongTernaryOperator} that first applies the {@code before} predicates to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongTernaryOperator<X> composeFromLong(@Nonnull final ThrowableLongPredicate<? extends X> before1,
            @Nonnull final ThrowableLongPredicate<? extends X> before2,
            @Nonnull final ThrowableLongPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.testThrows(value1), before2.testThrows(value2),
                                                             before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToLongFunction} that first applies the {@code before} predicates to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriShortToLongFunction} that first applies the {@code before} predicates to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortToLongFunction<X> composeFromShort(
            @Nonnull final ThrowableShortPredicate<? extends X> before1,
            @Nonnull final ThrowableShortPredicate<? extends X> before2,
            @Nonnull final ThrowableShortPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.testThrows(value1), before2.testThrows(value2),
                                                             before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableTriBooleanFunction<S, X> andThen(
            @Nonnull final ThrowableLongFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyThrows(applyAsLongThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableBooleanTernaryOperator} that first applies this function to its input, and
     * then applies the {@code after} predicate to the result. This method is just convenience, to provide the ability
     * to transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableBooleanTernaryOperator} that first applies this function to its input, and
     * then applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanTernaryOperator<X> andThenToBoolean(
            @Nonnull final ThrowableLongPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.testThrows(applyAsLongThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriBooleanToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriBooleanToByteFunction<X> andThenToByte(
            @Nonnull final ThrowableLongToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByteThrows(applyAsLongThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriBooleanToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriBooleanToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableLongToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsCharThrows(applyAsLongThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriBooleanToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriBooleanToDoubleFunction<X> andThenToDouble(
            @Nonnull final ThrowableLongToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDoubleThrows(applyAsLongThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriBooleanToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriBooleanToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableLongToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloatThrows(applyAsLongThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToIntFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriBooleanToIntFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriBooleanToIntFunction<X> andThenToInt(
            @Nonnull final ThrowableLongToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsIntThrows(applyAsLongThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableTriBooleanToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriBooleanToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableLongUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLongThrows(applyAsLongThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriBooleanToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriBooleanToShortFunction<X> andThenToShort(
            @Nonnull final ThrowableLongToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShortThrows(applyAsLongThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableLongConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriBooleanConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableLongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriBooleanConsumer<X> consume(@Nonnull final ThrowableLongConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.acceptThrows(applyAsLongThrows(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableTriBooleanToLongFunction}. Whenever it is called,
     * the mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableTriBooleanToLongFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableTriBooleanToLongFunction<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Triple<Boolean, Boolean, Boolean>, Long> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableTriBooleanToLongFunction<X> & Memoized) (value1, value2, value3) -> {
                final long returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3), ThrowableFunction.of(
                            key -> applyAsLongThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} which represents this {@link ThrowableTriBooleanToLongFunction}.
     * Thereby the primitive input argument for this function is autoboxed. This method provides the possibility to use
     * this {@code ThrowableTriBooleanToLongFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTriFunction} which represents this {@code ThrowableTriBooleanToLongFunction}.
     */
    @Nonnull
    default ThrowableTriFunction<Boolean, Boolean, Boolean, Long, X> boxed() {
        return this::applyAsLongThrows;
    }

    /**
     * Returns a composed {@link TriBooleanToLongFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link TriBooleanToLongFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default TriBooleanToLongFunction nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link TriBooleanToLongFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link TriBooleanToLongFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default TriBooleanToLongFunction nest(
            @Nonnull final Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link TriBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover}
     * operation is represented by a curried operation which is called with throwable information and same arguments of
     * this function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link TriBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default TriBooleanToLongFunction recover(
            @Nonnull final Function<? super Throwable, ? extends TriBooleanToLongFunction> recover) {
        Objects.requireNonNull(recover);
        return (value1, value2, value3) -> {
            try {
                return this.applyAsLongThrows(value1, value2, value3);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                final TriBooleanToLongFunction function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsLong(value1, value2, value3);
            }
        };
    }

    /**
     * Returns a composed {@link TriBooleanToLongFunction} that applies this function to its input and sneakily throws
     * the thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means
     * that each throwable thrown from the returned composed function behaves exactly the same as an <em>unchecked</em>
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
     * @return A composed {@link TriBooleanToLongFunction} that applies this function to its input and sneakily throws
     * the thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default TriBooleanToLongFunction sneakyThrow() {
        return (value1, value2, value3) -> {
            try {
                return this.applyAsLongThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}