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

import at.gridtec.lambda4j.predicates.TriPredicate;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of two object-valued and a {@code boolean}-valued argument. This is
 * the {@code (reference, reference, boolean)}-consuming specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, Object, boolean)};
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjBooleanPredicate<T, U> {

    /**
     * Creates a {@link BiObjBooleanPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code BiObjBooleanPredicate} which always returns a given value.
     */
    static <T, U> BiObjBooleanPredicate<T, U> constant(boolean ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Returns a {@link BiObjBooleanPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first target value with which to compare for equality
     * @param targetRef2 The second target value with which to compare for equality
     * @param targetValue The third target value with which to compare for equality
     * @return A {@code BiObjBooleanPredicate} that tests if the given arguments are equal to the ones of this
     * predicate.
     * @see #isNotEqual(Object, Object, boolean)
     */
    static <T, U> BiObjBooleanPredicate<T, U> isEqual(Object targetRef1, Object targetRef2, boolean targetValue) {
        return (t, u, value) -> (t == null ? targetRef1 == null : t.equals(targetRef1)) && (t == null ? targetRef2
                == null : t.equals(targetRef2)) && (value == targetValue);
    }

    /**
     * Returns a {@link BiObjBooleanPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate according to {@code value != target} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first target value with which to compare for equality
     * @param targetRef2 The second target value with which to compare for equality
     * @param targetValue The third target value with which to compare for equality
     * @return A {@code BiObjBooleanPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(Object, Object, boolean)
     */
    static <T, U> BiObjBooleanPredicate<T, U> isNotEqual(Object targetRef1, Object targetRef2, boolean targetValue) {
        return (t, u, value) -> !(t == null ? targetRef1 == null : t.equals(targetRef1)) || !(t == null ? targetRef2
                == null : t.equals(targetRef2)) || (value != targetValue);
    }

    /**
     * Returns a {@link BiObjBooleanPredicate} the always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiObjBooleanPredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static <T, U> BiObjBooleanPredicate<T, U> alwaysTrue() {
        return (t, u, value) -> true;
    }

    /**
     * Returns a {@link BiObjBooleanPredicate} the always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiObjBooleanPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static <T, U> BiObjBooleanPredicate<T, U> alwaysFalse() {
        return (t, u, value) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(T t, U u, boolean value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a {@link BiObjBooleanPredicate} that represents the logical negation of this one.
     *
     * @return A {@code BiObjBooleanPredicate} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    default BiObjBooleanPredicate<T, U> negate() {
        return (t, u, value) -> !test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjBooleanPredicate} that represents a short-circuiting logical AND of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code BiObjBooleanPredicate} throws an exception, the {@code other} {@code BiObjBooleanPredicate} will not be
     * evaluated.
     *
     * @param other A {@code BiObjBooleanPredicate} that will be logically-ANDed with this one
     * @return A composed {@code BiObjBooleanPredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(BiObjBooleanPredicate)
     * @see #xor(BiObjBooleanPredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    default BiObjBooleanPredicate<T, U> and(final BiObjBooleanPredicate<T, U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) && other.test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjBooleanPredicate} that represents a short-circuiting logical OR of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code BiObjBooleanPredicate} throws an exception, the {@code other} {@code BiObjBooleanPredicate} will not be
     * evaluated.
     *
     * @param other A {@code BiObjBooleanPredicate} that will be logically-ORed with this one
     * @return A composed {@code BiObjBooleanPredicate} that represents the short-circuiting logical OR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BiObjBooleanPredicate)
     * @see #xor(BiObjBooleanPredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    default BiObjBooleanPredicate<T, U> or(final BiObjBooleanPredicate<T, U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) && other.test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjBooleanPredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation
     * of this {@code BiObjBooleanPredicate} throws an exception, the {@code other} {@code BiObjBooleanPredicate} will
     * not be evaluated.
     *
     * @param other A {@code BiObjBooleanPredicate} that will be logically-XORed with this one
     * @return A composed {@code BiObjBooleanPredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BiObjBooleanPredicate)
     * @see #or(BiObjBooleanPredicate)
     */
    default BiObjBooleanPredicate<T, U> xor(final BiObjBooleanPredicate<T, U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) ^ other.test(t, u, value);
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link BiObjBooleanPredicate}. Thereby the
     * primitive input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriPredicate} which represents this {@code BiObjBooleanPredicate}.
     */
    @Nonnull
    default TriPredicate<T, U, Boolean> boxed() {
        return this::test;
    }
}
