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
import at.gridtec.lambda4j.function.primitives.conversion.ShortToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToLongFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

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
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToShortFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ToShortFunction<T> constant(short ret) {
        return t -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     */
    short applyAsShort(T t);

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
     * Returns a composed {@link ToShortFunction} that first applies the {@code before} operation to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param before The operation to apply before this function is applied
     * @return A composed {@code ToShortFunction} that first applies the {@code before} operation to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a primitive specialization of {@link UnaryOperator}. Therefore the
     * given operation handles primitive types. In this case this is {@code short}.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToShortFunction<T> compose(@Nonnull final UnaryOperator<T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsShort(before.apply(t));
    }

    /**
     * Returns a composed {@link ToShortFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <V> The type of the argument to the before function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToShortFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default <V> ToShortFunction<V> compose(@Nonnull final Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return v -> applyAsShort(before.apply(v));
    }

    /**
     * Returns a composed {@link Function} that first applies this function to its input, and then applies the {@code
     * after} function to the result. If evaluation of either function throws an exception, it is relayed to the caller
     * of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code Function} that first applies this function to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator)
     * @see #compose(Function)
     */
    @Nonnull
    default <R> Function<T, R> andThen(@Nonnull final ShortFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return t -> after.apply(applyAsShort(t));
    }

    /**
     * Returns a composed {@link Predicate} that first applies this function to its input, and then applies the {@code
     * after} function to the result. If evaluation of either function throws an exception, it is relayed to the caller
     * of the composed function. This method is just convenience, to provide the ability to transform this function to
     * an equal function, returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code Predicate} that first applies this function to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default Predicate<T> andThenToBoolean(@Nonnull final ShortToBooleanFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsBoolean(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToByteFunction<T> andThenToByte(@Nonnull final ShortToByteFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsByte(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToCharFunction<T> andThenToChar(@Nonnull final ShortToCharFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsChar(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToDoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToDoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToDoubleFunction<T> andThenToDouble(@Nonnull final ShortToDoubleFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsDouble(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToFloatFunction<T> andThenToFloat(@Nonnull final ShortToFloatFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsFloat(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToIntFunction<T> andThenToInt(@Nonnull final ShortToIntFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsInt(applyAsShort(t));
    }

    /**
     * Returns a composed {@link ToLongFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToLongFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToLongFunction<T> andThenToLong(@Nonnull final ShortToLongFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsLong(applyAsShort(t));

    }

    /**
     * Returns a composed {@link ToShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToShortFunction<T> andThenToShort(@Nonnull final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsShort(applyAsShort(t));
    }

    /**
     * Returns a composed {@link Consumer} that fist applies this function to its input, and then consumes the result
     * using the given {@link ShortConsumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer} that fist applies this function to its input, and then consumes the result
     * using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default Consumer<T> consume(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.accept(applyAsShort(t));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link ToShortFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ToShortFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code ToShortFunction}.
     */
    @Nonnull
    default Function<T, Short> boxed() {
        return this::applyAsShort;
    }
}
