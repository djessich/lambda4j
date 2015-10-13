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
import at.gridtec.lambda4j.function.primitives.conversion.IntToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.IntToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.IntToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.IntToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.IntToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToCharBiFunction;
import at.gridtec.lambda4j.predicates.TriPredicate;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that produces a int-valued result from three arguments. This is the {@code int}-producing
 * primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToIntTriFunction<T, U, V> {

    /**
     * Creates a {@link ToIntTriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param value The return value for the constant
     * @return A {@code ToIntTriFunction} which always returns a given value.
     */
    static <T, U, V> ToIntTriFunction<T, U, V> constant(byte value) {
        return (t, u, v) -> value;
    }

    /**
     * Creates a {@link ToIntTriFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link ToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToIntTriFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code ToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToIntTriFunction<T, U, V> onlyFirst(@Nonnull final ToIntFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsInt(t);
    }

    /**
     * Creates a {@link ToIntTriFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link ToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToIntTriFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code ToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToIntTriFunction<T, U, V> onlySecond(@Nonnull final ToIntFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsInt(u);
    }

    /**
     * Creates a {@link ToIntTriFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@link ToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToIntTriFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@code ToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToIntTriFunction<T, U, V> onlyThird(@Nonnull final ToIntFunction<? super V> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsInt(v);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The return value from the function, which is its result.
     */
    int applyAsInt(T t, U u, V v);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that first applies the {@code before} {@link UnaryOperator}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code UnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code UnaryOperator} to apply before this operation is applied
     * @param before3 The third {@code UnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToIntTriFunction} that first applies the {@code before} {@code UnaryOperator}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntUnaryOperator)
     * @see #andThen(IntFunction)
     */
    default ToIntTriFunction<T, U, V> compose(final UnaryOperator<T> before1, final UnaryOperator<U> before2,
            final UnaryOperator<V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> applyAsInt(before1.apply(t), before2.apply(u), before3.apply(v));
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that applies the given {@code before} {@link Function}s to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <A> The type of the first argument to the before operation
     * @param <B> The type of the second argument to the before operation
     * @param <C> The type of the third argument to the before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code Function} to apply before this operation is applied
     * @param before3 The third before {@code Function} to apply before this operation is applied
     * @return A composed {@code ToIntTriFunction} that applies the given {@code before} {@code Function}s to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(IntUnaryOperator)
     * @see #andThen(IntFunction)
     */
    default <A, B, C> ToIntTriFunction<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsInt(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code IntUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToIntTriFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator, UnaryOperator, UnaryOperator)
     * @see #compose(Function, Function, Function)
     */
    default ToIntTriFunction<T, U, V> andThen(final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsInt(applyAsInt(t, u, v));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code IntFunction} to apply after this operation is applied
     * @return A composed {@code TriFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator, UnaryOperator, UnaryOperator)
     * @see #compose(Function, Function, Function)
     */
    default <R> TriFunction<T, U, V, R> andThen(final IntFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(applyAsInt(t, u, v));
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code IntToBooleanFunction} to apply after this operation is applied
     * @return A composed {@code TriPredicate} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default TriPredicate<T, U, V> toBoolean(final IntToBooleanFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsBoolean(applyAsInt(t, u, v));
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code IntToByteFunction} to apply after this operation is applied
     * @return A composed {@code ToByteTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToByteTriFunction<T, U, V> toByte(final IntToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsByte(applyAsInt(t, u, v));
    }

    /**
     * Returns a composed {@link ToCharBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code IntToCharFunction} to apply after this operation is applied
     * @return A composed {@code ToCharBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToCharTriFunction<T, U, V> toChar(final IntToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsChar(applyAsInt(t, u, v));
    }

    /**
     * Returns a composed {@link ToDoubleTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code IntToDoubleFunction} to apply after this operation is applied
     * @return A composed {@code ToDoubleTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToDoubleTriFunction<T, U, V> toDouble(final IntToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsDouble(applyAsInt(t, u, v));
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code IntToFloatFunction} to apply after this operation is applied
     * @return A composed {@code ToFloatTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToFloatTriFunction<T, U, V> toFloat(final IntToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsFloat(applyAsInt(t, u, v));
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code IntToLongFunction} to apply after this operation is applied
     * @return A composed {@code ToLongTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToLongTriFunction<T, U, V> toLong(final IntToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsLong(applyAsInt(t, u, v));

    }

    /**
     * Returns a composed {@link ToShortTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code IntToShortFunction} to apply after this operation is applied
     * @return A composed {@code ToShortTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToShortTriFunction<T, U, V> toShort(final IntToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsShort(applyAsInt(t, u, v));
    }

    /**
     * Returns a composed {@link TriConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code IntConsumer}. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The {@code IntConsumer} which consumes the result from this operation
     * @return A composed {@code TriConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default TriConsumer<T, U, V> consume(IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(applyAsInt(t, u, v));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ToIntTriFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     * @return A composed {@code TriFunction} which represents this {@code ToIntTriFunction}.
     */
    @Nonnull
    default TriFunction<T, U, V, Integer> boxed() {
        return this::applyAsInt;
    }
}
