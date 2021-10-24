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

package org.lambda4j.function.tri.to;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableCharConsumer;
import org.lambda4j.consumer.tri.ThrowableTriConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableCharFunction;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.bi.to.ThrowableToCharBiFunction;
import org.lambda4j.function.conversion.ThrowableCharToByteFunction;
import org.lambda4j.function.conversion.ThrowableCharToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableCharToFloatFunction;
import org.lambda4j.function.conversion.ThrowableCharToIntFunction;
import org.lambda4j.function.conversion.ThrowableCharToLongFunction;
import org.lambda4j.function.conversion.ThrowableCharToShortFunction;
import org.lambda4j.function.to.ThrowableToCharFunction;
import org.lambda4j.function.tri.ThrowableTriFunction;
import org.lambda4j.operator.unary.ThrowableCharUnaryOperator;
import org.lambda4j.predicate.ThrowableCharPredicate;
import org.lambda4j.predicate.tri.ThrowableTriPredicate;

/**
 * Represents an operation that accepts three input arguments and produces a {@code char}-valued result which is able to
 * throw any {@link Throwable}. This is a primitive specialization of {@link ThrowableTriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsCharThrows(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableTriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableToCharTriFunction<T, U, V, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableToCharTriFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableToCharTriFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U, V, X extends Throwable> ThrowableToCharTriFunction<T, U, V, X> of(
            @Nullable ThrowableToCharTriFunction<T, U, V, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableToCharTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ThrowableToCharTriFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, U, V, X extends Throwable> char call(
            @Nonnull ThrowableToCharTriFunction<? super T, ? super U, ? super V, ? extends X> function, T t, U u,
            V v) throws X {
        Objects.requireNonNull(function);
        return function.applyAsCharThrows(t, u, v);
    }

    /**
     * Creates a {@link ThrowableToCharTriFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowableToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableToCharTriFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableToCharTriFunction<T, U, V, X> onlyFirst(
            @Nonnull ThrowableToCharFunction<? super T, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsCharThrows(t);
    }

    /**
     * Creates a {@link ThrowableToCharTriFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ThrowableToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableToCharTriFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableToCharTriFunction<T, U, V, X> onlySecond(
            @Nonnull ThrowableToCharFunction<? super U, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsCharThrows(u);
    }

    /**
     * Creates a {@link ThrowableToCharTriFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@link ThrowableToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableToCharTriFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableToCharTriFunction<T, U, V, X> onlyThird(
            @Nonnull ThrowableToCharFunction<? super V, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsCharThrows(v);
    }

    /**
     * Creates a {@link ThrowableToCharTriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableToCharTriFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableToCharTriFunction<T, U, V, X> constant(char ret) {
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
    char applyAsCharThrows(T t, U u, V v) throws X;

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default char applyAsCharThrows(@Nonnull Triple<T, U, V> tuple) throws X {
        Objects.requireNonNull(tuple);
        return applyAsCharThrows(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToCharBiFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ThrowableToCharBiFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToCharBiFunction<U, V, X> applyAsCharThrowsPartially(T t) {
        return (u, v) -> applyAsCharThrows(t, u, v);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToCharFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param u The second argument to this function used to partially apply this function
     * @return A {@code ThrowableToCharFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToCharFunction<V, X> applyAsCharThrowsPartially(T t, U u) {
        return v -> applyAsCharThrows(t, u, v);
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
     * Returns a composed {@link ThrowableToCharTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ThrowableToCharTriFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableToCharTriFunction<A, B, C, X> compose(
            @Nonnull ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull ThrowableFunction<? super B, ? extends U, ? extends X> before2,
            @Nonnull ThrowableFunction<? super C, ? extends V, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsCharThrows(before1.applyThrows(a), before2.applyThrows(b), before3.applyThrows(c));
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
            @Nonnull ThrowableCharFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyThrows(applyAsCharThrows(t, u, v));
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
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriPredicate<T, U, V, X> andThenToBoolean(
            @Nonnull ThrowableCharPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.testThrows(applyAsCharThrows(t, u, v));
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
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableToByteTriFunction<T, U, V, X> andThenToByte(
            @Nonnull ThrowableCharToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsByteThrows(applyAsCharThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableToCharTriFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableToCharTriFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableToCharTriFunction<T, U, V, X> andThenToChar(
            @Nonnull ThrowableCharUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsCharThrows(applyAsCharThrows(t, u, v));
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
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableToDoubleTriFunction<T, U, V, X> andThenToDouble(
            @Nonnull ThrowableCharToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsDoubleThrows(applyAsCharThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableToFloatTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToFloatTriFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableToFloatTriFunction<T, U, V, X> andThenToFloat(
            @Nonnull ThrowableCharToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsFloatThrows(applyAsCharThrows(t, u, v));
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
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableToIntTriFunction<T, U, V, X> andThenToInt(
            @Nonnull ThrowableCharToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsIntThrows(applyAsCharThrows(t, u, v));
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
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableToLongTriFunction<T, U, V, X> andThenToLong(
            @Nonnull ThrowableCharToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsLongThrows(applyAsCharThrows(t, u, v));
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
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableToShortTriFunction<T, U, V, X> andThenToShort(
            @Nonnull ThrowableCharToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsShortThrows(applyAsCharThrows(t, u, v));
    }

    /**
     * Returns a composed {@link ThrowableTriConsumer} that fist applies this function to its input, and then consumes
     * the result using the given {@link ThrowableCharConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code ThrowableCharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriConsumer<T, U, V, X> consume(@Nonnull ThrowableCharConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.acceptThrows(applyAsCharThrows(t, u, v));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ThrowableToCharFunction<Triple<T, U, V>, X> tupled() {
        return this::applyAsCharThrows;
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ThrowableToCharTriFunction<V, U, T, X> reversed() {
        return (v, u, t) -> applyAsCharThrows(t, u, v);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableToCharTriFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableToCharTriFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableToCharTriFunction<T, U, V, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, U, V>, Character> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ThrowableToCharTriFunction<T, U, V, X> & Memoized) (t, u, v) -> {
                char returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, u, v), ThrowableFunction.of(
                            key -> applyAsCharThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriFunction} which represents this {@link ThrowableToCharTriFunction}. Thereby
     * the primitive input argument for this function is autoboxed. This method provides the possibility to use this
     * {@code ThrowableToCharTriFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTriFunction} which represents this {@code ThrowableToCharTriFunction}.
     */
    @Nonnull
    default ThrowableTriFunction<T, U, V, Character, X> boxed() {
        return this::applyAsCharThrows;
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that applies this function to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link ToCharTriFunction} that applies this function to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ToCharTriFunction<T, U, V> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that applies this function to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ToCharTriFunction} that applies this function to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ToCharTriFunction<T, U, V> nest(
            @Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link ToCharTriFunction} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ToCharTriFunction<T, U, V> recover(
            @Nonnull Function<? super Throwable, ? extends ToCharTriFunction<? super T, ? super U, ? super V>> recover) {
        Objects.requireNonNull(recover);
        return (t, u, v) -> {
            try {
                return applyAsCharThrows(t, u, v);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                ToCharTriFunction<? super T, ? super U, ? super V> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsChar(t, u, v);
            }
        };
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
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
     * @return A composed {@link ToCharTriFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ToCharTriFunction<T, U, V> sneakyThrow() {
        return (t, u, v) -> {
            try {
                return applyAsCharThrows(t, u, v);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
