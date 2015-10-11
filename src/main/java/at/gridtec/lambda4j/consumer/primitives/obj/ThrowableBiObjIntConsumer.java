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
package at.gridtec.lambda4j.consumer.primitives.obj;

import at.gridtec.lambda4j.consumer.TriConsumer;
import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * This functional interface implements a {@link BiObjIntConsumer} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #acceptThrows(Object, Object, int)} .
 *
 * @param <T> The type of the first argument to the operation
 * @param <U> The type of the second argument to the operation
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiObjIntConsumer<T, U> extends BiObjIntConsumer<T, U> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableBiObjIntConsumer}. This is a convenience
     * method in case the given {@link ThrowableBiObjIntConsumer} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableBiObjIntConsumer} is
     * returned as-is.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param lambda The {@code ThrowableBiObjIntConsumer} which should be returned as-is.
     * @return The given {@code ThrowableBiObjIntConsumer} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiObjIntConsumer<T, U> wrap(final ThrowableBiObjIntConsumer<T, U> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableBiObjIntConsumer} from the given {@link BiObjIntConsumer}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param lambda A {@code BiObjIntConsumer} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableBiObjIntConsumer} from the given {@code BiObjIntConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiObjIntConsumer<T, U> from(final BiObjIntConsumer<T, U> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::accept;
    }

    /**
     * Creates a {@link ThrowableBiObjIntConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link Consumer}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiObjIntConsumer} which uses the {@code first} parameter of this one as argument for
     * the given {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiObjIntConsumer<T, U> onlyFirst(final Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(t);
    }

    /**
     * Creates a {@link ThrowableBiObjIntConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link Consumer}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiObjIntConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@code Consumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiObjIntConsumer<T, U> onlySecond(final Consumer<? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(u);
    }

    /**
     * Creates a {@link ThrowableBiObjIntConsumer} which uses the {@code third} parameter of this one as argument for the given
     * {@link IntConsumer}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableBiObjIntConsumer} which uses the {@code third} parameter of this one as argument for
     * the given {@code IntConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiObjIntConsumer<T, U> onlyThird(final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(value);
    }

    /**
     * The accept method for this {@link BiObjIntConsumer} which is able to throw any {@link Exception} type.
     *
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @throws Exception Any exception from this operations action
     */
    void acceptThrows(T t, U u, int value) throws Exception;

    /**
     * Overrides the {@link BiObjIntConsumer#accept(Object, Object, int)} method by using a redefinition as default
     * method. It calls the {@link #acceptThrows(Object, Object, int)} method of this interface and catches the thrown
     * {@link Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception
     * types are sneakily thrown.
     *
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default void accept(T t, U u, int value) {
        try {
            acceptThrows(t, u, value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableBiObjIntConsumer} that applies this {@code ThrowableBiObjIntConsumer} to its
     * input, and if an error occurred, applies the given one. The exception from this {@code ThrowableBiObjIntConsumer}
     * is ignored.
     *
     * @param other A {@code ThrowableBiObjIntConsumer} to be applied if this one fails
     * @return A composed {@code ThrowableBiObjIntConsumer} that applies this {@code ThrowableBiObjIntConsumer}, and if
     * an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableBiObjIntConsumer<T, U> orElse(final ThrowableBiObjIntConsumer<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> {
            try {
                acceptThrows(t, u, value);
            } catch (Exception ignored) {
                other.acceptThrows(t, u, value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableBiObjIntConsumer} that applies this {@code ThrowableBiObjIntConsumer} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableBiObjIntConsumer} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableBiObjIntConsumer} that applies this {@code ThrowableBiObjIntConsumer}, and if
     * an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableBiObjIntConsumer<T, U> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (t, u, value) -> {
            try {
                acceptThrows(t, u, value);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link BiObjIntConsumer} that applies this {@link ThrowableBiObjIntConsumer} to its input,
     * ignoring any possible errors, unless it is an unchecked exception.
     *
     * @return A composed {@code BiObjIntConsumer} that applies this {@code ThrowableBiObjIntConsumer}, ignoring any
     * possible errors, unless it is an unchecked exception.
     */
    default BiObjIntConsumer<T, U> ignore() {
        return (t, u, value) -> {
            try {
                acceptThrows(t, u, value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception ignored) {
                // Do nothing
            }
        };
    }

    /**
     * Returns a composed {@link BiObjIntConsumer} that applies this {@link ThrowableBiObjIntConsumer} to its input,
     * ignoring any possible errors.
     *
     * @return A composed {@code BiObjIntConsumer} that applies this {@code ThrowableBiObjIntConsumer}, ignoring any
     * possible errors.
     */
    default BiObjIntConsumer<T, U> ignoreAll() {
        return (t, u, value) -> {
            try {
                acceptThrows(t, u, value);
            } catch (Exception ignored) {
                // Do nothing
            }
        };
    }
}
