/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */
package at.gridtec.internals.lang.function.throwable.predicates;

import at.gridtec.internals.lang.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.LongPredicate;

/**
 * This functional interface implements a {@link LongPredicate} which is able to throw any {@link Exception}.
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
 *
 * @see java.util.function.Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableLongPredicate extends LongPredicate {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableLongPredicate}. This is a convenience
     * method in case the given {@link ThrowableLongPredicate} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableLongPredicate} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableLongPredicate} which should be returned as-is.
     * @return The given {@code ThrowableLongPredicate} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongPredicate wrap(final ThrowableLongPredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableLongPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableLongPredicate} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongPredicate constant(boolean ret) {
        Objects.requireNonNull(ret);
        return value -> ret;
    }

    /**
     * The test method for this {@link LongPredicate} which is able to throw any {@link Exception} type.
     *
     * @param value The argument for the function
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     * @throws Exception Any exception from this functions action
     */
    boolean testThrows(long value) throws Exception;

    /**
     * Overrides the {@link LongPredicate#test(long)} method by using a redefinition as default method. It calls the
     * {@link #testThrows(long)} method of this interface and catches the thrown {@link Exception}s from it. If it is
     * of type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @param value The argument for the function
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default boolean test(long value) {
        try {
            return testThrows(value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableLongPredicate} that applies this {@code ThrowableLongPredicate} to its input,
     * and if an error occurred, applies the given one. The exception from this {@code ThrowableLongPredicate} is
     * ignored.
     *
     * @param other A {@code ThrowableLongPredicate} to be applied if this one fails
     * @return A composed {@code ThrowableLongPredicate} that applies this {@code ThrowableLongPredicate}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableLongPredicate orElse(final ThrowableLongPredicate other) {
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
     * Returns a composed {@link ThrowableLongPredicate} that applies this {@code ThrowableLongPredicate} to its input,
     * and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableLongPredicate} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableLongPredicate} that applies this {@code ThrowableLongPredicate}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableLongPredicate orThrow(Class<X> clazz) {
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
     * Returns a composed {@link LongPredicate} that applies this {@link ThrowableLongPredicate} to its input, and if
     * an error occurred, applies the given {@code LongPredicate} representing a fallback. The exception from this
     * {@code ThrowableLongPredicate} is ignored.
     *
     * @param fallback A {@code LongPredicate} to be applied if this one fails
     * @return A composed {@code LongPredicate} that applies this {@code ThrowableLongPredicate}, and if an error
     * occurred, applies the given {@code LongPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default LongPredicate fallbackTo(final LongPredicate fallback) {
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
     * Returns a composed {@link ThrowableLongPredicate} that applies this {@code ThrowableLongPredicate} to its input,
     * additionally performing the provided action to the resulting value. This method exists mainly to support
     * debugging.
     *
     * @param action A {@link Consumer} to be applied additionally to this {@code ThrowableLongPredicate}
     * @return A composed {@code ThrowableLongPredicate} that applies this {@code ThrowableLongPredicate}, additionally
     * performing the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableLongPredicate peek(final Consumer<? super Boolean> action) {
        Objects.requireNonNull(action);
        return value -> {
            final boolean ret = test(value);
            action.accept(ret);
            return ret;
        };
    }

    /**
     * Returns a composed {@link LongPredicate} that applies this {@link ThrowableLongPredicate} to its input, and if
     * an error occurred, returns {@code true}. The exception from this {@code ThrowableLongPredicate} is ignored.
     *
     * @return A composed {@code LongPredicate} that applies this {@code ThrowableLongPredicate}, and if an error
     * occurred, returns {@code true}.
     */
    default LongPredicate orReturnTrue() {
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return true;
            }
        };
    }

    /**
     * Returns a composed {@link LongPredicate} that applies this {@link ThrowableLongPredicate} to its input, and if
     * an error occurred, returns {@code false}. The exception from this {@code ThrowableLongPredicate} is ignored.
     *
     * @return A composed {@code LongPredicate} that applies this {@code ThrowableLongPredicate}, and if an error
     * occurred, returns {@code false}.
     */
    default LongPredicate orReturnFalse() {
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return false;
            }
        };
    }
}
