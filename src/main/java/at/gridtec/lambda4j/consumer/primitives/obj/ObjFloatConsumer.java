/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.gridtec.lambda4j.consumer.primitives.obj;

import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Represents an operation that accepts an object-valued and a {@code float}-valued argument, and returns no result.
 * This is the {@code (reference, float)} specialization of {@link BiConsumer}. Unlike most other functional interfaces,
 * {@code ObjFloatConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, float)}.
 *
 * @param <T> The type of argument to the operation
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjFloatConsumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t The first argument to the operation
     * @param value The second argument to the operation
     */
    void accept(T t, float value);

    /**
     * Returns a composed {@link ObjFloatConsumer} that applies the given {@code before} {@link Function} and {@link
     * FloatUnaryOperator} to its input, and then applies this operation to the result. If evaluation of either of the
     * given operations throws an exception, it is relayed to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The {@code Function} to apply before this operation is applied
     * @param before2 The {@code FloatUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ObjFloatConsumer} that applies the given {@code before} {@code Function} and {@code
     * FloatUnaryOperator} to its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ObjFloatConsumer)
     */
    default <U> ObjFloatConsumer<U> compose(final Function<? super U, ? extends T> before1,
            final FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> accept(before1.apply(u), before2.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link BiConsumer} that applies the given {@code before} {@link Function} and {@link
     * ToFloatFunction} to its input, and then applies this operation to the result. If evaluation of either of the
     * given operations throws an exception, it is relayed to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The {@code Function} to apply after this operation is applied
     * @param before2 The {@code ToFloatFunction} to apply after this operation is applied
     * @return A composed {@code BiConsumer} that applies the given {@code before} {@code Function} and {@code
     * ToFloatFunction} to its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ObjFloatConsumer)
     */
    default <U, V> BiConsumer<U, V> compose(final Function<? super U, ? extends T> before1,
            final ToFloatFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> accept(before1.apply(u), before2.applyAsFloat(v));
    }

    /**
     * Returns a composed {@link ObjFloatConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link ObjFloatConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(Function, FloatUnaryOperator)
     * @see #compose(Function, ToFloatFunction)
     */
    default ObjFloatConsumer<T> andThen(final ObjFloatConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (t, value) -> {
            accept(t, value);
            after.accept(t, value);
        };
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link ObjFloatConsumer}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjFloatConsumer} with JRE specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code ObjFloatConsumer}.
     */
    default BiConsumer<T, Float> boxed() {
        return this::accept;
    }
}
