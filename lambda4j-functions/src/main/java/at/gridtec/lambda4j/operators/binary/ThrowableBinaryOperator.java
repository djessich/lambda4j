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
package at.gridtec.lambda4j.operators.binary;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * This functional interface implements a {@link BinaryOperator} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(Object, Object)}.
 *
 * @param <T> The type of the arguments and the return value for this operator
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBinaryOperator<T> extends BinaryOperator<T> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableBinaryOperator}. This is a convenience
     * method in case the given {@link ThrowableBinaryOperator} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableBinaryOperator} is
     * returned as-is.
     *
     * @param <T> The type of the arguments and the return value for this operator
     * @param lambda The {@code ThrowableBinaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableBinaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableBinaryOperator<T> wrap(final ThrowableBinaryOperator<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableBinaryOperator} from the given {@link BinaryOperator}. This method is just convenience
     * to provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param <T> The type of the arguments and the return value for this operator
     * @param lambda A {@code BinaryOperator} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableBinaryOperator} from the given {@code BinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableBinaryOperator<T> from(final BinaryOperator<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::apply;
    }

    /**
     * Creates a {@link ThrowableBinaryOperator} which always returns a given value.
     *
     * @param <T> The type of the arguments and the return value for this operator
     * @param r The return value for the constant
     * @return A {@code ThrowableBinaryOperator} which always returns a given value.
     */
    static <T> ThrowableBinaryOperator<T> constant(T r) {
        return (t, u) -> r;
    }

    /**
     * Returns a {@link ThrowableBinaryOperator} which returns the lesser of two elements, according to the specified
     * {@code Comparator}.
     *
     * @param <T> The type of the arguments and the return value for this operator
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ThrowableBinaryOperator} which returns the lesser of two elements, according to the supplied
     * {@code Comparator}
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableBinaryOperator<T> minBy(final Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * Returns a {@link ThrowableBinaryOperator} which returns the greater of two elements, according to the specified
     * {@code Comparator}.
     *
     * @param <T> The type of the arguments and the return value for this operator
     * @param comparator A {@code Comparator} for comparing the operators operands
     * @return A {@code ThrowableBinaryOperator} which returns the greater of two elements, according to the supplied
     * {@code Comparator}
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableBinaryOperator<T> maxBy(final Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    /**
     * Creates a {@link BinaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * UnaryOperator}.
     *
     * @param <T> The type of argument to the operator
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code BinaryOperator} which uses the {@code left} parameter as argument for the given {@code
     * UnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> BinaryOperator<T> onlyLeft(final UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.apply(left);
    }

    /**
     * Creates a {@link BinaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * UnaryOperator}.
     *
     * @param <T> The type of argument to the operator
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code BinaryOperator} which uses the {@code right} parameter as argument for the given {@code
     * UnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> BinaryOperator<T> onlyRight(final UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.apply(right);
    }

    /**
     * The apply method for this {@link BinaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param t The first argument for the operator
     * @param u The second argument for the operator
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    T applyThrows(T t, T u) throws Exception;

    /**
     * Overrides the {@link BinaryOperator#apply(Object, Object)} method by using a redefinition as default method. It
     * calls the {@link #applyThrows(Object, Object)} method of this interface and catches the thrown {@link Exception}s
     * from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other throwable types are sneakily
     * thrown.
     *
     * @param t The first argument for the operator
     * @param u The second argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default T apply(T t, T u) {
        try {
            return applyThrows(t, u);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableBinaryOperator} that applies this {@code ThrowableBinaryOperator} to its
     * input, and if an  error occurred, applies the given one. The exception from this {@code ThrowableBinaryOperator}
     * is ignored.
     *
     * @param other A {@code ThrowableBinaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableBinaryOperator} that applies this {@code ThrowableBinaryOperator}, and if an
     * error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableBinaryOperator<T> orElse(final ThrowableBinaryOperator<T> other) {
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
     * Returns a composed {@link ThrowableBinaryOperator} that applies this {@code ThrowableBinaryOperator} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableBinaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableBinaryOperator} that applies this {@code ThrowableBinaryOperator}, and if an
     * error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableBinaryOperator<T> orThrow(Class<X> clazz) {
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
     * Returns a composed {@link BinaryOperator} that applies this {@link ThrowableBinaryOperator} to its input, and if
     * an error occurred, applies the given {@code BinaryOperator} representing a fallback. The exception from this
     * {@code ThrowableBinaryOperator} is ignored.
     *
     * @param fallback A {@code BinaryOperator} to be applied if this one fails
     * @return A composed {@code BinaryOperator} that applies this {@code ThrowableBinaryOperator}, and if an error
     * occurred, applies the given {@code BinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default BinaryOperator<T> fallbackTo(final BinaryOperator<T> fallback) {
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
     * Returns a composed {@link BinaryOperator} that applies this {@link ThrowableBinaryOperator} to its input, and if
     * an error occurred, returns the given value. The exception from this {@code ThrowableBinaryOperator} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableBinaryOperator} fails
     * @return A composed {@code BinaryOperator} that applies this {@code ThrowableBinaryOperator}, and if an error
     * occurred, returns the given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default BinaryOperator<T> orReturn(final T value) {
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
     * Returns a composed {@link BinaryOperator} that applies this {@link ThrowableBinaryOperator} to its input, and if
     * an error occurred, returns the supplied value from the given {@link Supplier}. The exception from this {@code
     * ThrowableBinaryOperator} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableBinaryOperator} fails
     * @return A composed {@code BinaryOperator} that applies this {@code ThrowableBinaryOperator}, and if an error
     * occurred, the supplied value from the given {@code Supplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default BinaryOperator<T> orReturn(final Supplier<? extends T> supplier) {
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
     * Returns a composed {@link BinaryOperator} that applies this {@link ThrowableBinaryOperator} to its input, and if
     * an error occurred, returns the left value from this operator. The exception from this {@code
     * ThrowableBinaryOperator} is ignored.
     *
     * @return A composed {@code BinaryOperator} that applies this {@code ThrowableBinaryOperator}, and if an error
     * occurred, returns the left value from this operator.
     */
    default BinaryOperator<T> orReturnLeft() {
        return (t, u) -> {
            try {
                return applyThrows(t, u);
            } catch (Exception ignored) {
                return t;
            }
        };
    }

    /**
     * Returns a composed {@link BinaryOperator} that applies this {@link ThrowableBinaryOperator} to its input, and if
     * an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableBinaryOperator} is ignored.
     *
     * @return A composed {@code BinaryOperator} that applies this {@code ThrowableBinaryOperator}, and if an error
     * occurred, returns the right value from this operator.
     */
    default BinaryOperator<T> orReturnRight() {
        return (t, u) -> {
            try {
                return applyThrows(t, u);
            } catch (Exception ignored) {
                return u;
            }
        };
    }
}