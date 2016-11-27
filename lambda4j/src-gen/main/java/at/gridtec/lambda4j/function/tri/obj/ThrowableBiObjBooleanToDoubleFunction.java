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
package at.gridtec.lambda4j.function.tri.obj;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableDoubleConsumer;
import at.gridtec.lambda4j.consumer.tri.obj.ThrowableBiObjBooleanConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableBooleanFunction;
import at.gridtec.lambda4j.function.ThrowableByteFunction;
import at.gridtec.lambda4j.function.ThrowableCharFunction;
import at.gridtec.lambda4j.function.ThrowableDoubleFunction;
import at.gridtec.lambda4j.function.ThrowableFloatFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.ThrowableIntFunction;
import at.gridtec.lambda4j.function.ThrowableLongFunction;
import at.gridtec.lambda4j.function.ThrowableShortFunction;
import at.gridtec.lambda4j.function.bi.obj.ThrowableObjBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToIntFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToLongFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToShortFunction;
import at.gridtec.lambda4j.function.to.ThrowableToDoubleFunction;
import at.gridtec.lambda4j.function.tri.ThrowableTriFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriByteToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriCharToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriFloatToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriIntToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriLongToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriShortToDoubleFunction;
import at.gridtec.lambda4j.function.tri.to.ThrowableToDoubleTriFunction;
import at.gridtec.lambda4j.operator.ternary.ThrowableDoubleTernaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableDoubleUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableBytePredicate;
import at.gridtec.lambda4j.predicate.ThrowableCharPredicate;
import at.gridtec.lambda4j.predicate.ThrowableDoublePredicate;
import at.gridtec.lambda4j.predicate.ThrowableFloatPredicate;
import at.gridtec.lambda4j.predicate.ThrowableIntPredicate;
import at.gridtec.lambda4j.predicate.ThrowableLongPredicate;
import at.gridtec.lambda4j.predicate.ThrowablePredicate;
import at.gridtec.lambda4j.predicate.ThrowableShortPredicate;
import at.gridtec.lambda4j.predicate.tri.obj.ThrowableBiObjBooleanPredicate;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents an operation that accepts two object-valued and one {@code boolean}-valued input argument and produces a
 * {@code double}-valued result which is able to throw any {@link Throwable}. This is a (reference, reference, boolean)
 * specialization of {@link ThrowableTriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDoubleThrows(Object, Object, * boolean)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableTriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiObjBooleanToDoubleFunction<T, U, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableBiObjBooleanToDoubleFunction} based on a lambda expression or a method reference.
     * Thereby the given lambda expression or method reference is returned on an as-is basis to implicitly transform it
     * to the desired type. With this method, it is possible to ensure that correct type is used from lambda expression
     * or method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBiObjBooleanToDoubleFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, U, X extends Throwable> ThrowableBiObjBooleanToDoubleFunction<T, U, X> of(
            @Nullable final ThrowableBiObjBooleanToDoubleFunction<T, U, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBiObjBooleanToDoubleFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ThrowableBiObjBooleanToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, U, X extends Throwable> double call(
            @Nonnull final ThrowableBiObjBooleanToDoubleFunction<? super T, ? super U, ? extends X> function, T t, U u,
            boolean value) throws X {
        Objects.requireNonNull(function);
        return function.applyAsDoubleThrows(t, u, value);
    }

    /**
     * Creates a {@link ThrowableBiObjBooleanToDoubleFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@link ThrowableToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiObjBooleanToDoubleFunction} which uses the {@code first} parameter of this
     * one as argument for the given {@code ThrowableToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjBooleanToDoubleFunction<T, U, X> onlyFirst(
            @Nonnull final ThrowableToDoubleFunction<? super T, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsDoubleThrows(t);
    }

    /**
     * Creates a {@link ThrowableBiObjBooleanToDoubleFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@link ThrowableToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiObjBooleanToDoubleFunction} which uses the {@code second} parameter of this
     * one as argument for the given {@code ThrowableToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjBooleanToDoubleFunction<T, U, X> onlySecond(
            @Nonnull final ThrowableToDoubleFunction<? super U, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsDoubleThrows(u);
    }

    /**
     * Creates a {@link ThrowableBiObjBooleanToDoubleFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@link ThrowableBooleanToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableBiObjBooleanToDoubleFunction} which uses the {@code third} parameter of this
     * one as argument for the given {@code ThrowableBooleanToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjBooleanToDoubleFunction<T, U, X> onlyThird(
            @Nonnull final ThrowableBooleanToDoubleFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsDoubleThrows(value);
    }

    /**
     * Creates a {@link ThrowableBiObjBooleanToDoubleFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableBiObjBooleanToDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjBooleanToDoubleFunction<T, U, X> constant(double ret) {
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
    double applyAsDoubleThrows(T t, U u, boolean value) throws X;

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
    default double applyAsDoubleThrows(@Nonnull Pair<T, U> tuple, boolean value) throws X {
        Objects.requireNonNull(tuple);
        return applyAsDoubleThrows(tuple.getLeft(), tuple.getRight(), value);
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
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given predicate, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableToDoubleTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToDoubleTriFunction<A, B, C, X> compose(
            @Nonnull final ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull final ThrowableFunction<? super B, ? extends U, ? extends X> before2,
            @Nonnull final ThrowablePredicate<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsDoubleThrows(before1.applyThrows(a), before2.applyThrows(b), before3.testThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code ThrowableTriBooleanToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToDoubleFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableBooleanFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                               before3.applyAsBooleanThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriByteToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToDoubleFunction<X> composeFromByte(
            @Nonnull final ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableByteFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableBytePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                               before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriCharToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharToDoubleFunction<X> composeFromChar(
            @Nonnull final ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableCharFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableCharPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                               before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableDoubleTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableDoubleTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleTernaryOperator<X> composeFromDouble(
            @Nonnull final ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableDoubleFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableDoublePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                               before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriFloatToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatToDoubleFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableFloatFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableFloatPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                               before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriIntToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToDoubleFunction<X> composeFromInt(
            @Nonnull final ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableIntFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableIntPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                               before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriLongToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongToDoubleFunction<X> composeFromLong(
            @Nonnull final ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableLongFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableLongPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                               before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ThrowableTriShortToDoubleFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortToDoubleFunction<X> composeFromShort(
            @Nonnull final ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableShortFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableShortPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDoubleThrows(before1.applyThrows(value1), before2.applyThrows(value2),
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
            @Nonnull final ThrowableDoubleFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyThrows(applyAsDoubleThrows(t, u, value));
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
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiObjBooleanPredicate<T, U, X> andThenToBoolean(
            @Nonnull final ThrowableDoublePredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.testThrows(applyAsDoubleThrows(t, u, value));
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
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToByteFunction<T, U, X> andThenToByte(
            @Nonnull final ThrowableDoubleToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsByteThrows(applyAsDoubleThrows(t, u, value));
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
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToCharFunction<T, U, X> andThenToChar(
            @Nonnull final ThrowableDoubleToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsCharThrows(applyAsDoubleThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanToDoubleFunction} that first applies this function to its input,
     * and then applies the {@code after} operator to the result. This method is just convenience, to provide the
     * ability to transform this primitive function to an operation returning {@code double}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanToDoubleFunction} that first applies this function to its input,
     * and then applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToDoubleFunction<T, U, X> andThenToDouble(
            @Nonnull final ThrowableDoubleUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsDoubleThrows(applyAsDoubleThrows(t, u, value));
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
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToFloatFunction<T, U, X> andThenToFloat(
            @Nonnull final ThrowableDoubleToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsFloatThrows(applyAsDoubleThrows(t, u, value));
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
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToIntFunction<T, U, X> andThenToInt(
            @Nonnull final ThrowableDoubleToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsIntThrows(applyAsDoubleThrows(t, u, value));
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
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToLongFunction<T, U, X> andThenToLong(
            @Nonnull final ThrowableDoubleToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsLongThrows(applyAsDoubleThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanToShortFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiObjBooleanToShortFunction} that first applies this function to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiObjBooleanToShortFunction<T, U, X> andThenToShort(
            @Nonnull final ThrowableDoubleToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsShortThrows(applyAsDoubleThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjBooleanConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableDoubleConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiObjBooleanConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableDoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiObjBooleanConsumer<T, U, X> consume(
            @Nonnull final ThrowableDoubleConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.acceptThrows(applyAsDoubleThrows(t, u, value));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ThrowableObjBooleanToDoubleFunction<Pair<T, U>, X> tupled() {
        return this::applyAsDoubleThrows;
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableBiObjBooleanToDoubleFunction}. Whenever it is
     * called, the mapping between the input parameters and the return value is preserved in a cache, making subsequent
     * calls returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableBiObjBooleanToDoubleFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableBiObjBooleanToDoubleFunction<T, U, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Triple<T, U, Boolean>, Double> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableBiObjBooleanToDoubleFunction<T, U, X> & Memoized) (t, u, value) -> {
                final double returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, u, value), ThrowableFunction.of(
                            key -> applyAsDoubleThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} which represents this {@link
     * ThrowableBiObjBooleanToDoubleFunction}. Thereby the primitive input argument for this function is autoboxed.
     *
     * @return A composed {@code ThrowableTriFunction} which represents this {@code ThrowableBiObjBooleanToDoubleFunction}.
     */
    @Nonnull
    default ThrowableTriFunction<T, U, Boolean, Double, X> boxed() {
        return this::applyAsDoubleThrows;
    }

    /**
     * Returns a composed {@link BiObjBooleanToDoubleFunction} that applies this function to its input and nests the
     * thrown {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link BiObjBooleanToDoubleFunction} that applies this function to its input and nests the
     * thrown {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nestWith(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default BiObjBooleanToDoubleFunction<T, U> nest() {
        return nestWith(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link BiObjBooleanToDoubleFunction} that applies this function to its input and nests the
     * thrown {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown
     * {@code Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link BiObjBooleanToDoubleFunction} that applies this function to its input and nests the
     * thrown {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default BiObjBooleanToDoubleFunction<T, U> nestWith(
            @Nonnull final Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link BiObjBooleanToDoubleFunction} that applies this function to its input and sneakily
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
     * @return A composed {@link BiObjBooleanToDoubleFunction} that applies this function to its input and sneakily
     * throws the thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default BiObjBooleanToDoubleFunction<T, U> sneakyThrow() {
        return (t, u, value) -> {
            try {
                return this.applyAsDoubleThrows(t, u, value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

    /**
     * Returns a composed {@link BiObjBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover}
     * operation is represented by a curried operation which is called with throwable information and same arguments of
     * this function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link BiObjBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default BiObjBooleanToDoubleFunction<T, U> recover(
            @Nonnull final Function<? super Throwable, ? extends BiObjBooleanToDoubleFunction<? super T, ? super U>> recover) {
        Objects.requireNonNull(recover);
        return (t, u, value) -> {
            try {
                return this.applyAsDoubleThrows(t, u, value);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                final BiObjBooleanToDoubleFunction<? super T, ? super U> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsDouble(t, u, value);
            }
        };
    }

}