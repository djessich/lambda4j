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
package at.gridtec.lambda4j.operators.unary;

import at.gridtec.lambda4j.consumer.primitives.FloatConsumer;
import at.gridtec.lambda4j.function.primitives.FloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single {@code float}-valued operand that produces a {@code float}-valued result. This is
 * the primitive type specialization of {@link UnaryOperator} for {@code float}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(float)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatUnaryOperator {

    /**
     * Creates a {@link FloatUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static FloatUnaryOperator constant(float ret) {
        return operand -> ret;
    }

    /**
     * Returns a {@link FloatUnaryOperator} that always returns its input argument.
     *
     * @return A {@code FloatUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static FloatUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    float applyAsFloat(float operand);

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code FloatUnaryOperator} that first applies the {@code before} operator and then applies
     * this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is the primitive specialization of {@link UnaryOperator}. Therefore
     * the given operation handles primitive types. In this case this is {@code float}.
     * @see #andThen(FloatUnaryOperator)
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default FloatUnaryOperator compose(@Nonnull final FloatUnaryOperator before) {
        Objects.requireNonNull(before);
        return operand -> applyAsFloat(before.applyAsFloat(operand));
    }

    /**
     * Returns a composed {@link ToFloatFunction} that first applies the {@code before} operation to its input, and then
     * applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code ToFloatFunction} that first applies the {@code before} operation and then applies this
     * operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(FloatUnaryOperator)
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default <T> ToFloatFunction<T> compose(@Nonnull final ToFloatFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsFloat(before.applyAsFloat(t));
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code FloatUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is the primitive specialization of {@link UnaryOperator}. Therefore the
     * returned operation handles primitive types. In this case this is {@code float}.
     * @see #compose(FloatUnaryOperator)
     * @see #compose(ToFloatFunction)
     */
    @Nonnull
    default FloatUnaryOperator andThen(@Nonnull final FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return operand -> after.applyAsFloat(applyAsFloat(operand));
    }

    /**
     * Returns a composed {@link FloatFunction} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code FloatFunction} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned operation is able to handle every type.
     * @see #compose(FloatUnaryOperator)
     * @see #compose(ToFloatFunction)
     */
    @Nonnull
    default <R> FloatFunction<R> andThen(@Nonnull final FloatFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToBooleanFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code boolean}, using the {@code float}-to-{@code boolean} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code FloatToBooleanFunction} that first gets the result from this operation, and then
     * applies the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatToBooleanFunction toBoolean(@Nonnull final FloatToBooleanFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code byte}, using the {@code float}-to-{@code byte} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code FloatToByteFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatToByteFunction toByte(@Nonnull final FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code char}, using the {@code float}-to-{@code char} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code FloatToCharFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatToCharFunction toChar(@Nonnull final FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code double}, using the {@code float}-to-{@code double} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code FloatToDoubleFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatToDoubleFunction toDouble(@Nonnull final FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operator to {@code int}, using the {@code float}-to-{@code int} primitive specialization of {@link Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code FloatToIntFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatToIntFunction toInt(@Nonnull final FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code long}, using the {@code float}-to-{@code long} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code FloatToLongFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatToLongFunction toLong(@Nonnull final FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code short}, using the {@code float}-to-{@code short} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code FloatToShortFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatToShortFunction toShort(@Nonnull final FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code FloatConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatConsumer consume(@Nonnull final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link UnaryOperator} which represents this {@link FloatUnaryOperator}. Thereby the primitive
     * input argument for this operator is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatUnaryOperator} with JRE specific methods, only accepting {@code UnaryOperator}.
     *
     * @return A composed {@code UnaryOperator} which represents this {@code FloatUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator<Float> boxed() {
        return this::applyAsFloat;
    }
}