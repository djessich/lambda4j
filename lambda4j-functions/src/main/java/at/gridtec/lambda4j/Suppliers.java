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
package at.gridtec.lambda4j;

import at.gridtec.lambda4j.supplier.ByteSupplier;
import at.gridtec.lambda4j.supplier.CharSupplier;
import at.gridtec.lambda4j.supplier.FloatSupplier;
import at.gridtec.lambda4j.supplier.ShortSupplier;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

/**
 * This class provides utility methods, which pertain to all {@link FunctionalInterface}s representing a supplier.
 */
public class Suppliers {

    /**
     * Calls the given {@link Supplier} and returns its result.
     *
     * @param <T> The type of return value from the function
     * @param supplier The supplier to be called
     * @return The result of the given {@code Supplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    public static <T> T call(@Nonnull final Supplier<? extends T> supplier) {
        Objects.requireNonNull(supplier);
        return supplier.get();
    }

    /**
     * Calls the given {@link BooleanSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code BooleanSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    public static boolean call(@Nonnull final BooleanSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsBoolean();
    }

    /**
     * Calls the given {@link ByteSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code ByteSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static byte call(@Nonnull final ByteSupplier supplier) {
        return ByteSupplier.call(supplier);
    }

    /**
     * Calls the given {@link CharSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code CharSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static char call(@Nonnull final CharSupplier supplier) {
        return CharSupplier.call(supplier);
    }

    /**
     * Calls the given {@link CharSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code CharSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    public static double call(@Nonnull final DoubleSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsDouble();
    }

    /**
     * Calls the given {@link FloatSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code FloatSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static float call(@Nonnull final FloatSupplier supplier) {
        return FloatSupplier.call(supplier);
    }

    /**
     * Calls the given {@link FloatSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code FloatSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    public static int call(@Nonnull final IntSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsInt();
    }

    /**
     * Calls the given {@link FloatSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code FloatSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    public static long call(@Nonnull final LongSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsLong();
    }

    /**
     * Calls the given {@link ShortSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code ShortSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static short call(@Nonnull final ShortSupplier supplier) {
        return ShortSupplier.call(supplier);
    }

    /**
     * Creates a {@link BooleanSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanSupplier} which always returns a given value.
     * @apiNote This function mainly exists as a convenience helper. Each supplier of this library provides an identical
     * public static {@code constant()} method to this one, depending on its type.
     */
    @Nonnull
    public static BooleanSupplier constant(boolean ret) {
        return () -> ret;
    }

    /**
     * Creates a {@link ShortSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortSupplier} which always returns a given value.
     * @apiNote This function mainly exists as a convenience helper. Each supplier of this library provides an identical
     * public static {@code constant()} method to this one, depending on its type.
     */
    @Nonnull
    public static ByteSupplier constant(byte ret) {
        return ByteSupplier.constant(ret);
    }

    /**
     * Creates a {@link CharSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharSupplier} which always returns a given value.
     * @apiNote This function mainly exists as a convenience helper. Each supplier of this library provides an identical
     * public static {@code constant()} method to this one, depending on its type.
     */
    @Nonnull
    public static CharSupplier constant(char ret) {
        return CharSupplier.constant(ret);
    }

    /**
     * Creates a {@link DoubleSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code DoubleSupplier} which always returns a given value.
     * @apiNote This function mainly exists as a convenience helper. Each supplier of this library provides an identical
     * public static {@code constant()} method to this one, depending on its type.
     */
    @Nonnull
    public static DoubleSupplier constant(double ret) {
        return () -> ret;
    }

    /**
     * Creates a {@link FloatSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatSupplier} which always returns a given value.
     * @apiNote This function mainly exists as a convenience helper. Each supplier of this library provides an identical
     * public static {@code constant()} method to this one, depending on its type.
     */
    @Nonnull
    public static FloatSupplier constant(float ret) {
        return FloatSupplier.constant(ret);
    }

    /**
     * Creates a {@link ByteSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteSupplier} which always returns a given value.
     * @apiNote This function mainly exists as a convenience helper. Each supplier of this library provides an identical
     * public static {@code constant()} method to this one, depending on its type.
     */
    @Nonnull
    public static IntSupplier constant(int ret) {
        return () -> ret;
    }

    /**
     * Creates a {@link ByteSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteSupplier} which always returns a given value.
     * @apiNote This function mainly exists as a convenience helper. Each supplier of this library provides an identical
     * public static {@code constant()} method to this one, depending on its type.
     */
    @Nonnull
    public static LongSupplier constant(long ret) {
        return () -> ret;
    }

    /**
     * Creates a {@link ShortSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortSupplier} which always returns a given value.
     * @apiNote This function mainly exists as a convenience helper. Each supplier of this library provides an identical
     * public static {@code constant()} method to this one, depending on its type.
     */
    @Nonnull
    public static ShortSupplier constant(short ret) {
        return ShortSupplier.constant(ret);
    }

    /**
     * Returns a {@link Supplier} whose {@code get()} method synchronizes on {@code delegate} before calling it, making
     * it thread-safe.
     *
     * @param <T> The type of return value from the Supplier
     * @param delegate The {@code Supplier} that will be synchronized before calling it
     * @return A {@code Supplier} whose {@code get()} method synchronizes on {@code delegate} before calling it, making
     * it thread-safe.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static <T> Supplier<T> synchronizedSupplier(@Nonnull final Supplier<? extends T> delegate) {
        Objects.requireNonNull(delegate);
        return new ThreadSafeSupplier<>(Objects.requireNonNull(delegate));
    }

    /**
     * A thread-safe implementation of {@link Supplier}.
     *
     * @param <T> The type of return value from the Supplier
     */
    private static class ThreadSafeSupplier<T> implements Supplier<T> {
        final Supplier<? extends T> delegate;

        ThreadSafeSupplier(final Supplier<? extends T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public T get() {
            synchronized (delegate) {
                return delegate.get();
            }
        }
    }
}
