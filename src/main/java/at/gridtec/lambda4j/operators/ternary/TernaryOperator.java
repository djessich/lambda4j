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

import at.gridtec.lambda4j.function.TriFunction;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

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
     * @param <T> The type of argument to the operator
     * @param r The return value for the constant
     * @return A {@code TernaryOperator} which always returns a given value.
     */
    static <T> TernaryOperator<T> constant(T r) {
        return (t, u, v) -> r;
    }

    /**
     * Creates a {@link TernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * UnaryOperator}.
     *
     * @param <T> The type of argument to the operator
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code TernaryOperator} which uses the {@code left} parameter as argument for the given {@code
     * UnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> TernaryOperator<T> onlyLeft(@Nonnull final UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (t, u, v) -> operator.apply(t);
    }

    /**
     * Creates a {@link TernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * UnaryOperator}.
     *
     * @param <T> The type of argument to the operator
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code TernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code UnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> TernaryOperator<T> onlyMiddle(@Nonnull final UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (t, u, v) -> operator.apply(u);
    }

    /**
     * Creates a {@link TernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * UnaryOperator}.
     *
     * @param <T> The type of argument to the operator
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code TernaryOperator} which uses the {@code right} parameter as argument for the given {@code
     * UnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> TernaryOperator<T> onlyRight(@Nonnull final UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (t, u, v) -> operator.apply(v);
    }
}
