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

package org.lambda4j.operator.ternary;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.tri.TriDoubleConsumer;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.ByteToDoubleFunction;
import org.lambda4j.function.conversion.CharToDoubleFunction;
import org.lambda4j.function.conversion.DoubleToByteFunction;
import org.lambda4j.function.conversion.DoubleToCharFunction;
import org.lambda4j.function.conversion.DoubleToFloatFunction;
import org.lambda4j.function.conversion.DoubleToShortFunction;
import org.lambda4j.function.conversion.FloatToDoubleFunction;
import org.lambda4j.function.conversion.ShortToDoubleFunction;
import org.lambda4j.function.tri.TriDoubleFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriByteToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriCharToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToByteFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToCharFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToFloatFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToIntFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToLongFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToShortFunction;
import org.lambda4j.function.tri.conversion.TriFloatToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriIntToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriLongToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriShortToDoubleFunction;
import org.lambda4j.function.tri.to.ToDoubleTriFunction;
import org.lambda4j.operator.binary.DoubleBinaryOperator2;
import org.lambda4j.operator.unary.DoubleUnaryOperator2;
import org.lambda4j.predicate.tri.TriDoublePredicate;

/**
 * Represents an operation that accepts three {@code double}-valued input arguments and produces a {@code double}-valued
 * result. This is a primitive specialization of {@link TernaryOperator}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(double, double, double)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleTernaryOperator extends Lambda {

    /**
     * Constructs a {@link DoubleTernaryOperator} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code DoubleTernaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static DoubleTernaryOperator of(@Nullable DoubleTernaryOperator expression) {
        return expression;
    }

    /**
     * Calls the given {@link DoubleTernaryOperator} with the given arguments and returns its result.
     *
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @param value3 The third argument to the operator
     * @return The result from the given {@code DoubleTernaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static double call(@Nonnull DoubleTernaryOperator operator, double value1, double value2, double value3) {
        Objects.requireNonNull(operator);
        return operator.applyAsDouble(value1, value2, value3);
    }

    /**
     * Creates a {@link DoubleTernaryOperator} which uses the {@code first} parameter of this one as argument for the
     * given {@link DoubleUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code DoubleTernaryOperator} which uses the {@code first} parameter of this one as argument
     * for the given {@code DoubleUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static DoubleTernaryOperator onlyFirst(@Nonnull DoubleUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsDouble(value1);
    }

    /**
     * Creates a {@link DoubleTernaryOperator} which uses the {@code second} parameter of this one as argument for the
     * given {@link DoubleUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code DoubleTernaryOperator} which uses the {@code second} parameter of this one as argument
     * for the given {@code DoubleUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static DoubleTernaryOperator onlySecond(@Nonnull DoubleUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsDouble(value2);
    }

    /**
     * Creates a {@link DoubleTernaryOperator} which uses the {@code third} parameter of this one as argument for the
     * given {@link DoubleUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code third} parameter of this one
     * @return Creates a {@code DoubleTernaryOperator} which uses the {@code third} parameter of this one as argument
     * for the given {@code DoubleUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static DoubleTernaryOperator onlyThird(@Nonnull DoubleUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsDouble(value3);
    }

    /**
     * Creates a {@link DoubleTernaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code DoubleTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static DoubleTernaryOperator constant(double ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @param value3 The third argument to the operator
     * @return The return value from the operator, which is its result.
     */
    double applyAsDouble(double value1, double value2, double value3);

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link DoubleBinaryOperator2} as
     * result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @return A {@code DoubleBinaryOperator2} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default DoubleBinaryOperator2 applyAsDoublePartially(double value1) {
        return (value2, value3) -> applyAsDouble(value1, value2, value3);
    }

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link DoubleUnaryOperator2} as
     * result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @param value2 The second argument to this operator used to partially apply this function
     * @return A {@code DoubleUnaryOperator2} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default DoubleUnaryOperator2 applyAsDoublePartially(double value1, double value2) {
        return value3 -> applyAsDouble(value1, value2, value3);
    }

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
     * Returns a composed {@link ToDoubleTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code ToDoubleTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToDoubleTriFunction<A, B, C> compose(@Nonnull ToDoubleFunction<? super A> before1,
            @Nonnull ToDoubleFunction<? super B> before2, @Nonnull ToDoubleFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsDouble(before1.applyAsDouble(a), before2.applyAsDouble(b), before3.applyAsDouble(c));
    }

    /**
     * Returns a composed {@link TriBooleanToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code TriBooleanToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToDoubleFunction composeFromBoolean(@Nonnull BooleanToDoubleFunction before1,
            @Nonnull BooleanToDoubleFunction before2, @Nonnull BooleanToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriByteToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code TriByteToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToDoubleFunction composeFromByte(@Nonnull ByteToDoubleFunction before1,
            @Nonnull ByteToDoubleFunction before2, @Nonnull ByteToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriCharToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code TriCharToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToDoubleFunction composeFromChar(@Nonnull CharToDoubleFunction before1,
            @Nonnull CharToDoubleFunction before2, @Nonnull CharToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that first applies the {@code before} operators to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@code DoubleTernaryOperator} that first applies the {@code before} operators to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleTernaryOperator composeFromDouble(@Nonnull DoubleUnaryOperator before1,
            @Nonnull DoubleUnaryOperator before2, @Nonnull DoubleUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriFloatToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code TriFloatToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToDoubleFunction composeFromFloat(@Nonnull FloatToDoubleFunction before1,
            @Nonnull FloatToDoubleFunction before2, @Nonnull FloatToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriIntToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code TriIntToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToDoubleFunction composeFromInt(@Nonnull IntToDoubleFunction before1,
            @Nonnull IntToDoubleFunction before2, @Nonnull IntToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriLongToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code TriLongToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToDoubleFunction composeFromLong(@Nonnull LongToDoubleFunction before1,
            @Nonnull LongToDoubleFunction before2, @Nonnull LongToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriShortToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @param before3 The third function to apply before this operator is applied
     * @return A composed {@code TriShortToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToDoubleFunction composeFromShort(@Nonnull ShortToDoubleFunction before1,
            @Nonnull ShortToDoubleFunction before2, @Nonnull ShortToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoubleFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriDoubleFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriDoubleFunction<S> andThen(@Nonnull DoubleFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsDouble(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code TriDoublePredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriDoublePredicate andThenToBoolean(@Nonnull DoublePredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(applyAsDouble(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriDoubleToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriDoubleToByteFunction andThenToByte(@Nonnull DoubleToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsDouble(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriDoubleToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriDoubleToCharFunction andThenToChar(@Nonnull DoubleToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsDouble(value1, value2, value3));
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code double}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code DoubleTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleTernaryOperator andThenToDouble(@Nonnull DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsDouble(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriDoubleToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriDoubleToFloatFunction andThenToFloat(@Nonnull DoubleToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsDouble(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriDoubleToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriDoubleToIntFunction andThenToInt(@Nonnull DoubleToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsDouble(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriDoubleToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriDoubleToLongFunction andThenToLong(@Nonnull DoubleToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsDouble(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriDoubleToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriDoubleToShortFunction andThenToShort(@Nonnull DoubleToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsDouble(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@link DoubleConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriDoubleConsumer} that first applies this operator to its input, and then consumes the
     * result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriDoubleConsumer consume(@Nonnull DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsDouble(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link DoubleTernaryOperator}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code DoubleTernaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default DoubleTernaryOperator memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Double, Double, Double>, Double> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (DoubleTernaryOperator & Memoized) (value1, value2, value3) -> {
                double returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3),
                            key -> applyAsDouble(key.getLeft(), key.getMiddle(),
                                    key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link DoubleTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed. This method provides the possibility to use this {@code
     * DoubleTernaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code DoubleTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Double> boxed() {
        return this::applyAsDouble;
    }

}
