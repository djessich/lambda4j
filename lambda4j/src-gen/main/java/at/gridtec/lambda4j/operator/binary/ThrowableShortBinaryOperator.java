/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.operator.binary;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableShortConsumer;
import at.gridtec.lambda4j.consumer.bi.ThrowableBiShortConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.ThrowableShortFunction;
import at.gridtec.lambda4j.function.bi.ThrowableBiShortFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiBooleanToShortFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiByteToShortFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiCharToShortFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiDoubleToShortFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiFloatToShortFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiIntToShortFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiLongToShortFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiShortToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiShortToCharFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiShortToDoubleFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiShortToFloatFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiShortToIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.ThrowableBiShortToLongFunction;
import at.gridtec.lambda4j.function.bi.to.ThrowableToShortBiFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableByteToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableLongToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToIntFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToLongFunction;
import at.gridtec.lambda4j.function.to.ThrowableToShortFunction;
import at.gridtec.lambda4j.operator.unary.ThrowableShortUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableShortPredicate;
import at.gridtec.lambda4j.predicate.bi.ThrowableBiShortPredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Represents an operation that accepts two {@code short}-valued input arguments and produces a
 * {@code short}-valued result which is able to throw any {@link Throwable}.
 * This is a primitive specialization of {@link ThrowableBinaryOperator}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShortThrows(short, short)}.
 *
 * @param <X> The type of the throwable to be thrown by this operator
 * @see ThrowableBinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableShortBinaryOperator<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableShortBinaryOperator} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableShortBinaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableShortBinaryOperator<X> of(
            @Nullable final ThrowableShortBinaryOperator<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableShortBinaryOperator} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @return The result from the given {@code ThrowableShortBinaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this operators action
     */
    static <X extends Throwable> short call(@Nonnull final ThrowableShortBinaryOperator<? extends X> operator,
            short value1, short value2) throws X {
        Objects.requireNonNull(operator);
        return operator.applyAsShortThrows(value1, value2);
    }

    /**
     * Creates a {@link ThrowableShortBinaryOperator} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowableShortUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableShortBinaryOperator} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableShortUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableShortBinaryOperator<X> onlyFirst(
            @Nonnull final ThrowableShortUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsShortThrows(value1);
    }

    /**
     * Creates a {@link ThrowableShortBinaryOperator} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableShortUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableShortBinaryOperator} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableShortUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableShortBinaryOperator<X> onlySecond(
            @Nonnull final ThrowableShortUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsShortThrows(value2);
    }

    /**
     * Creates a {@link ThrowableShortBinaryOperator} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param ret The return value for the constant
     * @return A {@code ThrowableShortBinaryOperator} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableShortBinaryOperator<X> constant(short ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link ThrowableShortBinaryOperator} which returns the lesser of two elements according to the
     * specified {@code Comparator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code ThrowableShortBinaryOperator} which returns the lesser of its operands, according to the
     * supplied {@code Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static <X extends Throwable> ThrowableShortBinaryOperator<X> minBy(@Nonnull final Comparator<Short> comparator) {
        Objects.requireNonNull(comparator);
        return (value1, value2) -> comparator.compare(value1, value2) <= 0 ? value1 : value2;
    }

    /**
     * Returns a {@link ThrowableShortBinaryOperator} which returns the greater of two elements according to the
     * specified {@code Comparator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code ThrowableShortBinaryOperator} which returns the greater of its operands, according to the
     * supplied {@code Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static <X extends Throwable> ThrowableShortBinaryOperator<X> maxBy(@Nonnull final Comparator<Short> comparator) {
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
    short applyAsShortThrows(short value1, short value2) throws X;

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
     * Returns a composed {@link ThrowableToShortBiFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableToShortBiFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ThrowableToShortBiFunction<A, B, X> compose(
            @Nonnull final ThrowableToShortFunction<? super A, ? extends X> before1,
            @Nonnull final ThrowableToShortFunction<? super B, ? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsShortThrows(before1.applyAsShortThrows(a), before2.applyAsShortThrows(b));
    }

    /**
     * Returns a composed {@link ThrowableBiBooleanToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiBooleanToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiBooleanToShortFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToShortFunction<? extends X> before1,
            @Nonnull final ThrowableBooleanToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                                                      before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiByteToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiByteToShortFunction<X> composeFromByte(
            @Nonnull final ThrowableByteToShortFunction<? extends X> before1,
            @Nonnull final ThrowableByteToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                                                      before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiCharToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiCharToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiCharToShortFunction<X> composeFromChar(
            @Nonnull final ThrowableCharToShortFunction<? extends X> before1,
            @Nonnull final ThrowableCharToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                                                      before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiDoubleToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiDoubleToShortFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoubleToShortFunction<? extends X> before1,
            @Nonnull final ThrowableDoubleToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                                                      before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiFloatToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiFloatToShortFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatToShortFunction<? extends X> before1,
            @Nonnull final ThrowableFloatToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                                                      before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiIntToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiIntToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiIntToShortFunction<X> composeFromInt(
            @Nonnull final ThrowableIntToShortFunction<? extends X> before1,
            @Nonnull final ThrowableIntToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                                                      before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiLongToShortFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ThrowableBiLongToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiLongToShortFunction<X> composeFromLong(
            @Nonnull final ThrowableLongToShortFunction<? extends X> before1,
            @Nonnull final ThrowableLongToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                                                      before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableShortBinaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @return A composed {@code ThrowableShortBinaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortBinaryOperator<X> composeFromShort(
            @Nonnull final ThrowableShortUnaryOperator<? extends X> before1,
            @Nonnull final ThrowableShortUnaryOperator<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsShortThrows(before1.applyAsShortThrows(value1),
                                                      before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBiShortFunction<S, X> andThen(
            @Nonnull final ThrowableShortFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyThrows(applyAsShortThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortPredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code ThrowableBiShortPredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiShortPredicate<X> andThenToBoolean(@Nonnull final ThrowableShortPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.testThrows(applyAsShortThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiShortToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiShortToByteFunction<X> andThenToByte(
            @Nonnull final ThrowableShortToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByteThrows(applyAsShortThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiShortToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiShortToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableShortToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsCharThrows(applyAsShortThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiShortToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiShortToDoubleFunction<X> andThenToDouble(
            @Nonnull final ThrowableShortToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDoubleThrows(applyAsShortThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToFloatFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiShortToFloatFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiShortToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableShortToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloatThrows(applyAsShortThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiShortToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiShortToIntFunction<X> andThenToInt(
            @Nonnull final ThrowableShortToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsIntThrows(applyAsShortThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableBiShortToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiShortToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableShortToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLongThrows(applyAsShortThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableShortBinaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ThrowableShortBinaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortBinaryOperator<X> andThenToShort(
            @Nonnull final ThrowableShortUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShortThrows(applyAsShortThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortConsumer} that fist applies this operator to its input, and then
     * consumes the result using the given {@link ThrowableShortConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiShortConsumer} that first applies this operator to its input, and then
     * consumes the result using the given {@code ThrowableShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiShortConsumer<X> consume(@Nonnull final ThrowableShortConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.acceptThrows(applyAsShortThrows(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableShortBinaryOperator}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableShortBinaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableShortBinaryOperator<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<Short, Short>, Short> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableShortBinaryOperator<X> & Memoized) (value1, value2) -> {
                final short returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2), ThrowableFunction.of(
                            key -> applyAsShortThrows(key.getLeft(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableBinaryOperator} which represents this {@link ThrowableShortBinaryOperator}.
     * Thereby the primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code ThrowableBinaryOperator} which represents this {@code ThrowableShortBinaryOperator}.
     */
    @Nonnull
    default ThrowableBinaryOperator<Short, X> boxed() {
        return this::applyAsShortThrows;
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that applies this operator to its input and nests the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is
     * nested (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown
     * throwables message and the thrown throwable itself.
     *
     * @return A composed {@code ShortBinaryOperator} that applies this operator to its input and nests the thrown
     * {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default ShortBinaryOperator nest() {
        return (value1, value2) -> {
            try {
                return this.applyAsShortThrows(value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
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
     * @return A composed {@link ShortBinaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default ShortBinaryOperator sneakyThrow() {
        return (value1, value2) -> {
            try {
                return this.applyAsShortThrows(value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * operator.
     *
     * @param recover The operation to apply if this operator throws a {@code Throwable}
     * @return A composed {@link ShortBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code recover} operation if a {@link Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing operator is {@code null}
     * @implNote The implementation checks that the returned enclosing operator from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     */
    @Nonnull
    default ShortBinaryOperator recover(
            @Nonnull final Function<? super Throwable, ? extends ShortBinaryOperator> recover) {
        Objects.requireNonNull(recover);
        return (value1, value2) -> {
            try {
                return this.applyAsShortThrows(value1, value2);
            } catch (Throwable throwable) {
                final ShortBinaryOperator operator = recover.apply(throwable);
                Objects.requireNonNull(operator, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return operator.applyAsShort(value1, value2);
            }
        };
    }

}