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
import java.util.function.Function;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableBooleanConsumer;
import org.lambda4j.consumer.tri.obj.ThrowableObjBiBooleanConsumer;
import org.lambda4j.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.function.ThrowableBooleanFunction;
import org.lambda4j.function.ThrowableByteFunction;
import org.lambda4j.function.ThrowableCharFunction;
import org.lambda4j.function.ThrowableDoubleFunction;
import org.lambda4j.function.ThrowableFloatFunction;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.ThrowableIntFunction;
import org.lambda4j.function.ThrowableLongFunction;
import org.lambda4j.function.ThrowableShortFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToByteFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToCharFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToFloatFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToIntFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
import org.lambda4j.function.tri.obj.ThrowableObjBiBooleanFunction;
import org.lambda4j.function.tri.obj.ThrowableObjBiBooleanToByteFunction;
import org.lambda4j.function.tri.obj.ThrowableObjBiBooleanToCharFunction;
import org.lambda4j.function.tri.obj.ThrowableObjBiBooleanToDoubleFunction;
import org.lambda4j.function.tri.obj.ThrowableObjBiBooleanToFloatFunction;
import org.lambda4j.function.tri.obj.ThrowableObjBiBooleanToIntFunction;
import org.lambda4j.function.tri.obj.ThrowableObjBiBooleanToLongFunction;
import org.lambda4j.function.tri.obj.ThrowableObjBiBooleanToShortFunction;
import org.lambda4j.operator.binary.ThrowableBooleanBinaryOperator;
import org.lambda4j.operator.ternary.ThrowableBooleanTernaryOperator;
import org.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import org.lambda4j.predicate.ThrowableBytePredicate;
import org.lambda4j.predicate.ThrowableCharPredicate;
import org.lambda4j.predicate.ThrowableDoublePredicate;
import org.lambda4j.predicate.ThrowableFloatPredicate;
import org.lambda4j.predicate.ThrowableIntPredicate;
import org.lambda4j.predicate.ThrowableLongPredicate;
import org.lambda4j.predicate.ThrowablePredicate;
import org.lambda4j.predicate.ThrowableShortPredicate;
import org.lambda4j.predicate.bi.obj.ThrowableObjBooleanPredicate;
import org.lambda4j.predicate.tri.ThrowableTriBytePredicate;
import org.lambda4j.predicate.tri.ThrowableTriCharPredicate;
import org.lambda4j.predicate.tri.ThrowableTriDoublePredicate;
import org.lambda4j.predicate.tri.ThrowableTriFloatPredicate;
import org.lambda4j.predicate.tri.ThrowableTriIntPredicate;
import org.lambda4j.predicate.tri.ThrowableTriLongPredicate;
import org.lambda4j.predicate.tri.ThrowableTriPredicate;
import org.lambda4j.predicate.tri.ThrowableTriShortPredicate;
import org.lambda4j.util.ThrowableUtils;

/**
 * Represents an predicate (boolean-valued function) of one object-valued and two {@code boolean}-valued input arguments
 * which is able to throw any {@link Throwable}. This is a (reference, boolean, boolean) specialization of {@link
 * ThrowableTriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(Object, boolean, boolean)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <X> The type of the throwable to be thrown by this predicate
 * @see ThrowableTriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableObjBiBooleanPredicate<T, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableObjBiBooleanPredicate} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableObjBiBooleanPredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, X extends Throwable> ThrowableObjBiBooleanPredicate<T, X> of(
            @Nullable ThrowableObjBiBooleanPredicate<T, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableObjBiBooleanPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value1 The second argument to the predicate
     * @param value2 The third argument to the predicate
     * @return The result from the given {@code ThrowableObjBiBooleanPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this predicates action
     */
    static <T, X extends Throwable> boolean call(
            @Nonnull ThrowableObjBiBooleanPredicate<? super T, ? extends X> predicate, T t, boolean value1,
            boolean value2) throws X {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(t, value1, value2);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanPredicate} which uses the {@code first} parameter of this one as argument
     * for the given {@link ThrowablePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableObjBiBooleanPredicate} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowablePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanPredicate<T, X> onlyFirst(
            @Nonnull ThrowablePredicate<? super T, ? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (t, value1, value2) -> predicate.testThrows(t);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanPredicate} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableBooleanUnaryOperator}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableObjBiBooleanPredicate} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableBooleanUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanPredicate<T, X> onlySecond(
            @Nonnull ThrowableBooleanUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (t, value1, value2) -> operator.applyAsBooleanThrows(value1);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanPredicate} which uses the {@code third} parameter of this one as argument
     * for the given {@link ThrowableBooleanUnaryOperator}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param operator The operator which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableObjBiBooleanPredicate} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableBooleanUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanPredicate<T, X> onlyThird(
            @Nonnull ThrowableBooleanUnaryOperator<? extends X> operator) {
        Objects.requireNonNull(operator);
        return (t, value1, value2) -> operator.applyAsBooleanThrows(value2);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowableObjBiBooleanPredicate} which always returns a given value.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanPredicate<T, X> constant(boolean ret) {
        return (t, value1, value2) -> ret;
    }

    /**
     * Returns a {@link ThrowableObjBiBooleanPredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableObjBiBooleanPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanPredicate<T, X> alwaysTrue() {
        return (t, value1, value2) -> true;
    }

    /**
     * Returns a {@link ThrowableObjBiBooleanPredicate} that always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableObjBiBooleanPredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanPredicate<T, X> alwaysFalse() {
        return (t, value1, value2) -> false;
    }

    /**
     * Returns a {@link ThrowableObjBiBooleanPredicate} that tests if the given arguments are <b>equal</b> to the ones
     * of this predicate.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @param target3 The third reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowableObjBiBooleanPredicate} that tests if the given arguments are <b>equal</b> to the ones
     * of this predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanPredicate<T, X> isEqual(@Nullable Object target1,
            boolean target2, boolean target3) {
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
     * @throws X Any throwable from this predicates action
     */
    boolean testThrows(T t, boolean value1, boolean value2) throws X;

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowableBooleanBinaryOperator}
     * as result.
     *
     * @param t The first argument to this predicate used to partially apply this function
     * @return A {@code ThrowableBooleanBinaryOperator} that represents this operator partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableBooleanBinaryOperator<X> testThrowsPartially(T t) {
        return (value1, value2) -> testThrows(t, value1, value2);
    }

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowableBooleanUnaryOperator}
     * as result.
     *
     * @param t The first argument to this predicate used to partially apply this function
     * @param value1 The second argument to this predicate used to partially apply this function
     * @return A {@code ThrowableBooleanUnaryOperator} that represents this operator partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableBooleanUnaryOperator<X> testThrowsPartially(T t, boolean value1) {
        return value2 -> testThrows(t, value1, value2);
    }

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowableObjBooleanPredicate}
     * as result.
     *
     * @param value1 The second argument to this predicate used to partially apply this function
     * @return A {@code ThrowableObjBooleanPredicate} that represents this operator partially applied the some
     * arguments.
     */
    @Nonnull
    default ThrowableObjBooleanPredicate<T, X> testThrowsPartially(boolean value1) {
        return (t, value2) -> testThrows(t, value1, value2);
    }

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link ThrowablePredicate} as result.
     *
     * @param value1 The second argument to this predicate used to partially apply this function
     * @param value2 The third argument to this predicate used to partially apply this function
     * @return A {@code ThrowablePredicate} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default ThrowablePredicate<T, X> testThrowsPartially(boolean value1, boolean value2) {
        return t -> testThrows(t, value1, value2);
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
     * Returns a composed {@link ThrowableTriPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed predicate
     * @param <B> The type of the argument to the second given predicate, and of composed predicate
     * @param <C> The type of the argument to the third given predicate, and of composed predicate
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second predicate to apply before this predicate is applied
     * @param before3 The third predicate to apply before this predicate is applied
     * @return A composed {@code ThrowableTriPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableTriPredicate<A, B, C, X> compose(
            @Nonnull ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull ThrowablePredicate<? super B, ? extends X> before2,
            @Nonnull ThrowablePredicate<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> testThrows(before1.applyThrows(a), before2.testThrows(b), before3.testThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableBooleanTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second operator to apply before this predicate is applied
     * @param before3 The third operator to apply before this predicate is applied
     * @return A composed {@code ThrowableBooleanTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanTernaryOperator<X> composeFromBoolean(
            @Nonnull ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableBooleanUnaryOperator<? extends X> before2,
            @Nonnull ThrowableBooleanUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.applyAsBooleanThrows(value2),
                before3.applyAsBooleanThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBytePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second predicate to apply before this predicate is applied
     * @param before3 The third predicate to apply before this predicate is applied
     * @return A composed {@code ThrowableTriBytePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriBytePredicate<X> composeFromByte(
            @Nonnull ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableBytePredicate<? extends X> before2,
            @Nonnull ThrowableBytePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second predicate to apply before this predicate is applied
     * @param before3 The third predicate to apply before this predicate is applied
     * @return A composed {@code ThrowableTriCharPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharPredicate<X> composeFromChar(
            @Nonnull ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableCharPredicate<? extends X> before2,
            @Nonnull ThrowableCharPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoublePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second predicate to apply before this predicate is applied
     * @param before3 The third predicate to apply before this predicate is applied
     * @return A composed {@code ThrowableTriDoublePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoublePredicate<X> composeFromDouble(
            @Nonnull ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableDoublePredicate<? extends X> before2,
            @Nonnull ThrowableDoublePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second predicate to apply before this predicate is applied
     * @param before3 The third predicate to apply before this predicate is applied
     * @return A composed {@code ThrowableTriFloatPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatPredicate<X> composeFromFloat(
            @Nonnull ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableFloatPredicate<? extends X> before2,
            @Nonnull ThrowableFloatPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code int} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second predicate to apply before this predicate is applied
     * @param before3 The third predicate to apply before this predicate is applied
     * @return A composed {@code ThrowableTriIntPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntPredicate<X> composeFromInt(
            @Nonnull ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableIntPredicate<? extends X> before2,
            @Nonnull ThrowableIntPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second predicate to apply before this predicate is applied
     * @param before3 The third predicate to apply before this predicate is applied
     * @return A composed {@code ThrowableTriLongPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongPredicate<X> composeFromLong(
            @Nonnull ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableLongPredicate<? extends X> before2,
            @Nonnull ThrowableLongPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second predicate to apply before this predicate is applied
     * @param before3 The third predicate to apply before this predicate is applied
     * @return A composed {@code ThrowableTriShortPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortPredicate<X> composeFromShort(
            @Nonnull ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull ThrowableShortPredicate<? extends X> before2,
            @Nonnull ThrowableShortPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.testThrows(value2),
                before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableObjBiBooleanFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableObjBiBooleanFunction<T, S, X> andThen(
            @Nonnull ThrowableBooleanFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyThrows(testThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanPredicate} that first applies this predicate to its input, and
     * then applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code ThrowableObjBiBooleanPredicate} that first applies this predicate to its input, and
     * then applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableObjBiBooleanPredicate<T, X> andThenToBoolean(
            @Nonnull ThrowableBooleanUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsBooleanThrows(testThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToByteFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableObjBiBooleanToByteFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToByteFunction<T, X> andThenToByte(
            @Nonnull ThrowableBooleanToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsByteThrows(testThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToCharFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableObjBiBooleanToCharFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToCharFunction<T, X> andThenToChar(
            @Nonnull ThrowableBooleanToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsCharThrows(testThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToDoubleFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableObjBiBooleanToDoubleFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToDoubleFunction<T, X> andThenToDouble(
            @Nonnull ThrowableBooleanToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsDoubleThrows(testThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToFloatFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableObjBiBooleanToFloatFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToFloatFunction<T, X> andThenToFloat(
            @Nonnull ThrowableBooleanToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsFloatThrows(testThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToIntFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableObjBiBooleanToIntFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToIntFunction<T, X> andThenToInt(
            @Nonnull ThrowableBooleanToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsIntThrows(testThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToLongFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableObjBiBooleanToLongFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToLongFunction<T, X> andThenToLong(
            @Nonnull ThrowableBooleanToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsLongThrows(testThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanToShortFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableObjBiBooleanToShortFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableObjBiBooleanToShortFunction<T, X> andThenToShort(
            @Nonnull ThrowableBooleanToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsShortThrows(testThrows(t, value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanConsumer} that fist applies this predicate to its input, and then
     * consumes the result using the given {@link ThrowableBooleanConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableObjBiBooleanConsumer} that first applies this predicate to its input, and then
     * consumes the result using the given {@code ThrowableBooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableObjBiBooleanConsumer<T, X> consume(@Nonnull ThrowableBooleanConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.acceptThrows(testThrows(t, value1, value2));
    }

    /**
     * Returns a {@link ThrowableObjBiBooleanPredicate} that represents the logical negation of this one.
     *
     * @return A {@code ThrowableObjBiBooleanPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default ThrowableObjBiBooleanPredicate<T, X> negate() {
        return (t, value1, value2) -> !testThrows(t, value1, value2);
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanPredicate} that represents a short-circuiting logical AND of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code false}, then the
     * {@code other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableObjBiBooleanPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableObjBiBooleanPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ThrowableObjBiBooleanPredicate} that represents the short-circuiting logical AND of
     * this predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(ThrowableObjBiBooleanPredicate)
     * @see #xor(ThrowableObjBiBooleanPredicate)
     */
    @Nonnull
    default ThrowableObjBiBooleanPredicate<T, X> and(
            @Nonnull ThrowableObjBiBooleanPredicate<? super T, ? extends X> other) {
        Objects.requireNonNull(other);
        return (t, value1, value2) -> testThrows(t, value1, value2) && other.testThrows(t, value1, value2);
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanPredicate} that represents a short-circuiting logical OR of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code
     * other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableObjBiBooleanPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableObjBiBooleanPredicate} that will be logically-ORed with this one
     * @return A composed {@code ThrowableObjBiBooleanPredicate} that represents the short-circuiting logical OR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableObjBiBooleanPredicate)
     * @see #xor(ThrowableObjBiBooleanPredicate)
     */
    @Nonnull
    default ThrowableObjBiBooleanPredicate<T, X> or(
            @Nonnull ThrowableObjBiBooleanPredicate<? super T, ? extends X> other) {
        Objects.requireNonNull(other);
        return (t, value1, value2) -> testThrows(t, value1, value2) || other.testThrows(t, value1, value2);
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanPredicate} that represents a short-circuiting logical XOR of this
     * predicate and another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if
     * evaluation of this {@code ThrowableObjBiBooleanPredicate} throws an exception, the {@code other} predicate will
     * not be evaluated.
     *
     * @param other A {@code ThrowableObjBiBooleanPredicate} that will be logically-XORed with this one
     * @return A composed {@code ThrowableObjBiBooleanPredicate} that represents the short-circuiting logical XOR of
     * this predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableObjBiBooleanPredicate)
     * @see #or(ThrowableObjBiBooleanPredicate)
     */
    @Nonnull
    default ThrowableObjBiBooleanPredicate<T, X> xor(
            @Nonnull ThrowableObjBiBooleanPredicate<? super T, ? extends X> other) {
        Objects.requireNonNull(other);
        return (t, value1, value2) -> testThrows(t, value1, value2) ^ other.testThrows(t, value1, value2);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableObjBiBooleanPredicate}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableObjBiBooleanPredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableObjBiBooleanPredicate<T, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, Boolean, Boolean>, Boolean> cache = new ConcurrentHashMap<>();
            return (ThrowableObjBiBooleanPredicate<T, X> & Memoized) (t, value1, value2) -> {
                return cache.computeIfAbsent(Triple.of(t, value1, value2), ThrowableFunction.of(
                        key -> testThrows(key.getLeft(), key.getMiddle(), key.getRight())));
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriPredicate} which represents this {@link ThrowableObjBiBooleanPredicate}.
     * Thereby the primitive input argument for this predicate is autoboxed. This method provides the possibility to use
     * this {@code ThrowableObjBiBooleanPredicate} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTriPredicate} which represents this {@code ThrowableObjBiBooleanPredicate}.
     */
    @Nonnull
    default ThrowableTriPredicate<T, Boolean, Boolean, X> boxed() {
        return this::testThrows;
    }

    /**
     * Returns a composed {@link ObjBiBooleanPredicate} that applies this predicate to its input and nests the thrown
     * {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link ObjBiBooleanPredicate} that applies this predicate to its input and nests the thrown
     * {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ObjBiBooleanPredicate<T> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ObjBiBooleanPredicate} that applies this predicate to its input and nests the thrown
     * {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ObjBiBooleanPredicate} that applies this predicate to its input and nests the thrown
     * {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ObjBiBooleanPredicate<T> nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        Objects.requireNonNull(mapper);
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ObjBiBooleanPredicate} that first applies this predicate to its input, and then applies
     * the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * predicate.
     *
     * @param recover The operation to apply if this predicate throws a {@code Throwable}
     * @return A composed {@link ObjBiBooleanPredicate} that first applies this predicate to its input, and then applies
     * the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing predicate is {@code null}
     * @implSpec The implementation checks that the returned enclosing predicate from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ObjBiBooleanPredicate<T> recover(
            @Nonnull Function<? super Throwable, ? extends ObjBiBooleanPredicate<? super T>> recover) {
        Objects.requireNonNull(recover);
        return (t, value1, value2) -> {
            try {
                return testThrows(t, value1, value2);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                ObjBiBooleanPredicate<? super T> predicate = recover.apply(throwable);
                Objects.requireNonNull(predicate, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return predicate.test(t, value1, value2);
            }
        };
    }

    /**
     * Returns a composed {@link ObjBiBooleanPredicate} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed predicate behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this predicate in the returned composed
     * predicate by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing predicate.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing predicate variant of this throwable predicate, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed predicate. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed predicate. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed predicate is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed predicate, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed predicate.
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
     * @return A composed {@link ObjBiBooleanPredicate} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ObjBiBooleanPredicate<T> sneakyThrow() {
        return (t, value1, value2) -> {
            try {
                return testThrows(t, value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }
}
