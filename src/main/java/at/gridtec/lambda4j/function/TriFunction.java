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

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts three arguments and produces a result. This is the three-arity specialization of
 * {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @param <R> The type of return value from the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriFunction<T, U, V, R> {

    /**
     * Creates a {@link TriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code TriFunction} which always returns a given value.
     */
    static <T, U, V, R> TriFunction<T, U, V, R> constant(R r) {
        return (t, u, v) -> r;
    }

    /**
     * Creates a {@link TriFunction} which uses the first parameter of this one as argument for the given {@link
     * Function}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param <V> The type of the third argument to the operation
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriFunction} which uses the first parameter of this one as argument for the given {@code
     * Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U, V, R> TriFunction<T, U, V, R> onlyFirst(final Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.apply(t);
    }

    /**
     * Creates a {@link TriFunction} which uses the second parameter of this one as argument for the given {@link
     * Function}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param <V> The type of the third argument to the operation
     * @param <R> The type of return value from the function
     * @param function The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriFunction} which uses the second parameter of this one as argument for the given
     * {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U, V, R> TriFunction<T, U, V, R> onlySecond(final Function<? super U, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.apply(u);
    }

    /**
     * Creates a {@link TriFunction} which uses the third parameter of this one as argument for the given {@link
     * Function}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param <V> The type of the third argument to the operation
     * @param <R> The type of return value from the function
     * @param function The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriFunction} which uses the third parameter of this one as argument for the given {@code
     * Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U, V, R> TriFunction<T, U, V, R> onlyThird(final Function<? super V, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.apply(v);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return he return value from the function, which is its result.
     */
    R apply(T t, U u, V v);

    /**
     * Returns a composed {@link TriFunction} that applies the given {@code before} {@link Function}s to its input, and
     * then applies this function to the result. If evaluation of either of the given functions throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before function
     * @param <B> The type of the argument to the second before function
     * @param <C> The type of the argument to the third before function
     * @param before1 The first before {@code Function} to apply before this function is applied
     * @param before2 The second before {@code Function} to apply before this function is applied
     * @param before3 The third before {@code Function} to apply before this function is applied
     * @return A composed {@code TriFunction} that applies the given {@code before} {@code Function}s to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <A, B, C> TriFunction<A, B, C, R> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> apply(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies this function to its input, and then applies the {@code
     * after} {@link Function} to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFunction} that first applies this function and then applies the {@code after}
     * function.
     * @throws NullPointerException If given after function is {@code null}
     * @see #compose(Function, Function, Function)
     */
    default <S> TriFunction<T, U, V, S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(apply(t, u, v));
    }

    /**
     * Returns a curried version of this {@link ThrowableTriFunction}.
     *
     * @return A curried version of this {@code ThrowableTriFunction}.
     * @see #reversed(Function)
     */
    default Function<T, Function<U, Function<V, R>>> curried() {
        return t -> u -> v -> apply(t, u, v);
    }

    /**
     * Returns a reversed (uncurried) {@link TriFunction} from the given curried {@code TriFunction}.
     *
     * @param f A curried {@code TriFunction}
     * @return A reversed (uncurried) {@link TriFunction} from the given curried {@code TriFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #curried()
     */
    default TriFunction<T, U, V, R> reversed(
            Function<? super T, Function<? super U, Function<? super V, ? extends R>>> f) {
        Objects.requireNonNull(f);
        return (t, u, v) -> f.apply(t).apply(u).apply(v);
    }
}
