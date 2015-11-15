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
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;
import at.gridtec.lambda4j.supplier.ShortSupplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

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
     * Calls the given {@link ToShortObjShortFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ToShortObjShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static <T> short call(@Nonnull final ToShortObjShortFunction<? super T> function, T t, short value) {
        Objects.requireNonNull(function);
        return function.applyAsShort(t, value);
    }

    /**
     * Creates a {@link ToShortObjShortFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToShortFunction}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToShortObjShortFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ToShortObjShortFunction<T> onlyFirst(@Nonnull final ToShortFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsShort(t);
    }

    /**
     * Creates a {@link ToShortObjShortFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ShortUnaryOperator}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToShortObjShortFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ToShortObjShortFunction<T> onlySecond(@Nonnull final ShortUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsShort(value);
    }

    /**
     * Creates a {@link ToShortObjShortFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToShortObjShortFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ToShortObjShortFunction<T> constant(short ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
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
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToShortObjShortFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code ToShortObjShortFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code short}.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default <U> ToShortObjShortFunction<U> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> applyAsShort(before1.apply(u), before2.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ToShortBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToShortBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default <U, V> ToShortBiFunction<U, V> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final ToShortFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> applyAsShort(before1.apply(u), before2.applyAsShort(v));
    }

    /**
     * Returns a composed {@link ObjShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, ShortUnaryOperator)
     * @see #compose(Function, ToShortFunction)
     */
    @Nonnull
    default <S> ObjShortFunction<T, S> andThen(@Nonnull final ShortFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsShort(t, value));
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ShortConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjShortConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjShortConsumer<T> consume(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsShort(t, value));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 1};
     *
     * @param t The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ShortUnaryOperator partial(T t) {
        return value -> applyAsShort(t, value);
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 1};
     *
     * @param value The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ToShortFunction<T> partial(short value) {
        return t -> applyAsShort(t, value);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 0}.
     *
     * @param t The first argument to partially apply to the function
     * @param value The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ShortSupplier partial(T t, short value) {
        return () -> applyAsShort(t, value);
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjShortFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjShortFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjShortFunction}.
     */
    @Nonnull
    default BiFunction<T, Short, Short> boxed() {
        return this::applyAsShort;
    }
}
