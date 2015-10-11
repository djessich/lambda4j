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

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.ObjShortConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToShortBiFunction;
import at.gridtec.lambda4j.operators.binary.ShortBinaryOperator;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Represents a function that accepts an object-valued and a {@code short}-valued argument, and produces a {@code
 * short}-valued result. This is the {@code (reference, short)}, {@code short}-producing primitive specialization for
 * {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(Object, short)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToShortObjShortFunction<T> {

    /**
     * Creates a {@link ToShortObjShortFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToShortObjShortFunction} which always returns a given value.
     */
    static <T> ToShortObjShortFunction<T> constant(short ret) {
        return (t, value) -> ret;
    }

    /**
     * Creates a {@link ToShortObjShortFunction} which uses the first parameter of this one as argument for the given
     * {@link ToShortFunction}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToShortObjShortFunction} which uses the first parameter of this one as argument for the
     * given {@code ToShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ToShortObjShortFunction<T> onlyFirst(final ToShortFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsShort(t);
    }

    /**
     * Creates a {@link ToShortObjShortFunction} which uses the second parameter of this one as argument for the given
     * {@link ShortUnaryOperator}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToShortObjShortFunction} which uses the second parameter of this one as argument for the
     * given {@code ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ToShortObjShortFunction<T> onlySecond(final ShortUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsShort(value);
    }

    /**
     * Applies this {@link ToShortObjShortFunction} to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    short applyAsShort(T t, short value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToShortObjShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code ShortUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToShortObjShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortUnaryOperator)
     * @see #andThen(ShortFunction)
     */
    default <U> ToShortObjShortFunction<U> compose(final Function<? super U, ? extends T> before1,
            final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> applyAsShort(before1.apply(u), before2.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ToShortBiFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToShortFunction} to apply before this operation is applied
     * @return A composed {@code ToShortBiFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ShortUnaryOperator)
     * @see #andThen(ShortFunction)
     */
    default <U, V> ToShortBiFunction<U, V> compose(final Function<? super U, ? extends T> before1,
            final ToShortFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> applyAsShort(before1.apply(u), before2.applyAsShort(v));
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
     * @see #compose(Function, ShortUnaryOperator)
     * @see #compose(Function, ToShortFunction)
     */
    default ToShortObjShortFunction<T> andThen(final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsShort(applyAsShort(t, value));
    }

    /**
     * Returns a composed {@link ObjShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code ShortFunction} to apply after this operation is applied
     * @return A composed {@code ObjShortFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, ShortUnaryOperator)
     * @see #compose(Function, ToShortFunction)
     */
    default <S> ObjShortFunction<T, S> andThen(final ShortFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsShort(t, value));
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code ObjShortConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ObjShortConsumer<T> consume(ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsShort(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjShortFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjShortFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjShortFunction}.
     */
    default BiFunction<T, Short, Short> boxed() {
        return this::applyAsShort;
    }
}
