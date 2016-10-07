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
import at.gridtec.lambda4j.consumer.BooleanConsumer;
import at.gridtec.lambda4j.consumer.bi.BiBooleanConsumer;
import at.gridtec.lambda4j.function.BooleanFunction;
import at.gridtec.lambda4j.function.bi.BiBooleanFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiBooleanToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiBooleanToCharFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiBooleanToFloatFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiBooleanToIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiBooleanToLongFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiBooleanToShortFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.operator.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.predicate.BytePredicate;
import at.gridtec.lambda4j.predicate.CharPredicate;
import at.gridtec.lambda4j.predicate.FloatPredicate;
import at.gridtec.lambda4j.predicate.ShortPredicate;
import at.gridtec.lambda4j.predicate.bi.BiBytePredicate;
import at.gridtec.lambda4j.predicate.bi.BiCharPredicate;
import at.gridtec.lambda4j.predicate.bi.BiDoublePredicate;
import at.gridtec.lambda4j.predicate.bi.BiFloatPredicate;
import at.gridtec.lambda4j.predicate.bi.BiIntPredicate;
import at.gridtec.lambda4j.predicate.bi.BiLongPredicate;
import at.gridtec.lambda4j.predicate.bi.BiPredicate2;
import at.gridtec.lambda4j.predicate.bi.BiShortPredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * Represents an operation that accepts two {@code boolean}-valued input arguments and produces a
 * {@code boolean}-valued result.
 * This is a primitive specialization of {@link BinaryOperator2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(boolean, boolean)}.
 *
 * @see BinaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanBinaryOperator extends Lambda {

    /**
     * Constructs a {@link BooleanBinaryOperator} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BooleanBinaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static BooleanBinaryOperator of(@Nullable final BooleanBinaryOperator expression) {
        return expression;
    }

    /**
     * Calls the given {@link BooleanBinaryOperator} with the given arguments and returns its result.
     *
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @return The result from the given {@code BooleanBinaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static boolean call(@Nonnull final BooleanBinaryOperator operator, boolean value1, boolean value2) {
        Objects.requireNonNull(operator);
        return operator.applyAsBoolean(value1, value2);
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which uses the {@code first} parameter of this one as argument for the
     * given {@link BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code BooleanBinaryOperator} which uses the {@code first} parameter of this one as argument
     * for the given {@code BooleanUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BooleanBinaryOperator onlyFirst(@Nonnull final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsBoolean(value1);
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which uses the {@code second} parameter of this one as argument for the
     * given {@link BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code BooleanBinaryOperator} which uses the {@code second} parameter of this one as argument
     * for the given {@code BooleanUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BooleanBinaryOperator onlySecond(@Nonnull final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsBoolean(value2);
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanBinaryOperator} which always returns a given value.
     */
    @Nonnull
    static BooleanBinaryOperator constant(boolean ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link BooleanBinaryOperator} which returns the lesser of two elements according to the specified
     * {@code Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code BooleanBinaryOperator} which returns the lesser of its operands, according to the supplied
     * {@code Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static BooleanBinaryOperator minBy(@Nonnull final Comparator<Boolean> comparator) {
        Objects.requireNonNull(comparator);
        return (value1, value2) -> comparator.compare(value1, value2) <= 0 ? value1 : value2;
    }

    /**
     * Returns a {@link BooleanBinaryOperator} which returns the greater of two elements according to the specified
     * {@code Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code BooleanBinaryOperator} which returns the greater of its operands, according to the supplied
     * {@code Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static BooleanBinaryOperator maxBy(@Nonnull final Comparator<Boolean> comparator) {
        Objects.requireNonNull(comparator);
        return (value1, value2) -> comparator.compare(value1, value2) >= 0 ? value1 : value2;
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @return The return value from the operator, which is its result.
     */
    boolean applyAsBoolean(boolean value1, boolean value2);

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
     * Returns a composed {@link BiPredicate2} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given predicate, and of composed predicate
     * @param <B> The type of the argument to the second given predicate, and of composed predicate
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @return A composed {@code BiPredicate2} that first applies the {@code before} predicates to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiPredicate2<A, B> compose(@Nonnull final Predicate<? super A> before1,
            @Nonnull final Predicate<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsBoolean(before1.test(a), before2.test(b));
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that first applies the {@code before} operators to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @return A composed {@code BooleanBinaryOperator} that first applies the {@code before} operators to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanBinaryOperator composeFromBoolean(@Nonnull final BooleanUnaryOperator before1,
            @Nonnull final BooleanUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsBoolean(before1.applyAsBoolean(value1), before2.applyAsBoolean(value2));
    }

    /**
     * Returns a composed {@link BiBytePredicate} that first applies the {@code before} predicates to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @return A composed {@code BiBytePredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiBytePredicate composeFromByte(@Nonnull final BytePredicate before1,
            @Nonnull final BytePredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsBoolean(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiCharPredicate} that first applies the {@code before} predicates to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @return A composed {@code BiCharPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharPredicate composeFromChar(@Nonnull final CharPredicate before1,
            @Nonnull final CharPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsBoolean(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiDoublePredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @return A composed {@code BiDoublePredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoublePredicate composeFromDouble(@Nonnull final DoublePredicate before1,
            @Nonnull final DoublePredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsBoolean(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiFloatPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @return A composed {@code BiFloatPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatPredicate composeFromFloat(@Nonnull final FloatPredicate before1,
            @Nonnull final FloatPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsBoolean(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiIntPredicate} that first applies the {@code before} predicates to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @return A composed {@code BiIntPredicate} that first applies the {@code before} predicates to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntPredicate composeFromInt(@Nonnull final IntPredicate before1, @Nonnull final IntPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsBoolean(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiLongPredicate} that first applies the {@code before} predicates to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @return A composed {@code BiLongPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongPredicate composeFromLong(@Nonnull final LongPredicate before1,
            @Nonnull final LongPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsBoolean(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiShortPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first predicate to apply before this operator is applied
     * @param before2 The second predicate to apply before this operator is applied
     * @return A composed {@code BiShortPredicate} that first applies the {@code before} predicates to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortPredicate composeFromShort(@Nonnull final ShortPredicate before1,
            @Nonnull final ShortPredicate before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsBoolean(before1.test(value1), before2.test(value2));
    }

    /**
     * Returns a composed {@link BiBooleanFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiBooleanFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiBooleanFunction<S> andThen(@Nonnull final BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BooleanBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanBinaryOperator andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsBoolean(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiBooleanToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiBooleanToByteFunction andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiBooleanToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiBooleanToCharFunction andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiBooleanToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiBooleanToDoubleFunction andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiBooleanToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiBooleanToFloatFunction andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiBooleanToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiBooleanToIntFunction andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiBooleanToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiBooleanToLongFunction andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiBooleanToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiBooleanToShortFunction andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiBooleanConsumer} that first applies this operator to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiBooleanConsumer consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link BooleanBinaryOperator}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BooleanBinaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BooleanBinaryOperator memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<Boolean, Boolean>, Boolean> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (BooleanBinaryOperator & Memoized) (value1, value2) -> {
                final boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2),
                                                        key -> applyAsBoolean(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link BinaryOperator} which represents this {@link BooleanBinaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed. This method is just convenience to provide the ability
     * to use this {@code BooleanBinaryOperator} with JDK specific methods, only accepting {@code BinaryOperator}.
     *
     * @return A composed {@code BinaryOperator} which represents this {@code BooleanBinaryOperator}.
     */
    @Nonnull
    default BinaryOperator<Boolean> boxed() {
        return this::applyAsBoolean;
    }

}