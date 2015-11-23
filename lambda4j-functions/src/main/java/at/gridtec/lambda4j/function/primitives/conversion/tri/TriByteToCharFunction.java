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
package at.gridtec.lambda4j.function.primitives.conversion.tri;

import at.gridtec.lambda4j.consumer.primitives.CharConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.ByteTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.CharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToCharTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.ByteTriFunction;
import at.gridtec.lambda4j.operators.ternary.ByteTernaryOperator;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;
import at.gridtec.lambda4j.predicates.primitives.CharPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.ByteTriPredicate;
import at.gridtec.lambda4j.supplier.CharSupplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts three byte-valued arguments and produces a char-valued result. This is the {@code
 * byte}-to-{@code char} primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(byte, byte, byte)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriByteToCharFunction {

    /**
     * Calls the given {@link TriByteToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriByteToCharFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static char call(@Nonnull final TriByteToCharFunction function, byte value1, byte value2, byte value3) {
        Objects.requireNonNull(function);
        return function.applyAsChar(value1, value2, value3);
    }

    /**
     * Creates a {@link TriByteToCharFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ByteToCharFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriByteToCharFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ByteToCharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriByteToCharFunction onlyFirst(@Nonnull final ByteToCharFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsChar(value1);
    }

    /**
     * Creates a {@link TriByteToCharFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ByteToCharFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriByteToCharFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ByteToCharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriByteToCharFunction onlySecond(@Nonnull final ByteToCharFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsChar(value2);
    }

    /**
     * Creates a {@link TriByteToCharFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link ByteToCharFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriByteToCharFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code ByteToCharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriByteToCharFunction onlyThird(@Nonnull final ByteToCharFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsChar(value3);
    }

    /**
     * Creates a {@link TriByteToCharFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriByteToCharFunction} which always returns a given value.
     */
    @Nonnull
    static TriByteToCharFunction constant(char ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    char applyAsChar(byte value1, byte value2, byte value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link TriByteToCharFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code TriByteToCharFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code byte}.
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriByteToCharFunction compose(@Nonnull final ByteUnaryOperator before1,
            @Nonnull final ByteUnaryOperator before2, @Nonnull final ByteUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.applyAsByte(value1), before2.applyAsByte(value2),
                                                       before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param <V> The type of the argument to the third before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToCharTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default <T, U, V> ToCharTriFunction<T, U, V> compose(@Nonnull final ToByteFunction<? super T> before1,
            @Nonnull final ToByteFunction<? super U> before2, @Nonnull final ToByteFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u, v) -> applyAsChar(before1.applyAsByte(t), before2.applyAsByte(u), before3.applyAsByte(v));
    }

    /**
     * Returns a composed {@link ByteTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ByteUnaryOperator, ByteUnaryOperator, ByteUnaryOperator)
     * @see #compose(ToByteFunction, ToByteFunction, ToByteFunction)
     */
    @Nonnull
    default <R> ByteTriFunction<R> andThen(@Nonnull final CharFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ByteTriPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteTriPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default ByteTriPredicate andThenToBoolean(@Nonnull final CharPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default ByteTernaryOperator andThenToByte(@Nonnull final CharToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriByteToCharFunction andThenToChar(@Nonnull final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriByteToDoubleFunction andThenToDouble(@Nonnull final CharToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriByteToFloatFunction andThenToFloat(@Nonnull final CharToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriByteToIntFunction andThenToInt(@Nonnull final CharToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriByteToLongFunction andThenToLong(@Nonnull final CharToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default TriByteToShortFunction andThenToShort(@Nonnull final CharToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ByteTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link CharConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ByteTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteTriConsumer consume(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsChar(value1, value2, value3));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default BiByteToCharFunction partial(byte value1) {
        return (value2, value3) -> applyAsChar(value1, value2, value3);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ByteToCharFunction partial(byte value1, byte value2) {
        return value3 -> applyAsChar(value1, value2, value3);
    }

    /**
     * Applies this function partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @param value3 The third argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default CharSupplier partial(byte value1, byte value2, byte value3) {
        return () -> applyAsChar(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriByteToCharFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriByteToCharFunction}.
     */
    @Nonnull
    default TriFunction<Byte, Byte, Byte, Character> boxed() {
        return this::applyAsChar;
    }
}
