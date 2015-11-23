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
import java.util.function.DoubleSupplier;

/**
 * This functional interface implements a {@link DoubleSupplier} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsDoubleThrows()}.
 *
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.Supplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableDoubleSupplier extends DoubleSupplier {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableDoubleSupplier}. This is a convenience
     * method in case the given {@link ThrowableDoubleSupplier} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableDoubleSupplier} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableDoubleSupplier} which should be returned as-is.
     * @return The given {@code ThrowableDoubleSupplier} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ThrowableDoubleSupplier wrap(@Nonnull final ThrowableDoubleSupplier lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableDoubleSupplier} from the given {@link DoubleSupplier}. This method is just convenience
     * to provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param lambda A {@code DoubleSupplier} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableDoubleSupplier} from the given {@code DoubleSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ThrowableDoubleSupplier from(@Nonnull final DoubleSupplier lambda) {
        Objects.requireNonNull(lambda);
        return lambda::getAsDouble;
    }

    /**
     * Creates a {@link ThrowableDoubleSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableDoubleSupplier} which always returns a given value.
     */
    static ThrowableDoubleSupplier constant(double ret) {
        return () -> ret;
    }

    /**
     * The get method for this {@link DoubleSupplier} which is able to throw any {@link Exception} type.
     *
     * @return The supplied value.
     * @throws Exception Any exception from this functions action
     */
    double getAsDoubleThrows() throws Exception;

    /**
     * Overrides the {@link DoubleSupplier#getAsDouble()} method by using a redefinition as default method. It calls the
     * {@link #getAsDoubleThrows()} method of this interface and catches the thrown {@link Exception}s from it. If it is
     * of type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @return The supplied value.
     * @see at.gridtec.lambda4j.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default double getAsDouble() {
        try {
            return getAsDoubleThrows();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableDoubleSupplier} that applies this {@code ThrowableDoubleSupplier} to its
     * input, and if an error occurred, applies the given one. The exception from this {@code ThrowableDoubleSupplier}
     * is ignored.
     *
     * @param other A {@code ThrowableDoubleSupplier} to be applied if this one fails
     * @return A composed {@code ThrowableDoubleSupplier} that applies this {@code ThrowableDoubleSupplier}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    default ThrowableDoubleSupplier orElse(@Nonnull final ThrowableDoubleSupplier other) {
        Objects.requireNonNull(other);
        return () -> {
            try {
                return getAsDoubleThrows();
            } catch (Exception ignored) {
                return other.getAsDoubleThrows();
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableDoubleSupplier} that applies this {@code ThrowableDoubleSupplier} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableDoubleSupplier} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableDoubleSupplier} that applies this {@code ThrowableDoubleSupplier}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    default <X extends Exception> ThrowableDoubleSupplier orThrow(@Nonnull Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return () -> {
            try {
                return getAsDoubleThrows();
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link DoubleSupplier} that applies this {@link ThrowableDoubleSupplier} to its input, and if
     * an error occurred, applies the given {@code DoubleSupplier} representing a fallback. The exception from this
     * {@code ThrowableDoubleSupplier} is ignored.
     *
     * @param fallback A {@code DoubleSupplier} to be applied if this one fails
     * @return A composed {@code DoubleSupplier} that applies this {@code ThrowableDoubleSupplier}, and if an error
     * occurred, applies the given {@code DoubleSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    default DoubleSupplier fallbackTo(@Nonnull final DoubleSupplier fallback) {
        Objects.requireNonNull(fallback);
        return () -> {
            try {
                return getAsDoubleThrows();
            } catch (Exception ignored) {
                return fallback.getAsDouble();
            }
        };
    }

    /**
     * Returns a composed {@link DoubleSupplier} that applies this {@link ThrowableDoubleSupplier} to its input, and if
     * an error occurred, returns the given value. The exception from this {@code ThrowableDoubleSupplier} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableDoubleSupplier} fails
     * @return A composed {@code DoubleSupplier} that applies this {@code ThrowableDoubleSupplier}, and if an error
     * occurred, returns the given value.
     */
    @Nonnull
    default DoubleSupplier orReturn(double value) {
        return () -> {
            try {
                return getAsDoubleThrows();
            } catch (Exception ignored) {
                return value;
            }
        };
    }
}
