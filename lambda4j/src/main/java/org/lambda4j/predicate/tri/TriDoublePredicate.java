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

package org.lambda4j.predicate.tri;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoublePredicate;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.consumer.tri.TriDoubleConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.conversion.ByteToDoubleFunction;
import org.lambda4j.function.conversion.CharToDoubleFunction;
import org.lambda4j.function.conversion.FloatToDoubleFunction;
import org.lambda4j.function.conversion.ShortToDoubleFunction;
import org.lambda4j.function.tri.TriDoubleFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToByteFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToCharFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToFloatFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToIntFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToLongFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToShortFunction;
import org.lambda4j.operator.ternary.BooleanTernaryOperator;
import org.lambda4j.operator.ternary.DoubleTernaryOperator;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.DoublePredicate2;
import org.lambda4j.predicate.bi.BiDoublePredicate;

/**
 * Represents an predicate (boolean-valued function) of three {@code double}-valued input arguments. This is a primitive
 * specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(double, double, double)}.
 *
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriDoublePredicate extends Lambda {

    /**
     * Constructs a {@link TriDoublePredicate} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriDoublePredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static TriDoublePredicate of(@Nullable TriDoublePredicate expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriDoublePredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code TriDoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static boolean call(@Nonnull TriDoublePredicate predicate, double value1, double value2, double value3) {
        Objects.requireNonNull(predicate);
        return predicate.test(value1, value2, value3);
    }

    /**
     * Creates a {@link TriDoublePredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link DoublePredicate}.
     *
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriDoublePredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@code DoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriDoublePredicate onlyFirst(@Nonnull DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value1);
    }

    /**
     * Creates a {@link TriDoublePredicate} which uses the {@code second} parameter of this one as argument for the
     * given {@link DoublePredicate}.
     *
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriDoublePredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@code DoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriDoublePredicate onlySecond(@Nonnull DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value2);
    }

    /**
     * Creates a {@link TriDoublePredicate} which uses the {@code third} parameter of this one as argument for the given
     * {@link DoublePredicate}.
     *
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriDoublePredicate} which uses the {@code third} parameter of this one as argument for
     * the given {@code DoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriDoublePredicate onlyThird(@Nonnull DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value3);
    }

    /**
     * Creates a {@link TriDoublePredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriDoublePredicate} which always returns a given value.
     */
    @Nonnull
    static TriDoublePredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link TriDoublePredicate} that always returns {@code true}.
     *
     * @return A {@link TriDoublePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static TriDoublePredicate alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link TriDoublePredicate} that always returns {@code false}.
     *
     * @return A {@link TriDoublePredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static TriDoublePredicate alwaysFalse() {
        return (value1, value2, value3) -> false;
    }

    /**
     * Returns a {@link TriDoublePredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     *
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @param target3 The third reference with which to compare for equality, which may be {@code null}
     * @return A {@code TriDoublePredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static TriDoublePredicate isEqual(double target1, double target2, double target3) {
        return (value1, value2, value3) -> value1 == target1 && value2 == target2 && value3 == target3;
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(double value1, double value2, double value3);

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link BiDoublePredicate} as result.
     *
     * @param value1 The first argument to this predicate used to partially apply this function
     * @return A {@code BiDoublePredicate} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default BiDoublePredicate testPartially(double value1) {
        return (value2, value3) -> test(value1, value2, value3);
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link DoublePredicate2} as result.
     *
     * @param value1 The first argument to this predicate used to partially apply this function
     * @param value2 The second argument to this predicate used to partially apply this function
     * @return A {@code DoublePredicate2} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default DoublePredicate2 testPartially(double value1, double value2) {
        return value3 -> test(value1, value2, value3);
    }

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed predicate
     * @param <B> The type of the argument to the second given function, and of composed predicate
     * @param <C> The type of the argument to the third given function, and of composed predicate
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> TriPredicate<A, B, C> compose(@Nonnull ToDoubleFunction<? super A> before1,
            @Nonnull ToDoubleFunction<? super B> before2, @Nonnull ToDoubleFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> test(before1.applyAsDouble(a), before2.applyAsDouble(b), before3.applyAsDouble(c));
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code BooleanTernaryOperator} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanTernaryOperator composeFromBoolean(@Nonnull BooleanToDoubleFunction before1,
            @Nonnull BooleanToDoubleFunction before2, @Nonnull BooleanToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriBytePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriBytePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriBytePredicate composeFromByte(@Nonnull ByteToDoubleFunction before1,
            @Nonnull ByteToDoubleFunction before2, @Nonnull ByteToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriCharPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriCharPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharPredicate composeFromChar(@Nonnull CharToDoubleFunction before1,
            @Nonnull CharToDoubleFunction before2, @Nonnull CharToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that first applies the {@code before} operators to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before1 The first operator to apply before this predicate is applied
     * @param before2 The second operator to apply before this predicate is applied
     * @param before3 The third operator to apply before this predicate is applied
     * @return A composed {@code TriDoublePredicate} that first applies the {@code before} operators to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoublePredicate composeFromDouble(@Nonnull DoubleUnaryOperator before1,
            @Nonnull DoubleUnaryOperator before2, @Nonnull DoubleUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriFloatPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriFloatPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatPredicate composeFromFloat(@Nonnull FloatToDoubleFunction before1,
            @Nonnull FloatToDoubleFunction before2, @Nonnull FloatToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriIntPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriIntPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntPredicate composeFromInt(@Nonnull IntToDoubleFunction before1,
            @Nonnull IntToDoubleFunction before2, @Nonnull IntToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriLongPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriLongPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongPredicate composeFromLong(@Nonnull LongToDoubleFunction before1,
            @Nonnull LongToDoubleFunction before2, @Nonnull LongToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriShortPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriShortPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortPredicate composeFromShort(@Nonnull ShortToDoubleFunction before1,
            @Nonnull ShortToDoubleFunction before2, @Nonnull ShortToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoubleFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriDoubleFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriDoubleFunction<S> andThen(@Nonnull BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that first applies this predicate to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code TriDoublePredicate} that first applies this predicate to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriDoublePredicate andThenToBoolean(@Nonnull BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsBoolean(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriDoubleToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriDoubleToByteFunction andThenToByte(@Nonnull BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriDoubleToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriDoubleToCharFunction andThenToChar(@Nonnull BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code DoubleTernaryOperator} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleTernaryOperator andThenToDouble(@Nonnull BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriDoubleToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriDoubleToFloatFunction andThenToFloat(@Nonnull BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriDoubleToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriDoubleToIntFunction andThenToInt(@Nonnull BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriDoubleToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriDoubleToLongFunction andThenToLong(@Nonnull BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriDoubleToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriDoubleToShortFunction andThenToShort(@Nonnull BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(test(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleConsumer} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriDoubleConsumer} that first applies this predicate to its input, and then consumes
     * the result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriDoubleConsumer consume(@Nonnull BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(test(value1, value2, value3));
    }

    /**
     * Returns a {@link TriDoublePredicate} that represents the logical negation of this one.
     *
     * @return A {@code TriDoublePredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default TriDoublePredicate negate() {
        return (value1, value2, value3) -> !test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that represents a short-circuiting logical AND of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code TriDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code TriDoublePredicate} that will be logically-ANDed with this one
     * @return A composed {@code TriDoublePredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(TriDoublePredicate)
     * @see #xor(TriDoublePredicate)
     */
    @Nonnull
    default TriDoublePredicate and(@Nonnull TriDoublePredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code TriDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code TriDoublePredicate} that will be logically-ORed with this one
     * @return A composed {@code TriDoublePredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(TriDoublePredicate)
     * @see #xor(TriDoublePredicate)
     */
    @Nonnull
    default TriDoublePredicate or(@Nonnull TriDoublePredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) || other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation
     * of this {@code TriDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code TriDoublePredicate} that will be logically-XORed with this one
     * @return A composed {@code TriDoublePredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(TriDoublePredicate)
     * @see #or(TriDoublePredicate)
     */
    @Nonnull
    default TriDoublePredicate xor(@Nonnull TriDoublePredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) ^ other.test(value1, value2, value3);
    }

    /**
     * Returns a reversed version of this predicate. This may be useful in recursive context.
     *
     * @return A reversed version of this predicate.
     */
    @Nonnull
    default TriDoublePredicate reversed() {
        return (value3, value2, value1) -> test(value1, value2, value3);
    }

    /**
     * Returns a memoized (caching) version of this {@link TriDoublePredicate}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriDoublePredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriDoublePredicate memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Double, Double, Double>, Boolean> cache = new ConcurrentHashMap<>();
            return (TriDoublePredicate & Memoized) (value1, value2, value3) -> {
                return cache.computeIfAbsent(Triple.of(value1, value2, value3),
                        key -> test(key.getLeft(), key.getMiddle(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link TriDoublePredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method provides the possibility to use this {@code
     * TriDoublePredicate} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriPredicate} which represents this {@code TriDoublePredicate}.
     */
    @Nonnull
    default TriPredicate<Double, Double, Double> boxed() {
        return this::test;
    }
}
