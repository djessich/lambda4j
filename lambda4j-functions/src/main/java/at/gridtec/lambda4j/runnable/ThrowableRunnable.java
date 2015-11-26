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
package at.gridtec.lambda4j.runnable;

import at.gridtec.lambda4j.throwables.ThrownByFunctionalInterfaceException;

/**
 * Represents a function that accepts no argument and produces no result which is able to throw any {@link Throwable}.
 * As it derives from {@link Runnable} it should be used to implement code which is intended to be executed by a
 * thread.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #runThrows()}.
 *
 * @apiNote This is a throwable JRE lambda.
 * @see Runnable
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableRunnable extends Runnable {

    /**
     * Applies this {@link Runnable}. Thereby any {@link Throwable} is able to be thrown.
     *
     * @throws Throwable Any throwable from this functions action
     */
    void runThrows() throws Throwable;

    /**
     * Applies this {@link Runnable}.
     *
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #runThrows()} method of this runnable and catches the thrown {@link
     * Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is propagated
     * as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default void run() {
        try {
            runThrows();
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable);
        }
    }
}