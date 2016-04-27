/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.functions.operator.ternary;

import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.function.tri.TriFunction;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts three input arguments and produces a result.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, Object, Object)}.
 *
 * @param <T> The type of the arguments to the operator and of return from the operator
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TernaryOperator<T> extends Lambda, TriFunction<T, T, T, T> {

    /**
     * Constructs a {@link TernaryOperator} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TernaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T> TernaryOperator<T> of(@Nonnull final TernaryOperator<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link TernaryOperator} with the given arguments and returns its result.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param operator The operator to be called
     * @param t The first argument to the operator
     * @param u The second argument to the operator
     * @param v The third argument to the operator
     * @return The result from the given {@code TernaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> T call(@Nonnull final TernaryOperator<T> operator, T t, T u, T v) {
        Objects.requireNonNull(operator);
        return operator.apply(t, u, v);
    }

    /**
     * Creates a {@link TernaryOperator} which uses the {@code first} parameter of this one as argument for the given
     * {@link UnaryOperator}.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code TernaryOperator} which uses the {@code first} parameter of this one as argument for the
     * given {@code UnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> TernaryOperator<T> onlyFirst(@Nonnull final UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (t, u, v) -> operator.apply(t);
    }

    /**
     * Creates a {@link TernaryOperator} which uses the {@code second} parameter of this one as argument for the given
     * {@link UnaryOperator}.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code TernaryOperator} which uses the {@code second} parameter of this one as argument for the
     * given {@code UnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> TernaryOperator<T> onlySecond(@Nonnull final UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (t, u, v) -> operator.apply(u);
    }

    /**
     * Creates a {@link TernaryOperator} which uses the {@code third} parameter of this one as argument for the given
     * {@link UnaryOperator}.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param operator The operator which accepts the {@code third} parameter of this one
     * @return Creates a {@code TernaryOperator} which uses the {@code third} parameter of this one as argument for the
     * given {@code UnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> TernaryOperator<T> onlyThird(@Nonnull final UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (t, u, v) -> operator.apply(v);
    }

    /**
     * Creates a {@link TernaryOperator} which always returns a given value.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param ret The return value for the constant
     * @return A {@code TernaryOperator} which always returns a given value.
     */
    @Nonnull
    static <T> TernaryOperator<T> constant(T ret) {
        return (t, u, v) -> ret;
    }

}
