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
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjByteConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToByteTriFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts two object-valued and a {@code byte}-valued argument, and produces a {@code
 * byte}-valued result. This is the {@code (reference, reference, byte)}, {@code byte}-producing primitive
 * specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(Object, Object, byte)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToByteBiObjByteFunction<T, U> {

    /**
     * Creates a {@link ToByteBiObjByteFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToByteBiObjByteFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U> ToByteBiObjByteFunction<T, U> constant(byte ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Creates a {@link ToByteBiObjByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToByteBiObjByteFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ToByteBiObjByteFunction<T, U> onlyFirst(@Nonnull final ToByteFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsByte(t);
    }

    /**
     * Creates a {@link ToByteBiObjByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToByteBiObjByteFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ToByteBiObjByteFunction<T, U> onlySecond(@Nonnull final ToByteFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsByte(u);
    }

    /**
     * Creates a {@link ToByteBiObjByteFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link ByteUnaryOperator}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToByteBiObjByteFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ToByteBiObjByteFunction<T, U> onlyThird(@Nonnull final ByteUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsByte(value);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(T t, U u, byte value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToByteBiObjByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code Function} to apply before this operation is applied
     * @param before3 The third {@code ByteUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToByteBiObjByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteUnaryOperator)
     * @see #andThen(ByteFunction)
     */
    default <A, B> ToByteBiObjByteFunction<A, B> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final ByteUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> applyAsByte(before1.apply(a), before2.apply(b), before3.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToByteFunction} to apply before this operation is applied
     * @return A composed {@code ToByteTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ByteUnaryOperator)
     * @see #andThen(ByteFunction)
     */
    default <A, B, C> ToByteTriFunction<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final ToByteFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsByte(before1.apply(a), before2.apply(b), before3.applyAsByte(c));
    }

    /**
     * Returns a composed {@link ToByteBiObjByteFunction} that first applies this operation to its input, and then
     * applies the {@code after} operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param after The {@code ByteUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToByteBiObjByteFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, ByteUnaryOperator)
     * @see #compose(Function, Function, ToByteFunction)
     */
    default ToByteBiObjByteFunction<T, U> andThen(final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsByte(applyAsByte(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjByteFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code ByteFunction} to apply after this operation is applied
     * @return A composed {@code BiObjByteFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, ByteUnaryOperator)
     * @see #compose(Function, Function, ToByteFunction)
     */
    default <S> BiObjByteFunction<T, U, S> andThen(final ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsByte(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjByteConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code ByteConsumer}. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The {@code ByteConsumer} which consumes the result from this operation
     * @return A composed {@code BiObjByteConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BiObjByteConsumer<T, U> consume(ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsByte(t, u, value));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjByteFunction}. Thereby the primitive input
     * argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjByteFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Byte, Byte> boxed() {
        return this::applyAsByte;
    }
}
