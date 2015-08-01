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
package at.gridtec.lambda4j.predicates.primitives.obj;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of an object-valued and a {@code long}-valued argument. This is the
 * {@code (reference, long)}-consuming specialization of {@link BiPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, long)};
 *
 * @param <T> The type of argument to the predicate
 * @see java.util.function.BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjLongPredicate<T> {

    /**
     * Creates a {@link ObjLongPredicate} which always returns a given value.
     *
     * @param <T> The type of argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ObjLongPredicate} which always returns a given value.
     */
    static <T> ObjLongPredicate<T> constant(boolean ret) {
        return (t, value) -> ret;
    }

    /**
     * Returns a {@link ObjLongPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The first target value with which to compare for equality
     * @param targetValue The second target value with which to compare for equality
     * @return A {@code ObjLongPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, long)
     */
    static <T> ObjLongPredicate<T> isEqual(Object targetRef, long targetValue) {
        return (t, value) -> (t == null ? targetRef == null : t.equals(targetRef)) && (value == targetValue);
    }

    /**
     * Returns a {@link ObjLongPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The first target value with which to compare for equality
     * @param targetValue The second target value with which to compare for equality
     * @return A {@code ObjLongPredicate} that tests if the given arguments are not equal to the ones of this predicate.
     * @see #isEqual(Object, long)
     */
    static <T> ObjLongPredicate<T> isNotEqual(Object targetRef, long targetValue) {
        return (t, value) -> !(t == null ? targetRef == null : t.equals(targetRef)) || (value != targetValue);
    }

    /**
     * Returns a {@link ObjLongPredicate} the always returns {@code true}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ObjLongPredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static <T> ObjLongPredicate<T> alwaysTrue() {
        return (t, value) -> true;
    }

    /**
     * Returns a {@link ObjLongPredicate} the always returns {@code false}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ObjLongPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static <T> ObjLongPredicate<T> alwaysFalse() {
        return (t, value) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean test(T t, long value);

    /**
     * Returns a {@link ObjLongPredicate} that represents the logical negation of this one.
     *
     * @return A {@code ObjLongPredicate} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    default ObjLongPredicate<T> negate() {
        return (t, value) -> !test(t, value);
    }

    /**
     * Returns a composed {@link ObjLongPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ObjLongPredicate} throws an exception, the {@code other} {@code ObjLongPredicate} will not be evaluated.
     *
     * @param other A {@code ObjLongPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ObjLongPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(ObjLongPredicate)
     * @see #xor(ObjLongPredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    default ObjLongPredicate<T> and(final ObjLongPredicate<T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) && other.test(t, value);
    }

    /**
     * Returns a composed {@link ObjLongPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ObjLongPredicate} throws an exception, the {@code other} {@code ObjLongPredicate} will not be evaluated.
     *
     * @param other A {@code ObjLongPredicate} that will be logically-ORed with this one
     * @return A composed {@code ObjLongPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ObjLongPredicate)
     * @see #xor(ObjLongPredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    default ObjLongPredicate<T> or(final ObjLongPredicate<T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) && other.test(t, value);
    }

    /**
     * Returns a composed {@link ObjLongPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ObjLongPredicate} throws an exception, the {@code other} {@code ObjLongPredicate} will not be evaluated.
     *
     * @param other A {@code ObjLongPredicate} that will be logically-XORed with this one
     * @return A composed {@code ObjLongPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ObjLongPredicate)
     * @see #or(ObjLongPredicate)
     */
    default ObjLongPredicate<T> xor(final ObjLongPredicate<T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) ^ other.test(t, value);
    }

    /**
     * Returns a composed {@link BiPredicate} which represents this {@link ObjLongPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method is just convenience to provide the ability to use
     * this {@code ObjLongPredicate} with JRE specific methods, only accepting {@code BiPredicate}.
     *
     * @return A composed {@code BiPredicate} which represents this {@code ObjLongPredicate}.
     */
    default BiPredicate<T, Long> boxed() {
        return this::test;
    }
}
