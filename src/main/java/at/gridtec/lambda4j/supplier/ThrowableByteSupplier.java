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

import at.gridtec.lambda4j.util.ThrowableUtils;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * This functional interface implements a {@link ByteSupplier} which is able to throw any {@link Exception}.
 * <p>
 * The thrown {@link Exception} is sneakily thrown unless its a {@link RuntimeException}. This means that there is no
 * need to catch the thrown exception, nor to declare that you throw it using the <em>throws</em> keyword. The exception
 * is still thrown, but the Java compiler stops warning about it.
 * <p>
 * However, when using this throwing lambda, be aware of the following consequences: <ol> <li>If the calling code is to
 * handle a thrown {@code Exception}, it MUST be declared in the methods <em>throws</em> clause which uses this lambda.
 * The compiler will not force you to add it.</li> <li>If the calling code already handles a thrown {@code Exception},
 * it needs to be declared in the methods <em>throws</em> clause which uses this lambda. If not the compiler prints an
 * error that the corresponding {@code try} block never throws the specific exception.</li> <li>In any case, there is no
 * way of explicitly catching the thrown {@code Exception} in the method which uses this lambda. If you try, the
 * compiler prints an error that the corresponding {@code try} block never throws the specific exception.</li> </ol>
 * <p>
 * When the calling code never throws the specific exception that it declares, you should omit it. For example: {@code
 * new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but UTF-8 is guaranteed by the Java specification
 * to be always present. The exception should therefore be omitted.
 * <p>
 * Moreover, if no checked exception should be used at all or its use is inappropriate for any reasons, omit the
 * declaration in the <em>throws</em> clause. The checked exception will behave just like a normal <b>unchecked</b>
 * exception due to sneaky throwing.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsByteThrows()}.
 *
 * @see java.util.function.Supplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableByteSupplier extends ByteSupplier {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableByteSupplier}. This is a convenience
     * method in case the given {@link ThrowableByteSupplier} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableByteSupplier} is returned
     * as-is.
     *
     * @param lambda The {@code ThrowableByteSupplier} which should be returned as-is.
     * @return The given {@code ThrowableByteSupplier} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ThrowableByteSupplier wrap(@Nonnull final ThrowableByteSupplier lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableByteSupplier} from the given {@link ByteSupplier}. This method is just convenience to
     * provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param lambda A {@code ByteSupplier} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableByteSupplier} from the given {@code ByteSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ThrowableByteSupplier from(@Nonnull final ByteSupplier lambda) {
        Objects.requireNonNull(lambda);
        return lambda::getAsByte;
    }

    /**
     * Creates a {@link ThrowableByteSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableByteSupplier} which always returns a given value.
     */
    static ThrowableByteSupplier constant(byte ret) {
        return () -> ret;
    }

    /**
     * The get method for this {@link ByteSupplier} which is able to throw any {@link Exception} type.
     *
     * @return The supplied value.
     * @throws Exception Any exception from this functions action
     */
    byte getAsByteThrows() throws Exception;

    /**
     * Overrides the {@link ByteSupplier#getAsByte()} method by using a redefinition as default method. It calls the
     * {@link #getAsByteThrows()} method of this interface and catches the thrown {@link Exception}s from it. If it is
     * of type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @return The supplied value.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default byte getAsByte() {
        try {
            return getAsByteThrows();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableByteSupplier} that applies this {@code ThrowableByteSupplier} to its input,
     * and if an error occurred, applies the given one. The exception from this {@code ThrowableByteSupplier} is
     * ignored.
     *
     * @param other A {@code ThrowableByteSupplier} to be applied if this one fails
     * @return A composed {@code ThrowableByteSupplier} that applies this {@code ThrowableByteSupplier}, and if an error
     * occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableByteSupplier orElse(@Nonnull final ThrowableByteSupplier other) {
        Objects.requireNonNull(other);
        return () -> {
            try {
                return getAsByteThrows();
            } catch (Exception ignored) {
                return other.getAsByteThrows();
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableByteSupplier} that applies this {@code ThrowableByteSupplier} to its input,
     * and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableByteSupplier} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableByteSupplier} that applies this {@code ThrowableByteSupplier}, and if an error
     * occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    default <X extends Exception> ThrowableByteSupplier orThrow(@Nonnull Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return () -> {
            try {
                return getAsByteThrows();
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ByteSupplier} that applies this {@link ThrowableByteSupplier} to its input, and if an
     * error occurred, applies the given {@code ByteSupplier} representing a fallback. The exception from this {@code
     * ThrowableByteSupplier} is ignored.
     *
     * @param fallback A {@code ByteSupplier} to be applied if this one fails
     * @return A composed {@code ByteSupplier} that applies this {@code ThrowableByteSupplier}, and if an error
     * occurred, applies the given {@code ByteSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    default ByteSupplier fallbackTo(@Nonnull final ByteSupplier fallback) {
        Objects.requireNonNull(fallback);
        return () -> {
            try {
                return getAsByteThrows();
            } catch (Exception ignored) {
                return fallback.getAsByte();
            }
        };
    }

    /**
     * Returns a composed {@link ByteSupplier} that applies this {@link ThrowableByteSupplier} to its input, and if an
     * error occurred, returns the given value. The exception from this {@code ThrowableByteSupplier} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableByteSupplier} fails
     * @return A composed {@code ByteSupplier} that applies this {@code ThrowableByteSupplier}, and if an error
     * occurred, returns the given value.
     */
    @Nonnull
    default ByteSupplier orReturn(byte value) {
        return () -> {
            try {
                return getAsByteThrows();
            } catch (Exception ignored) {
                return value;
            }
        };
    }
}
