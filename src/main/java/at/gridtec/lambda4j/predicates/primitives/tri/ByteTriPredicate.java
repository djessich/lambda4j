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

/**
 * Represents a predicate (boolean-valued function) of three {@code byte}-valued argument. This is the {@code
 * byte}-consuming primitive type specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(byte, byte, byte)}.
 *
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteTriPredicate {

    /**
     * Creates a {@link ByteTriPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteTriPredicate} which always returns a given value.
     */
    @Nonnull
    static ByteTriPredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link ByteTriPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code ByteTriPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(byte, byte, byte)
     */
    static ByteTriPredicate isEqual(byte target1, byte target2, byte target3) {
        return (value1, value2, value3) -> (value1 == target1) && (value2 == target2) && (value3 == target3);
    }

    /**
     * Returns a {@link ByteTriPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code ByteTriPredicate} that tests if the given arguments are not equal to the ones of this predicate.
     * @see #isEqual(byte, byte, byte)
     */
    static ByteTriPredicate isNotEqual(byte target1, byte target2, byte target3) {
        return (value1, value2, value3) -> (value1 != target1) && (value2 != target2) && (value3 != target3);
    }

    /**
     * Returns a {@link ByteTriPredicate} that always returns {@code true}.
     *
     * @return A {@link ByteTriPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static ByteTriPredicate alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link ByteTriPredicate} the always returns {@code false}.
     *
     * @return A {@link ByteTriPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static ByteTriPredicate alwaysFalse() {
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
    boolean test(byte value1, byte value2, byte value3);

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
     * Returns a {@link ByteTriPredicate} that represents the logical negation of this one.
     *
     * @return A {@code ByteTriPredicate} that represents the logical negation of this one.
     * @see TriPredicate#negate()
     */
    default ByteTriPredicate negate() {
        return (value1, value2, value3) -> !test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link ByteTriPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ByteTriPredicate} throws an exception, the {@code other} {@code ByteTriPredicate} will not be evaluated.
     *
     * @param other A {@code ByteTriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ByteTriPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(ByteTriPredicate)
     * @see #xor(ByteTriPredicate)
     * @see TriPredicate#and(TriPredicate)
     */
    default ByteTriPredicate and(final ByteTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link ByteTriPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ByteTriPredicate} throws an exception, the {@code other} {@code ByteTriPredicate} will not be evaluated.
     *
     * @param other A {@code ByteTriPredicate} that will be logically-ORed with this one
     * @return A composed {@code ByteTriPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ByteTriPredicate)
     * @see #xor(ByteTriPredicate)
     * @see TriPredicate#or(TriPredicate)
     */
    default ByteTriPredicate or(final ByteTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link ByteTriPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code ByteTriPredicate} throws an exception, the {@code other} {@code ByteTriPredicate} will not be
     * evaluated.
     *
     * @param other A {@code ByteTriPredicate} that will be logically-XORed with this one
     * @return A composed {@code ByteTriPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ByteTriPredicate)
     * @see #or(ByteTriPredicate)
     * @see TriPredicate#xor(TriPredicate)
     */
    default ByteTriPredicate xor(final ByteTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) ^ other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link ByteTriPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriPredicate} which represents this {@code ByteTriPredicate}.
     */
    @Nonnull
    default TriPredicate<Byte, Byte, Byte> boxed() {
        return this::test;
    }
}
