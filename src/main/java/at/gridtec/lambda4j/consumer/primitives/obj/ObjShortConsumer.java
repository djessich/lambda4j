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

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents an operation that accepts an object-valued and a {@code short}-valued argument, and returns no result.
 * This is the {@code (reference, short)} specialization of {@link BiConsumer}. Unlike most other functional interfaces,
 * {@code ObjShortConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, short)}.
 *
 * @param <T> The type of argument to the operation
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjShortConsumer<T> {

    /**
     * Creates a {@link ObjShortConsumer} which uses the {@code first} parameter of this one as argument for the given {@link
     * Consumer}.
     *
     * @param <T> The type of the first argument to the operation
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjShortConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ObjShortConsumer<T> onlyFirst(final Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(t);
    }

    /**
     * Creates a {@link ObjShortConsumer} which uses the {@code second} parameter of this one as argument for the given {@link
     * ShortConsumer}.
     *
     * @param <T> The type of the first argument to the operation
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjShortConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@code ShortConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ObjShortConsumer<T> onlySecond(final ShortConsumer consumer) {
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
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that applies the given {@code before} {@link Function} and {@link
     * ShortUnaryOperator} to its input, and then applies this operation to the result. If evaluation of either of the
     * given operations throws an exception, it is relayed to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The {@code Function} to apply before this operation is applied
     * @param before2 The {@code ShortUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ObjShortConsumer} that applies the given {@code before} {@code Function} and {@code
     * ShortUnaryOperator} to its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ObjShortConsumer)
     */
    default <U> ObjShortConsumer<U> compose(final Function<? super U, ? extends T> before1,
            final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> accept(before1.apply(u), before2.applyAsShort(value));
    }

    /**
     * Returns a composed {@link BiConsumer} that applies the given {@code before} {@link Function} and {@link
     * ToShortFunction} to its input, and then applies this operation to the result. If evaluation of either of the
     * given operations throws an exception, it is relayed to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The {@code Function} to apply after this operation is applied
     * @param before2 The {@code ToShortFunction} to apply after this operation is applied
     * @return A composed {@code BiConsumer} that applies the given {@code before} {@code Function} and {@code
     * ToShortFunction} to its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ObjShortConsumer)
     */
    default <U, V> BiConsumer<U, V> compose(final Function<? super U, ? extends T> before1,
            final ToShortFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> accept(before1.apply(u), before2.applyAsShort(v));
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link ObjShortConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(Function, ShortUnaryOperator)
     * @see #compose(Function, ToShortFunction)
     */
    default ObjShortConsumer<T> andThen(final ObjShortConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (t, value) -> {
            accept(t, value);
            after.accept(t, value);
        };
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link ObjShortConsumer}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjShortConsumer} with JRE specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code ObjShortConsumer}.
     */
    default BiConsumer<T, Short> boxed() {
        return this::accept;
    }
}
