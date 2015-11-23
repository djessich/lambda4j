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

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts an object-valued and a {@code short}-valued argument, and returns no result.
 * This is the {@code (reference, short)} specialization of {@link BiConsumer}. Unlike most other functional interfaces,
 * {@code ObjShortConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, short)}.
 *
 * @param <T> The type of argument to the operation to be consumed
 * @see BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjShortConsumer<T> {

    /**
     * Calls the given {@link ObjShortConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    static <T> void call(@Nonnull final ObjShortConsumer<? super T> consumer, T t, short value) {
        Objects.requireNonNull(consumer);
        consumer.accept(t, value);
    }

    /**
     * Creates a {@link ObjShortConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link Consumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjShortConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ObjShortConsumer<T> onlyFirst(@Nonnull final Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(t);
    }

    /**
     * Creates a {@link ObjShortConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link ShortConsumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjShortConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@code ShortConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ObjShortConsumer<T> onlySecond(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(value);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     */
    void accept(T t, short value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @return A composed {@link ObjShortConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code short}.
     * @see #andThen(ObjShortConsumer)
     */
    @Nonnull
    default <U> ObjShortConsumer<U> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> accept(before1.apply(u), before2.applyAsShort(value));
    }

    /**
     * Returns a composed {@link BiConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @return A composed {@link BiConsumer} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ObjShortConsumer)
     */
    @Nonnull
    default <U, V> BiConsumer<U, V> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final ToShortFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> accept(before1.apply(u), before2.applyAsShort(v));
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link ObjShortConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(Function, ShortUnaryOperator)
     * @see #compose(Function, ToShortFunction)
     */
    @Nonnull
    default ObjShortConsumer<T> andThen(@Nonnull final ObjShortConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (t, value) -> {
            accept(t, value);
            after.accept(t, value);
        };
    }

    /**
     * Applies this operation partially to one argument. The result is an operation of arity {@code 1};
     *
     * @param t The argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default ShortConsumer partial(T t) {
        return value -> accept(t, value);
    }

    /**
     * Applies this operation partially to one argument. The result is an operation of arity {@code 1};
     *
     * @param value The argument to partially apply to the operation
     * @return A partial application of this operation.
     */
    @Nonnull
    default Consumer<T> partial(short value) {
        return t -> accept(t, value);
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link ObjShortConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjShortConsumer} with JRE specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code ObjShortConsumer}.
     */
    @Nonnull
    default BiConsumer<T, Short> boxed() {
        return this::accept;
    }
}