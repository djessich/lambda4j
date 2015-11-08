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

import at.gridtec.lambda4j.consumer.primitives.obj.BiObjIntConsumer;
import at.gridtec.lambda4j.function.TriFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two object-valued and a {@code int}-valued argument, and produces a result. This
 * is the {@code (reference, reference, int)} specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, Object, int)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjIntFunction<T, U, R> {

    /**
     * Calls the given {@link BiObjIntFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static <T, U, R> R call(@Nonnull final BiObjIntFunction<? super T, ? super U, ? extends R> function, final T t,
            final U u, int value) {
        Objects.requireNonNull(function);
        return function.apply(t, u, value);
    }

    /**
     * Creates a {@link BiObjIntFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjIntFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjIntFunction<T, U, R> onlyFirst(@Nonnull final Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(t);
    }

    /**
     * Creates a {@link BiObjIntFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjIntFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjIntFunction<T, U, R> onlySecond(@Nonnull final Function<? super U, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(u);
    }

    /**
     * Creates a {@link BiObjIntFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@link IntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjIntFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@code IntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjIntFunction<T, U, R> onlyThird(@Nonnull final IntFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(value);
    }

    /**
     * Creates a {@link BiObjIntFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code BiObjIntFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, R> BiObjIntFunction<T, U, R> constant(R r) {
        return (t, u, value) -> r;
    }

    /**
     * Performs this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(T t, U u, int value);

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
     * Returns a composed {@link BiObjIntFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code BiObjIntFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code int}.
     * @see #andThen(Function)
     */
    @Nonnull
    default <A, B> BiObjIntFunction<A, B, R> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final IntUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> apply(before1.apply(a), before2.apply(b), before3.applyAsInt(value));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(Function)
     */
    @Nonnull
    default <A, B, C> TriFunction<A, B, C, R> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final ToIntFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> apply(before1.apply(a), before2.apply(b), before3.applyAsInt(c));
    }

    /**
     * Returns a composed {@link BiObjIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, IntUnaryOperator)
     * @see #compose(Function, Function, ToIntFunction)
     */
    @Nonnull
    default <S> BiObjIntFunction<T, U, S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(apply(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjIntConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjIntConsumer<T, U> consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(apply(t, u, value));
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     */
    @Nonnull
    default BiObjIntFunction<T, U, Optional<R>> nonNull() {
        return (t, u, value) -> Optional.ofNullable(apply(t, u, value));
    }
    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjIntFunction}. Thereby the primitive input
     * argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjIntFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Integer, R> boxed() {
        return this::apply;
    }
}
