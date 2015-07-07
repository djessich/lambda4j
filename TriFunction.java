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

import at.gridtec.internals.lang.function.throwable.ThrowableFunction;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts three arguments and produces a result. This is the three-arity specialization of
 * {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @param <R> The type of return value from the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriFunction<T, U, V, R> {

    /**
     * Creates a {@link TriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code TriFunction} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U, V, R> TriFunction<T, U, V, R> constant(R r) {
        Objects.requireNonNull(r);
        return (t, u, v) -> r;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return he return value from the function, which is its result.
     */
    R apply(T t, U u, V v);

    /**
     * Returns a composed {@link TriFunction} that applies the given {@code before} {@link Function}s to its input, and
     * then applies this function to the result. If evaluation of either of the given functions throws an exception, it
     * is relayed to the caller of the composed function. To workaround this, provide a throwing counterpart of the
     * arguments.
     *
     * @param <A> The type of the argument to the first before function
     * @param <B> The type of the argument to the second before function
     * @param <C> The type of the argument to the third before function
     * @param before1 The first before {@code Function}
     * @param before2 The second before {@code Function}
     * @param before3 The third before {@code Function}
     * @return A composed {@code TriFunction} that applies the given {@code before} {@code Function}s to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see ThrowableFunction
     */
    default <A, B, C> TriFunction<A, B, C, R> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> apply(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies this function to its input, and then applies the
     * {@code after} {@link Function} to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. To workaround this, provide a throwing counterpart of the
     * argument.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFunction} that first applies this function and then applies the {@code after}
     * function.
     * @throws NullPointerException If after function is {@code null}
     * @see ThrowableFunction
     */
    default <S> TriFunction<T, U, V, S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(apply(t, u, v));
    }
}
