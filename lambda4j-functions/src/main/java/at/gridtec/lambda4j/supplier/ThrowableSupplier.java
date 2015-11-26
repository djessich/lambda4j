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
package at.gridtec.lambda4j.supplier;

import at.gridtec.lambda4j.throwables.ThrownByFunctionalInterfaceException;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Represents a supplier of results which is able to throw any {@link Throwable}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getThrows()}.
 *
 * @param <T> The type of return value from the supplier
 * @apiNote This is a throwable JRE lambda.
 * @see Supplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableSupplier<T> extends Supplier<T> {

    /**
     * Calls the given {@link ThrowableSupplier} and returns its result.
     *
     * @param <T> The type of return value from the Supplier
     * @param supplier The supplier to be called
     * @return The result of the given {@code ThrowableSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     * @throws Throwable Any throwable from this suppliers action
     */
    static <T> T call(@Nonnull final ThrowableSupplier<? extends T> supplier) throws Throwable {
        Objects.requireNonNull(supplier);
        return supplier.getThrows();
    }

    /**
     * Creates a {@link ThrowableSupplier} which always returns a given value.
     *
     * @param <T> The type of return value from the Supplier
     * @param t The return value for the constant
     * @return A {@code ThrowableSupplier} which always returns a given value.
     */
    @Nonnull
    static <T> ThrowableSupplier<T> constant(T t) {
        return () -> t;
    }

    /**
     * Gets the supplied result from this supplier. Thereby any {@link Throwable} type is able to be thrown.
     *
     * @return The supplied result.
     * @throws Throwable Any throwable from this suppliers action
     */
    T getThrows() throws Throwable;

    /**
     * Gets the supplied result from this supplier.
     *
     * @return The supplied result.
     * @apiNote This method is redefined as a default method for compatibility reasons, as some types may only call this
     * method.
     * @implSpec This method calls {@link #getThrows()} method of this supplier and catches the thrown {@link
     * Throwable}s from it. If it is of type {@link Error} or {@link RuntimeException}, the throwable is propagated
     * as-is. Other throwable types are nested in {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default T get() {
        try {
            return getThrows();
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable t) {
            throw new ThrownByFunctionalInterfaceException(t);
        }
    }
}