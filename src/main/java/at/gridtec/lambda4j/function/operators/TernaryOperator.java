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
package at.gridtec.lambda4j.function.operators;

import at.gridtec.lambda4j.function.TriFunction;

/**
 * Represents an operation upon three operands of the same type, producing a result of the same type as the operands.
 * This is a specialization of {@link TriFunction} for the case where the operands and the result are all of the same
 * type.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, Object, Object)}.
 *
 * @param <T> The type of the operands and result of the operator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TernaryOperator<T> extends TriFunction<T, T, T, T> {

    /**
     * Creates a {@link TernaryOperator} which always returns a given value.
     *
     * @param <T> The type of argument for the operator
     * @param r The return value for the constant
     * @return A {@code TernaryOperator} which always returns a given value.
     */
    static <T> TernaryOperator<T> constant(T r) {
        return (t, u, v) -> r;
    }
}
