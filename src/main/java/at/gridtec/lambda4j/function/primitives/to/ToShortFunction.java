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
package at.gridtec.lambda4j.function.primitives.to;

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.*;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import java.util.Objects;
import java.util.function.*;

/**
 * Represents a function that produces a short-valued result from one argument. This is the {@code short}-producing
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(Object)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToShortFunction<T> {

    /**
     * Creates a {@link ToShortFunction} which always returns a given value.
     *
     * @param <T> The type of return value from the function
     * @param value The return value for the constant
     * @return A {@code ShortFunction} which always returns a given value.
     */
    static <T> ToShortFunction<T> constant(short value) {
        return t -> value;
    }

    /**
     * Applies this {@link ToShortFunction} to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     */
    short applyAsShort(T t);

    /**
     * Returns a composed {@link ToShortFunction} that first applies the {@code before} {@link UnaryOperator} to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before The {@code UnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToShortFunction} that first applies the {@code before} {@code UnaryOperator} to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    default ToShortFunction<T> compose(final UnaryOperator<T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsShort(before.apply(t));
    }

    /**
     * Returns a composed {@link ToShortFunction} that applies the given {@code before} {@link Function} to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <V> The type of the argument to the before operation
     * @param before The before {@code Function} to apply before this operation is applied
     * @return A composed {@code ToShortFunction} that applies the given {@code before} {@code Function} to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ShortFunction)
     */
    default <V> ToShortFunction<V> compose(final Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return v -> applyAsShort(before.apply(v));
    }

    /**
     * Returns a composed {@link ToShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ShortUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToShortFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator)
     * @see #compose(Function)
     */
    default ToShortFunction<T> andThen(final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsShort(applyAsShort(t));
    }

    /**
     * Returns a composed {@link Function} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <R> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code ShortFunction} to apply after this operation is applied
     * @return A composed {@code Function} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator)
     * @see #compose(Function)
     */
    default <R> Function<T, R> andThen(final ShortFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return t -> after.apply(this.applyAsShort(t));
    }

    /**
     * Returns a composed {@link Predicate} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result. If evaluation of either operations throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param after The {@code ShortToBooleanFunction} to apply after this operation is applied
     * @return A composed {@code Predicate} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default Predicate<T> toBoolean(final ShortToBooleanFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsBoolean(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToByteFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ShortToByteFunction} to apply after this operation is applied
     * @return A composed {@code ToByteFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToByteFunction<T> toByte(final ShortToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsShort(value));
    }

    /**
     * Returns a composed {@link ToCharFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ShortToCharFunction} to apply after this operation is applied
     * @return A composed {@code ToCharFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToCharFunction<T> toChar(final ShortToCharFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsChar(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToDoubleFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ShortToDoubleFunction} to apply after this operation is applied
     * @return A composed {@code ToDoubleFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToDoubleFunction<T> toDouble(final ShortToDoubleFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsDouble(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToFloatFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ShortToFloatFunction} to apply after this operation is applied
     * @return A composed {@code ToFloatFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToFloatFunction<T> toFloat(final ShortToFloatFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsFloat(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ShortToIntFunction} to apply after this operation is applied
     * @return A composed {@code ToIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToIntFunction<T> toInt(final ShortToIntFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsInt(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToLongFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ShortToLongFunction} to apply after this operation is applied
     * @return A composed {@code ToLongFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToLongFunction<T> toLong(final ShortToLongFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsLong(applyAsShort(t));

    }

    /**
     * Returns a composed {@link Consumer} that fist applies this operation to its input, and then consumes the result
     * using the given {@code ShortConsumer}. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The {@code ShortConsumer} which consumes the result from this operation
     * @return A composed {@code Consumer} that first applies this operation to its input, and then consumes the result
     * using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default Consumer<T> consume(ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.accept(this.applyAsShort(t));
    }
}
