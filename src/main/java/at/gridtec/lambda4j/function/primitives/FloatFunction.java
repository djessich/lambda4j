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

import at.gridtec.lambda4j.consumer.primitives.FloatConsumer;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a function that accepts a float-valued argument and produces a result. This is the {@code float}-consuming
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(float)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatFunction<R> {

    /**
     * Creates a {@link FloatFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code FloatFunction} which always returns a given value.
     */
    static <R> FloatFunction<R> constant(R r) {
        return value -> r;
    }

    /**
     * Applies this {@link FloatFunction} to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(float value);

    /**
     * Returns a composed {@link FloatFunction} that first applies the {@code before} {@link FloatUnaryOperator} to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before The {@code FloatUnaryOperator} to apply before this operation is applied
     * @return A composed {@code FloatFunction} that first applies the {@code before} {@code FloatUnaryOperator} to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default FloatFunction<R> compose(final FloatUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link Function} that applies the given {@code before} {@link ToFloatFunction} to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The before {@code ToFloatFunction} to apply before this operation is applied
     * @return A composed {@code Function} that applies the given {@code before} {@code ToFloatFunction} to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T> Function<T, R> compose(final ToFloatFunction<? super T> before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code FloatFunction} to apply after this operation is applied
     * @return A composed {@code FloatFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(FloatUnaryOperator)
     * @see #compose(ToFloatFunction)
     */
    default <S> FloatFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(apply(value));
    }

    /**
     * Returns a composed {@link FloatConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code FloatFunction} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default FloatConsumer consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(this.apply(value));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link FloatFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code FloatFunction}.
     */
    default Function<Float, R> boxed() {
        return this::apply;
    }
}