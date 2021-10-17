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
package org.lambda4j.function.to;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.Consumer2;
import org.lambda4j.function.Function2;
import org.lambda4j.function.conversion.IntToByteFunction;
import org.lambda4j.function.conversion.IntToCharFunction;
import org.lambda4j.function.conversion.IntToFloatFunction;
import org.lambda4j.function.conversion.IntToShortFunction;
import org.lambda4j.predicate.Predicate2;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;

/**
 * Represents an operation that accepts one input argument and produces a
 * {@code int}-valued result.
 * This is a primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(Object)}.
 *
 * @param <T> The type of the argument to the function
 * @apiNote This is a JDK lambda.
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToIntFunction2<T> extends Lambda, ToIntFunction<T> {

    /**
     * Constructs a {@link ToIntFunction2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ToIntFunction2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T> ToIntFunction2<T> of(@Nullable final ToIntFunction2<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ToIntFunction} with the given argument and returns its result.
     *
     * @param <T> The type of the argument to the function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code ToIntFunction2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> int call(@Nonnull final ToIntFunction<? super T> function, T t) {
        Objects.requireNonNull(function);
        return function.applyAsInt(t);
    }

    /**
     * Creates a {@link ToIntFunction2} which always returns a given value.
     *
     * @param <T> The type of the argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToIntFunction2} which always returns a given value.
     */
    @Nonnull
    static <T> ToIntFunction2<T> constant(int ret) {
        return (t) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     */
    int applyAsInt(T t);

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
     * Returns a composed {@link ToIntFunction2} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToIntFunction2} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToIntFunction2<A> compose(@Nonnull final Function<? super A, ? extends T> before) {
        Objects.requireNonNull(before);
        return (a) -> applyAsInt(before.apply(a));
    }

    /**
     * Returns a composed {@link Function2} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code Function2} that first applies this function to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> Function2<T, S> andThen(@Nonnull final IntFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t) -> after.apply(applyAsInt(t));
    }

    /**
     * Returns a composed {@link Predicate2} that first applies this function to its input, and then applies the {@code
     * after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code Predicate2} that first applies this function to its input, and then applies the {@code
     * after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default Predicate2<T> andThenToBoolean(@Nonnull final IntPredicate after) {
        Objects.requireNonNull(after);
        return (t) -> after.test(applyAsInt(t));
    }

    /**
     * Returns a composed {@link ToByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ToByteFunction<T> andThenToByte(@Nonnull final IntToByteFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsByte(applyAsInt(t));
    }

    /**
     * Returns a composed {@link ToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ToCharFunction<T> andThenToChar(@Nonnull final IntToCharFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsChar(applyAsInt(t));
    }

    /**
     * Returns a composed {@link ToDoubleFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToDoubleFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ToDoubleFunction2<T> andThenToDouble(@Nonnull final IntToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsDouble(applyAsInt(t));
    }

    /**
     * Returns a composed {@link ToFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ToFloatFunction<T> andThenToFloat(@Nonnull final IntToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsFloat(applyAsInt(t));
    }

    /**
     * Returns a composed {@link ToIntFunction2} that first applies this function to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ToIntFunction2} that first applies this function to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ToIntFunction2<T> andThenToInt(@Nonnull final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsInt(applyAsInt(t));
    }

    /**
     * Returns a composed {@link ToLongFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToLongFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ToLongFunction2<T> andThenToLong(@Nonnull final IntToLongFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsLong(applyAsInt(t));
    }

    /**
     * Returns a composed {@link ToShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ToShortFunction<T> andThenToShort(@Nonnull final IntToShortFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsShort(applyAsInt(t));
    }

    /**
     * Returns a composed {@link Consumer2} that fist applies this function to its input, and then consumes the result
     * using the given {@link IntConsumer}.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer2} that first applies this function to its input, and then consumes the result
     * using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default Consumer2<T> consume(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t) -> consumer.accept(applyAsInt(t));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ToIntFunction2<T> reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link ToIntFunction2}. Whenever it is called, the mapping between
     * the input parameter and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ToIntFunction2}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ToIntFunction2<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<T, Integer> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ToIntFunction2<T> & Memoized) (t) -> {
                final int returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(t, this::applyAsInt);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Function2} which represents this {@link ToIntFunction2}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this
     * {@code ToIntFunction2} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Function2} which represents this {@code ToIntFunction2}.
     */
    @Nonnull
    default Function2<T, Integer> boxed() {
        return this::applyAsInt;
    }

}