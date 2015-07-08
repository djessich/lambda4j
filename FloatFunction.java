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
import java.util.function.Function;

/**
 * Represents a function that accepts a float-valued argument and produces a result. This is the {@code
 * float}-consuming primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(float)}.
 *
 * @param <R> The type of return value from the function
 * @see Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatFunction<R> {

    /**
     * Creates a {@link FloatFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code FloatFunction} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> FloatFunction<R> constant(R r) {
        Objects.requireNonNull(r);
        return (t) -> r;
    }

    /**
     * Applies this {@link FloatFunction} to the given argument.
     *
     * @param value The argument for the function
     * @return The return value from the function, which is its result.
     */
    R apply(float value);

    /**
     * Returns a composed {@link Function} that applies the given {@code before} {@link ToFloatFunction} to its input,
     * and then applies this function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <T> The type of argument for the function
     * @param before The {@code ToFloatFunction} to apply before this function is applied
     * @return A composed {@code Function} that applies the given {@code before} {@code ToFloatFunction} to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given before function is {@code null}
     * @see #andThen(Function)
     */
    default <T> Function<T, R> compose(final ToFloatFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> apply(before.applyAsFloat(t));
    }

    /**
     * Returns a composed {@link FloatFunction} that first applies this function to its input, and then applies the
     * {@code after} {@link Function} to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code Function} to apply after this function is applied
     * @return A composed {@code FloatFunction} that first applies this function and then applies the {@code after}
     * function.
     * @throws NullPointerException If after function is {@code null}
     * @see #compose(ToFloatFunction)
     */
    default <S> FloatFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(apply(value));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link FloatFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code FloatFunction}.
     */
    default Function<Float, R> boxed() {
        return this::apply;
    }
}
