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

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;

/**
 * Represents a function that accepts an object-valued and a {@code long}-valued argument, and produces a {@code
 * long}-valued result. This is the {@code (reference, long)}, {@code long}-producing primitive specialization for
 * {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(Object, long)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToLongObjLongFunction<T> {

    /**
     * Creates a {@link ToLongObjLongFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToLongObjLongFunction} which always returns a given value.
     */
    static <T> ToLongObjLongFunction<T> constant(byte ret) {
        return (t, value) -> ret;
    }

    /**
     * Creates a {@link ToLongObjLongFunction} which uses the first parameter of this one as argument for the given
     * {@link ToLongFunction}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToLongObjLongFunction} which uses the first parameter of this one as argument for the
     * given {@code ToLongFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ToLongObjLongFunction<T> onlyFirst(final ToLongFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsLong(t);
    }

    /**
     * Creates a {@link ToLongObjLongFunction} which uses the second parameter of this one as argument for the given
     * {@link LongUnaryOperator}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToLongObjLongFunction} which uses the second parameter of this one as argument for the
     * given {@code LongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ToLongObjLongFunction<T> onlySecond(final LongUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsLong(value);
    }

    /**
     * Applies this {@link ToLongObjLongFunction} to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    long applyAsLong(T t, long value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToLongObjLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code LongUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToLongObjLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongUnaryOperator)
     * @see #andThen(LongFunction)
     */
    default <U> ToLongObjLongFunction<U> compose(final Function<? super U, ? extends T> before1,
            final LongUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> applyAsLong(before1.apply(u), before2.applyAsLong(value));
    }

    /**
     * Returns a composed {@link ToLongBiFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToLongFunction} to apply before this operation is applied
     * @return A composed {@code ToLongBiFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(LongUnaryOperator)
     * @see #andThen(LongFunction)
     */
    default <U, V> ToLongBiFunction<U, V> compose(final Function<? super U, ? extends T> before1,
            final ToLongFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> applyAsLong(before1.apply(u), before2.applyAsLong(v));
    }

    /**
     * Returns a composed {@link ToLongObjLongFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToLongFunction} to apply after this operation is applied
     * @return A composed {@code LongBinaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, LongUnaryOperator)
     * @see #compose(Function, ToLongFunction)
     */
    default ToLongObjLongFunction<T> andThen(final LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsLong(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjLongFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code LongFunction} to apply after this operation is applied
     * @return A composed {@code ObjLongFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, LongUnaryOperator)
     * @see #compose(Function, ToLongFunction)
     */
    default <S> ObjLongFunction<T, S> andThen(final LongFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjLongConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code ObjLongConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ObjLongConsumer<T> consume(LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjLongFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjLongFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjLongFunction}.
     */
    default BiFunction<T, Long, Long> boxed() {
        return this::applyAsLong;
    }
}
