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

import at.gridtec.lambda4j.consumer.primitives.CharConsumer;
import at.gridtec.lambda4j.consumer.primitives.obj.ObjCharConsumer;
import at.gridtec.lambda4j.function.primitives.CharFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.bi.ToCharBiFunction;
import at.gridtec.lambda4j.operators.binary.CharBinaryOperator;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Represents a function that accepts an object-valued and a {@code char}-valued argument, and produces a {@code
 * char}-valued result. This is the {@code (reference, char)}, {@code char}-producing primitive specialization for
 * {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(Object, char)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToCharObjCharFunction<T> {

    /**
     * Creates a {@link ToCharObjCharFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToCharObjCharFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ToCharObjCharFunction<T> constant(char ret) {
        return (t, value) -> ret;
    }

    /**
     * Creates a {@link ToCharObjCharFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToCharFunction}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToCharObjCharFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToCharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ToCharObjCharFunction<T> onlyFirst(@Nonnull final ToCharFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsChar(t);
    }

    /**
     * Creates a {@link ToCharObjCharFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link CharUnaryOperator}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToCharObjCharFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ToCharObjCharFunction<T> onlySecond(@Nonnull final CharUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsChar(value);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    char applyAsChar(T t, char value);

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
     * Returns a composed {@link ToCharObjCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code CharUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToCharObjCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharUnaryOperator)
     * @see #andThen(CharFunction)
     */
    default <U> ToCharObjCharFunction<U> compose(final Function<? super U, ? extends T> before1,
            final CharUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> applyAsChar(before1.apply(u), before2.applyAsChar(value));
    }

    /**
     * Returns a composed {@link ToCharBiFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToCharFunction} to apply before this operation is applied
     * @return A composed {@code ToCharBiFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(CharUnaryOperator)
     * @see #andThen(CharFunction)
     */
    default <U, V> ToCharBiFunction<U, V> compose(final Function<? super U, ? extends T> before1,
            final ToCharFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> applyAsChar(before1.apply(u), before2.applyAsChar(v));
    }

    /**
     * Returns a composed {@link CharBinaryOperator} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToCharFunction} to apply after this operation is applied
     * @return A composed {@code CharBinaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, CharUnaryOperator)
     * @see #compose(Function, ToCharFunction)
     */
    default ToCharObjCharFunction<T> andThen(final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsChar(applyAsChar(t, value));
    }

    /**
     * Returns a composed {@link ObjCharFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code CharFunction} to apply after this operation is applied
     * @return A composed {@code ObjCharFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, CharUnaryOperator)
     * @see #compose(Function, ToCharFunction)
     */
    default <S> ObjCharFunction<T, S> andThen(final CharFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsChar(t, value));
    }

    /**
     * Returns a composed {@link ObjCharConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@link CharConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjCharConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjCharConsumer<T> consume(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsChar(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjCharFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjCharFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjCharFunction}.
     */
    @Nonnull
    default BiFunction<T, Character, Character> boxed() {
        return this::applyAsChar;
    }
}
