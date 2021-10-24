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
import org.lambda4j.consumer.ThrowableLongConsumer;
import org.lambda4j.consumer.tri.obj.ThrowableObjBiDoubleConsumer;
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
import org.lambda4j.function.bi.conversion.ThrowableBiDoubleToLongFunction;
import org.lambda4j.function.bi.obj.ThrowableObjDoubleToLongFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableByteToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableCharToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToLongFunction;
import org.lambda4j.function.conversion.ThrowableFloatToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableIntToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableLongToByteFunction;
import org.lambda4j.function.conversion.ThrowableLongToCharFunction;
import org.lambda4j.function.conversion.ThrowableLongToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableLongToFloatFunction;
import org.lambda4j.function.conversion.ThrowableLongToIntFunction;
import org.lambda4j.function.conversion.ThrowableLongToShortFunction;
import org.lambda4j.function.conversion.ThrowableShortToDoubleFunction;
import org.lambda4j.function.to.ThrowableToDoubleFunction;
import org.lambda4j.function.to.ThrowableToLongFunction;
import org.lambda4j.function.tri.ThrowableTriFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriBooleanToLongFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriByteToLongFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriCharToLongFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriDoubleToLongFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriFloatToLongFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriIntToLongFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriShortToLongFunction;
import org.lambda4j.function.tri.to.ThrowableToLongTriFunction;
import org.lambda4j.operator.ternary.ThrowableLongTernaryOperator;
import org.lambda4j.operator.unary.ThrowableDoubleUnaryOperator;
import org.lambda4j.operator.unary.ThrowableLongUnaryOperator;
import org.lambda4j.predicate.ThrowableLongPredicate;
import org.lambda4j.predicate.tri.obj.ThrowableObjBiDoublePredicate;

/**
 * Represents an operation that accepts one object-valued and two {@code double}-valued input arguments and produces a
 * {@code long}-valued result which is able to throw any {@link Throwable}. This is a (reference, double, double)
 * specialization of {@link ThrowableTriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLongThrows(Object, double, double)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableTriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableObjBiDoubleToLongFunction<T, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableObjBiDoubleToLongFunction} based on a lambda expression or a method reference.
     * Thereby the given lambda expression or method reference is returned on an as-is basis to implicitly transform it
     * to the desired type. With this method, it is possible to ensure that correct type is used from lambda expression
     * or method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableObjBiDoubleToLongFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, X extends Throwable> ThrowableObjBiDoubleToLongFunction<T, X> of(
            @Nullable ThrowableObjBiDoubleToLongFunction<T, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableObjBiDoubleToLongFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The result from the given {@code ThrowableObjBiDoubleToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, X extends Throwable> long call(
            @Nonnull ThrowableObjBiDoubleToLongFunction<? super T, ? extends X> function, T t, double value1,
            double value2) throws X {
        Objects.requireNonNull(function);
        return function.applyAsLongThrows(t, value1, value2);
    }

    /**
     * Creates a {@link ThrowableObjBiDoubleToLongFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@link ThrowableToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableObjBiDoubleToLongFunction} which uses the {@code first} parameter of this one
     * as argument for the given {@code ThrowableToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiDoubleToLongFunction<T, X> onlyFirst(
            @Nonnull ThrowableToLongFunction<? super T, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsLongThrows(t);
    }

    /**
     * Creates a {@link ThrowableObjBiDoubleToLongFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@link ThrowableDoubleToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableObjBiDoubleToLongFunction} which uses the {@code second} parameter of this one
     * as argument for the given {@code ThrowableDoubleToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiDoubleToLongFunction<T, X> onlySecond(
            @Nonnull ThrowableDoubleToLongFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsLongThrows(value1);
    }

    /**
     * Creates a {@link ThrowableObjBiDoubleToLongFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@link ThrowableDoubleToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableObjBiDoubleToLongFunction} which uses the {@code third} parameter of this one
     * as argument for the given {@code ThrowableDoubleToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiDoubleToLongFunction<T, X> onlyThird(
            @Nonnull ThrowableDoubleToLongFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsLongThrows(value2);
    }

    /**
     * Creates a {@link ThrowableObjBiDoubleToLongFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableObjBiDoubleToLongFunction} which always returns a given value.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiDoubleToLongFunction<T, X> constant(long ret) {
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
    long applyAsLongThrows(T t, double value1, double value2) throws X;

    /**
     * Applies this function partially to some arguments of this one, producing a {@link
     * ThrowableBiDoubleToLongFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ThrowableBiDoubleToLongFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableBiDoubleToLongFunction<X> applyAsLongThrowsPartially(T t) {
        return (value1, value2) -> applyAsLongThrows(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableDoubleToLongFunction}
     * as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value1 The second argument to this function used to partially apply this function
     * @return A {@code ThrowableDoubleToLongFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableDoubleToLongFunction<X> applyAsLongThrowsPartially(T t, double value1) {
        return value2 -> applyAsLongThrows(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link
     * ThrowableObjDoubleToLongFunction} as result.
     *
     * @param value1 The second argument to this function used to partially apply this function
     * @return A {@code ThrowableObjDoubleToLongFunction} that represents this function partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableObjDoubleToLongFunction<T, X> applyAsLongThrowsPartially(double value1) {
        return (t, value2) -> applyAsLongThrows(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToLongFunction} as
     * result.
     *
     * @param value1 The second argument to this function used to partially apply this function
     * @param value2 The third argument to this function used to partially apply this function
     * @return A {@code ThrowableToLongFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToLongFunction<T, X> applyAsLongThrowsPartially(double value1, double value2) {
        return t -> applyAsLongThrows(t, value1, value2);
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
     * Returns a composed {@link ThrowableToLongTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableToLongTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToLongTriFunction<A, B, C, X> compose(
            @Nonnull ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull ThrowableToDoubleFunction<? super B, ? extends X> before2,
            @Nonnull ThrowableToDoubleFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsLongThrows(before1.applyThrows(a), before2.applyAsDoubleThrows(b),
                before3.applyAsDoubleThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToLongFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriBooleanToLongFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToLongFunction<X> composeFromBoolean(
            @Nonnull ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.applyThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToLongFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriByteToLongFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToLongFunction<X> composeFromByte(
            @Nonnull ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableByteToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableByteToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.applyThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToLongFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriCharToLongFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharToLongFunction<X> composeFromChar(
            @Nonnull ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableCharToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableCharToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.applyThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToLongFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code ThrowableTriDoubleToLongFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleToLongFunction<X> composeFromDouble(
            @Nonnull ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> before2,
            @Nonnull ThrowableDoubleUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.applyThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToLongFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriFloatToLongFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatToLongFunction<X> composeFromFloat(
            @Nonnull ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableFloatToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableFloatToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.applyThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToLongFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriIntToLongFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToLongFunction<X> composeFromInt(
            @Nonnull ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableIntToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableIntToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.applyThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableLongTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableLongTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongTernaryOperator<X> composeFromLong(
            @Nonnull ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableLongToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableLongToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.applyThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToLongFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriShortToLongFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortToLongFunction<X> composeFromShort(
            @Nonnull ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableShortToDoubleFunction<? extends X> before2,
            @Nonnull ThrowableShortToDoubleFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLongThrows(before1.applyThrows(value1),
                before2.applyAsDoubleThrows(value2),
                before3.applyAsDoubleThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableObjBiDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableObjBiDoubleFunction<T, S, X> andThen(
            @Nonnull ThrowableLongFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyThrows(applyAsLongThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiDoublePredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableObjBiDoublePredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableObjBiDoublePredicate<T, X> andThenToBoolean(
            @Nonnull ThrowableLongPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.testThrows(applyAsLongThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiDoubleToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiDoubleToByteFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableObjBiDoubleToByteFunction<T, X> andThenToByte(
            @Nonnull ThrowableLongToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsByteThrows(applyAsLongThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiDoubleToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiDoubleToCharFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableObjBiDoubleToCharFunction<T, X> andThenToChar(
            @Nonnull ThrowableLongToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsCharThrows(applyAsLongThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiDoubleToDoubleFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiDoubleToDoubleFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableObjBiDoubleToDoubleFunction<T, X> andThenToDouble(
            @Nonnull ThrowableLongToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsDoubleThrows(applyAsLongThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiDoubleToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiDoubleToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableObjBiDoubleToFloatFunction<T, X> andThenToFloat(
            @Nonnull ThrowableLongToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsFloatThrows(applyAsLongThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiDoubleToIntFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiDoubleToIntFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableObjBiDoubleToIntFunction<T, X> andThenToInt(
            @Nonnull ThrowableLongToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsIntThrows(applyAsLongThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiDoubleToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableObjBiDoubleToLongFunction} that first applies this function to its input, and
     * then applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableObjBiDoubleToLongFunction<T, X> andThenToLong(
            @Nonnull ThrowableLongUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsLongThrows(applyAsLongThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiDoubleToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableObjBiDoubleToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableObjBiDoubleToShortFunction<T, X> andThenToShort(
            @Nonnull ThrowableLongToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsShortThrows(applyAsLongThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiDoubleConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableLongConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableObjBiDoubleConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableLongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableObjBiDoubleConsumer<T, X> consume(@Nonnull ThrowableLongConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.acceptThrows(applyAsLongThrows(t, value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableObjBiDoubleToLongFunction}. Whenever it is called,
     * the mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableObjBiDoubleToLongFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableObjBiDoubleToLongFunction<T, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, Double, Double>, Long> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ThrowableObjBiDoubleToLongFunction<T, X> & Memoized) (t, value1, value2) -> {
                long returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, value1, value2), ThrowableFunction.of(
                            key -> applyAsLongThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} which represents this {@link ThrowableObjBiDoubleToLongFunction}.
     * Thereby the primitive input argument for this function is autoboxed. This method provides the possibility to use
     * this {@code ThrowableObjBiDoubleToLongFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTriFunction} which represents this {@code ThrowableObjBiDoubleToLongFunction}.
     */
    @Nonnull
    default ThrowableTriFunction<T, Double, Double, Long, X> boxed() {
        return this::applyAsLongThrows;
    }

    /**
     * Returns a composed {@link ObjBiDoubleToLongFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link ObjBiDoubleToLongFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ObjBiDoubleToLongFunction<T> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ObjBiDoubleToLongFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ObjBiDoubleToLongFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ObjBiDoubleToLongFunction<T> nest(
            @Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ObjBiDoubleToLongFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover}
     * operation is represented by a curried operation which is called with throwable information and same arguments of
     * this function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link ObjBiDoubleToLongFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ObjBiDoubleToLongFunction<T> recover(
            @Nonnull Function<? super Throwable, ? extends ObjBiDoubleToLongFunction<? super T>> recover) {
        Objects.requireNonNull(recover);
        return (t, value1, value2) -> {
            try {
                return applyAsLongThrows(t, value1, value2);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                ObjBiDoubleToLongFunction<? super T> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsLong(t, value1, value2);
            }
        };
    }

    /**
     * Returns a composed {@link ObjBiDoubleToLongFunction} that applies this function to its input and sneakily throws
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
     * @return A composed {@link ObjBiDoubleToLongFunction} that applies this function to its input and sneakily throws
     * the thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ObjBiDoubleToLongFunction<T> sneakyThrow() {
        return (t, value1, value2) -> {
            try {
                return applyAsLongThrows(t, value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
