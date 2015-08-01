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
 * Represents a predicate (boolean-valued function) of an object-valued and a {@code double}-valued argument. This is
 * the {@code (reference, double)}-consuming specialization of {@link BiPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, double)};
 *
 * @param <T> The type of argument to the predicate
 * @see java.util.function.BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjDoublePredicate<T> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ObjDoublePredicate}. This is a convenience method
     * in case the given {@link ObjDoublePredicate} is ambiguous for the compiler. This might happen for overloaded
     * methods accepting different functional interfaces. The given {@code ObjDoublePredicate} is returned as-is.
     *
     * @param <T> The type of argument to the predicate
     * @param lambda The {@code ObjDoublePredicate} which should be returned as-is.
     * @return The given {@code ObjDoublePredicate} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ObjDoublePredicate<T> wrap(final ObjDoublePredicate<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ObjDoublePredicate} from the given {@link ObjDoublePredicate}. This method is just convenience
     * to provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param <T> The type of argument to the predicate
     * @param lambda A {@code ObjDoublePredicate} which should be mapped to its throwable counterpart
     * @return A {@code ObjDoublePredicate} from the given {@code ObjDoublePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ObjDoublePredicate<T> from(final ObjDoublePredicate<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::test;
    }

    /**
     * Creates a {@link ObjDoublePredicate} which always returns a given value.
     *
     * @param <T> The type of argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ObjDoublePredicate} which always returns a given value.
     */
    static <T> ObjDoublePredicate<T> constant(boolean ret) {
        return (t, value) -> ret;
    }

    /**
     * Returns a {@link ObjDoublePredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The first target value with which to compare for equality
     * @param targetValue The second target value with which to compare for equality
     * @return A {@code ObjDoublePredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, double)
     */
    static <T> ObjDoublePredicate<T> isEqual(Object targetRef, double targetValue) {
        return (t, value) -> (t == null ? targetRef == null : t.equals(targetRef)) && (value == targetValue);
    }

    /**
     * Returns a {@link ObjDoublePredicate} that tests if the given arguments are not equal to the ones of this
     * predicate according to {@code value != target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The first target value with which to compare for equality
     * @param targetValue The second target value with which to compare for equality
     * @return A {@code ObjDoublePredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(Object, double)
     */
    static <T> ObjDoublePredicate<T> isNotEqual(Object targetRef, double targetValue) {
        return (t, value) -> !(t == null ? targetRef == null : t.equals(targetRef)) || (value != targetValue);
    }

    /**
     * Returns a {@link ObjDoublePredicate} the always returns {@code true}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ObjDoublePredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static <T> ObjDoublePredicate<T> alwaysTrue() {
        return (t, value) -> true;
    }

    /**
     * Returns a {@link ObjDoublePredicate} the always returns {@code false}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ObjDoublePredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static <T> ObjDoublePredicate<T> alwaysFalse() {
        return (t, value) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean test(T t, double value);

    /**
     * Returns a {@link ObjDoublePredicate} that represents the logical negation of this one.
     *
     * @return A {@code ObjDoublePredicate} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    default ObjDoublePredicate<T> negate() {
        return (t, value) -> !test(t, value);
    }

    /**
     * Returns a composed {@link ObjDoublePredicate} that represents a short-circuiting logical AND of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ObjDoublePredicate} throws an exception, the {@code other} {@code ObjDoublePredicate} will not be
     * evaluated.
     *
     * @param other A {@code ObjDoublePredicate} that will be logically-ANDed with this one
     * @return A composed {@code ObjDoublePredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(ObjDoublePredicate)
     * @see #xor(ObjDoublePredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    default ObjDoublePredicate<T> and(final ObjDoublePredicate<T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) && other.test(t, value);
    }

    /**
     * Returns a composed {@link ObjDoublePredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ObjDoublePredicate} throws an exception, the {@code other} {@code ObjDoublePredicate} will not be
     * evaluated.
     *
     * @param other A {@code ObjDoublePredicate} that will be logically-ORed with this one
     * @return A composed {@code ObjDoublePredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ObjDoublePredicate)
     * @see #xor(ObjDoublePredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    default ObjDoublePredicate<T> or(final ObjDoublePredicate<T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) && other.test(t, value);
    }

    /**
     * Returns a composed {@link ObjDoublePredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ObjDoublePredicate} throws an exception, the {@code other} {@code ObjDoublePredicate} will not be
     * evaluated.
     *
     * @param other A {@code ObjDoublePredicate} that will be logically-XORed with this one
     * @return A composed {@code ObjDoublePredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ObjDoublePredicate)
     * @see #or(ObjDoublePredicate)
     */
    default ObjDoublePredicate<T> xor(final ObjDoublePredicate<T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) ^ other.test(t, value);
    }

    /**
     * Returns a composed {@link BiPredicate} which represents this {@link ObjDoublePredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method is just convenience to provide the ability to use
     * this {@code ObjDoublePredicate} with JRE specific methods, only accepting {@code BiPredicate}.
     *
     * @return A composed {@code BiPredicate} which represents this {@code ObjDoublePredicate}.
     */
    default BiPredicate<T, Double> boxed() {
        return this::test;
    }
}
