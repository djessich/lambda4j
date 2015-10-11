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
package at.gridtec.lambda4j.operators.ternary;

import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import java.util.Objects;

/**
 * Represents an operation on a two {@code char}-valued operands and producing a {@code char}-valued result. This is the
 * primitive type specialization of {@link TernaryOperator} for {@code char}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(char, char, char)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharTernaryOperator {

    /**
     * Creates a {@link CharTernaryOperator} which always returns a given value.
     *
     * @param r The return value for the constant
     * @return A {@code CharTernaryOperator} which always returns a given value.
     */
    static CharTernaryOperator constant(char r) {
        return (left, middle, right) -> r;
    }

    /**
     * Creates a {@link CharTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * CharUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code CharTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static CharTernaryOperator onlyLeft(final CharUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsChar(left);
    }

    /**
     * Creates a {@link CharTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * CharUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code CharTernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static CharTernaryOperator onlyMiddle(final CharUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsChar(middle);
    }

    /**
     * Creates a {@link CharTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * CharUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code CharTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code CharUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static CharTernaryOperator onlyRight(final CharUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsChar(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    char applyAsChar(char left, char middle, char right);

    /**
     * Returns a composed {@link CharTernaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code CharUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code CharUnaryOperator} to apply before this operator is applied
     * @param before3 The third {@code CharUnaryOperator} to apply before this operator is applied
     * @return A composed {@code CharTernaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(CharUnaryOperator)
     */
    default CharTernaryOperator compose(final CharUnaryOperator before1, final CharUnaryOperator before2,
            final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsChar(before1.applyAsChar(left), before2.applyAsChar(middle),
                                                    before3.applyAsChar(right));
    }

    /**
     * Returns a composed {@link CharTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code CharUnaryOperator} to apply after this operator is applied
     * @return A composed {@code CharTernaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator, CharUnaryOperator)
     */
    default CharTernaryOperator andThen(CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsChar(applyAsChar(left, middle, right));
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link CharTernaryOperator}. Thereby the
     * primitive input argument for this operation is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code CharTernaryOperator}.
     */
    default TernaryOperator<Character> boxed() {
        return this::applyAsChar;
    }
}