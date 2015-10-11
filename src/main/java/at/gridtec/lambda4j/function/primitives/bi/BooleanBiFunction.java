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

import at.gridtec.lambda4j.consumer.primitives.bi.BooleanBiConsumer;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.operators.binary.BooleanBinaryOperator;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents a function that accepts two boolean-valued arguments and produces a result. This is the {@code
 * boolean}-consuming primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(boolean, boolean)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanBiFunction<R> {

    /**
     * Creates a {@link BooleanBiFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code BooleanBiFunction} which always returns a given value.
     */
    static <R> BooleanBiFunction<R> constant(R r) {
        return (value1, value2) -> r;
    }

    /**
     * Creates a {@link BooleanBiFunction} which uses the {@code first} parameter of this one as argument for the given {@link
     * BooleanFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BooleanBiFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@code BooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> BooleanBiFunction<R> onlyFirst(final BooleanFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value1);
    }

    /**
     * Creates a {@link BooleanBiFunction} which uses the {@code second} parameter of this one as argument for the given {@link
     * BooleanFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BooleanBiFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@code BooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> BooleanBiFunction<R> onlySecond(final BooleanFunction<? extends R> function) {
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
    R apply(boolean value1, boolean value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BooleanBiFunction} that first applies the {@code before} {@link BooleanUnaryOperator}s
     * to its input, and then applies this operation to the result. If evaluation of either operation throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code BooleanUnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code BooleanUnaryOperator} to apply before this operation is applied
     * @return A composed {@code BooleanBiFunction} that first applies the {@code before} {@code BooleanUnaryOperator}s
     * to its input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default BooleanBiFunction<R> compose(final BooleanUnaryOperator before1, final BooleanUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsBoolean(value1), before2.applyAsBoolean(value2));
    }

    /**
     * Returns a composed {@link BiFunction} that applies the given {@code before} {@link Predicate}s to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first before {@code Predicate} to apply before this operation is applied
     * @param before2 The second before {@code Predicate} to apply before this operation is applied
     * @return A composed {@code BiFunction} that applies the given {@code before} {@code Predicate}s to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T, U> BiFunction<T, U, R> compose(final Predicate<? super T> before1, final Predicate<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToBooleanFunction} to apply after this operation is applied
     * @return A composed {@code BooleanBinaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(BooleanUnaryOperator, BooleanUnaryOperator)
     * @see #compose(Predicate, Predicate)
     */
    default BooleanBinaryOperator andThen(final Predicate<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.test(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BooleanBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code BooleanBiFunction} to apply after this operation is applied
     * @return A composed {@code BooleanBiFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(BooleanUnaryOperator, BooleanUnaryOperator)
     * @see #compose(Predicate, Predicate)
     */
    default <S> BooleanBiFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BooleanBiConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code BooleanBiConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BooleanBiConsumer consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(this.apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link BooleanBiFunction}. Thereby the primitive
     * input argument for this operation is autoboxed. This method is just convenience to provide the ability to use
     * this {@code BooleanBiFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code BooleanBiFunction}.
     */
    default BiFunction<Boolean, Boolean, R> boxed() {
        return this::apply;
    }
}
