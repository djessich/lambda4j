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
import at.gridtec.lambda4j.operators.binary.ByteBinaryOperator;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

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
    static <T> ToByteObjByteFunction<T> constant(byte ret) {
        return (t, value) -> ret;
    }

    /**
     * Creates a {@link ToByteObjByteFunction} which uses the first parameter of this one as argument for the given
     * {@link ToByteFunction}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToByteObjByteFunction} which uses the first parameter of this one as argument for the
     * given {@code ToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ToByteObjByteFunction<T> onlyFirst(final ToByteFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsByte(t);
    }

    /**
     * Creates a {@link ToByteObjByteFunction} which uses the second parameter of this one as argument for the given
     * {@link ByteUnaryOperator}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToByteObjByteFunction} which uses the second parameter of this one as argument for the
     * given {@code ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ToByteObjByteFunction<T> onlySecond(final ByteUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsByte(value);
    }

    /**
     * Applies this {@link ToByteObjByteFunction} to the given arguments.
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
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToByteObjByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code ByteUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToByteObjByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteUnaryOperator)
     * @see #andThen(ByteFunction)
     */
    default <U> ToByteObjByteFunction<U> compose(final Function<? super U, ? extends T> before1,
            final ByteUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> applyAsByte(before1.apply(u), before2.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ToByteBiFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToByteFunction} to apply before this operation is applied
     * @return A composed {@code ToByteBiFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ByteUnaryOperator)
     * @see #andThen(ByteFunction)
     */
    default <U, V> ToByteBiFunction<U, V> compose(final Function<? super U, ? extends T> before1,
            final ToByteFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> applyAsByte(before1.apply(u), before2.applyAsByte(v));
    }

    /**
     * Returns a composed {@link ByteBinaryOperator} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToByteFunction} to apply after this operation is applied
     * @return A composed {@code ByteBinaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, ByteUnaryOperator)
     * @see #compose(Function, ToByteFunction)
     */
    default ToByteObjByteFunction<T> andThen(final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsByte(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjByteFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code ByteFunction} to apply after this operation is applied
     * @return A composed {@code ObjByteFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, ByteUnaryOperator)
     * @see #compose(Function, ToByteFunction)
     */
    default <S> ObjByteFunction<T, S> andThen(final ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link ObjByteConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code ObjByteConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ObjByteConsumer<T> consume(ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsByte(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjByteFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjByteFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjByteFunction}.
     */
    default BiFunction<T, Byte, Byte> boxed() {
        return this::applyAsByte;
    }
}
