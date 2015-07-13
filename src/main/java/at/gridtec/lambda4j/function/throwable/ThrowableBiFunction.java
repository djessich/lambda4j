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
package at.gridtec.lambda4j.function.throwable;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * This functional interface implements a {@link BiFunction} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <R> The type of return value from the function
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiFunction<T, U, R> extends BiFunction<T, U, R> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableBiFunction}. This is a convenience
     * method in case the given {@link ThrowableBiFunction} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableBiFunction} is returned
     * as-is.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param lambda The {@code ThrowableBiFunction} which should be returned as-is.
     * @return The given {@code ThrowableBiFunction} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U, R> ThrowableBiFunction<T, U, R> wrap(final ThrowableBiFunction<T, U, R> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableBiFunction} from the given {@link BiFunction}. This method is just convenience to
     * provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param lambda A {@code BiFunction} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableBiFunction} from the given {@code BiFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U, R> ThrowableBiFunction<T, U, R> from(final BiFunction<T, U, R> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::apply;
    }

    /**
     * Creates a {@link ThrowableBiFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ThrowableBiFunction} which always returns a given value.
     */
    static <T, U, R> ThrowableBiFunction<T, U, R> constant(R r) {
        return (t, u) -> r;
    }

    /**
     * The apply method for this {@link BiFunction} which is able to throw any {@link Exception} type.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     * @throws Exception Any exception from this functions action
     */
    R applyThrows(T t, U u) throws Exception;

    /**
     * Overrides the {@link BiFunction#apply(Object, Object)} method by using a redefinition as default method. It
     * calls the {@link #applyThrows(Object, Object)} method of this interface and catches the thrown {@link
     * Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types
     * are sneakily thrown.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     * @see at.gridtec.lambda4j.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default R apply(T t, U u) {
        try {
            return applyThrows(t, u);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw at.gridtec.lambda4j.util.ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableBiFunction} that applies this {@code ThrowableBiFunction} to its input, and
     * if an error occurred, applies the given one. The exception from this {@code ThrowableBiFunction} is ignored.
     *
     * @param other A {@code ThrowableBiFunction} to be applied if this one fails
     * @return A composed {@code ThrowableBiFunction} that applies this {@code ThrowableBiFunction}, and if an error
     * occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableBiFunction<T, U, R> orElse(final ThrowableBiFunction<? super T, ? super U, ? extends R> other) {
        Objects.requireNonNull(other);
        return (t, u) -> {
            try {
                return applyThrows(t, u);
            } catch (Exception ignored) {
                return other.applyThrows(t, u);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableBiFunction} that applies this {@code ThrowableBiFunction} to its input, and
     * if an error occurred, throws the given {@link Exception}. The exception from this {@code ThrowableBiFunction} is
     * added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableBiFunction} that applies this {@code ThrowableBiFunction}, and if an error
     * occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableBiFunction<T, U, R> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (t, u) -> {
            try {
                return applyThrows(t, u);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link BiFunction} that applies this {@link ThrowableBiFunction} to its input, and if an
     * error occurred, applies the given {@code BiFunction} representing a fallback. The exception from this {@code
     * ThrowableBiFunction} is ignored.
     *
     * @param fallback A {@code BiFunction} to be applied if this one fails
     * @return A composed {@code BiFunction} that applies this {@code ThrowableBiFunction}, and if an error occurred,
     * applies the given {@code BiFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default BiFunction<T, U, R> fallbackTo(final BiFunction<? super T, ? super U, ? extends R> fallback) {
        Objects.requireNonNull(fallback);
        return (t, u) -> {
            try {
                return applyThrows(t, u);
            } catch (Exception ignored) {
                return fallback.apply(t, u);
            }
        };
    }

    /**
     * Returns a composed {@link BiFunction} that applies this {@link ThrowableBiFunction} to its input, and if an
     * error occurred, returns the given value. The exception from this {@code ThrowableBiFunction} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableBiFunction} fails
     * @return A composed {@code BiFunction} that applies this {@code ThrowableBiFunction}, and if an error occurred,
     * returns the given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default BiFunction<T, U, R> orReturn(final R value) {
        Objects.requireNonNull(value);
        return (t, u) -> {
            try {
                return applyThrows(t, u);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link BiFunction} that applies this {@link ThrowableBiFunction} to its input, and if an
     * error occurred, returns the supplied value from the given {@link Supplier}. The exception from this {@code
     * ThrowableBiFunction} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableBiFunction} fails
     * @return A composed {@code BiFunction} that applies this {@code ThrowableBiFunction}, and if an error occurred,
     * the supplied value from the given {@code Supplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default BiFunction<T, U, R> orReturn(final Supplier<? extends R> supplier) {
        Objects.requireNonNull(supplier);
        return (t, u) -> {
            try {
                return applyThrows(t, u);
            } catch (Exception ignored) {
                return supplier.get();
            }
        };
    }

    /**
     * Returns a curried version of this {@link ThrowableBiFunction}. The returned curried version of this {@code
     * ThrowableBiFunction} is able to throw any {@link Exception} type.
     *
     * @return A curried version of this {@code ThrowableBiFunction}.
     * @see #reversed(ThrowableFunction)
     */
    default ThrowableFunction<T, ThrowableFunction<U, R>> curried() {
        return t -> u -> apply(t, u);
    }

    /**
     * Returns a reversed (uncurried) {@link ThrowableBiFunction} from the given curried {@code ThrowableBiFunction}.
     * The returned {@code ThrowableBiFunction} from the given curried {@code ThrowableBiFunction} is able to throw any
     * {@link Exception} type.
     *
     * @param f A curried {@code ThrowableBiFunction}
     * @return A reversed (uncurried) {@link ThrowableBiFunction} from the given curried {@code ThrowableBiFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #curried()
     */
    default ThrowableBiFunction<T, U, R> reversed(
            ThrowableFunction<? super T, ThrowableFunction<? super U, ? extends R>> f) {
        Objects.requireNonNull(f);
        return (t, u) -> f.apply(t).apply(u);
    }
}
