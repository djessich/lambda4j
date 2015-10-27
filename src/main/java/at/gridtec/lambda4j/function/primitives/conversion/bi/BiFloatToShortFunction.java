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
import at.gridtec.lambda4j.consumer.primitives.bi.FloatBiConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.bi.FloatBiFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToShortBiFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two float-valued arguments and produces a short-valued result. This is the {@code
 * float}-to-{@code short} primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(float, float)}.
 *
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiFloatToShortFunction {

    /**
     * Calls the given {@link BiFloatToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiFloatToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static short call(@Nonnull final BiFloatToShortFunction function, float value1, float value2) {
        Objects.requireNonNull(function);
        return function.applyAsShort(value1, value2);
    }

    /**
     * Creates a {@link BiFloatToShortFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link FloatToShortFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiFloatToShortFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code FloatToShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiFloatToShortFunction onlyFirst(@Nonnull final FloatToShortFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsShort(value1);
    }

    /**
     * Creates a {@link BiFloatToShortFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link FloatToShortFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiFloatToShortFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code FloatToShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiFloatToShortFunction onlySecond(@Nonnull final FloatToShortFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsShort(value2);
    }

    /**
     * Creates a {@link BiFloatToShortFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiFloatToShortFunction} which always returns a given value.
     */
    @Nonnull
    static BiFloatToShortFunction constant(short ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    short applyAsShort(float value1, float value2);

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
     * Returns a composed {@link BiFloatToShortFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code BiFloatToShortFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code float}.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default BiFloatToShortFunction compose(@Nonnull final FloatUnaryOperator before1,
            @Nonnull final FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShort(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
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
    default <T, U> ToShortBiFunction<T, U> compose(@Nonnull final ToFloatFunction<? super T> before1,
            @Nonnull final ToFloatFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsShort(before1.applyAsFloat(t), before2.applyAsFloat(u));
    }

    /**
     * Returns a composed {@link FloatBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(FloatUnaryOperator, FloatUnaryOperator)
     * @see #compose(ToFloatFunction, ToFloatFunction)
     */
    @Nonnull
    default <R> FloatBiFunction<R> andThen(@Nonnull final ShortFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link FloatBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ShortConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code FloatBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatBiConsumer consume(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsShort(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link BiFloatToShortFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BiFloatToShortFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code BiFloatToShortFunction}.
     */
    @Nonnull
    default BiFunction<Float, Float, Short> boxed() {
        return this::applyAsShort;
    }
}
