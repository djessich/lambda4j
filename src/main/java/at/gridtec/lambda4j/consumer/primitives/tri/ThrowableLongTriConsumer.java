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
package at.gridtec.lambda4j.consumer.primitives.tri;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.LongConsumer;

/**
 * This functional interface implements a {@link LongTriConsumer} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #acceptThrows(long, long, long)}.
 *
 * @see java.util.function.Consumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableLongTriConsumer extends LongTriConsumer {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableLongTriConsumer}. This is a convenience
     * method in case the given {@link ThrowableLongTriConsumer} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableLongTriConsumer} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableLongTriConsumer} which should be returned as-is.
     * @return The given {@code ThrowableLongTriConsumer} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongTriConsumer wrap(final ThrowableLongTriConsumer lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableLongTriConsumer} from the given {@link LongTriConsumer}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code LongTriConsumer} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableLongTriConsumer} from the given {@code LongTriConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongTriConsumer from(final LongTriConsumer lambda) {
        Objects.requireNonNull(lambda);
        return lambda::accept;
    }

    /**
     * Creates a {@link LongTriConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code LongTriConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static LongTriConsumer onlyFirst(final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link LongTriConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code LongTriConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@code LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static LongTriConsumer onlySecond(final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value2);
    }

    /**
     * Creates a {@link LongTriConsumer} which uses the {@code third} parameter as argument for the given {@link
     * LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code LongTriConsumer} which uses the {@code third} parameter as argument for the given {@code
     * LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static LongTriConsumer onlyThird(final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value3);
    }

    /**
     * The accept method for this {@link LongTriConsumer} which is able to throw any {@link Exception} type.
     *
     * @param value1 The first argument for the operation to be consumed
     * @param value2 The second argument for the operation to be consumed
     * @param value3 The third argument for the operation to be consumed
     * @throws Exception Any exception from this operations action
     */
    void acceptThrows(long value1, long value2, long value3) throws Exception;

    /**
     * Overrides the {@link LongTriConsumer#accept(long, long, long)} method by using a redefinition as default method.
     * It calls the {@link #acceptThrows(long, long, long)} method of this interface and catches the thrown {@link
     * Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types
     * are sneakily thrown.
     *
     * @param value1 The first argument for the operation to be consumed
     * @param value2 The second argument for the operation to be consumed
     * @param value3 The third argument for the operation to be consumed
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default void accept(long value1, long value2, long value3) {
        try {
            acceptThrows(value1, value2, value3);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableLongTriConsumer} that applies this {@code ThrowableLongTriConsumer} to its
     * input, and if an error occurred, applies the given one. The exception from this {@code ThrowableLongTriConsumer}
     * is ignored.
     *
     * @param other A {@code ThrowableLongTriConsumer} to be applied if this one fails
     * @return A composed {@code ThrowableLongTriConsumer} that applies this {@code ThrowableLongTriConsumer}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableLongTriConsumer orElse(final ThrowableLongTriConsumer other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> {
            try {
                acceptThrows(value1, value2, value3);
            } catch (Exception ignored) {
                other.acceptThrows(value1, value2, value3);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableLongTriConsumer} that applies this {@code ThrowableLongTriConsumer} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableLongTriConsumer} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableLongTriConsumer} that applies this {@code ThrowableLongTriConsumer}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableLongTriConsumer orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (value1, value2, value3) -> {
            try {
                acceptThrows(value1, value2, value3);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link LongTriConsumer} that applies this {@link ThrowableLongTriConsumer} to its input,
     * ignoring any possible errors, unless it is an unchecked exception.
     *
     * @return A composed {@code LongTriConsumer} that applies this {@code ThrowableLongTriConsumer}, ignoring any
     * possible errors, unless it is an unchecked exception.
     */
    default LongTriConsumer ignore() {
        return (value1, value2, value3) -> {
            try {
                acceptThrows(value1, value2, value3);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception ignored) {
                // Do nothing
            }
        };
    }

    /**
     * Returns a composed {@link LongTriConsumer} that applies this {@link ThrowableLongTriConsumer} to its input,
     * ignoring any possible errors.
     *
     * @return A composed {@code LongTriConsumer} that applies this {@code ThrowableLongTriConsumer}, ignoring any
     * possible errors.
     */
    default LongTriConsumer ignoreAll() {
        return (value1, value2, value3) -> {
            try {
                acceptThrows(value1, value2, value3);
            } catch (Exception ignored) {
                // Do nothing
            }
        };
    }
}
