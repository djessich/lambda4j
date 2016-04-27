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
import at.gridtec.lambda4j.functions.function.ThrowableFunction;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts one input argument and produces a result which is able to throw any {@link
 * Throwable}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(Object)}.
 *
 * @param <T> The type of the argument to the operator and of return from the operator
 * @param <X> The type of the throwable to be thrown by this operator
 * @apiNote This is a throwable JDK lambda.
 * @see ThrowableUnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableUnaryOperator<T, X extends Throwable>
        extends Lambda, ThrowableFunction<T, T, X>, UnaryOperator<T> {

    /**
     * Constructs a {@link ThrowableUnaryOperator} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the argument to the operator and of return from the operator
     * @param <X> The type of the throwable to be thrown by this operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableUnaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableUnaryOperator<T, X> of(
            @Nonnull final ThrowableUnaryOperator<T, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableUnaryOperator} with the given argument and returns its result.
     *
     * @param <T> The type of the argument to the operator and of return from the operator
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator to be called
     * @param t The argument to the operator
     * @return The result from the given {@code ThrowableUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this operators action
     */
    static <T, X extends Throwable> T call(@Nonnull final ThrowableUnaryOperator<T, ? extends X> operator, T t) throws
            X {
        Objects.requireNonNull(operator);
        return operator.applyThrows(t);
    }

    /**
     * Returns a {@link ThrowableUnaryOperator} that always returns its input argument.
     *
     * @param <T> The type of the argument to the operator and of return from the operator
     * @param <X> The type of the throwable to be thrown by this operator
     * @return A {@code  ThrowableUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableUnaryOperator<T, X> identity() {
        return (t) -> t;
    }

    /**
     * Creates a {@link ThrowableUnaryOperator} which always returns a given value.
     *
     * @param <T> The type of the argument to the operator and of return from the operator
     * @param <X> The type of the throwable to be thrown by this operator
     * @param ret The return value for the constant
     * @return A {@code ThrowableUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableUnaryOperator<T, X> constant(T ret) {
        return (t) -> ret;
    }

}
