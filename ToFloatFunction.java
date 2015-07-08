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
 * Represents a function that produces a float-valued result from one argument. This is the {@code float}-producing
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(Object)}.
 *
 * @param <T> The type of argument for the function
 * @see Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToFloatFunction<T> {

    /**
     * Creates a {@link ToFloatFunction} which always returns a given value.
     *
     * @param <T> The type of argument for the function
     * @param ret The return value for the constant
     * @return A {@code ToFloatFunction} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ToFloatFunction<T> constant(float ret) {
        Objects.requireNonNull(ret);
        return t -> ret;
    }

    /**
     * Applies this {@link ToFloatFunction} to the given argument.
     *
     * @param t The argument for the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(T t);

    /**
     * Returns a composed {@link ToFloatFunction} that applies the given {@code before} {@link Function} to its input,
     * and then applies this function to the result. If evaluation of either of the given functions throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <V> The type of argument for the function
     * @param before The {@code Function} to apply before this function is applied
     * @return A composed {@code ToFloatFunction} that applies the given {@code before} {@code Function} to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given before function is {@code null}
     * @see #andThen(FloatFunction)
     */
    default <V> ToFloatFunction<V> compose(final Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return v -> this.applyAsFloat(before.apply(v));
    }

    /**
     * Returns a composed {@link Function} that first applies this {@link ToFloatFunction} to its input, and then
     * applies the {@code after} {@link FloatFunction} to the result. If evaluation of either function throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <R> The type of return value from the function
     * @param after The {@code FloatFunction} to apply after this function is applied
     * @return A composed {@code Function} that first applies this function and then applies the {@code after} function.
     * @throws NullPointerException If after function is {@code null}
     * @see #compose(Function)
     */
    default <R> Function<T, R> andThen(final FloatFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return t -> after.apply(applyAsFloat(t));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link ToFloatFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code ToFloatFunction}.
     */
    default Function<T, Float> boxed() {
        return this::applyAsFloat;
    }
}
