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
package at.gridtec.lambda4j.function;

import at.gridtec.lambda4j.throwables.ThrownByFunctionalInterfaceException;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts three arguments and produces a result which is able to throw any {@link
 * Throwable}. This is the three-arity specialization of {@link ThrowableFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(Object)}.
 *
 * @param <T> The type of argument for the function
 * @param <R> The type of return value from the function
 * @apiNote This is a throwable JRE lambda.
 * @see Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableFunction<T, R> extends Function<T, R> {

    /**
     * Calls the given {@link ThrowableFunction} with the given argument and returns its result.
     *
     * @param <T> The type of argument for the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code ThrowableFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @throws Throwable Any throwable from the given functions action
     */
    static <T, R> R call(@Nonnull final ThrowableFunction<? super T, ? extends R> function, T t) throws Throwable {
        Objects.requireNonNull(function);
        return function.applyThrows(t);
    }

    /**
     * Creates a {@link ThrowableFunction} which always returns a given value.
     *
     * @param <T> The type of argument for the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ThrowableFunction} which always returns a given value.
     */
    @Nonnull
    static <T, R> ThrowableFunction<T, R> constant(R r) {
        return t -> r;
    }

    /**
     * Applies this function to the given argument. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     * @throws Throwable Any throwable from this functions action
     */
    R applyThrows(T t) throws Throwable;

    /**
     * Applies this function to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #applyThrows(Object)} method of this function and catches the thrown {@link
     * Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is propagated
     * as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default R apply(T t) {
        try {
            return applyThrows(t);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable);
        }
    }
}
