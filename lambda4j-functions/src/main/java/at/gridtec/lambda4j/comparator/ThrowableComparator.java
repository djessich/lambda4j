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
package at.gridtec.lambda4j.comparator;

import at.gridtec.lambda4j.throwables.ThrownByFunctionalInterfaceException;

import java.util.Comparator;

/**
 * Represents an operation that evaluates two arguments of same type for order and returns an {@code int}-valued result
 * which is able to throw any {@link Throwable}. The return value can be a negative integer, zero, or a positive integer
 * as the first argument is less than, equal to, or greater than the second.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #compareThrows(Object, Object)}.
 *
 * @param <T> The type of the compared values
 * @apiNote This is a throwable JRE lambda.
 * @see Comparator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableComparator<T> extends Comparator<T> {

    /**
     * Compares this comparators two arguments for order. Returns a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the second. Thereby any {@link Throwable} is able to be
     * thrown.
     *
     * @param o1 The first object to be compared
     * @param o2 The second object to be compared
     * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
     * than the second.
     * @throws Throwable Any throwable from this functions action
     */
    int compareThrows(T o1, T o2) throws Throwable;

    /**
     * Compares this comparators two arguments for order. Returns a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the second.
     *
     * @param o1 The first object to be compared
     * @param o2 The second object to be compared
     * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
     * than the second.
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #compareThrows(Object, Object)} method of this comparator and catches the
     * thrown {@link Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is
     * propagated as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default int compare(T o1, T o2) {
        try {
            return compareThrows(o1, o2);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable);
        }
    }
}
