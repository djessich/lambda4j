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
package at.gridtec.lambda4j.predicate.bi;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableBooleanConsumer;
import at.gridtec.lambda4j.consumer.bi.ThrowableBiDoubleConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableBooleanFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.bi.ThrowableBiDoubleFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiDoubleToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiDoubleToCharFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiDoubleToFloatFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiDoubleToIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiDoubleToLongFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiDoubleToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToIntFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableByteToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableLongToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToDoubleFunction;
import at.gridtec.lambda4j.function.to.ThrowableToDoubleFunction;
import at.gridtec.lambda4j.operator.binary.ThrowableBooleanBinaryOperator;
import at.gridtec.lambda4j.operator.binary.ThrowableDoubleBinaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableDoubleUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableDoublePredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents an predicate (boolean-valued function) of two {@code double}-valued input arguments which is able to throw
 * any {@link Throwable}. This is a primitive specialization of {@link ThrowableBiPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(double, double)}.
 *
 * @param <X> The type of the throwable to be thrown by this predicate
 * @see ThrowableBiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiDoublePredicate<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableBiDoublePredicate} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBiDoublePredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableBiDoublePredicate<X> of(
            @Nullable final ThrowableBiDoublePredicate<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBiDoublePredicate} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code ThrowableBiDoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this predicates action
     */
    static <X extends Throwable> boolean call(@Nonnull final ThrowableBiDoublePredicate<? extends X> predicate,
            double value1, double value2) throws X {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(value1, value2);
    }

    /**
     * Creates a {@link ThrowableBiDoublePredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowableDoublePredicate}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiDoublePredicate} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableDoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiDoublePredicate<X> onlyFirst(
            @Nonnull final ThrowableDoublePredicate<? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2) -> predicate.testThrows(value1);
    }

    /**
     * Creates a {@link ThrowableBiDoublePredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@link ThrowableDoublePredicate}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiDoublePredicate} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableDoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiDoublePredicate<X> onlySecond(
            @Nonnull final ThrowableDoublePredicate<? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2) -> predicate.testThrows(value2);
    }

    /**
     * Creates a {@link ThrowableBiDoublePredicate} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowableBiDoublePredicate} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiDoublePredicate<X> constant(boolean ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link ThrowableBiDoublePredicate} that always returns {@code true}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableBiDoublePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiDoublePredicate<X> alwaysTrue() {
        return (value1, value2) -> true;
    }

    /**
     * Returns a {@link ThrowableBiDoublePredicate} that always returns {@code false}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableBiDoublePredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiDoublePredicate<X> alwaysFalse() {
        return (value1, value2) -> false;
    }

    /**
     * Returns a {@link ThrowableBiDoublePredicate} that tests if the given arguments are <b>equal</b> to the ones of
     * this predicate.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowableBiDoublePredicate} that tests if the given arguments are <b>equal</b> to the ones of
     * this predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiDoublePredicate<X> isEqual(double target1, double target2) {
        return (value1, value2) -> (value1 == target1) && (value2 == target2);
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The return value from the predicate, which is its result.
     * @throws X Any throwable from this predicates action
     */
    boolean testThrows(double value1, double value2) throws X;

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
            @Nonnull final ThrowableToDoubleFunction<? super A, ? extends X> before1,
            @Nonnull final ThrowableToDoubleFunction<? super B, ? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> testThrows(before1.applyAsDoubleThrows(a), before2.applyAsDoubleThrows(b));
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
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanBinaryOperator<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToDoubleFunction<? extends X> before1,
            @Nonnull final ThrowableBooleanToDoubleFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsDoubleThrows(value1), before2.applyAsDoubleThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiBytePredicate} that first applies the {@code before} functions to
     * its input, and then applies this predicate to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiBytePredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiBytePredicate<X> composeFromByte(
            @Nonnull final ThrowableByteToDoubleFunction<? extends X> before1,
            @Nonnull final ThrowableByteToDoubleFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsDoubleThrows(value1), before2.applyAsDoubleThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiCharPredicate} that first applies the {@code before} functions to
     * its input, and then applies this predicate to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiCharPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiCharPredicate<X> composeFromChar(
            @Nonnull final ThrowableCharToDoubleFunction<? extends X> before1,
            @Nonnull final ThrowableCharToDoubleFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsDoubleThrows(value1), before2.applyAsDoubleThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoublePredicate} that first applies the {@code before} operators to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before1 The first operator to apply before this predicate is applied
     * @param before2 The second operator to apply before this predicate is applied
     * @return A composed {@code ThrowableBiDoublePredicate} that first applies the {@code before} operators to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiDoublePredicate<X> composeFromDouble(
            @Nonnull final ThrowableDoubleUnaryOperator<? extends X> before1,
            @Nonnull final ThrowableDoubleUnaryOperator<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsDoubleThrows(value1), before2.applyAsDoubleThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiFloatPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiFloatPredicate<X> composeFromFloat(
            @Nonnull final ThrowableFloatToDoubleFunction<? extends X> before1,
            @Nonnull final ThrowableFloatToDoubleFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsDoubleThrows(value1), before2.applyAsDoubleThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiIntPredicate} that first applies the {@code before} functions to
     * its input, and then applies this predicate to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiIntPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiIntPredicate<X> composeFromInt(@Nonnull final ThrowableIntToDoubleFunction<? extends X> before1,
            @Nonnull final ThrowableIntToDoubleFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsDoubleThrows(value1), before2.applyAsDoubleThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiLongPredicate} that first applies the {@code before} functions to
     * its input, and then applies this predicate to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code ThrowableBiLongPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiLongPredicate<X> composeFromLong(
            @Nonnull final ThrowableLongToDoubleFunction<? extends X> before1,
            @Nonnull final ThrowableLongToDoubleFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsDoubleThrows(value1), before2.applyAsDoubleThrows(value2));
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
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiShortPredicate<X> composeFromShort(
            @Nonnull final ThrowableShortToDoubleFunction<? extends X> before1,
            @Nonnull final ThrowableShortToDoubleFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> testThrows(before1.applyAsDoubleThrows(value1), before2.applyAsDoubleThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBiDoubleFunction<S, X> andThen(
            @Nonnull final ThrowableBooleanFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoublePredicate} that first applies this predicate to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code ThrowableBiDoublePredicate} that first applies this predicate to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiDoublePredicate<X> andThenToBoolean(
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsBooleanThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToByteFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiDoubleToByteFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiDoubleToByteFunction<X> andThenToByte(
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByteThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToCharFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiDoubleToCharFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiDoubleToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableBooleanToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsCharThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableDoubleBinaryOperator} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableDoubleBinaryOperator} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleBinaryOperator<X> andThenToDouble(
            @Nonnull final ThrowableBooleanToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDoubleThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToFloatFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiDoubleToFloatFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiDoubleToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableBooleanToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloatThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToIntFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiDoubleToIntFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiDoubleToIntFunction<X> andThenToInt(
            @Nonnull final ThrowableBooleanToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsIntThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToLongFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiDoubleToLongFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiDoubleToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableBooleanToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLongThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToShortFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiDoubleToShortFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiDoubleToShortFunction<X> andThenToShort(
            @Nonnull final ThrowableBooleanToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShortThrows(testThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleConsumer} that fist applies this predicate to its input, and then
     * consumes the result using the given {@link ThrowableBooleanConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiDoubleConsumer} that first applies this predicate to its input, and then
     * consumes the result using the given {@code ThrowableBooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiDoubleConsumer<X> consume(@Nonnull final ThrowableBooleanConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.acceptThrows(testThrows(value1, value2));
    }

    /**
     * Returns a {@link ThrowableBiDoublePredicate} that represents the logical negation of this one.
     *
     * @return A {@code ThrowableBiDoublePredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default ThrowableBiDoublePredicate<X> negate() {
        return (value1, value2) -> !testThrows(value1, value2);
    }

    /**
     * Returns a composed {@link ThrowableBiDoublePredicate} that represents a short-circuiting logical AND of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code false}, then the
     * {@code other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableBiDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableBiDoublePredicate} that will be logically-ANDed with this one
     * @return A composed {@code ThrowableBiDoublePredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(ThrowableBiDoublePredicate)
     * @see #xor(ThrowableBiDoublePredicate)
     */
    @Nonnull
    default ThrowableBiDoublePredicate<X> and(@Nonnull final ThrowableBiDoublePredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> testThrows(value1, value2) && other.testThrows(value1, value2);
    }

    /**
     * Returns a composed {@link ThrowableBiDoublePredicate} that represents a short-circuiting logical OR of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code
     * other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableBiDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableBiDoublePredicate} that will be logically-ORed with this one
     * @return A composed {@code ThrowableBiDoublePredicate} that represents the short-circuiting logical OR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableBiDoublePredicate)
     * @see #xor(ThrowableBiDoublePredicate)
     */
    @Nonnull
    default ThrowableBiDoublePredicate<X> or(@Nonnull final ThrowableBiDoublePredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> testThrows(value1, value2) || other.testThrows(value1, value2);
    }

    /**
     * Returns a composed {@link ThrowableBiDoublePredicate} that represents a short-circuiting logical XOR of this
     * predicate and another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if
     * evaluation of this {@code ThrowableBiDoublePredicate} throws an exception, the {@code other} predicate will not
     * be evaluated.
     *
     * @param other A {@code ThrowableBiDoublePredicate} that will be logically-XORed with this one
     * @return A composed {@code ThrowableBiDoublePredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableBiDoublePredicate)
     * @see #or(ThrowableBiDoublePredicate)
     */
    @Nonnull
    default ThrowableBiDoublePredicate<X> xor(@Nonnull final ThrowableBiDoublePredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> testThrows(value1, value2) ^ other.testThrows(value1, value2);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableBiDoublePredicate}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableBiDoublePredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableBiDoublePredicate<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<Double, Double>, Boolean> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableBiDoublePredicate<X> & Memoized) (value1, value2) -> {
                final boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2), ThrowableFunction.of(
                            key -> testThrows(key.getLeft(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableBiPredicate} which represents this {@link ThrowableBiDoublePredicate}. Thereby
     * the primitive input argument for this predicate is autoboxed.
     *
     * @return A composed {@code ThrowableBiPredicate} which represents this {@code ThrowableBiDoublePredicate}.
     */
    @Nonnull
    default ThrowableBiPredicate<Double, Double, X> boxed() {
        return this::testThrows;
    }

    /**
     * Returns a composed {@link BiDoublePredicate} that applies this predicate to its input and nests the thrown {@link
     * Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is nested
     * (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown throwables
     * message and the thrown throwable itself.
     *
     * @return A composed {@code BiDoublePredicate} that applies this predicate to its input and nests the thrown {@code
     * Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default BiDoublePredicate nest() {
        return (value1, value2) -> {
            try {
                return this.testThrows(value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link BiDoublePredicate} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
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
     * @return A composed {@link BiDoublePredicate} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default BiDoublePredicate sneakyThrow() {
        return (value1, value2) -> {
            try {
                return this.testThrows(value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

    /**
     * Returns a composed {@link BiDoublePredicate} that first applies this predicate to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * predicate.
     *
     * @param recover The operation to apply if this predicate throws a {@code Throwable}
     * @return A composed {@link BiDoublePredicate} that first applies this predicate to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing predicate is {@code null}
     * @implNote The implementation checks that the returned enclosing predicate from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     */
    @Nonnull
    default BiDoublePredicate recover(@Nonnull final Function<? super Throwable, ? extends BiDoublePredicate> recover) {
        Objects.requireNonNull(recover);
        return (value1, value2) -> {
            try {
                return this.testThrows(value1, value2);
            } catch (Throwable throwable) {
                final BiDoublePredicate predicate = recover.apply(throwable);
                Objects.requireNonNull(predicate, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return predicate.test(value1, value2);
            }
        };
    }

}