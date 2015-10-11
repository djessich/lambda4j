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
package at.gridtec.lambda4j.operators.ternary;

import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.predicates.TriPredicate;

import java.util.Objects;

/**
 * Represents an operation on a two {@code boolean}-valued operands and producing a {@code boolean}-valued result. This
 * is the primitive type specialization of {@link TernaryOperator} for {@code boolean}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(boolean, boolean, boolean)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanTernaryOperator {

    /**
     * Creates a {@link BooleanTernaryOperator} which always returns a given value.
     *
     * @param r The return value for the constant
     * @return A {@code BooleanTernaryOperator} which always returns a given value.
     */
    static BooleanTernaryOperator constant(boolean r) {
        return (left, middle, right) -> r;
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code BooleanTernaryOperator} which uses the {@code left} parameter as argument for the given {@code
     * BooleanUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static BooleanTernaryOperator onlyLeft(final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsBoolean(left);
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code BooleanTernaryOperator} which uses the {@code middle} parameter as argument for the given {@code
     * BooleanUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static BooleanTernaryOperator onlyMiddle(final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsBoolean(middle);
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code BooleanTernaryOperator} which uses the {@code right} parameter as argument for the given {@code
     * BooleanUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static BooleanTernaryOperator onlyRight(final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsBoolean(right);
    }

    /**
     * Returns a {@link BooleanTernaryOperator} that tests if the given arguments are equal to the ones of this operator
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code BooleanTernaryOperator} that tests if the given arguments are equal to the ones of this
     * operator.
     * @see #isNotEqual(boolean, boolean, boolean)
     */
    static BooleanTernaryOperator isEqual(boolean target1, boolean target2, boolean target3) {
        return (value1, value2, value3) -> (value1 == target1) && (value2 == target2) && (value3 == target3);
    }

    /**
     * Returns a {@link BooleanTernaryOperator} that tests if the given arguments are not equal to the ones of this
     * operator according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code BooleanTernaryOperator} that tests if the given arguments are not equal to the ones of this
     * operator.
     * @see #isEqual(boolean, boolean, boolean)
     */
    static BooleanTernaryOperator isNotEqual(boolean target1, boolean target2, boolean target3) {
        return (value1, value2, value3) -> (value1 != target1) && (value2 != target2) && (value3 != target3);
    }

    /**
     * Returns a {@link BooleanTernaryOperator} the always returns {@code true}.
     *
     * @return A {@link BooleanTernaryOperator} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static BooleanTernaryOperator alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link BooleanTernaryOperator} the always returns {@code false}.
     *
     * @return A {@link BooleanTernaryOperator} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static BooleanTernaryOperator alwaysFalse() {
        return (value1, value2, value3) -> false;
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which applies a logical AND to its input arguments.
     *
     * @return A {@link BooleanTernaryOperator} which applies a logical AND to its input arguments.
     * @see #and(BooleanTernaryOperator)
     */
    static BooleanTernaryOperator and() {
        return (value1, value2, value3) -> value1 && value2 && value3;
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which applies a logical OR to its input arguments.
     *
     * @return A {@link BooleanTernaryOperator} which applies a logical OR to its input arguments.
     * @see #or(BooleanTernaryOperator)
     */
    static BooleanTernaryOperator or() {
        return (value1, value2, value3) -> value1 || value2 || value3;
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which applies a logical XOR to its input arguments.
     *
     * @return A {@link BooleanTernaryOperator} which applies a logical XOR to its input arguments.
     * @see #xor(BooleanTernaryOperator)
     */
    static BooleanTernaryOperator xor() {
        return (value1, value2, value3) -> value1 ^ value2 ^ value3;
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    boolean applyAsBoolean(boolean left, boolean middle, boolean right);

    /**
     * Returns a {@link BooleanTernaryOperator} that represents the logical negation of this one.
     *
     * @return A {@code BooleanTernaryOperator} that represents the logical negation of this one.
     * @see TriPredicate#negate()
     */
    default BooleanTernaryOperator negate() {
        return (value1, value2, value3) -> !applyAsBoolean(value1, value2, value3);
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that represents a short-circuiting logical AND of this operator
     * and another. When evaluating the composed operator, if this operator is {@code false}, then the {@code other}
     * operator is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation of this
     * {@code BooleanTernaryOperator} throws an exception, the {@code other} {@code BooleanTernaryOperator} will not be
     * evaluated.
     *
     * @param other A {@code BooleanTernaryOperator} that will be logically-ANDed with this one
     * @return A composed {@code BooleanTernaryOperator} that represents the short-circuiting logical AND of this
     * operator and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(BooleanTernaryOperator)
     * @see #xor(BooleanTernaryOperator)
     * @see TriPredicate#and(TriPredicate)
     */
    default BooleanTernaryOperator and(final BooleanTernaryOperator other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> applyAsBoolean(value1, value2, value3) && other.applyAsBoolean(value1,
                                                                                                          value2,
                                                                                                          value3);
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that represents a short-circuiting logical OR of this operator
     * and another. When evaluating the composed operator, if this operator is {@code true}, then the {@code other}
     * operator is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation of this
     * {@code BooleanTernaryOperator} throws an exception, the {@code other} {@code BooleanTernaryOperator} will not be
     * evaluated.
     *
     * @param other A {@code BooleanTernaryOperator} that will be logically-ORed with this one
     * @return A composed {@code BooleanTernaryOperator} that represents the short-circuiting logical OR of this
     * operator and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BooleanTernaryOperator)
     * @see #xor(BooleanTernaryOperator)
     * @see TriPredicate#or(TriPredicate)
     */
    default BooleanTernaryOperator or(final BooleanTernaryOperator other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> applyAsBoolean(value1, value2, value3) && other.applyAsBoolean(value1,
                                                                                                          value2,
                                                                                                          value3);
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that represents a short-circuiting logical XOR of this operator
     * and another. Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation
     * of this {@code BooleanTernaryOperator} throws an exception, the {@code other} {@code BooleanTernaryOperator} will
     * not be evaluated.
     *
     * @param other A {@code BooleanTernaryOperator} that will be logically-XORed with this one
     * @return A composed {@code BooleanTernaryOperator} that represents the short-circuiting logical XOR of this
     * operator and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BooleanTernaryOperator)
     * @see #or(BooleanTernaryOperator)
     * @see TriPredicate#xor(TriPredicate)
     */
    default BooleanTernaryOperator xor(final BooleanTernaryOperator other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> applyAsBoolean(value1, value2, value3) ^ other.applyAsBoolean(value1, value2,
                                                                                                         value3);
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code BooleanUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code BooleanUnaryOperator} to apply before this operator is applied
     * @param before3 The third {@code BooleanUnaryOperator} to apply before this operator is applied
     * @return A composed {@code BooleanTernaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(BooleanUnaryOperator)
     */
    default BooleanTernaryOperator compose(final BooleanUnaryOperator before1, final BooleanUnaryOperator before2,
            final BooleanUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsBoolean(before1.applyAsBoolean(left), before2.applyAsBoolean(middle),
                                                       before3.applyAsBoolean(right));
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code BooleanUnaryOperator} to apply after this operator is applied
     * @return A composed {@code BooleanTernaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(BooleanUnaryOperator, BooleanUnaryOperator, BooleanUnaryOperator)
     */
    default BooleanTernaryOperator andThen(BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsBoolean(applyAsBoolean(left, middle, right));
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link BooleanTernaryOperator}. Thereby the
     * primitive input argument for this operation is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code BooleanTernaryOperator}.
     */
    default TernaryOperator<Boolean> boxed() {
        return this::applyAsBoolean;
    }
}