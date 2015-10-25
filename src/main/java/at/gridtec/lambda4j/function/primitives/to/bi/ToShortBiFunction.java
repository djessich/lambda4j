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

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToLongFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

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
 * Represents a function that produces a short-valued result from two arguments. This is the {@code short}-producing
 * primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToShortBiFunction<T, U> {

    /**
     * Creates a {@link ToShortBiFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToShortBiFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U> ToShortBiFunction<T, U> constant(short ret) {
        return (t, u) -> ret;
    }

    /**
     * Creates a {@link ToShortBiFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link ToShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToShortBiFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code ToShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ToShortBiFunction<T, U> onlyFirst(@Nonnull final ToShortFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u) -> function.applyAsShort(t);
    }

    /**
     * Creates a {@link ToShortBiFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link ToShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToShortBiFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code ToShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ToShortBiFunction<T, U> onlySecond(@Nonnull final ToShortFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u) -> function.applyAsShort(u);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     */
    short applyAsShort(T t, U u);

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
     * Returns a composed {@link ToShortBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this operation is applied
     * @param before2 The second operation to apply before this operation is applied
     * @return A composed {@code ToShortBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code short}.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToShortBiFunction<T, U> compose(@Nonnull final UnaryOperator<T> before1,
            @Nonnull final UnaryOperator<U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsShort(before1.apply(t), before2.apply(u));
    }

    /**
     * Returns a composed {@link ToShortBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before function
     * @param <B> The type of the argument to the second before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToShortBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default <A, B> ToShortBiFunction<A, B> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsShort(before1.apply(a), before2.apply(b));
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
    default <R> BiFunction<T, U, R> andThen(@Nonnull final ShortFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.apply(applyAsShort(t, u));
    }

    /**
     * Returns a composed {@link BiPredicate} that first applies this function to its input, and then applies the {@code
     * after} function to the result. If evaluation of either function throws an exception, it is relayed to the caller
     * of the composed function.
     * <p>
     * This method is just convenience, to provide the ability to transform this function to an equal function,
     * returning {@code boolean}. Thereby the {@code short}-to-{@code boolean} primitive specialization of {@link
     * Function} is used.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiPredicate} that first applies this function to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    default BiPredicate<T, U> andThenToBoolean(final ShortToBooleanFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsBoolean(applyAsShort(t, u));
    }

    /**
     * Returns a composed {@link ToByteBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * <p>
     * This method is just convenience, to provide the ability to transform this function to an equal function,
     * returning {@code byte}. Thereby the {@code short}-to-{@code byte} primitive specialization of {@link Function} is
     * used.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToByteBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToByteBiFunction<T, U> andThenToByte(@Nonnull final ShortToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsByte(applyAsShort(t, u));
    }

    /**
     * Returns a composed {@link ToCharBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * <p>
     * This method is just convenience, to provide the ability to transform this function to an equal function,
     * returning {@code char}. Thereby the {@code short}-to-{@code char} primitive specialization of {@link Function} is
     * used.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToCharBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToCharBiFunction<T, U> andThenToChar(@Nonnull final ShortToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsChar(applyAsShort(t, u));
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * <p>
     * This method is just convenience, to provide the ability to transform this function to an equal function,
     * returning {@code double}. Thereby the {@code short}-to-{@code double} primitive specialization of {@link
     * Function} is used.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToDoubleBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToDoubleBiFunction<T, U> andThenToDouble(@Nonnull final ShortToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsDouble(applyAsShort(t, u));
    }

    /**
     * Returns a composed {@link ToFloatBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * <p>
     * This method is just convenience, to provide the ability to transform this function to an equal function,
     * returning {@code float}. Thereby the {@code short}-to-{@code float} primitive specialization of {@link Function}
     * is used.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToFloatBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToFloatBiFunction<T, U> andThenToFloat(@Nonnull final ShortToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsFloat(applyAsShort(t, u));
    }

    /**
     * Returns a composed {@link ToIntBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * <p>
     * This method is just convenience, to provide the ability to transform this function to an equal function,
     * returning {@code int}. Thereby the {@code short}-to-{@code int} primitive specialization of {@link Function} is
     * used.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToIntBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToIntBiFunction<T, U> andThenToInt(@Nonnull final ShortToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsInt(applyAsShort(t, u));
    }

    /**
     * Returns a composed {@link ToLongBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * <p>
     * This method is just convenience, to provide the ability to transform this function to an equal function,
     * returning {@code long}. Thereby the {@code short}-to-{@code long} primitive specialization of {@link Function} is
     * used.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToLongBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToLongBiFunction<T, U> andThenToLong(@Nonnull final ShortToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsLong(applyAsShort(t, u));

    }

    /**
     * Returns a composed {@link ToShortBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * <p>
     * This method is just convenience, to provide the ability to transform this function to an equal function,
     * returning {@code short}. Thereby the {@code short}-producing primitive specialization of {@link UnaryOperator} is
     * used.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToShortBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ToShortBiFunction<T, U> andThenToShort(@Nonnull final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsShort(applyAsShort(t, u));
    }

    /**
     * Returns a composed {@link BiConsumer} that fist applies this function to its input, and then consumes the result
     * using the given {@link ShortConsumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiConsumer} that first applies this function to its input, and then consumes the result
     * using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiConsumer<T, U> consume(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.accept(applyAsShort(t, u));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ToShortBiFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ToShortBiFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ToShortBiFunction}.
     */
    @Nonnull
    default BiFunction<T, U, Short> boxed() {
        return this::applyAsShort;
    }
}
