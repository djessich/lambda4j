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
package at.gridtec.lambda4j.operators.ternary;

import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.operators.unary.ThrowableFloatUnaryOperator;
import at.gridtec.lambda4j.supplier.FloatSupplier;
import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;

/**
 * This functional interface implements a {@link FloatTernaryOperator} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(float, float, float)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableFloatTernaryOperator extends FloatTernaryOperator {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableFloatTernaryOperator}. This is a
     * convenience method in case the given {@link ThrowableFloatTernaryOperator} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableFloatTernaryOperator} is returned as-is.
     *
     * @param lambda The {@code ThrowableFloatTernaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableFloatTernaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatTernaryOperator wrap(final ThrowableFloatTernaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableFloatTernaryOperator} from the given {@link FloatTernaryOperator}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code FloatTernaryOperator} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableFloatTernaryOperator} from the given {@code FloatTernaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatTernaryOperator from(final FloatTernaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsFloat;
    }

    /**
     * Creates a {@link ThrowableFloatTernaryOperator} which always returns a given value.
     *
     * @param r The return value for the constant
     * @return A {@code ThrowableFloatTernaryOperator} which always returns a given value.
     */
    static ThrowableFloatTernaryOperator constant(float r) {
        return (left, middle, right) -> r;
    }

    /**
     * Creates a {@link ThrowableFloatTernaryOperator} which uses the left parameter as argument for the given {@link
     * ThrowableFloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code ThrowableFloatTernaryOperator} which uses the left parameter as argument for the given
     * {@code ThrowableFloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatTernaryOperator onlyLeft(final ThrowableFloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(left);
    }

    /**
     * Creates a {@link ThrowableFloatTernaryOperator} which uses the middle parameter as argument for the given {@link
     * ThrowableFloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code ThrowableFloatTernaryOperator} which uses the middle parameter as argument for the given
     * {@code ThrowableFloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatTernaryOperator onlyMiddle(final ThrowableFloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(middle);
    }

    /**
     * Creates a {@link ThrowableFloatTernaryOperator} which uses the right parameter as argument for the given {@link
     * ThrowableFloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code ThrowableFloatTernaryOperator} which uses the right parameter as argument for the given
     * {@code ThrowableFloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatTernaryOperator onlyRight(final ThrowableFloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(right);
    }

    /**
     * The applyAsFloat method for this {@link FloatTernaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    float applyThrows(float left, float middle, float right) throws Exception;

    /**
     * Overrides the {@link FloatTernaryOperator#applyAsFloat(float, float, float)} method by using a redefinition as
     * default method. It calls the {@link #applyThrows(float, float, float)} method of this interface and catches the
     * thrown {@link Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other
     * throwable types are sneakily thrown.
     *
     * @param left The first argument for the operator
     * @param middle The second argument for the operator
     * @param right The third argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default float applyAsFloat(float left, float middle, float right) {
        try {
            return applyThrows(left, middle, right);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableFloatTernaryOperator} that applies this {@code ThrowableFloatTernaryOperator}
     * to its input, and if an  error occurred, applies the given one. The exception from this {@code
     * ThrowableFloatTernaryOperator} is ignored.
     *
     * @param other A {@code ThrowableFloatTernaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableFloatTernaryOperator} that applies this {@code ThrowableFloatTernaryOperator},
     * and if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableFloatTernaryOperator orElse(final ThrowableFloatTernaryOperator other) {
        Objects.requireNonNull(other);
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return other.applyThrows(left, middle, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableFloatTernaryOperator} that applies this {@code ThrowableFloatTernaryOperator}
     * to its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableFloatTernaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableFloatTernaryOperator} that applies this {@code ThrowableFloatTernaryOperator},
     * and if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableFloatTernaryOperator orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that applies this {@link ThrowableFloatTernaryOperator} to its
     * input, and if an error occurred, applies the given {@code FloatTernaryOperator} representing a fallback. The
     * exception from this {@code ThrowableFloatTernaryOperator} is ignored.
     *
     * @param fallback A {@code FloatTernaryOperator} to be applied if this one fails
     * @return A composed {@code FloatTernaryOperator} that applies this {@code ThrowableFloatTernaryOperator}, and if
     * an error occurred, applies the given {@code FloatTernaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatTernaryOperator fallbackTo(final FloatTernaryOperator fallback) {
        Objects.requireNonNull(fallback);
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return fallback.applyAsFloat(left, middle, right);
            }
        };
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that applies this {@link ThrowableFloatTernaryOperator} to its
     * input, and if an error occurred, returns the given value. The exception from this {@code
     * ThrowableFloatTernaryOperator} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableFloatTernaryOperator} fails
     * @return A composed {@code FloatTernaryOperator} that applies this {@code ThrowableFloatTernaryOperator}, and if
     * an error occurred, returns the given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatTernaryOperator orReturn(float value) {
        Objects.requireNonNull(value);
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that applies this {@link ThrowableFloatTernaryOperator} to its
     * input, and if an error occurred, returns the supplied value from the given {@link FloatSupplier}. The exception
     * from this {@code ThrowableFloatTernaryOperator} is ignored.
     *
     * @param supplier A {@code FloatSupplier} to return a supplied value if this {@code ThrowableFloatTernaryOperator}
     * fails
     * @return A composed {@code FloatTernaryOperator} that applies this {@code ThrowableFloatTernaryOperator}, and if
     * an error occurred, the supplied value from the given {@code FloatSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatTernaryOperator orReturn(final FloatSupplier supplier) {
        Objects.requireNonNull(supplier);
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return supplier.getAsFloat();
            }
        };
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that applies this {@link ThrowableFloatTernaryOperator} to its
     * input, and if an error occurred, returns the left value from this operator. The exception from this {@code
     * ThrowableFloatTernaryOperator} is ignored.
     *
     * @return A composed {@code FloatTernaryOperator} that applies this {@code ThrowableFloatTernaryOperator}, and if
     * an error occurred, returns the left value from this operator.
     */
    default FloatTernaryOperator orReturnLeft() {
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return left;
            }
        };
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that applies this {@link ThrowableFloatTernaryOperator} to its
     * input, and if an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableFloatTernaryOperator} is ignored.
     *
     * @return A composed {@code FloatTernaryOperator} that applies this {@code ThrowableFloatTernaryOperator}, and if
     * an error occurred, returns the right value from this operator.
     */
    default FloatTernaryOperator orReturnMiddle() {
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return middle;
            }
        };
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that applies this {@link ThrowableFloatTernaryOperator} to its
     * input, and if an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableFloatTernaryOperator} is ignored.
     *
     * @return A composed {@code FloatTernaryOperator} that applies this {@code ThrowableFloatTernaryOperator}, and if
     * an error occurred, returns the right value from this operator.
     */
    default FloatTernaryOperator orReturnRight() {
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return right;
            }
        };
    }
}
