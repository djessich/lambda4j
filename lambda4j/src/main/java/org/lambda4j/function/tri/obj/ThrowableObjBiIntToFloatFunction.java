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
import org.lambda4j.consumer.ThrowableFloatConsumer;
import org.lambda4j.consumer.tri.obj.ThrowableObjBiIntConsumer;
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
import org.lambda4j.function.bi.conversion.ThrowableBiIntToFloatFunction;
import org.lambda4j.function.bi.obj.ThrowableObjIntToFloatFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToIntFunction;
import org.lambda4j.function.conversion.ThrowableByteToIntFunction;
import org.lambda4j.function.conversion.ThrowableCharToIntFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToIntFunction;
import org.lambda4j.function.conversion.ThrowableFloatToByteFunction;
import org.lambda4j.function.conversion.ThrowableFloatToCharFunction;
import org.lambda4j.function.conversion.ThrowableFloatToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableFloatToIntFunction;
import org.lambda4j.function.conversion.ThrowableFloatToLongFunction;
import org.lambda4j.function.conversion.ThrowableFloatToShortFunction;
import org.lambda4j.function.conversion.ThrowableIntToFloatFunction;
import org.lambda4j.function.conversion.ThrowableLongToIntFunction;
import org.lambda4j.function.conversion.ThrowableShortToIntFunction;
import org.lambda4j.function.to.ThrowableToFloatFunction;
import org.lambda4j.function.to.ThrowableToIntFunction;
import org.lambda4j.function.tri.ThrowableTriFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriBooleanToFloatFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToFloatFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriCharToFloatFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToFloatFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriIntToFloatFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToFloatFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToFloatFunction;
import org.lambda4j.function.tri.to.ThrowableToFloatTriFunction;
import org.lambda4j.operator.ternary.ThrowableFloatTernaryOperator;
import org.lambda4j.operator.unary.ThrowableFloatUnaryOperator;
import org.lambda4j.operator.unary.ThrowableIntUnaryOperator;
import org.lambda4j.predicate.ThrowableFloatPredicate;
import org.lambda4j.predicate.tri.obj.ThrowableObjBiIntPredicate;

/**
 * Represents an operation that accepts one object-valued and two {@code int}-valued input arguments and produces a
 * {@code float}-valued result which is able to throw any {@link Throwable}. This is a (reference, int, int)
 * specialization of {@link ThrowableTriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloatThrows(Object, int, int)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableTriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableObjBiIntToFloatFunction<T, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableObjBiIntToFloatFunction} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableObjBiIntToFloatFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, X extends Throwable> ThrowableObjBiIntToFloatFunction<T, X> of(
            @Nullable ThrowableObjBiIntToFloatFunction<T, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableObjBiIntToFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The result from the given {@code ThrowableObjBiIntToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, X extends Throwable> float call(
            @Nonnull ThrowableObjBiIntToFloatFunction<? super T, ? extends X> function, T t, int value1,
            int value2) throws X {
        Objects.requireNonNull(function);
        return function.applyAsFloatThrows(t, value1, value2);
    }

    /**
     * Creates a {@link ThrowableObjBiIntToFloatFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@link ThrowableToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableObjBiIntToFloatFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiIntToFloatFunction<T, X> onlyFirst(
            @Nonnull ThrowableToFloatFunction<? super T, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsFloatThrows(t);
    }

    /**
     * Creates a {@link ThrowableObjBiIntToFloatFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@link ThrowableIntToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableObjBiIntToFloatFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableIntToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiIntToFloatFunction<T, X> onlySecond(
            @Nonnull ThrowableIntToFloatFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsFloatThrows(value1);
    }

    /**
     * Creates a {@link ThrowableObjBiIntToFloatFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@link ThrowableIntToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableObjBiIntToFloatFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableIntToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiIntToFloatFunction<T, X> onlyThird(
            @Nonnull ThrowableIntToFloatFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsFloatThrows(value2);
    }

    /**
     * Creates a {@link ThrowableObjBiIntToFloatFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableObjBiIntToFloatFunction} which always returns a given value.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiIntToFloatFunction<T, X> constant(float ret) {
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
    float applyAsFloatThrows(T t, int value1, int value2) throws X;

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableBiIntToFloatFunction}
     * as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ThrowableBiIntToFloatFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableBiIntToFloatFunction<X> applyAsFloatThrowsPartially(T t) {
        return (value1, value2) -> applyAsFloatThrows(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableIntToFloatFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value1 The second argument to this function used to partially apply this function
     * @return A {@code ThrowableIntToFloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableIntToFloatFunction<X> applyAsFloatThrowsPartially(T t, int value1) {
        return value2 -> applyAsFloatThrows(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableObjIntToFloatFunction}
     * as result.
     *
     * @param value1 The second argument to this function used to partially apply this function
     * @return A {@code ThrowableObjIntToFloatFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableObjIntToFloatFunction<T, X> applyAsFloatThrowsPartially(int value1) {
        return (t, value2) -> applyAsFloatThrows(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToFloatFunction} as
     * result.
     *
     * @param value1 The second argument to this function used to partially apply this function
     * @param value2 The third argument to this function used to partially apply this function
     * @return A {@code ThrowableToFloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToFloatFunction<T, X> applyAsFloatThrowsPartially(int value1, int value2) {
        return t -> applyAsFloatThrows(t, value1, value2);
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
     * Returns a composed {@link ThrowableToFloatTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableToFloatTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToFloatTriFunction<A, B, C, X> compose(
            @Nonnull ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull ThrowableToIntFunction<? super B, ? extends X> before2,
            @Nonnull ThrowableToIntFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsFloatThrows(before1.applyThrows(a), before2.applyAsIntThrows(b),
                before3.applyAsIntThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriBooleanToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToFloatFunction<X> composeFromBoolean(
            @Nonnull ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableBooleanToIntFunction<? extends X> before2,
            @Nonnull ThrowableBooleanToIntFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyThrows(value1),
                before2.applyAsIntThrows(value2),
                before3.applyAsIntThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriByteToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToFloatFunction<X> composeFromByte(
            @Nonnull ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableByteToIntFunction<? extends X> before2,
            @Nonnull ThrowableByteToIntFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyThrows(value1),
                before2.applyAsIntThrows(value2),
                before3.applyAsIntThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriCharToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharToFloatFunction<X> composeFromChar(
            @Nonnull ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableCharToIntFunction<? extends X> before2,
            @Nonnull ThrowableCharToIntFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyThrows(value1),
                before2.applyAsIntThrows(value2),
                before3.applyAsIntThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriDoubleToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleToFloatFunction<X> composeFromDouble(
            @Nonnull ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableDoubleToIntFunction<? extends X> before2,
            @Nonnull ThrowableDoubleToIntFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyThrows(value1),
                before2.applyAsIntThrows(value2),
                before3.applyAsIntThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableFloatTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableFloatTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatTernaryOperator<X> composeFromFloat(
            @Nonnull ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableFloatToIntFunction<? extends X> before2,
            @Nonnull ThrowableFloatToIntFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyThrows(value1),
                before2.applyAsIntThrows(value2),
                before3.applyAsIntThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code ThrowableTriIntToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToFloatFunction<X> composeFromInt(
            @Nonnull ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableIntUnaryOperator<? extends X> before2,
            @Nonnull ThrowableIntUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyThrows(value1),
                before2.applyAsIntThrows(value2),
                before3.applyAsIntThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriLongToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongToFloatFunction<X> composeFromLong(
            @Nonnull ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableLongToIntFunction<? extends X> before2,
            @Nonnull ThrowableLongToIntFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyThrows(value1),
                before2.applyAsIntThrows(value2),
                before3.applyAsIntThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriShortToFloatFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortToFloatFunction<X> composeFromShort(
            @Nonnull ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableShortToIntFunction<? extends X> before2,
            @Nonnull ThrowableShortToIntFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloatThrows(before1.applyThrows(value1),
                before2.applyAsIntThrows(value2),
                before3.applyAsIntThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableObjBiIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableObjBiIntFunction<T, S, X> andThen(
            @Nonnull ThrowableFloatFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyThrows(applyAsFloatThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiIntPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableObjBiIntPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableObjBiIntPredicate<T, X> andThenToBoolean(
            @Nonnull ThrowableFloatPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.testThrows(applyAsFloatThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiIntToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiIntToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableObjBiIntToByteFunction<T, X> andThenToByte(
            @Nonnull ThrowableFloatToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsByteThrows(applyAsFloatThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiIntToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiIntToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableObjBiIntToCharFunction<T, X> andThenToChar(
            @Nonnull ThrowableFloatToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsCharThrows(applyAsFloatThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiIntToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiIntToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableObjBiIntToDoubleFunction<T, X> andThenToDouble(
            @Nonnull ThrowableFloatToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsDoubleThrows(applyAsFloatThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiIntToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableObjBiIntToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableObjBiIntToFloatFunction<T, X> andThenToFloat(
            @Nonnull ThrowableFloatUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsFloatThrows(applyAsFloatThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiIntToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiIntToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableObjBiIntToIntFunction<T, X> andThenToInt(
            @Nonnull ThrowableFloatToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsIntThrows(applyAsFloatThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiIntToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiIntToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableObjBiIntToLongFunction<T, X> andThenToLong(
            @Nonnull ThrowableFloatToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsLongThrows(applyAsFloatThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiIntToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiIntToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableObjBiIntToShortFunction<T, X> andThenToShort(
            @Nonnull ThrowableFloatToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsShortThrows(applyAsFloatThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiIntConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableFloatConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableObjBiIntConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableFloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableObjBiIntConsumer<T, X> consume(@Nonnull ThrowableFloatConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.acceptThrows(applyAsFloatThrows(t, value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableObjBiIntToFloatFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableObjBiIntToFloatFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableObjBiIntToFloatFunction<T, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, Integer, Integer>, Float> cache = new ConcurrentHashMap<>();
            return (ThrowableObjBiIntToFloatFunction<T, X> & Memoized) (t, value1, value2) -> {
                return cache.computeIfAbsent(Triple.of(t, value1, value2), ThrowableFunction.of(
                        key -> applyAsFloatThrows(key.getLeft(), key.getMiddle(), key.getRight())));
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} which represents this {@link ThrowableObjBiIntToFloatFunction}.
     * Thereby the primitive input argument for this function is autoboxed. This method provides the possibility to use
     * this {@code ThrowableObjBiIntToFloatFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTriFunction} which represents this {@code ThrowableObjBiIntToFloatFunction}.
     */
    @Nonnull
    default ThrowableTriFunction<T, Integer, Integer, Float, X> boxed() {
        return this::applyAsFloatThrows;
    }

    /**
     * Returns a composed {@link ObjBiIntToFloatFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link ObjBiIntToFloatFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ObjBiIntToFloatFunction<T> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ObjBiIntToFloatFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ObjBiIntToFloatFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ObjBiIntToFloatFunction<T> nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        Objects.requireNonNull(mapper);
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ObjBiIntToFloatFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover}
     * operation is represented by a curried operation which is called with throwable information and same arguments of
     * this function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link ObjBiIntToFloatFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ObjBiIntToFloatFunction<T> recover(
            @Nonnull Function<? super Throwable, ? extends ObjBiIntToFloatFunction<? super T>> recover) {
        Objects.requireNonNull(recover);
        return (t, value1, value2) -> {
            try {
                return applyAsFloatThrows(t, value1, value2);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                ObjBiIntToFloatFunction<? super T> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsFloat(t, value1, value2);
            }
        };
    }

    /**
     * Returns a composed {@link ObjBiIntToFloatFunction} that applies this function to its input and sneakily throws
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
     * @return A composed {@link ObjBiIntToFloatFunction} that applies this function to its input and sneakily throws
     * the thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ObjBiIntToFloatFunction<T> sneakyThrow() {
        return (t, value1, value2) -> {
            try {
                return applyAsFloatThrows(t, value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
