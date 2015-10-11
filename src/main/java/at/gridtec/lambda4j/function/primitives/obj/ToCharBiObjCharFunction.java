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
import at.gridtec.lambda4j.consumer.primitives.obj.BiObjCharConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.CharFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToCharTriFunction;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Represents a function that accepts two object-valued and a {@code char}-valued argument, and produces a {@code
 * char}-valued result. This is the {@code (reference, reference, char)}, {@code char}-producing primitive
 * specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(Object, Object, char)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToCharBiObjCharFunction<T, U> {

    /**
     * Creates a {@link ToCharBiObjCharFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToCharBiObjCharFunction} which always returns a given value.
     */
    static <T, U> ToCharBiObjCharFunction<T, U> constant(char ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Creates a {@link ToCharBiObjCharFunction} which uses the first parameter of this one as argument for the given
     * {@link ToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToCharBiObjCharFunction} which uses the first parameter of this one as argument for the
     * given {@code ToCharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToCharBiObjCharFunction<T, U> onlyFirst(final ToCharFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsChar(t);
    }

    /**
     * Creates a {@link ToCharBiObjCharFunction} which uses the second parameter of this one as argument for the given
     * {@link ToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToCharBiObjCharFunction} which uses the second parameter of this one as argument for the
     * given {@code ToCharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToCharBiObjCharFunction<T, U> onlySecond(final ToCharFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsChar(u);
    }

    /**
     * Creates a {@link ToCharBiObjCharFunction} which uses the third parameter of this one as argument for the given
     * {@link CharUnaryOperator}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToCharBiObjCharFunction} which uses the third parameter of this one as argument for the
     * given {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToCharBiObjCharFunction<T, U> onlyThird(final CharUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsChar(value);
    }

    /**
     * Applies this {@link ToCharBiObjCharFunction} to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    char applyAsChar(T t, U u, char value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToCharBiObjCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code Function} to apply before this operation is applied
     * @param before3 The third {@code CharUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToCharBiObjCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharUnaryOperator)
     * @see #andThen(CharFunction)
     */
    default <A, B> ToCharBiObjCharFunction<A, B> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> applyAsChar(before1.apply(a), before2.apply(b), before3.applyAsChar(value));
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToCharFunction} to apply before this operation is applied
     * @return A composed {@code ToCharTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(CharUnaryOperator)
     * @see #andThen(CharFunction)
     */
    default <A, B, C> ToCharTriFunction<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final ToCharFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsChar(before1.apply(a), before2.apply(b), before3.applyAsChar(c));
    }

    /**
     * Returns a composed {@link ToCharBiObjCharFunction} that first applies this operation to its input, and then
     * applies the {@code after} operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param after The {@code CharUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToCharBiObjCharFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, CharUnaryOperator)
     * @see #compose(Function, Function, ToCharFunction)
     */
    default ToCharBiObjCharFunction<T, U> andThen(final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsChar(applyAsChar(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjCharFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code CharFunction} to apply after this operation is applied
     * @return A composed {@code BiObjCharFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, CharUnaryOperator)
     * @see #compose(Function, Function, ToCharFunction)
     */
    default <S> BiObjCharFunction<T, U, S> andThen(final CharFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsChar(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjCharConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code CharConsumer}. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The {@code CharConsumer} which consumes the result from this operation
     * @return A composed {@code BiObjCharConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BiObjCharConsumer<T, U> consume(CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsChar(t, u, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjCharFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjCharFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjCharFunction}.
     */
    default TriFunction<T, U, Character, Character> boxed() {
        return this::applyAsChar;
    }
}
