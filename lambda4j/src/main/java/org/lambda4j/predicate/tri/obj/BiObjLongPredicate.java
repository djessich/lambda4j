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
import java.util.function.DoubleToLongFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.consumer.tri.obj.BiObjLongConsumer;
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
import org.lambda4j.function.conversion.ByteToLongFunction;
import org.lambda4j.function.conversion.CharToLongFunction;
import org.lambda4j.function.conversion.FloatToLongFunction;
import org.lambda4j.function.conversion.ShortToLongFunction;
import org.lambda4j.function.tri.obj.BiObjLongFunction;
import org.lambda4j.function.tri.obj.BiObjLongToByteFunction;
import org.lambda4j.function.tri.obj.BiObjLongToCharFunction;
import org.lambda4j.function.tri.obj.BiObjLongToDoubleFunction;
import org.lambda4j.function.tri.obj.BiObjLongToFloatFunction;
import org.lambda4j.function.tri.obj.BiObjLongToIntFunction;
import org.lambda4j.function.tri.obj.BiObjLongToLongFunction;
import org.lambda4j.function.tri.obj.BiObjLongToShortFunction;
import org.lambda4j.operator.ternary.BooleanTernaryOperator;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.LongPredicate2;
import org.lambda4j.predicate.Predicate2;
import org.lambda4j.predicate.bi.BiPredicate2;
import org.lambda4j.predicate.bi.obj.ObjLongPredicate;
import org.lambda4j.predicate.tri.TriBytePredicate;
import org.lambda4j.predicate.tri.TriCharPredicate;
import org.lambda4j.predicate.tri.TriDoublePredicate;
import org.lambda4j.predicate.tri.TriFloatPredicate;
import org.lambda4j.predicate.tri.TriIntPredicate;
import org.lambda4j.predicate.tri.TriLongPredicate;
import org.lambda4j.predicate.tri.TriPredicate;
import org.lambda4j.predicate.tri.TriShortPredicate;

/**
 * Represents an predicate (boolean-valued function) of two object-valued and one {@code long}-valued input argument.
 * This is a (reference, reference, long) specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, Object, long)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjLongPredicate<T, U> extends Lambda {

    /**
     * Constructs a {@link BiObjLongPredicate} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiObjLongPredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U> BiObjLongPredicate<T, U> of(@Nullable BiObjLongPredicate<T, U> expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiObjLongPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code BiObjLongPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U> boolean call(@Nonnull BiObjLongPredicate<? super T, ? super U> predicate, T t, U u,
            long value) {
        Objects.requireNonNull(predicate);
        return predicate.test(t, u, value);
    }

    /**
     * Creates a {@link BiObjLongPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjLongPredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@code Predicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjLongPredicate<T, U> onlyFirst(@Nonnull Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, value) -> predicate.test(t);
    }

    /**
     * Creates a {@link BiObjLongPredicate} which uses the {@code second} parameter of this one as argument for the
     * given {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjLongPredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@code Predicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjLongPredicate<T, U> onlySecond(@Nonnull Predicate<? super U> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, value) -> predicate.test(u);
    }

    /**
     * Creates a {@link BiObjLongPredicate} which uses the {@code third} parameter of this one as argument for the given
     * {@link LongPredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjLongPredicate} which uses the {@code third} parameter of this one as argument for
     * the given {@code LongPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjLongPredicate<T, U> onlyThird(@Nonnull LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, value) -> predicate.test(value);
    }

    /**
     * Creates a {@link BiObjLongPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code BiObjLongPredicate} which always returns a given value.
     */
    @Nonnull
    static <T, U> BiObjLongPredicate<T, U> constant(boolean ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Returns a {@link BiObjLongPredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiObjLongPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T, U> BiObjLongPredicate<T, U> alwaysTrue() {
        return (t, u, value) -> true;
    }

    /**
     * Returns a {@link BiObjLongPredicate} that always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiObjLongPredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T, U> BiObjLongPredicate<T, U> alwaysFalse() {
        return (t, u, value) -> false;
    }

    /**
     * Returns a {@link BiObjLongPredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @param target3 The third reference with which to compare for equality, which may be {@code null}
     * @return A {@code BiObjLongPredicate} that tests if the given arguments are <b>equal</b> to the ones of this
     * predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <T, U> BiObjLongPredicate<T, U> isEqual(@Nullable Object target1, @Nullable Object target2, long target3) {
        return (t, u, value) -> t == null ? target1 == null : t.equals(target1) && (u == null
                ? target2 == null
                : u.equals(target2)) && value == target3;
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(T t, U u, long value);

    /**
     * Applies this predicate to the given tuple.
     *
     * @param tuple The tuple to be applied to the predicate
     * @param value The primitive value to be applied to the predicate
     * @return The return value from the predicate, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default boolean test(@Nonnull Pair<T, U> tuple, long value) {
        Objects.requireNonNull(tuple);
        return test(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link ObjLongPredicate} as result.
     *
     * @param t The first argument to this predicate used to partially apply this function
     * @return A {@code ObjLongPredicate} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default ObjLongPredicate<U> testPartially(T t) {
        return (u, value) -> test(t, u, value);
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link LongPredicate2} as result.
     *
     * @param t The first argument to this predicate used to partially apply this function
     * @param u The second argument to this predicate used to partially apply this function
     * @return A {@code LongPredicate2} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default LongPredicate2 testPartially(T t, U u) {
        return value -> test(t, u, value);
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link BiPredicate2} as result.
     *
     * @param value The third argument to this predicate used to partially apply this function
     * @return A {@code BiPredicate2} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default BiPredicate2<T, U> testPartially(long value) {
        return (t, u) -> test(t, u, value);
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link Predicate2} as result.
     *
     * @param t The first argument to this predicate used to partially apply this function
     * @param value The third argument to this predicate used to partially apply this function
     * @return A {@code Predicate2} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default Predicate2<U> testPartially(T t, long value) {
        return u -> test(t, u, value);
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
            @Nonnull Function<? super B, ? extends U> before2, @Nonnull ToLongFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> test(before1.apply(a), before2.apply(b), before3.applyAsLong(c));
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
            @Nonnull BooleanFunction<? extends U> before2, @Nonnull BooleanToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.apply(value2),
                before3.applyAsLong(value3));
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
            @Nonnull ByteFunction<? extends U> before2, @Nonnull ByteToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.apply(value2),
                before3.applyAsLong(value3));
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
            @Nonnull CharFunction<? extends U> before2, @Nonnull CharToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.apply(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriDoublePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriDoublePredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoublePredicate composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleFunction<? extends U> before2, @Nonnull DoubleToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.apply(value2),
                before3.applyAsLong(value3));
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
            @Nonnull FloatFunction<? extends U> before2, @Nonnull FloatToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.apply(value2),
                before3.applyAsLong(value3));
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
            @Nonnull IntFunction<? extends U> before2, @Nonnull IntToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.apply(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriLongPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third operator to apply before this predicate is applied
     * @return A composed {@code TriLongPredicate} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongPredicate composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongFunction<? extends U> before2, @Nonnull LongUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.apply(value2),
                before3.applyAsLong(value3));
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
            @Nonnull ShortFunction<? extends U> before2, @Nonnull ShortToLongFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> test(before1.apply(value1), before2.apply(value2),
                before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link BiObjLongFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiObjLongFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiObjLongFunction<T, U, S> andThen(@Nonnull BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(test(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongPredicate} that first applies this predicate to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code BiObjLongPredicate} that first applies this predicate to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiObjLongPredicate<T, U> andThenToBoolean(@Nonnull BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsBoolean(test(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiObjLongToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiObjLongToByteFunction<T, U> andThenToByte(@Nonnull BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsByte(test(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiObjLongToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiObjLongToCharFunction<T, U> andThenToChar(@Nonnull BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsChar(test(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiObjLongToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiObjLongToDoubleFunction<T, U> andThenToDouble(@Nonnull BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsDouble(test(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiObjLongToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiObjLongToFloatFunction<T, U> andThenToFloat(@Nonnull BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsFloat(test(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiObjLongToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiObjLongToIntFunction<T, U> andThenToInt(@Nonnull BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsInt(test(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiObjLongToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiObjLongToLongFunction<T, U> andThenToLong(@Nonnull BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsLong(test(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiObjLongToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiObjLongToShortFunction<T, U> andThenToShort(@Nonnull BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsShort(test(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjLongConsumer} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjLongConsumer} that first applies this predicate to its input, and then consumes
     * the result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjLongConsumer<T, U> consume(@Nonnull BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(test(t, u, value));
    }

    /**
     * Returns a {@link BiObjLongPredicate} that represents the logical negation of this one.
     *
     * @return A {@code BiObjLongPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default BiObjLongPredicate<T, U> negate() {
        return (t, u, value) -> !test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjLongPredicate} that represents a short-circuiting logical AND of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code BiObjLongPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiObjLongPredicate} that will be logically-ANDed with this one
     * @return A composed {@code BiObjLongPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(BiObjLongPredicate)
     * @see #xor(BiObjLongPredicate)
     */
    @Nonnull
    default BiObjLongPredicate<T, U> and(@Nonnull BiObjLongPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) && other.test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjLongPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code BiObjLongPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiObjLongPredicate} that will be logically-ORed with this one
     * @return A composed {@code BiObjLongPredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(BiObjLongPredicate)
     * @see #xor(BiObjLongPredicate)
     */
    @Nonnull
    default BiObjLongPredicate<T, U> or(@Nonnull BiObjLongPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) || other.test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjLongPredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation
     * of this {@code BiObjLongPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiObjLongPredicate} that will be logically-XORed with this one
     * @return A composed {@code BiObjLongPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(BiObjLongPredicate)
     * @see #or(BiObjLongPredicate)
     */
    @Nonnull
    default BiObjLongPredicate<T, U> xor(@Nonnull BiObjLongPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) ^ other.test(t, u, value);
    }

    /**
     * Returns a tupled version of this predicate.
     *
     * @return A tupled version of this predicate.
     */
    @Nonnull
    default ObjLongPredicate<Pair<T, U>> tupled() {
        return this::test;
    }

    /**
     * Returns a memoized (caching) version of this {@link BiObjLongPredicate}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiObjLongPredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiObjLongPredicate<T, U> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, U, Long>, Boolean> cache = new ConcurrentHashMap<>();
            return (BiObjLongPredicate<T, U> & Memoized) (t, u, value) -> {
                return cache.computeIfAbsent(Triple.of(t, u, value),
                        key -> test(key.getLeft(), key.getMiddle(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link BiObjLongPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method provides the possibility to use this {@code
     * BiObjLongPredicate} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriPredicate} which represents this {@code BiObjLongPredicate}.
     */
    @Nonnull
    default TriPredicate<T, U, Long> boxed() {
        return this::test;
    }
}
