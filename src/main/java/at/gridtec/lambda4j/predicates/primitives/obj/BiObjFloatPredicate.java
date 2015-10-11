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

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of two object-valued and a {@code float}-valued argument. This is
 * the {@code (reference, reference, float)}-consuming specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, Object, float)};
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjFloatPredicate<T, U> {

    /**
     * Creates a {@link BiObjFloatPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code BiObjFloatPredicate} which always returns a given value.
     */
    static <T, U> BiObjFloatPredicate<T, U> constant(boolean ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Returns a {@link BiObjFloatPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first target value with which to compare for equality
     * @param targetRef2 The second target value with which to compare for equality
     * @param targetValue The third target value with which to compare for equality
     * @return A {@code BiObjFloatPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, Object, float)
     */
    static <T, U> BiObjFloatPredicate<T, U> isEqual(Object targetRef1, Object targetRef2, float targetValue) {
        return (t, u, value) -> (t == null ? targetRef1 == null : t.equals(targetRef1)) && (t == null ? targetRef2
                == null : t.equals(targetRef2)) && (value == targetValue);
    }

    /**
     * Returns a {@link BiObjFloatPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate according to {@code value != target} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first target value with which to compare for equality
     * @param targetRef2 The second target value with which to compare for equality
     * @param targetValue The third target value with which to compare for equality
     * @return A {@code BiObjFloatPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(Object, Object, float)
     */
    static <T, U> BiObjFloatPredicate<T, U> isNotEqual(Object targetRef1, Object targetRef2, float targetValue) {
        return (t, u, value) -> !(t == null ? targetRef1 == null : t.equals(targetRef1)) || !(t == null ? targetRef2
                == null : t.equals(targetRef2)) || (value != targetValue);
    }

    /**
     * Returns a {@link BiObjFloatPredicate} the always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiObjFloatPredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static <T, U> BiObjFloatPredicate<T, U> alwaysTrue() {
        return (t, u, value) -> true;
    }

    /**
     * Returns a {@link BiObjFloatPredicate} the always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiObjFloatPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static <T, U> BiObjFloatPredicate<T, U> alwaysFalse() {
        return (t, u, value) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean test(T t, U u, float value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a {@link BiObjFloatPredicate} that represents the logical negation of this one.
     *
     * @return A {@code BiObjFloatPredicate} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    default BiObjFloatPredicate<T, U> negate() {
        return (t, u, value) -> !test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjFloatPredicate} that represents a short-circuiting logical AND of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code BiObjFloatPredicate} throws an exception, the {@code other} {@code BiObjFloatPredicate} will not be
     * evaluated.
     *
     * @param other A {@code BiObjFloatPredicate} that will be logically-ANDed with this one
     * @return A composed {@code BiObjFloatPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(BiObjFloatPredicate)
     * @see #xor(BiObjFloatPredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    default BiObjFloatPredicate<T, U> and(final BiObjFloatPredicate<T, U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) && other.test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjFloatPredicate} that represents a short-circuiting logical OR of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code BiObjFloatPredicate} throws an exception, the {@code other} {@code BiObjFloatPredicate} will not be
     * evaluated.
     *
     * @param other A {@code BiObjFloatPredicate} that will be logically-ORed with this one
     * @return A composed {@code BiObjFloatPredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BiObjFloatPredicate)
     * @see #xor(BiObjFloatPredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    default BiObjFloatPredicate<T, U> or(final BiObjFloatPredicate<T, U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) && other.test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjFloatPredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation
     * of this {@code BiObjFloatPredicate} throws an exception, the {@code other} {@code BiObjFloatPredicate} will not
     * be evaluated.
     *
     * @param other A {@code BiObjFloatPredicate} that will be logically-XORed with this one
     * @return A composed {@code BiObjFloatPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BiObjFloatPredicate)
     * @see #or(BiObjFloatPredicate)
     */
    default BiObjFloatPredicate<T, U> xor(final BiObjFloatPredicate<T, U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) ^ other.test(t, u, value);
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link BiObjFloatPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method is just convenience to provide the ability to use
     * this {@code BiObjFloatPredicate} with JRE specific methods, only accepting {@code TriPredicate}.
     *
     * @return A composed {@code TriPredicate} which represents this {@code BiObjFloatPredicate}.
     */
    default TriPredicate<T, U, Float> boxed() {
        return this::test;
    }
}
