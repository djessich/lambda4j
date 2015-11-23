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
package at.gridtec.lambda4j.operators.unary;

import at.gridtec.lambda4j.supplier.ByteSupplier;
import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;

/**
 * This functional interface implements a {@link ByteUnaryOperator} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByteThrows(byte)}.
 *
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableByteUnaryOperator extends ByteUnaryOperator {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableByteUnaryOperator}. This is a convenience
     * method in case the given {@link ThrowableByteUnaryOperator} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableByteUnaryOperator} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableByteUnaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableByteUnaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableByteUnaryOperator wrap(final ThrowableByteUnaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableByteUnaryOperator} from the given {@link ByteUnaryOperator}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code ByteUnaryOperator} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableByteUnaryOperator} from the given {@code ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableByteUnaryOperator from(final ByteUnaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsByte;
    }

    /**
     * Creates a {@link ThrowableByteUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableByteUnaryOperator} which always returns a given value.
     */
    static ThrowableByteUnaryOperator constant(byte ret) {
        return operand -> ret;
    }

    /**
     * The apply method for this {@link ByteUnaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param operand The argument for the operator
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    byte applyAsByteThrows(byte operand) throws Exception;

    /**
     * Overrides the {@link ByteUnaryOperator#applyAsByte(byte)} method by using a redefinition as default method. It
     * calls the {@link #applyAsByteThrows(byte)} method of this interface and catches the thrown {@link Exception}s
     * from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other throwable types are sneakily
     * thrown.
     *
     * @param operand The argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default byte applyAsByte(byte operand) {
        try {
            return applyAsByteThrows(operand);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableByteUnaryOperator} that applies this {@code ThrowableByteUnaryOperator} to its
     * input, and if an  error occurred, applies the given one. The exception from this {@code
     * ThrowableByteUnaryOperator} is ignored.
     *
     * @param other A {@code ThrowableByteUnaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableByteUnaryOperator} that applies this {@code ThrowableByteUnaryOperator}, and
     * if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableByteUnaryOperator orElse(final ThrowableByteUnaryOperator other) {
        Objects.requireNonNull(other);
        return operand -> {
            try {
                return applyAsByteThrows(operand);
            } catch (Exception ignored) {
                return other.applyAsByteThrows(operand);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableByteUnaryOperator} that applies this {@code ThrowableByteUnaryOperator} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableByteUnaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableByteUnaryOperator} that applies this {@code ThrowableByteUnaryOperator}, and
     * if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableByteUnaryOperator orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return operand -> {
            try {
                return applyAsByteThrows(operand);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that applies this {@link ThrowableByteUnaryOperator} to its input,
     * and if an error occurred, applies the given {@code ByteUnaryOperator} representing a fallback. The exception from
     * this {@code ThrowableByteUnaryOperator} is ignored.
     *
     * @param fallback A {@code ByteUnaryOperator} to be applied if this one fails
     * @return A composed {@code ByteUnaryOperator} that applies this {@code ThrowableByteUnaryOperator}, and if an
     * error occurred, applies the given {@code UnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ByteUnaryOperator fallbackTo(final ByteUnaryOperator fallback) {
        Objects.requireNonNull(fallback);
        return operand -> {
            try {
                return applyAsByteThrows(operand);
            } catch (Exception ignored) {
                return fallback.applyAsByte(operand);
            }
        };
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that applies this {@link ThrowableByteUnaryOperator} to its input,
     * and if an error occurred, returns the given value. The exception from this {@code ThrowableByteUnaryOperator} is
     * ignored.
     *
     * @param value The value to be returned if this {@code ThrowableByteUnaryOperator} fails
     * @return A composed {@code ByteUnaryOperator} that applies this {@code ThrowableByteUnaryOperator}, and if an
     * error occurred, returns the given value.
     */
    default ByteUnaryOperator orReturn(byte value) {
        return operand -> {
            try {
                return applyAsByteThrows(operand);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that applies this {@link ThrowableByteUnaryOperator} to its input,
     * and if an error occurred, returns the supplied value from the given {@link ByteSupplier}. The exception from this
     * {@code ThrowableByteUnaryOperator} is ignored.
     *
     * @param supplier A {@code ByteSupplier} to return a supplied value if this {@code ThrowableByteUnaryOperator}
     * fails
     * @return A composed {@code ByteUnaryOperator} that applies this {@code ThrowableByteUnaryOperator}, and if an
     * error occurred, the supplied value from the given {@code ByteSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ByteUnaryOperator orReturn(final ByteSupplier supplier) {
        Objects.requireNonNull(supplier);
        return operand -> {
            try {
                return applyAsByteThrows(operand);
            } catch (Exception ignored) {
                return supplier.getAsByte();
            }
        };
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that applies this {@link ThrowableByteUnaryOperator} to its input,
     * and if an error occurred, returns its value. The exception from this {@code ThrowableByteUnaryOperator} is
     * ignored.
     *
     * @return A composed {@code ByteUnaryOperator} that applies this {@code ThrowableByteUnaryOperator}, and if an
     * error occurred, returns its value.
     */
    default ByteUnaryOperator orReturnSelf() {
        return operand -> {
            try {
                return applyAsByteThrows(operand);
            } catch (Exception ignored) {
                return operand;
            }
        };
    }
}
