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

import at.gridtec.internals.lang.function.ToFloatFunction;

import java.util.Objects;

/**
 * Represents an operation that accepts three {@code float}-valued arguments and returns no result. This is the
 * primitive type specialization of {@link TriConsumer} for {@code float}. Unlike most other functional interfaces,
 * {@code
 * FloatBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(float, float, float)}.
 *
 * @see at.gridtec.internals.lang.function.consumer.TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatTriConsumer {

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument for the operation to be consumed
     * @param value2 The second argument for the operation to be consumed
     * @param value3 The second argument for the operation to be consumed
     */
    void accept(float value1, float value2, float value3);

    /**
     * Returns a composed {@link TriConsumer} that applies the given {@code before} {@link ToFloatFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed operation.
     *
     * @param <T> The type of the argument for the first before function, and of composed operation
     * @param <U> The type of the argument to the second before function, and of composed operation
     * @param <V> The type of the argument to the third before function, and of composed operation
     * @param before1 The first before {@code ToFloatFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToFloatFunction} to apply before this operation is applied
     * @param before3 The third before {@code ToFloatFunction} to apply before this operation is applied
     * @return A composed {@code TriConsumer} that applies the given {@code before} {@code ToFloatFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(FloatTriConsumer)
     */
    default <T, U, V> TriConsumer<T, U, V> compose(final ToFloatFunction<? super T> before1,
            final ToFloatFunction<? super U> before2, final ToFloatFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> accept(before1.applyAsFloat(t), before2.applyAsFloat(u), before3.applyAsFloat(v));
    }

    /**
     * Returns a composed {@code FloatTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If performing either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The {@code FloatTriConsumer} to apply after this one is applied
     * @return A composed {@code FloatTriConsumer} that performs in sequence, this operation followed by the {@code
     * after} operation
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(ToFloatFunction, ToFloatFunction, ToFloatFunction)
     */
    default FloatTriConsumer andThen(final FloatTriConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> {
            accept(value1, value2, value3);
            after.accept(value1, value2, value3);
        };
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link FloatTriConsumer}. Thereby the primitive
     * input argument for this operation is autoboxed.
     *
     * @return A composed {@code TriConsumer} which represents this {@code FloatTriConsumer}.
     */
    default TriConsumer<Float, Float, Float> boxed() {
        return this::accept;
    }
}
