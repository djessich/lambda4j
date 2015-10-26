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
package at.gridtec.lambda4j.predicates.primitives.tri;

import at.gridtec.lambda4j.predicates.TriPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.LongPredicate;

/**
 * Represents a predicate (boolean-valued function) of three {@code long}-valued argument. This is the {@code
 * long}-consuming primitive type specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(long, long, long)}.
 *
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface LongTriPredicate {

    /**
     * Calls the given {@link LongTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code LongTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    static boolean call(@Nonnull final LongTriPredicate predicate, long value1, long value2, long value3) {
        Objects.requireNonNull(predicate);
        return predicate.test(value1, value2, value3);
    }

    /**
     * Creates a {@link LongTriPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link LongPredicate}.
     *
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code LongTriPredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@code LongPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTriPredicate onlyFirst(@Nonnull final LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value1);
    }

    /**
     * Creates a {@link LongTriPredicate} which uses the {@code second} parameter of this one as argument for the given
     * {@link LongPredicate}.
     *
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code LongTriPredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@code LongPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTriPredicate onlySecond(@Nonnull final LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value2);
    }

    /**
     * Creates a {@link LongTriPredicate} which uses the {@code third} parameter of this one as argument for the given
     * {@link LongPredicate}.
     *
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code LongTriPredicate} which uses the {@code third} parameter of this one as argument for the
     * given {@code LongPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTriPredicate onlyThird(@Nonnull final LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value3);
    }

    /**
     * Creates a {@link LongTriPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code LongTriPredicate} which always returns a given value.
     */
    @Nonnull
    static LongTriPredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link LongTriPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code LongTriPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(long, long, long)
     */
    @Nonnull
    static LongTriPredicate isEqual(long target1, long target2, long target3) {
        return (value1, value2, value3) -> (value1 == target1) && (value2 == target2) && (value3 == target3);
    }

    /**
     * Returns a {@link LongTriPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code LongTriPredicate} that tests if the given arguments are not equal to the ones of this predicate.
     * @see #isEqual(long, long, long)
     */
    @Nonnull
    static LongTriPredicate isNotEqual(long target1, long target2, long target3) {
        return (value1, value2, value3) -> (value1 != target1) && (value2 != target2) && (value3 != target3);
    }

    /**
     * Returns a {@link LongTriPredicate} that always returns {@code true}.
     *
     * @return A {@link LongTriPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static LongTriPredicate alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link LongTriPredicate} the always returns {@code false}.
     *
     * @return A {@link LongTriPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static LongTriPredicate alwaysFalse() {
        return (value1, value2, value3) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(long value1, long value2, long value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a {@link LongTriPredicate} that represents the logical negation of this one.
     *
     * @return A {@code LongTriPredicate} that represents the logical negation of this one.
     * @see TriPredicate#negate()
     */
    @Nonnull
    default LongTriPredicate negate() {
        return (value1, value2, value3) -> !test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link LongTriPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code LongTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code LongTriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code LongTriPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(LongTriPredicate)
     * @see #xor(LongTriPredicate)
     * @see TriPredicate#and(TriPredicate)
     */
    @Nonnull
    default LongTriPredicate and(@Nonnull final LongTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link LongTriPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code LongTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code LongTriPredicate} that will be logically-ORed with this one
     * @return A composed {@code LongTriPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(LongTriPredicate)
     * @see #xor(LongTriPredicate)
     * @see TriPredicate#or(TriPredicate)
     */
    @Nonnull
    default LongTriPredicate or(@Nonnull final LongTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link LongTriPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code LongTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code LongTriPredicate} that will be logically-XORed with this one
     * @return A composed {@code LongTriPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(LongTriPredicate)
     * @see #or(LongTriPredicate)
     * @see TriPredicate#xor(TriPredicate)
     */
    @Nonnull
    default LongTriPredicate xor(@Nonnull final LongTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) ^ other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link LongTriPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriPredicate} which represents this {@code LongTriPredicate}.
     */
    @Nonnull
    default TriPredicate<Long, Long, Long> boxed() {
        return this::test;
    }
}
