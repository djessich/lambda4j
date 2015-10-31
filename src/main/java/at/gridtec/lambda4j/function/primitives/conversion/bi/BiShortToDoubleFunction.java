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

import at.gridtec.lambda4j.consumer.primitives.bi.ShortBiConsumer;
import at.gridtec.lambda4j.function.primitives.bi.ShortBiFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.DoubleToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.binary.ShortBinaryOperator;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ToDoubleBiFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two short-valued arguments and produces a double-valued result. This is the {@code
 * short}-to-{@code double} primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(short, short)}.
 *
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiShortToDoubleFunction {

    /**
     * Calls the given {@link BiShortToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiShortToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static double call(@Nonnull final BiShortToDoubleFunction function, short value1, short value2) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(value1, value2);
    }

    /**
     * Creates a {@link BiShortToDoubleFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ShortToDoubleFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiShortToDoubleFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ShortToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiShortToDoubleFunction onlyFirst(@Nonnull final ShortToDoubleFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsDouble(value1);
    }

    /**
     * Creates a {@link BiShortToDoubleFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ShortToDoubleFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiShortToDoubleFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ShortToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiShortToDoubleFunction onlySecond(@Nonnull final ShortToDoubleFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsDouble(value2);
    }

    /**
     * Creates a {@link BiShortToDoubleFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiShortToDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static BiShortToDoubleFunction constant(double ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    double applyAsDouble(short value1, short value2);

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
     * Returns a composed {@link BiShortToDoubleFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code BiShortToDoubleFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code short}.
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BiShortToDoubleFunction compose(@Nonnull final ShortUnaryOperator before1,
            @Nonnull final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToDoubleBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default <T, U> ToDoubleBiFunction<T, U> compose(@Nonnull final ToShortFunction<? super T> before1,
            @Nonnull final ToShortFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsDouble(before1.applyAsShort(t), before2.applyAsShort(u));
    }

    /**
     * Returns a composed {@link ShortBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ShortUnaryOperator, ShortUnaryOperator)
     * @see #compose(ToShortFunction, ToShortFunction)
     */
    @Nonnull
    default <R> ShortBiFunction<R> andThen(@Nonnull final DoubleFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiShortToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BiShortToBooleanFunction andThenToBoolean(@Nonnull final DoubleToBooleanFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsBoolean(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BiShortToByteFunction andThenToByte(@Nonnull final DoubleToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BiShortToCharFunction andThenToChar(@Nonnull final DoubleToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiShortToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BiShortToDoubleFunction andThenToDouble(@Nonnull final DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiShortToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BiShortToFloatFunction andThenToFloat(@Nonnull final DoubleToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiShortToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BiShortToIntFunction andThenToInt(@Nonnull final DoubleToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BiShortToLongFunction andThenToLong(@Nonnull final DoubleToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortBinaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default ShortBinaryOperator andThenToShort(@Nonnull final DoubleToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link ShortBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link DoubleConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ShortBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortBiConsumer consume(@Nonnull final DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link BiShortToDoubleFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method is just convenience to provide the ability
     * to use this {@code BiShortToDoubleFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code BiShortToDoubleFunction}.
     */
    @Nonnull
    default BiFunction<Short, Short, Double> boxed() {
        return this::applyAsDouble;
    }
}
