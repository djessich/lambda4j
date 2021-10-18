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

package org.lambda4j.predicate.bi;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableBooleanConsumer;
import org.lambda4j.consumer.bi.ThrowableBiFloatConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableBooleanFunction;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.bi.ThrowableBiFloatFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiFloatToByteFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiFloatToCharFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiFloatToDoubleFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiFloatToIntFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiFloatToLongFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiFloatToShortFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToByteFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToCharFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToFloatFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToIntFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
import org.lambda4j.function.conversion.ThrowableByteToFloatFunction;
import org.lambda4j.function.conversion.ThrowableCharToFloatFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToFloatFunction;
import org.lambda4j.function.conversion.ThrowableIntToFloatFunction;
import org.lambda4j.function.conversion.ThrowableLongToFloatFunction;
import org.lambda4j.function.conversion.ThrowableShortToFloatFunction;
import org.lambda4j.function.to.ThrowableToFloatFunction;
import org.lambda4j.operator.binary.ThrowableBooleanBinaryOperator;
import org.lambda4j.operator.binary.ThrowableFloatBinaryOperator;
import org.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import org.lambda4j.operator.unary.ThrowableFloatUnaryOperator;
import org.lambda4j.predicate.ThrowableFloatPredicate;

/**
 * Represents an predicate (boolean-valued function) of two {@code float}-valued input arguments which is able to throw
 * any {@link Throwable}. This is a primitive specialization of {@link ThrowableBiPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(float, float)}.
 *
 * @param <X> The type of the throwable to be thrown by this predicate
 * @see ThrowableBiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiFloatPredicate<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableBiFloatPredicate} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBiFloatPredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableBiFloatPredicate<X> of(
            @Nullable ThrowableBiFloatPredicate<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBiFloatPredicate} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code ThrowableBiFloatPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this predicates action
     */
    static <X extends Throwable> boolean call(@Nonnull ThrowableBiFloatPredicate<? extends X> predicate,
            float value1, float value2) throws X {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(value1, value2);
    }

    /**
     * Creates a {@link ThrowableBiFloatPredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowableFloatPredicate}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiFloatPredicate} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableFloatPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiFloatPredicate<X> onlyFirst(
            @Nonnull ThrowableFloatPredicate<? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2) -> predicate.testThrows(value1);
    }

    /**
     * Creates a {@link ThrowableBiFloatPredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@link ThrowableFloatPredicate}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiFloatPredicate} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableFloatPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiFloatPredicate<X> onlySecond(
            @Nonnull ThrowableFloatPredicate<? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2) -> predicate.testThrows(value2);
    }

    /**
     * Creates a {@link ThrowableBiFloatPredicate} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowableBiFloatPredicate} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiFloatPredicate<X> constant(boolean ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link ThrowableBiFloatPredicate} that always returns {@code true}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableBiFloatPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiFloatPredicate<X> alwaysTrue() {
        return (value1, value2) -> true;
    }

    /**
     * Returns a {@link ThrowableBiFloatPredicate} that always returns {@code false}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableBiFloatPredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiFloatPredicate<X> alwaysFalse() {
        return (value1, value2) -> false;
    }

    /**
     * Returns a {@link ThrowableBiFloatPredicate} that tests if the given arguments are <b>equal</b> to the ones of
     * this predicate.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowableBiFloatPredicate} that tests if the given arguments are <b>equal</b> to the ones of
     * this predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiFloatPredicate<X> isEqual(float target1, float target2) {
        return (value1, value2) -> value1 == target1 && value2 == target2;
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The return value from the predicate, which is its result.
     * @throws X Any throwable from this predicates action
     */
    boolean testThrows(float value1, float value2) throws X;

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link ThrowableFloatPredicate} as
     * result.
     *
     * @param value1 The first argument to this predicate used to partially apply this function
     * @return A {@code ThrowableFloatPredicate} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default ThrowableFloatPredicate<X> testThrowsPartially(float value1) {
        return value2 -> testThrows(value1, value2);
    }

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ThrowableBiPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed predicate
     * @param <B> The type of the argument to the second given function, and of composed predicate
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ThrowableBiPredicate<A, B, X> compose(
            @Nonnull ThrowableToFloatFunction<? super A, ? extends X> before1,
            @Nonnull ThrowableToFloatFunction<? super B, ? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> testThrows(before1.applyAsFloatThrows(a), before2.applyAsFloatThrows(b));
    }

    /**
     * Returns a composed {@link ThrowableBooleanBinaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBooleanBinaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanBinaryOperator<X> composeFromBoolean(
            @Nonnull ThrowableBooleanToFloatFunction<? extends X> before1,
            @Nonnull ThrowableBooleanToFloatFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsFloatThrows(value1), before2.applyAsFloatThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiBytePredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code byte} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiBytePredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiBytePredicate<X> composeFromByte(
            @Nonnull ThrowableByteToFloatFunction<? extends X> before1,
            @Nonnull ThrowableByteToFloatFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsFloatThrows(value1), before2.applyAsFloatThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiCharPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code char} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiCharPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiCharPredicate<X> composeFromChar(
            @Nonnull ThrowableCharToFloatFunction<? extends X> before1,
            @Nonnull ThrowableCharToFloatFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsFloatThrows(value1), before2.applyAsFloatThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoublePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiDoublePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiDoublePredicate<X> composeFromDouble(
            @Nonnull ThrowableDoubleToFloatFunction<? extends X> before1,
            @Nonnull ThrowableDoubleToFloatFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsFloatThrows(value1), before2.applyAsFloatThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatPredicate} that first applies the {@code before} operators to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive predicate is executed.
     *
     * @param before1 The first operator to apply before this predicate is applied
     * @param before2 The second operator to apply before this predicate is applied
     * @return A composed {@code ThrowableBiFloatPredicate} that first applies the {@code before} operators to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiFloatPredicate<X> composeFromFloat(
            @Nonnull ThrowableFloatUnaryOperator<? extends X> before1,
            @Nonnull ThrowableFloatUnaryOperator<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsFloatThrows(value1), before2.applyAsFloatThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiIntPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code int} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiIntPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiIntPredicate<X> composeFromInt(@Nonnull ThrowableIntToFloatFunction<? extends X> before1,
            @Nonnull ThrowableIntToFloatFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsFloatThrows(value1), before2.applyAsFloatThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiLongPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code long} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiLongPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiLongPredicate<X> composeFromLong(
            @Nonnull ThrowableLongToFloatFunction<? extends X> before1,
            @Nonnull ThrowableLongToFloatFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsFloatThrows(value1), before2.applyAsFloatThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiShortPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiShortPredicate<X> composeFromShort(
            @Nonnull ThrowableShortToFloatFunction<? extends X> before1,
            @Nonnull ThrowableShortToFloatFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsFloatThrows(value1), before2.applyAsFloatThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBiFloatFunction<S, X> andThen(
            @Nonnull ThrowableBooleanFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatPredicate} that first applies this predicate to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code ThrowableBiFloatPredicate} that first applies this predicate to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiFloatPredicate<X> andThenToBoolean(
            @Nonnull ThrowableBooleanUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsBooleanThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToByteFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiFloatToByteFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiFloatToByteFunction<X> andThenToByte(
            @Nonnull ThrowableBooleanToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByteThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToCharFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiFloatToCharFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiFloatToCharFunction<X> andThenToChar(
            @Nonnull ThrowableBooleanToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsCharThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToDoubleFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiFloatToDoubleFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiFloatToDoubleFunction<X> andThenToDouble(
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDoubleThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableFloatBinaryOperator} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableFloatBinaryOperator} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatBinaryOperator<X> andThenToFloat(
            @Nonnull ThrowableBooleanToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloatThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiFloatToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiFloatToIntFunction<X> andThenToInt(
            @Nonnull ThrowableBooleanToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsIntThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToLongFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiFloatToLongFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiFloatToLongFunction<X> andThenToLong(
            @Nonnull ThrowableBooleanToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLongThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToShortFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiFloatToShortFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiFloatToShortFunction<X> andThenToShort(
            @Nonnull ThrowableBooleanToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShortThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatConsumer} that fist applies this predicate to its input, and then
     * consumes the result using the given {@link ThrowableBooleanConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiFloatConsumer} that first applies this predicate to its input, and then
     * consumes the result using the given {@code ThrowableBooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiFloatConsumer<X> consume(@Nonnull ThrowableBooleanConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.acceptThrows(testThrows(value1, value2));
    }

    /**
     * Returns a {@link ThrowableBiFloatPredicate} that represents the logical negation of this one.
     *
     * @return A {@code ThrowableBiFloatPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default ThrowableBiFloatPredicate<X> negate() {
        return (value1, value2) -> !testThrows(value1, value2);
    }

    /**
     * Returns a composed {@link ThrowableBiFloatPredicate} that represents a short-circuiting logical AND of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code false}, then the
     * {@code other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableBiFloatPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableBiFloatPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ThrowableBiFloatPredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(ThrowableBiFloatPredicate)
     * @see #xor(ThrowableBiFloatPredicate)
     */
    @Nonnull
    default ThrowableBiFloatPredicate<X> and(@Nonnull ThrowableBiFloatPredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> testThrows(value1, value2) && other.testThrows(value1, value2);
    }

    /**
     * Returns a composed {@link ThrowableBiFloatPredicate} that represents a short-circuiting logical OR of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code
     * other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableBiFloatPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableBiFloatPredicate} that will be logically-ORed with this one
     * @return A composed {@code ThrowableBiFloatPredicate} that represents the short-circuiting logical OR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableBiFloatPredicate)
     * @see #xor(ThrowableBiFloatPredicate)
     */
    @Nonnull
    default ThrowableBiFloatPredicate<X> or(@Nonnull ThrowableBiFloatPredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> testThrows(value1, value2) || other.testThrows(value1, value2);
    }

    /**
     * Returns a composed {@link ThrowableBiFloatPredicate} that represents a short-circuiting logical XOR of this
     * predicate and another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if
     * evaluation of this {@code ThrowableBiFloatPredicate} throws an exception, the {@code other} predicate will not be
     * evaluated.
     *
     * @param other A {@code ThrowableBiFloatPredicate} that will be logically-XORed with this one
     * @return A composed {@code ThrowableBiFloatPredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableBiFloatPredicate)
     * @see #or(ThrowableBiFloatPredicate)
     */
    @Nonnull
    default ThrowableBiFloatPredicate<X> xor(@Nonnull ThrowableBiFloatPredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> testThrows(value1, value2) ^ other.testThrows(value1, value2);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableBiFloatPredicate}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableBiFloatPredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableBiFloatPredicate<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<Float, Float>, Boolean> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ThrowableBiFloatPredicate<X> & Memoized) (value1, value2) -> {
                boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2), ThrowableFunction.of(
                            key -> testThrows(key.getLeft(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableBiPredicate} which represents this {@link ThrowableBiFloatPredicate}. Thereby
     * the primitive input argument for this predicate is autoboxed. This method provides the possibility to use this
     * {@code ThrowableBiFloatPredicate} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableBiPredicate} which represents this {@code ThrowableBiFloatPredicate}.
     */
    @Nonnull
    default ThrowableBiPredicate<Float, Float, X> boxed() {
        return this::testThrows;
    }

    /**
     * Returns a composed {@link BiFloatPredicate} that applies this predicate to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link BiFloatPredicate} that applies this predicate to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default BiFloatPredicate nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link BiFloatPredicate} that applies this predicate to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link BiFloatPredicate} that applies this predicate to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default BiFloatPredicate nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link BiFloatPredicate} that first applies this predicate to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * predicate.
     *
     * @param recover The operation to apply if this predicate throws a {@code Throwable}
     * @return A composed {@link BiFloatPredicate} that first applies this predicate to its input, and then applies the
     * {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing predicate is {@code null}
     * @implSpec The implementation checks that the returned enclosing predicate from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default BiFloatPredicate recover(@Nonnull Function<? super Throwable, ? extends BiFloatPredicate> recover) {
        Objects.requireNonNull(recover);
        return (value1, value2) -> {
            try {
                return testThrows(value1, value2);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                BiFloatPredicate predicate = recover.apply(throwable);
                Objects.requireNonNull(predicate, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return predicate.test(value1, value2);
            }
        };
    }

    /**
     * Returns a composed {@link BiFloatPredicate} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed predicate behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this predicate in the returned composed
     * predicate by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing predicate.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing predicate variant of this throwable predicate, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed predicate. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed predicate. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed predicate is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed predicate, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed predicate.
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
     * @return A composed {@link BiFloatPredicate} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default BiFloatPredicate sneakyThrow() {
        return (value1, value2) -> {
            try {
                return testThrows(value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
