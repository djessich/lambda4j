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

import at.gridtec.lambda4j.consumer.primitives.obj.BiObjIntConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToIntTriFunction;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;

/**
 * Represents a function that accepts two object-valued and a {@code int}-valued argument, and produces a {@code
 * int}-valued result. This is the {@code (reference, reference, int)}, {@code int}-producing primitive specialization
 * for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(Object, Object, int)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToIntBiObjIntFunction<T, U> {

    /**
     * Creates a {@link ToIntBiObjIntFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToIntBiObjIntFunction} which always returns a given value.
     */
    static <T, U> ToIntBiObjIntFunction<T, U> constant(byte ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Creates a {@link ToIntBiObjIntFunction} which uses the first parameter of this one as argument for the given
     * {@link ToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToIntBiObjIntFunction} which uses the first parameter of this one as argument for the
     * given {@code ToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToIntBiObjIntFunction<T, U> onlyFirst(final ToIntFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsInt(t);
    }

    /**
     * Creates a {@link ToIntBiObjIntFunction} which uses the second parameter of this one as argument for the given
     * {@link ToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToIntBiObjIntFunction} which uses the second parameter of this one as argument for the
     * given {@code ToIntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToIntBiObjIntFunction<T, U> onlySecond(final ToIntFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsInt(u);
    }

    /**
     * Creates a {@link ToIntBiObjIntFunction} which uses the third parameter of this one as argument for the given
     * {@link IntUnaryOperator}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToIntBiObjIntFunction} which uses the third parameter of this one as argument for the
     * given {@code IntUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToIntBiObjIntFunction<T, U> onlyThird(final IntUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsInt(value);
    }

    /**
     * Applies this {@link ToIntBiObjIntFunction} to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    int applyAsInt(T t, U u, int value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToIntBiObjIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code Function} to apply before this operation is applied
     * @param before3 The third {@code IntUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToIntBiObjIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(IntUnaryOperator)
     * @see #andThen(IntFunction)
     */
    default <A, B> ToIntBiObjIntFunction<A, B> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final IntUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> applyAsInt(before1.apply(a), before2.apply(b), before3.applyAsInt(value));
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToIntFunction} to apply before this operation is applied
     * @return A composed {@code ToIntTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(IntUnaryOperator)
     * @see #andThen(IntFunction)
     */
    default <A, B, C> ToIntTriFunction<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final ToIntFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsInt(before1.apply(a), before2.apply(b), before3.applyAsInt(c));
    }

    /**
     * Returns a composed {@link ToIntBiObjIntFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code IntUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToIntBiObjIntFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, IntUnaryOperator)
     * @see #compose(Function, Function, ToIntFunction)
     */
    default ToIntBiObjIntFunction<T, U> andThen(final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsInt(applyAsInt(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code IntFunction} to apply after this operation is applied
     * @return A composed {@code BiObjIntFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, IntUnaryOperator)
     * @see #compose(Function, Function, ToIntFunction)
     */
    default <S> BiObjIntFunction<T, U, S> andThen(final IntFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsInt(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code IntConsumer}. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The {@code IntConsumer} which consumes the result from this operation
     * @return A composed {@code BiObjIntConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BiObjIntConsumer<T, U> consume(IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsInt(t, u, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjIntFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjIntFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjIntFunction}.
     */
    default TriFunction<T, U, Integer, Integer> boxed() {
        return this::applyAsInt;
    }
}
