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
 * Represents a function that accepts a boolean-valued argument and produces a double-valued result. This is the {@code
 * boolean}-to-{@code double} primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(boolean)}.
 *
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanToDoubleFunction {

    /**
     * Creates a {@link BooleanToDoubleFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanToDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static BooleanToDoubleFunction constant(double ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to this function
     * @return The result from this function, which is its result.
     */
    double applyAsDouble(boolean value);

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
     * Returns a composed {@link BooleanToDoubleFunction} that first applies the {@code before} operation to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before The operation to apply before this function is applied
     * @return A composed {@code BooleanToDoubleFunction} that first applies the {@code before} operation to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a primitive specialization of {@link UnaryOperator}. Therefore the
     * given operation handles primitive types. In this case this is {@code byte}.
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BooleanToDoubleFunction compose(@Nonnull final BooleanUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsDouble(before.applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link ToDoubleFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the before function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToDoubleFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default <T> ToDoubleFunction<T> compose(@Nonnull final Predicate<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsDouble(before.test(t));
    }

    /**
     * Returns a composed {@link BooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(BooleanUnaryOperator)
     * @see #compose(Predicate)
     */
    @Nonnull
    default <R> BooleanFunction<R> andThen(@Nonnull final DoubleFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BooleanUnaryOperator andThenToBoolean(@Nonnull final DoubleToBooleanFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BooleanToByteFunction andThenToByte(@Nonnull final DoubleToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BooleanToCharFunction andThenToChar(@Nonnull final DoubleToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BooleanToDoubleFunction andThenToDouble(@Nonnull final DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BooleanToFloatFunction andThenToFloat(@Nonnull final DoubleToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BooleanToIntFunction andThenToInt(@Nonnull final DoubleToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BooleanToLongFunction andThenToLong(@Nonnull final DoubleToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BooleanToShortFunction andThenToShort(@Nonnull final DoubleToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link DoubleConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanConsumer consume(@Nonnull final DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link BooleanToDoubleFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BooleanToDoubleFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code BooleanToDoubleFunction}.
     */
    @Nonnull
    default Function<Boolean, Double> boxed() {
        return this::applyAsDouble;
    }
}
