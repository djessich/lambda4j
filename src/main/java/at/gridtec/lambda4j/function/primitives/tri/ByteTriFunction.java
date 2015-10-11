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
package at.gridtec.lambda4j.function.primitives.tri;

import at.gridtec.lambda4j.consumer.primitives.tri.ByteTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.operators.ternary.ByteTernaryOperator;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a function that accepts three byte-valued argument and produces a result. This is the {@code
 * byte}-consuming primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(byte, byte, byte)}.
 *
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteTriFunction<R> {

    /**
     * Creates a {@link ByteTriFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ByteTriFunction} which always returns a given value.
     */
    static <R> ByteTriFunction<R> constant(R r) {
        return (value1, value2, value3) -> r;
    }

    /**
     * Creates a {@link ByteTriFunction} which uses the first parameter of this one as argument for the given {@link
     * ByteFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ByteTriFunction} which uses the first parameter of this one as argument for the given
     * {@code ByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> ByteTriFunction<R> onlyFirst(final ByteFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value1);
    }

    /**
     * Creates a {@link ByteTriFunction} which uses the second parameter of this one as argument for the given {@link
     * ByteFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ByteTriFunction} which uses the second parameter of this one as argument for the given
     * {@code ByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> ByteTriFunction<R> onlySecond(final ByteFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value2);
    }

    /**
     * Creates a {@link ByteTriFunction} which uses the third parameter of this one as argument for the given {@link
     * ByteFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ByteTriFunction} which uses the third parameter of this one as argument for the given
     * {@code ByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> ByteTriFunction<R> onlyThird(final ByteFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value2);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(byte value1, byte value2, byte value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ByteTriFunction} that first applies the {@code before} {@link ByteUnaryOperator}s to
     * its input, and then applies this operation to the result. If evaluation of either operation throws an exception,
     * it is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code ByteUnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code ByteUnaryOperator} to apply before this operation is applied
     * @param before3 The third {@code ByteUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ByteTriFunction} that first applies the {@code before} {@code ByteUnaryOperator}s to
     * its input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default ByteTriFunction<R> compose(final ByteUnaryOperator before1, final ByteUnaryOperator before2,
            final ByteUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2, value3) -> apply(before1.applyAsByte(value1), before2.applyAsByte(value2),
                                                 before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link TriFunction} that applies the given {@code before} {@link ToByteFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first before {@code ToByteFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToByteFunction} to apply before this operation is applied
     * @param before3 The third before {@code ToByteFunction} to apply before this operation is applied
     * @return A composed {@code TriFunction} that applies the given {@code before} {@code ToByteFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T, U, V> TriFunction<T, U, V, R> compose(final ToByteFunction<? super T> before1,
            final ToByteFunction<? super U> before2, final ToByteFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2, value3) -> apply(before1.applyAsByte(value1), before2.applyAsByte(value2),
                                                 before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToByteFunction} to apply after this operation is applied
     * @return A composed {@code ByteTernaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ByteUnaryOperator, ByteUnaryOperator, ByteUnaryOperator)
     * @see #compose(ToByteFunction, ToByteFunction, ToByteFunction)
     */
    default ByteTernaryOperator andThen(final ToByteFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ByteTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code ByteTriFunction} to apply after this operation is applied
     * @return A composed {@code ByteTriFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ByteUnaryOperator, ByteUnaryOperator, ByteUnaryOperator)
     * @see #compose(ToByteFunction, ToByteFunction, ToByteFunction)
     */
    default <S> ByteTriFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ByteTriConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code ByteTriConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ByteTriConsumer consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(this.apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ByteTriFunction}. Thereby the primitive input
     * argument for this operation is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ByteTriFunction}.
     */
    default TriFunction<Byte, Byte, Byte, R> boxed() {
        return this::apply;
    }
}
