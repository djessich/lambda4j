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

import at.gridtec.lambda4j.consumer.primitives.CharConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.CharTriConsumer;
import at.gridtec.lambda4j.function.primitives.CharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriCharToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToCharTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.CharTriFunction;
import at.gridtec.lambda4j.operators.binary.CharBinaryOperator;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;
import at.gridtec.lambda4j.predicates.primitives.CharPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.CharTriPredicate;
import at.gridtec.lambda4j.supplier.CharSupplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a two {@code char}-valued operands and producing a {@code char}-valued result. This is the
 * primitive type specialization of {@link TernaryOperator} for {@code char}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(char, char, char)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharTernaryOperator {

    /**
     * Calls the given {@link CharTernaryOperator} with the given arguments and returns its result.
     *
     * @param operator The operator to be called
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The result from the given {@code CharTernaryOperator}.
     * @throws NullPointerException If the given operator is {@code null}
     */
    static char call(@Nonnull final CharTernaryOperator operator, char left, char middle, char right) {
        Objects.requireNonNull(operator);
        return operator.applyAsChar(left, middle, right);
    }

    /**
     * Creates a {@link CharTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * CharUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code CharTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharTernaryOperator onlyLeft(@Nonnull final CharUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsChar(left);
    }

    /**
     * Creates a {@link CharTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * CharUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code CharTernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharTernaryOperator onlyMiddle(@Nonnull final CharUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsChar(middle);
    }

    /**
     * Creates a {@link CharTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * CharUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code CharTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharTernaryOperator onlyRight(@Nonnull final CharUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsChar(right);
    }

    /**
     * Creates a {@link CharTernaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static CharTernaryOperator constant(char ret) {
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
    char applyAsChar(char left, char middle, char right);

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
     * Returns a composed {@link CharTernaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@link CharTernaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code char}.
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default CharTernaryOperator compose(@Nonnull final CharUnaryOperator before1,
            @Nonnull final CharUnaryOperator before2, @Nonnull final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsChar(before1.applyAsChar(left), before2.applyAsChar(middle),
                                                    before3.applyAsChar(right));
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first operation to apply before this operator is applied
     * @param before2 The second operation to apply before this operator is applied
     * @param before3 The third operation to apply before this operator is applied
     * @return A composed {@link ToCharTriFunction} that first applies the {@code before} operations to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default <T, U, V> ToCharTriFunction<T, U, V> compose(@Nonnull final ToCharFunction<? super T> before1,
            @Nonnull final ToCharFunction<? super U> before2, @Nonnull final ToCharFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> applyAsChar(before1.applyAsChar(t), before2.applyAsChar(u), before3.applyAsChar(v));
    }

    /**
     * Returns a composed {@link CharTriFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code CharTriFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator, CharUnaryOperator)
     * @see #compose(ToCharFunction, ToCharFunction, ToCharFunction)
     */
    @Nonnull
    default <R> CharTriFunction<R> andThen(@Nonnull final CharFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link CharTriPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code CharTriPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default CharTriPredicate andThenToBoolean(@Nonnull final CharPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriCharToByteFunction andThenToByte(@Nonnull final CharToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link CharTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code CharTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default CharTernaryOperator andThenToChar(@Nonnull final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriCharToDoubleFunction andThenToDouble(@Nonnull final CharToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriCharToFloatFunction andThenToFloat(@Nonnull final CharToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriCharToIntFunction andThenToInt(@Nonnull final CharToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriCharToLongFunction andThenToLong(@Nonnull final CharToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriCharToShortFunction andThenToShort(@Nonnull final CharToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link CharTriConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code CharConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code CharTriConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharTriConsumer consume(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsChar(value1, value2, value3));
    }

    /**
     * Applies this operator partially to one argument. The result is an operator of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the operator
     * @return A partial application of this operator.
     */
    @Nonnull
    default CharBinaryOperator partial(char value1) {
        return (value2, value3) -> applyAsChar(value1, value2, value3);
    }

    /**
     * Applies this operator partially to two arguments. The result is an operator of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the operator
     * @param value2 The second argument to partially apply to the operator
     * @return A partial application of this operator.
     */
    @Nonnull
    default CharUnaryOperator partial(char value1, char value2) {
        return value3 -> applyAsChar(value1, value2, value3);
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
    default CharSupplier partial(char value1, char value2, char value3) {
        return () -> applyAsChar(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link CharTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code CharTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Character> boxed() {
        return this::applyAsChar;
    }
}