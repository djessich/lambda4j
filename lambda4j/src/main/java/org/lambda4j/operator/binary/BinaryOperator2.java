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
package org.lambda4j.operator.binary;

import org.lambda4j.Lambda;
import org.lambda4j.function.bi.BiFunction2;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts two input arguments and produces a
 * result.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T> The type of the arguments to the operator and of return from the operator
 * @apiNote This is a JDK lambda.
 * @see BinaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BinaryOperator2<T> extends Lambda, BiFunction2<T, T, T>, BinaryOperator<T> {

    /**
     * Constructs a {@link BinaryOperator2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BinaryOperator2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T> BinaryOperator2<T> of(@Nullable final BinaryOperator2<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link BinaryOperator} with the given arguments and returns its result.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param operator The operator to be called
     * @param t The first argument to the operator
     * @param u The second argument to the operator
     * @return The result from the given {@code BinaryOperator2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> T call(@Nonnull final BinaryOperator<T> operator, T t, T u) {
        Objects.requireNonNull(operator);
        return operator.apply(t, u);
    }

    /**
     * Creates a {@link BinaryOperator2} which uses the {@code first} parameter of this one as argument for the given
     * {@link UnaryOperator}.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code BinaryOperator2} which uses the {@code first} parameter of this one as argument for the
     * given {@code UnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> BinaryOperator2<T> onlyFirst(@Nonnull final UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (t, u) -> operator.apply(t);
    }

    /**
     * Creates a {@link BinaryOperator2} which uses the {@code second} parameter of this one as argument for the given
     * {@link UnaryOperator}.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code BinaryOperator2} which uses the {@code second} parameter of this one as argument for the
     * given {@code UnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> BinaryOperator2<T> onlySecond(@Nonnull final UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (t, u) -> operator.apply(u);
    }

    /**
     * Creates a {@link BinaryOperator2} which always returns a given value.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param ret The return value for the constant
     * @return A {@code BinaryOperator2} which always returns a given value.
     */
    @Nonnull
    static <T> BinaryOperator2<T> constant(T ret) {
        return (t, u) -> ret;
    }

    /**
     * Returns a {@link BinaryOperator2} which returns the lesser of two elements according to the specified {@code
     * Comparator}.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code BinaryOperator2} which returns the lesser of its operands, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static <T> BinaryOperator2<T> minBy(@Nonnull final Comparator<T> comparator) {
        Objects.requireNonNull(comparator);
        return (t, u) -> comparator.compare(t, u) <= 0 ? t : u;
    }

    /**
     * Returns a {@link BinaryOperator2} which returns the greater of two elements according to the specified {@code
     * Comparator}.
     *
     * @param <T> The type of the arguments to the operator and of return from the operator
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code BinaryOperator2} which returns the greater of its operands, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static <T> BinaryOperator2<T> maxBy(@Nonnull final Comparator<T> comparator) {
        Objects.requireNonNull(comparator);
        return (t, u) -> comparator.compare(t, u) >= 0 ? t : u;
    }

}