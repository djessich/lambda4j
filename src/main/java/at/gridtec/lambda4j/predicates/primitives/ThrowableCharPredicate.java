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
package at.gridtec.lambda4j.predicates.primitives;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;

/**
 * This functional interface implements a {@link CharPredicate} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(char)}.
 *
 * @see java.util.function.Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableCharPredicate extends CharPredicate {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableCharPredicate}. This is a convenience
     * method in case the given {@link ThrowableCharPredicate} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableCharPredicate} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableCharPredicate} which should be returned as-is.
     * @return The given {@code ThrowableCharPredicate} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableCharPredicate wrap(final ThrowableCharPredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableCharPredicate} from the given {@link CharPredicate}. This method is just convenience to
     * provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param lambda A {@code CharPredicate} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableCharPredicate} from the given {@code CharPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableCharPredicate from(final CharPredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda::test;
    }

    /**
     * Creates a {@link ThrowableCharPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableCharPredicate} which always returns a given value.
     */
    static ThrowableCharPredicate constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Returns a {@link ThrowableCharPredicate} that tests if the given argument is equal to the one of this predicate
     * according to {@code value == target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code ThrowableCharPredicate} that tests if the given argument is equal to the one of this predicate.
     * @see #isNotEqual(char)
     */
    static ThrowableCharPredicate isEqual(char target) {
        return value -> value == target;
    }

    /**
     * Returns a {@link ThrowableCharPredicate} that tests if the given argument is not equal to the one of this
     * predicate according to {@code value != target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code ThrowableCharPredicate} that tests if the given argument is not equal to the one of this
     * predicate.
     * @see #isEqual(char)
     */
    static ThrowableCharPredicate isNotEqual(char target) {
        return value -> value != target;
    }

    /**
     * Returns a {@link ThrowableCharPredicate} the always returns {@code true}.
     *
     * @return A {@link ThrowableCharPredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static ThrowableCharPredicate alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link ThrowableCharPredicate} the always returns {@code false}.
     *
     * @return A {@link ThrowableCharPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static ThrowableCharPredicate alwaysFalse() {
        return value -> false;
    }

    /**
     * The test method for this {@link CharPredicate} which is able to throw any {@link Exception} type.
     *
     * @param value The argument for the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @throws Exception Any exception from this functions action
     */
    boolean testThrows(char value) throws Exception;

    /**
     * Overrides the {@link CharPredicate#test(char)} method by using a redefinition as default method. It calls the
     * {@link #testThrows(char)} method of this interface and catches the thrown {@link Exception}s from it. If it is of
     * type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @param value The argument for the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default boolean test(char value) {
        try {
            return testThrows(value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableCharPredicate} that applies this {@code ThrowableCharPredicate} to its input,
     * and if an error occurred, applies the given one. The exception from this {@code ThrowableCharPredicate} is
     * ignored.
     *
     * @param other A {@code ThrowableCharPredicate} to be applied if this one fails
     * @return A composed {@code ThrowableCharPredicate} that applies this {@code ThrowableCharPredicate}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableCharPredicate orElse(final ThrowableCharPredicate other) {
        Objects.requireNonNull(other);
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return other.testThrows(value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableCharPredicate} that applies this {@code ThrowableCharPredicate} to its input,
     * and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableCharPredicate} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableCharPredicate} that applies this {@code ThrowableCharPredicate}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableCharPredicate orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link CharPredicate} that applies this {@link ThrowableCharPredicate} to its input, and if an
     * error occurred, applies the given {@code CharPredicate} representing a fallback. The exception from this {@code
     * ThrowableCharPredicate} is ignored.
     *
     * @param fallback A {@code CharPredicate} to be applied if this one fails
     * @return A composed {@code CharPredicate} that applies this {@code ThrowableCharPredicate}, and if an error
     * occurred, applies the given {@code CharPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default CharPredicate fallbackTo(final CharPredicate fallback) {
        Objects.requireNonNull(fallback);
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return fallback.test(value);
            }
        };
    }

    /**
     * Returns a composed {@link CharPredicate} that applies this {@link ThrowableCharPredicate} to its input, and if an
     * error occurred, returns {@code true}. The exception from this {@code ThrowableCharPredicate} is ignored.
     *
     * @return A composed {@code CharPredicate} that applies this {@code ThrowableCharPredicate}, and if an error
     * occurred, returns {@code true}.
     */
    default CharPredicate orReturnTrue() {
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return true;
            }
        };
    }

    /**
     * Returns a composed {@link CharPredicate} that applies this {@link ThrowableCharPredicate} to its input, and if an
     * error occurred, returns {@code false}. The exception from this {@code ThrowableCharPredicate} is ignored.
     *
     * @return A composed {@code CharPredicate} that applies this {@code ThrowableCharPredicate}, and if an error
     * occurred, returns {@code false}.
     */
    default CharPredicate orReturnFalse() {
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return false;
            }
        };
    }
}
