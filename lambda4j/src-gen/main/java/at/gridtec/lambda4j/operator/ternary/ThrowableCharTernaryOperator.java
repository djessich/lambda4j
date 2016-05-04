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
package at.gridtec.lambda4j.operator.ternary;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableCharConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriCharConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableCharFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableByteToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToIntFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToLongFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableLongToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToCharFunction;
import at.gridtec.lambda4j.function.to.ThrowableToCharFunction;
import at.gridtec.lambda4j.function.tri.ThrowableTriCharFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriBooleanToCharFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriByteToCharFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriCharToByteFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriCharToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriCharToFloatFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriCharToIntFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriCharToLongFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriCharToShortFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriDoubleToCharFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriFloatToCharFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriIntToCharFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriLongToCharFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriShortToCharFunction;
import at.gridtec.lambda4j.function.tri.to.ThrowableToCharTriFunction;
import at.gridtec.lambda4j.operator.unary.ThrowableCharUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableCharPredicate;
import at.gridtec.lambda4j.predicate.tri.ThrowableTriCharPredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an operation that accepts three {@code char}-valued input arguments and produces a {@code char}-valued
 * result which is able to throw any {@link Throwable}. This is a primitive specialization of {@link
 * ThrowableTernaryOperator}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsCharThrows(char, char, char)}.
 *
 * @param <X> The type of the throwable to be thrown by this operator
 * @see ThrowableTernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableCharTernaryOperator<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableCharTernaryOperator} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableCharTernaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <X extends Throwable> ThrowableCharTernaryOperator<X> of(
            @Nonnull final ThrowableCharTernaryOperator<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableCharTernaryOperator} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @param value3 The third argument to the operator
     * @return The result from the given {@code ThrowableCharTernaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this operators action
     */
    static <X extends Throwable> char call(@Nonnull final ThrowableCharTernaryOperator<? extends X> operator,
            char value1, char value2, char value3) throws X {
        Objects.requireNonNull(operator);
        return operator.applyAsCharThrows(value1, value2, value3);
    }

    /**
     * Creates a {@link ThrowableCharTernaryOperator} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowableCharUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableCharTernaryOperator} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableCharUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableCharTernaryOperator<X> onlyFirst(
            @Nonnull final ThrowableCharUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsCharThrows(value1);
    }

    /**
     * Creates a {@link ThrowableCharTernaryOperator} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableCharUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableCharTernaryOperator} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableCharUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableCharTernaryOperator<X> onlySecond(
            @Nonnull final ThrowableCharUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsCharThrows(value2);
    }

    /**
     * Creates a {@link ThrowableCharTernaryOperator} which uses the {@code third} parameter of this one as argument for
     * the given {@link ThrowableCharUnaryOperator}.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableCharTernaryOperator} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableCharUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableCharTernaryOperator<X> onlyThird(
            @Nonnull final ThrowableCharUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsCharThrows(value3);
    }

    /**
     * Creates a {@link ThrowableCharTernaryOperator} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param ret The return value for the constant
     * @return A {@code ThrowableCharTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableCharTernaryOperator<X> constant(char ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @param value3 The third argument to the operator
     * @return The return value from the operator, which is its result.
     * @throws X Any throwable from this operators action
     */
    char applyAsCharThrows(char value1, char value2, char value3) throws X;

    /**
     * Returns the number of arguments for this operator.
     *
     * @return The number of arguments for this operator.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ThrowableToCharTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableToCharTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToCharTriFunction<A, B, C, X> compose(
            @Nonnull final ThrowableToCharFunction<? super A, ? extends X> before1,
            @Nonnull final ThrowableToCharFunction<? super B, ? extends X> before2,
            @Nonnull final ThrowableToCharFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsCharThrows(before1.applyAsCharThrows(a), before2.applyAsCharThrows(b),
                                              before3.applyAsCharThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanToCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriBooleanToCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanToCharFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToCharFunction<? extends X> before1,
            @Nonnull final ThrowableBooleanToCharFunction<? extends X> before2,
            @Nonnull final ThrowableBooleanToCharFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyAsCharThrows(value1),
                                                             before2.applyAsCharThrows(value2),
                                                             before3.applyAsCharThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriByteToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteToCharFunction<X> composeFromByte(
            @Nonnull final ThrowableByteToCharFunction<? extends X> before1,
            @Nonnull final ThrowableByteToCharFunction<? extends X> before2,
            @Nonnull final ThrowableByteToCharFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyAsCharThrows(value1),
                                                             before2.applyAsCharThrows(value2),
                                                             before3.applyAsCharThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableCharTernaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@code ThrowableCharTernaryOperator} that first applies the {@code before} operators to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharTernaryOperator<X> composeFromChar(
            @Nonnull final ThrowableCharUnaryOperator<? extends X> before1,
            @Nonnull final ThrowableCharUnaryOperator<? extends X> before2,
            @Nonnull final ThrowableCharUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyAsCharThrows(value1),
                                                             before2.applyAsCharThrows(value2),
                                                             before3.applyAsCharThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleToCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result. This method is just convenience, to provide the ability
     * to execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriDoubleToCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleToCharFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoubleToCharFunction<? extends X> before1,
            @Nonnull final ThrowableDoubleToCharFunction<? extends X> before2,
            @Nonnull final ThrowableDoubleToCharFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyAsCharThrows(value1),
                                                             before2.applyAsCharThrows(value2),
                                                             before3.applyAsCharThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriFloatToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatToCharFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatToCharFunction<? extends X> before1,
            @Nonnull final ThrowableFloatToCharFunction<? extends X> before2,
            @Nonnull final ThrowableFloatToCharFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyAsCharThrows(value1),
                                                             before2.applyAsCharThrows(value2),
                                                             before3.applyAsCharThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriIntToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntToCharFunction<X> composeFromInt(
            @Nonnull final ThrowableIntToCharFunction<? extends X> before1,
            @Nonnull final ThrowableIntToCharFunction<? extends X> before2,
            @Nonnull final ThrowableIntToCharFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyAsCharThrows(value1),
                                                             before2.applyAsCharThrows(value2),
                                                             before3.applyAsCharThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriLongToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongToCharFunction<X> composeFromLong(
            @Nonnull final ThrowableLongToCharFunction<? extends X> before1,
            @Nonnull final ThrowableLongToCharFunction<? extends X> before2,
            @Nonnull final ThrowableLongToCharFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyAsCharThrows(value1),
                                                             before2.applyAsCharThrows(value2),
                                                             before3.applyAsCharThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ThrowableTriShortToCharFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortToCharFunction<X> composeFromShort(
            @Nonnull final ThrowableShortToCharFunction<? extends X> before1,
            @Nonnull final ThrowableShortToCharFunction<? extends X> before2,
            @Nonnull final ThrowableShortToCharFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsCharThrows(before1.applyAsCharThrows(value1),
                                                             before2.applyAsCharThrows(value2),
                                                             before3.applyAsCharThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableTriCharFunction<S, X> andThen(
            @Nonnull final ThrowableCharFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyThrows(applyAsCharThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharPredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code ThrowableTriCharPredicate} that first applies this operator to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriCharPredicate<X> andThenToBoolean(@Nonnull final ThrowableCharPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.testThrows(applyAsCharThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriCharToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriCharToByteFunction<X> andThenToByte(
            @Nonnull final ThrowableCharToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByteThrows(applyAsCharThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableCharTernaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ThrowableCharTernaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharTernaryOperator<X> andThenToChar(
            @Nonnull final ThrowableCharUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsCharThrows(applyAsCharThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriCharToDoubleFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriCharToDoubleFunction<X> andThenToDouble(
            @Nonnull final ThrowableCharToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDoubleThrows(applyAsCharThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToFloatFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriCharToFloatFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriCharToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableCharToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloatThrows(applyAsCharThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriCharToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriCharToIntFunction<X> andThenToInt(
            @Nonnull final ThrowableCharToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsIntThrows(applyAsCharThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriCharToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriCharToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableCharToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLongThrows(applyAsCharThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharToShortFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableTriCharToShortFunction} that first applies this operator to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriCharToShortFunction<X> andThenToShort(
            @Nonnull final ThrowableCharToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShortThrows(applyAsCharThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharConsumer} that fist applies this operator to its input, and then
     * consumes the result using the given {@link ThrowableCharConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriCharConsumer} that first applies this operator to its input, and then
     * consumes the result using the given {@code ThrowableCharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriCharConsumer<X> consume(@Nonnull final ThrowableCharConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.acceptThrows(applyAsCharThrows(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableCharTernaryOperator}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableCharTernaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableCharTernaryOperator<X> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Triple<Character, Character, Character>, Character> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableCharTernaryOperator<X> & Memoized) (value1, value2, value3) -> {
                final char returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3), ThrowableFunction.of(
                            key -> applyAsCharThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTernaryOperator} which represents this {@link ThrowableCharTernaryOperator}.
     * Thereby the primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code ThrowableTernaryOperator} which represents this {@code ThrowableCharTernaryOperator}.
     */
    @Nonnull
    default ThrowableTernaryOperator<Character, X> boxed() {
        return this::applyAsCharThrows;
    }

    /**
     * Returns a composed {@link CharTernaryOperator} that applies this operator to its input and nests the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is
     * nested (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown
     * throwables message and the thrown throwable itself.
     *
     * @return A composed {@code CharTernaryOperator} that applies this operator to its input and nests the thrown
     * {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default CharTernaryOperator nest() {
        return (value1, value2, value3) -> {
            try {
                return this.applyAsCharThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link CharTernaryOperator} that applies this operator to its input and sneakily throws the
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
     *         final Class<?> sneakyThrowingFunctionalInterface("some illegal class name");
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link CharTernaryOperator} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default CharTernaryOperator sneakyThrow() {
        return (value1, value2, value3) -> {
            try {
                return this.applyAsCharThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
