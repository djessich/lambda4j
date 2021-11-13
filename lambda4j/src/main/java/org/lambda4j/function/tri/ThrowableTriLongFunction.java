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

package org.lambda4j.function.tri;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableConsumer;
import org.lambda4j.consumer.tri.ThrowableTriLongConsumer;
import org.lambda4j.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.ThrowableLongFunction;
import org.lambda4j.function.bi.ThrowableBiLongFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import org.lambda4j.function.conversion.ThrowableByteToLongFunction;
import org.lambda4j.function.conversion.ThrowableCharToLongFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToLongFunction;
import org.lambda4j.function.conversion.ThrowableFloatToLongFunction;
import org.lambda4j.function.conversion.ThrowableIntToLongFunction;
import org.lambda4j.function.conversion.ThrowableShortToLongFunction;
import org.lambda4j.function.to.ThrowableToByteFunction;
import org.lambda4j.function.to.ThrowableToCharFunction;
import org.lambda4j.function.to.ThrowableToDoubleFunction;
import org.lambda4j.function.to.ThrowableToFloatFunction;
import org.lambda4j.function.to.ThrowableToIntFunction;
import org.lambda4j.function.to.ThrowableToLongFunction;
import org.lambda4j.function.to.ThrowableToShortFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToByteFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToCharFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToDoubleFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToFloatFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToIntFunction;
import org.lambda4j.function.tri.conversion.ThrowableTriLongToShortFunction;
import org.lambda4j.operator.ternary.ThrowableLongTernaryOperator;
import org.lambda4j.operator.unary.ThrowableLongUnaryOperator;
import org.lambda4j.predicate.ThrowablePredicate;
import org.lambda4j.predicate.tri.ThrowableTriLongPredicate;
import org.lambda4j.util.ThrowableUtils;

/**
 * Represents an operation that accepts three {@code long}-valued input arguments and produces a result which is able to
 * throw any {@link Throwable}. This is a primitive specialization of {@link ThrowableTriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(long, long, long)}.
 *
 * @param <R> The type of return value from the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableTriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableTriLongFunction<R, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableTriLongFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableTriLongFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <R, X extends Throwable> ThrowableTriLongFunction<R, X> of(
            @Nullable ThrowableTriLongFunction<R, X> expression) {
        return expression;
    }

    /**
     * Constructs a {@link ThrowableTriLongFunction} based on a curried lambda expression. Thereby the given curried
     * lambda expression is converted to the desired uncurried type of same arity. With this method, it is possible to
     * uncurry a curried lambda expression.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param curried A curried lambda expression, e.g. {@code value1 -> value2 -> value3 -> method(value1, value2,
     * value3)}
     * @return A {@code ThrowableTriLongFunction} from given curried lambda expression.
     * @throws X Any throwable from this functions action
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <R, X extends Throwable> ThrowableTriLongFunction<R, X> of(
            @Nullable ThrowableLongFunction<ThrowableLongFunction<ThrowableLongFunction<R, X>, X>, X> curried)
            throws X {
        if (Objects.isNull(curried)) {
            return null;
        }
        return (value1, value2, value3) -> curried.applyThrows(value1).applyThrows(value2).applyThrows(value3);
    }

    /**
     * Lifts a partial {@link ThrowableTriLongFunction} into a total {@link ThrowableTriLongFunction} that returns an
     * {@link Optional} result.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param partial A function that is only defined for some values in its domain
     * @return A partial {@code ThrowableTriLongFunction} lifted into a total {@code ThrowableTriLongFunction} that
     * returns an {@code Optional} result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R, X extends Throwable> ThrowableTriLongFunction<Optional<R>, X> lift(
            @Nonnull ThrowableTriLongFunction<? extends R, ? extends X> partial) {
        Objects.requireNonNull(partial);
        return (value1, value2, value3) -> Optional.ofNullable(partial.applyThrows(value1, value2, value3));
    }

    /**
     * Calls the given {@link ThrowableTriLongFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code ThrowableTriLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <R, X extends Throwable> R call(@Nonnull ThrowableTriLongFunction<? extends R, ? extends X> function,
            long value1, long value2, long value3) throws X {
        Objects.requireNonNull(function);
        return function.applyThrows(value1, value2, value3);
    }

    /**
     * Creates a {@link ThrowableTriLongFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ThrowableLongFunction}.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableTriLongFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ThrowableLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R, X extends Throwable> ThrowableTriLongFunction<R, X> onlyFirst(
            @Nonnull ThrowableLongFunction<? extends R, ? extends X> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyThrows(value1);
    }

    /**
     * Creates a {@link ThrowableTriLongFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ThrowableLongFunction}.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableTriLongFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R, X extends Throwable> ThrowableTriLongFunction<R, X> onlySecond(
            @Nonnull ThrowableLongFunction<? extends R, ? extends X> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyThrows(value2);
    }

    /**
     * Creates a {@link ThrowableTriLongFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link ThrowableLongFunction}.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableTriLongFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code ThrowableLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R, X extends Throwable> ThrowableTriLongFunction<R, X> onlyThird(
            @Nonnull ThrowableLongFunction<? extends R, ? extends X> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyThrows(value3);
    }

    /**
     * Creates a {@link ThrowableTriLongFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableTriLongFunction} which always returns a given value.
     */
    @Nonnull
    static <R, X extends Throwable> ThrowableTriLongFunction<R, X> constant(R ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    R applyThrows(long value1, long value2, long value3) throws X;

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableBiLongFunction} as
     * result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @return A {@code ThrowableBiLongFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableBiLongFunction<R, X> applyThrowsPartially(long value1) {
        return (value2, value3) -> applyThrows(value1, value2, value3);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableLongFunction} as
     * result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @param value2 The second argument to this function used to partially apply this function
     * @return A {@code ThrowableLongFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableLongFunction<R, X> applyThrowsPartially(long value1, long value2) {
        return value3 -> applyThrows(value1, value2, value3);
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
     * Returns a composed {@link ThrowableTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableTriFunction<A, B, C, R, X> compose(
            @Nonnull ThrowableToLongFunction<? super A, ? extends X> before1,
            @Nonnull ThrowableToLongFunction<? super B, ? extends X> before2,
            @Nonnull ThrowableToLongFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyThrows(before1.applyAsLongThrows(a), before2.applyAsLongThrows(b),
                before3.applyAsLongThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriBooleanFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanFunction<R, X> composeFromBoolean(
            @Nonnull ThrowableBooleanToLongFunction<? extends X> before1,
            @Nonnull ThrowableBooleanToLongFunction<? extends X> before2,
            @Nonnull ThrowableBooleanToLongFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyThrows(before1.applyAsLongThrows(value1),
                before2.applyAsLongThrows(value2),
                before3.applyAsLongThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteFunction<R, X> composeFromByte(
            @Nonnull ThrowableByteToLongFunction<? extends X> before1,
            @Nonnull ThrowableByteToLongFunction<? extends X> before2,
            @Nonnull ThrowableByteToLongFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyThrows(before1.applyAsLongThrows(value1),
                before2.applyAsLongThrows(value2),
                before3.applyAsLongThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharFunction<R, X> composeFromChar(
            @Nonnull ThrowableCharToLongFunction<? extends X> before1,
            @Nonnull ThrowableCharToLongFunction<? extends X> before2,
            @Nonnull ThrowableCharToLongFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyThrows(before1.applyAsLongThrows(value1),
                before2.applyAsLongThrows(value2),
                before3.applyAsLongThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleFunction<R, X> composeFromDouble(
            @Nonnull ThrowableDoubleToLongFunction<? extends X> before1,
            @Nonnull ThrowableDoubleToLongFunction<? extends X> before2,
            @Nonnull ThrowableDoubleToLongFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyThrows(before1.applyAsLongThrows(value1),
                before2.applyAsLongThrows(value2),
                before3.applyAsLongThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatFunction<R, X> composeFromFloat(
            @Nonnull ThrowableFloatToLongFunction<? extends X> before1,
            @Nonnull ThrowableFloatToLongFunction<? extends X> before2,
            @Nonnull ThrowableFloatToLongFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyThrows(before1.applyAsLongThrows(value1),
                before2.applyAsLongThrows(value2),
                before3.applyAsLongThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntFunction<R, X> composeFromInt(@Nonnull ThrowableIntToLongFunction<? extends X> before1,
            @Nonnull ThrowableIntToLongFunction<? extends X> before2,
            @Nonnull ThrowableIntToLongFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyThrows(before1.applyAsLongThrows(value1),
                before2.applyAsLongThrows(value2),
                before3.applyAsLongThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code ThrowableTriLongFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongFunction<R, X> composeFromLong(
            @Nonnull ThrowableLongUnaryOperator<? extends X> before1,
            @Nonnull ThrowableLongUnaryOperator<? extends X> before2,
            @Nonnull ThrowableLongUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyThrows(before1.applyAsLongThrows(value1),
                before2.applyAsLongThrows(value2),
                before3.applyAsLongThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortFunction<R, X> composeFromShort(
            @Nonnull ThrowableShortToLongFunction<? extends X> before1,
            @Nonnull ThrowableShortToLongFunction<? extends X> before2,
            @Nonnull ThrowableShortToLongFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyThrows(before1.applyAsLongThrows(value1),
                before2.applyAsLongThrows(value2),
                before3.applyAsLongThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableTriLongFunction<S, X> andThen(
            @Nonnull ThrowableFunction<? super R, ? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyThrows(applyThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableTriLongPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriLongPredicate<X> andThenToBoolean(@Nonnull ThrowablePredicate<? super R, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.testThrows(applyThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableTriLongToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriLongToByteFunction<X> andThenToByte(
            @Nonnull ThrowableToByteFunction<? super R, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByteThrows(applyThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriLongToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriLongToCharFunction<X> andThenToChar(
            @Nonnull ThrowableToCharFunction<? super R, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsCharThrows(applyThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriLongToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriLongToDoubleFunction<X> andThenToDouble(
            @Nonnull ThrowableToDoubleFunction<? super R, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDoubleThrows(applyThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriLongToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriLongToFloatFunction<X> andThenToFloat(
            @Nonnull ThrowableToFloatFunction<? super R, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloatThrows(applyThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriLongToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriLongToIntFunction<X> andThenToInt(
            @Nonnull ThrowableToIntFunction<? super R, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsIntThrows(applyThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableLongTernaryOperator} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableLongTernaryOperator} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongTernaryOperator<X> andThenToLong(
            @Nonnull ThrowableToLongFunction<? super R, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLongThrows(applyThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriLongToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriLongToShortFunction<X> andThenToShort(
            @Nonnull ThrowableToShortFunction<? super R, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShortThrows(applyThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriLongConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriLongConsumer<X> consume(@Nonnull ThrowableConsumer<? super R, ? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.acceptThrows(applyThrows(value1, value2, value3));
    }

    /**
     * Returns a curried version of this function.
     *
     * @return A curried version of this function.
     */
    @Nonnull
    default ThrowableLongFunction<ThrowableLongFunction<ThrowableLongFunction<R, X>, X>, X> curried() {
        return value1 -> value2 -> value3 -> applyThrows(value1, value2, value3);
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ThrowableTriLongFunction<R, X> reversed() {
        return (value3, value2, value1) -> applyThrows(value1, value2, value3);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableTriLongFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableTriLongFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableTriLongFunction<R, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Long, Long, Long>, R> cache = new ConcurrentHashMap<>();
            return (ThrowableTriLongFunction<R, X> & Memoized) (value1, value2, value3) -> {
                return cache.computeIfAbsent(Triple.of(value1, value2, value3),
                        ThrowableFunction.of(key -> applyThrows(key.getLeft(), key.getMiddle(), key.getRight())));
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} which represents this {@link ThrowableTriLongFunction}. Thereby
     * the primitive input argument for this function is autoboxed. This method provides the possibility to use this
     * {@code ThrowableTriLongFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTriFunction} which represents this {@code ThrowableTriLongFunction}.
     */
    @Nonnull
    default ThrowableTriFunction<Long, Long, Long, R, X> boxed() {
        return this::applyThrows;
    }

    /**
     * Returns a composed {@link TriLongFunction} that applies this function to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link TriLongFunction} that applies this function to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default TriLongFunction<R> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link TriLongFunction} that applies this function to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link TriLongFunction} that applies this function to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default TriLongFunction<R> nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        Objects.requireNonNull(mapper);
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link TriLongFunction} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link TriLongFunction} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default TriLongFunction<R> recover(
            @Nonnull Function<? super Throwable, ? extends TriLongFunction<? extends R>> recover) {
        Objects.requireNonNull(recover);
        return (value1, value2, value3) -> {
            try {
                return applyThrows(value1, value2, value3);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                TriLongFunction<? extends R> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.apply(value1, value2, value3);
            }
        };
    }

    /**
     * Returns a composed {@link TriLongFunction} that applies this function to its input and sneakily throws the thrown
     * {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that each
     * throwable thrown from the returned composed function behaves exactly the same as an <em>unchecked</em> throwable
     * does. As a result, there is no need to handle the throwable of this function in the returned composed function by
     * either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause, as it would
     * be done in a non sneaky throwing function.
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
     * @return A composed {@link TriLongFunction} that applies this function to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default TriLongFunction<R> sneakyThrow() {
        return (value1, value2, value3) -> {
            try {
                return applyThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }
}
