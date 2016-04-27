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
package at.gridtec.lambda4j.functions.operator.unary;

import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.function.Function2;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts one input argument and produces a result.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object)}.
 *
 * @param <T> The type of the argument to the operator and of return from the operator
 * @apiNote This is a JDK lambda.
 * @see UnaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface UnaryOperator2<T> extends Lambda, Function2<T, T>, UnaryOperator<T> {

    /**
     * Constructs a {@link UnaryOperator2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the argument to the operator and of return from the operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code UnaryOperator2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T> UnaryOperator2<T> of(@Nonnull final UnaryOperator2<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link UnaryOperator} with the given argument and returns its result.
     *
     * @param <T> The type of the argument to the operator and of return from the operator
     * @param operator The operator to be called
     * @param t The argument to the operator
     * @return The result from the given {@code UnaryOperator2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> T call(@Nonnull final UnaryOperator<T> operator, T t) {
        Objects.requireNonNull(operator);
        return operator.apply(t);
    }

    /**
     * Returns a {@link UnaryOperator2} that always returns its input argument.
     *
     * @param <T> The type of the argument to the operator and of return from the operator
     * @return A {@code  UnaryOperator2} that always returns its input argument
     */
    @Nonnull
    static <T> UnaryOperator2<T> identity() {
        return (t) -> t;
    }

    /**
     * Creates a {@link UnaryOperator2} which always returns a given value.
     *
     * @param <T> The type of the argument to the operator and of return from the operator
     * @param ret The return value for the constant
     * @return A {@code UnaryOperator2} which always returns a given value.
     */
    @Nonnull
    static <T> UnaryOperator2<T> constant(T ret) {
        return (t) -> ret;
    }

}
