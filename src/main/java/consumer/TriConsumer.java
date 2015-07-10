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

package at.gridtec.internals.lang.function.consumer;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents an operation that accepts three input arguments and returns no result.  This is the three-arity
 * specialization of {@link Consumer}. Unlike most other functional interfaces, {@code TriConsumer} is expected to
 * operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the operation
 * @param <U> The type of the second argument to the operation
 * @param <V> The type of the third argument to the operation
 * @see java.util.function.Consumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriConsumer<T, U, V> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t The first argument for the operation to be consumed
     * @param u The second argument for the operation to be consumed
     * @param v The third argument for the operation to be consumed
     */
    void accept(T t, U u, V v);

    /**
     * Returns a composed {@link TriConsumer} that applies the given {@code before} {@link Function}s to its input, and
     * then applies this operation to the result. If evaluation of either of the given operations throws an exception,
     * it is relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first before {@code Function} to apply after this operation is applied
     * @param before2 The second before {@code Function} to apply after this operation is applied
     * @param before3 The third before {@code Function} to apply after this operation is applied
     * @return A composed {@code TriConsumer} that applies the given {@code before} {@code Function}s to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #accept(Object, Object, Object)
     */
    default <A, B, C> TriConsumer<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> accept(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link TriConsumer} that first applies this operation to its input, and then applies the
     * {@code after} {@link TriConsumer} to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code TriFunction} that first applies this operation and then applies the {@code after}
     * function.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(Function, Function, Function)
     */
    default TriConsumer<T, U, V> andThen(final TriConsumer<? super T, ? super U, ? super V> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> {
            accept(t, u, v);
            after.accept(t, u, v);
        };
    }
}
