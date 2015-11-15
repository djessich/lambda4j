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
import at.gridtec.lambda4j.function.primitives.to.bi.ToFloatBiFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToFloatTriFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.supplier.FloatSupplier;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

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
     * Calls the given {@link ToFloatBiObjFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ToFloatBiObjFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static <T, U> float call(@Nonnull final ToFloatBiObjFloatFunction<? super T, ? super U> function, T t, U u,
            float value) {
        Objects.requireNonNull(function);
        return function.applyAsFloat(t, u, value);
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
    @Nonnull
    static <T, U> ToFloatBiObjFloatFunction<T, U> onlyFirst(@Nonnull final ToFloatFunction<? super T> function) {
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
    @Nonnull
    static <T, U> ToFloatBiObjFloatFunction<T, U> onlySecond(@Nonnull final ToFloatFunction<? super U> function) {
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
    @Nonnull
    static <T, U> ToFloatBiObjFloatFunction<T, U> onlyThird(@Nonnull final FloatUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsFloat(value);
    }

    /**
     * Creates a {@link ToFloatBiObjFloatFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToFloatBiObjFloatFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U> ToFloatBiObjFloatFunction<T, U> constant(float ret) {
        return (t, u, value) -> ret;
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
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @param value The primitive value to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default float applyAsFloat(@Nonnull Pair<T, U> tuple, float value) {
        Objects.requireNonNull(tuple);
        return applyAsFloat(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToFloatBiObjFloatFunction} that first applies the {@code before} operations to its
     * input, and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code ToFloatBiObjFloatFunction} that first applies the {@code before} operations to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code float}.
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default <A, B> ToFloatBiObjFloatFunction<A, B> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final FloatUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> applyAsFloat(before1.apply(a), before2.apply(b), before3.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToFloatTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default <A, B, C> ToFloatTriFunction<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2,
            @Nonnull final ToFloatFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsFloat(before1.apply(a), before2.apply(b), before3.applyAsFloat(c));
    }

    /**
     * Returns a composed {@link BiObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, FloatUnaryOperator)
     * @see #compose(Function, Function, ToFloatFunction)
     */
    @Nonnull
    default <S> BiObjFloatFunction<T, U, S> andThen(@Nonnull final FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatConsumer} that fist applies this operation to its input, and then consumes
     * the result using the given {@link FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjFloatConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjFloatConsumer<T, U> consume(@Nonnull final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsFloat(t, u, value));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param t The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ToFloatObjFloatFunction<U> partial(T t) {
        return (u, value) -> applyAsFloat(t, u, value);
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param value The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ToFloatBiFunction<T, U> partial(float value) {
        return (t, u) -> applyAsFloat(t, u, value);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the function
     * @param u The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default FloatUnaryOperator partial(T t, U u) {
        return value -> applyAsFloat(t, u, value);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the function
     * @param value The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ToFloatFunction<U> partial(T t, float value) {
        return u -> applyAsFloat(t, u, value);
    }

    /**
     * Applies this function partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param t The first argument to partially apply to the function
     * @param u The second argument to partially apply to the function
     * @param value The third argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default FloatSupplier partial(T t, U u, float value) {
        return () -> applyAsFloat(t, u, value);
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ToFloatObjFloatFunction<Pair<T, U>> tupled() {
        return this::applyAsFloat;
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjFloatFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjFloatFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Float, Float> boxed() {
        return this::applyAsFloat;
    }
}
