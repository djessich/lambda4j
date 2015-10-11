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
package at.gridtec.lambda4j.operators.binary;

import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

/**
 * Represents an operation on a two {@code boolean}-valued operands and producing a {@code boolean}-valued result. This
 * is the primitive type specialization of {@link BinaryOperator} for {@code boolean}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(boolean, boolean)}.
 *
 * @see BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanBinaryOperator {

    /**
     * Creates a {@link BooleanBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanBinaryOperator} which always returns a given value.
     */
    static BooleanBinaryOperator constant(boolean ret) {
        return (left, right) -> ret;
    }

    /**
     * Returns a {@link BooleanBinaryOperator} which returns the lesser of two elements, according to the specified
     * {@code Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code BooleanBinaryOperator} which returns the lesser of two elements, according to the supplied
     * {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static BooleanBinaryOperator minBy(final Comparator<? super Boolean> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link BooleanBinaryOperator} which returns the greater of two elements, according to the specified
     * {@code Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code BooleanBinaryOperator} which returns the greater of two elements, according to the supplied
     * {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static BooleanBinaryOperator maxBy(final Comparator<? super Boolean> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code BooleanBinaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code BooleanUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static BooleanBinaryOperator onlyLeft(final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsBoolean(left);
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code BooleanBinaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code BooleanUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static BooleanBinaryOperator onlyRight(final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsBoolean(right);
    }

    /**
     * Returns a {@link BooleanBinaryOperator} that tests if the given arguments are equal to the ones of this operator
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @return A {@code BooleanBinaryOperator} that tests if the given arguments are equal to the ones of this operator.
     * @see #isNotEqual(boolean, boolean)
     */
    static BooleanBinaryOperator isEqual(boolean target1, boolean target2) {
        return (value1, value2) -> (value1 == target1) && (value2 == target2);
    }

    /**
     * Returns a {@link BooleanBinaryOperator} that tests if the given arguments are not equal to the ones of this
     * operator according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @return A {@code BooleanBinaryOperator} that tests if the given arguments are not equal to the ones of this
     * operator.
     * @see #isEqual(boolean, boolean)
     */
    static BooleanBinaryOperator isNotEqual(boolean target1, boolean target2) {
        return (value1, value2) -> (value1 != target1) || (value2 != target2);
    }

    /**
     * Returns a {@link BooleanBinaryOperator} the always returns {@code true}.
     *
     * @return A {@link BooleanBinaryOperator} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static BooleanBinaryOperator alwaysTrue() {
        return (value1, value2) -> true;
    }

    /**
     * Returns a {@link BooleanBinaryOperator} the always returns {@code false}.
     *
     * @return A {@link BooleanBinaryOperator} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static BooleanBinaryOperator alwaysFalse() {
        return (value1, value2) -> false;
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which applies a logical AND to its input arguments.
     *
     * @return A {@link BooleanBinaryOperator} which applies a logical AND to its input arguments.
     * @see #and(BooleanBinaryOperator)
     */
    static BooleanBinaryOperator and() {
        return Boolean::logicalAnd;
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which applies a logical OR to its input arguments.
     *
     * @return A {@link BooleanBinaryOperator} which applies a logical OR to its input arguments.
     * @see #or(BooleanBinaryOperator)
     */
    static BooleanBinaryOperator or() {
        return Boolean::logicalOr;
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which applies a logical XOR to its input arguments.
     *
     * @return A {@link BooleanBinaryOperator} which applies a logical XOR to its input arguments.
     * @see #xor(BooleanBinaryOperator)
     */
    static BooleanBinaryOperator xor() {
        return Boolean::logicalXor;
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param right The second argument to the operator (right input)
     * @return The return value from the operator.
     */
    boolean applyAsBoolean(boolean left, boolean right);

    /**
     * Returns a {@link BooleanBinaryOperator} that represents the logical negation of this one.
     *
     * @return A {@code BooleanBinaryOperator} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    default BooleanBinaryOperator negate() {
        return (value1, value2) -> !applyAsBoolean(value1, value2);
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that represents a short-circuiting logical AND of this operator
     * and another. When evaluating the composed operator, if this operator is {@code false}, then the {@code other}
     * operator is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation of this
     * {@code BooleanBinaryOperator} throws an exception, the {@code other} {@code BooleanBinaryOperator} will not be
     * evaluated.
     *
     * @param other A {@code BooleanBinaryOperator} that will be logically-ANDed with this one
     * @return A composed {@code BooleanBinaryOperator} that represents the short-circuiting logical AND of this
     * operator and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(BooleanBinaryOperator)
     * @see #xor(BooleanBinaryOperator)
     * @see BiPredicate#and(BiPredicate)
     */
    default BooleanBinaryOperator and(final BooleanBinaryOperator other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> applyAsBoolean(value1, value2) && other.applyAsBoolean(value1, value2);
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that represents a short-circuiting logical OR of this operator
     * and another. When evaluating the composed operator, if this operator is {@code true}, then the {@code other}
     * operator is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation of this
     * {@code BooleanBinaryOperator} throws an exception, the {@code other} {@code BooleanBinaryOperator} will not be
     * evaluated.
     *
     * @param other A {@code BooleanBinaryOperator} that will be logically-ORed with this one
     * @return A composed {@code BooleanBinaryOperator} that represents the short-circuiting logical OR of this operator
     * and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BooleanBinaryOperator)
     * @see #xor(BooleanBinaryOperator)
     * @see BiPredicate#or(BiPredicate)
     */
    default BooleanBinaryOperator or(final BooleanBinaryOperator other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> applyAsBoolean(value1, value2) && other.applyAsBoolean(value1, value2);
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that represents a short-circuiting logical XOR of this operator
     * and another. Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation
     * of this {@code BooleanBinaryOperator} throws an exception, the {@code other} {@code BooleanBinaryOperator} will
     * not be evaluated.
     *
     * @param other A {@code BooleanBinaryOperator} that will be logically-XORed with this one
     * @return A composed {@code BooleanBinaryOperator} that represents the short-circuiting logical XOR of this
     * operator and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BooleanBinaryOperator)
     * @see #or(BooleanBinaryOperator)
     */
    default BooleanBinaryOperator xor(final BooleanBinaryOperator other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> applyAsBoolean(value1, value2) ^ other.applyAsBoolean(value1, value2);
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code BooleanUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code BooleanUnaryOperator} to apply before this operator is applied
     * @return A composed {@code BooleanBinaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(BooleanUnaryOperator)
     */
    default BooleanBinaryOperator compose(final BooleanUnaryOperator before1, final BooleanUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (left, right) -> applyAsBoolean(before1.applyAsBoolean(left), before2.applyAsBoolean(right));
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code BooleanUnaryOperator} to apply after this operator is applied
     * @return A composed {@code BooleanBinaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(BooleanUnaryOperator, BooleanUnaryOperator)
     */
    default BooleanBinaryOperator andThen(BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, right) -> after.applyAsBoolean(applyAsBoolean(left, right));
    }

    /**
     * Returns a composed {@link BinaryOperator} which represents this {@link BooleanBinaryOperator}. Thereby the
     * primitive input argument for this operation is autoboxed. This method is just convenience to provide the ability
     * to use this {@code BooleanBinaryOperator} with JRE specific methods, only accepting {@code BinaryOperator}.
     *
     * @return A composed {@code BinaryOperator} which represents this {@code BooleanBinaryOperator}.
     */
    default BinaryOperator<Boolean> boxed() {
        return this::applyAsBoolean;
    }
}
