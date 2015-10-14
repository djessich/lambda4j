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
package at.gridtec.lambda4j.operators.binary;

import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * Represents an operation on a two {@code char}-valued operands and producing a {@code char}-valued result. This is the
 * primitive type specialization of {@link BinaryOperator} for {@code char}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(char, char)}.
 *
 * @see java.util.function.BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharBinaryOperator {

    /**
     * Creates a {@link CharBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharBinaryOperator} which always returns a given value.
     */
    @Nonnull
    static CharBinaryOperator constant(char ret) {
        return (left, right) -> ret;
    }

    /**
     * Returns a {@link CharBinaryOperator} which returns the lesser of two elements, according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code CharBinaryOperator} which returns the lesser of two elements, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static CharBinaryOperator minBy(final Comparator<? super Character> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link CharBinaryOperator} which returns the greater of two elements, according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code CharBinaryOperator} which returns the greater of two elements, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static CharBinaryOperator maxBy(final Comparator<? super Character> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    /**
     * Returns a {@link CharBinaryOperator} which returns the lesser of two elements according to {@code left &lt;=
     * right} operation.
     *
     * @return A {@code CharBinaryOperator} which returns the lesser of two elements.
     * @see BinaryOperator#minBy(Comparator)
     */
    static CharBinaryOperator min() {
        return (left, right) -> (left <= right) ? left : right;
    }

    /**
     * Returns a {@link CharBinaryOperator} which returns the greater of two elements according to {@code left &gt;=
     * right} operation.
     *
     * @return A {@code CharBinaryOperator} which returns the greater of two elements.
     * @see BinaryOperator#maxBy(Comparator)
     */
    static CharBinaryOperator max() {
        return (left, right) -> (left >= right) ? left : right;
    }

    /**
     * Creates a {@link CharBinaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * CharUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code CharBinaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharBinaryOperator onlyLeft(@Nonnull final CharUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsChar(left);
    }

    /**
     * Creates a {@link CharBinaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * CharUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code CharBinaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharBinaryOperator onlyRight(@Nonnull final CharUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsChar(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param right The second argument to the operator (right input)
     * @return The return value from the operator.
     */
    char applyAsChar(char left, char right);

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
     * Returns a composed {@link CharBinaryOperator} that first applies the given {@code before} operators to its input,
     * and then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operator.
     *
     * @param before1 The first {@code CharUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code CharUnaryOperator} to apply before this operator is applied
     * @return A composed {@code CharBinaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(CharUnaryOperator)
     */
    default CharBinaryOperator compose(final CharUnaryOperator before1, final CharUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (left, right) -> applyAsChar(before1.applyAsChar(left), before2.applyAsChar(right));
    }

    /**
     * Returns a composed {@link CharBinaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The {@code CharUnaryOperator} to apply after this operator is applied
     * @return A composed {@code CharBinaryOperator} that first applies this operator and then applies the {@code after}
     * operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator)
     */
    default CharBinaryOperator andThen(CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, right) -> after.applyAsChar(applyAsChar(left, right));
    }

    /**
     * Returns a composed {@link BinaryOperator} which represents this {@link CharBinaryOperator}. Thereby the primitive
     * input argument for this operator is autoboxed. This method is just convenience to provide the ability to use this
     * {@code CharBinaryOperator} with JRE specific methods, only accepting {@code BinaryOperator}.
     *
     * @return A composed {@code BinaryOperator} which represents this {@code CharBinaryOperator}.
     */
    @Nonnull
    default BinaryOperator<Character> boxed() {
        return this::applyAsChar;
    }
}
