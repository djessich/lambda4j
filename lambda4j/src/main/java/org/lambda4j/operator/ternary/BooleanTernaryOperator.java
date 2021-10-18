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
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.consumer.tri.TriBooleanConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.tri.TriBooleanFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToByteFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToCharFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToFloatFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToIntFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToLongFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToShortFunction;
import org.lambda4j.operator.binary.BooleanBinaryOperator;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.ShortPredicate;
import org.lambda4j.predicate.tri.TriBytePredicate;
import org.lambda4j.predicate.tri.TriCharPredicate;
import org.lambda4j.predicate.tri.TriDoublePredicate;
import org.lambda4j.predicate.tri.TriFloatPredicate;
import org.lambda4j.predicate.tri.TriIntPredicate;
import org.lambda4j.predicate.tri.TriLongPredicate;
import org.lambda4j.predicate.tri.TriPredicate;
import org.lambda4j.predicate.tri.TriShortPredicate;

/**
 * Represents an operation that accepts three {@code boolean}-valued input arguments and produces a {@code
 * boolean}-valued result. This is a primitive specialization of {@link TernaryOperator}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(boolean, boolean, boolean)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanTernaryOperator extends Lambda {

    /**
     * Constructs a {@link BooleanTernaryOperator} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BooleanTernaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static BooleanTernaryOperator of(@Nullable BooleanTernaryOperator expression) {
        return expression;
    }

    /**
     * Calls the given {@link BooleanTernaryOperator} with the given arguments and returns its result.
     *
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @param value3 The third argument to the operator
     * @return The result from the given {@code BooleanTernaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static boolean call(@Nonnull BooleanTernaryOperator operator, boolean value1, boolean value2,
            boolean value3) {
        Objects.requireNonNull(operator);
        return operator.applyAsBoolean(value1, value2, value3);
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which uses the {@code first} parameter of this one as argument for the
     * given {@link BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code BooleanTernaryOperator} which uses the {@code first} parameter of this one as argument
     * for the given {@code BooleanUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BooleanTernaryOperator onlyFirst(@Nonnull BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsBoolean(value1);
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which uses the {@code second} parameter of this one as argument for the
     * given {@link BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code BooleanTernaryOperator} which uses the {@code second} parameter of this one as argument
     * for the given {@code BooleanUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BooleanTernaryOperator onlySecond(@Nonnull BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsBoolean(value2);
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which uses the {@code third} parameter of this one as argument for the
     * given {@link BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code third} parameter of this one
     * @return Creates a {@code BooleanTernaryOperator} which uses the {@code third} parameter of this one as argument
     * for the given {@code BooleanUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BooleanTernaryOperator onlyThird(@Nonnull BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2, value3) -> operator.applyAsBoolean(value3);
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static BooleanTernaryOperator constant(boolean ret) {
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
    boolean applyAsBoolean(boolean value1, boolean value2, boolean value3);

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link BooleanBinaryOperator} as
     * result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @return A {@code BooleanBinaryOperator} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default BooleanBinaryOperator applyAsBooleanPartially(boolean value1) {
        return (value2, value3) -> applyAsBoolean(value1, value2, value3);
    }

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link BooleanUnaryOperator} as
     * result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @param value2 The second argument to this operator used to partially apply this function
     * @return A {@code BooleanUnaryOperator} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default BooleanUnaryOperator applyAsBooleanPartially(boolean value1, boolean value2) {
        return value3 -> applyAsBoolean(value1, value2, value3);
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
     * Returns a composed {@link TriPredicate} that first applies the {@code before} predicates to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given predicate, and of composed predicate
     * @param <B> The type of the argument to the second given predicate, and of composed predicate
     * @param <C> The type of the argument to the third given predicate, and of composed predicate
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @param before3 The third predicate to apply before this operator is applied
     * @return A composed {@code TriPredicate} that first applies the {@code before} predicates to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> TriPredicate<A, B, C> compose(@Nonnull Predicate<? super A> before1,
            @Nonnull Predicate<? super B> before2, @Nonnull Predicate<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsBoolean(before1.test(a), before2.test(b), before3.test(c));
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that first applies the {@code before} operators to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@code BooleanTernaryOperator} that first applies the {@code before} operators to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanTernaryOperator composeFromBoolean(@Nonnull BooleanUnaryOperator before1,
            @Nonnull BooleanUnaryOperator before2, @Nonnull BooleanUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsBoolean(before1.applyAsBoolean(value1),
                before2.applyAsBoolean(value2),
                before3.applyAsBoolean(value3));
    }

    /**
     * Returns a composed {@link TriBytePredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @param before3 The third predicate to apply before this operator is applied
     * @return A composed {@code TriBytePredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriBytePredicate composeFromByte(@Nonnull BytePredicate before1, @Nonnull BytePredicate before2,
            @Nonnull BytePredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsBoolean(before1.test(value1), before2.test(value2),
                before3.test(value3));
    }

    /**
     * Returns a composed {@link TriCharPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @param before3 The third predicate to apply before this operator is applied
     * @return A composed {@code TriCharPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharPredicate composeFromChar(@Nonnull CharPredicate before1, @Nonnull CharPredicate before2,
            @Nonnull CharPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsBoolean(before1.test(value1), before2.test(value2),
                before3.test(value3));
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @param before3 The third predicate to apply before this operator is applied
     * @return A composed {@code TriDoublePredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoublePredicate composeFromDouble(@Nonnull DoublePredicate before1,
            @Nonnull DoublePredicate before2, @Nonnull DoublePredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsBoolean(before1.test(value1), before2.test(value2),
                before3.test(value3));
    }

    /**
     * Returns a composed {@link TriFloatPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @param before3 The third predicate to apply before this operator is applied
     * @return A composed {@code TriFloatPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatPredicate composeFromFloat(@Nonnull FloatPredicate before1,
            @Nonnull FloatPredicate before2, @Nonnull FloatPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsBoolean(before1.test(value1), before2.test(value2),
                before3.test(value3));
    }

    /**
     * Returns a composed {@link TriIntPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @param before3 The third predicate to apply before this operator is applied
     * @return A composed {@code TriIntPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntPredicate composeFromInt(@Nonnull IntPredicate before1, @Nonnull IntPredicate before2,
            @Nonnull IntPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsBoolean(before1.test(value1), before2.test(value2),
                before3.test(value3));
    }

    /**
     * Returns a composed {@link TriLongPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @param before3 The third predicate to apply before this operator is applied
     * @return A composed {@code TriLongPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongPredicate composeFromLong(@Nonnull LongPredicate before1, @Nonnull LongPredicate before2,
            @Nonnull LongPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsBoolean(before1.test(value1), before2.test(value2),
                before3.test(value3));
    }

    /**
     * Returns a composed {@link TriShortPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @param before3 The third predicate to apply before this operator is applied
     * @return A composed {@code TriShortPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortPredicate composeFromShort(@Nonnull ShortPredicate before1,
            @Nonnull ShortPredicate before2, @Nonnull ShortPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsBoolean(before1.test(value1), before2.test(value2),
                before3.test(value3));
    }

    /**
     * Returns a composed {@link TriBooleanFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriBooleanFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriBooleanFunction<S> andThen(@Nonnull BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BooleanTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanTernaryOperator andThenToBoolean(@Nonnull BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsBoolean(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriBooleanToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriBooleanToByteFunction andThenToByte(@Nonnull BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriBooleanToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriBooleanToCharFunction andThenToChar(@Nonnull BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriBooleanToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriBooleanToDoubleFunction andThenToDouble(@Nonnull BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriBooleanToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriBooleanToFloatFunction andThenToFloat(@Nonnull BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriBooleanToIntFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriBooleanToIntFunction andThenToInt(@Nonnull BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriBooleanToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriBooleanToLongFunction andThenToLong(@Nonnull BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code TriBooleanToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriBooleanToShortFunction andThenToShort(@Nonnull BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriBooleanConsumer} that first applies this operator to its input, and then consumes
     * the result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriBooleanConsumer consume(@Nonnull BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link BooleanTernaryOperator}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BooleanTernaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BooleanTernaryOperator memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Boolean, Boolean, Boolean>, Boolean> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (BooleanTernaryOperator & Memoized) (value1, value2, value3) -> {
                boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3),
                            key -> applyAsBoolean(key.getLeft(), key.getMiddle(),
                                    key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link BooleanTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed. This method provides the possibility to use this {@code
     * BooleanTernaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code BooleanTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Boolean> boxed() {
        return this::applyAsBoolean;
    }

}
