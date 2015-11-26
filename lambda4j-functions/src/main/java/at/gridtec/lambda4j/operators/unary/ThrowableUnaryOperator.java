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
package at.gridtec.lambda4j.operators.unary;

import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.throwables.ThrownByFunctionalInterfaceException;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents an operation upon a single operand of a type, producing a result of the same type as the operand which is
 * able to throw any {@link Throwable}. This is a specialization of {@link ThrowableFunction} for the case where the
 * operand and the result are all of the same type.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(Object)}.
 *
 * @param <T> The type of the operands and result of the operator
 * @apiNote This is a throwable JRE lambda
 * @see UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableUnaryOperator<T> extends UnaryOperator<T> {

    /**
     * Calls the given {@link ThrowableUnaryOperator} with the given argument and returns its result.
     *
     * @param <T> The type of the operands and result of the operator
     * @param operator The operator to be called
     * @param operand The argument to the operator
     * @return The result from the given {@code ThrowableBinaryOperator}.
     * @throws NullPointerException If the given operator is {@code null}
     * @throws Throwable Any throwable from the given operators action
     */
    @SuppressWarnings("unchecked")
    static <T> T call(@Nonnull final ThrowableUnaryOperator<? super T> operator, T operand) throws Throwable {
        Objects.requireNonNull(operator);
        return (T) operator.applyThrows(operand);
    }

    /**
     * Creates a {@link ThrowableUnaryOperator} which always returns a given value.
     *
     * @param <T> The type of argument for the operator
     * @param r The return value for the constant
     * @return A {@code ThrowableUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static <T> ThrowableUnaryOperator<T> constant(T r) {
        return operand -> r;
    }

    /**
     * Returns a {@link ThrowableUnaryOperator} that always returns its input argument.
     *
     * @return A {@code ThrowableUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static ThrowableUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Applies this operator to the given argument. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param operand The argument to the operator
     * @return The return value from the operator.
     * @throws Throwable Any throwable from this operators action
     */
    T applyThrows(T operand) throws Throwable;

    /**
     * Applies this operator to the given argument.
     *
     * @param operand The argument to the operator
     * @return The return value from the operator.
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #applyThrows(Object)} method of this operator and catches the thrown {@link
     * Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is propagated
     * as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default T apply(T operand) {
        try {
            return applyThrows(operand);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable);
        }
    }
}
