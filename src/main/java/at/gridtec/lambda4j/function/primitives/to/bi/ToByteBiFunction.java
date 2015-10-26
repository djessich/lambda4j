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
package at.gridtec.lambda4j.function.primitives.to.bi;

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that produces a byte-valued result from two arguments. This is the {@code byte}-producing
 * primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToByteBiFunction<T, U> {

    /**
     * Creates a {@link ToByteBiFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToByteBiFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U> ToByteBiFunction<T, U> constant(byte ret) {
        return (t, u) -> ret;
    }

    /**
     * Creates a {@link ToByteBiFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link ToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToByteBiFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code ToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ToByteBiFunction<T, U> onlyFirst(@Nonnull final ToByteFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u) -> function.applyAsByte(t);
    }

    /**
     * Creates a {@link ToByteBiFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link ToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToByteBiFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code ToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ToByteBiFunction<T, U> onlySecond(@Nonnull final ToByteFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u) -> function.applyAsByte(u);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(T t, U u);

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
     * Returns a composed {@link ToByteBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @return A composed {@code ToByteBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code byte}.
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ToByteBiFunction<T, U> compose(@Nonnull final UnaryOperator<T> before1,
            @Nonnull final UnaryOperator<U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsByte(before1.apply(t), before2.apply(u));
    }

    /**
     * Returns a composed {@link ToByteBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before function
     * @param <B> The type of the argument to the second before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToByteBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default <A, B> ToByteBiFunction<A, B> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsByte(before1.apply(a), before2.apply(b));
    }

    /**
     * Returns a composed {@link BiFunction} that first applies this function to its input, and then applies the {@code
     * after} function to the result. If evaluation of either function throws an exception, it is relayed to the caller
     * of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFunction} that first applies this function to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator, UnaryOperator)
     * @see #compose(Function, Function)
     */
    @Nonnull
    default <R> BiFunction<T, U, R> andThen(@Nonnull final ByteFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.apply(applyAsByte(t, u));
    }

    /**
     * Returns a composed {@link BiPredicate} that first applies this function to its input, and then applies the {@code
     * after} function to the result. If evaluation of either function throws an exception, it is relayed to the caller
     * of the composed function. This method is just convenience, to provide the ability to transform this operation to
     * an operation, returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiPredicate} that first applies this function to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    default BiPredicate<T, U> andThenToBoolean(final ByteToBooleanFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsBoolean(applyAsByte(t, u));
    }

    /**
     * Returns a composed {@link ToByteBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToByteBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ToByteBiFunction<T, U> andThenToByte(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsByte(applyAsByte(t, u));
    }

    /**
     * Returns a composed {@link ToCharBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToCharBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ToCharBiFunction<T, U> andThenToChar(@Nonnull final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsChar(applyAsByte(t, u));
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToDoubleBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ToDoubleBiFunction<T, U> andThenToDouble(@Nonnull final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsDouble(applyAsByte(t, u));
    }

    /**
     * Returns a composed {@link ToFloatBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToFloatBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ToFloatBiFunction<T, U> andThenToFloat(@Nonnull final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsFloat(applyAsByte(t, u));
    }

    /**
     * Returns a composed {@link ToIntBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToIntBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ToIntBiFunction<T, U> andThenToInt(@Nonnull final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsInt(applyAsByte(t, u));
    }

    /**
     * Returns a composed {@link ToLongBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToLongBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ToLongBiFunction<T, U> andThenToLong(@Nonnull final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsLong(applyAsByte(t, u));

    }

    /**
     * Returns a composed {@link ToShortBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToShortBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ToShortBiFunction<T, U> andThenToShort(@Nonnull final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsShort(applyAsByte(t, u));
    }

    /**
     * Returns a composed {@link BiConsumer} that fist applies this function to its input, and then consumes the result
     * using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiConsumer} that fist applies this function to its input, and then consumes the result
     * using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiConsumer<T, U> consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.accept(applyAsByte(t, u));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ToByteFunction<Pair<T, U>> tupled() {
        return t -> applyAsByte(t.getLeft(), t.getRight());
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ToByteBiFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ToByteBiFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ToByteBiFunction}.
     */
    @Nonnull
    default BiFunction<T, U, Byte> boxed() {
        return this::applyAsByte;
    }
}
