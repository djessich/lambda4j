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
package at.gridtec.lambda4j.throwable.function;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.DoubleSupplier;
import java.util.function.ToDoubleBiFunction;

/**
 * This functional interface implements a {@link ToDoubleBiFunction} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDoubleThrows(Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableToDoubleBiFunction<T, U> extends ToDoubleBiFunction<T, U> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableToDoubleBiFunction}. This is a
     * convenience method in case the given {@link ThrowableToDoubleBiFunction} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableToDoubleBiFunction} is returned as-is.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param lambda The {@code ThrowableToDoubleBiFunction} which should be returned as-is.
     * @return The given {@code ThrowableToDoubleBiFunction} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableToDoubleBiFunction<T, U> wrap(final ThrowableToDoubleBiFunction<T, U> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableToDoubleBiFunction} from the given {@link ToDoubleBiFunction}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param lambda A {@code ToDoubleBiFunction} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableToDoubleBiFunction} from the given {@code ToDoubleBiFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableToDoubleBiFunction<T, U> from(final ToDoubleBiFunction<T, U> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsDouble;
    }

    /**
     * Creates a {@link ThrowableToDoubleBiFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ThrowableToDoubleBiFunction} which always returns a given value.
     */
    static <T, U> ThrowableToDoubleBiFunction<T, U> constant(double ret) {
        return (t, u) -> ret;
    }

    /**
     * The apply method for this {@link ToDoubleBiFunction} which is able to throw any {@link Exception} type.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     * @throws Exception Any exception from this functions action
     */
    double applyAsDoubleThrows(T t, U u) throws Exception;

    /**
     * Overrides the {@link ToDoubleBiFunction#applyAsDouble(Object, Object)} method by using a redefinition as default
     * method. It calls the {@link #applyAsDoubleThrows(Object, Object)} method of this interface and catches the thrown
     * {@link Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception
     * types are sneakily thrown.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     * @see at.gridtec.lambda4j.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default double applyAsDouble(T t, U u) {
        try {
            return applyAsDoubleThrows(t, u);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableToDoubleBiFunction} that applies this {@code ThrowableToDoubleBiFunction} to
     * its input, and if an error occurred, applies the given one. The exception from this {@code
     * ThrowableToDoubleBiFunction} is ignored.
     *
     * @param other A {@code ThrowableToDoubleBiFunction} to be applied if this one fails
     * @return A composed {@code ThrowableToDoubleBiFunction} that applies this {@code ThrowableToDoubleBiFunction}, and
     * if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableToDoubleBiFunction<T, U> orElse(final ThrowableToDoubleBiFunction<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u) -> {
            try {
                return applyAsDoubleThrows(t, u);
            } catch (Exception ignored) {
                return other.applyAsDoubleThrows(t, u);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableToDoubleBiFunction} that applies this {@code ThrowableToDoubleBiFunction} to
     * its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableToDoubleBiFunction} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableToDoubleBiFunction} that applies this {@code ThrowableToDoubleBiFunction}, and
     * if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableToDoubleBiFunction<T, U> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (t, u) -> {
            try {
                return applyAsDouble(t, u);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction} that applies this {@link ThrowableToDoubleBiFunction} to its input,
     * and if an error occurred, applies the given {@code ToDoubleBiFunction} representing a fallback. The exception
     * from this {@code ThrowableToDoubleBiFunction} is ignored.
     *
     * @param fallback A {@code ToDoubleBiFunction} to be applied if this one fails
     * @return A composed {@code ToDoubleBiFunction} that applies this {@code ThrowableToDoubleBiFunction}, and if an
     * error occurred, applies the given {@code ToDoubleBiFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ToDoubleBiFunction<T, U> fallbackTo(final ToDoubleBiFunction<? super T, ? super U> fallback) {
        Objects.requireNonNull(fallback);
        return (t, u) -> {
            try {
                return applyAsDoubleThrows(t, u);
            } catch (Exception ignored) {
                return fallback.applyAsDouble(t, u);
            }
        };
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction} that applies this {@link ThrowableToDoubleBiFunction} to its input,
     * and if an error occurred, returns the given value. The exception from this {@code ThrowableToDoubleBiFunction} is
     * ignored.
     *
     * @param value The value to be returned if this {@code ThrowableToDoubleBiFunction} fails
     * @return A composed {@code ToDoubleBiFunction} that applies this {@code ThrowableToDoubleBiFunction}, and if an
     * error occurred, returns the given value.
     */
    default ToDoubleBiFunction<T, U> orReturn(double value) {
        return (t, u) -> {
            try {
                return applyAsDoubleThrows(t, u);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction} that applies this {@link ThrowableToDoubleBiFunction} to its input,
     * and if an error occurred, returns the supplied value from the given {@link DoubleSupplier}. The exception from
     * this {@code ThrowableToDoubleBiFunction} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableToDoubleBiFunction} fails
     * @return A composed {@code ToDoubleBiFunction} that applies this {@code ThrowableToDoubleBiFunction}, and if an
     * error occurred, the supplied value from the given {@code DoubleSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ToDoubleBiFunction<T, U> orReturn(final DoubleSupplier supplier) {
        Objects.requireNonNull(supplier);
        return (t, u) -> {
            try {
                return applyAsDoubleThrows(t, u);
            } catch (Exception ignored) {
                return supplier.getAsDouble();
            }
        };
    }
}
