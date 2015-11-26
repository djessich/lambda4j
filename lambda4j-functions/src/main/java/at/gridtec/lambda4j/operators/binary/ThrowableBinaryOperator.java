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

import at.gridtec.lambda4j.comparator.ThrowableComparator;
import at.gridtec.lambda4j.function.ThrowableBiFunction;
import at.gridtec.lambda4j.operators.unary.ThrowableUnaryOperator;
import at.gridtec.lambda4j.throwables.ThrownByFunctionalInterfaceException;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * Represents an operation upon two operands of the same type, producing a result of the same type as the operands which
 * is able to throw any {@link Throwable}. This is a specialization of {@link ThrowableBiFunction} for the case where
 * the operands and the result are all of the same type.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(Object, Object)}.
 *
 * @param <T> The type of the operands and result of the operator
 * @apiNote This is a throwable JRE lambda
 * @see BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBinaryOperator<T> extends BinaryOperator<T> {

    /**
     * Calls the given {@link ThrowableBinaryOperator} with the given arguments and returns its result.
     *
     * @param <T> The type of the operands and result of the operator
     * @param operator The operator to be called
     * @param left The first argument to the operator (left input)
     * @param right The third argument to the operator (right input)
     * @return The result from the given {@code ThrowableBinaryOperator}.
     * @throws NullPointerException If the given operator is {@code null}
     * @throws Throwable Any throwable from the given operators action
     */
    @SuppressWarnings("unchecked")
    static <T> T call(@Nonnull final ThrowableBinaryOperator<? super T> operator, T left, T right) throws Throwable {
        Objects.requireNonNull(operator);
        return (T) operator.applyThrows(left, right);
    }

    /**
     * Creates a {@link ThrowableBinaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * ThrowableUnaryOperator}.
     *
     * @param <T> The type of argument to the operator
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code ThrowableBinaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code ThrowableUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ThrowableBinaryOperator<T> onlyLeft(@Nonnull final ThrowableUnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyThrows(left);
    }

    /**
     * Creates a {@link ThrowableBinaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * ThrowableUnaryOperator}.
     *
     * @param <T> The type of argument to the operator
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code ThrowableBinaryOperator} which uses the {@code right} parameter as argument for the
     * given {@code ThrowableUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ThrowableBinaryOperator<T> onlyRight(@Nonnull final ThrowableUnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyThrows(right);
    }

    /**
     * Creates a {@link ThrowableBinaryOperator} which always returns a given value.
     *
     * @param <T> The type of the arguments and the return value for this operator
     * @param r The return value for the constant
     * @return A {@code ThrowableBinaryOperator} which always returns a given value.
     */
    @Nonnull
    static <T> ThrowableBinaryOperator<T> constant(T r) {
        return (left, right) -> r;
    }

    /**
     * Returns a {@link ThrowableBinaryOperator} which returns the lesser of two elements, according to the specified
     * {@code ThrowableComparator}.
     *
     * @param <T> The type of the arguments and the return value for this operator
     * @param comparator A {@code ThrowableComparator} for comparing the operators operands
     * @return A {@code ThrowableBinaryOperator} which returns the lesser of two elements, according to the supplied
     * {@code ThrowableComparator}
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ThrowableBinaryOperator<T> minBy(@Nonnull final ThrowableComparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (left, right) -> comparator.compareThrows(left, right) <= 0 ? left : right;
    }

    /**
     * Returns a {@link ThrowableBinaryOperator} which returns the greater of two elements, according to the specified
     * {@code ThrowableComparator}.
     *
     * @param <T> The type of the arguments and the return value for this operator
     * @param comparator A {@code ThrowableComparator} for comparing the operators operands
     * @return A {@code ThrowableBinaryOperator} which returns the greater of two elements, according to the supplied
     * {@code ThrowableComparator}
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ThrowableBinaryOperator<T> maxBy(@Nonnull final ThrowableComparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (left, right) -> comparator.compareThrows(left, right) >= 0 ? left : right;
    }

    /**
     * Applies this operator to the given arguments. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param left The first argument to the operator
     * @param right The second argument to the operator
     * @return The return value from the operator.
     * @throws Throwable Any throwable from this operators action
     */
    T applyThrows(T left, T right) throws Throwable;

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator
     * @param right The second argument to the operator
     * @return The return value from the operator.
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #applyThrows(Object, Object)} method of this operator and catches the thrown
     * {@link Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is
     * propagated as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default T apply(T left, T right) {
        try {
            return applyThrows(left, right);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable);
        }
    }
}
