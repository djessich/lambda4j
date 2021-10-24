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

package org.lambda4j.function.tri.obj;

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
import org.lambda4j.consumer.tri.obj.ThrowableObjBiBooleanConsumer;
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
import org.lambda4j.function.bi.conversion.ThrowableBiBooleanToDoubleFunction;
import org.lambda4j.function.bi.obj.ThrowableObjBooleanToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToByteFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToCharFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToFloatFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToIntFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToLongFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToShortFunction;
import org.lambda4j.function.to.ThrowableToDoubleFunction;
import org.lambda4j.function.tri.ThrowableTriFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriBooleanToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriCharToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriFloatToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriIntToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToDoubleFunction;
import org.lambda4j.function.tri.to.ThrowableToDoubleTriFunction;
import org.lambda4j.operator.ternary.ThrowableDoubleTernaryOperator;
import org.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import org.lambda4j.operator.unary.ThrowableDoubleUnaryOperator;
import org.lambda4j.predicate.ThrowableBytePredicate;
import org.lambda4j.predicate.ThrowableCharPredicate;
import org.lambda4j.predicate.ThrowableDoublePredicate;
import org.lambda4j.predicate.ThrowableFloatPredicate;
import org.lambda4j.predicate.ThrowableIntPredicate;
import org.lambda4j.predicate.ThrowableLongPredicate;
import org.lambda4j.predicate.ThrowablePredicate;
import org.lambda4j.predicate.ThrowableShortPredicate;
import org.lambda4j.predicate.tri.obj.ThrowableObjBiBooleanPredicate;

/**
 * Represents an operation that accepts one object-valued and two {@code boolean}-valued input arguments and produces a
 * {@code double}-valued result which is able to throw any {@link Throwable}. This is a (reference, boolean, boolean)
 * specialization of {@link ThrowableTriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDoubleThrows(Object, boolean,
 * boolean)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableTriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableObjBiBooleanToDoubleFunction<T, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableObjBiBooleanToDoubleFunction} based on a lambda expression or a method reference.
     * Thereby the given lambda expression or method reference is returned on an as-is basis to implicitly transform it
     * to the desired type. With this method, it is possible to ensure that correct type is used from lambda expression
     * or method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableObjBiBooleanToDoubleFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, X extends Throwable> ThrowableObjBiBooleanToDoubleFunction<T, X> of(
            @Nullable ThrowableObjBiBooleanToDoubleFunction<T, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableObjBiBooleanToDoubleFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The result from the given {@code ThrowableObjBiBooleanToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, X extends Throwable> double call(
            @Nonnull ThrowableObjBiBooleanToDoubleFunction<? super T, ? extends X> function, T t, boolean value1,
            boolean value2) throws X {
        Objects.requireNonNull(function);
        return function.applyAsDoubleThrows(t, value1, value2);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanToDoubleFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@link ThrowableToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableObjBiBooleanToDoubleFunction} which uses the {@code first} parameter of this
     * one as argument for the given {@code ThrowableToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanToDoubleFunction<T, X> onlyFirst(
            @Nonnull ThrowableToDoubleFunction<? super T, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsDoubleThrows(t);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanToDoubleFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@link ThrowableBooleanToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableObjBiBooleanToDoubleFunction} which uses the {@code second} parameter of this
     * one as argument for the given {@code ThrowableBooleanToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanToDoubleFunction<T, X> onlySecond(
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsDoubleThrows(value1);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanToDoubleFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@link ThrowableBooleanToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableObjBiBooleanToDoubleFunction} which uses the {@code third} parameter of this
     * one as argument for the given {@code ThrowableBooleanToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanToDoubleFunction<T, X> onlyThird(
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsDoubleThrows(value2);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanToDoubleFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableObjBiBooleanToDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanToDoubleFunction<T, X> constant(double ret) {
        return (t, value1, value2) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    double applyAsDoubleThrows(T t, boolean value1, boolean value2) throws X;

    /**
     * Applies this function partially to some arguments of this one, producing a {@link
     * ThrowableBiBooleanToDoubleFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ThrowableBiBooleanToDoubleFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableBiBooleanToDoubleFunction<X> applyAsDoubleThrowsPartially(T t) {
        return (value1, value2) -> applyAsDoubleThrows(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link
     * ThrowableBooleanToDoubleFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value1 The second argument to this function used to partially apply this function
     * @return A {@code ThrowableBooleanToDoubleFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableBooleanToDoubleFunction<X> applyAsDoubleThrowsPartially(T t, boolean value1) {
        return value2 -> applyAsDoubleThrows(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link
     * ThrowableObjBooleanToDoubleFunction} as result.
     *
     * @param value1 The second argument to this function used to partially apply this function
     * @return A {@code ThrowableObjBooleanToDoubleFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableObjBooleanToDoubleFunction<T, X> applyAsDoubleThrowsPartially(boolean value1) {
        return (t, value2) -> applyAsDoubleThrows(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToDoubleFunction} as
     * result.
     *
     * @param value1 The second argument to this function used to partially apply this function
     * @param value2 The third argument to this function used to partially apply this function
     * @return A {@code ThrowableToDoubleFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToDoubleFunction<T, X> applyAsDoubleThrowsPartially(boolean value1, boolean value2) {
        return t -> applyAsDoubleThrows(t, value1, value2);
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
     * Returns a composed {@link ThrowableToDoubleTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given predicate, and of composed function
     * @param <C> The type of the argument to the third given predicate, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableToDoubleTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToDoubleTriFunction<A, B, C, X> compose(
            @Nonnull ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull ThrowablePredicate<? super B, ? extends X> before2,
            @Nonnull ThrowablePredicate<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsDoubleThrows(before1.applyThrows(a), before2.testThrows(b), before3.testThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code ThrowableTriBooleanToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToDoubleFunction<X> composeFromBoolean(
            @Nonnull ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableBooleanUnaryOperator<? extends X> before2,
            @Nonnull ThrowableBooleanUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1),
                before2.applyAsBooleanThrows(value2),
                before3.applyAsBooleanThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriByteToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToDoubleFunction<X> composeFromByte(
            @Nonnull ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableBytePredicate<? extends X> before2,
            @Nonnull ThrowableBytePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriCharToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharToDoubleFunction<X> composeFromChar(
            @Nonnull ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableCharPredicate<? extends X> before2,
            @Nonnull ThrowableCharPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableDoubleTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableDoubleTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleTernaryOperator<X> composeFromDouble(
            @Nonnull ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableDoublePredicate<? extends X> before2,
            @Nonnull ThrowableDoublePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriFloatToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatToDoubleFunction<X> composeFromFloat(
            @Nonnull ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableFloatPredicate<? extends X> before2,
            @Nonnull ThrowableFloatPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriIntToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToDoubleFunction<X> composeFromInt(
            @Nonnull ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableIntPredicate<? extends X> before2,
            @Nonnull ThrowableIntPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriLongToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongToDoubleFunction<X> composeFromLong(
            @Nonnull ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableLongPredicate<? extends X> before2,
            @Nonnull ThrowableLongPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriShortToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortToDoubleFunction<X> composeFromShort(
            @Nonnull ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableShortPredicate<? extends X> before2,
            @Nonnull ThrowableShortPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableObjBiBooleanFunction<T, S, X> andThen(
            @Nonnull ThrowableDoubleFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyThrows(applyAsDoubleThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableObjBiBooleanPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableObjBiBooleanPredicate<T, X> andThenToBoolean(
            @Nonnull ThrowableDoublePredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.testThrows(applyAsDoubleThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiBooleanToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToByteFunction<T, X> andThenToByte(
            @Nonnull ThrowableDoubleToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsByteThrows(applyAsDoubleThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiBooleanToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToCharFunction<T, X> andThenToChar(
            @Nonnull ThrowableDoubleToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsCharThrows(applyAsDoubleThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToDoubleFunction} that first applies this function to its input,
     * and then applies the {@code after} operator to the result. This method is just convenience, to provide the
     * ability to transform this primitive function to an operation returning {@code double}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableObjBiBooleanToDoubleFunction} that first applies this function to its input,
     * and then applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToDoubleFunction<T, X> andThenToDouble(
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsDoubleThrows(applyAsDoubleThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToFloatFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiBooleanToFloatFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToFloatFunction<T, X> andThenToFloat(
            @Nonnull ThrowableDoubleToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsFloatThrows(applyAsDoubleThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToIntFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiBooleanToIntFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToIntFunction<T, X> andThenToInt(
            @Nonnull ThrowableDoubleToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsIntThrows(applyAsDoubleThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiBooleanToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToLongFunction<T, X> andThenToLong(
            @Nonnull ThrowableDoubleToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsLongThrows(applyAsDoubleThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToShortFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiBooleanToShortFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToShortFunction<T, X> andThenToShort(
            @Nonnull ThrowableDoubleToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsShortThrows(applyAsDoubleThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableDoubleConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableObjBiBooleanConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableDoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableObjBiBooleanConsumer<T, X> consume(@Nonnull ThrowableDoubleConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.acceptThrows(applyAsDoubleThrows(t, value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableObjBiBooleanToDoubleFunction}. Whenever it is
     * called, the mapping between the input parameters and the return value is preserved in a cache, making subsequent
     * calls returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableObjBiBooleanToDoubleFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableObjBiBooleanToDoubleFunction<T, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, Boolean, Boolean>, Double> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ThrowableObjBiBooleanToDoubleFunction<T, X> & Memoized) (t, value1, value2) -> {
                double returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, value1, value2), ThrowableFunction.of(
                            key -> applyAsDoubleThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} which represents this {@link
     * ThrowableObjBiBooleanToDoubleFunction}. Thereby the primitive input argument for this function is autoboxed. This
     * method provides the possibility to use this {@code ThrowableObjBiBooleanToDoubleFunction} with methods provided
     * by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTriFunction} which represents this {@code
     * ThrowableObjBiBooleanToDoubleFunction}.
     */
    @Nonnull
    default ThrowableTriFunction<T, Boolean, Boolean, Double, X> boxed() {
        return this::applyAsDoubleThrows;
    }

    /**
     * Returns a composed {@link ObjBiBooleanToDoubleFunction} that applies this function to its input and nests the
     * thrown {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link ObjBiBooleanToDoubleFunction} that applies this function to its input and nests the
     * thrown {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ObjBiBooleanToDoubleFunction<T> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ObjBiBooleanToDoubleFunction} that applies this function to its input and nests the
     * thrown {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown
     * {@code Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ObjBiBooleanToDoubleFunction} that applies this function to its input and nests the
     * thrown {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ObjBiBooleanToDoubleFunction<T> nest(
            @Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ObjBiBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover}
     * operation is represented by a curried operation which is called with throwable information and same arguments of
     * this function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link ObjBiBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ObjBiBooleanToDoubleFunction<T> recover(
            @Nonnull Function<? super Throwable, ? extends ObjBiBooleanToDoubleFunction<? super T>> recover) {
        Objects.requireNonNull(recover);
        return (t, value1, value2) -> {
            try {
                return applyAsDoubleThrows(t, value1, value2);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                ObjBiBooleanToDoubleFunction<? super T> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsDouble(t, value1, value2);
            }
        };
    }

    /**
     * Returns a composed {@link ObjBiBooleanToDoubleFunction} that applies this function to its input and sneakily
     * throws the thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This
     * means that each throwable thrown from the returned composed function behaves exactly the same as an
     * <em>unchecked</em> throwable does. As a result, there is no need to handle the throwable of this function in the
     * returned composed function by either wrapping it in an <em>unchecked</em> throwable or to declare it in the
     * {@code throws} clause, as it would be done in a non sneaky throwing function.
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
     * @return A composed {@link ObjBiBooleanToDoubleFunction} that applies this function to its input and sneakily
     * throws the thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ObjBiBooleanToDoubleFunction<T> sneakyThrow() {
        return (t, value1, value2) -> {
            try {
                return applyAsDoubleThrows(t, value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
