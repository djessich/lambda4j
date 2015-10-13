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
package at.gridtec.lambda4j.predicates.primitives.bi;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of two {@code int}-valued argument. This is the {@code
 * int}-consuming primitive type specialization of {@link BiPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(int, int)}.
 *
 * @see BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface IntBiPredicate {

    /**
     * Creates a {@link IntBiPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code IntBiPredicate} which always returns a given value.
     */
    static IntBiPredicate constant(boolean ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link IntBiPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @return A {@code IntBiPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(int, int)
     */
    static IntBiPredicate isEqual(int target1, int target2) {
        return (value1, value2) -> (value1 == target1) && (value2 == target2);
    }

    /**
     * Returns a {@link IntBiPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @return A {@code IntBiPredicate} that tests if the given arguments are not equal to the ones of this predicate.
     * @see #isEqual(int, int)
     */
    static IntBiPredicate isNotEqual(int target1, int target2) {
        return (value1, value2) -> (value1 != target1) || (value2 != target2);
    }

    /**
     * Returns a {@link IntBiPredicate} the always returns {@code true}.
     *
     * @return A {@link IntBiPredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static IntBiPredicate alwaysTrue() {
        return (value1, value2) -> true;
    }

    /**
     * Returns a {@link IntBiPredicate} the always returns {@code false}.
     *
     * @return A {@link IntBiPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static IntBiPredicate alwaysFalse() {
        return (value1, value2) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(int value1, int value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a {@link IntBiPredicate} that represents the logical negation of this one.
     *
     * @return A {@code IntBiPredicate} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    default IntBiPredicate negate() {
        return (value1, value2) -> !test(value1, value2);
    }

    /**
     * Returns a composed {@link IntBiPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code IntBiPredicate} throws an exception, the {@code other} {@code IntBiPredicate} will not be evaluated.
     *
     * @param other A {@code IntBiPredicate} that will be logically-ANDed with this one
     * @return A composed {@code IntBiPredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(IntBiPredicate)
     * @see #xor(IntBiPredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    default IntBiPredicate and(final IntBiPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) && other.test(value1, value2);
    }

    /**
     * Returns a composed {@link IntBiPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code IntBiPredicate} throws an exception, the {@code other} {@code IntBiPredicate} will not be evaluated.
     *
     * @param other A {@code IntBiPredicate} that will be logically-ORed with this one
     * @return A composed {@code IntBiPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(IntBiPredicate)
     * @see #xor(IntBiPredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    default IntBiPredicate or(final IntBiPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) && other.test(value1, value2);
    }

    /**
     * Returns a composed {@link IntBiPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code IntBiPredicate} throws an exception, the {@code other} {@code IntBiPredicate} will not be evaluated.
     *
     * @param other A {@code IntBiPredicate} that will be logically-XORed with this one
     * @return A composed {@code IntBiPredicate} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(IntBiPredicate)
     * @see #or(IntBiPredicate)
     */
    default IntBiPredicate xor(final IntBiPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) ^ other.test(value1, value2);
    }

    /**
     * Returns a composed {@link BiPredicate} which represents this {@link IntBiPredicate}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code IntBiPredicate} with JRE specific methods, only accepting {@code BiPredicate}.
     *
     * @return A composed {@code BiPredicate} which represents this {@code IntBiPredicate}.
     */
    @Nonnull
    default BiPredicate<Integer, Integer> boxed() {
        return this::test;
    }
}
