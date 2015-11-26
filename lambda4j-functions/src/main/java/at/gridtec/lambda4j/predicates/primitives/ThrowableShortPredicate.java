/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.predicates.primitives;

import at.gridtec.lambda4j.predicates.ThrowablePredicate;
import at.gridtec.lambda4j.supplier.ThrowableBooleanSupplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one {@code short}-valued argument which is able to throw any
 * {@link Throwable}. This is the {@code short}-consuming primitive type specialization of {@link ThrowablePredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(short)}.
 *
 * @see Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableShortPredicate {

    /**
     * Calls the given {@link ThrowableShortPredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code ThrowableShortPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @throws Throwable Any throwable from the given predicates action
     */
    static boolean call(@Nonnull final ThrowableShortPredicate predicate, short value) throws Throwable {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(value);
    }

    /**
     * Creates a {@link ThrowableShortPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableShortPredicate} which always returns a given value.
     */
    @Nonnull
    static ThrowableShortPredicate constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Returns a {@link ThrowableShortPredicate} that tests if the given argument is equal to the one of this predicate
     * according to {@code value == target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code ThrowableShortPredicate} that tests if the given argument is equal to the one of this predicate.
     * @see #isNotEqual(short)
     */
    @Nonnull
    static ThrowableShortPredicate isEqual(short target) {
        return value -> value == target;
    }

    /**
     * Returns a {@link ThrowableShortPredicate} that tests if the given argument is not equal to the one of this
     * predicate according to {@code value != target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code ThrowableShortPredicate} that tests if the given argument is not equal to the one of this
     * predicate.
     * @see #isEqual(short)
     */
    @Nonnull
    static ThrowableShortPredicate isNotEqual(short target) {
        return value -> value != target;
    }

    /**
     * Returns a {@link ThrowableShortPredicate} that always returns {@code true}.
     *
     * @return A {@link ThrowableShortPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static ThrowableShortPredicate alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link ThrowableShortPredicate} the always returns {@code false}.
     *
     * @return A {@link ThrowableShortPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static ThrowableShortPredicate alwaysFalse() {
        return value -> false;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value The argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean testThrows(short value) throws Throwable;

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a {@link ThrowableShortPredicate} that represents the logical negation of this one.
     *
     * @return A {@code ThrowableShortPredicate} that represents the logical negation of this one.
     * @see ThrowablePredicate#negate()
     */
    @Nonnull
    default ThrowableShortPredicate negate() {
        return value -> !testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowableShortPredicate} that represents a short-circuiting logical AND of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code false}, then the
     * {@code other} predicate is not evaluated.
     * <p>
     * If evaluation of this {@code ThrowableShortPredicate} throws an exception, the {@code other} predicate will not
     * be evaluated.
     *
     * @param other A {@code ThrowableShortPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ThrowableShortPredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(ThrowableShortPredicate)
     * @see #xor(ThrowableShortPredicate)
     */
    @Nonnull
    default ThrowableShortPredicate and(@Nonnull final ThrowableShortPredicate other) {
        Objects.requireNonNull(other);
        return value -> testThrows(value) && other.testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowableShortPredicate} that represents a short-circuiting logical OR of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code
     * other} predicate is not evaluated.
     * <p>
     * If evaluation of this {@code ThrowableShortPredicate} throws an exception, the {@code other} predicate will not
     * be evaluated.
     *
     * @param other A {@code ThrowableShortPredicate} that will be logically-ORed with this one
     * @return A composed {@code ThrowableShortPredicate} that represents the short-circuiting logical OR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ThrowableShortPredicate)
     * @see #xor(ThrowableShortPredicate)
     */
    @Nonnull
    default ThrowableShortPredicate or(@Nonnull final ThrowableShortPredicate other) {
        Objects.requireNonNull(other);
        return value -> testThrows(value) && other.testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowableShortPredicate} that represents a short-circuiting logical XOR of this
     * predicate and another. If evaluation of this {@code ThrowableShortPredicate} throws an exception, the {@code
     * other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableShortPredicate} that will be logically-XORed with this one
     * @return A composed {@code ThrowableShortPredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ThrowableShortPredicate)
     * @see #or(ThrowableShortPredicate)
     */
    @Nonnull
    default ThrowableShortPredicate xor(@Nonnull final ThrowableShortPredicate other) {
        Objects.requireNonNull(other);
        return value -> testThrows(value) ^ other.testThrows(value);
    }

    /**
     * Applies this predicate partially to one argument. The result is an operation of arity {@code 0};
     *
     * @param value The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default ThrowableBooleanSupplier partial(short value) {
        return () -> testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowablePredicate} which represents this {@link ThrowableShortPredicate}. Thereby the
     * primitive input argument for this predicate is autoboxed. This method is just convenience to provide the ability
     * to use this {@code ThrowableShortPredicate} with JRE specific methods, only accepting {@code
     * ThrowablePredicate}.
     *
     * @return A composed {@code ThrowablePredicate} which represents this {@code ThrowableShortPredicate}.
     */
    @Nonnull
    default ThrowablePredicate<Short> boxed() {
        return this::testThrows;
    }
}