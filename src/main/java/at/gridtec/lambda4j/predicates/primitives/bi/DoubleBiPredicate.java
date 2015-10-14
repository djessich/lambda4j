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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of two {@code double}-valued argument. This is the {@code
 * double}-consuming primitive type specialization of {@link BiPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(double, double)}.
 *
 * @see BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleBiPredicate {

    /**
     * Creates a {@link DoubleBiPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code DoubleBiPredicate} which always returns a given value.
     */
    @Nonnull
    static DoubleBiPredicate constant(boolean ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link DoubleBiPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @return A {@code DoubleBiPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(double, double)
     */
    static DoubleBiPredicate isEqual(double target1, double target2) {
        return (value1, value2) -> (value1 == target1) && (value2 == target2);
    }

    /**
     * Returns a {@link DoubleBiPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @return A {@code DoubleBiPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(double, double)
     */
    static DoubleBiPredicate isNotEqual(double target1, double target2) {
        return (value1, value2) -> (value1 != target1) || (value2 != target2);
    }

    /**
     * Returns a {@link DoubleBiPredicate} the always returns {@code true}.
     *
     * @return A {@link DoubleBiPredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static DoubleBiPredicate alwaysTrue() {
        return (value1, value2) -> true;
    }

    /**
     * Returns a {@link DoubleBiPredicate} the always returns {@code false}.
     *
     * @return A {@link DoubleBiPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static DoubleBiPredicate alwaysFalse() {
        return (value1, value2) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(double value1, double value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a {@link DoubleBiPredicate} that represents the logical negation of this one.
     *
     * @return A {@code DoubleBiPredicate} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    default DoubleBiPredicate negate() {
        return (value1, value2) -> !test(value1, value2);
    }

    /**
     * Returns a composed {@link DoubleBiPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code DoubleBiPredicate} throws an exception, the {@code other} {@code DoubleBiPredicate} will not be
     * evaluated.
     *
     * @param other A {@code DoubleBiPredicate} that will be logically-ANDed with this one
     * @return A composed {@code DoubleBiPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(DoubleBiPredicate)
     * @see #xor(DoubleBiPredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    default DoubleBiPredicate and(final DoubleBiPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) && other.test(value1, value2);
    }

    /**
     * Returns a composed {@link DoubleBiPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code DoubleBiPredicate} throws an exception, the {@code other} {@code DoubleBiPredicate} will not be
     * evaluated.
     *
     * @param other A {@code DoubleBiPredicate} that will be logically-ORed with this one
     * @return A composed {@code DoubleBiPredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(DoubleBiPredicate)
     * @see #xor(DoubleBiPredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    default DoubleBiPredicate or(final DoubleBiPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) && other.test(value1, value2);
    }

    /**
     * Returns a composed {@link DoubleBiPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code DoubleBiPredicate} throws an exception, the {@code other} {@code DoubleBiPredicate} will not be
     * evaluated.
     *
     * @param other A {@code DoubleBiPredicate} that will be logically-XORed with this one
     * @return A composed {@code DoubleBiPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(DoubleBiPredicate)
     * @see #or(DoubleBiPredicate)
     */
    default DoubleBiPredicate xor(final DoubleBiPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) ^ other.test(value1, value2);
    }

    /**
     * Returns a composed {@link BiPredicate} which represents this {@link DoubleBiPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method is just convenience to provide the ability to use
     * this {@code DoubleBiPredicate} with JRE specific methods, only accepting {@code BiPredicate}.
     *
     * @return A composed {@code BiPredicate} which represents this {@code DoubleBiPredicate}.
     */
    @Nonnull
    default BiPredicate<Double, Double> boxed() {
        return this::test;
    }
}
