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
import at.gridtec.lambda4j.function.primitives.to.bi.ToByteBiFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToByteTriFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;
import at.gridtec.lambda4j.supplier.ByteSupplier;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

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
     * Calls the given {@link ToByteBiObjByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code ToByteBiObjByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static <T, U> byte call(@Nonnull final ToByteBiObjByteFunction<? super T, ? super U> function, T t, U u,
            byte value) {
        Objects.requireNonNull(function);
        return function.applyAsByte(t, u, value);
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
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(T t, U u, byte value);

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @param value The primitive value to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default byte applyAsByte(@Nonnull Pair<T, U> tuple, byte value) {
        Objects.requireNonNull(tuple);
        return applyAsByte(tuple.getLeft(), tuple.getRight(), value);
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
     * Returns a composed {@link ToByteBiObjByteFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code ToByteBiObjByteFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code byte}.
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default <A, B> ToByteBiObjByteFunction<A, B> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final ByteUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> applyAsByte(before1.apply(a), before2.apply(b), before3.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToByteTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default <A, B, C> ToByteTriFunction<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final ToByteFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsByte(before1.apply(a), before2.apply(b), before3.applyAsByte(c));
    }

    /**
     * Returns a composed {@link BiObjByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, ByteUnaryOperator)
     * @see #compose(Function, Function, ToByteFunction)
     */
    @Nonnull
    default <S> BiObjByteFunction<T, U, S> andThen(@Nonnull final ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsByte(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjByteConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjByteConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjByteConsumer<T, U> consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsByte(t, u, value));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param t The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ToByteObjByteFunction<U> partial(T t) {
        return (u, value) -> applyAsByte(t, u, value);
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param value The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ToByteBiFunction<T, U> partial(byte value) {
        return (t, u) -> applyAsByte(t, u, value);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the function
     * @param u The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ByteUnaryOperator partial(T t, U u) {
        return value -> applyAsByte(t, u, value);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the function
     * @param value The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ToByteFunction<U> partial(T t, byte value) {
        return u -> applyAsByte(t, u, value);
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
    default ByteSupplier partial(T t, U u, byte value) {
        return () -> applyAsByte(t, u, value);
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ToByteObjByteFunction<Pair<T, U>> tupled() {
        return this::applyAsByte;
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
