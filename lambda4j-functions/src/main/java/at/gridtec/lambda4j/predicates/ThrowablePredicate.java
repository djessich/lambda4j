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
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of a single argument which is able to throw any {@link Throwable}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(Object)}.
 *
 * @param <T> The type of the argument to the predicate
 * @apiNote This is a throwable JRE lambda
 * @see Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowablePredicate<T> extends Predicate<T> {

    /**
     * Calls the given {@link ThrowablePredicate} with the given argument and returns its result.
     *
     * @param <T> The type of the argument to the predicate
     * @param predicate The predicate to be called
     * @param t The argument to the predicate
     * @return The result from the given {@code ThrowableTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @throws Throwable Any throwable from the given predicates action
     */
    static <T> boolean call(@Nonnull final ThrowablePredicate<? super T> predicate, T t) throws Throwable {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(t);
    }

    /**
     * Creates a {@link ThrowablePredicate} which always returns a given value.
     *
     * @param <T> The type of argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowablePredicate} which always returns a given value.
     */
    static <T> ThrowablePredicate<T> constant(boolean ret) {
        return t -> ret;
    }

    /**
     * Returns a {@link ThrowablePredicate} that tests if the given argument is equal to the one of this predicate
     * according to {@link Objects#equals(Object)} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The object reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowablePredicate} that tests if the given argument is equal to the one of this predicate.
     * @see #isNotEqual(Object)
     */
    static <T> ThrowablePredicate<T> isEqual(final Object targetRef) {
        return t -> t == null ? targetRef == null : t.equals(targetRef);
    }

    /**
     * Returns a {@link ThrowablePredicate} that tests if the given argument is equal to the one of this predicate
     * according to {@link Objects#equals(Object)} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The object reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowablePredicate} that tests if the given argument is not equal to the one of this predicate.
     * @see #isEqual(Object)
     */
    static <T> ThrowablePredicate<T> isNotEqual(final Object targetRef) {
        return t -> !(t == null ? targetRef == null : t.equals(targetRef));
    }

    /**
     * Returns a {@link ThrowablePredicate} that always returns {@code true}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ThrowablePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    static <T> ThrowablePredicate<T> alwaysTrue() {
        return t -> true;
    }

    /**
     * Returns a {@link ThrowablePredicate} the always returns {@code false}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ThrowablePredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static <T> ThrowablePredicate<T> alwaysFalse() {
        return t -> false;
    }

    /**
     * Evaluates this predicate on the given argument. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param t The argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @throws Throwable Any throwable from this predicates action
     */
    boolean testThrows(T t) throws Throwable;

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t The argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #testThrows(Object)} method of this function and catches the thrown {@link
     * Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is propagated
     * as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default boolean test(T t) {
        try {
            return testThrows(t);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable);
        }
    }
}
