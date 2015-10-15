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
 * Represents a predicate (boolean-valued function) of one {@code byte}-valued argument. This is the {@code
 * byte}-consuming primitive type specialization of {@link Predicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(byte)}.
 *
 * @see java.util.function.Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BytePredicate {

    /**
     * Creates a {@link BytePredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BytePredicate} which always returns a given value.
     */
    @Nonnull
    static BytePredicate constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Returns a {@link BytePredicate} that tests if the given argument is equal to the one of this predicate according
     * to {@code value == target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code BytePredicate} that tests if the given argument is equal to the one of this predicate.
     * @see #isNotEqual(byte)
     */
    static BytePredicate isEqual(byte target) {
        return value -> value == target;
    }

    /**
     * Returns a {@link BytePredicate} that tests if the given argument is not equal to the one of this predicate
     * according to {@code value != target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code BytePredicate} that tests if the given argument is not equal to the one of this predicate.
     * @see #isEqual(byte)
     */
    static BytePredicate isNotEqual(byte target) {
        return value -> value != target;
    }

    /**
     * Returns a {@link BytePredicate} that always returns {@code true}.
     *
     * @return A {@link BytePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static BytePredicate alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link BytePredicate} the always returns {@code false}.
     *
     * @return A {@link BytePredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static BytePredicate alwaysFalse() {
        return value -> false;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value The argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean test(byte value);

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
     * Returns a {@link BytePredicate} that represents the logical negation of this one.
     *
     * @return A {@code BytePredicate} that represents the logical negation of this one.
     * @see Predicate#negate()
     */
    default BytePredicate negate() {
        return value -> !test(value);
    }

    /**
     * Returns a composed {@link BytePredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code BytePredicate} throws an exception, the {@code other} {@code BytePredicate} will not be evaluated.
     *
     * @param other A {@code BytePredicate} that will be logically-ANDed with this one
     * @return A composed {@code BytePredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(BytePredicate)
     * @see #xor(BytePredicate)
     * @see Predicate#and(Predicate)
     */
    default BytePredicate and(final BytePredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link BytePredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code BytePredicate} throws an exception, the {@code other} {@code BytePredicate} will not be evaluated.
     *
     * @param other A {@code BytePredicate} that will be logically-ORed with this one
     * @return A composed {@code BytePredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BytePredicate)
     * @see #xor(BytePredicate)
     * @see Predicate#or(Predicate)
     */
    default BytePredicate or(final BytePredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link BytePredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code BytePredicate} throws an exception, the {@code other} {@code BytePredicate} will not be evaluated.
     *
     * @param other A {@code BytePredicate} that will be logically-XORed with this one
     * @return A composed {@code BytePredicate} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BytePredicate)
     * @see #or(BytePredicate)
     */
    default BytePredicate xor(final BytePredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) ^ other.test(value);
    }

    /**
     * Returns a composed {@link Predicate} which represents this {@link BytePredicate}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BytePredicate} with JRE specific methods, only accepting {@code Predicate}.
     *
     * @return A composed {@code Predicate} which represents this {@code BytePredicate}.
     */
    @Nonnull
    default Predicate<Byte> boxed() {
        return this::test;
    }
}