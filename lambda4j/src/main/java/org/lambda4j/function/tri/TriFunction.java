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
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.tri.TriConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.Function2;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.BiFunction2;
import org.lambda4j.function.to.ToByteFunction;
import org.lambda4j.function.to.ToCharFunction;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.function.to.ToShortFunction;
import org.lambda4j.function.tri.to.ToByteTriFunction;
import org.lambda4j.function.tri.to.ToCharTriFunction;
import org.lambda4j.function.tri.to.ToDoubleTriFunction;
import org.lambda4j.function.tri.to.ToFloatTriFunction;
import org.lambda4j.function.tri.to.ToIntTriFunction;
import org.lambda4j.function.tri.to.ToLongTriFunction;
import org.lambda4j.function.tri.to.ToShortTriFunction;
import org.lambda4j.predicate.tri.TriPredicate;

/**
 * Represents an operation that accepts three input arguments and produces a result.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriFunction<T, U, V, R> extends Lambda {

    /**
     * Constructs a {@link TriFunction} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U, V, R> TriFunction<T, U, V, R> of(@Nullable TriFunction<T, U, V, R> expression) {
        return expression;
    }

    /**
     * Constructs a {@link TriFunction} based on a curried lambda expression. Thereby the given curried lambda
     * expression is converted to the desired uncurried type of same arity. With this method, it is possible to uncurry
     * a curried lambda expression.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param curried A curried lambda expression, e.g. {@code t -> u -> v -> method(t, u, v)}
     * @return A {@code TriFunction} from given curried lambda expression.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U, V, R> TriFunction<T, U, V, R> of(@Nullable Function2<T, Function2<U, Function2<V, R>>> curried) {
        if (Objects.isNull(curried)) {
            return null;
        }
        return (t, u, v) -> curried.apply(t).apply(u).apply(v);
    }

    /**
     * Lifts a partial {@link TriFunction} into a total {@link TriFunction} that returns an {@link Optional} result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param partial A function that is only defined for some values in its domain
     * @return A partial {@code TriFunction} lifted into a total {@code TriFunction} that returns an {@code Optional}
     * result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, R> TriFunction<T, U, V, Optional<R>> lift(
            @Nonnull TriFunction<? super T, ? super U, ? super V, ? extends R> partial) {
        Objects.requireNonNull(partial);
        return (t, u, v) -> Optional.ofNullable(partial.apply(t, u, v));
    }

    /**
     * Calls the given {@link TriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code TriFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U, V, R> R call(@Nonnull TriFunction<? super T, ? super U, ? super V, ? extends R> function, T t,
            U u, V v) {
        Objects.requireNonNull(function);
        return function.apply(t, u, v);
    }

    /**
     * Creates a {@link TriFunction} which uses the {@code first} parameter of this one as argument for the given {@link
     * Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code Function}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, R> TriFunction<T, U, V, R> onlyFirst(@Nonnull Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.apply(t);
    }

    /**
     * Creates a {@link TriFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@code Function}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, R> TriFunction<T, U, V, R> onlySecond(@Nonnull Function<? super U, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.apply(u);
    }

    /**
     * Creates a {@link TriFunction} which uses the {@code third} parameter of this one as argument for the given {@link
     * Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@code Function}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, R> TriFunction<T, U, V, R> onlyThird(@Nonnull Function<? super V, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.apply(v);
    }

    /**
     * Creates a {@link TriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code TriFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, V, R> TriFunction<T, U, V, R> constant(R ret) {
        return (t, u, v) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(T t, U u, V v);

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default R apply(@Nonnull Triple<T, U, V> tuple) {
        Objects.requireNonNull(tuple);
        return apply(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BiFunction2} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code BiFunction2} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BiFunction2<U, V, R> applyPartially(T t) {
        return (u, v) -> apply(t, u, v);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link Function2} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param u The second argument to this function used to partially apply this function
     * @return A {@code Function2} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default Function2<V, R> applyPartially(T t, U u) {
        return v -> apply(t, u, v);
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
     * Returns a composed {@link TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> TriFunction<A, B, C, R> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull Function<? super B, ? extends U> before2, @Nonnull Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> apply(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link TriBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanFunction<R> composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanFunction<? extends U> before2, @Nonnull BooleanFunction<? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.apply(value3));
    }

    /**
     * Returns a composed {@link TriByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteFunction<R> composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteFunction<? extends U> before2, @Nonnull ByteFunction<? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.apply(value3));
    }

    /**
     * Returns a composed {@link TriCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharFunction<R> composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharFunction<? extends U> before2, @Nonnull CharFunction<? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.apply(value3));
    }

    /**
     * Returns a composed {@link TriDoubleFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriDoubleFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleFunction<R> composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleFunction<? extends U> before2, @Nonnull DoubleFunction<? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.apply(value3));
    }

    /**
     * Returns a composed {@link TriFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatFunction<R> composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatFunction<? extends U> before2, @Nonnull FloatFunction<? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.apply(value3));
    }

    /**
     * Returns a composed {@link TriIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntFunction<R> composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntFunction<? extends U> before2, @Nonnull IntFunction<? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.apply(value3));
    }

    /**
     * Returns a composed {@link TriLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongFunction<R> composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongFunction<? extends U> before2, @Nonnull LongFunction<? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.apply(value3));
    }

    /**
     * Returns a composed {@link TriShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortFunction<R> composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortFunction<? extends U> before2, @Nonnull ShortFunction<? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.apply(value3));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies this function to its input, and then applies the {@code
     * after} function to the result. If evaluation of either operation throws an exception, it is relayed to the caller
     * of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFunction} that first applies this function to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriFunction<T, U, V, S> andThen(@Nonnull Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(apply(t, u, v));
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code TriPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriPredicate<T, U, V> andThenToBoolean(@Nonnull Predicate<? super R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.test(apply(t, u, v));
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToByteTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ToByteTriFunction<T, U, V> andThenToByte(@Nonnull ToByteFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsByte(apply(t, u, v));
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToCharTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ToCharTriFunction<T, U, V> andThenToChar(@Nonnull ToCharFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsChar(apply(t, u, v));
    }

    /**
     * Returns a composed {@link ToDoubleTriFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToDoubleTriFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ToDoubleTriFunction<T, U, V> andThenToDouble(@Nonnull ToDoubleFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsDouble(apply(t, u, v));
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToFloatTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ToFloatTriFunction<T, U, V> andThenToFloat(@Nonnull ToFloatFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsFloat(apply(t, u, v));
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToIntTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ToIntTriFunction<T, U, V> andThenToInt(@Nonnull ToIntFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsInt(apply(t, u, v));
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToLongTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ToLongTriFunction<T, U, V> andThenToLong(@Nonnull ToLongFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsLong(apply(t, u, v));
    }

    /**
     * Returns a composed {@link ToShortTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToShortTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ToShortTriFunction<T, U, V> andThenToShort(@Nonnull ToShortFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsShort(apply(t, u, v));
    }

    /**
     * Returns a composed {@link TriConsumer} that fist applies this function to its input, and then consumes the result
     * using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriConsumer<T, U, V> consume(@Nonnull Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(apply(t, u, v));
    }

    /**
     * Returns a curried version of this function.
     *
     * @return A curried version of this function.
     */
    @Nonnull
    default Function2<T, Function2<U, Function2<V, R>>> curried() {
        return t -> u -> v -> apply(t, u, v);
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default Function2<Triple<T, U, V>, R> tupled() {
        return this::apply;
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default TriFunction<V, U, T, R> reversed() {
        return (v, u, t) -> apply(t, u, v);
    }

    /**
     * Returns a memoized (caching) version of this {@link TriFunction}. Whenever it is called, the mapping between the
     * input parameters and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriFunction<T, U, V, R> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, U, V>, R> cache = new ConcurrentHashMap<>();
            return (TriFunction<T, U, V, R> & Memoized) (t, u, v) -> {
                return cache.computeIfAbsent(Triple.of(t, u, v),
                        key -> apply(key.getLeft(), key.getMiddle(), key.getRight()));
            };
        }
    }
}
