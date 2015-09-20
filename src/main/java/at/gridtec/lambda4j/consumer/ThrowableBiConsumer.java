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

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * This functional interface implements a {@link BiConsumer} which is able to throw any {@link Exception}.
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
 *
 * @param <T> The type of the first argument for the operation
 * @param <U> The type of the second argument for the operation
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.Consumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiConsumer<T, U> extends BiConsumer<T, U> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableBiConsumer}. This is a convenience method
     * in case the given {@link ThrowableBiConsumer} is ambiguous for the compiler. This might happen for overloaded
     * methods accepting different functional interfaces. The given {@code ThrowableBiConsumer} is returned as-is.
     *
     * @param <T> The type of the first argument for the operation
     * @param <U> The type of the second argument for the operation
     * @param lambda The {@code ThrowableBiConsumer} which should be returned as-is.
     * @return The given {@code ThrowableBiConsumer} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiConsumer<T, U> wrap(final ThrowableBiConsumer<T, U> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableBiConsumer} from the given {@link BiConsumer}. This method is just convenience to
     * provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param <T> The type of the first argument for the operation
     * @param <U> The type of the second argument for the operation
     * @param lambda A {@code BiConsumer} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableBiConsumer} from the given {@code BiConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiConsumer<T, U> from(final BiConsumer<T, U> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::accept;
    }

    /**
     * Creates a {@link ThrowableBiConsumer} which uses the first parameter of this one as argument for the given {@link
     * ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiConsumer} which uses the first parameter of this one as argument for the
     * given {@code ThrowableConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiConsumer<T, U> onlyFirst(final ThrowableConsumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.accept(t);
    }

    /**
     * Creates a {@link ThrowableBiConsumer} which uses the second parameter of this one as argument for the given
     * {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the operation
     * @param <U> The type of the second argument to the operation
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiConsumer} which uses the second parameter of this one as argument for the
     * given {@code ThrowableConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiConsumer<T, U> onlySecond(final ThrowableConsumer<? super U> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.accept(u);
    }

    /**
     * The accept method for this {@link BiConsumer} which is able to throw any {@link Exception} type.
     *
     * @param t The first argument for the operation to be consumed
     * @param u The second argument for the operation to be consumed
     * @throws Exception Any exception from this operations action
     */
    void acceptThrows(T t, U u) throws Exception;

    /**
     * Overrides the {@link BiConsumer#accept(Object, Object)} method by using a redefinition as default method. It
     * calls the {@link #acceptThrows(Object, Object)} method of this interface and catches the thrown {@link
     * Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types
     * are sneakily thrown.
     *
     * @param t The first argument for the operation to be consumed
     * @param u The second argument for the operation to be consumed
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default void accept(T t, U u) {
        try {
            acceptThrows(t, u);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableBiConsumer} that applies this {@code ThrowableBiConsumer} to its input, and if
     * an error occurred, applies the given one. The exception from this {@code ThrowableBiConsumer} is ignored.
     *
     * @param other A {@code ThrowableBiConsumer} to be applied if this one fails
     * @return A composed {@code ThrowableBiConsumer} that applies this {@code ThrowableBiConsumer}, and if an error
     * occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableBiConsumer<T, U> orElse(final ThrowableBiConsumer<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u) -> {
            try {
                acceptThrows(t, u);
            } catch (Exception ignored) {
                other.acceptThrows(t, u);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableBiConsumer} that applies this {@code ThrowableBiConsumer} to its input, and if
     * an error occurred, throws the given {@link Exception}. The exception from this {@code ThrowableBiConsumer} is
     * added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableBiConsumer} that applies this {@code ThrowableBiConsumer}, and if an error
     * occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableBiConsumer<T, U> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (t, u) -> {
            try {
                acceptThrows(t, u);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link BiConsumer} that applies this {@link ThrowableBiConsumer} to its input, ignoring any
     * possible errors, unless it is an unchecked exception.
     *
     * @return A composed {@code BiConsumer} that applies this {@code ThrowableBiConsumer}, ignoring any possible
     * errors, unless it is an unchecked exception.
     */
    default BiConsumer<T, U> ignore() {
        return (t, u) -> {
            try {
                acceptThrows(t, u);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception ignored) {
                // Do nothing
            }
        };
    }

    /**
     * Returns a composed {@link BiConsumer} that applies this {@link ThrowableBiConsumer} to its input, ignoring any
     * possible errors.
     *
     * @return A composed {@code BiConsumer} that applies this {@code ThrowableBiConsumer}, ignoring any possible
     * errors.
     */
    default BiConsumer<T, U> ignoreAll() {
        return (t, u) -> {
            try {
                acceptThrows(t, u);
            } catch (Exception ignored) {
                // Do nothing
            }
        };
    }
}
