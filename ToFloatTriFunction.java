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
 * Represents a function that produces a float-valued result from three arguments. This is the {@code float}-producing
 * primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToFloatTriFunction<T, U, V> {

    /**
     * Creates a {@link ToFloatTriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToFloatFunction} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U, V> ToFloatTriFunction<T, U, V> constant(float ret) {
        Objects.requireNonNull(ret);
        return (t, u, v) -> ret;
    }

    /**
     * Applies this {@link ToFloatTriFunction} to the given argument.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(T t, U u, V v);

    /**
     * Returns a composed {@link ToFloatTriFunction} that applies the given {@code before} {@link Function}s to its
     * input, and then applies this function to the result. If evaluation of either of the given functions throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before function
     * @param <B> The type of the argument to the second before function
     * @param <C> The type of the argument to the second before function
     * @param before1 The first before {@code Function} to apply before this function is applied
     * @param before2 The second before {@code Function} to apply before this function is applied
     * @param before3 The third before {@code Function} to apply before this function is applied
     * @return A composed {@code ToFloatTriFunction} that applies the given {@code before} {@code Function}s to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(FloatFunction)
     */
    default <A, B, C> ToFloatTriFunction<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> this.applyAsFloat(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies this {@link ToFloatTriFunction} to its input, and then
     * applies the {@code after} {@link FloatFunction} to the result. If evaluation of either function throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <R> The type of return value from the function
     * @param after The {@code FloatFunction} to apply after this function is applied
     * @return A composed {@code TriFunction} that first applies this function and then applies the {@code after}
     * function.
     * @throws NullPointerException If given after function is {@code null}
     * @see #compose(Function, Function, Function)
     */
    default <R> TriFunction<T, U, V, R> andThen(final FloatFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(applyAsFloat(t, u, v));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ToFloatTriFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ToFloatTriFunction}.
     */
    default TriFunction<T, U, V, Float> boxed() {
        return this::applyAsFloat;
    }
}
