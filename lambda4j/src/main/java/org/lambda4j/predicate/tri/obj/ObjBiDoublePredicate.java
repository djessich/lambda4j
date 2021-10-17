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

package org.lambda4j.predicate.tri.obj;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.consumer.tri.obj.ObjBiDoubleConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
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
import org.lambda4j.function.tri.obj.ObjBiDoubleFunction;
import org.lambda4j.function.tri.obj.ObjBiDoubleToByteFunction;
import org.lambda4j.function.tri.obj.ObjBiDoubleToCharFunction;
import org.lambda4j.function.tri.obj.ObjBiDoubleToDoubleFunction;
import org.lambda4j.function.tri.obj.ObjBiDoubleToFloatFunction;
import org.lambda4j.function.tri.obj.ObjBiDoubleToIntFunction;
import org.lambda4j.function.tri.obj.ObjBiDoubleToLongFunction;
import org.lambda4j.function.tri.obj.ObjBiDoubleToShortFunction;
import org.lambda4j.operator.ternary.BooleanTernaryOperator;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.DoublePredicate2;
import org.lambda4j.predicate.Predicate2;
import org.lambda4j.predicate.bi.BiDoublePredicate;
import org.lambda4j.predicate.bi.obj.ObjDoublePredicate;
import org.lambda4j.predicate.tri.TriBytePredicate;
import org.lambda4j.predicate.tri.TriCharPredicate;
import org.lambda4j.predicate.tri.TriDoublePredicate;
import org.lambda4j.predicate.tri.TriFloatPredicate;
import org.lambda4j.predicate.tri.TriIntPredicate;
import org.lambda4j.predicate.tri.TriLongPredicate;
import org.lambda4j.predicate.tri.TriPredicate;
import org.lambda4j.predicate.tri.TriShortPredicate;

/**
 * Represents an predicate (boolean-valued function) of one object-valued and two {@code double}-valued input arguments.
 * This is a (reference, double, double) specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, double, double)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjBiDoublePredicate<T> extends Lambda {

    /**
     * Constructs a {@link ObjBiDoublePredicate} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjBiDoublePredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T> ObjBiDoublePredicate<T> of(@Nullable ObjBiDoublePredicate<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjBiDoublePredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value1 The second argument to the predicate
     * @param value2 The third argument to the predicate
     * @return The result from the given {@code ObjBiDoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> boolean call(@Nonnull ObjBiDoublePredicate<? super T> predicate, T t, double value1,
            double value2) {
        Objects.requireNonNull(predicate);
        return predicate.test(t, value1, value2);
    }

    /**
     * Creates a {@link ObjBiDoublePredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjBiDoublePredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@code Predicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiDoublePredicate<T> onlyFirst(@Nonnull Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (t, value1, value2) -> predicate.test(t);
    }

    /**
     * Creates a {@link ObjBiDoublePredicate} which uses the {@code second} parameter of this one as argument for the
     * given {@link DoublePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjBiDoublePredicate} which uses the {@code second} parameter of this one as argument
     * for the given {@code DoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiDoublePredicate<T> onlySecond(@Nonnull DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return (t, value1, value2) -> predicate.test(value1);
    }

    /**
     * Creates a {@link ObjBiDoublePredicate} which uses the {@code third} parameter of this one as argument for the
     * given {@link DoublePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code ObjBiDoublePredicate} which uses the {@code third} parameter of this one as argument for
     * the given {@code DoublePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiDoublePredicate<T> onlyThird(@Nonnull DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return (t, value1, value2) -> predicate.test(value2);
    }

    /**
     * Creates a {@link ObjBiDoublePredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ObjBiDoublePredicate} which always returns a given value.
     */
    @Nonnull
    static <T> ObjBiDoublePredicate<T> constant(boolean ret) {
        return (t, value1, value2) -> ret;
    }

    /**
     * Returns a {@link ObjBiDoublePredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @return A {@link ObjBiDoublePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T> ObjBiDoublePredicate<T> alwaysTrue() {
        return (t, value1, value2) -> true;
    }

    /**
     * Returns a {@link ObjBiDoublePredicate} that always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @return A {@link ObjBiDoublePredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T> ObjBiDoublePredicate<T> alwaysFalse() {
        return (t, value1, value2) -> false;
    }

    /**
     * Returns a {@link ObjBiDoublePredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     *
     * @param <T> The type of the first argument to the predicate
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @param target3 The third reference with which to compare for equality, which may be {@code null}
     * @return A {@code ObjBiDoublePredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <T> ObjBiDoublePredicate<T> isEqual(@Nullable Object target1, double target2, double target3) {
        return (t, value1, value2) -> t == null ? target1 == null : t.equals(target1) && value1 == target2
                && value2 == target3;
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param t The first argument to the predicate
     * @param value1 The second argument to the predicate
     * @param value2 The third argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(T t, double value1, double value2);

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link BiDoublePredicate} as result.
     *
     * @param t The first argument to this predicate used to partially apply this function
     * @return A {@code BiDoublePredicate} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default BiDoublePredicate ptest(T t) {
        return (value1, value2) -> test(t, value1, value2);
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link DoublePredicate2} as result.
     *
     * @param t The first argument to this predicate used to partially apply this function
     * @param value1 The second argument to this predicate used to partially apply this function
     * @return A {@code DoublePredicate2} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default DoublePredicate2 ptest(T t, double value1) {
        return value2 -> test(t, value1, value2);
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link ObjDoublePredicate} as
     * result.
     *
     * @param value1 The second argument to this predicate used to partially apply this function
     * @return A {@code ObjDoublePredicate} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default ObjDoublePredicate<T> ptest(double value1) {
        return (t, value2) -> test(t, value1, value2);
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link Predicate2} as result.
     *
     * @param value1 The second argument to this predicate used to partially apply this function
     * @param value2 The third argument to this predicate used to partially apply this function
     * @return A {@code Predicate2} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default Predicate2<T> ptest(double value1, double value2) {
        return t -> test(t, value1, value2);
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
    default <A, B, C> TriPredicate<A, B, C> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull ToDoubleFunction<? super B> before2, @Nonnull ToDoubleFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> test(before1.apply(a), before2.applyAsDouble(b), before3.applyAsDouble(c));
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
    default BooleanTernaryOperator composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanToDoubleFunction before2, @Nonnull BooleanToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.applyAsDouble(value2),
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
    default TriBytePredicate composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteToDoubleFunction before2, @Nonnull ByteToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.applyAsDouble(value2),
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
    default TriCharPredicate composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharToDoubleFunction before2, @Nonnull CharToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second operator to apply before this predicate is applied
     * @param before3 The third operator to apply before this predicate is applied
     * @return A composed {@code TriDoublePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoublePredicate composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleUnaryOperator before2, @Nonnull DoubleUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.applyAsDouble(value2),
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
    default TriFloatPredicate composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatToDoubleFunction before2, @Nonnull FloatToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.applyAsDouble(value2),
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
    default TriIntPredicate composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntToDoubleFunction before2, @Nonnull IntToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.applyAsDouble(value2),
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
    default TriLongPredicate composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongToDoubleFunction before2, @Nonnull LongToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.applyAsDouble(value2),
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
    default TriShortPredicate composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortToDoubleFunction before2, @Nonnull ShortToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.applyAsDouble(value2),
                before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link ObjBiDoubleFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjBiDoubleFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjBiDoubleFunction<T, S> andThen(@Nonnull BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.apply(test(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiDoublePredicate} that first applies this predicate to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code ObjBiDoublePredicate} that first applies this predicate to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjBiDoublePredicate<T> andThenToBoolean(@Nonnull BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsBoolean(test(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiDoubleToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjBiDoubleToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjBiDoubleToByteFunction<T> andThenToByte(@Nonnull BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsByte(test(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiDoubleToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjBiDoubleToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjBiDoubleToCharFunction<T> andThenToChar(@Nonnull BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsChar(test(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiDoubleToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjBiDoubleToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjBiDoubleToDoubleFunction<T> andThenToDouble(@Nonnull BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsDouble(test(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiDoubleToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjBiDoubleToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjBiDoubleToFloatFunction<T> andThenToFloat(@Nonnull BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsFloat(test(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiDoubleToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjBiDoubleToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjBiDoubleToIntFunction<T> andThenToInt(@Nonnull BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsInt(test(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiDoubleToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjBiDoubleToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjBiDoubleToLongFunction<T> andThenToLong(@Nonnull BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsLong(test(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiDoubleToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ObjBiDoubleToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjBiDoubleToShortFunction<T> andThenToShort(@Nonnull BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsShort(test(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiDoubleConsumer} that fist applies this predicate to its input, and then consumes
     * the result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjBiDoubleConsumer} that first applies this predicate to its input, and then consumes
     * the result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjBiDoubleConsumer<T> consume(@Nonnull BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.accept(test(t, value1, value2));
    }

    /**
     * Returns a {@link ObjBiDoublePredicate} that represents the logical negation of this one.
     *
     * @return A {@code ObjBiDoublePredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default ObjBiDoublePredicate<T> negate() {
        return (t, value1, value2) -> !test(t, value1, value2);
    }

    /**
     * Returns a composed {@link ObjBiDoublePredicate} that represents a short-circuiting logical AND of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ObjBiDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ObjBiDoublePredicate} that will be logically-ANDed with this one
     * @return A composed {@code ObjBiDoublePredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(ObjBiDoublePredicate)
     * @see #xor(ObjBiDoublePredicate)
     */
    @Nonnull
    default ObjBiDoublePredicate<T> and(@Nonnull ObjBiDoublePredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value1, value2) -> test(t, value1, value2) && other.test(t, value1, value2);
    }

    /**
     * Returns a composed {@link ObjBiDoublePredicate} that represents a short-circuiting logical OR of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ObjBiDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ObjBiDoublePredicate} that will be logically-ORed with this one
     * @return A composed {@code ObjBiDoublePredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ObjBiDoublePredicate)
     * @see #xor(ObjBiDoublePredicate)
     */
    @Nonnull
    default ObjBiDoublePredicate<T> or(@Nonnull ObjBiDoublePredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value1, value2) -> test(t, value1, value2) || other.test(t, value1, value2);
    }

    /**
     * Returns a composed {@link ObjBiDoublePredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation
     * of this {@code ObjBiDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ObjBiDoublePredicate} that will be logically-XORed with this one
     * @return A composed {@code ObjBiDoublePredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ObjBiDoublePredicate)
     * @see #or(ObjBiDoublePredicate)
     */
    @Nonnull
    default ObjBiDoublePredicate<T> xor(@Nonnull ObjBiDoublePredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value1, value2) -> test(t, value1, value2) ^ other.test(t, value1, value2);
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjBiDoublePredicate}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjBiDoublePredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjBiDoublePredicate<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, Double, Double>, Boolean> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ObjBiDoublePredicate<T> & Memoized) (t, value1, value2) -> {
                boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, value1, value2),
                            key -> test(key.getLeft(), key.getMiddle(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link ObjBiDoublePredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method provides the possibility to use this {@code
     * ObjBiDoublePredicate} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriPredicate} which represents this {@code ObjBiDoublePredicate}.
     */
    @Nonnull
    default TriPredicate<T, Double, Double> boxed() {
        return this::test;
    }

}
