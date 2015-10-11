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
package at.gridtec.lambda4j.function.primitives.bi;

import at.gridtec.lambda4j.consumer.primitives.bi.ShortBiConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.binary.ShortBinaryOperator;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a function that accepts two short-valued arguments and produces a result. This is the {@code
 * short}-consuming primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(short, short)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortBiFunction<R> {

    /**
     * Creates a {@link ShortBiFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ShortBiFunction} which always returns a given value.
     */
    static <R> ShortBiFunction<R> constant(R r) {
        return (value1, value2) -> r;
    }

    /**
     * Creates a {@link ShortBiFunction} which uses the first parameter of this one as argument for the given {@link
     * ShortFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ShortBiFunction} which uses the first parameter of this one as argument for the given
     * {@code ShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> ShortBiFunction<R> onlyFirst(final ShortFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value1);
    }

    /**
     * Creates a {@link ShortBiFunction} which uses the second parameter of this one as argument for the given {@link
     * ShortFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ShortBiFunction} which uses the second parameter of this one as argument for the given
     * {@code ShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> ShortBiFunction<R> onlySecond(final ShortFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value2);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(short value1, short value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ShortBiFunction} that first applies the {@code before} {@link ShortUnaryOperator}s to
     * its input, and then applies this operation to the result. If evaluation of either operation throws an exception,
     * it is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code ShortUnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code ShortUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ShortBiFunction} that first applies the {@code before} {@code ShortUnaryOperator}s to
     * its input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default ShortBiFunction<R> compose(final ShortUnaryOperator before1, final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiFunction} that applies the given {@code before} {@link ToShortFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first before {@code ToShortFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToShortFunction} to apply before this operation is applied
     * @return A composed {@code BiFunction} that applies the given {@code before} {@code ToShortFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T, U> BiFunction<T, U, R> compose(final ToShortFunction<? super T> before1,
            final ToShortFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToShortFunction} to apply after this operation is applied
     * @return A composed {@code ShortBinaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ShortUnaryOperator, ShortUnaryOperator)
     * @see #compose(ToShortFunction, ToShortFunction)
     */
    default ShortBinaryOperator andThen(final ToShortFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(apply(value1, value2));
    }

    /**
     * Returns a composed {@link ShortBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code ShortBiFunction} to apply after this operation is applied
     * @return A composed {@code ShortBiFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ShortUnaryOperator, ShortUnaryOperator)
     * @see #compose(ToShortFunction, ToShortFunction)
     */
    default <S> ShortBiFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(apply(value1, value2));
    }

    /**
     * Returns a composed {@link ShortBiConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code ShortBiConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ShortBiConsumer consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(this.apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ShortBiFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortBiFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ShortBiFunction}.
     */
    default BiFunction<Short, Short, R> boxed() {
        return this::apply;
    }
}
