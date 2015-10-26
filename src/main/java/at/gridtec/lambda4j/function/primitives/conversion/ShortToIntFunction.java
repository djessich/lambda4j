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
package at.gridtec.lambda4j.function.primitives.conversion;

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import javax.annotation.Nonnegative;
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
 * Represents a function that accepts a short-valued argument and produces a int-valued result. This is the {@code
 * short}-to-{@code int} primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(short)}.
 *
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortToIntFunction {

    /**
     * Calls the given {@link ShortToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ShortToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static int call(@Nonnull final ShortToIntFunction function, short value) {
        Objects.requireNonNull(function);
        return function.applyAsInt(value);
    }

    /**
     * Creates a {@link ShortToIntFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortToIntFunction} which always returns a given value.
     */
    @Nonnull
    static ShortToIntFunction constant(int ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The result from this function, which is its result.
     */
    int applyAsInt(short value);

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
     * Returns a composed {@link ShortToIntFunction} that first applies the {@code before} operation to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param before The operation to apply before this function is applied
     * @return A composed {@code ShortToIntFunction} that first applies the {@code before} operation to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a primitive specialization of {@link UnaryOperator}. Therefore the
     * given operation handles primitive types. In this case this is {@code byte}.
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ShortToIntFunction compose(@Nonnull final ShortUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsInt(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ToIntFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the before function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToIntFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default <T> ToIntFunction<T> compose(@Nonnull final ToShortFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsInt(before.applyAsShort(t));
    }

    /**
     * Returns a composed {@link ShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ShortUnaryOperator)
     * @see #compose(ToShortFunction)
     */
    @Nonnull
    default <R> ShortFunction<R> andThen(@Nonnull final IntFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ShortToBooleanFunction andThenToBoolean(@Nonnull final IntToBooleanFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ShortToByteFunction andThenToByte(@Nonnull final IntToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ShortToCharFunction andThenToChar(@Nonnull final IntToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ShortToDoubleFunction andThenToDouble(@Nonnull final IntToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ShortToFloatFunction andThenToFloat(@Nonnull final IntToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ShortToIntFunction andThenToInt(@Nonnull final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ShortToLongFunction andThenToLong(@Nonnull final IntToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ShortUnaryOperator andThenToShort(@Nonnull final IntToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortConsumer consume(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsInt(value));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link ShortToIntFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortToIntFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code ShortToIntFunction}.
     */
    @Nonnull
    default Function<Short, Integer> boxed() {
        return this::applyAsInt;
    }
}
