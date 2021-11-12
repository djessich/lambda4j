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
import org.lambda4j.consumer.ThrowableShortConsumer;
import org.lambda4j.consumer.tri.ThrowableTriShortConsumer;
import org.lambda4j.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.ThrowableShortFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
import org.lambda4j.function.conversion.ThrowableByteToShortFunction;
import org.lambda4j.function.conversion.ThrowableCharToShortFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToShortFunction;
import org.lambda4j.function.conversion.ThrowableFloatToShortFunction;
import org.lambda4j.function.conversion.ThrowableIntToShortFunction;
import org.lambda4j.function.conversion.ThrowableLongToShortFunction;
import org.lambda4j.function.conversion.ThrowableShortToByteFunction;
import org.lambda4j.function.conversion.ThrowableShortToCharFunction;
import org.lambda4j.function.conversion.ThrowableShortToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableShortToFloatFunction;
import org.lambda4j.function.conversion.ThrowableShortToIntFunction;
import org.lambda4j.function.conversion.ThrowableShortToLongFunction;
import org.lambda4j.function.to.ThrowableToShortFunction;
import org.lambda4j.function.tri.ThrowableTriShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriBooleanToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriCharToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriFloatToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriIntToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToByteFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToCharFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToFloatFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToIntFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToLongFunction;
import org.lambda4j.function.tri.to.ThrowableToShortTriFunction;
import org.lambda4j.operator.binary.ThrowableShortBinaryOperator;
import org.lambda4j.operator.unary.ThrowableShortUnaryOperator;
import org.lambda4j.predicate.ThrowableShortPredicate;
import org.lambda4j.predicate.tri.ThrowableTriShortPredicate;
import org.lambda4j.util.ThrowableUtils;

/**
 * Represents an operation that accepts three {@code short}-valued input arguments and produces a {@code short}-valued
 * result which is able to throw any {@link Throwable}. This is a primitive specialization of {@link
 * ThrowableTernaryOperator}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShortThrows(short, short, short)}.
 *
 * @param <X> The type of the throwable to be thrown by this operator
 * @see ThrowableTernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableShortTernaryOperator<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableShortTernaryOperator} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableShortTernaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <X extends Throwable> ThrowableShortTernaryOperator<X> of(
            @Nullable ThrowableShortTernaryOperator<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableShortTernaryOperator} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @param value3 The third argument to the operator
     * @return The result from the given {@code ThrowableShortTernaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this operators action
     */
    static <X extends Throwable> short call(@Nonnull ThrowableShortTernaryOperator<? extends X> operator,
            short value1, short value2, short value3) throws X {
        Objects.requireNonNull(operator);
        return operator.applyAsShortThrows(value1, value2, value3);
    }

    /**
     * Creates a {@link ThrowableShortTernaryOperator} which uses the {@code first} parameter of this one as argument
     * for the given {@link ThrowableShortUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableShortTernaryOperator} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableShortUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableShortTernaryOperator<X> onlyFirst(
            @Nonnull ThrowableShortUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsShortThrows(value1);
    }

    /**
     * Creates a {@link ThrowableShortTernaryOperator} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableShortUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableShortTernaryOperator} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableShortUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableShortTernaryOperator<X> onlySecond(
            @Nonnull ThrowableShortUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsShortThrows(value2);
    }

    /**
     * Creates a {@link ThrowableShortTernaryOperator} which uses the {@code third} parameter of this one as argument
     * for the given {@link ThrowableShortUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableShortTernaryOperator} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableShortUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableShortTernaryOperator<X> onlyThird(
            @Nonnull ThrowableShortUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsShortThrows(value3);
    }

    /**
     * Creates a {@link ThrowableShortTernaryOperator} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param ret The return value for the constant
     * @return A {@code ThrowableShortTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableShortTernaryOperator<X> constant(short ret) {
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
    short applyAsShortThrows(short value1, short value2, short value3) throws X;

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowableShortBinaryOperator}
     * as result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @return A {@code ThrowableShortBinaryOperator} that represents this operator partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableShortBinaryOperator<X> applyAsShortThrowsPartially(short value1) {
        return (value2, value3) -> applyAsShortThrows(value1, value2, value3);
    }

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowableShortUnaryOperator} as
     * result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @param value2 The second argument to this operator used to partially apply this function
     * @return A {@code ThrowableShortUnaryOperator} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default ThrowableShortUnaryOperator<X> applyAsShortThrowsPartially(short value1, short value2) {
        return value3 -> applyAsShortThrows(value1, value2, value3);
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
     * Returns a composed {@link ThrowableToShortTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableToShortTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToShortTriFunction<A, B, C, X> compose(
            @Nonnull ThrowableToShortFunction<? super A, ? extends X> before1,
            @Nonnull ThrowableToShortFunction<? super B, ? extends X> before2,
            @Nonnull ThrowableToShortFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsShortThrows(before1.applyAsShortThrows(a), before2.applyAsShortThrows(b),
                before3.applyAsShortThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriBooleanToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToShortFunction<X> composeFromBoolean(
            @Nonnull ThrowableBooleanToShortFunction<? extends X> before1,
            @Nonnull ThrowableBooleanToShortFunction<? extends X> before2,
            @Nonnull ThrowableBooleanToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                before2.applyAsShortThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriByteToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToShortFunction<X> composeFromByte(
            @Nonnull ThrowableByteToShortFunction<? extends X> before1,
            @Nonnull ThrowableByteToShortFunction<? extends X> before2,
            @Nonnull ThrowableByteToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                before2.applyAsShortThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriCharToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharToShortFunction<X> composeFromChar(
            @Nonnull ThrowableCharToShortFunction<? extends X> before1,
            @Nonnull ThrowableCharToShortFunction<? extends X> before2,
            @Nonnull ThrowableCharToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                before2.applyAsShortThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriDoubleToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleToShortFunction<X> composeFromDouble(
            @Nonnull ThrowableDoubleToShortFunction<? extends X> before1,
            @Nonnull ThrowableDoubleToShortFunction<? extends X> before2,
            @Nonnull ThrowableDoubleToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                before2.applyAsShortThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriFloatToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatToShortFunction<X> composeFromFloat(
            @Nonnull ThrowableFloatToShortFunction<? extends X> before1,
            @Nonnull ThrowableFloatToShortFunction<? extends X> before2,
            @Nonnull ThrowableFloatToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                before2.applyAsShortThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriIntToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToShortFunction<X> composeFromInt(
            @Nonnull ThrowableIntToShortFunction<? extends X> before1,
            @Nonnull ThrowableIntToShortFunction<? extends X> before2,
            @Nonnull ThrowableIntToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                before2.applyAsShortThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriLongToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongToShortFunction<X> composeFromLong(
            @Nonnull ThrowableLongToShortFunction<? extends X> before1,
            @Nonnull ThrowableLongToShortFunction<? extends X> before2,
            @Nonnull ThrowableLongToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                before2.applyAsShortThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableShortTernaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@code ThrowableShortTernaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortTernaryOperator<X> composeFromShort(
            @Nonnull ThrowableShortUnaryOperator<? extends X> before1,
            @Nonnull ThrowableShortUnaryOperator<? extends X> before2,
            @Nonnull ThrowableShortUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                before2.applyAsShortThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableTriShortFunction<S, X> andThen(
            @Nonnull ThrowableShortFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyThrows(applyAsShortThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortPredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code ThrowableTriShortPredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriShortPredicate<X> andThenToBoolean(@Nonnull ThrowableShortPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.testThrows(applyAsShortThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToByteFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriShortToByteFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriShortToByteFunction<X> andThenToByte(
            @Nonnull ThrowableShortToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByteThrows(applyAsShortThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToCharFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriShortToCharFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriShortToCharFunction<X> andThenToChar(
            @Nonnull ThrowableShortToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsCharThrows(applyAsShortThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriShortToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriShortToDoubleFunction<X> andThenToDouble(
            @Nonnull ThrowableShortToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDoubleThrows(applyAsShortThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToFloatFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriShortToFloatFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriShortToFloatFunction<X> andThenToFloat(
            @Nonnull ThrowableShortToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloatThrows(applyAsShortThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriShortToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriShortToIntFunction<X> andThenToInt(
            @Nonnull ThrowableShortToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsIntThrows(applyAsShortThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToLongFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriShortToLongFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriShortToLongFunction<X> andThenToLong(
            @Nonnull ThrowableShortToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLongThrows(applyAsShortThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableShortTernaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ThrowableShortTernaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortTernaryOperator<X> andThenToShort(
            @Nonnull ThrowableShortUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShortThrows(applyAsShortThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortConsumer} that fist applies this operator to its input, and then
     * consumes the result using the given {@link ThrowableShortConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriShortConsumer} that first applies this operator to its input, and then
     * consumes the result using the given {@code ThrowableShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriShortConsumer<X> consume(@Nonnull ThrowableShortConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.acceptThrows(applyAsShortThrows(value1, value2, value3));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ThrowableShortTernaryOperator<X> reversed() {
        return (value3, value2, value1) -> applyAsShortThrows(value1, value2, value3);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableShortTernaryOperator}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableShortTernaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableShortTernaryOperator<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Short, Short, Short>, Short> cache = new ConcurrentHashMap<>();
            return (ThrowableShortTernaryOperator<X> & Memoized) (value1, value2, value3) -> {
                return cache.computeIfAbsent(Triple.of(value1, value2, value3), ThrowableFunction.of(
                        key -> applyAsShortThrows(key.getLeft(), key.getMiddle(), key.getRight())));
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTernaryOperator} which represents this {@link ThrowableShortTernaryOperator}.
     * Thereby the primitive input argument for this operator is autoboxed. This method provides the possibility to use
     * this {@code ThrowableShortTernaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTernaryOperator} which represents this {@code ThrowableShortTernaryOperator}.
     */
    @Nonnull
    default ThrowableTernaryOperator<Short, X> boxed() {
        return this::applyAsShortThrows;
    }

    /**
     * Returns a composed {@link ShortTernaryOperator} that applies this operator to its input and nests the thrown
     * {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link ShortTernaryOperator} that applies this operator to its input and nests the thrown
     * {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ShortTernaryOperator nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ShortTernaryOperator} that applies this operator to its input and nests the thrown
     * {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ShortTernaryOperator} that applies this operator to its input and nests the thrown
     * {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ShortTernaryOperator nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        Objects.requireNonNull(mapper);
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ShortTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * operator.
     *
     * @param recover The operation to apply if this operator throws a {@code Throwable}
     * @return A composed {@link ShortTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing operator is {@code null}
     * @implSpec The implementation checks that the returned enclosing operator from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ShortTernaryOperator recover(
            @Nonnull Function<? super Throwable, ? extends ShortTernaryOperator> recover) {
        Objects.requireNonNull(recover);
        return (value1, value2, value3) -> {
            try {
                return applyAsShortThrows(value1, value2, value3);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                ShortTernaryOperator operator = recover.apply(throwable);
                Objects.requireNonNull(operator, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return operator.applyAsShort(value1, value2, value3);
            }
        };
    }

    /**
     * Returns a composed {@link ShortTernaryOperator} that applies this operator to its input and sneakily throws the
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
     * @return A composed {@link ShortTernaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ShortTernaryOperator sneakyThrow() {
        return (value1, value2, value3) -> {
            try {
                return applyAsShortThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
