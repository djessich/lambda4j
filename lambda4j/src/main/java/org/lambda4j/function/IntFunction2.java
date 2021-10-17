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

package org.lambda4j.function;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.IntConsumer2;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.ByteToIntFunction;
import org.lambda4j.function.conversion.CharToIntFunction;
import org.lambda4j.function.conversion.FloatToIntFunction;
import org.lambda4j.function.conversion.ShortToIntFunction;

/**
 * Represents an operation that accepts one {@code int}-valued input argument and produces a result. This is a primitive
 * specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(int)}.
 *
 * @param <R> The type of return value from the function
 * @apiNote This is a JDK lambda.
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface IntFunction2<R> extends Lambda, IntFunction<R> {

    /**
     * Constructs a {@link IntFunction2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code IntFunction2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <R> IntFunction2<R> of(@Nullable IntFunction2<R> expression) {
        return expression;
    }

    /**
     * Lifts a partial {@link IntFunction} into a total {@link IntFunction2} that returns an {@link Optional} result.
     *
     * @param <R> The type of return value from the function
     * @param partial A function that is only defined for some values in its domain
     * @return A partial {@code IntFunction} lifted into a total {@code IntFunction2} that returns an {@code Optional}
     * result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> IntFunction2<Optional<R>> lift(@Nonnull IntFunction<? extends R> partial) {
        Objects.requireNonNull(partial);
        return value -> Optional.ofNullable(partial.apply(value));
    }

    /**
     * Calls the given {@link IntFunction} with the given argument and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code IntFunction2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <R> R call(@Nonnull IntFunction<? extends R> function, int value) {
        Objects.requireNonNull(function);
        return function.apply(value);
    }

    /**
     * Creates a {@link IntFunction2} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code IntFunction2} which always returns a given value.
     */
    @Nonnull
    static <R> IntFunction2<R> constant(R ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    @Override
    R apply(int value);

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
     * Returns a composed {@link Function2} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code Function2} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> Function2<A, R> compose(@Nonnull ToIntFunction<? super A> before) {
        Objects.requireNonNull(before);
        return a -> apply(before.applyAsInt(a));
    }

    /**
     * Returns a composed {@link BooleanFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code BooleanFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanFunction<R> composeFromBoolean(@Nonnull BooleanToIntFunction before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsInt(value));
    }

    /**
     * Returns a composed {@link ByteFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ByteFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteFunction<R> composeFromByte(@Nonnull ByteToIntFunction before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsInt(value));
    }

    /**
     * Returns a composed {@link CharFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code CharFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharFunction<R> composeFromChar(@Nonnull CharToIntFunction before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsInt(value));
    }

    /**
     * Returns a composed {@link DoubleFunction2} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code DoubleFunction2} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleFunction2<R> composeFromDouble(@Nonnull DoubleToIntFunction before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsInt(value));
    }

    /**
     * Returns a composed {@link FloatFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code FloatFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatFunction<R> composeFromFloat(@Nonnull FloatToIntFunction before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsInt(value));
    }

    /**
     * Returns a composed {@link IntFunction2} that first applies the {@code before} operator to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code IntFunction2} that first applies the {@code before} operator to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntFunction2<R> composeFromInt(@Nonnull IntUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsInt(value));
    }

    /**
     * Returns a composed {@link LongFunction2} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code LongFunction2} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongFunction2<R> composeFromLong(@Nonnull LongToIntFunction before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ShortFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortFunction<R> composeFromShort(@Nonnull ShortToIntFunction before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsInt(value));
    }

    /**
     * Returns a composed {@link IntFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code IntFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> IntFunction2<S> andThen(@Nonnull Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(apply(value));
    }

    /**
     * Returns a composed {@link IntConsumer2} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code IntConsumer2} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default IntConsumer2 consume(@Nonnull Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(apply(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link IntFunction2}. Whenever it is called, the mapping between the
     * input parameter and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code IntFunction2}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default IntFunction2<R> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Integer, R> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (IntFunction2<R> & Memoized) value -> {
                R returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::apply);
                }
                return returnValue;
            };
        }
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     * @deprecated Use {@code lift} method for lifting this function.
     */
    @Deprecated
    @Nonnull
    default IntFunction2<Optional<R>> nonNull() {
        return value -> Optional.ofNullable(apply(value));
    }

    /**
     * Returns a composed {@link Function2} which represents this {@link IntFunction2}. Thereby the primitive input
     * argument for this function is autoboxed. This method provides the possibility to use this {@code IntFunction2}
     * with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Function2} which represents this {@code IntFunction2}.
     */
    @Nonnull
    default Function2<Integer, R> boxed() {
        return this::apply;
    }

}
