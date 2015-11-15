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
import at.gridtec.lambda4j.consumer.primitives.obj.ObjFloatConsumer;
import at.gridtec.lambda4j.function.primitives.FloatFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToFloatBiFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.supplier.FloatSupplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts an object-valued and a {@code float}-valued argument, and produces a {@code
 * float}-valued result. This is the {@code (reference, float)}, {@code float}-producing primitive specialization for
 * {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(Object, float)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToFloatObjFloatFunction<T> {

    /**
     * Calls the given {@link ToFloatObjFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ToFloatObjFloatFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static <T> float call(@Nonnull final ToFloatObjFloatFunction<? super T> function, T t, float value) {
        Objects.requireNonNull(function);
        return function.applyAsFloat(t, value);
    }

    /**
     * Creates a {@link ToFloatObjFloatFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToFloatFunction}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToFloatObjFloatFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToFloatFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ToFloatObjFloatFunction<T> onlyFirst(@Nonnull final ToFloatFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsFloat(t);
    }

    /**
     * Creates a {@link ToFloatObjFloatFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link FloatUnaryOperator}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToFloatObjFloatFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ToFloatObjFloatFunction<T> onlySecond(@Nonnull final FloatUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsFloat(value);
    }

    /**
     * Creates a {@link ToFloatObjFloatFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToFloatObjFloatFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ToFloatObjFloatFunction<T> constant(float ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(T t, float value);

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
     * Returns a composed {@link ToFloatObjFloatFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code ToFloatObjFloatFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code float}.
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default <U> ToFloatObjFloatFunction<U> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> applyAsFloat(before1.apply(u), before2.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ToFloatBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToFloatBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(FloatFunction)
     */
    @Nonnull
    default <U, V> ToFloatBiFunction<U, V> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final ToFloatFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> applyAsFloat(before1.apply(u), before2.applyAsFloat(v));
    }

    /**
     * Returns a composed {@link ObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, FloatUnaryOperator)
     * @see #compose(Function, ToFloatFunction)
     */
    @Nonnull
    default <S> ObjFloatFunction<T, S> andThen(@Nonnull final FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsFloat(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjFloatConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjFloatConsumer<T> consume(@Nonnull final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsFloat(t, value));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 1};
     *
     * @param t The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default FloatUnaryOperator partial(T t) {
        return value -> applyAsFloat(t, value);
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 1};
     *
     * @param value The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ToFloatFunction<T> partial(float value) {
        return t -> applyAsFloat(t, value);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 0}.
     *
     * @param t The first argument to partially apply to the function
     * @param value The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default FloatSupplier partial(T t, float value) {
        return () -> applyAsFloat(t, value);
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjFloatFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjFloatFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjFloatFunction}.
     */
    @Nonnull
    default BiFunction<T, Float, Float> boxed() {
        return this::applyAsFloat;
    }
}
