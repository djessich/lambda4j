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
package at.gridtec.lambda4j.function.primitives.to.tri;

import at.gridtec.lambda4j.consumer.TriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToCharBiFunction;
import at.gridtec.lambda4j.predicates.TriPredicate;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that produces a long-valued result from three arguments. This is the {@code long}-producing
 * primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToLongTriFunction<T, U, V> {

    /**
     * Creates a {@link ToLongTriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param value The return value for the constant
     * @return A {@code ToLongTriFunction} which always returns a given value.
     */
    static <T, U, V> ToLongTriFunction<T, U, V> constant(byte value) {
        return (t, u, v) -> value;
    }

    /**
     * Creates a {@link ToLongTriFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link ToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToLongTriFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code ToLongFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToLongTriFunction<T, U, V> onlyFirst(@Nonnull final ToLongFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsLong(t);
    }

    /**
     * Creates a {@link ToLongTriFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link ToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToLongTriFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code ToLongFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToLongTriFunction<T, U, V> onlySecond(@Nonnull final ToLongFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsLong(u);
    }

    /**
     * Creates a {@link ToLongTriFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@link ToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToLongTriFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@code ToLongFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToLongTriFunction<T, U, V> onlyThird(@Nonnull final ToLongFunction<? super V> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsLong(v);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The return value from the function, which is its result.
     */
    long applyAsLong(T t, U u, V v);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that first applies the {@code before} {@link UnaryOperator}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code UnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code UnaryOperator} to apply before this operation is applied
     * @param before3 The third {@code UnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToLongTriFunction} that first applies the {@code before} {@code UnaryOperator}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongUnaryOperator)
     * @see #andThen(LongFunction)
     */
    default ToLongTriFunction<T, U, V> compose(final UnaryOperator<T> before1, final UnaryOperator<U> before2,
            final UnaryOperator<V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> applyAsLong(before1.apply(t), before2.apply(u), before3.apply(v));
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that applies the given {@code before} {@link Function}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <A> The type of the first argument to the before operation
     * @param <B> The type of the second argument to the before operation
     * @param <C> The type of the third argument to the before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code Function} to apply before this operation is applied
     * @param before3 The third before {@code Function} to apply before this operation is applied
     * @return A composed {@code ToLongTriFunction} that applies the given {@code before} {@code Function}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(LongUnaryOperator)
     * @see #andThen(LongFunction)
     */
    default <A, B, C> ToLongTriFunction<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsLong(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code LongUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToLongTriFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator, UnaryOperator, UnaryOperator)
     * @see #compose(Function, Function, Function)
     */
    default ToLongTriFunction<T, U, V> andThen(final LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsLong(applyAsLong(t, u, v));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code LongFunction} to apply after this operation is applied
     * @return A composed {@code TriFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator, UnaryOperator, UnaryOperator)
     * @see #compose(Function, Function, Function)
     */
    default <R> TriFunction<T, U, V, R> andThen(final LongFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(applyAsLong(t, u, v));
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code LongToBooleanFunction} to apply after this operation is applied
     * @return A composed {@code TriPredicate} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default TriPredicate<T, U, V> toBoolean(final LongToBooleanFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsBoolean(applyAsLong(t, u, v));
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code LongToByteFunction} to apply after this operation is applied
     * @return A composed {@code ToByteTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToByteTriFunction<T, U, V> toByte(final LongToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsByte(applyAsLong(t, u, v));
    }

    /**
     * Returns a composed {@link ToCharBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code LongToCharFunction} to apply after this operation is applied
     * @return A composed {@code ToCharBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToCharTriFunction<T, U, V> toChar(final LongToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsChar(applyAsLong(t, u, v));
    }

    /**
     * Returns a composed {@link ToDoubleTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code LongToDoubleFunction} to apply after this operation is applied
     * @return A composed {@code ToDoubleTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToDoubleTriFunction<T, U, V> toDouble(final LongToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsDouble(applyAsLong(t, u, v));
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code LongToFloatFunction} to apply after this operation is applied
     * @return A composed {@code ToFloatTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToFloatTriFunction<T, U, V> toFloat(final LongToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsFloat(applyAsLong(t, u, v));
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code LongToIntFunction} to apply after this operation is applied
     * @return A composed {@code ToIntTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToIntTriFunction<T, U, V> toInt(final LongToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsInt(applyAsLong(t, u, v));
    }

    /**
     * Returns a composed {@link ToShortTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code LongToShortFunction} to apply after this operation is applied
     * @return A composed {@code ToShortTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToShortTriFunction<T, U, V> toShort(final LongToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsShort(applyAsLong(t, u, v));
    }

    /**
     * Returns a composed {@link TriConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code LongConsumer}. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The {@code LongConsumer} which consumes the result from this operation
     * @return A composed {@code TriConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default TriConsumer<T, U, V> consume(LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(applyAsLong(t, u, v));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ToLongTriFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     * @return A composed {@code TriFunction} which represents this {@code ToLongTriFunction}.
     */
    @Nonnull
    default TriFunction<T, U, V, Long> boxed() {
        return this::applyAsLong;
    }
}
