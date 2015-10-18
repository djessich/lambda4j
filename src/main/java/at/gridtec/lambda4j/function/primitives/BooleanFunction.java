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

import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;

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
 * Represents a function that accepts a boolean-valued argument and produces a result. This is the {@code
 * boolean}-consuming primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(boolean)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanFunction<R> {

    /**
     * Creates a {@link BooleanFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code BooleanFunction} which always returns a given value.
     */
    @Nonnull
    static <R> BooleanFunction<R> constant(R r) {
        return value -> r;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(boolean value);

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
     * Returns a composed {@link BooleanFunction} that first applies the {@code before} operation to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param before The operation to apply before this function is applied
     * @return A composed {@link BooleanFunction} that first applies the {@code before} operation to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is the primitive specialization of {@link UnaryOperator}. Therefore
     * the given operation handles primitive types. In this case this is {@code boolean}.
     */
    @Nonnull
    default BooleanFunction<R> compose(@Nonnull final BooleanUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsBoolean(value));
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
    default <T> Function<T, R> compose(@Nonnull final Predicate<? super T> before) {
        Objects.requireNonNull(before);
        return value -> apply(before.test(value));
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link BooleanUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is the primitive specialization of {@link UnaryOperator}. Therefore the
     * returned operation handles primitive types. In this case this is {@code boolean}.
     * @see #compose(BooleanUnaryOperator)
     * @see #compose(Predicate)
     */
    @Nonnull
    default BooleanUnaryOperator andThen(@Nonnull final Predicate<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.test(apply(value));
    }

    /**
     * Returns a composed {@link BooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@link BooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned function is able to handle every type.
     * @see #compose(BooleanUnaryOperator)
     * @see #compose(Predicate)
     */
    @Nonnull
    default <S> BooleanFunction<S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code byte}, using the {@code boolean}-to-{@code byte} primitive specialization of {@link
     * Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link BooleanToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToByteFunction toByte(@Nonnull final ToByteFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code char}, using the {@code boolean}-to-{@code char} primitive specialization of {@link
     * Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link BooleanToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToCharFunction toChar(@Nonnull final ToCharFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this function to {@code double}, using the {@code boolean}-to-{@code double} primitive specialization
     * of {@link Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link BooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToDoubleFunction toDouble(@Nonnull final ToDoubleFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code float}, using the {@code boolean}-to-{@code float} primitive specialization of {@link
     * Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link BooleanToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToFloatFunction toFloat(@Nonnull final ToFloatFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code int}, using the {@code boolean}-to-{@code int} primitive specialization of {@link Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link BooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToIntFunction toInt(@Nonnull final ToIntFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code long}, using the {@code boolean}-to-{@code long} primitive specialization of {@link
     * Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link BooleanToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToLongFunction toLong(@Nonnull final ToLongFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * function to {@code short}, using the {@code boolean}-to-{@code short} primitive specialization of {@link
     * Function}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@link BooleanToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToShortFunction toShort(@Nonnull final ToShortFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(apply(value));
    }

    /**
     * Returns a composed {@link BooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@link BooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanConsumer consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(apply(value));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link BooleanFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BooleanFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code BooleanFunction}.
     */
    @Nonnull
    default Function<Boolean, R> boxed() {
        return this::apply;
    }
}