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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of two object-valued and a {@code double}-valued argument. This is
 * the {@code (reference, reference, double)}-consuming specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, Object, double)};
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjDoublePredicate<T, U> {

    /**
     * Creates a {@link BiObjDoublePredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code BiObjDoublePredicate} which always returns a given value.
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> constant(boolean ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Returns a {@link BiObjDoublePredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first target value with which to compare for equality
     * @param targetRef2 The second target value with which to compare for equality
     * @param targetValue The third target value with which to compare for equality
     * @return A {@code BiObjDoublePredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, Object, double)
     */
    static <T, U> BiObjDoublePredicate<T, U> isEqual(Object targetRef1, Object targetRef2, double targetValue) {
        return (t, u, value) -> (t == null ? targetRef1 == null : t.equals(targetRef1)) && (t == null ? targetRef2
                == null : t.equals(targetRef2)) && (value == targetValue);
    }

    /**
     * Returns a {@link BiObjDoublePredicate} that tests if the given arguments are not equal to the ones of this
     * predicate according to {@code value != target} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first target value with which to compare for equality
     * @param targetRef2 The second target value with which to compare for equality
     * @param targetValue The third target value with which to compare for equality
     * @return A {@code BiObjDoublePredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(Object, Object, double)
     */
    static <T, U> BiObjDoublePredicate<T, U> isNotEqual(Object targetRef1, Object targetRef2, double targetValue) {
        return (t, u, value) -> !(t == null ? targetRef1 == null : t.equals(targetRef1)) || !(t == null ? targetRef2
                == null : t.equals(targetRef2)) || (value != targetValue);
    }

    /**
     * Returns a {@link BiObjDoublePredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiObjDoublePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> alwaysTrue() {
        return (t, u, value) -> true;
    }

    /**
     * Returns a {@link BiObjDoublePredicate} the always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiObjDoublePredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> alwaysFalse() {
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
    boolean test(T t, U u, double value);

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
     * Returns a {@link BiObjDoublePredicate} that represents the logical negation of this one.
     *
     * @return A {@code BiObjDoublePredicate} that represents the logical negation of this one.
     * @see TriPredicate#negate()
     */
    @Nonnull
    default BiObjDoublePredicate<T, U> negate() {
        return (t, u, value) -> !test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjDoublePredicate} that represents a short-circuiting logical AND of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code BiObjDoublePredicate} throws an exception, the {@code other} {@code BiObjDoublePredicate} will not be
     * evaluated.
     *
     * @param other A {@code BiObjDoublePredicate} that will be logically-ANDed with this one
     * @return A composed {@code BiObjDoublePredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(BiObjDoublePredicate)
     * @see #xor(BiObjDoublePredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    default BiObjDoublePredicate<T, U> and(final BiObjDoublePredicate<T, U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) && other.test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjDoublePredicate} that represents a short-circuiting logical OR of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code BiObjDoublePredicate} throws an exception, the {@code other} {@code BiObjDoublePredicate} will not be
     * evaluated.
     *
     * @param other A {@code BiObjDoublePredicate} that will be logically-ORed with this one
     * @return A composed {@code BiObjDoublePredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BiObjDoublePredicate)
     * @see #xor(BiObjDoublePredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    default BiObjDoublePredicate<T, U> or(final BiObjDoublePredicate<T, U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) && other.test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjDoublePredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation
     * of this {@code BiObjDoublePredicate} throws an exception, the {@code other} {@code BiObjDoublePredicate} will not
     * be evaluated.
     *
     * @param other A {@code BiObjDoublePredicate} that will be logically-XORed with this one
     * @return A composed {@code BiObjDoublePredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BiObjDoublePredicate)
     * @see #or(BiObjDoublePredicate)
     */
    default BiObjDoublePredicate<T, U> xor(final BiObjDoublePredicate<T, U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) ^ other.test(t, u, value);
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link BiObjDoublePredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriPredicate} which represents this {@code BiObjDoublePredicate}.
     */
    @Nonnull
    default TriPredicate<T, U, Double> boxed() {
        return this::test;
    }
}
