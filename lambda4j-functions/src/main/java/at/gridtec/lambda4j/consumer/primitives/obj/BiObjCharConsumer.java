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
package at.gridtec.lambda4j.consumer.primitives.obj;

import at.gridtec.lambda4j.consumer.TriConsumer;
import at.gridtec.lambda4j.consumer.primitives.CharConsumer;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts two object-valued and a {@code char}-valued argument, and returns no result.
 * This is the {@code (reference, reference, char)} specialization of {@link TriConsumer}. Unlike most other functional
 * interfaces, {@code BiObjCharConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, Object, char)}.
 *
 * @param <T> The type of the first argument to the operation to be consumed
 * @param <U> The type of the second argument to the operation to be consumed
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjCharConsumer<T, U> {

    /**
     * Calls the given {@link BiObjCharConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    static <T, U> void call(@Nonnull final BiObjCharConsumer<? super T, ? super U> consumer, T t, U u, char value) {
        Objects.requireNonNull(consumer);
        consumer.accept(t, u, value);
    }

    /**
     * Creates a {@link BiObjCharConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link Consumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjCharConsumer} which uses the {@code first} parameter of this one as argument for
     * the given {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjCharConsumer<T, U> onlyFirst(@Nonnull final Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(t);
    }

    /**
     * Creates a {@link BiObjCharConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link Consumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjCharConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjCharConsumer<T, U> onlySecond(@Nonnull final Consumer<? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(u);
    }

    /**
     * Creates a {@link BiObjCharConsumer} which uses the {@code third} parameter of this one as argument for the given
     * {@link CharConsumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjCharConsumer} which uses the {@code third} parameter of this one as argument for
     * the given {@code CharConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjCharConsumer<T, U> onlyThird(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(value);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     */
    void accept(T t, U u, char value);

    /**
     * Performs this operation on the given tuple.
     *
     * @param tuple The tuple to be applied to the operation to be consumed
     * @param value The primitive value to be applied to the operation to be consumed
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default void accept(@Nonnull Pair<T, U> tuple, char value) {
        Objects.requireNonNull(tuple);
        accept(tuple.getLeft(), tuple.getRight(), value);
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
     * Returns a composed {@link BiObjCharConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @param before3 The third operation to apply before this operation is applied
     * @return A composed {@link BiObjCharConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code char}.
     * @see #andThen(BiObjCharConsumer)
     */
    @Nonnull
    default <A, B> BiObjCharConsumer<A, B> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> accept(before1.apply(a), before2.apply(b), before3.applyAsChar(value));
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
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(BiObjCharConsumer)
     */
    @Nonnull
    default <A, B, C> TriConsumer<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final ToCharFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> accept(before1.apply(a), before2.apply(b), before3.applyAsChar(c));
    }

    /**
     * Returns a composed {@link BiObjCharConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link BiObjCharConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(Function, Function, CharUnaryOperator)
     * @see #compose(Function, Function, ToCharFunction)
     */
    @Nonnull
    default BiObjCharConsumer<T, U> andThen(@Nonnull final BiObjCharConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> {
            accept(t, u, value);
            after.accept(t, u, value);
        };
    }

    /**
     * Applies this operation partially to one argument. The result is an operation of arity {@code 2};
     *
     * @param t The argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default ObjCharConsumer<U> partial(T t) {
        return (u, value) -> accept(t, u, value);
    }

    /**
     * Applies this operation partially to one argument. The result is an operation of arity {@code 2};
     *
     * @param value The argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default BiConsumer<T, U> partial(char value) {
        return (t, u) -> accept(t, u, value);
    }

    /**
     * Applies this operation partially to two arguments. The result is an operation of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the operation
     * @param u The second argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default CharConsumer partial(T t, U u) {
        return value -> accept(t, u, value);
    }

    /**
     * Applies this operation partially to two arguments. The result is an operation of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the operation
     * @param value The second argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default Consumer<U> partial(T t, char value) {
        return u -> accept(t, u, value);
    }

    /**
     * Returns a tupled version of this operation.
     *
     * @return A tupled version of this operation.
     */
    @Nonnull
    default ObjCharConsumer<Pair<T, U>> tupled() {
        return this::accept;
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link BiObjCharConsumer}. Thereby the primitive
     * input argument for this consumer is autoboxed.
     *
     * @return A composed {@code TriConsumer} which represents this {@code BiObjCharConsumer}.
     */
    @Nonnull
    default TriConsumer<T, U, Character> boxed() {
        return this::accept;
    }
}