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
package at.gridtec.lambda4j.predicates;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * This functional interface implements a {@link Predicate} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(Object)}.
 *
 * @param <T> The type of argument for the predicate
 * @apiNote This is a throwable JRE lambda
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowablePredicate<T> extends Predicate<T> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowablePredicate}. This is a convenience method
     * in case the given {@link ThrowablePredicate} is ambiguous for the compiler. This might happen for overloaded
     * methods accepting different functional interfaces. The given {@code ThrowablePredicate} is returned as-is.
     *
     * @param <T> The type of argument for the predicate
     * @param lambda The {@code ThrowablePredicate} which should be returned as-is.
     * @return The given {@code ThrowablePredicate} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowablePredicate<T> wrap(final ThrowablePredicate<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowablePredicate} from the given {@link Predicate}. This method is just convenience to
     * provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param <T> The type of argument for the predicate
     * @param lambda A {@code Predicate} which should be mapped to its throwable counterpart
     * @return A {@code ThrowablePredicate} from the given {@code Predicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowablePredicate<T> from(final Predicate<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::test;
    }

    /**
     * Creates a {@link ThrowablePredicate} which always returns a given value.
     *
     * @param <T> The type of argument for the predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowablePredicate} which always returns a given value.
     */
    static <T> ThrowablePredicate<T> constant(boolean ret) {
        return t -> ret;
    }

    /**
     * The test method for this {@link Predicate} which is able to throw any {@link Exception} type.
     *
     * @param t The argument for the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     * @throws Exception Any exception from this functions action
     */
    boolean testThrows(T t) throws Exception;

    /**
     * Overrides the {@link Predicate#test(Object)} method by using a redefinition as default method. It calls the
     * {@link #testThrows(Object)} method of this interface and catches the thrown {@link Exception}s from it. If it is
     * of type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @param t The argument for the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default boolean test(T t) {
        try {
            return testThrows(t);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowablePredicate} that applies this {@code ThrowablePredicate} to its input, and if
     * an error occurred, applies the given one. The exception from this {@code ThrowablePredicate} is ignored.
     *
     * @param other A {@code ThrowablePredicate} to be applied if this one fails
     * @return A composed {@code ThrowablePredicate} that applies this {@code ThrowablePredicate}, and if an error
     * occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowablePredicate<T> orElse(final ThrowablePredicate<? super T> other) {
        Objects.requireNonNull(other);
        return t -> {
            try {
                return testThrows(t);
            } catch (Exception ignored) {
                return other.testThrows(t);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowablePredicate} that applies this {@code ThrowablePredicate} to its input, and if
     * an error occurred, throws the given {@link Exception}. The exception from this {@code ThrowablePredicate} is
     * added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowablePredicate} that applies this {@code ThrowablePredicate}, and if an error
     * occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowablePredicate<T> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return t -> {
            try {
                return testThrows(t);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link Predicate} that applies this {@link ThrowablePredicate} to its input, and if an error
     * occurred, applies the given {@code Predicate} representing a fallback. The exception from this {@code
     * ThrowablePredicate} is ignored.
     *
     * @param fallback A {@code Predicate} to be applied if this one fails
     * @return A composed {@code Predicate} that applies this {@code ThrowablePredicate}, and if an error occurred,
     * applies the given {@code Predicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default Predicate<T> fallbackTo(final Predicate<? super T> fallback) {
        Objects.requireNonNull(fallback);
        return t -> {
            try {
                return testThrows(t);
            } catch (Exception ignored) {
                return fallback.test(t);
            }
        };
    }

    /**
     * Returns a composed {@link Predicate} that applies this {@link ThrowablePredicate} to its input, and if an error
     * occurred, returns {@code true}. The exception from this {@code ThrowablePredicate} is ignored.
     *
     * @return A composed {@code Predicate} that applies this {@code ThrowablePredicate}, and if an error occurred,
     * returns {@code true}.
     */
    default Predicate<T> orReturnTrue() {
        return t -> {
            try {
                return testThrows(t);
            } catch (Exception ignored) {
                return true;
            }
        };
    }

    /**
     * Returns a composed {@link Predicate} that applies this {@link ThrowablePredicate} to its input, and if an error
     * occurred, returns {@code false}. The exception from this {@code ThrowablePredicate} is ignored.
     *
     * @return A composed {@code Predicate} that applies this {@code ThrowablePredicate}, and if an error occurred,
     * returns {@code false}.
     */
    default Predicate<T> orReturnFalse() {
        return t -> {
            try {
                return testThrows(t);
            } catch (Exception ignored) {
                return false;
            }
        };
    }
}
