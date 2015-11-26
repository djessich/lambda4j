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

import at.gridtec.lambda4j.throwables.ThrownByFunctionalInterfaceException;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of two arguments which is able to throw any {@link Throwable}. This
 * is the two-arity specialization of {@link ThrowablePredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(Object, Object)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @apiNote This is a throwable JRE lambda
 * @see BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiPredicate<T, U> extends BiPredicate<T, U> {

    /**
     * Calls the given {@link ThrowableBiPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param function The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @return The result from the given {@code ThrowableBiPredicate}.
     * @throws NullPointerException If the given function is {@code null}
     * @throws Throwable Any throwable from this predicates action
     */
    static <T, U> boolean call(@Nonnull final ThrowableBiPredicate<? super T, ? super U> function, T t, U u) throws
            Throwable {
        Objects.requireNonNull(function);
        return function.testThrows(t, u);
    }

    /**
     * Creates a {@link ThrowableBiPredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@link ThrowablePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiPredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@code ThrowablePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ThrowableBiPredicate<T, U> onlyFirst(@Nonnull final ThrowablePredicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u) -> predicate.testThrows(t);
    }

    /**
     * Creates a {@link ThrowableBiPredicate} which uses the {@code second} parameter of this one as argument for the
     * given {@link ThrowablePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiPredicate} which uses the {@code second} parameter of this one as argument
     * for the given {@code ThrowablePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ThrowableBiPredicate<T, U> onlySecond(@Nonnull final ThrowablePredicate<? super U> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u) -> predicate.testThrows(u);
    }

    /**
     * Creates a {@link ThrowableBiPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument for the predicate
     * @param <U> The type of the second argument for the predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowableBiPredicate} which always returns a given value.
     */
    static <T, U> ThrowableBiPredicate<T, U> constant(boolean ret) {
        return (t, u) -> ret;
    }

    /**
     * Returns a {@link ThrowableBiPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@link Objects#equals(Object)} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first object reference with which to compare for equality, which may be {@code null}
     * @param targetRef2 The second object reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowableBiPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, Object)
     */
    //@formatter:off
    static <T, U> ThrowableBiPredicate<T, U> isEqual(final Object targetRef1, final Object targetRef2) {
        return (t, u) -> (t == null ? targetRef1 == null : t.equals(targetRef1))
                && (u == null ? targetRef2 == null : u.equals(targetRef2));
    }
    //@formatter:on

    /**
     * Returns a {@link ThrowableBiPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate according to {@link Objects#equals(Object)} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first object reference with which to compare for equality, which may be {@code null}
     * @param targetRef2 The second object reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowableBiPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(Object, Object)
     */
    //@formatter:off
    static <T, U> ThrowableBiPredicate<T, U> isNotEqual(final Object targetRef1, final Object targetRef2) {
        return (t, u) -> !(t == null ? targetRef1 == null : t.equals(targetRef1))
                || !(u == null ? targetRef2 == null : u.equals(targetRef2));
    }
    //@formatter:on

    /**
     * Returns a {@link ThrowableBiPredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link ThrowableBiPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    static <T, U> ThrowableBiPredicate<T, U> alwaysTrue() {
        return (t, u) -> true;
    }

    /**
     * Returns a {@link ThrowableBiPredicate} the always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link ThrowableBiPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static <T, U> ThrowableBiPredicate<T, U> alwaysFalse() {
        return (t, u) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     * @throws Throwable Any throwable from this predicates action
     */
    boolean testThrows(T t, U u) throws Throwable;

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #testThrows(Object, Object)} method of this function and catches the thrown
     * {@link Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is
     * propagated as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default boolean test(T t, U u) {
        try {
            return testThrows(t, u);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable);
        }
    }
}
