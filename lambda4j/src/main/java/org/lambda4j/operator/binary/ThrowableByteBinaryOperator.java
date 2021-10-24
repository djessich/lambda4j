/*
 * Copyright (c) 2021 The lambda4j authors.
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

package org.lambda4j.operator.binary;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableByteConsumer;
import org.lambda4j.consumer.bi.ThrowableBiByteConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableByteFunction;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.bi.ThrowableBiByteFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiBooleanToByteFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiByteToCharFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiByteToDoubleFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiByteToFloatFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiByteToIntFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiByteToLongFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiByteToShortFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiCharToByteFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiDoubleToByteFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiFloatToByteFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiIntToByteFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiLongToByteFunction;
import org.lambda4j.function.bi.conversion.ThrowableBiShortToByteFunction;
import org.lambda4j.function.bi.to.ThrowableToByteBiFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToByteFunction;
import org.lambda4j.function.conversion.ThrowableByteToCharFunction;
import org.lambda4j.function.conversion.ThrowableByteToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableByteToFloatFunction;
import org.lambda4j.function.conversion.ThrowableByteToIntFunction;
import org.lambda4j.function.conversion.ThrowableByteToLongFunction;
import org.lambda4j.function.conversion.ThrowableByteToShortFunction;
import org.lambda4j.function.conversion.ThrowableCharToByteFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToByteFunction;
import org.lambda4j.function.conversion.ThrowableFloatToByteFunction;
import org.lambda4j.function.conversion.ThrowableIntToByteFunction;
import org.lambda4j.function.conversion.ThrowableLongToByteFunction;
import org.lambda4j.function.conversion.ThrowableShortToByteFunction;
import org.lambda4j.function.to.ThrowableToByteFunction;
import org.lambda4j.operator.unary.ThrowableByteUnaryOperator;
import org.lambda4j.predicate.ThrowableBytePredicate;
import org.lambda4j.predicate.bi.ThrowableBiBytePredicate;

/**
 * Represents an operation that accepts two {@code byte}-valued input arguments and produces a {@code byte}-valued
 * result which is able to throw any {@link Throwable}. This is a primitive specialization of {@link
 * ThrowableBinaryOperator}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByteThrows(byte, byte)}.
 *
 * @param <X> The type of the throwable to be thrown by this operator
 * @see ThrowableBinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableByteBinaryOperator<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableByteBinaryOperator} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableByteBinaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <X extends Throwable> ThrowableByteBinaryOperator<X> of(
            @Nullable ThrowableByteBinaryOperator<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableByteBinaryOperator} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @return The result from the given {@code ThrowableByteBinaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this operators action
     */
    static <X extends Throwable> byte call(@Nonnull ThrowableByteBinaryOperator<? extends X> operator,
            byte value1, byte value2) throws X {
        Objects.requireNonNull(operator);
        return operator.applyAsByteThrows(value1, value2);
    }

    /**
     * Creates a {@link ThrowableByteBinaryOperator} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowableByteUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableByteBinaryOperator} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableByteUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableByteBinaryOperator<X> onlyFirst(
            @Nonnull ThrowableByteUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsByteThrows(value1);
    }

    /**
     * Creates a {@link ThrowableByteBinaryOperator} which uses the {@code second} parameter of this one as argument for
     * the given {@link ThrowableByteUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableByteBinaryOperator} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableByteUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableByteBinaryOperator<X> onlySecond(
            @Nonnull ThrowableByteUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsByteThrows(value2);
    }

    /**
     * Creates a {@link ThrowableByteBinaryOperator} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param ret The return value for the constant
     * @return A {@code ThrowableByteBinaryOperator} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableByteBinaryOperator<X> constant(byte ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link ThrowableByteBinaryOperator} which returns the lesser of two elements according to the specified
     * {@code Comparator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code ThrowableByteBinaryOperator} which returns the lesser of its operands, according to the supplied
     * {@code Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static <X extends Throwable> ThrowableByteBinaryOperator<X> minBy(@Nonnull Comparator<Byte> comparator) {
        Objects.requireNonNull(comparator);
        return (value1, value2) -> comparator.compare(value1, value2) <= 0 ? value1 : value2;
    }

    /**
     * Returns a {@link ThrowableByteBinaryOperator} which returns the greater of two elements according to the
     * specified {@code Comparator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code ThrowableByteBinaryOperator} which returns the greater of its operands, according to the
     * supplied {@code Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static <X extends Throwable> ThrowableByteBinaryOperator<X> maxBy(@Nonnull Comparator<Byte> comparator) {
        Objects.requireNonNull(comparator);
        return (value1, value2) -> comparator.compare(value1, value2) >= 0 ? value1 : value2;
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @return The return value from the operator, which is its result.
     * @throws X Any throwable from this operators action
     */
    byte applyAsByteThrows(byte value1, byte value2) throws X;

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowableByteUnaryOperator} as
     * result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @return A {@code ThrowableByteUnaryOperator} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default ThrowableByteUnaryOperator<X> applyAsByteThrowsPartially(byte value1) {
        return value2 -> applyAsByteThrows(value1, value2);
    }

    /**
     * Returns the number of arguments for this operator.
     *
     * @return The number of arguments for this operator.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ThrowableToByteBiFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableToByteBiFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ThrowableToByteBiFunction<A, B, X> compose(
            @Nonnull ThrowableToByteFunction<? super A, ? extends X> before1,
            @Nonnull ThrowableToByteFunction<? super B, ? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsByteThrows(before1.applyAsByteThrows(a), before2.applyAsByteThrows(b));
    }

    /**
     * Returns a composed {@link ThrowableBiBooleanToByteFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiBooleanToByteFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiBooleanToByteFunction<X> composeFromBoolean(
            @Nonnull ThrowableBooleanToByteFunction<? extends X> before1,
            @Nonnull ThrowableBooleanToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableByteBinaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @return A composed {@code ThrowableByteBinaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteBinaryOperator<X> composeFromByte(
            @Nonnull ThrowableByteUnaryOperator<? extends X> before1,
            @Nonnull ThrowableByteUnaryOperator<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiCharToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiCharToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiCharToByteFunction<X> composeFromChar(
            @Nonnull ThrowableCharToByteFunction<? extends X> before1,
            @Nonnull ThrowableCharToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiDoubleToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiDoubleToByteFunction<X> composeFromDouble(
            @Nonnull ThrowableDoubleToByteFunction<? extends X> before1,
            @Nonnull ThrowableDoubleToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiFloatToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiFloatToByteFunction<X> composeFromFloat(
            @Nonnull ThrowableFloatToByteFunction<? extends X> before1,
            @Nonnull ThrowableFloatToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiIntToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiIntToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiIntToByteFunction<X> composeFromInt(
            @Nonnull ThrowableIntToByteFunction<? extends X> before1,
            @Nonnull ThrowableIntToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiLongToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiLongToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiLongToByteFunction<X> composeFromLong(
            @Nonnull ThrowableLongToByteFunction<? extends X> before1,
            @Nonnull ThrowableLongToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiShortToByteFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiShortToByteFunction<X> composeFromShort(
            @Nonnull ThrowableShortToByteFunction<? extends X> before1,
            @Nonnull ThrowableShortToByteFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsByteThrows(before1.applyAsByteThrows(value1),
                before2.applyAsByteThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBiByteFunction<S, X> andThen(
            @Nonnull ThrowableByteFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyThrows(applyAsByteThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiBytePredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code ThrowableBiBytePredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiBytePredicate<X> andThenToBoolean(@Nonnull ThrowableBytePredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.testThrows(applyAsByteThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableByteBinaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ThrowableByteBinaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteBinaryOperator<X> andThenToByte(@Nonnull ThrowableByteUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByteThrows(applyAsByteThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiByteToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiByteToCharFunction<X> andThenToChar(
            @Nonnull ThrowableByteToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsCharThrows(applyAsByteThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiByteToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiByteToDoubleFunction<X> andThenToDouble(
            @Nonnull ThrowableByteToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDoubleThrows(applyAsByteThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiByteToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiByteToFloatFunction<X> andThenToFloat(
            @Nonnull ThrowableByteToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloatThrows(applyAsByteThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiByteToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiByteToIntFunction<X> andThenToInt(@Nonnull ThrowableByteToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsIntThrows(applyAsByteThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiByteToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiByteToLongFunction<X> andThenToLong(
            @Nonnull ThrowableByteToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLongThrows(applyAsByteThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiByteToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiByteToShortFunction<X> andThenToShort(
            @Nonnull ThrowableByteToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShortThrows(applyAsByteThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteConsumer} that fist applies this operator to its input, and then
     * consumes the result using the given {@link ThrowableByteConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiByteConsumer} that first applies this operator to its input, and then
     * consumes the result using the given {@code ThrowableByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiByteConsumer<X> consume(@Nonnull ThrowableByteConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.acceptThrows(applyAsByteThrows(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableByteBinaryOperator}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableByteBinaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableByteBinaryOperator<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<Byte, Byte>, Byte> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ThrowableByteBinaryOperator<X> & Memoized) (value1, value2) -> {
                byte returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2), ThrowableFunction.of(
                            key -> applyAsByteThrows(key.getLeft(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableBinaryOperator} which represents this {@link ThrowableByteBinaryOperator}.
     * Thereby the primitive input argument for this operator is autoboxed. This method provides the possibility to use
     * this {@code ThrowableByteBinaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableBinaryOperator} which represents this {@code ThrowableByteBinaryOperator}.
     */
    @Nonnull
    default ThrowableBinaryOperator<Byte, X> boxed() {
        return this::applyAsByteThrows;
    }

    /**
     * Returns a composed {@link ByteBinaryOperator} that applies this operator to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link ByteBinaryOperator} that applies this operator to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ByteBinaryOperator nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ByteBinaryOperator} that applies this operator to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ByteBinaryOperator} that applies this operator to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ByteBinaryOperator nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ByteBinaryOperator} that first applies this operator to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * operator.
     *
     * @param recover The operation to apply if this operator throws a {@code Throwable}
     * @return A composed {@link ByteBinaryOperator} that first applies this operator to its input, and then applies the
     * {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing operator is {@code null}
     * @implSpec The implementation checks that the returned enclosing operator from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ByteBinaryOperator recover(
            @Nonnull Function<? super Throwable, ? extends ByteBinaryOperator> recover) {
        Objects.requireNonNull(recover);
        return (value1, value2) -> {
            try {
                return applyAsByteThrows(value1, value2);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                ByteBinaryOperator operator = recover.apply(throwable);
                Objects.requireNonNull(operator, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return operator.applyAsByte(value1, value2);
            }
        };
    }

    /**
     * Returns a composed {@link ByteBinaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed operator behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this operator in the returned composed
     * operator by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing operator.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing operator variant of this throwable operator, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed operator. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed operator. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed operator is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed operator, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed operator.
     * <p>
     * With all that mentioned, the following example will demonstrate this methods correct use:
     * <pre>{@code
     * // when called with illegal value ClassNotFoundException is thrown
     * public Class<?> sneakyThrowingFunctionalInterface(final String className) throws ClassNotFoundException {
     *     return ThrowableFunction.of(Class::forName) // create the correct throwable functional interface
     *                .sneakyThrow() // create a non-throwable variant which is able to sneaky throw (this method)
     *                .apply(className); // apply non-throwable variant -> may sneaky throw a throwable
     * }
     *
     * // call the the method which surround the sneaky throwing functional interface
     * public void callingMethod() {
     *     try {
     *         final Class<?> clazz = sneakyThrowingFunctionalInterface("some illegal class name");
     *         // ... do something with clazz ...
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link ByteBinaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ByteBinaryOperator sneakyThrow() {
        return (value1, value2) -> {
            try {
                return applyAsByteThrows(value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
