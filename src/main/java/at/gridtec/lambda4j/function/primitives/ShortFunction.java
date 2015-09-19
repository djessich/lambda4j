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
package at.gridtec.lambda4j.function.primitives;

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a function that accepts a short-valued argument and produces a result. This is the {@code short}-consuming
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(short)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortFunction<R> {

    /**
     * Creates a {@link ShortFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ShortFunction} which always returns a given value.
     */
    static <R> ShortFunction<R> constant(R r) {
        return value -> r;
    }

    /**
     * Applies this {@link ShortFunction} to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(short value);

    /**
     * Returns a composed {@link ShortFunction} that first applies the {@code before} {@link ShortUnaryOperator} to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before The {@code ShortUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ShortFunction} that first applies the {@code before} {@code ShortUnaryOperator} to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default ShortFunction<R> compose(final ShortUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link Function} that applies the given {@code before} {@link ToShortFunction} to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The before {@code ToShortFunction} to apply before this operation is applied
     * @return A composed {@code Function} that applies the given {@code before} {@code ToShortFunction} to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T> Function<T, R> compose(final ToShortFunction<? super T> before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code ShortFunction} to apply after this operation is applied
     * @return A composed {@code ShortFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ShortUnaryOperator)
     * @see #compose(ToShortFunction)
     */
    default <S> ShortFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(apply(value));
    }

    /**
     * Returns a composed {@link ShortConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code ShortFunction} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ShortConsumer consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(this.apply(value));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link ShortFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code ShortFunction}.
     */
    default Function<Short, R> boxed() {
        return this::apply;
    }
}