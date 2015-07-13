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
package at.gridtec.lambda4j.throwable.runnable;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;

/**
 * This functional interface implements a {@link Runnable} which is able to throw any {@link Exception}.
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
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #runThrows()}.
 *
 * @apiNote This is a throwable JRE lambda
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableRunnable extends Runnable {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableRunnable}. This is a convenience method
     * in case the given {@link ThrowableRunnable} is ambiguous for the compiler. This might happen for overloaded
     * methods accepting different functional interfaces. The given {@code ThrowableRunnable} is returned as-is.
     *
     * @param lambda The {@code ThrowableRunnable} which should be returned as-is.
     * @return The given {@code ThrowableRunnable} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableRunnable wrap(final ThrowableRunnable lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableRunnable} from the given {@link Runnable}. This method is just convenience to provide
     * a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param lambda A {@code Runnable} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableRunnable} from the given {@code Runnable}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableRunnable from(final Runnable lambda) {
        Objects.requireNonNull(lambda);
        return lambda::run;
    }

    /**
     * The run method for this {@link Runnable} which is able to throw any {@link Exception} type.
     *
     * @throws Exception Any exception from this functions action
     */
    void runThrows() throws Exception;

    /**
     * Overrides the {@link Runnable#run()} method by using a redefinition as default method. It calls the {@link
     * #runThrows()} method of this interface and catches the thrown {@link Exception}s from it. If it is of type
     * {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @see at.gridtec.lambda4j.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default void run() {
        try {
            runThrows();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableRunnable} that executes this {@code ThrowableRunnable}, and if an error
     * occurred, executes the given one. The exception from this {@code ThrowableRunnable} is ignored.
     *
     * @param other A {@code ThrowableRunnable} to be executed if this one fails
     * @return A composed {@code ThrowableRunnable} that executes this {@code ThrowableRunnable}, and if an error
     * occurred, executes the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableRunnable orElse(final ThrowableRunnable other) {
        Objects.requireNonNull(other);
        return () -> {
            try {
                runThrows();
            } catch (Exception ignored) {
                other.runThrows();
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableRunnable} that applies this {@code ThrowableRunnable} to its input, and if an
     * error occurred, throws the given {@link Exception}. The exception from this {@code ThrowableRunnable} is added
     * as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableRunnable} that applies this {@code ThrowableRunnable}, and if an error
     * occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableRunnable orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return () -> {
            try {
                runThrows();
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableRunnable} that executes this {@code ThrowableRunnable}, and if an error
     * occurred, does nothing. The exception from this {@code ThrowableRunnable} is ignored, unless its an unchecked
     * exception.
     *
     * @return A composed {@code ThrowableRunnable} that executes this {@code ThrowableRunnable}, and if an error
     * occurred, does nothing.
     */
    default Runnable orDoNothing() {
        return () -> {
            try {
                runThrows();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception ignored) {
                // do nothing
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableRunnable} that executes this {@code ThrowableRunnable}, and if an error
     * occurred, does nothing. The exception from this {@code ThrowableRunnable} is ignored.
     *
     * @return A composed {@code ThrowableRunnable} that executes this {@code ThrowableRunnable}, and if an error
     * occurred, does nothing.
     */
    default Runnable orDoNothingAlways() {
        return () -> {
            try {
                runThrows();
            } catch (Exception ignored) {
                // do nothing
            }
        };
    }
}