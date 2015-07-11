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
package at.gridtec.lambda4j.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts three float-valued argument and produces a result. This is the {@code
 * float}-consuming primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(float, float, float)}.
 *
 * @param <R> The type of return value from the function
 * @see at.gridtec.lambda4j.function.consumer.TriConsumer
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
        return (t, u, v) -> r;
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
     * Returns a composed {@link TriFunction} that applies the given {@code before} {@link ToFloatFunction}s to its
     * input, and then applies this function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param <V> The type of the argument to the second before function
     * @param before1 The first before {@code ToFloatFunction} to apply before this function is applied
     * @param before2 The second before {@code ToFloatFunction} to apply before this function is applied
     * @param before3 The third before {@code ToFloatFunction} to apply before this function is applied
     * @return A composed {@code TriFunction} that applies the given {@code before} {@code ToFloatFunction}s to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T, U, V> TriFunction<T, U, V, R> compose(final ToFloatFunction<? super T> before1,
            final ToFloatFunction<? super U> before2, final ToFloatFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> apply(before1.applyAsFloat(t), before2.applyAsFloat(u), before3.applyAsFloat(v));
    }

    /**
     * Returns a composed {@link FloatTriFunction} that first applies this function to its input, and then applies the
     * {@code after} {@link Function} to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code Function} to apply after this function is applied
     * @return A composed {@code FloatFunction} that first applies this function and then applies the {@code after}
     * function.
     * @throws NullPointerException If given after function is {@code null}
     * @see #compose(ToFloatFunction, ToFloatFunction, ToFloatFunction)
     */
    default <S> FloatTriFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link FloatTriFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code FloatTriFunction}.
     */
    default TriFunction<Float, Float, Float, R> boxed() {
        return this::apply;
    }
}
