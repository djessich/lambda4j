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
package at.gridtec.lambda4j.predicates.primitives.tri;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;

/**
 * This functional interface implements a {@link IntTriPredicate} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(int, int, int)}.
 *
 * @see java.util.function.Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableIntTriPredicate extends IntTriPredicate {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableIntTriPredicate}. This is a convenience
     * method in case the given {@link ThrowableIntTriPredicate} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableIntTriPredicate} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableIntTriPredicate} which should be returned as-is.
     * @return The given {@code ThrowableIntTriPredicate} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableIntTriPredicate wrap(final ThrowableIntTriPredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableIntTriPredicate} from the given {@link IntTriPredicate}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code IntTriPredicate} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableIntTriPredicate} from the given {@code IntTriPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableIntTriPredicate from(final IntTriPredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda::test;
    }

    /**
     * Creates a {@link ThrowableIntTriPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableIntTriPredicate} which always returns a given value.
     */
    static ThrowableIntTriPredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link ThrowableIntTriPredicate} that tests if the given arguments are equal to the ones of this
     * predicate according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code ThrowableIntTriPredicate} that tests if the given arguments are equal to the ones of this
     * predicate.
     * @see #isNotEqual(int, int, int)
     */
    static ThrowableIntTriPredicate isEqual(int target1, int target2, int target3) {
        return (value1, value2, value3) -> (value1 == target1) && (value2 == target2) && (value3 != target3);
    }

    /**
     * Returns a {@link ThrowableIntTriPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code ThrowableIntTriPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(int, int, int)
     */
    static ThrowableIntTriPredicate isNotEqual(int target1, int target2, int target3) {
        return (value1, value2, value3) -> (value1 != target1) && (value2 != target2) && (value3 != target3);
    }

    /**
     * Returns a {@link ThrowableIntTriPredicate} that always returns {@code true}.
     *
     * @return A {@link ThrowableIntTriPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    static ThrowableIntTriPredicate alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link ThrowableIntTriPredicate} the always returns {@code false}.
     *
     * @return A {@link ThrowableIntTriPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static ThrowableIntTriPredicate alwaysFalse() {
        return (value1, value2, value3) -> false;
    }

    /**
     * The test method for this {@link ThrowableIntTriPredicate} which is able to throw any {@link Exception} type.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @throws Exception Any exception from this functions action
     */
    boolean testThrows(int value1, int value2, int value3) throws Exception;

    /**
     * Overrides the {@link IntTriPredicate#test(int, int, int)} method by using a redefinition as default method. It
     * calls the {@link #testThrows(int, int, int)} method of this interface and catches the thrown {@link Exception}s
     * from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily
     * thrown.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default boolean test(int value1, int value2, int value3) {
        try {
            return testThrows(value1, value2, value3);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableIntTriPredicate} that applies this {@code ThrowableIntTriPredicate} to its
     * input, and if an error occurred, applies the given one. The exception from this {@code ThrowableIntTriPredicate}
     * is ignored.
     *
     * @param other A {@code ThrowableIntTriPredicate} to be applied if this one fails
     * @return A composed {@code ThrowableIntTriPredicate} that applies this {@code ThrowableIntTriPredicate}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableIntTriPredicate orElse(final ThrowableIntTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> {
            try {
                return testThrows(value1, value2, value3);
            } catch (Exception ignored) {
                return other.testThrows(value1, value2, value3);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableIntTriPredicate} that applies this {@code ThrowableIntTriPredicate} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableIntTriPredicate} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableIntTriPredicate} that applies this {@code ThrowableIntTriPredicate}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableIntTriPredicate orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (value1, value2, value3) -> {
            try {
                return testThrows(value1, value2, value3);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link IntTriPredicate} that applies this {@link ThrowableIntTriPredicate} to its input, and
     * if an error occurred, applies the given {@code IntTriPredicate} representing a fallback. The exception from this
     * {@code ThrowableIntTriPredicate} is ignored.
     *
     * @param fallback A {@code IntTriPredicate} to be applied if this one fails
     * @return A composed {@code IntTriPredicate} that applies this {@code ThrowableIntTriPredicate}, and if an error
     * occurred, applies the given {@code IntTriPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default IntTriPredicate fallbackTo(final IntTriPredicate fallback) {
        Objects.requireNonNull(fallback);
        return (value1, value2, value3) -> {
            try {
                return testThrows(value1, value2, value3);
            } catch (Exception ignored) {
                return fallback.test(value1, value2, value3);
            }
        };
    }

    /**
     * Returns a composed {@link IntTriPredicate} that applies this {@link ThrowableIntTriPredicate} to its input, and
     * if an error occurred, returns {@code true}. The exception from this {@code ThrowableIntTriPredicate} is ignored.
     *
     * @return A composed {@code IntTriPredicate} that applies this {@code ThrowableIntTriPredicate}, and if an error
     * occurred, returns {@code true}.
     */
    default IntTriPredicate orReturnTrue() {
        return (value1, value2, value3) -> {
            try {
                return testThrows(value1, value2, value3);
            } catch (Exception ignored) {
                return true;
            }
        };
    }

    /**
     * Returns a composed {@link IntTriPredicate} that applies this {@link ThrowableIntTriPredicate} to its input, and
     * if an error occurred, returns {@code false}. The exception from this {@code ThrowableIntTriPredicate} is
     * ignored.
     *
     * @return A composed {@code IntTriPredicate} that applies this {@code ThrowableIntTriPredicate}, and if an error
     * occurred, returns {@code false}.
     */
    default IntTriPredicate orReturnFalse() {
        return (value1, value2, value3) -> {
            try {
                return testThrows(value1, value2, value3);
            } catch (Exception ignored) {
                return false;
            }
        };
    }
}