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
package at.gridtec.lambda4j.consumer;

import at.gridtec.lambda4j.function.ThrowableFunction;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Represents an operation that accepts three input arguments and returns no result which is able to throw any {@link
 * Throwable}. This is the three-arity specialization of {@link ThrowableConsumer}. Unlike most other functional
 * interfaces, {@code ThrowableTriConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #acceptThrows(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the operation to be consumed
 * @param <U> The type of the second argument to the operation to be consumed
 * @param <V> The type of the third argument to the operation to be consumed
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableTriConsumer<T, U, V> {

    /**
     * Calls the given {@link ThrowableTriConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param <V> The type of the third argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param v The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @throws Throwable Any throwable from the given consumers action
     */
    static <T, U, V> void call(@Nonnull final ThrowableTriConsumer<? super T, ? super U, ? super V> consumer, T t, U u,
            V v) throws Throwable {
        Objects.requireNonNull(consumer);
        consumer.acceptThrows(t, u, v);
    }

    /**
     * Creates a {@link ThrowableTriConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param <V> The type of the third argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableTriConsumer} which uses the {@code first} parameter of this one as argument for
     * the given {@code ThrowableConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ThrowableTriConsumer<T, U, V> onlyFirst(@Nonnull final ThrowableConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.acceptThrows(t);
    }

    /**
     * Creates a {@link ThrowableTriConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param <V> The type of the third argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableTriConsumer} which uses the {@code second} parameter of this one as argument
     * for the given {@code ThrowableConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ThrowableTriConsumer<T, U, V> onlySecond(@Nonnull final ThrowableConsumer<? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.acceptThrows(u);
    }

    /**
     * Creates a {@link ThrowableTriConsumer} which uses the {@code third} parameter of this one as argument for the
     * given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param <V> The type of the third argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableTriConsumer} which uses the {@code third} parameter of this one as argument for
     * the given {@code ThrowableConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ThrowableTriConsumer<T, U, V> onlyThird(@Nonnull final ThrowableConsumer<? super V> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.acceptThrows(v);
    }

    /**
     * Performs this operation on the given arguments. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param v The third argument to the operation to be consumed
     * @throws Throwable Any throwable from this consumers action
     */
    void acceptThrows(T t, U u, V v) throws Throwable;

    /**
     * Performs this operation on the given tuple. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param tuple The tuple to be applied to the operation to be consumed
     * @throws NullPointerException If given argument is {@code null}
     * @throws Throwable Any throwable from this functions action
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default void acceptThrows(@Nonnull Triple<T, U, V> tuple) throws Throwable {
        Objects.requireNonNull(tuple);
        acceptThrows(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ThrowableTriConsumer} that first applies the {@code before} operations to its input,
     * and then applies this operation to the result.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @param before3 The third operation to apply before this operation is applied
     * @return A composed {@link ThrowableTriConsumer} that first applies the {@code before} operations to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ThrowableTriConsumer)
     */
    @Nonnull
    default <A, B, C> ThrowableTriConsumer<A, B, C> compose(
            @Nonnull final ThrowableFunction<? super A, ? extends T> before1,
            @Nonnull final ThrowableFunction<? super B, ? extends U> before2,
            @Nonnull final ThrowableFunction<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> acceptThrows(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link ThrowableTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operator is applied
     * @return A composed {@link ThrowableTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ThrowableFunction, ThrowableFunction, ThrowableFunction)
     */
    @Nonnull
    default ThrowableTriConsumer<T, U, V> andThen(
            @Nonnull final ThrowableTriConsumer<? super T, ? super U, ? super V> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> {
            acceptThrows(t, u, v);
            after.acceptThrows(t, u, v);
        };
    }

    /**
     * Applies this operation partially to one argument. The result is an operation of arity {@code 2};
     *
     * @param t The argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default ThrowableBiConsumer<U, V> partial(T t) {
        return (u, v) -> acceptThrows(t, u, v);
    }

    /**
     * Applies this operation partially to two arguments. The result is an operation of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the operation
     * @param u The second argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default ThrowableConsumer<V> partial(T t, U u) {
        return v -> acceptThrows(t, u, v);
    }

    /**
     * Returns a tupled version of this operation.
     *
     * @return A tupled version of this operation.
     */
    @Nonnull
    default ThrowableConsumer<Triple<T, U, V>> tupled() {
        return this::acceptThrows;
    }

    /**
     * Returns a reversed version of this operation. This may be useful in recursive context.
     *
     * @return A reversed version of this operation.
     */
    @Nonnull
    default ThrowableTriConsumer<V, U, T> reversed() {
        return (v, u, t) -> acceptThrows(t, u, v);
    }
}
