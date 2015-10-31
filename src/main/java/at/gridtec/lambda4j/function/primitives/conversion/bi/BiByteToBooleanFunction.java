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

import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.ByteBiConsumer;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.function.primitives.bi.ByteBiFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.operators.binary.ByteBinaryOperator;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two byte-valued arguments and produces a boolean-valued result. This is the {@code
 * byte}-to-{@code boolean} primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(byte, byte)}.
 *
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiByteToBooleanFunction {

    /**
     * Calls the given {@link BiByteToBooleanFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiByteToBooleanFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static boolean call(@Nonnull final BiByteToBooleanFunction function, byte value1, byte value2) {
        Objects.requireNonNull(function);
        return function.applyAsBoolean(value1, value2);
    }

    /**
     * Creates a {@link BiByteToBooleanFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ByteToBooleanFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiByteToBooleanFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ByteToBooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiByteToBooleanFunction onlyFirst(@Nonnull final ByteToBooleanFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsBoolean(value1);
    }

    /**
     * Creates a {@link BiByteToBooleanFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ByteToBooleanFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiByteToBooleanFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ByteToBooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiByteToBooleanFunction onlySecond(@Nonnull final ByteToBooleanFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsBoolean(value2);
    }

    /**
     * Creates a {@link BiByteToBooleanFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiByteToBooleanFunction} which always returns a given value.
     */
    @Nonnull
    static BiByteToBooleanFunction constant(boolean ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    boolean applyAsBoolean(byte value1, byte value2);

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
     * Returns a composed {@link BiByteToBooleanFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code BiByteToBooleanFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code byte}.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiByteToBooleanFunction compose(@Nonnull final ByteUnaryOperator before1,
            @Nonnull final ByteUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsBoolean(before1.applyAsByte(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link BiPredicate} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiPredicate} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default <T, U> BiPredicate<T, U> compose(@Nonnull final ToByteFunction<? super T> before1,
            @Nonnull final ToByteFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsBoolean(before1.applyAsByte(t), before2.applyAsByte(u));
    }

    /**
     * Returns a composed {@link ByteBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ByteUnaryOperator, ByteUnaryOperator)
     * @see #compose(ToByteFunction, ToByteFunction)
     */
    @Nonnull
    default <R> ByteBiFunction<R> andThen(@Nonnull final BooleanFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiByteToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiByteToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiByteToBooleanFunction andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsBoolean(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link ByteBinaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteBinaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default ByteBinaryOperator andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiByteToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiByteToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiByteToCharFunction andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiByteToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiByteToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiByteToDoubleFunction andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiByteToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiByteToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiByteToFloatFunction andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiByteToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiByteToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiByteToIntFunction andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiByteToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiByteToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiByteToLongFunction andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiByteToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiByteToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiByteToShortFunction andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link ByteBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ByteBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteBiConsumer consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link BiByteToBooleanFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method is just convenience to provide the ability
     * to use this {@code BiByteToBooleanFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code BiByteToBooleanFunction}.
     */
    @Nonnull
    default BiFunction<Byte, Byte, Boolean> boxed() {
        return this::applyAsBoolean;
    }
}
