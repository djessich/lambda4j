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
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToCharBiFunction;
import at.gridtec.lambda4j.predicates.TriPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that produces a double-valued result from three arguments. This is the {@code double}-producing
 * primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToDoubleTriFunction<T, U, V> {

    /**
     * Creates a {@link ToDoubleTriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToDoubleTriFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, V> ToDoubleTriFunction<T, U, V> constant(double ret) {
        return (t, u, v) -> ret;
    }

    /**
     * Creates a {@link ToDoubleTriFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToDoubleTriFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code ToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToDoubleTriFunction<T, U, V> onlyFirst(@Nonnull final ToDoubleFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsDouble(t);
    }

    /**
     * Creates a {@link ToDoubleTriFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToDoubleTriFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code ToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToDoubleTriFunction<T, U, V> onlySecond(@Nonnull final ToDoubleFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsDouble(u);
    }

    /**
     * Creates a {@link ToDoubleTriFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link ToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToDoubleTriFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@code ToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToDoubleTriFunction<T, U, V> onlyThird(@Nonnull final ToDoubleFunction<? super V> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsDouble(v);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The return value from the function, which is its result.
     */
    double applyAsDouble(T t, U u, V v);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToDoubleTriFunction} that first applies the {@code before} {@link UnaryOperator}s to
     * its input, and then applies this operation to the result. If evaluation of either operation throws an exception,
     * it is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code UnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code UnaryOperator} to apply before this operation is applied
     * @param before3 The third {@code UnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToDoubleTriFunction} that first applies the {@code before} {@code UnaryOperator}s to
     * its input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleUnaryOperator)
     * @see #andThen(DoubleFunction)
     */
    default ToDoubleTriFunction<T, U, V> compose(final UnaryOperator<T> before1, final UnaryOperator<U> before2,
            final UnaryOperator<V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> applyAsDouble(before1.apply(t), before2.apply(u), before3.apply(v));
    }

    /**
     * Returns a composed {@link ToDoubleTriFunction} that applies the given {@code before} {@link Function}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <A> The type of the first argument to the before operation
     * @param <B> The type of the second argument to the before operation
     * @param <C> The type of the third argument to the before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code Function} to apply before this operation is applied
     * @param before3 The third before {@code Function} to apply before this operation is applied
     * @return A composed {@code ToDoubleTriFunction} that applies the given {@code before} {@code Function}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(DoubleUnaryOperator)
     * @see #andThen(DoubleFunction)
     */
    default <A, B, C> ToDoubleTriFunction<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsDouble(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link ToDoubleTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code DoubleUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToDoubleTriFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator, UnaryOperator, UnaryOperator)
     * @see #compose(Function, Function, Function)
     */
    default ToDoubleTriFunction<T, U, V> andThen(final DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsDouble(applyAsDouble(t, u, v));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code DoubleFunction} to apply after this operation is applied
     * @return A composed {@code TriFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator, UnaryOperator, UnaryOperator)
     * @see #compose(Function, Function, Function)
     */
    default <R> TriFunction<T, U, V, R> andThen(final DoubleFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(applyAsDouble(t, u, v));
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code DoubleToBooleanFunction} to apply after this operation is applied
     * @return A composed {@code TriPredicate} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default TriPredicate<T, U, V> toBoolean(final DoubleToBooleanFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsBoolean(applyAsDouble(t, u, v));
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code DoubleToByteFunction} to apply after this operation is applied
     * @return A composed {@code ToByteTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToByteTriFunction<T, U, V> toByte(final DoubleToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsByte(applyAsDouble(t, u, v));
    }

    /**
     * Returns a composed {@link ToCharBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code DoubleToCharFunction} to apply after this operation is applied
     * @return A composed {@code ToCharBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToCharTriFunction<T, U, V> toChar(final DoubleToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsChar(applyAsDouble(t, u, v));
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code DoubleToFloatFunction} to apply after this operation is applied
     * @return A composed {@code ToFloatTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToFloatTriFunction<T, U, V> toFloat(final DoubleToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsFloat(applyAsDouble(t, u, v));
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code DoubleToIntFunction} to apply after this operation is applied
     * @return A composed {@code ToIntTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToIntTriFunction<T, U, V> toInt(final DoubleToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsInt(applyAsDouble(t, u, v));
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code DoubleToLongFunction} to apply after this operation is applied
     * @return A composed {@code ToLongTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToLongTriFunction<T, U, V> toLong(final DoubleToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsLong(applyAsDouble(t, u, v));

    }

    /**
     * Returns a composed {@link ToShortTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code DoubleToShortFunction} to apply after this operation is applied
     * @return A composed {@code ToShortTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToShortTriFunction<T, U, V> toShort(final DoubleToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsShort(applyAsDouble(t, u, v));
    }

    /**
     * Returns a composed {@link TriConsumer} that fist applies this operation to its input, and then consumes
     * the result using the given {@link DoubleConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriConsumer<T, U, V> consume(@Nonnull final DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(applyAsDouble(t, u, v));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ToDoubleTriFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ToDoubleTriFunction}.
     */
    @Nonnull
    default TriFunction<T, U, V, Double> boxed() {
        return this::applyAsDouble;
    }
}
