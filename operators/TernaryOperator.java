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

package at.gridtec.internals.lang.function.operators;

import at.gridtec.internals.lang.function.TriFunction;

import java.util.Objects;

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
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> TernaryOperator<T> constant(T r) {
        Objects.requireNonNull(r);
        return (t, u, v) -> r;
    }
}
