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

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableDoubleConsumer;
import org.lambda4j.consumer.tri.ThrowableTriDoubleConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableDoubleFunction;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableByteToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableCharToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToByteFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToCharFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToFloatFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToIntFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToLongFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToShortFunction;
import org.lambda4j.function.conversion.ThrowableFloatToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableIntToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableLongToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableShortToDoubleFunction;
import org.lambda4j.function.to.ThrowableToDoubleFunction;
import org.lambda4j.function.tri.ThrowableTriDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriBooleanToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriCharToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToByteFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToCharFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToFloatFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToIntFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToLongFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriFloatToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriIntToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToDoubleFunction;
import org.lambda4j.function.tri.to.ThrowableToDoubleTriFunction;
import org.lambda4j.operator.binary.ThrowableDoubleBinaryOperator;
import org.lambda4j.operator.unary.ThrowableDoubleUnaryOperator;
import org.lambda4j.predicate.ThrowableDoublePredicate;
import org.lambda4j.predicate.tri.ThrowableTriDoublePredicate;

/**
 * Represents an operation that accepts three {@code double}-valued input arguments and produces a {@code double}-valued
 * result which is able to throw any {@link Throwable}. This is a primitive specialization of {@link
 * ThrowableTernaryOperator}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDoubleThrows(double, double,
 * double)}.
 *
 * @param <X> The type of the throwable to be thrown by this operator
 * @see ThrowableTernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableDoubleTernaryOperator<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableDoubleTernaryOperator} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableDoubleTernaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <X extends Throwable> ThrowableDoubleTernaryOperator<X> of(
            @Nullable ThrowableDoubleTernaryOperator<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableDoubleTernaryOperator} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @param value3 The third argument to the operator
     * @return The result from the given {@code ThrowableDoubleTernaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this operators action
     */
    static <X extends Throwable> double call(@Nonnull ThrowableDoubleTernaryOperator<? extends X> operator,
            double value1, double value2, double value3) throws X {
        Objects.requireNonNull(operator);
        return operator.applyAsDoubleThrows(value1, value2, value3);
    }

    /**
     * Creates a {@link ThrowableDoubleTernaryOperator} which uses the {@code first} parameter of this one as argument
     * for the given {@link ThrowableDoubleUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableDoubleTernaryOperator} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableDoubleUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableDoubleTernaryOperator<X> onlyFirst(
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsDoubleThrows(value1);
    }

    /**
     * Creates a {@link ThrowableDoubleTernaryOperator} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableDoubleUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableDoubleTernaryOperator} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableDoubleUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableDoubleTernaryOperator<X> onlySecond(
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsDoubleThrows(value2);
    }

    /**
     * Creates a {@link ThrowableDoubleTernaryOperator} which uses the {@code third} parameter of this one as argument
     * for the given {@link ThrowableDoubleUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableDoubleTernaryOperator} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableDoubleUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableDoubleTernaryOperator<X> onlyThird(
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsDoubleThrows(value3);
    }

    /**
     * Creates a {@link ThrowableDoubleTernaryOperator} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param ret The return value for the constant
     * @return A {@code ThrowableDoubleTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableDoubleTernaryOperator<X> constant(double ret) {
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
    double applyAsDoubleThrows(double value1, double value2, double value3) throws X;

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowableDoubleBinaryOperator}
     * as result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @return A {@code ThrowableDoubleBinaryOperator} that represents this operator partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableDoubleBinaryOperator<X> applyAsDoubleThrowsPartially(double value1) {
        return (value2, value3) -> applyAsDoubleThrows(value1, value2, value3);
    }

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowableDoubleUnaryOperator}
     * as result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @param value2 The second argument to this operator used to partially apply this function
     * @return A {@code ThrowableDoubleUnaryOperator} that represents this operator partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableDoubleUnaryOperator<X> applyAsDoubleThrowsPartially(double value1, double value2) {
        return value3 -> applyAsDoubleThrows(value1, value2, value3);
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
     * Returns a composed {@link ThrowableToDoubleTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableToDoubleTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToDoubleTriFunction<A, B, C, X> compose(
            @Nonnull ThrowableToDoubleFunction<? super A, ? extends X> before1,
            @Nonnull ThrowableToDoubleFunction<? super B, ? extends X> before2,
            @Nonnull ThrowableToDoubleFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsDoubleThrows(before1.applyAsDoubleThrows(a), before2.applyAsDoubleThrows(b),
                before3.applyAsDoubleThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriBooleanToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToDoubleFunction<X> composeFromBoolean(
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> before1,
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyAsDoubleThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriByteToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToDoubleFunction<X> composeFromByte(
            @Nonnull ThrowableByteToDoubleFunction<? extends X> before1,
            @Nonnull ThrowableByteToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableByteToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyAsDoubleThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriCharToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharToDoubleFunction<X> composeFromChar(
            @Nonnull ThrowableCharToDoubleFunction<? extends X> before1,
            @Nonnull ThrowableCharToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableCharToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyAsDoubleThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableDoubleTernaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@code ThrowableDoubleTernaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleTernaryOperator<X> composeFromDouble(
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> before1,
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> before2,
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyAsDoubleThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriFloatToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatToDoubleFunction<X> composeFromFloat(
            @Nonnull ThrowableFloatToDoubleFunction<? extends X> before1,
            @Nonnull ThrowableFloatToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableFloatToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyAsDoubleThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriIntToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToDoubleFunction<X> composeFromInt(
            @Nonnull ThrowableIntToDoubleFunction<? extends X> before1,
            @Nonnull ThrowableIntToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableIntToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyAsDoubleThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriLongToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongToDoubleFunction<X> composeFromLong(
            @Nonnull ThrowableLongToDoubleFunction<? extends X> before1,
            @Nonnull ThrowableLongToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableLongToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyAsDoubleThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriShortToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortToDoubleFunction<X> composeFromShort(
            @Nonnull ThrowableShortToDoubleFunction<? extends X> before1,
            @Nonnull ThrowableShortToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableShortToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyAsDoubleThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableTriDoubleFunction<S, X> andThen(
            @Nonnull ThrowableDoubleFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyThrows(applyAsDoubleThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoublePredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code ThrowableTriDoublePredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriDoublePredicate<X> andThenToBoolean(
            @Nonnull ThrowableDoublePredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.testThrows(applyAsDoubleThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToByteFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriDoubleToByteFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriDoubleToByteFunction<X> andThenToByte(
            @Nonnull ThrowableDoubleToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByteThrows(applyAsDoubleThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToCharFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriDoubleToCharFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriDoubleToCharFunction<X> andThenToChar(
            @Nonnull ThrowableDoubleToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsCharThrows(applyAsDoubleThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableDoubleTernaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ThrowableDoubleTernaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleTernaryOperator<X> andThenToDouble(
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDoubleThrows(applyAsDoubleThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToFloatFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriDoubleToFloatFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriDoubleToFloatFunction<X> andThenToFloat(
            @Nonnull ThrowableDoubleToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloatThrows(applyAsDoubleThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToIntFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriDoubleToIntFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriDoubleToIntFunction<X> andThenToInt(
            @Nonnull ThrowableDoubleToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsIntThrows(applyAsDoubleThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToLongFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriDoubleToLongFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriDoubleToLongFunction<X> andThenToLong(
            @Nonnull ThrowableDoubleToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLongThrows(applyAsDoubleThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToShortFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriDoubleToShortFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriDoubleToShortFunction<X> andThenToShort(
            @Nonnull ThrowableDoubleToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShortThrows(applyAsDoubleThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleConsumer} that fist applies this operator to its input, and then
     * consumes the result using the given {@link ThrowableDoubleConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriDoubleConsumer} that first applies this operator to its input, and then
     * consumes the result using the given {@code ThrowableDoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriDoubleConsumer<X> consume(@Nonnull ThrowableDoubleConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.acceptThrows(applyAsDoubleThrows(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableDoubleTernaryOperator}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableDoubleTernaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableDoubleTernaryOperator<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Double, Double, Double>, Double> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ThrowableDoubleTernaryOperator<X> & Memoized) (value1, value2, value3) -> {
                double returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3), ThrowableFunction.of(
                            key -> applyAsDoubleThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTernaryOperator} which represents this {@link ThrowableDoubleTernaryOperator}.
     * Thereby the primitive input argument for this operator is autoboxed. This method provides the possibility to use
     * this {@code ThrowableDoubleTernaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTernaryOperator} which represents this {@code ThrowableDoubleTernaryOperator}.
     */
    @Nonnull
    default ThrowableTernaryOperator<Double, X> boxed() {
        return this::applyAsDoubleThrows;
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that applies this operator to its input and nests the thrown
     * {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link DoubleTernaryOperator} that applies this operator to its input and nests the thrown
     * {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default DoubleTernaryOperator nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that applies this operator to its input and nests the thrown
     * {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link DoubleTernaryOperator} that applies this operator to its input and nests the thrown
     * {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default DoubleTernaryOperator nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * operator.
     *
     * @param recover The operation to apply if this operator throws a {@code Throwable}
     * @return A composed {@link DoubleTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing operator is {@code null}
     * @implSpec The implementation checks that the returned enclosing operator from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default DoubleTernaryOperator recover(
            @Nonnull Function<? super Throwable, ? extends DoubleTernaryOperator> recover) {
        Objects.requireNonNull(recover);
        return (value1, value2, value3) -> {
            try {
                return applyAsDoubleThrows(value1, value2, value3);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                DoubleTernaryOperator operator = recover.apply(throwable);
                Objects.requireNonNull(operator, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return operator.applyAsDouble(value1, value2, value3);
            }
        };
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that applies this operator to its input and sneakily throws the
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
     * @return A composed {@link DoubleTernaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default DoubleTernaryOperator sneakyThrow() {
        return (value1, value2, value3) -> {
            try {
                return applyAsDoubleThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
