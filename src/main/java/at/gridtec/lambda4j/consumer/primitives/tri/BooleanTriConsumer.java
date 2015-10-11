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
import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents an operation that accepts three {@code boolean}-valued arguments and returns no result. This is the
 * primitive type specialization of {@link TriConsumer} for {@code boolean}. Unlike most other functional interfaces,
 * {@code BooleanTriConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(boolean, boolean, boolean)}.
 *
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanTriConsumer {

    /**
     * Creates a {@link BooleanTriConsumer} which uses the {@code first} parameter as argument for the given {@link
     * BooleanConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code BooleanTriConsumer} which uses the {@code first} parameter as argument for the given
     * {@code BooleanConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static BooleanTriConsumer onlyFirst(final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link BooleanTriConsumer} which uses the {@code second} parameter as argument for the given {@link
     * BooleanConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code BooleanTriConsumer} which uses the {@code second} parameter as argument for the given
     * {@code BooleanConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static BooleanTriConsumer onlySecond(final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value2);
    }

    /**
     * Creates a {@link BooleanTriConsumer} which uses the {@code third} parameter as argument for the given {@link
     * BooleanConsumer}.
     *
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code BooleanTriConsumer} which uses the {@code third} parameter as argument for the given
     * {@code BooleanConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static BooleanTriConsumer onlyThird(final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value3);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     */
    void accept(boolean value1, boolean value2, boolean value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link BooleanTriConsumer} that applies the given {@code before} {@link BooleanUnaryOperator}s
     * to its input, and then applies this operation to the result. If evaluation of either of the given operations
     * throws an exception, it is relayed to the caller of the composed function.
     *
     * @param before1 The first before {@code BooleanUnaryOperator} to apply before this operation is applied
     * @param before2 The second before {@code BooleanUnaryOperator} to apply before this operation is applied
     * @param before3 The third before {@code BooleanUnaryOperator} to apply before this operation is applied
     * @return A composed {@code BooleanTriConsumer} that applies the given {@code before} {@code BooleanUnaryOperator}s
     * to its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(BooleanTriConsumer)
     */
    default BooleanTriConsumer compose(final BooleanUnaryOperator before1, final BooleanUnaryOperator before2,
            final BooleanUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsBoolean(value1), before2.applyAsBoolean(value2),
                                                  before3.applyAsBoolean(value3));
    }

    /**
     * Returns a composed {@link TriConsumer} that applies the given {@code before} {@link Predicate}s to its input, and
     * then applies this operation to the result. If evaluation of either of the given operations throws an exception,
     * it is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first before {@code Predicate} to apply before this operation is applied
     * @param before2 The second before {@code Predicate} to apply before this operation is applied
     * @param before3 The third before {@code Predicate} to apply before this operation is applied
     * @return A composed {@code TriConsumer} that applies the given {@code before} {@code Predicate}s to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(BooleanTriConsumer)
     */
    default <T, U, V> TriConsumer<T, U, V> compose(final Predicate<? super T> before1,
            final Predicate<? super U> before2, final Predicate<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link BooleanTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link BooleanTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(BooleanUnaryOperator, BooleanUnaryOperator, BooleanUnaryOperator)
     * @see #compose(Predicate, Predicate, Predicate)
     */
    default BooleanTriConsumer andThen(final BooleanTriConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> {
            accept(value1, value2, value3);
            after.accept(value1, value2, value3);
        };
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link BooleanTriConsumer}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriConsumer} which represents this {@code BooleanTriConsumer}.
     */
    default TriConsumer<Boolean, Boolean, Boolean> boxed() {
        return this::accept;
    }
}
