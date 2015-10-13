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

import at.gridtec.lambda4j.consumer.primitives.FloatConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjFloatConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.FloatFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToFloatTriFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts two object-valued and a {@code float}-valued argument, and produces a {@code
 * float}-valued result. This is the {@code (reference, reference, float)}, {@code float}-producing primitive
 * specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(Object, Object, float)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToFloatBiObjFloatFunction<T, U> {

    /**
     * Creates a {@link ToFloatBiObjFloatFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToFloatBiObjFloatFunction} which always returns a given value.
     */
    static <T, U> ToFloatBiObjFloatFunction<T, U> constant(float ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Creates a {@link ToFloatBiObjFloatFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@link ToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToFloatBiObjFloatFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ToFloatFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToFloatBiObjFloatFunction<T, U> onlyFirst(final ToFloatFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsFloat(t);
    }

    /**
     * Creates a {@link ToFloatBiObjFloatFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToFloatBiObjFloatFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ToFloatFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToFloatBiObjFloatFunction<T, U> onlySecond(final ToFloatFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsFloat(u);
    }

    /**
     * Creates a {@link ToFloatBiObjFloatFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@link FloatUnaryOperator}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToFloatBiObjFloatFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToFloatBiObjFloatFunction<T, U> onlyThird(final FloatUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsFloat(value);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(T t, U u, float value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToFloatBiObjFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code Function} to apply before this operation is applied
     * @param before3 The third {@code FloatUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToFloatBiObjFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(FloatUnaryOperator)
     * @see #andThen(FloatFunction)
     */
    default <A, B> ToFloatBiObjFloatFunction<A, B> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final FloatUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> applyAsFloat(before1.apply(a), before2.apply(b), before3.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToFloatFunction} to apply before this operation is applied
     * @return A composed {@code ToFloatTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(FloatUnaryOperator)
     * @see #andThen(FloatFunction)
     */
    default <A, B, C> ToFloatTriFunction<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final ToFloatFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsFloat(before1.apply(a), before2.apply(b), before3.applyAsFloat(c));
    }

    /**
     * Returns a composed {@link ToFloatBiObjFloatFunction} that first applies this operation to its input, and then
     * applies the {@code after} operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param after The {@code FloatUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToFloatBiObjFloatFunction} that first applies this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, FloatUnaryOperator)
     * @see #compose(Function, Function, ToFloatFunction)
     */
    default ToFloatBiObjFloatFunction<T, U> andThen(final FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsFloat(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code FloatFunction} to apply after this operation is applied
     * @return A composed {@code BiObjFloatFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, FloatUnaryOperator)
     * @see #compose(Function, Function, ToFloatFunction)
     */
    default <S> BiObjFloatFunction<T, U, S> andThen(final FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatConsumer} that fist applies this operation to its input, and then consumes
     * the result using the given {@code FloatConsumer}. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The {@code FloatConsumer} which consumes the result from this operation
     * @return A composed {@code BiObjFloatConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BiObjFloatConsumer<T, U> consume(FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjFloatFunction}. Thereby the primitive input
     * argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjFloatFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Float, Float> boxed() {
        return this::applyAsFloat;
    }
}
