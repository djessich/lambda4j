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
import at.gridtec.lambda4j.consumer.ThrowableDoubleConsumer;
import at.gridtec.lambda4j.consumer.ThrowableShortConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableDoubleFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.ThrowableShortFunction;
import at.gridtec.lambda4j.function.to.ThrowableToDoubleFunction;
import at.gridtec.lambda4j.function.to.ThrowableToShortFunction;
import at.gridtec.lambda4j.operator.unary.ThrowableDoubleUnaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableShortUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableDoublePredicate;
import at.gridtec.lambda4j.predicate.ThrowableShortPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an operation that accepts one {@code double}-valued input argument and produces a {@code short}-valued
 * result which is able to throw any {@link Throwable}. This is a primitive specialization of {@link
 * ThrowableFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShortThrows(double)}.
 *
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableDoubleToShortFunction<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableDoubleToShortFunction} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableDoubleToShortFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableDoubleToShortFunction<X> of(
            @Nullable final ThrowableDoubleToShortFunction<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableDoubleToShortFunction} with the given argument and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ThrowableDoubleToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <X extends Throwable> short call(@Nonnull final ThrowableDoubleToShortFunction<? extends X> function,
            double value) throws X {
        Objects.requireNonNull(function);
        return function.applyAsShortThrows(value);
    }

    /**
     * Creates a {@link ThrowableDoubleToShortFunction} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableDoubleToShortFunction} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableDoubleToShortFunction<X> constant(short ret) {
        return (value) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    short applyAsShortThrows(double value) throws X;

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
     * Returns a composed {@link ThrowableToShortFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableToShortFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ThrowableToShortFunction<A, X> compose(
            @Nonnull final ThrowableToDoubleFunction<? super A, ? extends X> before) {
        Objects.requireNonNull(before);
        return (a) -> applyAsShortThrows(before.applyAsDoubleThrows(a));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableBooleanToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanToShortFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableByteToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableByteToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteToShortFunction<X> composeFromByte(
            @Nonnull final ThrowableByteToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableCharToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableCharToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharToShortFunction<X> composeFromChar(
            @Nonnull final ThrowableCharToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToShortFunction} that first applies the {@code before} operator to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code ThrowableDoubleToShortFunction} that first applies the {@code before} operator to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleToShortFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoubleUnaryOperator<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableFloatToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableFloatToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatToShortFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableIntToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntToShortFunction<X> composeFromInt(
            @Nonnull final ThrowableIntToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableLongToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableLongToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongToShortFunction<X> composeFromLong(
            @Nonnull final ThrowableLongToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableShortUnaryOperator} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableShortUnaryOperator} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortUnaryOperator<X> composeFromShort(
            @Nonnull final ThrowableShortToDoubleFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsDoubleThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableDoubleFunction<S, X> andThen(
            @Nonnull final ThrowableShortFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoublePredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableDoublePredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableDoublePredicate<X> andThenToBoolean(@Nonnull final ThrowableShortPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.testThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableDoubleToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableDoubleToByteFunction<X> andThenToByte(
            @Nonnull final ThrowableShortToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsByteThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableDoubleToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableDoubleToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableShortToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsCharThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleUnaryOperator} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableDoubleUnaryOperator} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleUnaryOperator<X> andThenToDouble(
            @Nonnull final ThrowableShortToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsDoubleThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableDoubleToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableDoubleToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableShortToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsFloatThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableDoubleToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableDoubleToIntFunction<X> andThenToInt(
            @Nonnull final ThrowableShortToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsIntThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableDoubleToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableDoubleToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableShortToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsLongThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableDoubleToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableDoubleToShortFunction<X> andThenToShort(
            @Nonnull final ThrowableShortUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsShortThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableShortConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableDoubleConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableDoubleConsumer<X> consume(@Nonnull final ThrowableShortConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.acceptThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableDoubleToShortFunction}. Whenever it is called, the
     * mapping between the input parameter and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableDoubleToShortFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableDoubleToShortFunction<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Double, Short> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableDoubleToShortFunction<X> & Memoized) (value) -> {
                final short returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, ThrowableFunction.of(this::applyAsShortThrows));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableFunction} which represents this {@link ThrowableDoubleToShortFunction}.
     * Thereby the primitive input argument for this function is autoboxed.
     *
     * @return A composed {@code ThrowableFunction} which represents this {@code ThrowableDoubleToShortFunction}.
     */
    @Nonnull
    default ThrowableFunction<Double, Short, X> boxed() {
        return this::applyAsShortThrows;
    }

    /**
     * Returns a composed {@link DoubleToShortFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is
     * nested (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown
     * throwables message and the thrown throwable itself.
     *
     * @return A composed {@code DoubleToShortFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default DoubleToShortFunction nest() {
        return (value) -> {
            try {
                return this.applyAsShortThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link DoubleToShortFunction} that applies this function to its input and sneakily throws the
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
     * @return A composed {@link DoubleToShortFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default DoubleToShortFunction sneakyThrow() {
        return (value) -> {
            try {
                return this.applyAsShortThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}