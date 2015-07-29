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
 * This functional interface implements a {@link FloatTriPredicate} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(float, float, float)}.
 *
 * @see java.util.function.Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableFloatTriPredicate extends FloatTriPredicate {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableFloatTriPredicate}. This is a convenience
     * method in case the given {@link ThrowableFloatTriPredicate} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableFloatTriPredicate} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableFloatTriPredicate} which should be returned as-is.
     * @return The given {@code ThrowableFloatTriPredicate} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatTriPredicate wrap(final ThrowableFloatTriPredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableFloatTriPredicate} from the given {@link FloatTriPredicate}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code FloatTriPredicate} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableFloatTriPredicate} from the given {@code FloatTriPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatTriPredicate from(final FloatTriPredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda::test;
    }

    /**
     * Creates a {@link ThrowableFloatTriPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableFloatTriPredicate} which always returns a given value.
     */
    static ThrowableFloatTriPredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link ThrowableFloatTriPredicate} that tests if the given arguments are equal to the ones of this
     * predicate according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code ThrowableFloatTriPredicate} that tests if the given arguments are equal to the ones of this
     * predicate.
     * @see #isNotEqual(float, float, float)
     */
    static ThrowableFloatTriPredicate isEqual(float target1, float target2, float target3) {
        return (value1, value2, value3) -> (value1 == target1) && (value2 == target2) && (value3 != target3);
    }

    /**
     * Returns a {@link ThrowableFloatTriPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code ThrowableFloatTriPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(float, float, float)
     */
    static ThrowableFloatTriPredicate isNotEqual(float target1, float target2, float target3) {
        return (value1, value2, value3) -> (value1 != target1) && (value2 != target2) && (value3 != target3);
    }

    /**
     * Returns a {@link ThrowableFloatTriPredicate} the always returns {@code true}.
     *
     * @return A {@link ThrowableFloatTriPredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static ThrowableFloatTriPredicate alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link ThrowableFloatTriPredicate} the always returns {@code false}.
     *
     * @return A {@link ThrowableFloatTriPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static ThrowableFloatTriPredicate alwaysFalse() {
        return (value1, value2, value3) -> false;
    }

    /**
     * The test method for this {@link ThrowableFloatTriPredicate} which is able to throw any {@link Exception} type.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @throws Exception Any exception from this functions action
     */
    boolean testThrows(float value1, float value2, float value3) throws Exception;

    /**
     * Overrides the {@link FloatTriPredicate#test(float, float, float)} method by using a redefinition as default
     * method. It calls the {@link #testThrows(float, float, float)} method of this interface and catches the thrown
     * {@link Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception
     * types are sneakily thrown.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default boolean test(float value1, float value2, float value3) {
        try {
            return testThrows(value1, value2, value3);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableFloatTriPredicate} that applies this {@code ThrowableFloatTriPredicate} to its
     * input, and if an error occurred, applies the given one. The exception from this {@code
     * ThrowableFloatTriPredicate} is ignored.
     *
     * @param other A {@code ThrowableFloatTriPredicate} to be applied if this one fails
     * @return A composed {@code ThrowableFloatTriPredicate} that applies this {@code ThrowableFloatTriPredicate}, and
     * if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableFloatTriPredicate orElse(final ThrowableFloatTriPredicate other) {
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
     * Returns a composed {@link ThrowableFloatTriPredicate} that applies this {@code ThrowableFloatTriPredicate} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableFloatTriPredicate} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableFloatTriPredicate} that applies this {@code ThrowableFloatTriPredicate}, and
     * if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableFloatTriPredicate orThrow(Class<X> clazz) {
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
     * Returns a composed {@link FloatTriPredicate} that applies this {@link ThrowableFloatTriPredicate} to its input,
     * and if an error occurred, applies the given {@code FloatTriPredicate} representing a fallback. The exception from
     * this {@code ThrowableFloatTriPredicate} is ignored.
     *
     * @param fallback A {@code FloatTriPredicate} to be applied if this one fails
     * @return A composed {@code FloatTriPredicate} that applies this {@code ThrowableFloatTriPredicate}, and if an
     * error occurred, applies the given {@code FloatTriPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatTriPredicate fallbackTo(final FloatTriPredicate fallback) {
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
     * Returns a composed {@link FloatTriPredicate} that applies this {@link ThrowableFloatTriPredicate} to its input,
     * and if an error occurred, returns {@code true}. The exception from this {@code ThrowableFloatTriPredicate} is
     * ignored.
     *
     * @return A composed {@code FloatTriPredicate} that applies this {@code ThrowableFloatTriPredicate}, and if an
     * error occurred, returns {@code true}.
     */
    default FloatTriPredicate orReturnTrue() {
        return (value1, value2, value3) -> {
            try {
                return testThrows(value1, value2, value3);
            } catch (Exception ignored) {
                return true;
            }
        };
    }

    /**
     * Returns a composed {@link FloatTriPredicate} that applies this {@link ThrowableFloatTriPredicate} to its input,
     * and if an error occurred, returns {@code false}. The exception from this {@code ThrowableFloatTriPredicate} is
     * ignored.
     *
     * @return A composed {@code FloatTriPredicate} that applies this {@code ThrowableFloatTriPredicate}, and if an
     * error occurred, returns {@code false}.
     */
    default FloatTriPredicate orReturnFalse() {
        return (value1, value2, value3) -> {
            try {
                return testThrows(value1, value2, value3);
            } catch (Exception ignored) {
                return false;
            }
        };
    }
}
