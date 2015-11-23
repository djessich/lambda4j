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

import at.gridtec.lambda4j.predicates.primitives.FloatPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of an object-valued and a {@code float}-valued argument. This is the
 * {@code (reference, float)}-consuming specialization of {@link BiPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, float)};
 *
 * @param <T> The type of argument to the predicate
 * @see BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjFloatPredicate<T> {

    /**
     * Calls the given {@link ObjFloatPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The result from the given {@code ObjFloatPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    static <T> boolean call(@Nonnull final ObjFloatPredicate<? super T> predicate, T t, float value) {
        Objects.requireNonNull(predicate);
        return predicate.test(t, value);
    }

    /**
     * Creates a {@link ObjFloatPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link Predicate}.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjFloatPredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@code Predicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ObjFloatPredicate<T> onlyFirst(@Nonnull final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (t, value) -> predicate.test(t);
    }

    /**
     * Creates a {@link ObjFloatPredicate} which uses the {@code second} parameter of this one as argument for the given
     * {@link FloatPredicate}.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjFloatPredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@code FloatPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T> ObjFloatPredicate<T> onlySecond(@Nonnull final FloatPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (t, value) -> predicate.test(value);
    }

    /**
     * Creates a {@link ObjFloatPredicate} which always returns a given value.
     *
     * @param <T> The type of argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ObjFloatPredicate} which always returns a given value.
     */
    @Nonnull
    static <T> ObjFloatPredicate<T> constant(boolean ret) {
        return (t, value) -> ret;
    }

    /**
     * Returns a {@link ObjFloatPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The target reference with which to compare for equality, which may be {@code null}
     * @param targetValue The target value with which to compare for equality
     * @return A {@code ObjFloatPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, float)
     */
    @Nonnull
    static <T> ObjFloatPredicate<T> isEqual(@Nullable Object targetRef, float targetValue) {
        return (t, value) -> (t == null ? targetRef == null : t.equals(targetRef)) && (value == targetValue);
    }

    /**
     * Returns a {@link ObjFloatPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The target reference with which to compare for equality, which may be {@code null}
     * @param targetValue The target value with which to compare for equality
     * @return A {@code ObjFloatPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(Object, float)
     */
    @Nonnull
    static <T> ObjFloatPredicate<T> isNotEqual(@Nullable Object targetRef, float targetValue) {
        return (t, value) -> !(t == null ? targetRef == null : t.equals(targetRef)) || (value != targetValue);
    }

    /**
     * Returns a {@link ObjFloatPredicate} that always returns {@code true}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ObjFloatPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T> ObjFloatPredicate<T> alwaysTrue() {
        return (t, value) -> true;
    }

    /**
     * Returns a {@link ObjFloatPredicate} the always returns {@code false}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ObjFloatPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T> ObjFloatPredicate<T> alwaysFalse() {
        return (t, value) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(T t, float value);

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
     * Returns an {@link ObjFloatPredicate} that represents the logical negation of this one.
     *
     * @return An {@code ObjFloatPredicate} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    @Nonnull
    default ObjFloatPredicate<T> negate() {
        return (t, value) -> !test(t, value);
    }

    /**
     * Returns a composed {@link ObjFloatPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ObjFloatPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ObjFloatPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ObjFloatPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(ObjFloatPredicate)
     * @see #xor(ObjFloatPredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    @Nonnull
    default ObjFloatPredicate<T> and(@Nonnull final ObjFloatPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) && other.test(t, value);
    }

    /**
     * Returns a composed {@link ObjFloatPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ObjFloatPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ObjFloatPredicate} that will be logically-ORed with this one
     * @return A composed {@code ObjFloatPredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ObjFloatPredicate)
     * @see #xor(ObjFloatPredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    @Nonnull
    default ObjFloatPredicate<T> or(@Nonnull final ObjFloatPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) && other.test(t, value);
    }

    /**
     * Returns a composed {@link ObjFloatPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code ObjFloatPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ObjFloatPredicate} that will be logically-XORed with this one
     * @return A composed {@code ObjFloatPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ObjFloatPredicate)
     * @see #or(ObjFloatPredicate)
     */
    @Nonnull
    default ObjFloatPredicate<T> xor(@Nonnull final ObjFloatPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> test(t, value) ^ other.test(t, value);
    }

    /**
     * Applies this predicate partially to one argument. The result is a predicate of arity {@code 1};
     *
     * @param t The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default FloatPredicate partial(T t) {
        return value -> test(t, value);
    }

    /**
     * Applies this predicate partially to one argument. The result is a predicate of arity {@code 1};
     *
     * @param value The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default Predicate<T> partial(byte value) {
        return t -> test(t, value);
    }

    /**
     * Applies this predicate partially to two arguments. The result is a predicate of arity {@code 0}.
     *
     * @param t The first argument to partially apply to the predicate
     * @param value The second argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default BooleanSupplier partial(T t, byte value) {
        return () -> test(t, value);
    }

    /**
     * Returns a composed {@link BiPredicate} which represents this {@link ObjFloatPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed. This method is just convenience to provide the ability to use
     * this {@code ObjFloatPredicate} with JRE specific methods, only accepting {@code BiPredicate}.
     *
     * @return A composed {@code BiPredicate} which represents this {@code ObjFloatPredicate}.
     */
    @Nonnull
    default BiPredicate<T, Float> boxed() {
        return this::test;
    }
}