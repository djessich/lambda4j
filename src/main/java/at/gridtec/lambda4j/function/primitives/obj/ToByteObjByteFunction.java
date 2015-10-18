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

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.ObjByteConsumer;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToByteBiFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts an object-valued and a {@code byte}-valued argument, and produces a {@code
 * byte}-valued result. This is the {@code (reference, byte)}, {@code byte}-producing primitive specialization for
 * {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(Object, byte)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToByteObjByteFunction<T> {

    /**
     * Creates a {@link ToByteObjByteFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToByteObjByteFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ToByteObjByteFunction<T> constant(byte ret) {
        return (t, value) -> ret;
    }

    /**
     * Creates a {@link ToByteObjByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToByteFunction}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToByteObjByteFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ToByteObjByteFunction<T> onlyFirst(@Nonnull final ToByteFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsByte(t);
    }

    /**
     * Creates a {@link ToByteObjByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ByteUnaryOperator}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToByteObjByteFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ToByteObjByteFunction<T> onlySecond(@Nonnull final ByteUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsByte(value);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(T t, byte value);

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
     * Returns a composed {@link ToByteObjByteFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code ToByteObjByteFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code byte}.
     * @see #andThen(ByteUnaryOperator)
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default <U> ToByteObjByteFunction<U> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final ByteUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> applyAsByte(before1.apply(u), before2.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ToByteBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToByteBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ByteUnaryOperator)
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default <U, V> ToByteBiFunction<U, V> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final ToByteFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> applyAsByte(before1.apply(u), before2.applyAsByte(v));
    }

    /**
     * Returns a composed {@link ToByteObjByteFunction} that first applies this function to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToByteObjByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is a primitive specialization of {@link BiFunction}. Therefore the returned
     * operation handles primitive types. In this case this is {@code byte}.
     * @see #compose(Function, ByteUnaryOperator)
     * @see #compose(Function, ToByteFunction)
     */
    @Nonnull
    default ToByteObjByteFunction<T> andThen(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsByte(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned function is able to handle every type.
     * @see #compose(Function, ByteUnaryOperator)
     * @see #compose(Function, ToByteFunction)
     */
    @Nonnull
    default <S> ObjByteFunction<T, S> andThen(@Nonnull final ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjByteConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjByteConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjByteConsumer<T> consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjByteFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjByteFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjByteFunction}.
     */
    @Nonnull
    default BiFunction<T, Byte, Byte> boxed() {
        return this::applyAsByte;
    }
}
