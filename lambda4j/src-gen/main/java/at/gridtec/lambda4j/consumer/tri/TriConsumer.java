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
package at.gridtec.lambda4j.consumer.tri;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.Consumer2;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents an operation that accepts three input arguments and returns no result.
 * Unlike most other functional interfaces, {@code TriConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the consumer
 * @param <U> The type of the second argument to the consumer
 * @param <V> The type of the third argument to the consumer
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriConsumer<T, U, V> extends Lambda {

    /**
     * Constructs a {@link TriConsumer} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <V> The type of the third argument to the consumer
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, U, V> TriConsumer<T, U, V> of(@Nullable final TriConsumer<T, U, V> expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <V> The type of the third argument to the consumer
     * @param consumer The consumer to be called
     * @param t The first argument to the consumer
     * @param u The second argument to the consumer
     * @param v The third argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U, V> void call(@Nonnull final TriConsumer<? super T, ? super U, ? super V> consumer, T t, U u, V v) {
        Objects.requireNonNull(consumer);
        consumer.accept(t, u, v);
    }

    /**
     * Creates a {@link TriConsumer} which uses the {@code first} parameter of this one as argument for the given {@link
     * Consumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <V> The type of the third argument to the consumer
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
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
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <V> The type of the third argument to the consumer
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
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
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <V> The type of the third argument to the consumer
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriConsumer} which uses the {@code third} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> TriConsumer<T, U, V> onlyThird(@Nonnull final Consumer<? super V> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(v);
    }

    /**
     * Applies this consumer to the given arguments.
     *
     * @param t The first argument to the consumer
     * @param u The second argument to the consumer
     * @param v The third argument to the consumer
     */
    void accept(T t, U u, V v);

    /**
     * Applies this consumer to the given tuple.
     *
     * @param tuple The tuple to be applied to the consumer
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default void accept(@Nonnull Triple<T, U, V> tuple) {
        Objects.requireNonNull(tuple);
        accept(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

    /**
     * Returns the number of arguments for this consumer.
     *
     * @return The number of arguments for this consumer.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link TriConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed consumer
     * @param <B> The type of the argument to the second given function, and of composed consumer
     * @param <C> The type of the argument to the third given function, and of composed consumer
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code TriConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
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
     * Returns a composed {@link TriConsumer} that performs, in sequence, this consumer followed by the {@code after}
     * consumer.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * If performing this consumer throws an exception, the {@code after} consumer will not be performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link TriConsumer} that performs, in sequence, this consumer followed by the {@code after}
     * consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriConsumer<T, U, V> andThen(@Nonnull final TriConsumer<? super T, ? super U, ? super V> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> {
            accept(t, u, v);
            after.accept(t, u, v);
        };
    }

    /**
     * Returns a tupled version of this consumer.
     *
     * @return A tupled version of this consumer.
     */
    @Nonnull
    default Consumer2<Triple<T, U, V>> tupled() {
        return this::accept;
    }

    /**
     * Returns a reversed version of this consumer. This may be useful in recursive context.
     *
     * @return A reversed version of this consumer.
     */
    @Nonnull
    default TriConsumer<V, U, T> reversed() {
        return (v, u, t) -> accept(t, u, v);
    }

}