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
package at.gridtec.lambda4j.operators.unary;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single {@code boolean}-valued operand that produces a {@code boolean}-valued result.
 * This is the primitive type specialization of {@link UnaryOperator} for {@code boolean}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(boolean)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanUnaryOperator {

    /**
     * Creates a {@link BooleanUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static BooleanUnaryOperator constant(boolean ret) {
        return operand -> ret;
    }

    /**
     * Returns a {@link BooleanUnaryOperator} that always returns its input argument.
     *
     * @return A {@code BooleanUnaryOperator} that always returns its input argument
     */
    static BooleanUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Returns a {@link BooleanUnaryOperator} that tests if the given argument is equal to the one of this operator
     * according to {@code value == target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code BooleanUnaryOperator} that tests if the given argument is equal to the one of this operator.
     * @see #isNotEqual(boolean)
     */
    static BooleanUnaryOperator isEqual(boolean target) {
        return value -> value == target;
    }

    /**
     * Returns a {@link BooleanUnaryOperator} that tests if the given argument is not equal to the one of this operator
     * according to {@code value != target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code BooleanUnaryOperator} that tests if the given argument is not equal to the one of this operator.
     * @see #isEqual(boolean)
     */
    static BooleanUnaryOperator isNotEqual(boolean target) {
        return value -> value != target;
    }

    /**
     * Returns a {@link BooleanUnaryOperator} the always returns {@code true}.
     *
     * @return A {@link BooleanUnaryOperator} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static BooleanUnaryOperator alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link BooleanUnaryOperator} the always returns {@code false}.
     *
     * @return A {@link BooleanUnaryOperator} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static BooleanUnaryOperator alwaysFalse() {
        return value -> false;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    boolean applyAsBoolean(boolean operand);

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }
    /**
     * Returns a {@link BooleanUnaryOperator} that represents the logical negation of this one.
     *
     * @return A {@code BooleanUnaryOperator} that represents the logical negation of this one.
     * @see Predicate#negate()
     */
    default BooleanUnaryOperator negate() {
        return value -> !applyAsBoolean(value);
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that represents a short-circuiting logical AND of this operator
     * and another. When evaluating the composed operator, if this operator is {@code false}, then the {@code other}
     * operator is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation of this
     * {@code BooleanUnaryOperator} throws an exception, the {@code other} {@code BooleanUnaryOperator} will not be
     * evaluated.
     *
     * @param other A {@code BooleanUnaryOperator} that will be logically-ANDed with this one
     * @return A composed {@code BooleanUnaryOperator} that represents the short-circuiting logical AND of this operator
     * and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(BooleanUnaryOperator)
     * @see #xor(BooleanUnaryOperator)
     * @see Predicate#and(Predicate)
     */
    default BooleanUnaryOperator and(final BooleanUnaryOperator other) {
        Objects.requireNonNull(other);
        return value -> applyAsBoolean(value) && other.applyAsBoolean(value);
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that represents a short-circuiting logical OR of this operator
     * and another. When evaluating the composed operator, if this operator is {@code true}, then the {@code other}
     * operator is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation of this
     * {@code BooleanUnaryOperator} throws an exception, the {@code other} {@code BooleanUnaryOperator} will not be
     * evaluated.
     *
     * @param other A {@code BooleanUnaryOperator} that will be logically-ORed with this one
     * @return A composed {@code BooleanUnaryOperator} that represents the short-circuiting logical OR of this operator
     * and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BooleanUnaryOperator)
     * @see #xor(BooleanUnaryOperator)
     * @see Predicate#or(Predicate)
     */
    default BooleanUnaryOperator or(final BooleanUnaryOperator other) {
        Objects.requireNonNull(other);
        return value -> applyAsBoolean(value) && other.applyAsBoolean(value);
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that represents a short-circuiting logical XOR of this operator
     * and another. Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation
     * of this {@code BooleanUnaryOperator} throws an exception, the {@code other} {@code BooleanUnaryOperator} will not
     * be evaluated.
     *
     * @param other A {@code BooleanUnaryOperator} that will be logically-XORed with this one
     * @return A composed {@code BooleanUnaryOperator} that represents the short-circuiting logical XOR of this operator
     * and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BooleanUnaryOperator)
     * @see #or(BooleanUnaryOperator)
     */
    default BooleanUnaryOperator xor(final BooleanUnaryOperator other) {
        Objects.requireNonNull(other);
        return value -> applyAsBoolean(value) ^ other.applyAsBoolean(value);
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before The {@code BooleanUnaryOperator} to apply before this operator is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies the {@code before} operator and then applies
     * this operator.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanUnaryOperator)
     */
    default BooleanUnaryOperator compose(final BooleanUnaryOperator before) {
        Objects.requireNonNull(before);
        return operand -> applyAsBoolean(before.applyAsBoolean(operand));
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code BooleanUnaryOperator} to apply after this operator is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(BooleanUnaryOperator)
     */
    default BooleanUnaryOperator andThen(final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return operand -> after.applyAsBoolean(applyAsBoolean(operand));
    }

    /**
     * Returns a composed {@link UnaryOperator} which represents this {@link BooleanUnaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed. This method is just convenience to provide the ability
     * to use this {@code BooleanUnaryOperator} with JRE specific methods, only accepting {@code UnaryOperator}.
     *
     * @return A composed {@code UnaryOperator} which represents this {@code BooleanUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator<Boolean> boxed() {
        return this::applyAsBoolean;
    }
}