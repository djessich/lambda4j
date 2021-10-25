/*
 * Copyright (c) 2021 The lambda4j authors.
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

package org.lambda4j.operator.unary;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableIntConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.ThrowableIntFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToIntFunction;
import org.lambda4j.function.conversion.ThrowableByteToIntFunction;
import org.lambda4j.function.conversion.ThrowableCharToIntFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToIntFunction;
import org.lambda4j.function.conversion.ThrowableFloatToIntFunction;
import org.lambda4j.function.conversion.ThrowableIntToByteFunction;
import org.lambda4j.function.conversion.ThrowableIntToCharFunction;
import org.lambda4j.function.conversion.ThrowableIntToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableIntToFloatFunction;
import org.lambda4j.function.conversion.ThrowableIntToLongFunction;
import org.lambda4j.function.conversion.ThrowableIntToShortFunction;
import org.lambda4j.function.conversion.ThrowableLongToIntFunction;
import org.lambda4j.function.conversion.ThrowableShortToIntFunction;
import org.lambda4j.function.to.ThrowableToIntFunction;
import org.lambda4j.predicate.ThrowableIntPredicate;

/**
 * Represents an operation that accepts one {@code int}-valued input argument and produces a {@code int}-valued result
 * which is able to throw any {@link Throwable}. This is a primitive specialization of {@link ThrowableUnaryOperator}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsIntThrows(int)}.
 *
 * @param <X> The type of the throwable to be thrown by this operator
 * @apiNote This is a throwable JDK lambda.
 * @see ThrowableUnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableIntUnaryOperator<X extends Throwable> extends Lambda, IntUnaryOperator {

    /**
     * Constructs a {@link ThrowableIntUnaryOperator} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableIntUnaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <X extends Throwable> ThrowableIntUnaryOperator<X> of(
            @Nullable ThrowableIntUnaryOperator<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableIntUnaryOperator} with the given argument and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param operator The operator to be called
     * @param value The argument to the operator
     * @return The result from the given {@code ThrowableIntUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this operators action
     */
    static <X extends Throwable> int call(@Nonnull ThrowableIntUnaryOperator<? extends X> operator,
            int value) throws X {
        Objects.requireNonNull(operator);
        return operator.applyAsIntThrows(value);
    }

    /**
     * Returns a {@link ThrowableIntUnaryOperator} that always returns its input argument.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @return A {@code ThrowableIntUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static <X extends Throwable> ThrowableIntUnaryOperator<X> identity() {
        return value -> value;
    }

    /**
     * Creates a {@link ThrowableIntUnaryOperator} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this operator
     * @param ret The return value for the constant
     * @return A {@code ThrowableIntUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableIntUnaryOperator<X> constant(int ret) {
        return value -> ret;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param value The argument to the operator
     * @return The return value from the operator, which is its result.
     * @throws X Any throwable from this operators action
     */
    int applyAsIntThrows(int value) throws X;

    /**
     * Applies this operator to the given argument.
     *
     * @param value The argument to the operator
     * @return The return value from the operator, which is its result.
     * @apiNote This method mainly exists to use this {@link ThrowableIntUnaryOperator} in JRE specific methods only
     * accepting {@link IntUnaryOperator}. If this operator should be applied, then the {@link #applyAsIntThrows(int)}
     * method should be used.
     * @apiNote Overrides the {@link IntUnaryOperator#applyAsInt(int)} method by using a redefinition as default method.
     * This implementation calls the {@link #applyAsIntThrows(int)} method of this function and catches the eventually
     * thrown {@link Throwable} from it. If it is of type {@link RuntimeException} or {@link Error} it is rethrown as
     * is. Other {@code Throwable} types are wrapped in a {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default int applyAsInt(int value) {
        return nest().applyAsInt(value);
    }

    /**
     * Returns the number of arguments for this operator.
     *
     * @return The number of arguments for this operator.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ThrowableToIntFunction} that first applies the {@code before} function to its input,
     * and then applies this operator to the result.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ThrowableToIntFunction} that first applies the {@code before} function to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ThrowableToIntFunction<A, X> compose(
            @Nonnull ThrowableToIntFunction<? super A, ? extends X> before) {
        Objects.requireNonNull(before);
        return a -> applyAsIntThrows(before.applyAsIntThrows(a));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ThrowableBooleanToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanToIntFunction<X> composeFromBoolean(
            @Nonnull ThrowableBooleanToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsIntThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableByteToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ThrowableByteToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteToIntFunction<X> composeFromByte(
            @Nonnull ThrowableByteToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsIntThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableCharToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ThrowableCharToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharToIntFunction<X> composeFromChar(
            @Nonnull ThrowableCharToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsIntThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ThrowableDoubleToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleToIntFunction<X> composeFromDouble(
            @Nonnull ThrowableDoubleToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsIntThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableFloatToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ThrowableFloatToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatToIntFunction<X> composeFromFloat(
            @Nonnull ThrowableFloatToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsIntThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntUnaryOperator} that first applies the {@code before} operator to its input,
     * and then applies this operator to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code ThrowableIntUnaryOperator} that first applies the {@code before} operator to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntUnaryOperator<X> composeFromInt(@Nonnull ThrowableIntUnaryOperator<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsIntThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableLongToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ThrowableLongToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongToIntFunction<X> composeFromLong(
            @Nonnull ThrowableLongToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsIntThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableShortToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ThrowableShortToIntFunction} that first applies the {@code before} function to its
     * input, and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortToIntFunction<X> composeFromShort(
            @Nonnull ThrowableShortToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsIntThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableIntFunction<S, X> andThen(
            @Nonnull ThrowableIntFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyThrows(applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntPredicate} that first applies this operator to its input, and then applies
     * the {@code after} predicate to the result. This method is just convenience, to provide the ability to transform
     * this primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code ThrowableIntPredicate} that first applies this operator to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableIntPredicate<X> andThenToBoolean(@Nonnull ThrowableIntPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.testThrows(applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableIntToByteFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableIntToByteFunction<X> andThenToByte(@Nonnull ThrowableIntToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByteThrows(applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableIntToCharFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableIntToCharFunction<X> andThenToChar(@Nonnull ThrowableIntToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsCharThrows(applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableIntToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableIntToDoubleFunction<X> andThenToDouble(
            @Nonnull ThrowableIntToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDoubleThrows(applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableIntToFloatFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableIntToFloatFunction<X> andThenToFloat(
            @Nonnull ThrowableIntToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloatThrows(applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntUnaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code int}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ThrowableIntUnaryOperator} that first applies this operator to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntUnaryOperator<X> andThenToInt(@Nonnull ThrowableIntUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsIntThrows(applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableIntToLongFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableIntToLongFunction<X> andThenToLong(@Nonnull ThrowableIntToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLongThrows(applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ThrowableIntToShortFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableIntToShortFunction<X> andThenToShort(
            @Nonnull ThrowableIntToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShortThrows(applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntConsumer} that fist applies this operator to its input, and then consumes
     * the result using the given {@link ThrowableIntConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableIntConsumer} that first applies this operator to its input, and then consumes
     * the result using the given {@code ThrowableIntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableIntConsumer<X> consume(@Nonnull ThrowableIntConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.acceptThrows(applyAsIntThrows(value));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ThrowableIntUnaryOperator<X> reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableIntUnaryOperator}. Whenever it is called, the
     * mapping between the input parameter and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableIntUnaryOperator}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableIntUnaryOperator<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Integer, Integer> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ThrowableIntUnaryOperator<X> & Memoized) value -> {
                int returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, ThrowableFunction.of(this::applyAsIntThrows));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableUnaryOperator} which represents this {@link ThrowableIntUnaryOperator}.
     * Thereby the primitive input argument for this operator is autoboxed. This method provides the possibility to use
     * this {@code ThrowableIntUnaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableUnaryOperator} which represents this {@code ThrowableIntUnaryOperator}.
     */
    @Nonnull
    default ThrowableUnaryOperator<Integer, X> boxed() {
        return this::applyAsIntThrows;
    }

    /**
     * Returns a composed {@link IntUnaryOperator2} that applies this operator to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link IntUnaryOperator2} that applies this operator to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default IntUnaryOperator2 nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link IntUnaryOperator2} that applies this operator to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link IntUnaryOperator2} that applies this operator to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default IntUnaryOperator2 nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link IntUnaryOperator2} that first applies this operator to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same argument of this
     * operator.
     *
     * @param recover The operation to apply if this operator throws a {@code Throwable}
     * @return A composed {@link IntUnaryOperator2} that first applies this operator to its input, and then applies the
     * {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing operator is {@code null}
     * @implSpec The implementation checks that the returned enclosing operator from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default IntUnaryOperator2 recover(@Nonnull Function<? super Throwable, ? extends IntUnaryOperator> recover) {
        Objects.requireNonNull(recover);
        return value -> {
            try {
                return applyAsIntThrows(value);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                IntUnaryOperator operator = recover.apply(throwable);
                Objects.requireNonNull(operator, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return operator.applyAsInt(value);
            }
        };
    }

    /**
     * Returns a composed {@link IntUnaryOperator2} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed operator behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this operator in the returned composed
     * operator by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing operator.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing operator variant of this throwable operator, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed operator. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed operator. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed operator is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed operator, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed operator.
     * <p>
     * With all that mentioned, the following example will demonstrate this methods correct use:
     * <pre>{@code
     * // when called with illegal value ClassNotFoundException is thrown
     * public Class<?> sneakyThrowingFunctionalInterface(final String className) throws ClassNotFoundException {
     *     return ThrowableFunction.of(Class::forName) // create the correct throwable functional interface
     *                .sneakyThrow() // create a non-throwable variant which is able to sneaky throw (this method)
     *                .apply(className); // apply non-throwable variant -> may sneaky throw a throwable
     * }
     *
     * // call the the method which surround the sneaky throwing functional interface
     * public void callingMethod() {
     *     try {
     *         final Class<?> clazz = sneakyThrowingFunctionalInterface("some illegal class name");
     *         // ... do something with clazz ...
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link IntUnaryOperator2} that applies this operator to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default IntUnaryOperator2 sneakyThrow() {
        return value -> {
            try {
                return applyAsIntThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
