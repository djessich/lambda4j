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

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts an object-valued and a {@code byte}-valued argument, and returns no result. This
 * is the {@code (reference, byte)} specialization of {@link BiConsumer}. Unlike most other functional interfaces,
 * {@code ObjByteConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, byte)}.
 *
 * @param <T> The type of argument to the operation to be consumed
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjByteConsumer<T> {

    /**
     * Creates a {@link ObjByteConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link Consumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjByteConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ObjByteConsumer<T> onlyFirst(@Nonnull final Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(t);
    }

    /**
     * Creates a {@link ObjByteConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link ByteConsumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjByteConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@code ByteConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ObjByteConsumer<T> onlySecond(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(value);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     */
    void accept(T t, byte value);

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
     * Returns a composed {@link ObjByteConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @return A composed {@link ObjByteConsumer} that first applies the {@code before} operations to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given operations are {@code null}
     * @implNote The second input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code byte}.
     * @see #andThen(ObjByteConsumer)
     */
    @Nonnull
    default <U> ObjByteConsumer<U> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final ByteUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> accept(before1.apply(u), before2.applyAsByte(value));
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
     * @see #andThen(ObjByteConsumer)
     */
    @Nonnull
    default <U, V> BiConsumer<U, V> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final ToByteFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> accept(before1.apply(u), before2.applyAsByte(v));
    }

    /**
     * Returns a composed {@link ObjByteConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link ObjByteConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(Function, ByteUnaryOperator)
     * @see #compose(Function, ToByteFunction)
     */
    @Nonnull
    default ObjByteConsumer<T> andThen(@Nonnull final ObjByteConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (t, value) -> {
            accept(t, value);
            after.accept(t, value);
        };
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link ObjByteConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjByteConsumer} with JRE specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code ObjByteConsumer}.
     */
    @Nonnull
    default BiConsumer<T, Byte> boxed() {
        return this::accept;
    }
}
