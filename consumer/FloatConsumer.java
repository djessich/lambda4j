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
import java.util.function.Consumer;

/**
 * Represents an operation that accepts a single {@code float}-valued argument and returns no result. This is the
 * primitive type specialization of {@link Consumer} for {@code float}. Unlike most other functional interfaces,
 * {@code FloatConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(float)}.
 *
 * @see Consumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value The argument for the operation to be consumed
     */
    void accept(float value);

    /**
     * Returns a composed {@link Consumer} that applies the given {@code before} {@link ToFloatFunction} to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param <T> The type of argument for the operation, and of composed operation
     * @param before The {@code ToFloatFunction} to apply before this operation is applied
     * @return A composed {@code Consumer} that applies the given {@code before} {@code ToFloatFunction} to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If given before function is {@code null}
     * @see #andThen(FloatConsumer)
     */
    default <T> Consumer<T> compose(final ToFloatFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> accept(before.applyAsFloat(t));
    }

    /**
     * Returns a composed {@code FloatConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If performing either operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The {@code FloatConsumer} to apply after this one is applied
     * @return A composed {@code FloatConsumer} that performs in sequence, this operation followed by the {@code after}
     * operation
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(ToFloatFunction)
     */
    default FloatConsumer andThen(final FloatConsumer after) {
        Objects.requireNonNull(after);
        return value -> {
            accept(value);
            after.accept(value);
        };
    }

    /**
     * Returns a composed {@link Consumer} which represents this {@link FloatConsumer}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatConsumer} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Consumer} which represents this {@code FloatConsumer}.
     */
    default Consumer<Float> boxed() {
        return this::accept;
    }
}
