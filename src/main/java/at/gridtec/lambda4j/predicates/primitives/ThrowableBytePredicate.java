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
 * This functional interface implements a {@link BytePredicate} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(byte)}.
 *
 * @see java.util.function.Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBytePredicate extends BytePredicate {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableBytePredicate}. This is a convenience
     * method in case the given {@link ThrowableBytePredicate} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableBytePredicate} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableBytePredicate} which should be returned as-is.
     * @return The given {@code ThrowableBytePredicate} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableBytePredicate wrap(final ThrowableBytePredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableBytePredicate} from the given {@link BytePredicate}. This method is just convenience to
     * provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param lambda A {@code BytePredicate} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableBytePredicate} from the given {@code BytePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableBytePredicate from(final BytePredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda::test;
    }

    /**
     * Creates a {@link ThrowableBytePredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableBytePredicate} which always returns a given value.
     */
    static ThrowableBytePredicate constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Returns a {@link ThrowableBytePredicate} that tests if the given argument is equal to the one of this predicate
     * according to {@code value == target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code ThrowableBytePredicate} that tests if the given argument is equal to the one of this predicate.
     * @see #isNotEqual(byte)
     */
    static ThrowableBytePredicate isEqual(byte target) {
        return value -> value == target;
    }

    /**
     * Returns a {@link ThrowableBytePredicate} that tests if the given argument is not equal to the one of this
     * predicate according to {@code value != target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code ThrowableBytePredicate} that tests if the given argument is not equal to the one of this
     * predicate.
     * @see #isEqual(byte)
     */
    static ThrowableBytePredicate isNotEqual(byte target) {
        return value -> value != target;
    }

    /**
     * Returns a {@link ThrowableBytePredicate} the always returns {@code true}.
     *
     * @return A {@link ThrowableBytePredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static ThrowableBytePredicate alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link ThrowableBytePredicate} the always returns {@code false}.
     *
     * @return A {@link ThrowableBytePredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static ThrowableBytePredicate alwaysFalse() {
        return value -> false;
    }

    /**
     * The test method for this {@link BytePredicate} which is able to throw any {@link Exception} type.
     *
     * @param value The argument for the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @throws Exception Any exception from this functions action
     */
    boolean testThrows(byte value) throws Exception;

    /**
     * Overrides the {@link BytePredicate#test(byte)} method by using a redefinition as default method. It calls the
     * {@link #testThrows(byte)} method of this interface and catches the thrown {@link Exception}s from it. If it is of
     * type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @param value The argument for the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default boolean test(byte value) {
        try {
            return testThrows(value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableBytePredicate} that applies this {@code ThrowableBytePredicate} to its input,
     * and if an error occurred, applies the given one. The exception from this {@code ThrowableBytePredicate} is
     * ignored.
     *
     * @param other A {@code ThrowableBytePredicate} to be applied if this one fails
     * @return A composed {@code ThrowableBytePredicate} that applies this {@code ThrowableBytePredicate}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableBytePredicate orElse(final ThrowableBytePredicate other) {
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
     * Returns a composed {@link ThrowableBytePredicate} that applies this {@code ThrowableBytePredicate} to its input,
     * and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableBytePredicate} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableBytePredicate} that applies this {@code ThrowableBytePredicate}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableBytePredicate orThrow(Class<X> clazz) {
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
     * Returns a composed {@link BytePredicate} that applies this {@link ThrowableBytePredicate} to its input, and if an
     * error occurred, applies the given {@code BytePredicate} representing a fallback. The exception from this {@code
     * ThrowableBytePredicate} is ignored.
     *
     * @param fallback A {@code BytePredicate} to be applied if this one fails
     * @return A composed {@code BytePredicate} that applies this {@code ThrowableBytePredicate}, and if an error
     * occurred, applies the given {@code BytePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default BytePredicate fallbackTo(final BytePredicate fallback) {
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
     * Returns a composed {@link BytePredicate} that applies this {@link ThrowableBytePredicate} to its input, and if an
     * error occurred, returns {@code true}. The exception from this {@code ThrowableBytePredicate} is ignored.
     *
     * @return A composed {@code BytePredicate} that applies this {@code ThrowableBytePredicate}, and if an error
     * occurred, returns {@code true}.
     */
    default BytePredicate orReturnTrue() {
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return true;
            }
        };
    }

    /**
     * Returns a composed {@link BytePredicate} that applies this {@link ThrowableBytePredicate} to its input, and if an
     * error occurred, returns {@code false}. The exception from this {@code ThrowableBytePredicate} is ignored.
     *
     * @return A composed {@code BytePredicate} that applies this {@code ThrowableBytePredicate}, and if an error
     * occurred, returns {@code false}.
     */
    default BytePredicate orReturnFalse() {
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return false;
            }
        };
    }
}