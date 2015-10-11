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

package at.gridtec.lambda4j.consumer.primitives.tri;

import at.gridtec.lambda4j.consumer.TriConsumer;
import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import java.util.Objects;

/**
 * Represents an operation that accepts three {@code short}-valued arguments and returns no result. This is the
 * primitive type specialization of {@link TriConsumer} for {@code short}. Unlike most other functional interfaces,
 * {@code ShortTriConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(short, short, short)}.
 *
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortTriConsumer {

    /**
     * Creates a {@link ShortTriConsumer} which uses the first parameter as argument for the given {@link
     * ShortConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ShortTriConsumer} which uses the first parameter as argument for the given {@code
     * ShortConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortTriConsumer onlyFirst(final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link ShortTriConsumer} which uses the second parameter as argument for the given {@link
     * ShortConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ShortTriConsumer} which uses the second parameter as argument for the given {@code
     * ShortConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortTriConsumer onlySecond(final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value2);
    }

    /**
     * Creates a {@link ShortTriConsumer} which uses the third parameter as argument for the given {@link
     * ShortConsumer}.
     *
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code ShortTriConsumer} which uses the third parameter as argument for the given {@code
     * ShortConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortTriConsumer onlyThird(final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value3);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument for the operation to be consumed
     * @param value2 The second argument for the operation to be consumed
     * @param value3 The third argument for the operation to be consumed
     */
    void accept(short value1, short value2, short value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ShortTriConsumer} that applies the given {@code before} {@link ShortUnaryOperator}s to
     * its input, and then applies this operation to the result. If evaluation of either of the given operations throws
     * an exception, it is relayed to the caller of the composed function.
     *
     * @param before1 The first before {@code ShortUnaryOperator} to apply before this operation is applied
     * @param before2 The second before {@code ShortUnaryOperator} to apply before this operation is applied
     * @param before3 The third before {@code ShortUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ShortTriConsumer} that applies the given {@code before} {@code ShortUnaryOperator}s to
     * its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ShortTriConsumer)
     */
    default ShortTriConsumer compose(final ShortUnaryOperator before1, final ShortUnaryOperator before2,
            final ShortUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsShort(value1), before2.applyAsShort(value2),
                                                  before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriConsumer} that applies the given {@code before} {@link ToShortFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first before {@code ToShortFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToShortFunction} to apply before this operation is applied
     * @param before3 The third before {@code ToShortFunction} to apply before this operation is applied
     * @return A composed {@code TriConsumer} that applies the given {@code before} {@code ToShortFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ShortTriConsumer)
     */
    default <T, U, V> TriConsumer<T, U, V> compose(final ToShortFunction<? super T> before1,
            final ToShortFunction<? super U> before2, final ToShortFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsShort(value1), before2.applyAsShort(value2),
                                                  before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link ShortTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link ShortTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(ShortUnaryOperator, ShortUnaryOperator, ShortUnaryOperator)
     * @see #compose(ToShortFunction, ToShortFunction, ToShortFunction)
     */
    default ShortTriConsumer andThen(final ShortTriConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> {
            accept(value1, value2, value3);
            after.accept(value1, value2, value3);
        };
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link ShortTriConsumer}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriConsumer} which represents this {@code ShortTriConsumer}.
     */
    default TriConsumer<Short, Short, Short> boxed() {
        return this::accept;
    }
}
