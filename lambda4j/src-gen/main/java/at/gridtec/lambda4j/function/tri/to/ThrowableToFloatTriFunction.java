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
package at.gridtec.lambda4j.function.tri.to;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableFloatConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableFloatFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToIntFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToLongFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToShortFunction;
import at.gridtec.lambda4j.function.to.ThrowableToFloatFunction;
import at.gridtec.lambda4j.function.tri.ThrowableTriFunction;
import at.gridtec.lambda4j.operator.unary.ThrowableFloatUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableFloatPredicate;
import at.gridtec.lambda4j.predicate.tri.ThrowableTriPredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents an operation that accepts three input arguments and produces a
 * {@code float}-valued result which is able to throw any {@link Throwable}.
 * This is a primitive specialization of {@link ThrowableTriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloatThrows(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableTriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableToFloatTriFunction<T, U, V, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableToFloatTriFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableToFloatTriFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, U, V, X extends Throwable> ThrowableToFloatTriFunction<T, U, V, X> of(
            @Nullable final ThrowableToFloatTriFunction<T, U, V, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableToFloatTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ThrowableToFloatTriFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, U, V, X extends Throwable> float call(
            @Nonnull final ThrowableToFloatTriFunction<? super T, ? super U, ? super V, ? extends X> function, T t, U u,
            V v) throws X {
        Objects.requireNonNull(function);
        return function.applyAsFloatThrows(t, u, v);
    }

    /**
     * Creates a {@link ThrowableToFloatTriFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowableToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableToFloatTriFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableToFloatTriFunction<T, U, V, X> onlyFirst(
            @Nonnull final ThrowableToFloatFunction<? super T, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsFloatThrows(t);
    }

    /**
     * Creates a {@link ThrowableToFloatTriFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ThrowableToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableToFloatTriFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableToFloatTriFunction<T, U, V, X> onlySecond(
            @Nonnull final ThrowableToFloatFunction<? super U, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsFloatThrows(u);
    }

    /**
     * Creates a {@link ThrowableToFloatTriFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@link ThrowableToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableToFloatTriFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableToFloatTriFunction<T, U, V, X> onlyThird(
            @Nonnull final ThrowableToFloatFunction<? super V, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsFloatThrows(v);
    }

    /**
     * Creates a {@link ThrowableToFloatTriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableToFloatTriFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableToFloatTriFunction<T, U, V, X> constant(float ret) {
        return (t, u, v) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    float applyAsFloatThrows(T t, U u, V v) throws X;

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default float applyAsFloatThrows(@Nonnull Triple<T, U, V> tuple) throws X {
        Objects.requireNonNull(tuple);
        return applyAsFloatThrows(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ThrowableToFloatTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableToFloatTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToFloatTriFunction<A, B, C, X> compose(
            @Nonnull final ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull final ThrowableFunction<? super B, ? extends U, ? extends X> before2,
            @Nonnull final ThrowableFunction<? super C, ? extends V, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsFloatThrows(before1.applyThrows(a), before2.applyThrows(b), before3.applyThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableTriFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableTriFunction<T, U, V, S, X> andThen(
            @Nonnull final ThrowableFloatFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyThrows(applyAsFloatThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableTriPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableTriPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriPredicate<T, U, V, X> andThenToBoolean(
            @Nonnull final ThrowableFloatPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.testThrows(applyAsFloatThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableToByteTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToByteTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableToByteTriFunction<T, U, V, X> andThenToByte(
            @Nonnull final ThrowableFloatToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsByteThrows(applyAsFloatThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableToCharTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToCharTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableToCharTriFunction<T, U, V, X> andThenToChar(
            @Nonnull final ThrowableFloatToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsCharThrows(applyAsFloatThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableToDoubleTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToDoubleTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableToDoubleTriFunction<T, U, V, X> andThenToDouble(
            @Nonnull final ThrowableFloatToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsDoubleThrows(applyAsFloatThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableToFloatTriFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableToFloatTriFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableToFloatTriFunction<T, U, V, X> andThenToFloat(
            @Nonnull final ThrowableFloatUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsFloatThrows(applyAsFloatThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableToIntTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToIntTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableToIntTriFunction<T, U, V, X> andThenToInt(
            @Nonnull final ThrowableFloatToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsIntThrows(applyAsFloatThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableToLongTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToLongTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableToLongTriFunction<T, U, V, X> andThenToLong(
            @Nonnull final ThrowableFloatToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsLongThrows(applyAsFloatThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableToShortTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToShortTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableToShortTriFunction<T, U, V, X> andThenToShort(
            @Nonnull final ThrowableFloatToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsShortThrows(applyAsFloatThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableTriConsumer} that fist applies this function to its input, and then consumes
     * the result using the given {@link ThrowableFloatConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code ThrowableFloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriConsumer<T, U, V, X> consume(@Nonnull final ThrowableFloatConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.acceptThrows(applyAsFloatThrows(t, u, v));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ThrowableToFloatFunction<Triple<T, U, V>, X> tupled() {
        return this::applyAsFloatThrows;
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ThrowableToFloatTriFunction<V, U, T, X> reversed() {
        return (v, u, t) -> applyAsFloatThrows(t, u, v);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableToFloatTriFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableToFloatTriFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableToFloatTriFunction<T, U, V, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Triple<T, U, V>, Float> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableToFloatTriFunction<T, U, V, X> & Memoized) (t, u, v) -> {
                final float returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, u, v), ThrowableFunction.of(
                            key -> applyAsFloatThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} which represents this {@link ThrowableToFloatTriFunction}.
     * Thereby the primitive input argument for this function is autoboxed.
     *
     * @return A composed {@code ThrowableTriFunction} which represents this {@code ThrowableToFloatTriFunction}.
     */
    @Nonnull
    default ThrowableTriFunction<T, U, V, Float, X> boxed() {
        return this::applyAsFloatThrows;
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that applies this function to its input and nests the thrown {@link
     * Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is nested
     * (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown throwables
     * message and the thrown throwable itself.
     *
     * @return A composed {@code ToFloatTriFunction} that applies this function to its input and nests the thrown {@code
     * Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default ToFloatTriFunction<T, U, V> nest() {
        return (t, u, v) -> {
            try {
                return this.applyAsFloatThrows(t, u, v);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that applies this function to its input and sneakily throws the
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
     * @return A composed {@link ToFloatTriFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default ToFloatTriFunction<T, U, V> sneakyThrow() {
        return (t, u, v) -> {
            try {
                return this.applyAsFloatThrows(t, u, v);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link ToFloatTriFunction} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implNote The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     */
    @Nonnull
    default ToFloatTriFunction<T, U, V> recover(
            @Nonnull final Function<? super Throwable, ? extends ToFloatTriFunction<? super T, ? super U, ? super V>> recover) {
        Objects.requireNonNull(recover);
        return (t, u, v) -> {
            try {
                return this.applyAsFloatThrows(t, u, v);
            } catch (Throwable throwable) {
                final ToFloatTriFunction<? super T, ? super U, ? super V> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsFloat(t, u, v);
            }
        };
    }

}