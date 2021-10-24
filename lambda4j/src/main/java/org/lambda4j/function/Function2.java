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

package org.lambda4j.function;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.Consumer2;

/**
 * Represents an operation that accepts one input argument and produces a result.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object)}.
 *
 * @param <T> The type of the argument to the function
 * @param <R> The type of return value from the function
 * @apiNote This is a JDK lambda.
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface Function2<T, R> extends Lambda, Function<T, R> {

    /**
     * Constructs a {@link Function2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the argument to the function
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code Function2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, R> Function2<T, R> of(@Nullable Function2<T, R> expression) {
        return expression;
    }

    /**
     * Lifts a partial {@link Function} into a total {@link Function2} that returns an {@link Optional} result.
     *
     * @param <T> The type of the argument to the function
     * @param <R> The type of return value from the function
     * @param partial A function that is only defined for some values in its domain
     * @return A partial {@code Function} lifted into a total {@code Function2} that returns an {@code Optional} result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, R> Function2<T, Optional<R>> lift(@Nonnull Function<? super T, ? extends R> partial) {
        Objects.requireNonNull(partial);
        return t -> Optional.ofNullable(partial.apply(t));
    }

    /**
     * Calls the given {@link Function} with the given argument and returns its result.
     *
     * @param <T> The type of the argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code Function2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, R> R call(@Nonnull Function<? super T, ? extends R> function, T t) {
        Objects.requireNonNull(function);
        return function.apply(t);
    }

    /**
     * Returns a {@link Function2} that always returns its input argument.
     *
     * @param <T> The type of the argument to the function and of return from the function
     * @return A {@code Function2} that always returns its input argument
     */
    @Nonnull
    static <T> Function2<T, T> identity() {
        return t -> t;
    }

    /**
     * Creates a {@link Function2} which always returns a given value.
     *
     * @param <T> The type of the argument to the function
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code Function2} which always returns a given value.
     */
    @Nonnull
    static <T, R> Function2<T, R> constant(R ret) {
        return t -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     */
    @Override
    R apply(T t);

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link Function2} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code Function2} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Override
    @Nonnull
    default <A> Function2<A, R> compose(@Nonnull Function<? super A, ? extends T> before) {
        Objects.requireNonNull(before);
        return a -> apply(before.apply(a));
    }

    /**
     * Returns a composed {@link Function2} that first applies this function to its input, and then applies the {@code
     * after} function to the result. If evaluation of either operation throws an exception, it is relayed to the caller
     * of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code Function2} that first applies this function to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Override
    @Nonnull
    default <S> Function2<T, S> andThen(@Nonnull Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return t -> after.apply(apply(t));
    }

    /**
     * Returns a composed {@link Consumer2} that fist applies this function to its input, and then consumes the result
     * using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer2} that first applies this function to its input, and then consumes the result
     * using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default Consumer2<T> consume(@Nonnull Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.accept(apply(t));
    }

    /**
     * Returns a curried version of this function.
     *
     * @return A curried version of this function.
     */
    @Nonnull
    default Function2<T, R> curried() {
        return this;
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default Function2<T, R> reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link Function2}. Whenever it is called, the mapping between the
     * input parameter and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code Function2}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default Function2<T, R> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<T, R> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (Function2<T, R> & Memoized) t -> {
                R returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(t, this::apply);
                }
                return returnValue;
            };
        }
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     * @deprecated Use {@code lift} method for lifting this function.
     */
    @Deprecated
    @Nonnull
    default Function2<T, Optional<R>> nonNull() {
        return t -> Optional.ofNullable(apply(t));
    }

}
