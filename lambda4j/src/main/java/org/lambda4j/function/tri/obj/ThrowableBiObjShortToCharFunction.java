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
import org.lambda4j.consumer.ThrowableCharConsumer;
import org.lambda4j.consumer.tri.obj.ThrowableBiObjShortConsumer;
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
import org.lambda4j.function.bi.obj.ThrowableObjShortToCharFunction;
import org.lambda4j.function.bi.to.ThrowableToCharBiFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
import org.lambda4j.function.conversion.ThrowableByteToShortFunction;
import org.lambda4j.function.conversion.ThrowableCharToByteFunction;
import org.lambda4j.function.conversion.ThrowableCharToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableCharToFloatFunction;
import org.lambda4j.function.conversion.ThrowableCharToIntFunction;
import org.lambda4j.function.conversion.ThrowableCharToLongFunction;
import org.lambda4j.function.conversion.ThrowableCharToShortFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToShortFunction;
import org.lambda4j.function.conversion.ThrowableFloatToShortFunction;
import org.lambda4j.function.conversion.ThrowableIntToShortFunction;
import org.lambda4j.function.conversion.ThrowableLongToShortFunction;
import org.lambda4j.function.conversion.ThrowableShortToCharFunction;
import org.lambda4j.function.to.ThrowableToCharFunction;
import org.lambda4j.function.to.ThrowableToShortFunction;
import org.lambda4j.function.tri.ThrowableTriFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriBooleanToCharFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToCharFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToCharFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriFloatToCharFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriIntToCharFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToCharFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToCharFunction;
import org.lambda4j.function.tri.to.ThrowableToCharTriFunction;
import org.lambda4j.operator.ternary.ThrowableCharTernaryOperator;
import org.lambda4j.operator.unary.ThrowableCharUnaryOperator;
import org.lambda4j.operator.unary.ThrowableShortUnaryOperator;
import org.lambda4j.predicate.ThrowableCharPredicate;
import org.lambda4j.predicate.tri.obj.ThrowableBiObjShortPredicate;

/**
 * Represents an operation that accepts two object-valued and one {@code short}-valued input argument and produces a
 * {@code char}-valued result which is able to throw any {@link Throwable}. This is a (reference, reference, short)
 * specialization of {@link ThrowableTriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsCharThrows(Object, Object, short)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableTriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiObjShortToCharFunction<T, U, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableBiObjShortToCharFunction} based on a lambda expression or a method reference.
     * Thereby the given lambda expression or method reference is returned on an as-is basis to implicitly transform it
     * to the desired type. With this method, it is possible to ensure that correct type is used from lambda expression
     * or method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBiObjShortToCharFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U, X extends Throwable> ThrowableBiObjShortToCharFunction<T, U, X> of(
            @Nullable ThrowableBiObjShortToCharFunction<T, U, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBiObjShortToCharFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ThrowableBiObjShortToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, U, X extends Throwable> char call(
            @Nonnull ThrowableBiObjShortToCharFunction<? super T, ? super U, ? extends X> function, T t, U u,
            short value) throws X {
        Objects.requireNonNull(function);
        return function.applyAsCharThrows(t, u, value);
    }

    /**
     * Creates a {@link ThrowableBiObjShortToCharFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@link ThrowableToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiObjShortToCharFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortToCharFunction<T, U, X> onlyFirst(
            @Nonnull ThrowableToCharFunction<? super T, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsCharThrows(t);
    }

    /**
     * Creates a {@link ThrowableBiObjShortToCharFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@link ThrowableToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiObjShortToCharFunction} which uses the {@code second} parameter of this one
     * as argument for the given {@code ThrowableToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortToCharFunction<T, U, X> onlySecond(
            @Nonnull ThrowableToCharFunction<? super U, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsCharThrows(u);
    }

    /**
     * Creates a {@link ThrowableBiObjShortToCharFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@link ThrowableShortToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableBiObjShortToCharFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableShortToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortToCharFunction<T, U, X> onlyThird(
            @Nonnull ThrowableShortToCharFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsCharThrows(value);
    }

    /**
     * Creates a {@link ThrowableBiObjShortToCharFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableBiObjShortToCharFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortToCharFunction<T, U, X> constant(char ret) {
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
    char applyAsCharThrows(T t, U u, short value) throws X;

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
    default char applyAsCharThrows(@Nonnull Pair<T, U> tuple, short value) throws X {
        Objects.requireNonNull(tuple);
        return applyAsCharThrows(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link
     * ThrowableObjShortToCharFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ThrowableObjShortToCharFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableObjShortToCharFunction<U, X> applyAsCharThrowsPartially(T t) {
        return (u, value) -> applyAsCharThrows(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableShortToCharFunction}
     * as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param u The second argument to this function used to partially apply this function
     * @return A {@code ThrowableShortToCharFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableShortToCharFunction<X> applyAsCharThrowsPartially(T t, U u) {
        return value -> applyAsCharThrows(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToCharBiFunction} as
     * result.
     *
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code ThrowableToCharBiFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToCharBiFunction<T, U, X> applyAsCharThrowsPartially(short value) {
        return (t, u) -> applyAsCharThrows(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToCharFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code ThrowableToCharFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToCharFunction<U, X> applyAsCharThrowsPartially(T t, short value) {
        return u -> applyAsCharThrows(t, u, value);
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
     * Returns a composed {@link ThrowableToCharTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableToCharTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToCharTriFunction<A, B, C, X> compose(
            @Nonnull ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull ThrowableFunction<? super B, ? extends U, ? extends X> before2,
            @Nonnull ThrowableToShortFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsCharThrows(before1.applyThrows(a), before2.applyThrows(b),
                before3.applyAsShortThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriBooleanToCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToCharFunction<X> composeFromBoolean(
            @Nonnull ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableBooleanFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableBooleanToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriByteToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToCharFunction<X> composeFromByte(
            @Nonnull ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableByteFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableByteToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableCharTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableCharTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharTernaryOperator<X> composeFromChar(
            @Nonnull ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableCharFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableCharToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriDoubleToCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleToCharFunction<X> composeFromDouble(
            @Nonnull ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableDoubleFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableDoubleToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriFloatToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatToCharFunction<X> composeFromFloat(
            @Nonnull ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableFloatFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableFloatToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriIntToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToCharFunction<X> composeFromInt(
            @Nonnull ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableIntFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableIntToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriLongToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongToCharFunction<X> composeFromLong(
            @Nonnull ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableLongFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableLongToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code ThrowableTriShortToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortToCharFunction<X> composeFromShort(
            @Nonnull ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableShortFunction<? extends U, ? extends X> before2,
            @Nonnull ThrowableShortUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBiObjShortFunction<T, U, S, X> andThen(
            @Nonnull ThrowableCharFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyThrows(applyAsCharThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableBiObjShortPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiObjShortPredicate<T, U, X> andThenToBoolean(
            @Nonnull ThrowableCharPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.testThrows(applyAsCharThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjShortToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiObjShortToByteFunction<T, U, X> andThenToByte(
            @Nonnull ThrowableCharToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsByteThrows(applyAsCharThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableBiObjShortToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiObjShortToCharFunction<T, U, X> andThenToChar(
            @Nonnull ThrowableCharUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsCharThrows(applyAsCharThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjShortToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiObjShortToDoubleFunction<T, U, X> andThenToDouble(
            @Nonnull ThrowableCharToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsDoubleThrows(applyAsCharThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjShortToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiObjShortToFloatFunction<T, U, X> andThenToFloat(
            @Nonnull ThrowableCharToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsFloatThrows(applyAsCharThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToIntFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjShortToIntFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiObjShortToIntFunction<T, U, X> andThenToInt(
            @Nonnull ThrowableCharToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsIntThrows(applyAsCharThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjShortToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiObjShortToLongFunction<T, U, X> andThenToLong(
            @Nonnull ThrowableCharToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsLongThrows(applyAsCharThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjShortToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiObjShortToShortFunction<T, U, X> andThenToShort(
            @Nonnull ThrowableCharToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsShortThrows(applyAsCharThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableCharConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiObjShortConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableCharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiObjShortConsumer<T, U, X> consume(@Nonnull ThrowableCharConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.acceptThrows(applyAsCharThrows(t, u, value));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ThrowableObjShortToCharFunction<Pair<T, U>, X> tupled() {
        return this::applyAsCharThrows;
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableBiObjShortToCharFunction}. Whenever it is called,
     * the mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableBiObjShortToCharFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableBiObjShortToCharFunction<T, U, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, U, Short>, Character> cache = new ConcurrentHashMap<>();
            return (ThrowableBiObjShortToCharFunction<T, U, X> & Memoized) (t, u, value) -> {
                return cache.computeIfAbsent(Triple.of(t, u, value),
                        ThrowableFunction.of(key -> applyAsCharThrows(key.getLeft(), key.getMiddle(), key.getRight())));
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} which represents this {@link ThrowableBiObjShortToCharFunction}.
     * Thereby the primitive input argument for this function is autoboxed. This method provides the possibility to use
     * this {@code ThrowableBiObjShortToCharFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTriFunction} which represents this {@code ThrowableBiObjShortToCharFunction}.
     */
    @Nonnull
    default ThrowableTriFunction<T, U, Short, Character, X> boxed() {
        return this::applyAsCharThrows;
    }

    /**
     * Returns a composed {@link BiObjShortToCharFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link BiObjShortToCharFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default BiObjShortToCharFunction<T, U> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link BiObjShortToCharFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link BiObjShortToCharFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default BiObjShortToCharFunction<T, U> nest(
            @Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        Objects.requireNonNull(mapper);
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link BiObjShortToCharFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover}
     * operation is represented by a curried operation which is called with throwable information and same arguments of
     * this function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link BiObjShortToCharFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default BiObjShortToCharFunction<T, U> recover(
            @Nonnull Function<? super Throwable, ? extends BiObjShortToCharFunction<? super T, ? super U>> recover) {
        Objects.requireNonNull(recover);
        return (t, u, value) -> {
            try {
                return applyAsCharThrows(t, u, value);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                BiObjShortToCharFunction<? super T, ? super U> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsChar(t, u, value);
            }
        };
    }

    /**
     * Returns a composed {@link BiObjShortToCharFunction} that applies this function to its input and sneakily throws
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
     * @return A composed {@link BiObjShortToCharFunction} that applies this function to its input and sneakily throws
     * the thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default BiObjShortToCharFunction<T, U> sneakyThrow() {
        return (t, u, value) -> {
            try {
                return applyAsCharThrows(t, u, value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
