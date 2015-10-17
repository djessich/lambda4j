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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents an operation that accepts three input arguments and returns no result.  This is the three-arity
 * specialization of {@link Consumer}. Unlike most other functional interfaces, {@code TriConsumer} is expected to
 * operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the operation to be consumed
 * @param <U> The type of the second argument to the operation to be consumed
 * @param <V> The type of the third argument to the operation to be consumed
 * @see java.util.function.Consumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriConsumer<T, U, V> {

    /**
     * Creates a {@link TriConsumer} which uses the {@code first} parameter of this one as argument for the given {@link
     * Consumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param <V> The type of the third argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> TriConsumer<T, U, V> onlyFirst(@Nonnull final Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(t);
    }

    /**
     * Creates a {@link TriConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link Consumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param <V> The type of the third argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> TriConsumer<T, U, V> onlySecond(@Nonnull final Consumer<? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(u);
    }

    /**
     * Creates a {@link TriConsumer} which uses the {@code third} parameter of this one as argument for the given {@link
     * Consumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param <V> The type of the third argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriConsumer} which uses the {@code third} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> TriConsumer<T, U, V> onlyThird(@Nonnull final Consumer<? super V> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(v);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param v The third argument to the operation to be consumed
     */
    void accept(T t, U u, V v);

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
     * Returns a composed {@link TriConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @param before3 The third operation to apply before this operation is applied
     * @return A composed {@link TriConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(TriConsumer)
     */
    @Nonnull
    default <A, B, C> TriConsumer<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2,
            @Nonnull final Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> accept(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link TriConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation. If evaluation of either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after The operation to apply after this operator is applied
     * @return A composed {@link TriConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, Function)
     */
    @Nonnull
    default TriConsumer<T, U, V> andThen(@Nonnull final TriConsumer<? super T, ? super U, ? super V> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> {
            accept(t, u, v);
            after.accept(t, u, v);
        };
    }
}
