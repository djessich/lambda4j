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
     * Applies this operator to the given operand argument.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    short applyAsShort(short left, short middle, short right);
}