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
package at.gridtec.lambda4j.function;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ByteConsumer;
import at.gridtec.lambda4j.function.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.CharToByteFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToByteFunction;
import at.gridtec.lambda4j.function.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.function.conversion.IntToByteFunction;
import at.gridtec.lambda4j.function.conversion.LongToByteFunction;
import at.gridtec.lambda4j.function.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.function.to.ToByteFunction;
import at.gridtec.lambda4j.operator.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents an operation that accepts one {@code byte}-valued input argument and produces a result. This is a
 * primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(byte)}.
 *
 * @param <R> The type of return value from the function
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteFunction<R> extends Lambda {

    /**
     * Constructs a {@link ByteFunction} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ByteFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <R> ByteFunction<R> of(@Nullable final ByteFunction<R> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ByteFunction} with the given argument and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <R> R call(@Nonnull final ByteFunction<? extends R> function, byte value) {
        Objects.requireNonNull(function);
        return function.apply(value);
    }

    /**
     * Creates a {@link ByteFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code ByteFunction} which always returns a given value.
     */
    @Nonnull
    static <R> ByteFunction<R> constant(R ret) {
        return (value) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(byte value);

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
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> Function2<A, R> compose(@Nonnull final ToByteFunction<? super A> before) {
        Objects.requireNonNull(before);
        return (a) -> apply(before.applyAsByte(a));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanFunction<R> composeFromBoolean(@Nonnull final BooleanToByteFunction before) {
        Objects.requireNonNull(before);
        return (value) -> apply(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteFunction} that first applies the {@code before} operator to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code ByteFunction} that first applies the {@code before} operator to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteFunction<R> composeFromByte(@Nonnull final ByteUnaryOperator before) {
        Objects.requireNonNull(before);
        return (value) -> apply(before.applyAsByte(value));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharFunction<R> composeFromChar(@Nonnull final CharToByteFunction before) {
        Objects.requireNonNull(before);
        return (value) -> apply(before.applyAsByte(value));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleFunction2<R> composeFromDouble(@Nonnull final DoubleToByteFunction before) {
        Objects.requireNonNull(before);
        return (value) -> apply(before.applyAsByte(value));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatFunction<R> composeFromFloat(@Nonnull final FloatToByteFunction before) {
        Objects.requireNonNull(before);
        return (value) -> apply(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link IntFunction2} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code IntFunction2} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntFunction2<R> composeFromInt(@Nonnull final IntToByteFunction before) {
        Objects.requireNonNull(before);
        return (value) -> apply(before.applyAsByte(value));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongFunction2<R> composeFromLong(@Nonnull final LongToByteFunction before) {
        Objects.requireNonNull(before);
        return (value) -> apply(before.applyAsByte(value));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortFunction<R> composeFromShort(@Nonnull final ShortToByteFunction before) {
        Objects.requireNonNull(before);
        return (value) -> apply(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ByteFunction<S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value) -> after.apply(apply(value));
    }

    /**
     * Returns a composed {@link ByteConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ByteConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteConsumer consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.accept(apply(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ByteFunction}. Whenever it is called, the mapping between the
     * input parameter and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ByteFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ByteFunction<R> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Byte, R> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ByteFunction<R> & Memoized) (value) -> {
                final R returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::apply);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Function} which represents this {@link ByteFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ByteFunction} with JDK specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code ByteFunction}.
     */
    @Nonnull
    default Function<Byte, R> boxed() {
        return this::apply;
    }

}