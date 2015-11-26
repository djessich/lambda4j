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
import java.util.function.BiFunction;

/**
 * Represents a function that accepts two arguments and produces a result which is able to throw any {@link Throwable}.
 * This is the two-arity specialization of {@link ThrowableFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <R> The type of return value from the function
 * @apiNote This is a throwable JRE lambda.
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiFunction<T, U, R> extends BiFunction<T, U, R> {

    /**
     * Calls the given {@link ThrowableBiFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The result from the given {@code ThrowableBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     * @throws Throwable Any throwable from the given functions action
     */
    static <T, U, R> R call(@Nonnull final ThrowableBiFunction<? super T, ? super U, ? extends R> function, T t,
            U u) throws Throwable {
        Objects.requireNonNull(function);
        return function.applyThrows(t, u);
    }

    /**
     * Creates a {@link ThrowableBiFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ThrowableFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code ThrowableFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> ThrowableBiFunction<T, U, R> onlyFirst(
            @Nonnull final ThrowableFunction<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u) -> function.applyThrows(t);
    }

    /**
     * Creates a {@link ThrowableBiFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ThrowableFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code ThrowableFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> ThrowableBiFunction<T, U, R> onlySecond(
            @Nonnull final ThrowableFunction<? super U, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u) -> function.applyThrows(u);
    }

    /**
     * Creates a {@link ThrowableBiFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ThrowableBiFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, R> ThrowableBiFunction<T, U, R> constant(R r) {
        return (t, u) -> r;
    }

    /**
     * Applies this function to the given arguments. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     * @throws Throwable Any throwable from this functions action
     */
    R applyThrows(T t, U u) throws Throwable;

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #applyThrows(Object, Object)} method of this function and catches the thrown
     * {@link Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is
     * propagated as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default R apply(T t, U u) {
        try {
            return applyThrows(t, u);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable);
        }
    }
}
