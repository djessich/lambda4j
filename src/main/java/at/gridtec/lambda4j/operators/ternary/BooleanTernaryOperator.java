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

import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.BooleanTriConsumer;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriBooleanToShortFunction;
import at.gridtec.lambda4j.function.primitives.tri.BooleanTriFunction;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.predicates.TriPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

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
     * Calls the given {@link BooleanTernaryOperator} with the given arguments and returns its result.
     *
     * @param operator The operator to be called
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The result from the given {@code BooleanTernaryOperator}.
     * @throws NullPointerException If the given operator is {@code null}
     */
    static boolean call(@Nonnull final BooleanTernaryOperator operator, boolean left, boolean middle, boolean right) {
        Objects.requireNonNull(operator);
        return operator.applyAsBoolean(left, middle, right);
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code BooleanTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code BooleanUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BooleanTernaryOperator onlyLeft(@Nonnull final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsBoolean(left);
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code BooleanTernaryOperator} which uses the {@code middle} parameter as argument for the
     * given {@code BooleanUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BooleanTernaryOperator onlyMiddle(@Nonnull final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsBoolean(middle);
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * BooleanUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code BooleanTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code BooleanUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BooleanTernaryOperator onlyRight(@Nonnull final BooleanUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsBoolean(right);
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static BooleanTernaryOperator constant(boolean ret) {
        return (left, middle, right) -> ret;
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
    @Nonnull
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
    @Nonnull
    static BooleanTernaryOperator isNotEqual(boolean target1, boolean target2, boolean target3) {
        return (value1, value2, value3) -> (value1 != target1) && (value2 != target2) && (value3 != target3);
    }

    /**
     * Returns a {@link BooleanTernaryOperator} the always returns {@code true}.
     *
     * @return A {@link BooleanTernaryOperator} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static BooleanTernaryOperator alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link BooleanTernaryOperator} the always returns {@code false}.
     *
     * @return A {@link BooleanTernaryOperator} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static BooleanTernaryOperator alwaysFalse() {
        return (value1, value2, value3) -> false;
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which applies a logical AND to its input arguments.
     *
     * @return A {@link BooleanTernaryOperator} which applies a logical AND to its input arguments.
     * @see #and(BooleanTernaryOperator)
     */
    @Nonnull
    static BooleanTernaryOperator and() {
        return (value1, value2, value3) -> value1 && value2 && value3;
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which applies a logical OR to its input arguments.
     *
     * @return A {@link BooleanTernaryOperator} which applies a logical OR to its input arguments.
     * @see #or(BooleanTernaryOperator)
     */
    @Nonnull
    static BooleanTernaryOperator or() {
        return (value1, value2, value3) -> value1 || value2 || value3;
    }

    /**
     * Creates a {@link BooleanTernaryOperator} which applies a logical XOR to its input arguments.
     *
     * @return A {@link BooleanTernaryOperator} which applies a logical XOR to its input arguments.
     * @see #xor(BooleanTernaryOperator)
     */
    @Nonnull
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
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a {@link BooleanTernaryOperator} that represents the logical negation of this one.
     *
     * @return A {@code BooleanTernaryOperator} that represents the logical negation of this one.
     * @see TriPredicate#negate()
     */
    @Nonnull
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
    @Nonnull
    default BooleanTernaryOperator and(@Nonnull final BooleanTernaryOperator other) {
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
    @Nonnull
    default BooleanTernaryOperator or(@Nonnull final BooleanTernaryOperator other) {
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
    @Nonnull
    default BooleanTernaryOperator xor(@Nonnull final BooleanTernaryOperator other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> applyAsBoolean(value1, value2, value3) ^ other.applyAsBoolean(value1, value2,
                                                                                                         value3);
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that first applies the {@code before} operators to its input,
     * and then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operator.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @param before3 The third operator to apply before this operator is applied
     * @return A composed {@link BooleanTernaryOperator} that first applies the {@code before} operators to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code boolean}.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BooleanTernaryOperator compose(@Nonnull final BooleanUnaryOperator before1,
            @Nonnull final BooleanUnaryOperator before2, @Nonnull final BooleanUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsBoolean(before1.applyAsBoolean(left), before2.applyAsBoolean(middle),
                                                       before3.applyAsBoolean(right));
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies the {@code before} operations to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first operation to apply before this operator is applied
     * @param before2 The second operation to apply before this operator is applied
     * @param before3 The third operation to apply before this operator is applied
     * @return A composed {@link TriPredicate} that first applies the {@code before} operations to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default <T, U, V> TriPredicate<T, U, V> compose(@Nonnull final Predicate<? super T> before1,
            @Nonnull final Predicate<? super U> before2, @Nonnull final Predicate<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (t, u, v) -> applyAsBoolean(before1.test(t), before2.test(u), before3.test(v));
    }

    /**
     * Returns a composed {@link BooleanTriFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code BooleanTriFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(BooleanUnaryOperator, BooleanUnaryOperator, BooleanUnaryOperator)
     * @see #compose(Predicate, Predicate, Predicate)
     */
    @Nonnull
    default <R> BooleanTriFunction<R> andThen(@Nonnull final BooleanFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BooleanTernaryOperator andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsBoolean(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriBooleanToByteFunction andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriBooleanToCharFunction andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriBooleanToDoubleFunction andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriBooleanToFloatFunction andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriBooleanToIntFunction andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriBooleanToLongFunction andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriBooleanToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriBooleanToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriBooleanToShortFunction andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link BooleanTriConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BooleanTriConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanTriConsumer consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link BooleanTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code BooleanTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Boolean> boxed() {
        return this::applyAsBoolean;
    }
}