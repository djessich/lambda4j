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

import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.consumer.primitives.bi.BooleanBiConsumer;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.function.primitives.bi.BooleanBiFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiBooleanToShortFunction;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

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
     * Calls the given {@link BooleanBinaryOperator} with the given arguments and returns its result.
     *
     * @param operator The operator to be called
     * @param left The first argument to the operator (left input)
     * @param right The second argument to the operator (right input)
     * @return The result from the given {@code BooleanBinaryOperator}.
     * @throws NullPointerException If the given operator is {@code null}
     */
    static boolean call(@Nonnull final BooleanBinaryOperator operator, boolean left, boolean right) {
        Objects.requireNonNull(operator);
        return operator.applyAsBoolean(left, right);
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
    @Nonnull
    static BooleanBinaryOperator onlyLeft(@Nonnull final BooleanUnaryOperator operator) {
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
    @Nonnull
    static BooleanBinaryOperator onlyRight(@Nonnull final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsBoolean(right);
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanBinaryOperator} which always returns a given value.
     */
    @Nonnull
    static BooleanBinaryOperator constant(boolean ret) {
        return (left, right) -> ret;
    }

    /**
     * Returns a {@link BooleanBinaryOperator} which returns the lesser of two elements, according to the specified
     * {@link Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code BooleanBinaryOperator} which returns the lesser of two elements, according to the specified
     * {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static BooleanBinaryOperator minBy(@Nonnull final Comparator<? super Boolean> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link BooleanBinaryOperator} which returns the greater of two elements, according to the specified
     * {@link Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code BooleanBinaryOperator} which returns the greater of two elements, according to the specified
     * {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static BooleanBinaryOperator maxBy(@Nonnull final Comparator<? super Boolean> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
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
    @Nonnull
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
    @Nonnull
    static BooleanBinaryOperator isNotEqual(boolean target1, boolean target2) {
        return (value1, value2) -> (value1 != target1) || (value2 != target2);
    }

    /**
     * Returns a {@link BooleanBinaryOperator} the always returns {@code true}.
     *
     * @return A {@link BooleanBinaryOperator} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static BooleanBinaryOperator alwaysTrue() {
        return (value1, value2) -> true;
    }

    /**
     * Returns a {@link BooleanBinaryOperator} the always returns {@code false}.
     *
     * @return A {@link BooleanBinaryOperator} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static BooleanBinaryOperator alwaysFalse() {
        return (value1, value2) -> false;
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which applies a logical AND to its input arguments.
     *
     * @return A {@link BooleanBinaryOperator} which applies a logical AND to its input arguments.
     * @see #and(BooleanBinaryOperator)
     */
    @Nonnull
    static BooleanBinaryOperator and() {
        return Boolean::logicalAnd;
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which applies a logical OR to its input arguments.
     *
     * @return A {@link BooleanBinaryOperator} which applies a logical OR to its input arguments.
     * @see #or(BooleanBinaryOperator)
     */
    @Nonnull
    static BooleanBinaryOperator or() {
        return Boolean::logicalOr;
    }

    /**
     * Creates a {@link BooleanBinaryOperator} which applies a logical XOR to its input arguments.
     *
     * @return A {@link BooleanBinaryOperator} which applies a logical XOR to its input arguments.
     * @see #xor(BooleanBinaryOperator)
     */
    @Nonnull
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
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a {@link BooleanBinaryOperator} that represents the logical negation of this one.
     *
     * @return A {@code BooleanBinaryOperator} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    @Nonnull
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
    @Nonnull
    default BooleanBinaryOperator and(@Nonnull final BooleanBinaryOperator other) {
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
    @Nonnull
    default BooleanBinaryOperator or(@Nonnull final BooleanBinaryOperator other) {
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
    @Nonnull
    default BooleanBinaryOperator xor(@Nonnull final BooleanBinaryOperator other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> applyAsBoolean(value1, value2) ^ other.applyAsBoolean(value1, value2);
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that first applies the {@code before} operators to its input,
     * and then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operator.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @return A composed {@link BooleanBinaryOperator} that first applies the {@code before} operators to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code boolean}.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BooleanBinaryOperator compose(@Nonnull final BooleanUnaryOperator before1,
            @Nonnull final BooleanUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (left, right) -> applyAsBoolean(before1.applyAsBoolean(left), before2.applyAsBoolean(right));
    }

    /**
     * Returns a composed {@link BiPredicate} that first applies the {@code before} operations to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this operator is applied
     * @param before2 The second operation to apply before this operator is applied
     * @return A composed {@link BiPredicate} that first applies the {@code before} operations to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default <T, U> BiPredicate<T, U> compose(@Nonnull final Predicate<? super T> before1,
            @Nonnull final Predicate<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsBoolean(before1.test(t), before2.test(u));
    }

    /**
     * Returns a composed {@link BooleanBiFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code BooleanBiFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(BooleanUnaryOperator, BooleanUnaryOperator)
     * @see #compose(Predicate, Predicate)
     */
    @Nonnull
    default <R> BooleanBiFunction<R> andThen(@Nonnull final BooleanFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BooleanBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BooleanBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BooleanBinaryOperator andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsBoolean(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BiBooleanToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiBooleanToByteFunction andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code char}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BiBooleanToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiBooleanToCharFunction andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BiBooleanToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiBooleanToDoubleFunction andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code float}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BiBooleanToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiBooleanToFloatFunction andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BiBooleanToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiBooleanToIntFunction andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code long}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BiBooleanToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiBooleanToLongFunction andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BiBooleanToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code short}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BiBooleanToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BiBooleanToShortFunction andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsBoolean(value1, value2));
    }

    /**
     * Returns a composed {@link BooleanBiConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BooleanBiConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanBiConsumer consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsBoolean(value1, value2));
    }

    /**
     * Applies this operator partially to one argument. The result is an operator of arity {@code 1}.
     *
     * @param value1 The argument to partially apply to the operator
     * @return A partial application of this operator.
     */
    @Nonnull
    default BooleanUnaryOperator partial(boolean value1) {
        return value2 -> applyAsBoolean(value1, value2);
    }

    /**
     * Applies this operator partially to two arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the operator
     * @param value2 The second argument to partially apply to the operator
     * @return A partial application of this operator.
     */
    @Nonnull
    default BooleanSupplier partial(boolean value1, boolean value2) {
        return () -> applyAsBoolean(value1, value2);
    }

    /**
     * Returns a composed {@link BinaryOperator} which represents this {@link BooleanBinaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed. This method is just convenience to provide the ability
     * to use this {@code BooleanBinaryOperator} with JRE specific methods, only accepting {@code BinaryOperator}.
     *
     * @return A composed {@code BinaryOperator} which represents this {@code BooleanBinaryOperator}.
     */
    @Nonnull
    default BinaryOperator<Boolean> boxed() {
        return this::applyAsBoolean;
    }
}
