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
package at.gridtec.lambda4j.consumer.bi;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.Consumer2;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents an operation that accepts two input arguments and returns no result.
 * Unlike most other functional interfaces, {@code BiConsumer2} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, Object)}.
 *
 * @param <T> The type of the first argument to the consumer
 * @param <U> The type of the second argument to the consumer
 * @apiNote This is a JDK lambda.
 * @see BiConsumer2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiConsumer2<T, U> extends Lambda, BiConsumer<T, U> {

    /**
     * Constructs a {@link BiConsumer2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiConsumer2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, U> BiConsumer2<T, U> of(@Nullable final BiConsumer2<T, U> expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param consumer The consumer to be called
     * @param t The first argument to the consumer
     * @param u The second argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U> void call(@Nonnull final BiConsumer<? super T, ? super U> consumer, T t, U u) {
        Objects.requireNonNull(consumer);
        consumer.accept(t, u);
    }

    /**
     * Creates a {@link BiConsumer2} which uses the {@code first} parameter of this one as argument for the given {@link
     * Consumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiConsumer2} which uses the {@code first} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiConsumer2<T, U> onlyFirst(@Nonnull final Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.accept(t);
    }

    /**
     * Creates a {@link BiConsumer2} which uses the {@code second} parameter of this one as argument for the given
     * {@link Consumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiConsumer2} which uses the {@code second} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiConsumer2<T, U> onlySecond(@Nonnull final Consumer<? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.accept(u);
    }

    /**
     * Applies this consumer to the given arguments.
     *
     * @param t The first argument to the consumer
     * @param u The second argument to the consumer
     */
    void accept(T t, U u);

    /**
     * Applies this consumer to the given tuple.
     *
     * @param tuple The tuple to be applied to the consumer
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default void accept(@Nonnull Pair<T, U> tuple) {
        Objects.requireNonNull(tuple);
        accept(tuple.getLeft(), tuple.getRight());
    }

    /**
     * Returns the number of arguments for this consumer.
     *
     * @return The number of arguments for this consumer.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiConsumer2} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed consumer
     * @param <B> The type of the argument to the second given function, and of composed consumer
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiConsumer2} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiConsumer2<A, B> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> accept(before1.apply(a), before2.apply(b));
    }

    /**
     * Returns a composed {@link BiConsumer2} that performs, in sequence, this consumer followed by the {@code after}
     * consumer.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * If performing this consumer throws an exception, the {@code after} consumer will not be performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link BiConsumer2} that performs, in sequence, this consumer followed by the {@code after}
     * consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiConsumer2<T, U> andThen(@Nonnull final BiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        return (t, u) -> {
            accept(t, u);
            after.accept(t, u);
        };
    }

    /**
     * Returns a tupled version of this consumer.
     *
     * @return A tupled version of this consumer.
     */
    @Nonnull
    default Consumer2<Pair<T, U>> tupled() {
        return this::accept;
    }

    /**
     * Returns a reversed version of this consumer. This may be useful in recursive context.
     *
     * @return A reversed version of this consumer.
     */
    @Nonnull
    default BiConsumer2<U, T> reversed() {
        return (u, t) -> accept(t, u);
    }

}