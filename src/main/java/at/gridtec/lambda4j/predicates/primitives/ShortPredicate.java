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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one {@code short}-valued argument. This is the {@code
 * short}-consuming primitive type specialization of {@link Predicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(short)}.
 *
 * @see java.util.function.Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortPredicate {

    /**
     * Creates a {@link ShortPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortPredicate} which always returns a given value.
     */
    @Nonnull
    static ShortPredicate constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Returns a {@link ShortPredicate} that tests if the given argument is equal to the one of this predicate according
     * to {@code value == target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code ShortPredicate} that tests if the given argument is equal to the one of this predicate.
     * @see #isNotEqual(short)
     */
    static ShortPredicate isEqual(short target) {
        return value -> value == target;
    }

    /**
     * Returns a {@link ShortPredicate} that tests if the given argument is not equal to the one of this predicate
     * according to {@code value != target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code ShortPredicate} that tests if the given argument is not equal to the one of this predicate.
     * @see #isEqual(short)
     */
    static ShortPredicate isNotEqual(short target) {
        return value -> value != target;
    }

    /**
     * Returns a {@link ShortPredicate} the always returns {@code true}.
     *
     * @return A {@link ShortPredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static ShortPredicate alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link ShortPredicate} the always returns {@code false}.
     *
     * @return A {@link ShortPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static ShortPredicate alwaysFalse() {
        return value -> false;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value The argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean test(short value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a {@link ShortPredicate} that represents the logical negation of this one.
     *
     * @return A {@code ShortPredicate} that represents the logical negation of this one.
     * @see Predicate#negate()
     */
    default ShortPredicate negate() {
        return value -> !test(value);
    }

    /**
     * Returns a composed {@link ShortPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ShortPredicate} throws an exception, the {@code other} {@code ShortPredicate} will not be evaluated.
     *
     * @param other A {@code ShortPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ShortPredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(ShortPredicate)
     * @see #xor(ShortPredicate)
     * @see Predicate#and(Predicate)
     */
    default ShortPredicate and(final ShortPredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link ShortPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ShortPredicate} throws an exception, the {@code other} {@code ShortPredicate} will not be evaluated.
     *
     * @param other A {@code ShortPredicate} that will be logically-ORed with this one
     * @return A composed {@code ShortPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ShortPredicate)
     * @see #xor(ShortPredicate)
     * @see Predicate#or(Predicate)
     */
    default ShortPredicate or(final ShortPredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link ShortPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code ShortPredicate} throws an exception, the {@code other} {@code ShortPredicate} will not be evaluated.
     *
     * @param other A {@code ShortPredicate} that will be logically-XORed with this one
     * @return A composed {@code ShortPredicate} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ShortPredicate)
     * @see #or(ShortPredicate)
     */
    default ShortPredicate xor(final ShortPredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) ^ other.test(value);
    }

    /**
     * Returns a composed {@link Predicate} which represents this {@link ShortPredicate}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortPredicate} with JRE specific methods, only accepting {@code Predicate}.
     *
     * @return A composed {@code Predicate} which represents this {@code ShortPredicate}.
     */
    @Nonnull
    default Predicate<Short> boxed() {
        return this::test;
    }
}