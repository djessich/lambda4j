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

import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts a double-valued argument and produces a boolean-valued result. This is the {@code
 * double}-to-{@code boolean} primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(double)}.
 *
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleToBooleanFunction {

    /**
     * Creates a {@link DoubleToBooleanFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code DoubleToBooleanFunction} which always returns a given value.
     */
    @Nonnull
    static DoubleToBooleanFunction constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to this function
     * @return The result from this function, which is its result.
     */
    boolean applyAsBoolean(double value);

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
     * Returns a composed {@link DoubleToBooleanFunction} that first applies the {@code before} operation to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before The operation to apply before this function is applied
     * @return A composed {@code DoubleToBooleanFunction} that first applies the {@code before} operation to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a primitive specialization of {@link UnaryOperator}. Therefore the
     * given operation handles primitive types. In this case this is {@code byte}.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default DoubleToBooleanFunction compose(@Nonnull final DoubleUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsBoolean(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link Predicate} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the before function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToBooleanFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default <T> Predicate<T> compose(@Nonnull final ToDoubleFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsBoolean(before.applyAsDouble(t));
    }

    /**
     * Returns a composed {@link DoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(DoubleUnaryOperator)
     * @see #compose(ToDoubleFunction)
     */
    @Nonnull
    default <R> DoubleFunction<R> andThen(@Nonnull final BooleanFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link DoubleToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default DoubleToBooleanFunction andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link DoubleToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default DoubleToByteFunction andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link DoubleToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default DoubleToCharFunction andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link DoubleUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default DoubleUnaryOperator andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link DoubleToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default DoubleToFloatFunction andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link DoubleToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default DoubleToIntFunction andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link DoubleToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default DoubleToLongFunction andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link DoubleToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default DoubleToShortFunction andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link DoubleConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code DoubleConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default DoubleConsumer consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link DoubleToBooleanFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code DoubleToBooleanFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code DoubleToBooleanFunction}.
     */
    @Nonnull
    default Function<Double, Boolean> boxed() {
        return this::applyAsBoolean;
    }
}
