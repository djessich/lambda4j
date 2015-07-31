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

import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of an object-valued and a {@code boolean}-valued argument. This is
 * the {@code (reference, boolean)}-consuming specialization of {@link BiPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, boolean)};
 *
 * @param <T> The type of argument to the predicate
 * @see java.util.function.BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjBooleanPredicate<T> {

    /**
     * Creates a {@link ObjBooleanPredicate} which always returns a given value.
     *
     * @param <T> The type of argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ObjBooleanPredicate} which always returns a given value.
     */
    static <T> ObjBooleanPredicate<T> constant(boolean ret) {
        return (t, value) -> ret;
    }

    /**
     * Returns a {@link ObjBooleanPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The first target value with which to compare for equality
     * @param targetValue The second target value with which to compare for equality
     * @return A {@code ObjBooleanPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, boolean)
     */
    static <T> ObjBooleanPredicate<T> isEqual(Object targetRef, boolean targetValue) {
        return (t, value) -> (t == null ? targetRef == null : t.equals(targetRef)) && (value == targetValue);
    }

    /**
     * Returns a {@link ObjBooleanPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate according to {@code value != target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The first target value with which to compare for equality
     * @param targetValue The second target value with which to compare for equality
     * @return A {@code ObjBooleanPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(Object, boolean)
     */
    static <T> ObjBooleanPredicate<T> isNotEqual(Object targetRef, boolean targetValue) {
        return (t, value) -> !(t == null ? targetRef == null : t.equals(targetRef)) || (value != targetValue);
    }

    /**
     * Returns a {@link ObjBooleanPredicate} the always returns {@code true}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ObjBooleanPredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static <T> ObjBooleanPredicate<T> alwaysTrue() {
        return (t, value) -> true;
    }

    /**
     * Returns a {@link ObjBooleanPredicate} the always returns {@code false}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ObjBooleanPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static <T> ObjBooleanPredicate<T> alwaysFalse() {
        return (t, value) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean test(T t, boolean value);
}
