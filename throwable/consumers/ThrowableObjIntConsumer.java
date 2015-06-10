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
package at.gridtec.internals.lang.function.throwable.consumers;

import at.gridtec.internals.lang.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.ObjIntConsumer;

/**
 * This functional interface implements a {@link ObjIntConsumer} which is able to throw any {@link Exception}.
 * <p>
 * The thrown {@link Exception} is sneakily thrown unless its a {@link RuntimeException}. This means that there is no
 * need to catch the thrown exception, nor to declare that you throw it using the throws keyword. The exception is
 * still
 * thrown, but the Java compiler stops warning about it.
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
 * @param <T> The type of argument for the function
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface ThrowableObjIntConsumer<T> extends ObjIntConsumer<T> {

    /**
     * The accept method for this {@link ObjIntConsumer} which is able to throw any {@link Exception} type.
     *
     * @param t The first argument for the function to be consumed
     * @param value The second argument for the function to be consumed
     * @throws Exception Any exception from this functions action
     */
    void acceptThrows(T t, int value) throws Exception;

    /**
     * Overrides the {@link ObjIntConsumer#accept(Object, int)} method by using a redefinition as default method. It
     * calls the {@link #acceptThrows(Object, int)} method of this interface and catches the thrown {@link Exception}s
     * from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types are
     * sneakily thrown.
     *
     * @param t The first argument for the function to be consumed
     * @param value The second argument for the function to be consumed
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default void accept(T t, int value) {
        try {
            acceptThrows(t, value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableObjIntConsumer} that applies this {@code ThrowableObjIntConsumer} to its
     * input, and if an error occurred, applies the given one. The exception from this {@code ThrowableObjIntConsumer}
     * is ignored, unless it is an unchecked exception.
     *
     * @param other A {@code ThrowableObjIntConsumer} to be applied if this one fails
     * @return A composed {@code ThrowableObjIntConsumer} that applies this {@code ThrowableObjIntConsumer}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableObjIntConsumer<T> orElse(final ThrowableObjIntConsumer<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> {
            try {
                acceptThrows(t, value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception ignored) {
                other.acceptThrows(t, value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableObjIntConsumer} that applies this {@code ThrowableObjIntConsumer} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableObjIntConsumer} is added as suppressed to the given one, unless it is an unchecked exception.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableObjIntConsumer} that applies this {@code ThrowableObjIntConsumer}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableObjIntConsumer<T> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (t, value) -> {
            try {
                acceptThrows(t, value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableObjIntConsumer} that applies this {@code ThrowableObjIntConsumer} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableObjIntConsumer} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occrured
     * @return A composed {@code ThrowableObjIntConsumer} that applies this {@code ThrowableObjIntConsumer}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableObjIntConsumer<T> orThrowAlways(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (t, value) -> {
            try {
                acceptThrows(t, value);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ObjIntConsumer} that applies this {@link ThrowableObjIntConsumer} to its input,
     * ignoring any possible errors, unless it is an unchecked exception.
     *
     * @return A composed {@code ObjIntConsumer} that applies this {@code ThrowableObjIntConsumer}, ignoring any
     * possible errors, unless it is an unchecked exception.
     */
    default ObjIntConsumer<T> ignore() {
        return (t, value) -> {
            try {
                acceptThrows(t, value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception ignored) {
                // Do nothing
            }
        };
    }

    /**
     * Returns a composed {@link ObjIntConsumer} that applies this {@link ThrowableObjIntConsumer} to its input,
     * ignoring any possible errors.
     *
     * @return A composed {@code ObjIntConsumer} that applies this {@code ThrowableObjIntConsumer}, ignoring any
     * possible errors.
     */
    default ObjIntConsumer<T> ignoreAll() {
        return (t, value) -> {
            try {
                acceptThrows(t, value);
            } catch (Exception ignored) {
                // Do nothing
            }
        };
    }
}
