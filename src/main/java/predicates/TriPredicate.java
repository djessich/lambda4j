/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */

package at.gridtec.internals.lang.function.predicates;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of three arguments. This is the three-arity specialization of
 * {@link Predicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @param <V> The type of the third argument to the predicate
 * @see java.util.function.Predicate
 */
public interface TriPredicate<T, U, V> {

    /**
     * Creates a {@link TriPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code TriPredicate} which always returns a given value.
     */
    static <T, U, V> TriPredicate<T, U, V> constant(boolean ret) {
        return (t, v, u) -> ret;
    }

    /**
     * Returns a {@link TriPredicate} that tests if three arguments are equal according to {@link
     * Objects#equals(Object)} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param targetRef1 The first object reference with which to compare for equality, which may be {@code null}
     * @param targetRef2 The second object reference with which to compare for equality, which may be {@code null}
     * @param targetRef3 The third object reference with which to compare for equality, which may be {@code null}
     * @return A {@code TriPredicate} that tests if two arguments are equal according to {@link Objects#equals(Object,
     * Object)}
     */
    //@formatter:off
    static <T, U, V> TriPredicate<T, U, V> isEqual(final T targetRef1, final U targetRef2, final V targetRef3) {
        return (t, u, v) -> (t == null ? targetRef1 == null : t.equals(targetRef1))
                && (u == null ? targetRef2 == null : u.equals(targetRef2))
                && (v == null ? targetRef3 == null : v.equals(targetRef3));
    }
    //@formatter:on

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param v The third argument to the predicate
     * @return {@code true} if the input arguments match the predicate,
     * otherwise {@code false}
     */
    boolean test(T t, U u, V v);

    /**
     * Returns a {@link TriPredicate} that represents the logical negation of this one.
     *
     * @return A {@code TriPredicate} that represents the logical negation of this one.
     */
    default TriPredicate<T, U, V> negate() {
        return (t, u, v) -> !test(t, u, v);
    }

    /**
     * Returns a composed {@link TriPredicate} that represents a short-circuiting logical AND of this predicate and
     * another.  When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code TriPredicate} throws an exception, the {@code other} {@code TriPredicate} will not be evaluated.
     *
     * @param other A {@code TriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code TriPredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default TriPredicate<T, U, V> and(final TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) && other.test(t, u, v);
    }

    /**
     * Returns a composed {@link TriPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code TriPredicate} throws an exception, the {@code other} {@code TriPredicate} will not be evaluated.
     *
     * @param other A {@code TriPredicate} that will be logically-ORed with this one
     * @return A composed {@code TriPredicate} that represents the short-circuiting logical OR of this predicate and the
     * {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default TriPredicate<T, U, V> or(final TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) && other.test(t, u, v);
    }

    /**
     * Returns a composed {@link TriPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code TriPredicate} throws an exception, the {@code other} {@code TriPredicate} will not be evaluated.
     *
     * @param other A {@code TriPredicate} that will be logically-XORed with this one
     * @return A composed {@code TriPredicate} that represents the short-circuiting logical OR of this predicate and the
     * {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default TriPredicate<T, U, V> xor(final TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) ^ other.test(t, u, v);
    }
}
