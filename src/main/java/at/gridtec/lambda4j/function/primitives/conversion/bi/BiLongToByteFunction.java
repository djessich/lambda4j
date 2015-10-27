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
package at.gridtec.lambda4j.function.primitives.conversion.bi;

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.LongBiConsumer;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.bi.LongBiFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToByteBiFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two long-valued arguments and produces a byte-valued result. This is the {@code
 * long}-to-{@code byte} primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(long, long)}.
 *
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiLongToByteFunction {

    /**
     * Calls the given {@link BiLongToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiLongToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static byte call(@Nonnull final BiLongToByteFunction function, long value1, long value2) {
        Objects.requireNonNull(function);
        return function.applyAsByte(value1, value2);
    }

    /**
     * Creates a {@link BiLongToByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link LongToByteFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiLongToByteFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code LongToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiLongToByteFunction onlyFirst(@Nonnull final LongToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsByte(value1);
    }

    /**
     * Creates a {@link BiLongToByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link LongToByteFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiLongToByteFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code LongToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiLongToByteFunction onlySecond(@Nonnull final LongToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsByte(value2);
    }

    /**
     * Creates a {@link BiLongToByteFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiLongToByteFunction} which always returns a given value.
     */
    @Nonnull
    static BiLongToByteFunction constant(byte ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(long value1, long value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiLongToByteFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code BiLongToByteFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code long}.
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default BiLongToByteFunction compose(@Nonnull final LongUnaryOperator before1,
            @Nonnull final LongUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByte(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link ToByteBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToByteBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default <T, U> ToByteBiFunction<T, U> compose(@Nonnull final ToLongFunction<? super T> before1,
            @Nonnull final ToLongFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsByte(before1.applyAsLong(t), before2.applyAsLong(u));
    }

    /**
     * Returns a composed {@link LongBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(LongUnaryOperator, LongUnaryOperator)
     * @see #compose(ToLongFunction, ToLongFunction)
     */
    @Nonnull
    default <R> LongBiFunction<R> andThen(@Nonnull final ByteFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsByte(value1, value2));
    }

    /**
     * Returns a composed {@link LongBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code LongBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default LongBiConsumer consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsByte(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link BiLongToByteFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BiLongToByteFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code BiLongToByteFunction}.
     */
    @Nonnull
    default BiFunction<Long, Long, Byte> boxed() {
        return this::applyAsByte;
    }
}
