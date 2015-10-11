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

import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import java.util.Objects;

/**
 * Represents an operation on a two {@code short}-valued operands and producing a {@code short}-valued result. This is
 * the primitive type specialization of {@link TernaryOperator} for {@code short}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(short, short, short)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortTernaryOperator {

    /**
     * Creates a {@link ShortTernaryOperator} which always returns a given value.
     *
     * @param r The return value for the constant
     * @return A {@code ShortTernaryOperator} which always returns a given value.
     */
    static ShortTernaryOperator constant(short r) {
        return (left, middle, right) -> r;
    }

    /**
     * Creates a {@link ShortTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * ShortUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code ShortTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortTernaryOperator onlyLeft(final ShortUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsShort(left);
    }

    /**
     * Creates a {@link ShortTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * ShortUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code ShortTernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortTernaryOperator onlyMiddle(final ShortUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsShort(middle);
    }

    /**
     * Creates a {@link ShortTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * ShortUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code ShortTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code ShortUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ShortTernaryOperator onlyRight(final ShortUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsShort(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    short applyAsShort(short left, short middle, short right);

    /**
     * Returns a composed {@link ShortTernaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code ShortUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code ShortUnaryOperator} to apply before this operator is applied
     * @param before3 The third {@code ShortUnaryOperator} to apply before this operator is applied
     * @return A composed {@code ShortTernaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(ShortUnaryOperator)
     */
    default ShortTernaryOperator compose(final ShortUnaryOperator before1, final ShortUnaryOperator before2,
            final ShortUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsShort(before1.applyAsShort(left), before2.applyAsShort(middle),
                                                     before3.applyAsShort(right));
    }

    /**
     * Returns a composed {@link ShortTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code ShortUnaryOperator} to apply after this operator is applied
     * @return A composed {@code ShortTernaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(ShortUnaryOperator, ShortUnaryOperator, ShortUnaryOperator)
     */
    default ShortTernaryOperator andThen(ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsShort(applyAsShort(left, middle, right));
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link ShortTernaryOperator}. Thereby the
     * primitive input argument for this operation is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code ShortTernaryOperator}.
     */
    default TernaryOperator<Short> boxed() {
        return this::applyAsShort;
    }
}