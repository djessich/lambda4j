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
package at.gridtec.lambda4j.predicates.primitives.obj;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of an object-valued and a {@code byte}-valued argument. This is the
 * {@code (reference, byte)}-consuming specialization of {@link BiPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, byte)};
 *
 * @param <T> The type of argument to the predicate
 * @see java.util.function.BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjBytePredicate<T> {

    /**
     * Creates a {@link ObjBytePredicate} which always returns a given value.
     *
     * @param <T> The type of argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ObjBytePredicate} which always returns a given value.
     */
    @Nonnull
    static <T> ObjBytePredicate<T> constant(boolean ret) {
        return (t, value) -> ret;
    }

    /**
     * Returns a {@link ObjBytePredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@link Objects#equals(Object)} and {@code value == target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The target reference with which to compare for equality, which may be {@code null}
     * @param targetValue The target value with which to compare for equality
     * @return A {@code ObjBytePredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, byte)
     */
    @Nonnull
    static <T> ObjBytePredicate isEqual(@Nullable Object targetRef, byte targetValue) {
        return (t, value) -> ((t == null) ? targetRef == null : t.equals(targetRef)) && (value == targetValue);
    }

    /**
     * Returns a {@link ObjBytePredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The target reference with which to compare for equality, which may be {@code null}
     * @param targetValue The target value with which to compare for equality
     * @return A {@code ObjBytePredicate} that tests if the given arguments are not equal to the ones of this predicate.
     * @see #isEqual(Object, byte)
     */
    @Nonnull
    static <T> ObjBytePredicate isNotEqual(@Nullable Object targetRef, byte targetValue) {
        return (t, value) -> !(t == null ? targetRef == null : t.equals(targetRef)) && (value != targetValue);
    }

    /**
     * Returns a {@link ObjBytePredicate} that always returns {@code true}.
     *
     * @return A {@link ObjBytePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static ObjBytePredicate alwaysTrue() {
        return (t, value) -> true;
    }

    /**
     * Returns a {@link ObjBytePredicate} the always returns {@code false}.
     *
     * @return A {@link ObjBytePredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static ObjBytePredicate alwaysFalse() {
        return (t, value) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(T t, byte value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns an {@link ObjBytePredicate} that represents the logical negation of this one.
     *
     * @return An {@code ObjBytePredicate} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    @Nonnull
    default ObjBytePredicate<T> negate() {
        return (t, value) -> !test(t, value);
    }

    /**
     * Returns a composed {@link ObjBytePredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ObjBytePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ObjBytePredicate} that will be logically-ANDed with this one
     * @return A composed {@code ObjBytePredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(ObjBytePredicate)
     * @see #xor(ObjBytePredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    @Nonnull
    default ObjBytePredicate<T> and(@Nonnull final ObjBytePredicate<T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) && other.test(t, value);
    }

    /**
     * Returns a composed {@link ObjBytePredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ObjBytePredicate} throws an exception, the {@code other} {@code ObjBytePredicate} will not be evaluated.
     *
     * @param other A {@code ObjBytePredicate} that will be logically-ORed with this one
     * @return A composed {@code ObjBytePredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ObjBytePredicate)
     * @see #xor(ObjBytePredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    default ObjBytePredicate<T> or(final ObjBytePredicate<T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) && other.test(t, value);
    }

    /**
     * Returns a composed {@link ObjBytePredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code ObjBytePredicate} throws an exception, the {@code other} {@code ObjBytePredicate} will not be
     * evaluated.
     *
     * @param other A {@code ObjBytePredicate} that will be logically-XORed with this one
     * @return A composed {@code ObjBytePredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ObjBytePredicate)
     * @see #or(ObjBytePredicate)
     */
    default ObjBytePredicate<T> xor(final ObjBytePredicate<T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) ^ other.test(t, value);
    }

    /**
     * Returns a composed {@link BiPredicate} which represents this {@link ObjBytePredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method is just convenience to provide the ability to use
     * this {@code ObjBytePredicate} with JRE specific methods, only accepting {@code BiPredicate}.
     *
     * @return A composed {@code BiPredicate} which represents this {@code ObjBytePredicate}.
     */
    @Nonnull
    default BiPredicate<T, Byte> boxed() {
        return this::test;
    }
}
