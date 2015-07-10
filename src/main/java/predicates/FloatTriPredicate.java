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

/**
 * Represents a predicate (boolean-valued function) of three {@code float}-valued argument. This is the {@code
 * float}-consuming primitive type specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(float, float, float)}.
 *
 * @see TriPredicate
 */
public interface FloatTriPredicate {

    /**
     * Creates a {@link FloatTriPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatTriPredicate} which always returns a given value.
     */
    static FloatTriPredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     */
    boolean test(float value1, float value2, float value3);

    /**
     * Returns a {@link FloatTriPredicate} that represents the logical negation of this one.
     *
     * @return A {@code FloatTriPredicate} that represents the logical negation of this one.
     */
    default FloatTriPredicate negate() {
        return (value1, value2, value3) -> !test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link FloatTriPredicate} that represents a short-circuiting logical AND of this predicate
     * and another.  When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code FloatTriPredicate} throws an exception, the {@code other} {@code FloatTriPredicate} will not be
     * evaluated.
     *
     * @param other A {@code FloatTriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code FloatTriPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatTriPredicate and(final FloatTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link FloatTriPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code FloatTriPredicate} throws an exception, the {@code other} {@code FloatTriPredicate} will not be
     * evaluated.
     *
     * @param other A {@code FloatTriPredicate} that will be logically-ORed with this one
     * @return A composed {@code FloatTriPredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatTriPredicate or(final FloatTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link FloatTriPredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code FloatTriPredicate} throws an exception, the {@code other} {@code FloatTriPredicate} will not be
     * evaluated.
     *
     * @param other A {@code FloatTriPredicate} that will be logically-XORed with this one
     * @return A composed {@code FloatTriPredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatTriPredicate xor(final FloatTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) ^ other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link FloatTriPredicate}. Thereby the primitive
     * input arguments for this predicate are autoboxed.
     *
     * @return A composed {@code TriPredicate} which represents this {@code FloatTriPredicate}.
     */
    default TriPredicate<Float, Float, Float> boxed() {
        return this::test;
    }
}
