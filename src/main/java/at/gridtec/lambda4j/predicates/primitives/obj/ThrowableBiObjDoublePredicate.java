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
package at.gridtec.lambda4j.predicates.primitives.obj;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;

/**
 * This functional interface implements a {@link BiObjDoublePredicate} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(Object, Object, double)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @see java.util.function.BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiObjDoublePredicate<T, U> extends BiObjDoublePredicate<T, U> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableBiObjDoublePredicate}. This is a
     * convenience method in case the given {@link ThrowableBiObjDoublePredicate} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableBiObjDoublePredicate} is returned as-is.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param lambda The {@code ThrowableBiObjDoublePredicate} which should be returned as-is.
     * @return The given {@code ThrowableBiObjDoublePredicate} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiObjDoublePredicate<T, U> wrap(final ThrowableBiObjDoublePredicate<T, U> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableBiObjDoublePredicate} from the given {@link BiObjDoublePredicate}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param lambda A {@code BiObjDoublePredicate} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableBiObjDoublePredicate} from the given {@code BiObjDoublePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ThrowableBiObjDoublePredicate<T, U> from(final BiObjDoublePredicate<T, U> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::test;
    }

    /**
     * Creates a {@link ThrowableBiObjDoublePredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowableBiObjDoublePredicate} which always returns a given value.
     */
    static <T, U> ThrowableBiObjDoublePredicate<T, U> constant(boolean ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Returns a {@link ThrowableBiObjDoublePredicate} that tests if the given arguments are equal to the ones of this
     * predicate according to {@code value == target} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first target value with which to compare for equality
     * @param targetRef2 The second target value with which to compare for equality
     * @param targetValue The third target value with which to compare for equality
     * @return A {@code ThrowableBiObjDoublePredicate} that tests if the given arguments are equal to the ones of this
     * predicate.
     * @see #isNotEqual(Object, Object, double)
     */
    static <T, U> ThrowableBiObjDoublePredicate<T, U> isEqual(Object targetRef1, Object targetRef2,
            double targetValue) {
        return (t, u, value) -> (t == null ? targetRef1 == null : t.equals(targetRef1)) && (u == null ? targetRef2
                == null : u.equals(targetRef2)) && (value == targetValue);
    }

    /**
     * Returns a {@link ThrowableBiObjDoublePredicate} that tests if the given arguments are not equal to the ones of
     * this predicate according to {@code value != target} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first target value with which to compare for equality
     * @param targetRef2 The second target value with which to compare for equality
     * @param targetValue The third target value with which to compare for equality
     * @return A {@code ThrowableBiObjDoublePredicate} that tests if the given arguments are not equal to the ones of
     * this predicate.
     * @see #isEqual(Object, Object, double)
     */
    static <T, U> ThrowableBiObjDoublePredicate<T, U> isNotEqual(Object targetRef1, Object targetRef2,
            double targetValue) {
        return (t, u, value) -> !(t == null ? targetRef1 == null : t.equals(targetRef1)) || !(u == null ? targetRef2
                == null : u.equals(targetRef2)) || (value != targetValue);
    }

    /**
     * Returns a {@link ThrowableBiObjDoublePredicate} the always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link ThrowableBiObjDoublePredicate} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static <T, U> ThrowableBiObjDoublePredicate<T, U> alwaysTrue() {
        return (t, u, value) -> true;
    }

    /**
     * Returns a {@link ThrowableBiObjDoublePredicate} the always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link ThrowableBiObjDoublePredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static <T, U> ThrowableBiObjDoublePredicate<T, U> alwaysFalse() {
        return (t, u, value) -> false;
    }

    /**
     * The test method for this {@link ThrowableBiObjDoublePredicate} which is able to throw any {@link Exception}
     * type.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @throws Exception Any exception from this functions action
     */
    boolean testThrows(T t, U u, double value) throws Exception;

    /**
     * Overrides the {@link BiObjDoublePredicate#test(Object, Object, double)} method by using a redefinition as default
     * method. It calls the {@link #testThrows(Object, Object, double)} method of this interface and catches the thrown
     * {@link Exception}s from it . If it is of type {@link RuntimeException}, the exception is rethrown. Other
     * exception types are sneakily thrown.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default boolean test(T t, U u, double value) {
        try {
            return testThrows(t, u, value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableBiObjDoublePredicate} that applies this {@code ThrowableBiObjDoublePredicate}
     * to its input, and if an error occurred, applies the given one. The exception from this {@code
     * ThrowableBiObjDoublePredicate} is ignored.
     *
     * @param other A {@code ThrowableBiObjDoublePredicate} to be applied if this one fails
     * @return A composed {@code ThrowableBiObjDoublePredicate} that applies this {@code ThrowableBiObjDoublePredicate},
     * and if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableBiObjDoublePredicate<T, U> orElse(
            final ThrowableBiObjDoublePredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> {
            try {
                return testThrows(t, u, value);
            } catch (Exception ignored) {
                return other.testThrows(t, u, value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableBiObjDoublePredicate} that applies this {@code ThrowableBiObjDoublePredicate}
     * to its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableBiObjDoublePredicate} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableBiObjDoublePredicate} that applies this {@code ThrowableBiObjDoublePredicate},
     * and if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableBiObjDoublePredicate<T, U> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (t, u, value) -> {
            try {
                return testThrows(t, u, value);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link BiObjDoublePredicate} that applies this {@link ThrowableBiObjDoublePredicate} to its
     * input, and if an error occurred, applies the given {@code BiObjDoublePredicate} representing a fallback. The
     * exception from this {@code ThrowableBiObjDoublePredicate} is ignored.
     *
     * @param fallback A {@code BiObjDoublePredicate} to be applied if this one fails
     * @return A composed {@code BiObjDoublePredicate} that applies this {@code ThrowableBiObjDoublePredicate}, and if
     * an error occurred, applies the given {@code BiObjDoublePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default BiObjDoublePredicate<T, U> fallbackTo(final BiObjDoublePredicate<? super T, ? super U> fallback) {
        Objects.requireNonNull(fallback);
        return (t, u, value) -> {
            try {
                return testThrows(t, u, value);
            } catch (Exception ignored) {
                return fallback.test(t, u, value);
            }
        };
    }

    /**
     * Returns a composed {@link BiObjDoublePredicate} that applies this {@link ThrowableBiObjDoublePredicate} to its
     * input, and if an error occurred, returns {@code true}. The exception from this {@code
     * ThrowableBiObjDoublePredicate} is ignored.
     *
     * @return A composed {@code BiObjDoublePredicate} that applies this {@code ThrowableBiObjDoublePredicate}, and if
     * an error occurred, returns {@code true}.
     */
    default BiObjDoublePredicate<T, U> orReturnTrue() {
        return (t, u, value) -> {
            try {
                return testThrows(t, u, value);
            } catch (Exception ignored) {
                return true;
            }
        };
    }

    /**
     * Returns a composed {@link BiObjDoublePredicate} that applies this {@link ThrowableBiObjDoublePredicate} to its
     * input, and if an error occurred, returns {@code false}. The exception from this {@code
     * ThrowableBiObjDoublePredicate} is ignored.
     *
     * @return A composed {@code BiObjDoublePredicate} that applies this {@code ThrowableBiObjDoublePredicate}, and if
     * an error occurred, returns {@code false}.
     */
    default BiObjDoublePredicate<T, U> orReturnFalse() {
        return (t, u, value) -> {
            try {
                return testThrows(t, u, value);
            } catch (Exception ignored) {
                return false;
            }
        };
    }
}
