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

import at.gridtec.lambda4j.consumer.primitives.obj.BiObjLongConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToLongTriFunction;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;

/**
 * Represents a function that accepts two object-valued and a {@code long}-valued argument, and produces a {@code
 * long}-valued result. This is the {@code (reference, reference, long)}, {@code long}-producing primitive
 * specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(Object, Object, long)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToLongBiObjLongFunction<T, U> {

    /**
     * Creates a {@link ToLongBiObjLongFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToLongBiObjLongFunction} which always returns a given value.
     */
    static <T, U> ToLongBiObjLongFunction<T, U> constant(long ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Creates a {@link ToLongBiObjLongFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToLongBiObjLongFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToLongFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToLongBiObjLongFunction<T, U> onlyFirst(final ToLongFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsLong(t);
    }

    /**
     * Creates a {@link ToLongBiObjLongFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToLongBiObjLongFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ToLongFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToLongBiObjLongFunction<T, U> onlySecond(final ToLongFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsLong(u);
    }

    /**
     * Creates a {@link ToLongBiObjLongFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link LongUnaryOperator}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToLongBiObjLongFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code LongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToLongBiObjLongFunction<T, U> onlyThird(final LongUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsLong(value);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    long applyAsLong(T t, U u, long value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToLongBiObjLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code Function} to apply before this operation is applied
     * @param before3 The third {@code LongUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToLongBiObjLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongUnaryOperator)
     * @see #andThen(LongFunction)
     */
    default <A, B> ToLongBiObjLongFunction<A, B> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final LongUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> applyAsLong(before1.apply(a), before2.apply(b), before3.applyAsLong(value));
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToLongFunction} to apply before this operation is applied
     * @return A composed {@code ToLongTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(LongUnaryOperator)
     * @see #andThen(LongFunction)
     */
    default <A, B, C> ToLongTriFunction<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final ToLongFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsLong(before1.apply(a), before2.apply(b), before3.applyAsLong(c));
    }

    /**
     * Returns a composed {@link ToLongBiObjLongFunction} that first applies this operation to its input, and then
     * applies the {@code after} operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param after The {@code LongUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToLongBiObjLongFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, LongUnaryOperator)
     * @see #compose(Function, Function, ToLongFunction)
     */
    default ToLongBiObjLongFunction<T, U> andThen(final LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsLong(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code LongFunction} to apply after this operation is applied
     * @return A composed {@code BiObjLongFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, LongUnaryOperator)
     * @see #compose(Function, Function, ToLongFunction)
     */
    default <S> BiObjLongFunction<T, U, S> andThen(final LongFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code LongConsumer}. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The {@code LongConsumer} which consumes the result from this operation
     * @return A composed {@code BiObjLongConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BiObjLongConsumer<T, U> consume(LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjLongFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjLongFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjLongFunction}.
     */
    default TriFunction<T, U, Long, Long> boxed() {
        return this::applyAsLong;
    }
}
