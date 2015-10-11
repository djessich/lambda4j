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

import at.gridtec.lambda4j.consumer.TriConsumer;
import at.gridtec.lambda4j.consumer.primitives.FloatConsumer;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents an operation that accepts two object-valued and a {@code float}-valued argument, and returns no result.
 * This is the {@code (reference, reference, float)} specialization of {@link TriConsumer}. Unlike most other functional
 * interfaces, {@code BiObjFloatConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, Object, float)}.
 *
 * @param <T> The type of the first argument to the operation
 * @param <U> The type of the second argument to the operation
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjFloatConsumer<T, U> {

    /**
     * Creates a {@link BiObjFloatConsumer} which uses the {@code first} parameter of this one as argument for the given {@link
     * Consumer}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjFloatConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> BiObjFloatConsumer<T, U> onlyFirst(final Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(t);
    }

    /**
     * Creates a {@link BiObjFloatConsumer} which uses the {@code second} parameter of this one as argument for the given {@link
     * Consumer}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjFloatConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> BiObjFloatConsumer<T, U> onlySecond(final Consumer<? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(u);
    }

    /**
     * Creates a {@link BiObjFloatConsumer} which uses the {@code third} parameter of this one as argument for the given {@link
     * FloatConsumer}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjFloatConsumer} which uses the {@code third} parameter of this one as argument for the given
     * {@code FloatConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> BiObjFloatConsumer<T, U> onlyThird(final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(value);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     */
    void accept(T t, U u, float value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link BiObjFloatConsumer} that applies the given {@code before} {@link Function}s and {@link
     * FloatUnaryOperator} to its input, and then applies this operation to the result. If evaluation of either of the
     * given operations throws an exception, it is relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code Function} to apply before this operation is applied
     * @param before3 The {@code FloatUnaryOperator} to apply before this operation is applied
     * @return A composed {@code BiObjFloatConsumer} that applies the given {@code before} {@code Function}s and {@code
     * FloatUnaryOperator} to its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(BiObjFloatConsumer)
     */
    default <A, B> BiObjFloatConsumer<A, B> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final FloatUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> accept(before1.apply(a), before2.apply(b), before3.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link TriConsumer} that applies the given {@code before} {@link Function}s and {@link
     * ToFloatFunction} to its input, and then applies this operation to the result. If evaluation of either of the
     * given operations throws an exception, it is relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code Function} to apply before this operation is applied
     * @param before3 The {@code ToFloatFunction} to apply before this operation is applied
     * @return A composed {@code BiConsumer} that applies the given {@code before} {@code Function}s and {@code
     * ToFloatFunction} to its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(BiObjFloatConsumer)
     */
    default <A, B, C> TriConsumer<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final ToFloatFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> accept(before1.apply(a), before2.apply(b), before3.applyAsFloat(c));
    }

    /**
     * Returns a composed {@link BiObjFloatConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link BiObjFloatConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(Function, Function, FloatUnaryOperator)
     * @see #compose(Function, Function, ToFloatFunction)
     */
    default BiObjFloatConsumer<T, U> andThen(final BiObjFloatConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> {
            accept(t, u, value);
            after.accept(t, u, value);
        };
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link BiObjFloatConsumer}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriConsumer} which represents this {@code BiObjFloatConsumer}.
     */
    default TriConsumer<T, U, Float> boxed() {
        return this::accept;
    }
}
