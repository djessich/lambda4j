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
package at.gridtec.lambda4j.function.primitives;

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToLongFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts a short-valued argument and produces a result. This is the {@code short}-consuming
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(short)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortFunction<R> {

    /**
     * Creates a {@link ShortFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ShortFunction} which always returns a given value.
     */
    @Nonnull
    static <R> ShortFunction<R> constant(R r) {
        return value -> r;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(short value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ShortFunction} that first applies the {@code before} operation to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param before The operation to apply before this function is applied
     * @return A composed {@link ShortFunction} that first applies the {@code before} operation to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is the primitive specialization of {@link UnaryOperator}. Therefore
     * the given operation handles primitive types. In this case this is {@code short}.
     */
    @Nonnull
    default ShortFunction<R> compose(@Nonnull final ShortUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link Function} that first applies the {@code before} function to its input, and then applies
     * this function to the result. If evaluation of either function throws an exception, it is relayed to the caller of
     * the composed function.
     *
     * @param <T> The type of the argument to the before function
     * @param before The first function to apply before this function is applied
     * @return A omposed {@link Function} that first applies the {@code before} function to its input, and then applies
     * this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <T> Function<T, R> compose(@Nonnull final ToShortFunction<? super T> before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link ShortUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is the primitive specialization of {@link UnaryOperator}. Therefore the
     * returned operation handles primitive types. In this case this is {@code short}.
     * @see #compose(ShortUnaryOperator)
     * @see #compose(ToShortFunction)
     */
    @Nonnull
    default ShortUnaryOperator andThen(@Nonnull final ToShortFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(apply(value));
    }

    /**
     * Returns a composed {@link ShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@link ShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned function is able to handle every type.
     * @see #compose(ShortUnaryOperator)
     * @see #compose(ToShortFunction)
     */
    @Nonnull
    default <S> ShortFunction<S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(apply(value));
    }

    /**
     * Returns a composed {@link ShortToBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code boolean}, using the {@code short}-to-{@code boolean} primitive specialization of {@link
     * Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link ShortToBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortToBooleanFunction toBoolean(@Nonnull final Predicate<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.test(apply(value));
    }

    /**
     * Returns a composed {@link ShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code byte}, using the {@code short}-to-{@code byte} primitive specialization of {@link Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link ShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortToByteFunction toByte(@Nonnull final ToByteFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(apply(value));
    }

    /**
     * Returns a composed {@link ShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code char}, using the {@code short}-to-{@code char} primitive specialization of {@link Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link ShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortToCharFunction toChar(@Nonnull final ToCharFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(apply(value));
    }

    /**
     * Returns a composed {@link ShortToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code double}, using the {@code short}-to-{@code double} primitive specialization of {@link
     * Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link ShortToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortToDoubleFunction toDouble(@Nonnull final ToDoubleFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(apply(value));
    }

    /**
     * Returns a composed {@link ShortToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code float}, using the {@code short}-to-{@code float} primitive specialization of {@link
     * Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link ShortToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortToFloatFunction toFloat(@Nonnull final ToFloatFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(apply(value));
    }

    /**
     * Returns a composed {@link ShortToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code int}, using the {@code short}-to-{@code int} primitive specialization of {@link Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link ShortToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortToIntFunction toInt(@Nonnull final ToIntFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(apply(value));
    }

    /**
     * Returns a composed {@link ShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code long}, using the {@code short}-to-{@code long} primitive specialization of {@link Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link ShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortToLongFunction toLong(@Nonnull final ToLongFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(apply(value));
    }

    /**
     * Returns a composed {@link ShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@link ShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortConsumer consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(apply(value));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link ShortFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code ShortFunction}.
     */
    @Nonnull
    default Function<Short, R> boxed() {
        return this::apply;
    }
}