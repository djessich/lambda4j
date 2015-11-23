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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts an object-valued and a {@code int}-valued argument, and produces a result. This is
 * the {@code (reference, int)} specialization of {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, int)}.
 *
 * @param <T> The type of argument to the function
 * @param <R> The type of return value from the function
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjIntFunction<T, R> {

    /**
     * Calls the given {@link ObjIntFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjIntFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static <T, R> R call(@Nonnull final ObjIntFunction<? super T, ? extends R> function, T t, int value) {
        Objects.requireNonNull(function);
        return function.apply(t, value);
    }

    /**
     * Creates a {@link ObjIntFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link Function}.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjIntFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, R> ObjIntFunction<T, R> onlyFirst(@Nonnull final Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.apply(t);
    }

    /**
     * Creates a {@link ObjIntFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link IntFunction}.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjIntFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@code IntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, R> ObjIntFunction<T, R> onlySecond(@Nonnull final IntFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.apply(value);
    }

    /**
     * Creates a {@link ObjIntFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ObjIntFunction} which always returns a given value.
     */
    @Nonnull
    static <T, R> ObjIntFunction<T, R> constant(R r) {
        return (t, value) -> r;
    }

    /**
     * Performs this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(T t, int value);

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
     * Returns a composed {@link ObjIntFunction} that first applies the {@code before} operations to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code ObjIntFunction} that first applies the {@code before} operations to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code int}.
     * @see #andThen(Function)
     */
    @Nonnull
    default <U> ObjIntFunction<U, R> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final IntUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> apply(before1.apply(u), before2.applyAsInt(v));
    }

    /**
     * Returns a composed {@link BiFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(Function)
     */
    @Nonnull
    default <U, V> BiFunction<U, V, R> compose(@Nonnull final Function<? super U, ? extends T> before1,
            @Nonnull final ToIntFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> apply(before1.apply(u), before2.applyAsInt(v));
    }

    /**
     * Returns a composed {@link ObjIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, IntUnaryOperator)
     * @see #compose(Function, ToIntFunction)
     */
    @Nonnull
    default <S> ObjIntFunction<T, S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(apply(t, value));
    }

    /**
     * Returns a composed {@link ObjIntConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjIntConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjIntConsumer<T> consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(apply(t, value));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 1};
     *
     * @param t The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default IntFunction<R> partial(T t) {
        return value -> apply(t, value);
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 1};
     *
     * @param value The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default Function<T, R> partial(int value) {
        return t -> apply(t, value);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 0}.
     *
     * @param t The first argument to partially apply to the function
     * @param value The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default Supplier<R> partial(T t, int value) {
        return () -> apply(t, value);
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     */
    @Nonnull
    default ObjIntFunction<T, Optional<R>> nonNull() {
        return (t, value) -> Optional.ofNullable(apply(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjIntFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjIntFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjIntFunction}.
     */
    @Nonnull
    default BiFunction<T, Integer, R> boxed() {
        return this::apply;
    }
}
