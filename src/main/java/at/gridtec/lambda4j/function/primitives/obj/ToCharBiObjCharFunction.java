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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

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
    @Nonnull
    static <T, U> ToCharBiObjCharFunction<T, U> constant(char ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Creates a {@link ToCharBiObjCharFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToCharBiObjCharFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToCharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ToCharBiObjCharFunction<T, U> onlyFirst(@Nonnull final ToCharFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsChar(t);
    }

    /**
     * Creates a {@link ToCharBiObjCharFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToCharBiObjCharFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ToCharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ToCharBiObjCharFunction<T, U> onlySecond(@Nonnull final ToCharFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsChar(u);
    }

    /**
     * Creates a {@link ToCharBiObjCharFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link CharUnaryOperator}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToCharBiObjCharFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ToCharBiObjCharFunction<T, U> onlyThird(@Nonnull final CharUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsChar(value);
    }

    /**
     * Applies this function to the given arguments.
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
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToCharBiObjCharFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code ToCharBiObjCharFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code char}.
     * @see #andThen(CharUnaryOperator)
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default <A, B> ToCharBiObjCharFunction<A, B> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> applyAsChar(before1.apply(a), before2.apply(b), before3.applyAsChar(value));
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToCharTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(CharUnaryOperator)
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default <A, B, C> ToCharTriFunction<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final ToCharFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsChar(before1.apply(a), before2.apply(b), before3.applyAsChar(c));
    }

    /**
     * Returns a composed {@link ToCharBiObjCharFunction} that first applies this function to its input, and then
     * applies the {@code after} operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param after The operation to apply after this function is applied
     * @return A composed {@code ToCharBiObjCharFunction} that first applies this function to its input, and then
     * applies the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is a primitive specialization of {@link TriFunction}. Therefore the returned
     * operation handles primitive types. In this case this is {@code char}.
     * @see #compose(Function, Function, CharUnaryOperator)
     * @see #compose(Function, Function, ToCharFunction)
     */
    @Nonnull
    default ToCharBiObjCharFunction<T, U> andThen(@Nonnull final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsChar(applyAsChar(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned function is able to handle every type.
     * @see #compose(Function, Function, CharUnaryOperator)
     * @see #compose(Function, Function, ToCharFunction)
     */
    @Nonnull
    default <S> BiObjCharFunction<T, U, S> andThen(@Nonnull final CharFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsChar(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjCharConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@link CharConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjCharConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjCharConsumer<T, U> consume(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsChar(t, u, value));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjCharFunction}. Thereby the primitive input
     * argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjCharFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Character, Character> boxed() {
        return this::applyAsChar;
    }
}
