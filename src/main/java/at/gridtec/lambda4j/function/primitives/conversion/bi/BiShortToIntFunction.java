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
import at.gridtec.lambda4j.function.primitives.conversion.ShortToIntFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two short-valued arguments and produces a int-valued result. This is the {@code
 * short}-to-{@code int} primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(short, short)}.
 *
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiShortToIntFunction {

    /**
     * Calls the given {@link BiShortToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiShortToIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static int call(@Nonnull final BiShortToIntFunction function, short value1, short value2) {
        Objects.requireNonNull(function);
        return function.applyAsInt(value1, value2);
    }

    /**
     * Creates a {@link BiShortToIntFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ShortToIntFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiShortToIntFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code ShortToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiShortToIntFunction onlyFirst(@Nonnull final ShortToIntFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsInt(value1);
    }

    /**
     * Creates a {@link BiShortToIntFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ShortToIntFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiShortToIntFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ShortToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiShortToIntFunction onlySecond(@Nonnull final ShortToIntFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsInt(value2);
    }

    /**
     * Creates a {@link BiShortToIntFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiShortToIntFunction} which always returns a given value.
     */
    @Nonnull
    static BiShortToIntFunction constant(int ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    int applyAsInt(short value1, short value2);

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
     * Returns a composed {@link BiShortToIntFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code BiShortToIntFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code short}.
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default BiShortToIntFunction compose(@Nonnull final ShortUnaryOperator before1,
            @Nonnull final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link ToIntBiFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToIntBiFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(IntFunction)
     */
    @Nonnull
    default <T, U> ToIntBiFunction<T, U> compose(@Nonnull final ToShortFunction<? super T> before1,
            @Nonnull final ToShortFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsInt(before1.applyAsShort(t), before2.applyAsShort(u));
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
    default <R> ShortBiFunction<R> andThen(@Nonnull final IntFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link ShortBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ShortBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortBiConsumer consume(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link BiShortToIntFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BiShortToIntFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code BiShortToIntFunction}.
     */
    @Nonnull
    default BiFunction<Short, Short, Integer> boxed() {
        return this::applyAsInt;
    }
}
