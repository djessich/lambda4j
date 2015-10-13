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

import at.gridtec.lambda4j.consumer.primitives.FloatConsumer;
import at.gridtec.lambda4j.function.primitives.FloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that produces a float-valued result from one argument. This is the {@code float}-producing
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(Object)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToFloatFunction<T> {

    /**
     * Creates a {@link ToFloatFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToFloatFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ToFloatFunction<T> constant(float ret) {
        return t -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(T t);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ToFloatFunction} that first applies the {@code before} {@link UnaryOperator} to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before The {@code UnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToFloatFunction} that first applies the {@code before} {@code UnaryOperator} to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatUnaryOperator)
     * @see #andThen(FloatFunction)
     */
    default ToFloatFunction<T> compose(final UnaryOperator<T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsFloat(before.apply(t));
    }

    /**
     * Returns a composed {@link ToFloatFunction} that applies the given {@code before} {@link Function} to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <V> The type of the argument to the before operation
     * @param before The before {@code Function} to apply before this operation is applied
     * @return A composed {@code ToFloatFunction} that applies the given {@code before} {@code Function} to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(FloatUnaryOperator)
     * @see #andThen(FloatFunction)
     */
    default <V> ToFloatFunction<V> compose(final Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return v -> applyAsFloat(before.apply(v));
    }

    /**
     * Returns a composed {@link ToFloatFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToFloatFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator)
     * @see #compose(Function)
     */
    default ToFloatFunction<T> andThen(final FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsFloat(applyAsFloat(t));
    }

    /**
     * Returns a composed {@link Function} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <R> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code FloatFunction} to apply after this operation is applied
     * @return A composed {@code Function} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator)
     * @see #compose(Function)
     */
    default <R> Function<T, R> andThen(final FloatFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return t -> after.apply(this.applyAsFloat(t));
    }

    /**
     * Returns a composed {@link Predicate} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result. If evaluation of either operations throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param after The {@code FloatToBooleanFunction} to apply after this operation is applied
     * @return A composed {@code Predicate} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default Predicate<T> toBoolean(final FloatToBooleanFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsBoolean(applyAsFloat(t));
    }

    /**
     * Returns a composed {@link ToByteFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToByteFunction} to apply after this operation is applied
     * @return A composed {@code ToByteFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToByteFunction<T> toByte(final FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsByte(applyAsFloat(t));
    }

    /**
     * Returns a composed {@link ToCharFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToCharFunction} to apply after this operation is applied
     * @return A composed {@code ToCharFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToCharFunction<T> toChar(final FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsChar(applyAsFloat(t));
    }

    /**
     * Returns a composed {@link ToDoubleFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToDoubleFunction} to apply after this operation is applied
     * @return A composed {@code ToDoubleFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToDoubleFunction<T> toDouble(final FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsDouble(applyAsFloat(t));
    }

    /**
     * Returns a composed {@link ToIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToIntFunction} to apply after this operation is applied
     * @return A composed {@code ToIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToIntFunction<T> toInt(final FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsInt(applyAsFloat(t));
    }

    /**
     * Returns a composed {@link ToLongFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToLongFunction} to apply after this operation is applied
     * @return A composed {@code ToLongFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToLongFunction<T> toLong(final FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsLong(applyAsFloat(t));

    }

    /**
     * Returns a composed {@link ToShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToShortFunction} to apply after this operation is applied
     * @return A composed {@code ToShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToShortFunction<T> toShort(final FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsShort(applyAsFloat(t));
    }

    /**
     * Returns a composed {@link Consumer} that fist applies this operation to its input, and then consumes the result
     * using the given {@code FloatConsumer}. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The {@code FloatConsumer} which consumes the result from this operation
     * @return A composed {@code Consumer} that first applies this operation to its input, and then consumes the result
     * using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default Consumer<T> consume(FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.accept(this.applyAsFloat(t));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link ToFloatFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ToFloatFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code ToFloatFunction}.
     */
    @Nonnull
    default Function<T, Float> boxed() {
        return this::applyAsFloat;
    }
}
