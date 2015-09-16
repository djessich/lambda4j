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

import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * Represents an operation on a two {@code byte}-valued operands and producing a {@code byte}-valued result. This is the
 * primitive type specialization of {@link BinaryOperator} for {@code byte}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(byte, byte)}.
 *
 * @see java.util.function.BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteBinaryOperator {

    /**
     * Creates a {@link ByteBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteBinaryOperator} which always returns a given value.
     */
    static ByteBinaryOperator constant(byte ret) {
        return (left, right) -> ret;
    }

    /**
     * Returns a {@link ByteBinaryOperator} which returns the lesser of two elements, according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ByteBinaryOperator} which returns the lesser of two elements, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ByteBinaryOperator minBy(final Comparator<? super Byte> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link ByteBinaryOperator} which returns the greater of two elements, according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ByteBinaryOperator} which returns the greater of two elements, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ByteBinaryOperator maxBy(final Comparator<? super Byte> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    /**
     * Returns a {@link ByteBinaryOperator} which returns the lesser of two elements according to {@code left &lt;=
     * right} operation.
     *
     * @return A {@code ByteBinaryOperator} which returns the lesser of two elements.
     * @see BinaryOperator#minBy(Comparator)
     */
    static ByteBinaryOperator min() {
        return (left, right) -> (left <= right) ? left : right;
    }

    /**
     * Returns a {@link ByteBinaryOperator} which returns the greater of two elements according to {@code left &gt;=
     * right} operation.
     *
     * @return A {@code ByteBinaryOperator} which returns the greater of two elements.
     * @see BinaryOperator#maxBy(Comparator)
     */
    static ByteBinaryOperator max() {
        return (left, right) -> (left >= right) ? left : right;
    }

    /**
     * Creates a {@link ByteBinaryOperator} which uses the left parameter as argument for the given {@link
     * ByteUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code short} parameter of this one
     * @return Creates a {@code ByteBinaryOperator} which uses the left parameter as argument for the given {@code
     * ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ByteBinaryOperator onlyLeft(final ByteUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsByte(left);
    }

    /**
     * Creates a {@link ByteBinaryOperator} which uses the right parameter as argument for the given {@link
     * ByteUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code ByteBinaryOperator} which uses the right parameter as argument for the given {@code
     * ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ByteBinaryOperator onlyRight(final ByteUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsByte(right);
    }

    /**
     * Applies this operator to the given operand argument.
     *
     * @param left The first argument to the operator (left input)
     * @param right The second argument to the operator (right input)
     * @return The return value from the operator.
     */
    byte applyAsByte(byte left, byte right);

    /**
     * Returns a composed {@link ByteBinaryOperator} that first applies the given {@code before} operators to its input,
     * and then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operator.
     *
     * @param before1 The first {@code ByteUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code ByteUnaryOperator} to apply before this operator is applied
     * @return A composed {@code ByteBinaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(ByteUnaryOperator)
     */
    default ByteBinaryOperator compose(final ByteUnaryOperator before1, final ByteUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (left, right) -> applyAsByte(before1.applyAsByte(left), before2.applyAsByte(right));
    }

    /**
     * Returns a composed {@link ByteBinaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The {@code ByteUnaryOperator} to apply after this operator is applied
     * @return A composed {@code ByteBinaryOperator} that first applies this operator and then applies the {@code after}
     * operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(ByteUnaryOperator, ByteUnaryOperator)
     */
    default ByteBinaryOperator andThen(ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, right) -> after.applyAsByte(applyAsByte(left, right));
    }

    /**
     * Returns a composed {@link BinaryOperator} which represents this {@link ByteBinaryOperator}. Thereby the primitive
     * input argument for this operation is autoboxed. This method is just convenience to provide the ability to use
     * this {@code ByteBinaryOperator} with JRE specific methods, only accepting {@code BinaryOperator}.
     *
     * @return A composed {@code BinaryOperator} which represents this {@code ByteBinaryOperator}.
     */
    default BinaryOperator<Byte> boxed() {
        return this::applyAsByte;
    }
}
