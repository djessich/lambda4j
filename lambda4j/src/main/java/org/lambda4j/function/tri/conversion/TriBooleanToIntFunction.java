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

package org.lambda4j.function.tri.conversion;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoublePredicate;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.tri.TriBooleanConsumer;
import org.lambda4j.function.bi.conversion.BiBooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.IntToByteFunction;
import org.lambda4j.function.conversion.IntToCharFunction;
import org.lambda4j.function.conversion.IntToFloatFunction;
import org.lambda4j.function.conversion.IntToShortFunction;
import org.lambda4j.function.tri.TriBooleanFunction;
import org.lambda4j.function.tri.TriFunction;
import org.lambda4j.function.tri.to.ToIntTriFunction;
import org.lambda4j.operator.ternary.BooleanTernaryOperator;
import org.lambda4j.operator.ternary.IntTernaryOperator;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.ShortPredicate;

/**
 * Represents an operation that accepts three {@code boolean}-valued input arguments and produces a {@code int}-valued
 * result. This is a primitive specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(boolean, boolean, boolean)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriBooleanToIntFunction extends Lambda {

    /**
     * Constructs a {@link TriBooleanToIntFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriBooleanToIntFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static TriBooleanToIntFunction of(@Nullable TriBooleanToIntFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriBooleanToIntFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriBooleanToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static int call(@Nonnull TriBooleanToIntFunction function, boolean value1, boolean value2, boolean value3) {
        Objects.requireNonNull(function);
        return function.applyAsInt(value1, value2, value3);
    }

    /**
     * Creates a {@link TriBooleanToIntFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link BooleanToIntFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriBooleanToIntFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code BooleanToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriBooleanToIntFunction onlyFirst(@Nonnull BooleanToIntFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsInt(value1);
    }

    /**
     * Creates a {@link TriBooleanToIntFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link BooleanToIntFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriBooleanToIntFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code BooleanToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriBooleanToIntFunction onlySecond(@Nonnull BooleanToIntFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsInt(value2);
    }

    /**
     * Creates a {@link TriBooleanToIntFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link BooleanToIntFunction}.
     *
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriBooleanToIntFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code BooleanToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriBooleanToIntFunction onlyThird(@Nonnull BooleanToIntFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsInt(value3);
    }

    /**
     * Creates a {@link TriBooleanToIntFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriBooleanToIntFunction} which always returns a given value.
     */
    @Nonnull
    static TriBooleanToIntFunction constant(int ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    int applyAsInt(boolean value1, boolean value2, boolean value3);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BiBooleanToIntFunction} as
     * result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @return A {@code BiBooleanToIntFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BiBooleanToIntFunction applyAsIntPartially(boolean value1) {
        return (value2, value3) -> applyAsInt(value1, value2, value3);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BooleanToIntFunction} as
     * result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @param value2 The second argument to this function used to partially apply this function
     * @return A {@code BooleanToIntFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BooleanToIntFunction applyAsIntPartially(boolean value1, boolean value2) {
        return value3 -> applyAsInt(value1, value2, value3);
    }

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given predicate, and of composed function
     * @param <B> The type of the argument to the second given predicate, and of composed function
     * @param <C> The type of the argument to the third given predicate, and of composed function
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code ToIntTriFunction} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToIntTriFunction<A, B, C> compose(@Nonnull Predicate<? super A> before1,
            @Nonnull Predicate<? super B> before2, @Nonnull Predicate<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsInt(before1.test(a), before2.test(b), before3.test(c));
    }

    /**
     * Returns a composed {@link TriBooleanToIntFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriBooleanToIntFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToIntFunction composeFromBoolean(@Nonnull BooleanUnaryOperator before1,
            @Nonnull BooleanUnaryOperator before2, @Nonnull BooleanUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.applyAsBoolean(value1), before2.applyAsBoolean(value2),
                before3.applyAsBoolean(value3));
    }

    /**
     * Returns a composed {@link TriByteToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriByteToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToIntFunction composeFromByte(@Nonnull BytePredicate before1,
            @Nonnull BytePredicate before2, @Nonnull BytePredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriCharToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriCharToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToIntFunction composeFromChar(@Nonnull CharPredicate before1,
            @Nonnull CharPredicate before2, @Nonnull CharPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriDoubleToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToIntFunction composeFromDouble(@Nonnull DoublePredicate before1,
            @Nonnull DoublePredicate before2, @Nonnull DoublePredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriFloatToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriFloatToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToIntFunction composeFromFloat(@Nonnull FloatPredicate before1,
            @Nonnull FloatPredicate before2, @Nonnull FloatPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link IntTernaryOperator} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code IntTernaryOperator} that first applies the {@code before} predicates to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntTernaryOperator composeFromInt(@Nonnull IntPredicate before1, @Nonnull IntPredicate before2,
            @Nonnull IntPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriLongToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriLongToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToIntFunction composeFromLong(@Nonnull LongPredicate before1,
            @Nonnull LongPredicate before2, @Nonnull LongPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriShortToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first predicate to apply before this function is applied
     * @param before2 The second predicate to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriShortToIntFunction} that first applies the {@code before} predicates to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToIntFunction composeFromShort(@Nonnull ShortPredicate before1,
            @Nonnull ShortPredicate before2, @Nonnull ShortPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriBooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriBooleanFunction<S> andThen(@Nonnull IntFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code BooleanTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanTernaryOperator andThenToBoolean(@Nonnull IntPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriBooleanToByteFunction andThenToByte(@Nonnull IntToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriBooleanToCharFunction andThenToChar(@Nonnull IntToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriBooleanToDoubleFunction andThenToDouble(@Nonnull IntToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriBooleanToFloatFunction andThenToFloat(@Nonnull IntToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code TriBooleanToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriBooleanToIntFunction andThenToInt(@Nonnull IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriBooleanToLongFunction andThenToLong(@Nonnull IntToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriBooleanToShortFunction andThenToShort(@Nonnull IntToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriBooleanConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriBooleanConsumer consume(@Nonnull IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsInt(value1, value2, value3));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default TriBooleanToIntFunction reversed() {
        return (value3, value2, value1) -> applyAsInt(value1, value2, value3);
    }

    /**
     * Returns a memoized (caching) version of this {@link TriBooleanToIntFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriBooleanToIntFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriBooleanToIntFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Boolean, Boolean, Boolean>, Integer> cache = new ConcurrentHashMap<>();
            return (TriBooleanToIntFunction & Memoized) (value1, value2, value3) -> {
                return cache.computeIfAbsent(Triple.of(value1, value2, value3),
                        key -> applyAsInt(key.getLeft(), key.getMiddle(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriBooleanToIntFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * TriBooleanToIntFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriBooleanToIntFunction}.
     */
    @Nonnull
    default TriFunction<Boolean, Boolean, Boolean, Integer> boxed() {
        return this::applyAsInt;
    }
}
