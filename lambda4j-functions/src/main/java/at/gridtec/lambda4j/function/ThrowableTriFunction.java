/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.function;

import at.gridtec.lambda4j.consumer.ThrowableConsumer;
import at.gridtec.lambda4j.consumer.ThrowableTriConsumer;
import at.gridtec.lambda4j.supplier.ThrowableSupplier;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a function that accepts three arguments and produces a result which is able to throw any {@link
 * Throwable}. This is the three-arity specialization of {@link ThrowableFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableTriFunction<T, U, V, R> {

    /**
     * Calls the given {@link ThrowableTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ThrowableTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @throws Throwable Any throwable from the given functions action
     */
    static <T, U, V, R> R call(
            @Nonnull final ThrowableTriFunction<? super T, ? super U, ? super V, ? extends R> function, T t, U u,
            V v) throws Throwable {
        Objects.requireNonNull(function);
        return function.applyThrows(t, u, v);
    }

    /**
     * Creates a {@link ThrowableTriFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ThrowableFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableTriFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code ThrowableFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, R> ThrowableTriFunction<T, U, V, R> onlyFirst(
            @Nonnull final ThrowableFunction<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyThrows(t);
    }

    /**
     * Creates a {@link ThrowableTriFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ThrowableFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableTriFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ThrowableFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, R> ThrowableTriFunction<T, U, V, R> onlySecond(
            @Nonnull final ThrowableFunction<? super U, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyThrows(u);
    }

    /**
     * Creates a {@link ThrowableTriFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link ThrowableFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableTriFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@code ThrowableFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, R> ThrowableTriFunction<T, U, V, R> onlyThird(
            @Nonnull final ThrowableFunction<? super V, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyThrows(v);
    }

    /**
     * Creates a {@link ThrowableTriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ThrowableTriFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, V, R> ThrowableTriFunction<T, U, V, R> constant(R r) {
        return (t, u, v) -> r;
    }

    /**
     * Applies this function to the given arguments. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The return value from the function, which is its result.
     * @throws Throwable Any throwable from this functions action
     */
    R applyThrows(T t, U u, V v) throws Throwable;

    /**
     * Applies this function to the given tuple. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param tuple The tuple to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @throws Throwable Any throwable from this functions action
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default R applyThrows(@Nonnull Triple<T, U, V> tuple) throws Throwable {
        Objects.requireNonNull(tuple);
        return applyThrows(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
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
     * @param <A> The type of the argument to the first before function
     * @param <B> The type of the argument to the second before function
     * @param <C> The type of the argument to the third before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ThrowableFunction)
     */
    @Nonnull
    default <A, B, C> ThrowableTriFunction<A, B, C, R> compose(
            @Nonnull final ThrowableFunction<? super A, ? extends T> before1,
            @Nonnull final ThrowableFunction<? super B, ? extends U> before2,
            @Nonnull final ThrowableFunction<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyThrows(before1.applyThrows(a), before2.applyThrows(b), before3.applyThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ThrowableFunction, ThrowableFunction, ThrowableFunction)
     */
    @Nonnull
    default <S> ThrowableTriFunction<T, U, V, S> andThen(
            @Nonnull final ThrowableFunction<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyThrows(applyThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableTriConsumer} that fist applies this function to its input, and then consumes
     * the result using the given {@link ThrowableConsumer}. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code ThrowableConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriConsumer<T, U, V> consume(@Nonnull final ThrowableConsumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.acceptThrows(applyThrows(t, u, v));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param t The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ThrowableBiFunction<U, V, R> partial(T t) {
        return (u, v) -> applyThrows(t, u, v);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the function
     * @param u The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ThrowableFunction<V, R> partial(T t, U u) {
        return v -> applyThrows(t, u, v);
    }

    /**
     * Applies this function partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param t The first argument to partially apply to the function
     * @param u The second argument to partially apply to the function
     * @param v The third argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ThrowableSupplier<R> partial(T t, U u, V v) {
        return () -> applyThrows(t, u, v);
    }

    /**
     * Returns a curried version of this function.
     *
     * @return A curried version of this function.
     */
    @Nonnull
    default ThrowableFunction<T, ThrowableFunction<U, ThrowableFunction<V, R>>> curried() {
        return t -> u -> v -> applyThrows(t, u, v);
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ThrowableFunction<Triple<T, U, V>, R> tupled() {
        return this::applyThrows;
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ThrowableTriFunction<V, U, T, R> reversed() {
        return (v, u, t) -> applyThrows(t, u, v);
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     */
    @Nonnull
    default ThrowableTriFunction<T, U, V, Optional<R>> nonNull() {
        return (t, u, v) -> Optional.ofNullable(applyThrows(t, u, v));
    }
}
