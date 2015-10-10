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
package at.gridtec.lambda4j.function.primitives.to.bi;

import at.gridtec.lambda4j.consumer.primitives.FloatConsumer;
import at.gridtec.lambda4j.function.primitives.FloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.*;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import java.util.Objects;
import java.util.function.*;

/**
 * Represents a function that produces a float-valued result from two arguments. This is the {@code float}-producing
 * primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToFloatBiFunction<T, U> {

    /**
     * Creates a {@link ToFloatBiFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param value The return value for the constant
     * @return A {@code ToFloatBiFunction} which always returns a given value.
     */
    static <T, U> ToFloatBiFunction<T, U> constant(float value) {
        return (t, u) -> value;
    }

    /**
     * Creates a {@link ToFloatBiFunction} which uses the first parameter of this one as argument for the given {@link
     * ToFloatFunction}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToFloatBiFunction} which uses the first parameter of this one as argument for the given
     * {@code ToFloatFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToFloatBiFunction<T, U> onlyFirst(final ToFloatFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u) -> function.applyAsFloat(t);
    }

    /**
     * Creates a {@link ToFloatBiFunction} which uses the second parameter of this one as argument for the given {@link
     * ToFloatFunction}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param function The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToFloatBiFunction} which uses the second parameter of this one as argument for the given
     * {@code ToFloatFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToFloatBiFunction<T, U> onlySecond(final ToFloatFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u) -> function.applyAsFloat(u);
    }

    /**
     * Applies this {@link ToFloatBiFunction} to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(T t, U u);

    /**
     * Returns a composed {@link ToFloatBiFunction} that first applies the {@code before} {@link UnaryOperator}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code UnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code UnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToFloatBiFunction} that first applies the {@code before} {@code UnaryOperator}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatUnaryOperator)
     * @see #andThen(FloatFunction)
     */
    default ToFloatBiFunction<T, U> compose(final UnaryOperator<T> before1, final UnaryOperator<U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsFloat(before1.apply(t), before2.apply(u));
    }

    /**
     * Returns a composed {@link ToFloatBiFunction} that applies the given {@code before} {@link Function}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <A> The type of the first argument to the before operation
     * @param <B> The type of the second argument to the before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code Function} to apply before this operation is applied
     * @return A composed {@code ToFloatBiFunction} that applies the given {@code before} {@code Function}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(FloatUnaryOperator)
     * @see #andThen(FloatFunction)
     */
    default <A, B> ToFloatBiFunction<A, B> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsFloat(before1.apply(a), before2.apply(b));
    }

    /**
     * Returns a composed {@link ToFloatBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToFloatBiFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator, UnaryOperator)
     * @see #compose(Function, Function)
     */
    default ToFloatBiFunction<T, U> andThen(final FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsFloat(applyAsFloat(t, u));
    }

    /**
     * Returns a composed {@link BiFunction} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <R> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code FloatFunction} to apply after this operation is applied
     * @return A composed {@code BiFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator, UnaryOperator)
     * @see #compose(Function, Function)
     */
    default <R> BiFunction<T, U, R> andThen(final FloatFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.apply(applyAsFloat(t, u));
    }

    /**
     * Returns a composed {@link BiPredicate} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToBooleanFunction} to apply after this operation is applied
     * @return A composed {@code BiPredicate} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BiPredicate<T, U> toBoolean(final FloatToBooleanFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsBoolean(applyAsFloat(t, u));
    }

    /**
     * Returns a composed {@link ToByteBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToByteFunction} to apply after this operation is applied
     * @return A composed {@code ToByteBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToByteBiFunction<T, U> toByte(final FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsByte(applyAsFloat(t, u));
    }

    /**
     * Returns a composed {@link ToCharBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToCharFunction} to apply after this operation is applied
     * @return A composed {@code ToCharBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToCharBiFunction<T, U> toChar(final FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsChar(applyAsFloat(t, u));
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code FloatToDoubleFunction} to apply after this operation is applied
     * @return A composed {@code ToDoubleBiFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToDoubleBiFunction<T, U> toDouble(final FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsDouble(applyAsFloat(t, u));
    }

    /**
     * Returns a composed {@link ToIntBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToIntFunction} to apply after this operation is applied
     * @return A composed {@code ToIntBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToIntBiFunction<T, U> toInt(final FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsInt(applyAsFloat(t, u));
    }

    /**
     * Returns a composed {@link ToLongBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToLongFunction} to apply after this operation is applied
     * @return A composed {@code ToLongBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToLongBiFunction<T, U> toLong(final FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsLong(applyAsFloat(t, u));

    }

    /**
     * Returns a composed {@link ToShortBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code FloatToShortFunction} to apply after this operation is applied
     * @return A composed {@code ToShortBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToShortBiFunction<T, U> toShort(final FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsShort(applyAsFloat(t, u));
    }

    /**
     * Returns a composed {@link BiConsumer} that fist applies this operation to its input, and then consumes the result
     * using the given {@code FloatConsumer}. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The {@code FloatConsumer} which consumes the result from this operation
     * @return A composed {@code BiConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BiConsumer<T, U> consume(FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.accept(applyAsFloat(t, u));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ToFloatBiFunction}. Thereby the primitive
     * input argument for this operation is autoboxed. This method is just convenience to provide the ability to use
     * this {@code ToFloatBiFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ToFloatBiFunction}.
     */
    default BiFunction<T, U, Float> boxed() {
        return this::applyAsFloat;
    }
}
