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
import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two input arguments and returns no result which is able to throw any {@link
 * Throwable}. This is the two-arity specialization of {@link ThrowableConsumer}. Unlike most other functional
 * interfaces, {@code ThrowableBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, Object)}.
 *
 * @param <T> The type of the first argument to the operation to be consumed
 * @param <U> The type of the second argument to the operation to be consumed
 * @apiNote This is a throwable JRE lambda.
 * @see BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiConsumer<T, U> extends BiConsumer<T, U> {

    /**
     * Calls the given {@link ThrowableBiConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @throws Throwable Any throwable from the given consumers action
     */
    static <T, U> void call(@Nonnull final ThrowableBiConsumer<? super T, ? super U> consumer, T t, U u) throws
            Throwable {
        Objects.requireNonNull(consumer);
        consumer.acceptThrows(t, u);
    }

    /**
     * Creates a {@link ThrowableBiConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiConsumer} which uses the {@code first} parameter of this one as argument for
     * the given {@code ThrowableConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ThrowableBiConsumer<T, U> onlyFirst(@Nonnull final ThrowableConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.acceptThrows(t);
    }

    /**
     * Creates a {@link ThrowableBiConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@code ThrowableConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> ThrowableBiConsumer<T, U> onlySecond(@Nonnull final ThrowableConsumer<? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.acceptThrows(u);
    }

    /**
     * Performs this operation on the given arguments. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @throws Throwable Any throwable from this consumers action
     */
    void acceptThrows(T t, U u) throws Throwable;

    /**
     * Performs this operation on the given arguments.
     *
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #acceptThrows(Object, Object)} method of this consumer and catches the thrown
     * {@link Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is
     * propagated as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default void accept(T t, U u) {
        try {
            acceptThrows(t, u);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByFunctionalInterfaceException(throwable);
        }
    }
}
