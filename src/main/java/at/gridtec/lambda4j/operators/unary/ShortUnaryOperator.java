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

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToLongFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single {@code short}-valued operand that produces a {@code short}-valued result. This is
 * the primitive type specialization of {@link UnaryOperator} for {@code short}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(short)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortUnaryOperator {

    /**
     * Creates a {@link ShortUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static ShortUnaryOperator constant(short ret) {
        return operand -> ret;
    }

    /**
     * Returns a {@link ShortUnaryOperator} that always returns its input argument.
     *
     * @return A {@code ShortUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static ShortUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    short applyAsShort(short operand);

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
     * Returns a composed {@link ShortUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@link ShortUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a primitive specialization of {@link UnaryOperator}. Therefore the
     * given operation handles primitive types. In this case this is {@code short}.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortUnaryOperator compose(@Nonnull final ShortUnaryOperator before) {
        Objects.requireNonNull(before);
        return operand -> applyAsShort(before.applyAsShort(operand));
    }

    /**
     * Returns a composed {@link ToShortFunction} that first applies the {@code before} operation to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The operation to apply before this operator is applied
     * @return A composed {@link ToShortFunction} that first applies the {@code before} operation to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default <T> ToShortFunction<T> compose(@Nonnull final ToShortFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsShort(before.applyAsShort(t));
    }

    /**
     * Returns a composed {@link ShortFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code ShortFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ShortUnaryOperator)
     * @see #compose(ToShortFunction)
     */
    @Nonnull
    default <R> ShortFunction<R> andThen(@Nonnull final ShortFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortToBooleanFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ShortToBooleanFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortToBooleanFunction andThenToBoolean(@Nonnull final ShortToBooleanFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ShortToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortToByteFunction andThenToByte(@Nonnull final ShortToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ShortToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortToCharFunction andThenToChar(@Nonnull final ShortToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ShortToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortToDoubleFunction andThenToDouble(@Nonnull final ShortToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ShortToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortToFloatFunction andThenToFloat(@Nonnull final ShortToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ShortToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortToIntFunction andThenToInt(@Nonnull final ShortToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ShortToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortToLongFunction andThenToLong(@Nonnull final ShortToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ShortUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortUnaryOperator andThenToShort(@Nonnull final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return operand -> after.applyAsShort(applyAsShort(operand));
    }

    /**
     * Returns a composed {@link ShortConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code ShortConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ShortConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortConsumer consume(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsShort(value));
    }

    /**
     * Returns a composed {@link UnaryOperator} which represents this {@link ShortUnaryOperator}. Thereby the primitive
     * input argument for this operator is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortUnaryOperator} with JRE specific methods, only accepting {@code UnaryOperator}.
     *
     * @return A composed {@code UnaryOperator} which represents this {@code ShortUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator<Short> boxed() {
        return this::applyAsShort;
    }
}