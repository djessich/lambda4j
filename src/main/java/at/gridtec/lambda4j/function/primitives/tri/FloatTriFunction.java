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
package at.gridtec.lambda4j.function.primitives.tri;

import at.gridtec.lambda4j.consumer.primitives.tri.FloatTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.FloatFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.operators.ternary.FloatTernaryOperator;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a function that accepts three float-valued argument and produces a result. This is the {@code
 * float}-consuming primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(float, float, float)}.
 *
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatTriFunction<R> {

    /**
     * Creates a {@link FloatTriFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code FloatTriFunction} which always returns a given value.
     */
    static <R> FloatTriFunction<R> constant(R r) {
        return (value1, value2, value3) -> r;
    }

    /**
     * Creates a {@link FloatTriFunction} which uses the first parameter of this one as argument for the given {@link
     * FloatFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code FloatTriFunction} which uses the first parameter of this one as argument for the given
     * {@code FloatFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> FloatTriFunction<R> onlyFirst(final FloatFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value1);
    }

    /**
     * Creates a {@link FloatTriFunction} which uses the second parameter of this one as argument for the given {@link
     * FloatFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code FloatTriFunction} which uses the second parameter of this one as argument for the given
     * {@code FloatFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> FloatTriFunction<R> onlySecond(final FloatFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value2);
    }

    /**
     * Creates a {@link FloatTriFunction} which uses the third parameter of this one as argument for the given {@link
     * FloatFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code FloatTriFunction} which uses the third parameter of this one as argument for the given
     * {@code FloatFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> FloatTriFunction<R> onlyThird(final FloatFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value2);
    }

    /**
     * Applies this {@link FloatTriFunction} to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(float value1, float value2, float value3);

    /**
     * Returns a composed {@link FloatTriFunction} that first applies the {@code before} {@link FloatUnaryOperator}s to
     * its input, and then applies this operation to the result. If evaluation of either operation throws an exception,
     * it is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code FloatUnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code FloatUnaryOperator} to apply before this operation is applied
     * @param before3 The third {@code FloatUnaryOperator} to apply before this operation is applied
     * @return A composed {@code FloatTriFunction} that first applies the {@code before} {@code FloatUnaryOperator}s to
     * its input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default FloatTriFunction<R> compose(final FloatUnaryOperator before1, final FloatUnaryOperator before2,
            final FloatUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2, value3) -> apply(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                                                 before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriFunction} that applies the given {@code before} {@link ToFloatFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first before {@code ToFloatFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToFloatFunction} to apply before this operation is applied
     * @param before3 The third before {@code ToFloatFunction} to apply before this operation is applied
     * @return A composed {@code TriFunction} that applies the given {@code before} {@code ToFloatFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T, U, V> TriFunction<T, U, V, R> compose(final ToFloatFunction<? super T> before1,
            final ToFloatFunction<? super U> before2, final ToFloatFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2, value3) -> apply(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                                                 before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToFloatFunction} to apply after this operation is applied
     * @return A composed {@code FloatTernaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(FloatUnaryOperator, FloatUnaryOperator, FloatUnaryOperator)
     * @see #compose(ToFloatFunction, ToFloatFunction, ToFloatFunction)
     */
    default FloatTernaryOperator andThen(final ToFloatFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link FloatTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code FloatTriFunction} to apply after this operation is applied
     * @return A composed {@code FloatTriFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(FloatUnaryOperator, FloatUnaryOperator, FloatUnaryOperator)
     * @see #compose(ToFloatFunction, ToFloatFunction, ToFloatFunction)
     */
    default <S> FloatTriFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link FloatTriConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code FloatTriConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default FloatTriConsumer consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(this.apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link FloatTriFunction}. Thereby the primitive
     * input argument for this operation is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code FloatTriFunction}.
     */
    default TriFunction<Float, Float, Float, R> boxed() {
        return this::apply;
    }
}
