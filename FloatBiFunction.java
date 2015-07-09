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

package at.gridtec.internals.lang.function;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Represents a function that accepts two float-valued arguments and produces a result. This is the {@code
 * float}-consuming primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(float, float)}.
 *
 * @param <R> The type of return value from the function
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatBiFunction<R> {

    /**
     * Creates a {@link FloatBiFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code FloatBiFunction} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> FloatBiFunction<R> constant(R r) {
        Objects.requireNonNull(r);
        return (t, u) -> r;
    }

    /**
     * Applies this {@link FloatBiFunction} to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(float value1, float value2);

    /**
     * Returns a composed {@link BiFunction} that applies the given {@code before} {@link ToFloatFunction}s to its
     * input, and then applies this function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param before1 The first before {@code ToFloatFunction} to apply before this function is applied
     * @param before2 The second before {@code ToFloatFunction} to apply before this function is applied
     * @return A composed {@code BiFunction} that applies the given {@code before} {@code ToFloatFunction}s to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T, U> BiFunction<T, U, R> compose(final ToFloatFunction<? super T> before1,
            final ToFloatFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> apply(before1.applyAsFloat(t), before2.applyAsFloat(u));
    }

    /**
     * Returns a composed {@link FloatBiFunction} that first applies this function to its input, and then applies the
     * {@code after} {@link Function} to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code Function} to apply after this function is applied
     * @return A composed {@code FloatFunction} that first applies this function and then applies the {@code after}
     * function.
     * @throws NullPointerException If given after function is {@code null}
     * @see #compose(ToFloatFunction, ToFloatFunction)
     */
    default <S> FloatBiFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link FloatBiFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatBiFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code FloatBiFunction}.
     */
    default BiFunction<Float, Float, R> boxed() {
        return this::apply;
    }
}
