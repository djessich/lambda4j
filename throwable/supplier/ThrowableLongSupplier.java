/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */
package at.gridtec.internals.lang.function.throwable.supplier;

import at.gridtec.internals.lang.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;

/**
 * This functional interface implements a {@link LongSupplier} which is able to throw any {@link Exception}.
 * <p>
 * The thrown {@link Exception} is sneakily thrown unless its a {@link RuntimeException}. This means that there is no
 * need to catch the thrown exception, nor to declare that you throw it using the <em>throws</em> keyword. The
 * exception is still thrown, but the Java compiler stops warning about it.
 * <p>
 * However, when using this throwing lambda, be aware of the following consequences:
 * <ol>
 * <li>If the calling code is to handle a thrown {@code Exception}, it MUST be declared in the methods
 * <em>throws</em> clause which uses this lambda. The compiler will not force you to add it.</li>
 * <li>If the calling code already handles a thrown {@code Exception}, it needs to be declared in the methods
 * <em>throws</em> clause which uses this lambda. If not the compiler prints an error that the corresponding {@code
 * try} block never throws the specific exception.</li>
 * <li>In any case, there is no way of explicitly catching the thrown {@code Exception} in the method which uses this
 * lambda. If you try, the compiler prints an error that the corresponding {@code try} block never throws the specific
 * exception.</li>
 * </ol>
 * <p>
 * When the calling code never throws the specific exception that it declares, you should omit it. For example: {@code
 * new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but UTF-8 is guaranteed by the Java
 * specification to be always present. The exception should therefore be omitted.
 * <p>
 * Moreover, if no checked exception should be used at all or its use is inappropriate for any reasons, omit the
 * declaration in the <em>throws</em> clause. The checked exception will behave just like a normal <b>unchecked</b>
 * exception due to sneaky throwing.
 *
 * @see java.util.function.Supplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableLongSupplier extends LongSupplier {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableLongSupplier}. This is a convenience
     * method in case the given {@link ThrowableLongSupplier} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableLongSupplier} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableLongSupplier} which should be returned as-is.
     * @return The given {@code ThrowableLongSupplier} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongSupplier wrap(final ThrowableLongSupplier lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableLongSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableLongSupplier} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongSupplier of(long ret) {
        Objects.requireNonNull(ret);
        return () -> ret;
    }

    /**
     * The get method for this {@link LongSupplier} which is able to throw any {@link Exception} type.
     *
     * @return The supplied value.
     * @throws Exception Any exception from this functions action
     */
    long getAsLongThrows() throws Exception;

    /**
     * Overrides the {@link LongSupplier#getAsLong()} method by using a redefinition as default method. It calls the
     * {@link #getAsLongThrows()} method of this interface and catches the thrown {@link Exception}s from it. If it is
     * of type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @return The supplied value.
     * @see at.gridtec.internals.lang.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default long getAsLong() {
        try {
            return getAsLongThrows();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableLongSupplier} that applies this {@code ThrowableLongSupplier} to its input,
     * and if an error occurred, applies the given one. The exception from this {@code ThrowableLongSupplier} is
     * ignored.
     *
     * @param other A {@code ThrowableLongSupplier} to be applied if this one fails
     * @return A composed {@code ThrowableLongSupplier} that applies this {@code ThrowableLongSupplier}, and if an error
     * occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableLongSupplier orElse(final ThrowableLongSupplier other) {
        Objects.requireNonNull(other);
        return () -> {
            try {
                return getAsLongThrows();
            } catch (Exception ignored) {
                return other.getAsLongThrows();
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableLongSupplier} that applies this {@code ThrowableLongSupplier} to its input,
     * and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableLongSupplier} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableLongSupplier} that applies this {@code ThrowableLongSupplier}, and if an error
     * occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableLongSupplier orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return () -> {
            try {
                return getAsLongThrows();
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link LongSupplier} that applies this {@link ThrowableLongSupplier} to its input, and if an
     * error occurred, applies the given {@code LongSupplier} representing a fallback. The exception from this {@code
     * ThrowableLongSupplier} is ignored.
     *
     * @param fallback A {@code LongSupplier} to be applied if this one fails
     * @return A composed {@code LongSupplier} that applies this {@code ThrowableLongSupplier}, and if an error
     * occurred, applies the given {@code LongSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default LongSupplier fallbackTo(final LongSupplier fallback) {
        Objects.requireNonNull(fallback);
        return () -> {
            try {
                return getAsLongThrows();
            } catch (Exception ignored) {
                return fallback.getAsLong();
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableLongSupplier} that applies this {@code ThrowableLongSupplier} to its input,
     * additionally performing the provided action to the resulting value. This method exists mainly to support
     * debugging.
     *
     * @param action A {@link LongConsumer} to be applied additionally to this {@code ThrowableLongSupplier}
     * @return A composed {@code ThrowableLongSupplier} that applies this {@code ThrowableLongSupplier}, additionally
     * performing the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableLongSupplier peek(final LongConsumer action) {
        Objects.requireNonNull(action);
        return () -> {
            final long ret = getAsLong();
            action.accept(ret);
            return ret;
        };
    }

    /**
     * Returns a composed {@link LongSupplier} that applies this {@link ThrowableLongSupplier} to its input, and if an
     * error occurred, returns the given value. The exception from this {@code ThrowableLongSupplier} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableLongSupplier} fails
     * @return A composed {@code LongSupplier} that applies this {@code ThrowableLongSupplier}, and if an error
     * occurred, returns the given value.
     */
    default LongSupplier orReturn(long value) {
        return () -> {
            try {
                return getAsLongThrows();
            } catch (Exception ignored) {
                return value;
            }
        };
    }
}
