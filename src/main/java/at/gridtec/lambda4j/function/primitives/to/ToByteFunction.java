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

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

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
 * Represents a function that produces a byte-valued result from one argument. This is the {@code byte}-producing
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(Object)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToByteFunction<T> {

    /**
     * Creates a {@link ToByteFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToByteFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ToByteFunction<T> constant(byte ret) {
        return t -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(T t);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ToByteFunction} that first applies the {@code before} {@link UnaryOperator} to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before The {@code UnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToByteFunction} that first applies the {@code before} {@code UnaryOperator} to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteUnaryOperator)
     * @see #andThen(ByteFunction)
     */
    default ToByteFunction<T> compose(final UnaryOperator<T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsByte(before.apply(t));
    }

    /**
     * Returns a composed {@link ToByteFunction} that applies the given {@code before} {@link Function} to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <V> The type of the argument to the before operation
     * @param before The before {@code Function} to apply before this operation is applied
     * @return A composed {@code ToByteFunction} that applies the given {@code before} {@code Function} to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ByteUnaryOperator)
     * @see #andThen(ByteFunction)
     */
    default <V> ToByteFunction<V> compose(final Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return v -> applyAsByte(before.apply(v));
    }

    /**
     * Returns a composed {@link ToByteFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ByteUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToByteFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator)
     * @see #compose(Function)
     */
    default ToByteFunction<T> andThen(final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsByte(applyAsByte(t));
    }

    /**
     * Returns a composed {@link Function} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <R> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code ByteFunction} to apply after this operation is applied
     * @return A composed {@code Function} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator)
     * @see #compose(Function)
     */
    default <R> Function<T, R> andThen(final ByteFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return t -> after.apply(applyAsByte(t));
    }

    /**
     * Returns a composed {@link Predicate} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result. If evaluation of either operations throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param after The {@code ByteToBooleanFunction} to apply after this operation is applied
     * @return A composed {@code Predicate} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default Predicate<T> toBoolean(final ByteToBooleanFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsBoolean(applyAsByte(t));
    }

    /**
     * Returns a composed {@link ToCharFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ByteToCharFunction} to apply after this operation is applied
     * @return A composed {@code ToCharFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToCharFunction<T> toChar(final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsChar(applyAsByte(t));
    }

    /**
     * Returns a composed {@link ToDoubleFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ByteToDoubleFunction} to apply after this operation is applied
     * @return A composed {@code ToDoubleFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToDoubleFunction<T> toDouble(final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsDouble(applyAsByte(t));
    }

    /**
     * Returns a composed {@link ToFloatFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ByteToFloatFunction} to apply after this operation is applied
     * @return A composed {@code ToFloatFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToFloatFunction<T> toFloat(final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsFloat(applyAsByte(t));
    }

    /**
     * Returns a composed {@link ToIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ByteToIntFunction} to apply after this operation is applied
     * @return A composed {@code ToIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToIntFunction<T> toInt(final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsInt(applyAsByte(t));
    }

    /**
     * Returns a composed {@link ToLongFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ByteToLongFunction} to apply after this operation is applied
     * @return A composed {@code ToLongFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToLongFunction<T> toLong(final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsLong(applyAsByte(t));

    }

    /**
     * Returns a composed {@link ToShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ByteToShortFunction} to apply after this operation is applied
     * @return A composed {@code ToShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToShortFunction<T> toShort(final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsShort(applyAsByte(t));
    }

    /**
     * Returns a composed {@link Consumer} that fist applies this operation to its input, and then consumes the result
     * using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer} that first applies this operation to its input, and then consumes the result
     * using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default Consumer<T> consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.accept(applyAsByte(t));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link ToByteFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ToByteFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code ToByteFunction}.
     */
    @Nonnull
    default Function<T, Byte> boxed() {
        return this::applyAsByte;
    }
}
