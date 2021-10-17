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
import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.bi.BiLongConsumer;
import org.lambda4j.function.bi.BiLongFunction;
import org.lambda4j.function.bi.conversion.BiBooleanToLongFunction;
import org.lambda4j.function.bi.conversion.BiByteToLongFunction;
import org.lambda4j.function.bi.conversion.BiCharToLongFunction;
import org.lambda4j.function.bi.conversion.BiDoubleToLongFunction;
import org.lambda4j.function.bi.conversion.BiFloatToLongFunction;
import org.lambda4j.function.bi.conversion.BiIntToLongFunction;
import org.lambda4j.function.bi.conversion.BiLongToByteFunction;
import org.lambda4j.function.bi.conversion.BiLongToCharFunction;
import org.lambda4j.function.bi.conversion.BiLongToDoubleFunction;
import org.lambda4j.function.bi.conversion.BiLongToFloatFunction;
import org.lambda4j.function.bi.conversion.BiLongToIntFunction;
import org.lambda4j.function.bi.conversion.BiLongToShortFunction;
import org.lambda4j.function.bi.conversion.BiShortToLongFunction;
import org.lambda4j.function.bi.to.ToLongBiFunction2;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.ByteToLongFunction;
import org.lambda4j.function.conversion.CharToLongFunction;
import org.lambda4j.function.conversion.FloatToLongFunction;
import org.lambda4j.function.conversion.LongToByteFunction;
import org.lambda4j.function.conversion.LongToCharFunction;
import org.lambda4j.function.conversion.LongToFloatFunction;
import org.lambda4j.function.conversion.LongToShortFunction;
import org.lambda4j.function.conversion.ShortToLongFunction;
import org.lambda4j.operator.unary.LongUnaryOperator2;
import org.lambda4j.predicate.bi.BiLongPredicate;

/**
 * Represents an operation that accepts two {@code long}-valued input arguments and produces a {@code long}-valued
 * result. This is a primitive specialization of {@link BinaryOperator2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(long, long)}.
 *
 * @apiNote This is a JDK lambda.
 * @see BinaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface LongBinaryOperator2 extends Lambda, LongBinaryOperator {

    /**
     * Constructs a {@link LongBinaryOperator2} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code LongBinaryOperator2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static LongBinaryOperator2 of(@Nullable LongBinaryOperator2 expression) {
        return expression;
    }

    /**
     * Calls the given {@link LongBinaryOperator} with the given arguments and returns its result.
     *
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @return The result from the given {@code LongBinaryOperator2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static long call(@Nonnull LongBinaryOperator operator, long value1, long value2) {
        Objects.requireNonNull(operator);
        return operator.applyAsLong(value1, value2);
    }

    /**
     * Creates a {@link LongBinaryOperator2} which uses the {@code first} parameter of this one as argument for the
     * given {@link LongUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code LongBinaryOperator2} which uses the {@code first} parameter of this one as argument for
     * the given {@code LongUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static LongBinaryOperator2 onlyFirst(@Nonnull LongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsLong(value1);
    }

    /**
     * Creates a {@link LongBinaryOperator2} which uses the {@code second} parameter of this one as argument for the
     * given {@link LongUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code LongBinaryOperator2} which uses the {@code second} parameter of this one as argument for
     * the given {@code LongUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static LongBinaryOperator2 onlySecond(@Nonnull LongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsLong(value2);
    }

    /**
     * Creates a {@link LongBinaryOperator2} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code LongBinaryOperator2} which always returns a given value.
     */
    @Nonnull
    static LongBinaryOperator2 constant(long ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link LongBinaryOperator2} which returns the lesser of two elements according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code LongBinaryOperator2} which returns the lesser of its operands, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static LongBinaryOperator2 minBy(@Nonnull Comparator<Long> comparator) {
        Objects.requireNonNull(comparator);
        return (value1, value2) -> comparator.compare(value1, value2) <= 0 ? value1 : value2;
    }

    /**
     * Returns a {@link LongBinaryOperator2} which returns the greater of two elements according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code LongBinaryOperator2} which returns the greater of its operands, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static LongBinaryOperator2 maxBy(@Nonnull Comparator<Long> comparator) {
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
    @Override
    long applyAsLong(long value1, long value2);

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link LongUnaryOperator2} as result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @return A {@code LongUnaryOperator2} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default LongUnaryOperator2 papplyAsLong(long value1) {
        return value2 -> applyAsLong(value1, value2);
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
     * Returns a composed {@link ToLongBiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ToLongBiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToLongBiFunction2<A, B> compose(@Nonnull ToLongFunction<? super A> before1,
            @Nonnull ToLongFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsLong(before1.applyAsLong(a), before2.applyAsLong(b));
    }

    /**
     * Returns a composed {@link BiBooleanToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiBooleanToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToLongFunction composeFromBoolean(@Nonnull BooleanToLongFunction before1,
            @Nonnull BooleanToLongFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link BiByteToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiByteToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteToLongFunction composeFromByte(@Nonnull ByteToLongFunction before1,
            @Nonnull ByteToLongFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link BiCharToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiCharToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToLongFunction composeFromChar(@Nonnull CharToLongFunction before1,
            @Nonnull CharToLongFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link BiDoubleToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiDoubleToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleToLongFunction composeFromDouble(@Nonnull DoubleToLongFunction before1,
            @Nonnull DoubleToLongFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link BiFloatToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiFloatToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatToLongFunction composeFromFloat(@Nonnull FloatToLongFunction before1,
            @Nonnull FloatToLongFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link BiIntToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiIntToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntToLongFunction composeFromInt(@Nonnull IntToLongFunction before1,
            @Nonnull IntToLongFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link LongBinaryOperator2} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @return A composed {@code LongBinaryOperator2} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongBinaryOperator2 composeFromLong(@Nonnull LongUnaryOperator before1,
            @Nonnull LongUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link BiShortToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiShortToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToLongFunction composeFromShort(@Nonnull ShortToLongFunction before1,
            @Nonnull ShortToLongFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.applyAsLong(value1), before2.applyAsLong(value2));
    }

    /**
     * Returns a composed {@link BiLongFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiLongFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiLongFunction<S> andThen(@Nonnull LongFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiLongPredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code BiLongPredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiLongPredicate andThenToBoolean(@Nonnull LongPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.test(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiLongToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiLongToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiLongToByteFunction andThenToByte(@Nonnull LongToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiLongToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiLongToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiLongToCharFunction andThenToChar(@Nonnull LongToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiLongToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiLongToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiLongToDoubleFunction andThenToDouble(@Nonnull LongToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiLongToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiLongToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiLongToFloatFunction andThenToFloat(@Nonnull LongToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiLongToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiLongToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiLongToIntFunction andThenToInt(@Nonnull LongToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link LongBinaryOperator2} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code long}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code LongBinaryOperator2} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongBinaryOperator2 andThenToLong(@Nonnull LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiLongToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiLongToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiLongToShortFunction andThenToShort(@Nonnull LongToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiLongConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@link LongConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiLongConsumer} that first applies this operator to its input, and then consumes the
     * result using the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiLongConsumer consume(@Nonnull LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsLong(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link LongBinaryOperator2}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code LongBinaryOperator2}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default LongBinaryOperator2 memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<Long, Long>, Long> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (LongBinaryOperator2 & Memoized) (value1, value2) -> {
                long returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2),
                            key -> applyAsLong(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link BinaryOperator2} which represents this {@link LongBinaryOperator2}. Thereby the
     * primitive input argument for this operator is autoboxed. This method provides the possibility to use this {@code
     * LongBinaryOperator2} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BinaryOperator2} which represents this {@code LongBinaryOperator2}.
     */
    @Nonnull
    default BinaryOperator2<Long> boxed() {
        return this::applyAsLong;
    }

}
