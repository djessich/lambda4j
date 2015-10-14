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
package at.gridtec.lambda4j.function.primitives.obj;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

/**
 * Represents a function that accepts an object-valued and a {@code int}-valued argument, and produces a {@code
 * int}-valued result. This is the {@code (reference, int)}, {@code int}-producing primitive specialization for {@link
 * BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(Object, int)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToIntObjIntFunction<T> {

    /**
     * Creates a {@link ToIntObjIntFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToIntObjIntFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ToIntObjIntFunction<T> constant(int ret) {
        return (t, value) -> ret;
    }

    /**
     * Creates a {@link ToIntObjIntFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToIntFunction}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToIntObjIntFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code ToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ToIntObjIntFunction<T> onlyFirst(@Nonnull final ToIntFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsInt(t);
    }

    /**
     * Creates a {@link ToIntObjIntFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link IntUnaryOperator}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToIntObjIntFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code IntUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ToIntObjIntFunction<T> onlySecond(@Nonnull final IntUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsInt(value);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    int applyAsInt(T t, int value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToIntObjIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code IntUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToIntObjIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntUnaryOperator)
     * @see #andThen(IntFunction)
     */
    default <U> ToIntObjIntFunction<U> compose(final Function<? super U, ? extends T> before1,
            final IntUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> applyAsInt(before1.apply(u), before2.applyAsInt(value));
    }

    /**
     * Returns a composed {@link ToIntBiFunction} that applies the given {@code before} functions to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToIntFunction} to apply before this operation is applied
     * @return A composed {@code ToIntBiFunction} that applies the given {@code before} functions to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(IntUnaryOperator)
     * @see #andThen(IntFunction)
     */
    default <U, V> ToIntBiFunction<U, V> compose(final Function<? super U, ? extends T> before1,
            final ToIntFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> applyAsInt(before1.apply(u), before2.applyAsInt(v));
    }

    /**
     * Returns a composed {@link ToIntObjIntFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToIntFunction} to apply after this operation is applied
     * @return A composed {@code IntBinaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, IntUnaryOperator)
     * @see #compose(Function, ToIntFunction)
     */
    default ToIntObjIntFunction<T> andThen(final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsInt(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code IntFunction} to apply after this operation is applied
     * @return A composed {@code ObjIntFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, IntUnaryOperator)
     * @see #compose(Function, ToIntFunction)
     */
    default <S> ObjIntFunction<T, S> andThen(final IntFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjIntConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@link IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjIntConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjIntConsumer<T> consume(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjIntFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjIntFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjIntFunction}.
     */
    @Nonnull
    default BiFunction<T, Integer, Integer> boxed() {
        return this::applyAsInt;
    }
}
