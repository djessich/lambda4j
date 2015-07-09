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
import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two {@code float}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link BiConsumer} for {@code float}. Unlike most other functional interfaces, {@code
 * FloatBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(float, float)}.
 *
 * @see BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatBiConsumer {

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument for the function to be consumed
     * @param value2 The second argument for the function to be consumed
     */
    void accept(float value1, float value2);

    /**
     * Returns a composed {@link BiConsumer} that applies the given {@code before} {@link ToFloatFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument for the first before function, and of composed function
     * @param <U> The type of the argument to the second before function, and of composed function
     * @param before1 The first before {@code ToFloatFunction} to apply before this operation is applied
     * @param before1 The second before {@code ToFloatFunction} to apply before this operation is applied
     * @return A composed {@code BiConsumer} that applies the given {@code before} {@code ToFloatFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(FloatBiConsumer)
     */
    default <T, U> BiConsumer<T, U> compose(final ToFloatFunction<? super T> before1,
            final ToFloatFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> accept(before1.applyAsFloat(t), before2.applyAsFloat(u));
    }

    /**
     * Returns a composed {@code FloatBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If performing either operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The {@code FloatConsumer} to apply after this one is applied
     * @return A composed {@code FloatConsumer} that performs in sequence, this operation followed by the {@code after}
     * operation
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(ToFloatFunction, ToFloatFunction)
     */
    default FloatBiConsumer andThen(FloatBiConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> {
            accept(value1, value2);
            after.accept(value1, value2);
        };
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link FloatBiConsumer}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatBiConsumer} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code FloatBiConsumer}.
     */
    default BiConsumer<Float, Float> boxed() {
        return this::accept;
    }
}
