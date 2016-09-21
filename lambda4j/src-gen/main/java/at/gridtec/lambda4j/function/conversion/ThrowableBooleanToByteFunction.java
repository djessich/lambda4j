/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.function.conversion;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableBooleanConsumer;
import at.gridtec.lambda4j.consumer.ThrowableByteConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableBooleanFunction;
import at.gridtec.lambda4j.function.ThrowableByteFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.to.ThrowableToByteFunction;
import at.gridtec.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableByteUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableBytePredicate;
import at.gridtec.lambda4j.predicate.ThrowableCharPredicate;
import at.gridtec.lambda4j.predicate.ThrowableDoublePredicate;
import at.gridtec.lambda4j.predicate.ThrowableFloatPredicate;
import at.gridtec.lambda4j.predicate.ThrowableIntPredicate;
import at.gridtec.lambda4j.predicate.ThrowableLongPredicate;
import at.gridtec.lambda4j.predicate.ThrowablePredicate;
import at.gridtec.lambda4j.predicate.ThrowableShortPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an operation that accepts one {@code boolean}-valued input argument and produces a {@code byte}-valued
 * result which is able to throw any {@link Throwable}. This is a primitive specialization of {@link
 * ThrowableFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByteThrows(boolean)}.
 *
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBooleanToByteFunction<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableBooleanToByteFunction} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBooleanToByteFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <X extends Throwable> ThrowableBooleanToByteFunction<X> of(
            @Nonnull final ThrowableBooleanToByteFunction<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBooleanToByteFunction} with the given argument and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ThrowableBooleanToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <X extends Throwable> byte call(@Nonnull final ThrowableBooleanToByteFunction<? extends X> function,
            boolean value) throws X {
        Objects.requireNonNull(function);
        return function.applyAsByteThrows(value);
    }

    /**
     * Creates a {@link ThrowableBooleanToByteFunction} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableBooleanToByteFunction} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableBooleanToByteFunction<X> constant(byte ret) {
        return (value) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    byte applyAsByteThrows(boolean value) throws X;

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ThrowableToByteFunction} that first applies the {@code before} predicate to its input,
     * and then applies this function to the result.
     *
     * @param <A> The type of the argument to the given predicate, and of composed function
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ThrowableToByteFunction} that first applies the {@code before} predicate to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ThrowableToByteFunction<A, X> compose(
            @Nonnull final ThrowablePredicate<? super A, ? extends X> before) {
        Objects.requireNonNull(before);
        return (a) -> applyAsByteThrows(before.testThrows(a));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToByteFunction} that first applies the {@code before} operator to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code ThrowableBooleanToByteFunction} that first applies the {@code before} operator to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanToByteFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByteThrows(before.applyAsBooleanThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableByteUnaryOperator} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ThrowableByteUnaryOperator} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteUnaryOperator<X> composeFromByte(@Nonnull final ThrowableBytePredicate<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByteThrows(before.testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableCharToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ThrowableCharToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharToByteFunction<X> composeFromChar(@Nonnull final ThrowableCharPredicate<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByteThrows(before.testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ThrowableDoubleToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleToByteFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoublePredicate<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByteThrows(before.testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableFloatToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ThrowableFloatToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatToByteFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatPredicate<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByteThrows(before.testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ThrowableIntToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntToByteFunction<X> composeFromInt(@Nonnull final ThrowableIntPredicate<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByteThrows(before.testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableLongToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ThrowableLongToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongToByteFunction<X> composeFromLong(@Nonnull final ThrowableLongPredicate<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByteThrows(before.testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableShortToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ThrowableShortToByteFunction} that first applies the {@code before} predicate to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortToByteFunction<X> composeFromShort(
            @Nonnull final ThrowableShortPredicate<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByteThrows(before.testThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBooleanFunction<S, X> andThen(
            @Nonnull final ThrowableByteFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyThrows(applyAsByteThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBooleanUnaryOperator} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableBooleanUnaryOperator} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanUnaryOperator<X> andThenToBoolean(
            @Nonnull final ThrowableBytePredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.testThrows(applyAsByteThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableBooleanToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBooleanToByteFunction<X> andThenToByte(
            @Nonnull final ThrowableByteUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsByteThrows(applyAsByteThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBooleanToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBooleanToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableByteToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsCharThrows(applyAsByteThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBooleanToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBooleanToDoubleFunction<X> andThenToDouble(
            @Nonnull final ThrowableByteToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsDoubleThrows(applyAsByteThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBooleanToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBooleanToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableByteToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsFloatThrows(applyAsByteThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBooleanToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBooleanToIntFunction<X> andThenToInt(
            @Nonnull final ThrowableByteToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsIntThrows(applyAsByteThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBooleanToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBooleanToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableByteToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsLongThrows(applyAsByteThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBooleanToShortFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBooleanToShortFunction<X> andThenToShort(
            @Nonnull final ThrowableByteToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsShortThrows(applyAsByteThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableBooleanConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableByteConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBooleanConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBooleanConsumer<X> consume(@Nonnull final ThrowableByteConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.acceptThrows(applyAsByteThrows(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableBooleanToByteFunction}. Whenever it is called, the
     * mapping between the input parameter and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableBooleanToByteFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableBooleanToByteFunction<X> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Boolean, Byte> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableBooleanToByteFunction<X> & Memoized) (value) -> {
                final byte returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, ThrowableFunction.of(this::applyAsByteThrows));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableFunction} which represents this {@link ThrowableBooleanToByteFunction}.
     * Thereby the primitive input argument for this function is autoboxed.
     *
     * @return A composed {@code ThrowableFunction} which represents this {@code ThrowableBooleanToByteFunction}.
     */
    @Nonnull
    default ThrowableFunction<Boolean, Byte, X> boxed() {
        return this::applyAsByteThrows;
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is
     * nested (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown
     * throwables message and the thrown throwable itself.
     *
     * @return A composed {@code BooleanToByteFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default BooleanToByteFunction nest() {
        return (value) -> {
            try {
                return this.applyAsByteThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed function behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this function in the returned composed
     * function by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing function.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing function variant of this throwable function, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed function. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed function. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed function is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed function, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed function.
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
     *         final Class<?> sneakyThrowingFunctionalInterface("some illegal class name");
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link BooleanToByteFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default BooleanToByteFunction sneakyThrow() {
        return (value) -> {
            try {
                return this.applyAsByteThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}