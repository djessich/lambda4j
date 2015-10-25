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

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

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
 * Represents a function that accepts a byte-valued argument and produces a int-valued result. This is the {@code
 * byte}-to-{@code int} primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(byte)}.
 *
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteToIntFunction {

    /**
     * Creates a {@link ByteToIntFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanToShortFunction} which always returns a given value.
     */
    @Nonnull
    static ByteToIntFunction constant(int ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to this function
     * @return The result from this function, which is its result.
     */
    int applyAsInt(byte value);

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
     * Returns a composed {@link ByteToIntFunction} that first applies the {@code before} operation to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param before The operation to apply before this function is applied
     * @return A composed {@code ByteToIntFunction} that first applies the {@code before} operation to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a primitive specialization of {@link UnaryOperator}. Therefore the
     * given operation handles primitive types. In this case this is {@code byte}.
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ByteToIntFunction compose(@Nonnull final ByteUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsInt(before.applyAsByte(value));
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
    default <T> ToIntFunction<T> compose(@Nonnull final ToByteFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsInt(before.applyAsByte(t));
    }

    /**
     * Returns a composed {@link ByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ByteUnaryOperator)
     * @see #compose(ToByteFunction)
     */
    @Nonnull
    default <R> ByteFunction<R> andThen(@Nonnull final IntFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ByteToBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ByteToBooleanFunction andThenToBoolean(@Nonnull final IntToBooleanFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ByteUnaryOperator andThenToByte(@Nonnull final IntToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ByteToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ByteToCharFunction andThenToChar(@Nonnull final IntToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ByteToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ByteToDoubleFunction andThenToDouble(@Nonnull final IntToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ByteToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ByteToFloatFunction andThenToFloat(@Nonnull final IntToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ByteToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ByteToIntFunction andThenToInt(@Nonnull final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ByteToLongFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToLongFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ByteToLongFunction andThenToLong(@Nonnull final IntToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ByteToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default ByteToShortFunction andThenToShort(@Nonnull final IntToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ByteConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ByteConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteConsumer consume(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsInt(value));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link ByteToIntFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ByteToIntFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code ByteToIntFunction}.
     */
    @Nonnull
    default Function<Byte, Integer> boxed() {
        return this::applyAsInt;
    }
}
