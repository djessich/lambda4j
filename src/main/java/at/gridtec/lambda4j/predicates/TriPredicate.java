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
package at.gridtec.lambda4j.predicates;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of three arguments. This is the three-arity specialization of {@link
 * Predicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @param <V> The type of the third argument to the predicate
 * @see java.util.function.Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriPredicate<T, U, V> {

    /**
     * Creates a {@link TriPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code TriPredicate} which always returns a given value.
     */
    @Nonnull
    static <T, U, V> TriPredicate<T, U, V> constant(boolean ret) {
        return (t, v, u) -> ret;
    }

    /**
     * Returns a {@link TriPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@link Objects#equals(Object)} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param targetRef1 The first object reference with which to compare for equality, which may be {@code null}
     * @param targetRef2 The second object reference with which to compare for equality, which may be {@code null}
     * @param targetRef3 The third object reference with which to compare for equality, which may be {@code null}
     * @return A {@code TriPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, Object, Object)
     */
    @Nonnull
    //@formatter:off
    static <T, U, V> TriPredicate<T, U, V> isEqual(@Nullable Object targetRef1, @Nullable Object targetRef2,
            @Nullable Object targetRef3) {
        return (t, u, v) -> (t == null ? targetRef1 == null : t.equals(targetRef1))
                && (u == null ? targetRef2 == null : u.equals(targetRef2))
                && (v == null ? targetRef3 == null : v.equals(targetRef3));
    }
    //@formatter:on

    /**
     * Returns a {@link TriPredicate} that tests if given arguments are not equal to the ones of this predicate
     * according to {@link Objects#equals(Object)} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param targetRef1 The first object reference with which to compare for equality, which may be {@code null}
     * @param targetRef2 The second object reference with which to compare for equality, which may be {@code null}
     * @param targetRef3 The third object reference with which to compare for equality, which may be {@code null}
     * @return A {@code TriPredicate} that tests if the given arguments are not equal to the ones of this predicate.
     * @see #isEqual(Object, Object, Object)
     */
    @Nonnull
    //@formatter:off
    static <T, U, V> TriPredicate<T, U, V> isNotEqual(@Nullable Object targetRef1, @Nullable Object targetRef2,
            @Nullable Object targetRef3) {
        return (t, u, v) -> !(t == null ? targetRef1 == null : t.equals(targetRef1))
                || !(u == null ? targetRef2 == null : u.equals(targetRef2))
                || !(v == null ? targetRef3 == null : v.equals(targetRef3));
    }
    //@formatter:on

    /**
     * Returns a {@link TriPredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @return A {@link TriPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T, U, V> TriPredicate<T, U, V> alwaysTrue() {
        return (t, u, v) -> true;
    }

    /**
     * Returns a {@link TriPredicate} the always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @return A {@link TriPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T, U, V> TriPredicate<T, U, V> alwaysFalse() {
        return (t, u, v) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param v The third argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(T t, U u, V v);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a {@link TriPredicate} that represents the logical negation of this one.
     *
     * @return A {@code TriPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default TriPredicate<T, U, V> negate() {
        return (t, u, v) -> !test(t, u, v);
    }

    /**
     * Returns a composed {@link TriPredicate} that represents a short-circuiting logical AND of this predicate and
     * another.  When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code TriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code TriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code TriPredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(TriPredicate)
     * @see #xor(TriPredicate)
     */
    @Nonnull
    default TriPredicate<T, U, V> and(@Nonnull final TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) && other.test(t, u, v);
    }

    /**
     * Returns a composed {@link TriPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code TriPredicate} throws an exception, the {@code other} {@code TriPredicate} will not be evaluated.
     *
     * @param other A {@code TriPredicate} that will be logically-ORed with this one
     * @return A composed {@code TriPredicate} that represents the short-circuiting logical OR of this predicate and the
     * {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(TriPredicate)
     * @see #xor(TriPredicate)
     */
    default TriPredicate<T, U, V> or(final TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) && other.test(t, u, v);
    }

    /**
     * Returns a composed {@link TriPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code TriPredicate} throws an exception, the {@code other} {@code TriPredicate} will not be evaluated.
     *
     * @param other A {@code TriPredicate} that will be logically-XORed with this one
     * @return A composed {@code TriPredicate} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(TriPredicate)
     * @see #or(TriPredicate)
     */
    default TriPredicate<T, U, V> xor(final TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) ^ other.test(t, u, v);
    }
}
