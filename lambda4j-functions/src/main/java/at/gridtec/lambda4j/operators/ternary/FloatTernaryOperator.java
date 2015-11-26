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
package at.gridtec.lambda4j.operators.ternary;

import at.gridtec.lambda4j.consumer.primitives.FloatConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.FloatTriConsumer;
import at.gridtec.lambda4j.function.primitives.FloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriFloatToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToFloatTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.FloatTriFunction;
import at.gridtec.lambda4j.operators.binary.FloatBinaryOperator;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.predicates.primitives.FloatPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.FloatTriPredicate;
import at.gridtec.lambda4j.supplier.FloatSupplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a two {@code float}-valued operands and producing a {@code float}-valued result. This is
 * the primitive type specialization of {@link TernaryOperator} for {@code float}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(float, float, float)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatTernaryOperator {

    /**
     * Calls the given {@link FloatTernaryOperator} with the given arguments and returns its result.
     *
     * @param operator The operator to be called
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The result from the given {@code FloatTernaryOperator}.
     * @throws NullPointerException If the given operator is {@code null}
     */
    static float call(@Nonnull final FloatTernaryOperator operator, float left, float middle, float right) {
        Objects.requireNonNull(operator);
        return operator.applyAsFloat(left, middle, right);
    }

    /**
     * Creates a {@link FloatTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code FloatTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatTernaryOperator onlyLeft(@Nonnull final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(left);
    }

    /**
     * Creates a {@link FloatTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code FloatTernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatTernaryOperator onlyMiddle(@Nonnull final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(middle);
    }

    /**
     * Creates a {@link FloatTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code FloatTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatTernaryOperator onlyRight(@Nonnull final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(right);
    }

    /**
     * Creates a {@link FloatTernaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static FloatTernaryOperator constant(float ret) {
        return (left, middle, right) -> ret;
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    float applyAsFloat(float left, float middle, float right);

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@link FloatTernaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code float}.
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default FloatTernaryOperator compose(@Nonnull final FloatUnaryOperator before1,
            @Nonnull final FloatUnaryOperator before2, @Nonnull final FloatUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsFloat(before1.applyAsFloat(left), before2.applyAsFloat(middle),
                                                     before3.applyAsFloat(right));
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first operation to apply before this operator is applied
     * @param before2 The second operation to apply before this operator is applied
     * @param before3 The third operation to apply before this operator is applied
     * @return A composed {@link ToFloatTriFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default <T, U, V> ToFloatTriFunction<T, U, V> compose(@Nonnull final ToFloatFunction<? super T> before1,
            @Nonnull final ToFloatFunction<? super U> before2, @Nonnull final ToFloatFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> applyAsFloat(before1.applyAsFloat(t), before2.applyAsFloat(u), before3.applyAsFloat(v));
    }

    /**
     * Returns a composed {@link FloatTriFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code FloatTriFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(FloatUnaryOperator, FloatUnaryOperator, FloatUnaryOperator)
     * @see #compose(ToFloatFunction, ToFloatFunction, ToFloatFunction)
     */
    @Nonnull
    default <R> FloatTriFunction<R> andThen(@Nonnull final FloatFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link FloatTriPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatTriPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default FloatTriPredicate andThenToBoolean(@Nonnull final FloatPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default TriFloatToByteFunction andThenToByte(@Nonnull final FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default TriFloatToCharFunction andThenToChar(@Nonnull final FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default TriFloatToDoubleFunction andThenToDouble(@Nonnull final FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default FloatTernaryOperator andThenToFloat(@Nonnull final FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default TriFloatToIntFunction andThenToInt(@Nonnull final FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default TriFloatToLongFunction andThenToLong(@Nonnull final FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default TriFloatToShortFunction andThenToShort(@Nonnull final FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsFloat(value1, value2, value3));
    }

    /**
     * Returns a composed {@link FloatTriConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code FloatTriConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatTriConsumer consume(@Nonnull final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsFloat(value1, value2, value3));
    }

    /**
     * Applies this operator partially to one argument. The result is an operator of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the operator
     * @return A partial application of this operator.
     */
    @Nonnull
    default FloatBinaryOperator partial(float value1) {
        return (value2, value3) -> applyAsFloat(value1, value2, value3);
    }

    /**
     * Applies this operator partially to two arguments. The result is an operator of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the operator
     * @param value2 The second argument to partially apply to the operator
     * @return A partial application of this operator.
     */
    @Nonnull
    default FloatUnaryOperator partial(float value1, float value2) {
        return value3 -> applyAsFloat(value1, value2, value3);
    }

    /**
     * Applies this operator partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the operator
     * @param value2 The second argument to partially apply to the operator
     * @param value3 The third argument to partially apply to the operator
     * @return A partial application of this operator.
     */
    @Nonnull
    default FloatSupplier partial(float value1, float value2, float value3) {
        return () -> applyAsFloat(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link FloatTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code FloatTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Float> boxed() {
        return this::applyAsFloat;
    }
}