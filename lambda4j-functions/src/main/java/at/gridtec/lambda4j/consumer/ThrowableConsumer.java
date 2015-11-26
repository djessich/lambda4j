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
package at.gridtec.lambda4j.consumer;

import at.gridtec.lambda4j.throwables.ThrownByFunctionalInterfaceException;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts a single input argument and returns no result which is able to throw any {@link
 * Throwable}. Unlike most other functional interfaces, {@code ThrowableBiConsumer} is expected to operate via
 * side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object)}.
 *
 * @param <T> The type of the argument to the operation to be consumed
 * @apiNote This is a throwable JRE lambda.
 * @see Consumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableConsumer<T> extends Consumer<T> {

    /**
     * Calls the given {@link ThrowableConsumer} with the given argument and returns its result.
     *
     * @param <T> The type of the argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @throws Throwable Any throwable from the given consumers action
     */
    static <T> void call(@Nonnull final ThrowableConsumer<? super T> consumer, T t) throws Throwable {
        Objects.requireNonNull(consumer);
        consumer.acceptThrows(t);
    }

    /**
     * Performs this operation on the given argument. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param t The argument to the operation to be consumed
     * @throws Throwable Any throwable from this consumers action
     */
    void acceptThrows(T t) throws Throwable;

    /**
     * Performs this operation on the given argument.
     *
     * @param t The argument to the operation to be consumed
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #acceptThrows(Object)} method of this consumer and catches the thrown {@link
     * Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is propagated
     * as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default void accept(T t) {
        try {
            acceptThrows(t);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable);
        }
    }
}
