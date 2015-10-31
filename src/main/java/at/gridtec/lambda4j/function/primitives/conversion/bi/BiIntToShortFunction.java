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

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.IntBiConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.bi.IntBiFunction;
import at.gridtec.lambda4j.function.primitives.conversion.IntToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToLongFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToShortBiFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two int-valued arguments and produces a short-valued result. This is the {@code
 * int}-to-{@code short} primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(int, int)}.
 *
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiIntToShortFunction {

    /**
     * Calls the given {@link BiIntToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiIntToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static short call(@Nonnull final BiIntToShortFunction function, int value1, int value2) {
        Objects.requireNonNull(function);
        return function.applyAsShort(value1, value2);
    }

    /**
     * Creates a {@link BiIntToShortFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link IntToShortFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiIntToShortFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code IntToShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiIntToShortFunction onlyFirst(@Nonnull final IntToShortFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsShort(value1);
    }

    /**
     * Creates a {@link BiIntToShortFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link IntToShortFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiIntToShortFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code IntToShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiIntToShortFunction onlySecond(@Nonnull final IntToShortFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsShort(value2);
    }

    /**
     * Creates a {@link BiIntToShortFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiIntToShortFunction} which always returns a given value.
     */
    @Nonnull
    static BiIntToShortFunction constant(short ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    short applyAsShort(int value1, int value2);

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
     * Returns a composed {@link BiIntToShortFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code BiIntToShortFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code int}.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default BiIntToShortFunction compose(@Nonnull final IntUnaryOperator before1,
            @Nonnull final IntUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShort(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link ToShortBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToShortBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default <T, U> ToShortBiFunction<T, U> compose(@Nonnull final ToIntFunction<? super T> before1,
            @Nonnull final ToIntFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsShort(before1.applyAsInt(t), before2.applyAsInt(u));
    }

    /**
     * Returns a composed {@link IntBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code IntBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(IntUnaryOperator, IntUnaryOperator)
     * @see #compose(ToIntFunction, ToIntFunction)
     */
    @Nonnull
    default <R> IntBiFunction<R> andThen(@Nonnull final ShortFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default BiIntToBooleanFunction andThenToBoolean(@Nonnull final ShortToBooleanFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsBoolean(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default BiIntToByteFunction andThenToByte(@Nonnull final ShortToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default BiIntToCharFunction andThenToChar(@Nonnull final ShortToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default BiIntToDoubleFunction andThenToDouble(@Nonnull final ShortToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default BiIntToFloatFunction andThenToFloat(@Nonnull final ShortToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code IntBinaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default IntBinaryOperator andThenToInt(@Nonnull final ShortToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default BiIntToLongFunction andThenToLong(@Nonnull final ShortToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default BiIntToShortFunction andThenToShort(@Nonnull final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link IntBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ShortConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code IntBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default IntBiConsumer consume(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link BiIntToShortFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BiIntToShortFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code BiIntToShortFunction}.
     */
    @Nonnull
    default BiFunction<Integer, Integer, Short> boxed() {
        return this::applyAsShort;
    }
}
