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

package org.lambda4j.function.bi.obj;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.bi.obj.ObjFloatConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.Function2;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.BiBooleanFunction;
import org.lambda4j.function.bi.BiByteFunction;
import org.lambda4j.function.bi.BiCharFunction;
import org.lambda4j.function.bi.BiDoubleFunction;
import org.lambda4j.function.bi.BiFloatFunction;
import org.lambda4j.function.bi.BiFunction2;
import org.lambda4j.function.bi.BiIntFunction;
import org.lambda4j.function.bi.BiLongFunction;
import org.lambda4j.function.bi.BiShortFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.ByteToFloatFunction;
import org.lambda4j.function.conversion.CharToFloatFunction;
import org.lambda4j.function.conversion.DoubleToFloatFunction;
import org.lambda4j.function.conversion.IntToFloatFunction;
import org.lambda4j.function.conversion.LongToFloatFunction;
import org.lambda4j.function.conversion.ShortToFloatFunction;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.operator.unary.FloatUnaryOperator;

/**
 * Represents an operation that accepts one object-valued and one {@code float}-valued input argument and produces a
 * result. This is a (reference, float) specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, float)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <R> The type of return value from the function
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjFloatFunction<T, R> extends Lambda {

    /**
     * Constructs a {@link ObjFloatFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjFloatFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, R> ObjFloatFunction<T, R> of(@Nullable ObjFloatFunction<T, R> expression) {
        return expression;
    }

    /**
     * Lifts a partial {@link ObjFloatFunction} into a total {@link ObjFloatFunction} that returns an {@link Optional}
     * result.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param partial A function that is only defined for some values in its domain
     * @return A partial {@code ObjFloatFunction} lifted into a total {@code ObjFloatFunction} that returns an {@code
     * Optional} result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, R> ObjFloatFunction<T, Optional<R>> lift(
            @Nonnull ObjFloatFunction<? super T, ? extends R> partial) {
        Objects.requireNonNull(partial);
        return (t, value) -> Optional.ofNullable(partial.apply(t, value));
    }

    /**
     * Calls the given {@link ObjFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, R> R call(@Nonnull ObjFloatFunction<? super T, ? extends R> function, T t, float value) {
        Objects.requireNonNull(function);
        return function.apply(t, value);
    }

    /**
     * Creates a {@link ObjFloatFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjFloatFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code Function}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, R> ObjFloatFunction<T, R> onlyFirst(@Nonnull Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.apply(t);
    }

    /**
     * Creates a {@link ObjFloatFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link FloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjFloatFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code FloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, R> ObjFloatFunction<T, R> onlySecond(@Nonnull FloatFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.apply(value);
    }

    /**
     * Creates a {@link ObjFloatFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code ObjFloatFunction} which always returns a given value.
     */
    @Nonnull
    static <T, R> ObjFloatFunction<T, R> constant(R ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(T t, float value);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link FloatFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code FloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default FloatFunction<R> applyPartially(T t) {
        return value -> apply(t, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link Function2} as result.
     *
     * @param value The second argument to this function used to partially apply this function
     * @return A {@code Function2} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default Function2<T, R> applyPartially(float value) {
        return t -> apply(t, value);
    }

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiFunction2} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiFunction2} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiFunction2<A, B, R> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull ToFloatFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> apply(before1.apply(a), before2.applyAsFloat(b));
    }

    /**
     * Returns a composed {@link BiBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanFunction<R> composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteFunction<R> composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharFunction<R> composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiDoubleFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiDoubleFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleFunction<R> composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiFloatFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiFloatFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatFunction<R> composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntFunction<R> composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongFunction<R> composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiShortFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiShortFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortFunction<R> composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link ObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjFloatFunction<T, S> andThen(@Nonnull Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(apply(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjFloatConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjFloatConsumer<T> consume(@Nonnull Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(apply(t, value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjFloatFunction}. Whenever it is called, the mapping between
     * the input parameters and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjFloatFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjFloatFunction<T, R> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<T, Float>, R> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ObjFloatFunction<T, R> & Memoized) (t, value) -> {
                R returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(t, value), key -> apply(key.getLeft(), key.getRight()));
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
    default ObjFloatFunction<T, Optional<R>> nonNull() {
        return (t, value) -> Optional.ofNullable(apply(t, value));
    }

    /**
     * Returns a composed {@link BiFunction2} which represents this {@link ObjFloatFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * ObjFloatFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BiFunction2} which represents this {@code ObjFloatFunction}.
     */
    @Nonnull
    default BiFunction2<T, Float, R> boxed() {
        return this::apply;
    }

}
