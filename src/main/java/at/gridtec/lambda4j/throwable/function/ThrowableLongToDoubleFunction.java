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
import java.util.function.LongToDoubleFunction;

/**
 * This functional interface implements a {@link LongToDoubleFunction} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDoubleThrows(long)}.
 *
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableLongToDoubleFunction extends LongToDoubleFunction {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableLongToDoubleFunction}. This is a
     * convenience method in case the given {@link ThrowableLongToDoubleFunction} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableLongToDoubleFunction} is returned as-is.
     *
     * @param lambda The {@code ThrowableLongToDoubleFunction} which should be returned as-is.
     * @return The given {@code ThrowableLongToDoubleFunction} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongToDoubleFunction wrap(final ThrowableLongToDoubleFunction lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableLongToDoubleFunction} from the given {@link LongToDoubleFunction}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code LongToDoubleFunction} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableLongToDoubleFunction} from the given {@code LongToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongToDoubleFunction from(final LongToDoubleFunction lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsDouble;
    }

    /**
     * Creates a {@link ThrowableLongToDoubleFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableLongToDoubleFunction} which always returns a given value.
     */
    static ThrowableLongToDoubleFunction constant(double ret) {
        return value -> ret;
    }

    /**
     * The apply method for this {@link LongToDoubleFunction} which is able to throw any {@link Exception} type.
     *
     * @param value The argument for the function
     * @return The return value from the function, which is its result.
     * @throws Exception Any exception from this functions action
     */
    double applyAsDoubleThrows(long value) throws Exception;

    /**
     * Overrides the {@link LongToDoubleFunction#applyAsDouble(long)} method by using a redefinition as default method.
     * It calls the {@link #applyAsDoubleThrows(long)} method of this interface and catches the thrown {@link
     * Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types
     * are sneakily thrown.
     *
     * @param value The argument for the function
     * @return The return value from the function, which is its result.
     * @see at.gridtec.lambda4j.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default double applyAsDouble(long value) {
        try {
            return applyAsDoubleThrows(value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableLongToDoubleFunction} that applies this {@code ThrowableLongToDoubleFunction}
     * to its input, and if an error occurred, applies the given one. The exception from this {@code
     * ThrowableLongToDoubleFunction} is ignored.
     *
     * @param other A {@code ThrowableLongToDoubleFunction} to be applied if this one fails
     * @return A composed {@code ThrowableLongToDoubleFunction} that applies this {@code ThrowableLongToDoubleFunction},
     * and if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableLongToDoubleFunction orElse(final ThrowableLongToDoubleFunction other) {
        Objects.requireNonNull(other);
        return value -> {
            try {
                return applyAsDoubleThrows(value);
            } catch (Exception ignored) {
                return other.applyAsDoubleThrows(value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableLongToDoubleFunction} that applies this {@code ThrowableLongToDoubleFunction}
     * to its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableLongToDoubleFunction} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableLongToDoubleFunction} that applies this {@code ThrowableLongToDoubleFunction},
     * and if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableLongToDoubleFunction orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return value -> {
            try {
                return applyAsDouble(value);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link LongToDoubleFunction} that applies this {@link ThrowableLongToDoubleFunction} to its
     * input, and if an error occurred, applies the given {@code LongToDoubleFunction} representing a fallback. The
     * exception from this {@code ThrowableLongToDoubleFunction} is ignored.
     *
     * @param fallback A {@code LongToDoubleFunction} to be applied if this one fails
     * @return A composed {@code LongToDoubleFunction} that applies this {@code ThrowableLongToDoubleFunction}, and if
     * an error occurred, applies the given {@code LongToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default LongToDoubleFunction fallbackTo(final LongToDoubleFunction fallback) {
        Objects.requireNonNull(fallback);
        return value -> {
            try {
                return applyAsDoubleThrows(value);
            } catch (Exception ignored) {
                return fallback.applyAsDouble(value);
            }
        };
    }

    /**
     * Returns a composed {@link LongToDoubleFunction} that applies this {@link ThrowableLongToDoubleFunction} to its
     * input, and if an error occurred, returns the given value. The exception from this {@code
     * ThrowableLongToDoubleFunction} is ignored.
     *
     * @param retVal The value to be returned if this {@code ThrowableLongToDoubleFunction} fails
     * @return A composed {@code LongToDoubleFunction} that applies this {@code ThrowableLongToDoubleFunction}, and if
     * an error occurred, returns the given value.
     */
    default LongToDoubleFunction orReturn(double retVal) {
        return value -> {
            try {
                return applyAsDoubleThrows(value);
            } catch (Exception ignored) {
                return retVal;
            }
        };
    }

    /**
     * Returns a composed {@link LongToDoubleFunction} that applies this {@link ThrowableLongToDoubleFunction} to its
     * input, and if an error occurred, returns the supplied value from the given {@link DoubleSupplier}. The exception
     * from this {@code ThrowableLongToDoubleFunction} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableLongToDoubleFunction}
     * fails
     * @return A composed {@code LongToDoubleFunction} that applies this {@code ThrowableLongToDoubleFunction}, and if
     * an error occurred, the supplied value from the given {@code DoubleSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default LongToDoubleFunction orReturn(final DoubleSupplier supplier) {
        Objects.requireNonNull(supplier);
        return value -> {
            try {
                return applyAsDoubleThrows(value);
            } catch (Exception ignored) {
                return supplier.getAsDouble();
            }
        };
    }
}
