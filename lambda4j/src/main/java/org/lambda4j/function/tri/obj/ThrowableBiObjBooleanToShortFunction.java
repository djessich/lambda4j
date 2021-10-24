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

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableShortConsumer;
import org.lambda4j.consumer.tri.obj.ThrowableBiObjBooleanConsumer;
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
import org.lambda4j.function.bi.obj.ThrowableObjBooleanToShortFunction;
import org.lambda4j.function.bi.to.ThrowableToShortBiFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
import org.lambda4j.function.conversion.ThrowableShortToByteFunction;
import org.lambda4j.function.conversion.ThrowableShortToCharFunction;
import org.lambda4j.function.conversion.ThrowableShortToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableShortToFloatFunction;
import org.lambda4j.function.conversion.ThrowableShortToIntFunction;
import org.lambda4j.function.conversion.ThrowableShortToLongFunction;
import org.lambda4j.function.to.ThrowableToShortFunction;
import org.lambda4j.function.tri.ThrowableTriFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriBooleanToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriCharToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriFloatToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriIntToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToShortFunction;
import org.lambda4j.function.tri.to.ThrowableToShortTriFunction;
import org.lambda4j.operator.ternary.ThrowableShortTernaryOperator;
import org.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import org.lambda4j.operator.unary.ThrowableShortUnaryOperator;
import org.lambda4j.predicate.ThrowableBytePredicate;
import org.lambda4j.predicate.ThrowableCharPredicate;
import org.lambda4j.predicate.ThrowableDoublePredicate;
import org.lambda4j.predicate.ThrowableFloatPredicate;
import org.lambda4j.predicate.ThrowableIntPredicate;
import org.lambda4j.predicate.ThrowableLongPredicate;
import org.lambda4j.predicate.ThrowablePredicate;
import org.lambda4j.predicate.ThrowableShortPredicate;
import org.lambda4j.predicate.tri.obj.ThrowableBiObjBooleanPredicate;

/**
 * Represents an operation that accepts two object-valued and one {@code boolean}-valued input argument and produces a
 * {@code short}-valued result which is able to throw any {@link Throwable}. This is a (reference, reference, boolean)
 * specialization of {@link ThrowableTriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShortThrows(Object, Object,
 * boolean)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableTriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiObjBooleanToShortFunction<T, U, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableBiObjBooleanToShortFunction} based on a lambda expression or a method reference.
     * Thereby the given lambda expression or method reference is returned on an as-is basis to implicitly transform it
     * to the desired type. With this method, it is possible to ensure that correct type is used from lambda expression
     * or method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBiObjBooleanToShortFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U, X extends Throwable> ThrowableBiObjBooleanToShortFunction<T, U, X> of(
            @Nullable ThrowableBiObjBooleanToShortFunction<T, U, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBiObjBooleanToShortFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ThrowableBiObjBooleanToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, U, X extends Throwable> short call(
            @Nonnull ThrowableBiObjBooleanToShortFunction<? super T, ? super U, ? extends X> function, T t, U u,
            boolean value) throws X {
        Objects.requireNonNull(function);
        return function.applyAsShortThrows(t, u, value);
    }

    /**
     * Creates a {@link ThrowableBiObjBooleanToShortFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@link ThrowableToShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiObjBooleanToShortFunction} which uses the {@code first} parameter of this one
     * as argument for the given {@code ThrowableToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjBooleanToShortFunction<T, U, X> onlyFirst(
            @Nonnull ThrowableToShortFunction<? super T, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsShortThrows(t);
    }

    /**
     * Creates a {@link ThrowableBiObjBooleanToShortFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@link ThrowableToShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiObjBooleanToShortFunction} which uses the {@code second} parameter of this
     * one as argument for the given {@code ThrowableToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjBooleanToShortFunction<T, U, X> onlySecond(
            @Nonnull ThrowableToShortFunction<? super U, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsShortThrows(u);
    }

    /**
     * Creates a {@link ThrowableBiObjBooleanToShortFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@link ThrowableBooleanToShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableBiObjBooleanToShortFunction} which uses the {@code third} parameter of this one
     * as argument for the given {@code ThrowableBooleanToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjBooleanToShortFunction<T, U, X> onlyThird(
            @Nonnull ThrowableBooleanToShortFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsShortThrows(value);
    }

    /**
     * Creates a {@link ThrowableBiObjBooleanToShortFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableBiObjBooleanToShortFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjBooleanToShortFunction<T, U, X> constant(short ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    short applyAsShortThrows(T t, U u, boolean value) throws X;

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @param value The primitive value to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default short applyAsShortThrows(@Nonnull Pair<T, U> tuple, boolean value) throws X {
        Objects.requireNonNull(tuple);
        return applyAsShortThrows(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link
     * ThrowableObjBooleanToShortFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ThrowableObjBooleanToShortFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableObjBooleanToShortFunction<U, X> applyAsShortThrowsPartially(T t) {
        return (u, value) -> applyAsShortThrows(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link
     * ThrowableBooleanToShortFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param u The second argument to this function used to partially apply this function
     * @return A {@code ThrowableBooleanToShortFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableBooleanToShortFunction<X> applyAsShortThrowsPartially(T t, U u) {
        return value -> applyAsShortThrows(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToShortBiFunction} as
     * result.
     *
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code ThrowableToShortBiFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToShortBiFunction<T, U, X> applyAsShortThrowsPartially(boolean value) {
        return (t, u) -> applyAsShortThrows(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToShortFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code ThrowableToShortFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToShortFunction<U, X> applyAsShortThrowsPartially(T t, boolean value) {
        return u -> applyAsShortThrows(t, u, value);
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
     * Returns a composed {@link ThrowableToShortTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given predicate, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableToShortTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToShortTriFunction<A, B, C, X> compose(
            @Nonnull ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull ThrowableFunction<? super B, ? extends U, ? extends X> before2,
            @Nonnull ThrowablePredicate<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsShortThrows(before1.applyThrows(a), before2.applyThrows(b), before3.testThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code ThrowableTriBooleanToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToShortFunction<X> composeFromBoolean(
            @Nonnull ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableBooleanFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableBooleanUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.applyAsBooleanThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriByteToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToShortFunction<X> composeFromByte(
            @Nonnull ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableByteFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableBytePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriCharToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharToShortFunction<X> composeFromChar(
            @Nonnull ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableCharFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableCharPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriDoubleToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleToShortFunction<X> composeFromDouble(
            @Nonnull ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableDoubleFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableDoublePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriFloatToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatToShortFunction<X> composeFromFloat(
            @Nonnull ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableFloatFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableFloatPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriIntToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToShortFunction<X> composeFromInt(
            @Nonnull ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableIntFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableIntPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriLongToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongToShortFunction<X> composeFromLong(
            @Nonnull ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableLongFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableLongPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableShortTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableShortTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortTernaryOperator<X> composeFromShort(
            @Nonnull ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableShortFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableShortPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShortThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBiObjBooleanFunction<T, U, S, X> andThen(
            @Nonnull ThrowableShortFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyThrows(applyAsShortThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiObjBooleanPredicate<T, U, X> andThenToBoolean(
            @Nonnull ThrowableShortPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.testThrows(applyAsShortThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToByteFunction<T, U, X> andThenToByte(
            @Nonnull ThrowableShortToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsByteThrows(applyAsShortThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToCharFunction<T, U, X> andThenToChar(
            @Nonnull ThrowableShortToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsCharThrows(applyAsShortThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanToDoubleFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanToDoubleFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToDoubleFunction<T, U, X> andThenToDouble(
            @Nonnull ThrowableShortToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsDoubleThrows(applyAsShortThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanToFloatFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanToFloatFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToFloatFunction<T, U, X> andThenToFloat(
            @Nonnull ThrowableShortToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsFloatThrows(applyAsShortThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanToIntFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanToIntFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToIntFunction<T, U, X> andThenToInt(
            @Nonnull ThrowableShortToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsIntThrows(applyAsShortThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToLongFunction<T, U, X> andThenToLong(
            @Nonnull ThrowableShortToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsLongThrows(applyAsShortThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanToShortFunction} that first applies this function to its input,
     * and then applies the {@code after} operator to the result. This method is just convenience, to provide the
     * ability to transform this primitive function to an operation returning {@code short}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanToShortFunction} that first applies this function to its input,
     * and then applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToShortFunction<T, U, X> andThenToShort(
            @Nonnull ThrowableShortUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsShortThrows(applyAsShortThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableShortConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiObjBooleanConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiObjBooleanConsumer<T, U, X> consume(
            @Nonnull ThrowableShortConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.acceptThrows(applyAsShortThrows(t, u, value));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ThrowableObjBooleanToShortFunction<Pair<T, U>, X> tupled() {
        return this::applyAsShortThrows;
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableBiObjBooleanToShortFunction}. Whenever it is called,
     * the mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableBiObjBooleanToShortFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableBiObjBooleanToShortFunction<T, U, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, U, Boolean>, Short> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ThrowableBiObjBooleanToShortFunction<T, U, X> & Memoized) (t, u, value) -> {
                short returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, u, value), ThrowableFunction.of(
                            key -> applyAsShortThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} which represents this {@link
     * ThrowableBiObjBooleanToShortFunction}. Thereby the primitive input argument for this function is autoboxed. This
     * method provides the possibility to use this {@code ThrowableBiObjBooleanToShortFunction} with methods provided by
     * the {@code JDK}.
     *
     * @return A composed {@code ThrowableTriFunction} which represents this {@code
     * ThrowableBiObjBooleanToShortFunction}.
     */
    @Nonnull
    default ThrowableTriFunction<T, U, Boolean, Short, X> boxed() {
        return this::applyAsShortThrows;
    }

    /**
     * Returns a composed {@link BiObjBooleanToShortFunction} that applies this function to its input and nests the
     * thrown {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link BiObjBooleanToShortFunction} that applies this function to its input and nests the
     * thrown {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default BiObjBooleanToShortFunction<T, U> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link BiObjBooleanToShortFunction} that applies this function to its input and nests the
     * thrown {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown
     * {@code Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link BiObjBooleanToShortFunction} that applies this function to its input and nests the
     * thrown {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default BiObjBooleanToShortFunction<T, U> nest(
            @Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link BiObjBooleanToShortFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover}
     * operation is represented by a curried operation which is called with throwable information and same arguments of
     * this function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link BiObjBooleanToShortFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default BiObjBooleanToShortFunction<T, U> recover(
            @Nonnull Function<? super Throwable, ? extends BiObjBooleanToShortFunction<? super T, ? super U>> recover) {
        Objects.requireNonNull(recover);
        return (t, u, value) -> {
            try {
                return applyAsShortThrows(t, u, value);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                BiObjBooleanToShortFunction<? super T, ? super U> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsShort(t, u, value);
            }
        };
    }

    /**
     * Returns a composed {@link BiObjBooleanToShortFunction} that applies this function to its input and sneakily
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
     * @return A composed {@link BiObjBooleanToShortFunction} that applies this function to its input and sneakily
     * throws the thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default BiObjBooleanToShortFunction<T, U> sneakyThrow() {
        return (t, u, value) -> {
            try {
                return applyAsShortThrows(t, u, value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
