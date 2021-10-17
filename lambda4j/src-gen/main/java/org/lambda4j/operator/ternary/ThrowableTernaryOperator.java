/*
 * Copyright (c) 2021 The lambda4j authors.
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
package org.lambda4j.operator.ternary;

import org.lambda4j.Lambda;
import org.lambda4j.function.tri.ThrowableTriFunction;
import org.lambda4j.operator.unary.ThrowableUnaryOperator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Represents an operation that accepts three input arguments and produces a
 * result which is able to throw any {@link Throwable}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(Object, Object, Object)}.
 *
 * @param <T> The type of the arguments to the operator and of return from the operator
 * @param <X> The type of the throwable to be thrown by this operator
 * @see ThrowableTernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableTernaryOperator<T, X extends Throwable> extends Lambda, ThrowableTriFunction<T, T, T, T, X> {

    /**
     * Constructs a {@link ThrowableTernaryOperator} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param <X> The type of the throwable to be thrown by this operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableTernaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, X extends Throwable> ThrowableTernaryOperator<T, X> of(
            @Nullable final ThrowableTernaryOperator<T, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableTernaryOperator} with the given arguments and returns its result.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator to be called
     * @param t The first argument to the operator
     * @param u The second argument to the operator
     * @param v The third argument to the operator
     * @return The result from the given {@code ThrowableTernaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this operators action
     */
    static <T, X extends Throwable> T call(@Nonnull final ThrowableTernaryOperator<T, ? extends X> operator, T t, T u,
            T v) throws X {
        Objects.requireNonNull(operator);
        return operator.applyThrows(t, u, v);
    }

    /**
     * Creates a {@link ThrowableTernaryOperator} which uses the {@code first} parameter of this one as argument for the
     * given {@link ThrowableUnaryOperator}.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableTernaryOperator} which uses the {@code first} parameter of this one as argument
     * for the given {@code ThrowableUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableTernaryOperator<T, X> onlyFirst(
            @Nonnull final ThrowableUnaryOperator<T, ? extends X> operator) {
        Objects.requireNonNull(operator);
        return (t, u, v) -> operator.applyThrows(t);
    }

    /**
     * Creates a {@link ThrowableTernaryOperator} which uses the {@code second} parameter of this one as argument for
     * the given {@link ThrowableUnaryOperator}.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableTernaryOperator} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableTernaryOperator<T, X> onlySecond(
            @Nonnull final ThrowableUnaryOperator<T, ? extends X> operator) {
        Objects.requireNonNull(operator);
        return (t, u, v) -> operator.applyThrows(u);
    }

    /**
     * Creates a {@link ThrowableTernaryOperator} which uses the {@code third} parameter of this one as argument for the
     * given {@link ThrowableUnaryOperator}.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableTernaryOperator} which uses the {@code third} parameter of this one as argument
     * for the given {@code ThrowableUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableTernaryOperator<T, X> onlyThird(
            @Nonnull final ThrowableUnaryOperator<T, ? extends X> operator) {
        Objects.requireNonNull(operator);
        return (t, u, v) -> operator.applyThrows(v);
    }

    /**
     * Creates a {@link ThrowableTernaryOperator} which always returns a given value.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param <X> The type of the throwable to be thrown by this operator
     * @param ret The return value for the constant
     * @return A {@code ThrowableTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableTernaryOperator<T, X> constant(T ret) {
        return (t, u, v) -> ret;
    }

}