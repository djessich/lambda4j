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
package org.lambda4j.function.bi.obj;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableShortConsumer;
import org.lambda4j.consumer.bi.obj.ThrowableObjLongConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableBooleanFunction;
import org.lambda4j.function.ThrowableByteFunction;
import org.lambda4j.function.ThrowableCharFunction;
import org.lambda4j.function.ThrowableDoubleFunction;
import org.lambda4j.function.ThrowableFloatFunction;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.ThrowableIntFunction;
import org.lambda4j.function.ThrowableLongFunction;
import org.lambda4j.function.ThrowableShortFunction;
import org.lambda4j.function.bi.ThrowableBiFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiBooleanToShortFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiByteToShortFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiCharToShortFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiDoubleToShortFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiFloatToShortFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiIntToShortFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiLongToShortFunction;
import org.lambda4j.function.bi.to.ThrowableToShortBiFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import org.lambda4j.function.conversion.ThrowableByteToLongFunction;
import org.lambda4j.function.conversion.ThrowableCharToLongFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToLongFunction;
import org.lambda4j.function.conversion.ThrowableFloatToLongFunction;
import org.lambda4j.function.conversion.ThrowableIntToLongFunction;
import org.lambda4j.function.conversion.ThrowableLongToShortFunction;
import org.lambda4j.function.conversion.ThrowableShortToByteFunction;
import org.lambda4j.function.conversion.ThrowableShortToCharFunction;
import org.lambda4j.function.conversion.ThrowableShortToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableShortToFloatFunction;
import org.lambda4j.function.conversion.ThrowableShortToIntFunction;
import org.lambda4j.function.conversion.ThrowableShortToLongFunction;
import org.lambda4j.function.to.ThrowableToLongFunction;
import org.lambda4j.function.to.ThrowableToShortFunction;
import org.lambda4j.operator.binary.ThrowableShortBinaryOperator;
import org.lambda4j.operator.unary.ThrowableLongUnaryOperator;
import org.lambda4j.operator.unary.ThrowableShortUnaryOperator;
import org.lambda4j.predicate.ThrowableShortPredicate;
import org.lambda4j.predicate.bi.obj.ThrowableObjLongPredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents an operation that accepts one object-valued and one {@code long}-valued input argument and produces a
 * {@code short}-valued result which is able to throw any {@link Throwable}.
 * This is a (reference, long) specialization of {@link ThrowableBiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShortThrows(Object, long)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableBiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableObjLongToShortFunction<T, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableObjLongToShortFunction} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableObjLongToShortFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, X extends Throwable> ThrowableObjLongToShortFunction<T, X> of(
            @Nullable final ThrowableObjLongToShortFunction<T, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableObjLongToShortFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ThrowableObjLongToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, X extends Throwable> short call(
            @Nonnull final ThrowableObjLongToShortFunction<? super T, ? extends X> function, T t, long value) throws X {
        Objects.requireNonNull(function);
        return function.applyAsShortThrows(t, value);
    }

    /**
     * Creates a {@link ThrowableObjLongToShortFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@link ThrowableToShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableObjLongToShortFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjLongToShortFunction<T, X> onlyFirst(
            @Nonnull final ThrowableToShortFunction<? super T, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsShortThrows(t);
    }

    /**
     * Creates a {@link ThrowableObjLongToShortFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableLongToShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableObjLongToShortFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableLongToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjLongToShortFunction<T, X> onlySecond(
            @Nonnull final ThrowableLongToShortFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsShortThrows(value);
    }

    /**
     * Creates a {@link ThrowableObjLongToShortFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableObjLongToShortFunction} which always returns a given value.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjLongToShortFunction<T, X> constant(short ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    short applyAsShortThrows(T t, long value) throws X;

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableLongToShortFunction}
     * as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ThrowableLongToShortFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableLongToShortFunction<X> papplyAsShortThrows(T t) {
        return (value) -> this.applyAsShortThrows(t, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToShortFunction} as
     * result.
     *
     * @param value The second argument to this function used to partially apply this function
     * @return A {@code ThrowableToShortFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToShortFunction<T, X> papplyAsShortThrows(long value) {
        return (t) -> this.applyAsShortThrows(t, value);
    }

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
     * Returns a composed {@link ThrowableToShortBiFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableToShortBiFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ThrowableToShortBiFunction<A, B, X> compose(
            @Nonnull final ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull final ThrowableToLongFunction<? super B, ? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsShortThrows(before1.applyThrows(a), before2.applyAsLongThrows(b));
    }

    /**
     * Returns a composed {@link ThrowableBiBooleanToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiBooleanToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiBooleanToShortFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableBooleanToLongFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyAsLongThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiByteToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiByteToShortFunction<X> composeFromByte(
            @Nonnull final ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableByteToLongFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyAsLongThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiCharToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiCharToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiCharToShortFunction<X> composeFromChar(
            @Nonnull final ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableCharToLongFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyAsLongThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiDoubleToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiDoubleToShortFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableDoubleToLongFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyAsLongThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiFloatToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiFloatToShortFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableFloatToLongFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyAsLongThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiIntToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiIntToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiIntToShortFunction<X> composeFromInt(
            @Nonnull final ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableIntToLongFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyAsLongThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiLongToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code ThrowableBiLongToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiLongToShortFunction<X> composeFromLong(
            @Nonnull final ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableLongUnaryOperator<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyAsLongThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableShortBinaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableShortBinaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortBinaryOperator<X> composeFromShort(
            @Nonnull final ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableShortToLongFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyAsLongThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableObjLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableObjLongFunction<T, S, X> andThen(
            @Nonnull final ThrowableShortFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyThrows(applyAsShortThrows(t, value));
    }

    /**
     * Returns a composed {@link ThrowableObjLongPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableObjLongPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableObjLongPredicate<T, X> andThenToBoolean(
            @Nonnull final ThrowableShortPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.testThrows(applyAsShortThrows(t, value));
    }

    /**
     * Returns a composed {@link ThrowableObjLongToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjLongToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableObjLongToByteFunction<T, X> andThenToByte(
            @Nonnull final ThrowableShortToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsByteThrows(applyAsShortThrows(t, value));
    }

    /**
     * Returns a composed {@link ThrowableObjLongToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjLongToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableObjLongToCharFunction<T, X> andThenToChar(
            @Nonnull final ThrowableShortToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsCharThrows(applyAsShortThrows(t, value));
    }

    /**
     * Returns a composed {@link ThrowableObjLongToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjLongToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableObjLongToDoubleFunction<T, X> andThenToDouble(
            @Nonnull final ThrowableShortToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsDoubleThrows(applyAsShortThrows(t, value));
    }

    /**
     * Returns a composed {@link ThrowableObjLongToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjLongToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableObjLongToFloatFunction<T, X> andThenToFloat(
            @Nonnull final ThrowableShortToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsFloatThrows(applyAsShortThrows(t, value));
    }

    /**
     * Returns a composed {@link ThrowableObjLongToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjLongToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableObjLongToIntFunction<T, X> andThenToInt(
            @Nonnull final ThrowableShortToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsIntThrows(applyAsShortThrows(t, value));
    }

    /**
     * Returns a composed {@link ThrowableObjLongToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjLongToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableObjLongToLongFunction<T, X> andThenToLong(
            @Nonnull final ThrowableShortToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsLongThrows(applyAsShortThrows(t, value));
    }

    /**
     * Returns a composed {@link ThrowableObjLongToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableObjLongToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableObjLongToShortFunction<T, X> andThenToShort(
            @Nonnull final ThrowableShortUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsShortThrows(applyAsShortThrows(t, value));
    }

    /**
     * Returns a composed {@link ThrowableObjLongConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableShortConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableObjLongConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableObjLongConsumer<T, X> consume(@Nonnull final ThrowableShortConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.acceptThrows(applyAsShortThrows(t, value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableObjLongToShortFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableObjLongToShortFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableObjLongToShortFunction<T, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<T, Long>, Short> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableObjLongToShortFunction<T, X> & Memoized) (t, value) -> {
                final short returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(t, value), ThrowableFunction.of(
                            key -> applyAsShortThrows(key.getLeft(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableBiFunction} which represents this {@link ThrowableObjLongToShortFunction}.
     * Thereby the primitive input argument for this function is autoboxed. This method provides the possibility to use
     * this {@code ThrowableObjLongToShortFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableBiFunction} which represents this {@code ThrowableObjLongToShortFunction}.
     */
    @Nonnull
    default ThrowableBiFunction<T, Long, Short, X> boxed() {
        return this::applyAsShortThrows;
    }

    /**
     * Returns a composed {@link ObjLongToShortFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link ObjLongToShortFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ObjLongToShortFunction<T> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ObjLongToShortFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ObjLongToShortFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ObjLongToShortFunction<T> nest(
            @Nonnull final Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ObjLongToShortFunction} that first applies this function to its input, and then applies
     * the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link ObjLongToShortFunction} that first applies this function to its input, and then applies
     * the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ObjLongToShortFunction<T> recover(
            @Nonnull final Function<? super Throwable, ? extends ObjLongToShortFunction<? super T>> recover) {
        Objects.requireNonNull(recover);
        return (t, value) -> {
            try {
                return this.applyAsShortThrows(t, value);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                final ObjLongToShortFunction<? super T> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsShort(t, value);
            }
        };
    }

    /**
     * Returns a composed {@link ObjLongToShortFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
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
     * @return A composed {@link ObjLongToShortFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ObjLongToShortFunction<T> sneakyThrow() {
        return (t, value) -> {
            try {
                return this.applyAsShortThrows(t, value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}