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
 * Represents a function that produces a float-valued result from two arguments. This is the {@code float}-producing
 * primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToFloatBiFunction<T, U> {

    /**
     * Creates a {@link ToFloatBiFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToFloatFunction} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToFloatBiFunction<T, U> constant(float ret) {
        Objects.requireNonNull(ret);
        return (t, u) -> ret;
    }

    /**
     * Applies this {@link ToFloatBiFunction} to the given argument.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(T t, U u);

    /**
     * Returns a composed {@link ToFloatBiFunction} that applies the given {@code before} {@link Function}s to its
     * input, and then applies this function to the result. If evaluation of either of the given functions throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before function
     * @param <B> The type of the argument to the second before function
     * @param before1 The first before {@code Function} to apply before this function is applied
     * @param before2 The second before {@code Function} to apply before this function is applied
     * @return A composed {@code ToFloatBiFunction} that applies the given {@code before} {@code Function}s to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(FloatFunction)
     */
    default <A, B> ToFloatBiFunction<A, B> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> this.applyAsFloat(before1.apply(a), before2.apply(b));
    }

    /**
     * Returns a composed {@link BiFunction} that first applies this {@link ToFloatBiFunction} to its input, and then
     * applies the {@code after} {@link FloatFunction} to the result. If evaluation of either function throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <R> The type of return value from the function
     * @param after The {@code FloatFunction} to apply after this function is applied
     * @return A composed {@code BiFunction} that first applies this function and then applies the {@code after}
     * function.
     * @throws NullPointerException If given after function is {@code null}
     * @see #compose(Function, Function)
     */
    default <R> BiFunction<T, U, R> andThen(final FloatFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.apply(applyAsFloat(t, u));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ToFloatBiFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use
     * this {@code ToFloatBiFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ToFloatBiFunction}.
     */
    default BiFunction<T, U, Float> boxed() {
        return this::applyAsFloat;
    }
}
