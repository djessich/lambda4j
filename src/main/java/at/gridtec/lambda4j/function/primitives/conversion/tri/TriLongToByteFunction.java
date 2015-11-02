/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */
package at.gridtec.lambda4j.function.primitives.conversion.tri;

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.LongTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToByteTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.LongTriFunction;
import at.gridtec.lambda4j.operators.ternary.LongTernaryOperator;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts three long-valued arguments and produces a byte-valued result. This is the {@code
 * long}-to-{@code byte} primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(long, long, long)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriLongToByteFunction {

    /**
     * Calls the given {@link TriLongToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriLongToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static byte call(@Nonnull final TriLongToByteFunction function, long value1, long value2, long value3) {
        Objects.requireNonNull(function);
        return function.applyAsByte(value1, value2, value3);
    }

    /**
     * Creates a {@link TriLongToByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link LongToByteFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriLongToByteFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code LongToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriLongToByteFunction onlyFirst(@Nonnull final LongToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsByte(value1);
    }

    /**
     * Creates a {@link TriLongToByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link LongToByteFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriLongToByteFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code LongToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriLongToByteFunction onlySecond(@Nonnull final LongToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsByte(value2);
    }

    /**
     * Creates a {@link TriLongToByteFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link LongToByteFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriLongToByteFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code LongToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriLongToByteFunction onlyThird(@Nonnull final LongToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsByte(value3);
    }

    /**
     * Creates a {@link TriLongToByteFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriLongToByteFunction} which always returns a given value.
     */
    @Nonnull
    static TriLongToByteFunction constant(byte ret) {
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
    byte applyAsByte(long value1, long value2, long value3);

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
     * Returns a composed {@link TriLongToByteFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code TriLongToByteFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code long}.
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriLongToByteFunction compose(@Nonnull final LongUnaryOperator before1,
            @Nonnull final LongUnaryOperator before2, @Nonnull final LongUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.applyAsLong(value1), before2.applyAsLong(value2),
                                                       before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param <V> The type of the argument to the third before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToByteTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default <T, U, V> ToByteTriFunction<T, U, V> compose(@Nonnull final ToLongFunction<? super T> before1,
            @Nonnull final ToLongFunction<? super U> before2, @Nonnull final ToLongFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u, v) -> applyAsByte(before1.applyAsLong(t), before2.applyAsLong(u), before3.applyAsLong(v));
    }

    /**
     * Returns a composed {@link LongTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(LongUnaryOperator, LongUnaryOperator, LongUnaryOperator)
     * @see #compose(ToLongFunction, ToLongFunction, ToLongFunction)
     */
    @Nonnull
    default <R> LongTriFunction<R> andThen(@Nonnull final ByteFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriLongToBooleanFunction andThenToBoolean(@Nonnull final ByteToBooleanFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsBoolean(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriLongToByteFunction andThenToByte(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriLongToCharFunction andThenToChar(@Nonnull final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriLongToDoubleFunction andThenToDouble(@Nonnull final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriLongToFloatFunction andThenToFloat(@Nonnull final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriLongToIntFunction andThenToInt(@Nonnull final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default LongTernaryOperator andThenToLong(@Nonnull final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriLongToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriLongToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriLongToShortFunction andThenToShort(@Nonnull final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link LongTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code LongTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default LongTriConsumer consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriLongToByteFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriLongToByteFunction}.
     */
    @Nonnull
    default TriFunction<Long, Long, Long, Byte> boxed() {
        return this::applyAsByte;
    }
}
