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
 * Represents a predicate (boolean-valued function) of one {@code float}-valued argument. This is the {@code
 * float}-consuming primitive type specialization of {@link Predicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(float)}.
 *
 * @see java.util.function.Predicate
 */
public interface FloatPredicate {

    /**
     * Creates a {@link FloatPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatPredicate} which always returns a given value.
     */
    static FloatPredicate constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param value The argument for the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     */
    boolean test(float value);

    /**
     * Returns a {@link FloatPredicate} that represents the logical negation of this one.
     *
     * @return A {@code FloatPredicate} that represents the logical negation of this one.
     */
    default FloatPredicate negate() {
        return (value) -> !test(value);
    }

    /**
     * Returns a composed {@link FloatPredicate} that represents a short-circuiting logical AND of this predicate and
     * another.  When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code FloatPredicate} throws an exception, the {@code other} {@code FloatPredicate} will not be evaluated.
     *
     * @param other A {@code FloatPredicate} that will be logically-ANDed with this one
     * @return A composed {@code FloatPredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatPredicate and(final FloatPredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link FloatPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code FloatPredicate} throws an exception, the {@code other} {@code FloatPredicate} will not be evaluated.
     *
     * @param other A {@code FloatPredicate} that will be logically-ORed with this one
     * @return A composed {@code FloatPredicate} that represents the short-circuiting logical OR of this predicate and
     * the
     * {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatPredicate or(final FloatPredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link FloatPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code FloatPredicate} throws an exception, the {@code other} {@code FloatPredicate} will not be evaluated.
     *
     * @param other A {@code FloatPredicate} that will be logically-XORed with this one
     * @return A composed {@code FloatPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatPredicate xor(final FloatPredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) ^ other.test(value);
    }

    /**
     * Returns a composed {@link Predicate} which represents this {@link FloatPredicate}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatPredicate} with JRE specific methods, only accepting {@code Predicate}.
     *
     * @return A composed {@code Predicate} which represents this {@code FloatPredicate}.
     */
    default Predicate<Float> boxed() {
        return this::test;
    }
}
