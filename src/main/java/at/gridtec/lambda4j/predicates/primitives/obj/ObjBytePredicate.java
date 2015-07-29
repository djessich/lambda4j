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
     * @param ret The return value for the constant
     * @return A {@code ObjBytePredicate} which always returns a given value.
     */
    static ObjBytePredicate constant(boolean ret) {
        return (t, value) -> ret;
    }

    /**
     * Returns a {@link ObjBytePredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@link Objects#equals(Object)} and {@code value == target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The first target value with which to compare for equality
     * @param targetValue The second target value with which to compare for equality
     * @return A {@code ObjBytePredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, byte)
     */
    static <T> ObjBytePredicate isEqual(T targetRef, byte targetValue) {
        return (t, value) -> ((t == null) ? targetRef == null : t.equals(targetRef)) && (value == targetValue);
    }

    /**
     * Returns a {@link ObjBytePredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The first target value with which to compare for equality
     * @param targetValue The second target value with which to compare for equality
     * @return A {@code ObjBytePredicate} that tests if the given arguments are not equal to the ones of this predicate.
     * @see #isEqual(Object, byte)
     */
    static <T> ObjBytePredicate isNotEqual(T targetRef, byte targetValue) {
        return (t, value) -> !(t == null ? targetRef == null : t.equals(targetRef)) && (value != targetValue);
    }

    /**
     * Returns a {@link ObjBytePredicate} the always returns {@code true}.
     *
     * @return A {@link ObjBytePredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static ObjBytePredicate alwaysTrue() {
        return (t, value) -> true;
    }

    /**
     * Returns a {@link ObjBytePredicate} the always returns {@code false}.
     *
     * @return A {@link ObjBytePredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static ObjBytePredicate alwaysFalse() {
        return (t, value) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean test(T t, byte value);
}
