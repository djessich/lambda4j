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

package org.lambda4j.predicate;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoublePredicate;
import java.util.function.Function;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableBooleanConsumer;
import org.lambda4j.consumer.ThrowableDoubleConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableBooleanFunction;
import org.lambda4j.function.ThrowableDoubleFunction;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToByteFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToCharFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToFloatFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToIntFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
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
import org.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import org.lambda4j.operator.unary.ThrowableDoubleUnaryOperator;

/**
 * Represents an predicate (boolean-valued function) of one {@code double}-valued input argument which is able to throw
 * any {@link Throwable}. This is a primitive specialization of {@link ThrowablePredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(double)}.
 *
 * @param <X> The type of the throwable to be thrown by this predicate
 * @apiNote This is a throwable JDK lambda.
 * @see ThrowablePredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableDoublePredicate<X extends Throwable> extends Lambda, DoublePredicate {

    /**
     * Constructs a {@link ThrowableDoublePredicate} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableDoublePredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableDoublePredicate<X> of(
            @Nullable ThrowableDoublePredicate<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableDoublePredicate} with the given argument and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code ThrowableDoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this predicates action
     */
    static <X extends Throwable> boolean call(@Nonnull ThrowableDoublePredicate<? extends X> predicate,
            double value) throws X {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(value);
    }

    /**
     * Creates a {@link ThrowableDoublePredicate} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowableDoublePredicate} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableDoublePredicate<X> constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Returns a {@link ThrowableDoublePredicate} that always returns {@code true}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableDoublePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <X extends Throwable> ThrowableDoublePredicate<X> alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link ThrowableDoublePredicate} that always returns {@code false}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableDoublePredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <X extends Throwable> ThrowableDoublePredicate<X> alwaysFalse() {
        return value -> false;
    }

    /**
     * Returns a {@link ThrowableDoublePredicate} that tests if the given argument are <b>equal</b> to the one of this
     * predicate.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param target The reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowableDoublePredicate} that tests if the given argument are <b>equal</b> to the one of this
     * predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <X extends Throwable> ThrowableDoublePredicate<X> isEqual(double target) {
        return value -> value == target;
    }

    /**
     * Applies this predicate to the given argument.
     *
     * @param value The argument to the predicate
     * @return The return value from the predicate, which is its result.
     * @throws X Any throwable from this predicates action
     */
    boolean testThrows(double value) throws X;

    /**
     * Applies this predicate to the given argument.
     *
     * @param value The argument to the predicate
     * @return The return value from the predicate, which is its result.
     * @apiNote This method mainly exists to use this {@link ThrowableDoublePredicate} in JRE specific methods only
     * accepting {@link DoublePredicate}. If this predicate should be applied, then the {@link #testThrows(double)}
     * method should be used.
     * @apiNote Overrides the {@link DoublePredicate#test(double)} method by using a redefinition as default method.
     * This implementation calls the {@link #testThrows(double)} method of this function and catches the eventually
     * thrown {@link Throwable} from it. If it is of type {@link RuntimeException} or {@link Error} it is rethrown as
     * is. Other {@code Throwable} types are wrapped in a {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default boolean test(double value) {
        return nest().test(value);
    }

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ThrowablePredicate} that first applies the {@code before} function to its input, and
     * then applies this predicate to the result.
     *
     * @param <A> The type of the argument to the given function, and of composed predicate
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code ThrowablePredicate} that first applies the {@code before} function to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ThrowablePredicate<A, X> compose(
            @Nonnull ThrowableToDoubleFunction<? super A, ? extends X> before) {
        Objects.requireNonNull(before);
        return a -> testThrows(before.applyAsDoubleThrows(a));
    }

    /**
     * Returns a composed {@link ThrowableBooleanUnaryOperator} that first applies the {@code before} function to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code ThrowableBooleanUnaryOperator} that first applies the {@code before} function to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanUnaryOperator<X> composeFromBoolean(
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> testThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBytePredicate} that first applies the {@code before} function to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code byte} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code ThrowableBytePredicate} that first applies the {@code before} function to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBytePredicate<X> composeFromByte(
            @Nonnull ThrowableByteToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> testThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableCharPredicate} that first applies the {@code before} function to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code char} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code ThrowableCharPredicate} that first applies the {@code before} function to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharPredicate<X> composeFromChar(
            @Nonnull ThrowableCharToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> testThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoublePredicate} that first applies the {@code before} operator to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before The operator to apply before this predicate is applied
     * @return A composed {@code ThrowableDoublePredicate} that first applies the {@code before} operator to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoublePredicate<X> composeFromDouble(
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> testThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableFloatPredicate} that first applies the {@code before} function to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code float} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code ThrowableFloatPredicate} that first applies the {@code before} function to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatPredicate<X> composeFromFloat(
            @Nonnull ThrowableFloatToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> testThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntPredicate} that first applies the {@code before} function to its input, and
     * then applies this predicate to the result. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code ThrowableIntPredicate} that first applies the {@code before} function to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntPredicate<X> composeFromInt(@Nonnull ThrowableIntToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> testThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableLongPredicate} that first applies the {@code before} function to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code long} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code ThrowableLongPredicate} that first applies the {@code before} function to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongPredicate<X> composeFromLong(
            @Nonnull ThrowableLongToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> testThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableShortPredicate} that first applies the {@code before} function to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code short} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code ThrowableShortPredicate} that first applies the {@code before} function to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortPredicate<X> composeFromShort(
            @Nonnull ThrowableShortToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> testThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableDoubleFunction<S, X> andThen(
            @Nonnull ThrowableBooleanFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyThrows(testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoublePredicate} that first applies this predicate to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code ThrowableDoublePredicate} that first applies this predicate to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableDoublePredicate<X> andThenToBoolean(
            @Nonnull ThrowableBooleanUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBooleanThrows(testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableDoubleToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableDoubleToByteFunction<X> andThenToByte(
            @Nonnull ThrowableBooleanToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByteThrows(testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableDoubleToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableDoubleToCharFunction<X> andThenToChar(
            @Nonnull ThrowableBooleanToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsCharThrows(testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleUnaryOperator} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableDoubleUnaryOperator} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleUnaryOperator<X> andThenToDouble(
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDoubleThrows(testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToFloatFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableDoubleToFloatFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableDoubleToFloatFunction<X> andThenToFloat(
            @Nonnull ThrowableBooleanToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloatThrows(testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableDoubleToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableDoubleToIntFunction<X> andThenToInt(
            @Nonnull ThrowableBooleanToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsIntThrows(testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableDoubleToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableDoubleToLongFunction<X> andThenToLong(
            @Nonnull ThrowableBooleanToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLongThrows(testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToShortFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableDoubleToShortFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableDoubleToShortFunction<X> andThenToShort(
            @Nonnull ThrowableBooleanToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShortThrows(testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleConsumer} that fist applies this predicate to its input, and then
     * consumes the result using the given {@link ThrowableBooleanConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableDoubleConsumer} that first applies this predicate to its input, and then
     * consumes the result using the given {@code ThrowableBooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableDoubleConsumer<X> consume(@Nonnull ThrowableBooleanConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.acceptThrows(testThrows(value));
    }

    /**
     * Returns a {@link ThrowableDoublePredicate} that represents the logical negation of this one.
     *
     * @return A {@code ThrowableDoublePredicate} that represents the logical negation of this one.
     */
    @Override
    @Nonnull
    default ThrowableDoublePredicate<X> negate() {
        return value -> !testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowableDoublePredicate} that represents a short-circuiting logical AND of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code false}, then the
     * {@code other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableDoublePredicate} that will be logically-ANDed with this one
     * @return A composed {@code ThrowableDoublePredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(ThrowableDoublePredicate)
     * @see #xor(ThrowableDoublePredicate)
     */
    @Nonnull
    default ThrowableDoublePredicate<X> and(@Nonnull ThrowableDoublePredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return value -> testThrows(value) && other.testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowableDoublePredicate} that represents a short-circuiting logical OR of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code
     * other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableDoublePredicate} that will be logically-ORed with this one
     * @return A composed {@code ThrowableDoublePredicate} that represents the short-circuiting logical OR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableDoublePredicate)
     * @see #xor(ThrowableDoublePredicate)
     */
    @Nonnull
    default ThrowableDoublePredicate<X> or(@Nonnull ThrowableDoublePredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return value -> testThrows(value) || other.testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowableDoublePredicate} that represents a short-circuiting logical XOR of this
     * predicate and another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if
     * evaluation of this {@code ThrowableDoublePredicate} throws an exception, the {@code other} predicate will not be
     * evaluated.
     *
     * @param other A {@code ThrowableDoublePredicate} that will be logically-XORed with this one
     * @return A composed {@code ThrowableDoublePredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableDoublePredicate)
     * @see #or(ThrowableDoublePredicate)
     */
    @Nonnull
    default ThrowableDoublePredicate<X> xor(@Nonnull ThrowableDoublePredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return value -> testThrows(value) ^ other.testThrows(value);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableDoublePredicate}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableDoublePredicate}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableDoublePredicate<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Double, Boolean> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ThrowableDoublePredicate<X> & Memoized) value -> {
                boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, ThrowableFunction.of(this::testThrows));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowablePredicate} which represents this {@link ThrowableDoublePredicate}. Thereby the
     * primitive input argument for this predicate is autoboxed. This method provides the possibility to use this {@code
     * ThrowableDoublePredicate} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowablePredicate} which represents this {@code ThrowableDoublePredicate}.
     */
    @Nonnull
    default ThrowablePredicate<Double, X> boxed() {
        return this::testThrows;
    }

    /**
     * Returns a composed {@link DoublePredicate2} that applies this predicate to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link DoublePredicate2} that applies this predicate to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default DoublePredicate2 nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link DoublePredicate2} that applies this predicate to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link DoublePredicate2} that applies this predicate to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default DoublePredicate2 nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link DoublePredicate2} that first applies this predicate to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same argument of this
     * predicate.
     *
     * @param recover The operation to apply if this predicate throws a {@code Throwable}
     * @return A composed {@link DoublePredicate2} that first applies this predicate to its input, and then applies the
     * {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing predicate is {@code null}
     * @implSpec The implementation checks that the returned enclosing predicate from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default DoublePredicate2 recover(@Nonnull Function<? super Throwable, ? extends DoublePredicate> recover) {
        Objects.requireNonNull(recover);
        return value -> {
            try {
                return testThrows(value);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                DoublePredicate predicate = recover.apply(throwable);
                Objects.requireNonNull(predicate, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return predicate.test(value);
            }
        };
    }

    /**
     * Returns a composed {@link DoublePredicate2} that applies this predicate to its input and sneakily throws the
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
     * @return A composed {@link DoublePredicate2} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default DoublePredicate2 sneakyThrow() {
        return value -> {
            try {
                return testThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
