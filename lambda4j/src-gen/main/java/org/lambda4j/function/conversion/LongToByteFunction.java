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
package org.lambda4j.function.conversion;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ByteConsumer;
import org.lambda4j.consumer.LongConsumer2;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.Function2;
import org.lambda4j.function.LongFunction2;
import org.lambda4j.function.to.ToByteFunction;
import org.lambda4j.operator.unary.ByteUnaryOperator;
import org.lambda4j.operator.unary.LongUnaryOperator2;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.LongPredicate2;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;

/**
 * Represents an operation that accepts one {@code long}-valued input argument and produces a
 * {@code byte}-valued result.
 * This is a primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(long)}.
 *
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface LongToByteFunction extends Lambda {

    /**
     * Constructs a {@link LongToByteFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code LongToByteFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static LongToByteFunction of(@Nullable final LongToByteFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link LongToByteFunction} with the given argument and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code LongToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static byte call(@Nonnull final LongToByteFunction function, long value) {
        Objects.requireNonNull(function);
        return function.applyAsByte(value);
    }

    /**
     * Creates a {@link LongToByteFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code LongToByteFunction} which always returns a given value.
     */
    @Nonnull
    static LongToByteFunction constant(byte ret) {
        return (value) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(long value);

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
     * Returns a composed {@link ToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToByteFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToByteFunction<A> compose(@Nonnull final ToLongFunction<? super A> before) {
        Objects.requireNonNull(before);
        return (a) -> applyAsByte(before.applyAsLong(a));
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code BooleanToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToByteFunction composeFromBoolean(@Nonnull final BooleanToLongFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ByteUnaryOperator} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteUnaryOperator composeFromByte(@Nonnull final ByteToLongFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link CharToByteFunction} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code CharToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharToByteFunction composeFromChar(@Nonnull final CharToLongFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link DoubleToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code DoubleToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleToByteFunction composeFromDouble(@Nonnull final DoubleToLongFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link FloatToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code FloatToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatToByteFunction composeFromFloat(@Nonnull final FloatToLongFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link IntToByteFunction} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code IntToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntToByteFunction composeFromInt(@Nonnull final IntToLongFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongToByteFunction} that first applies the {@code before} operator to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code LongToByteFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongToByteFunction composeFromLong(@Nonnull final LongUnaryOperator before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link ShortToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ShortToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToByteFunction composeFromShort(@Nonnull final ShortToLongFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> LongFunction2<S> andThen(@Nonnull final ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value) -> after.apply(applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongPredicate2} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code LongPredicate2} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default LongPredicate2 andThenToBoolean(@Nonnull final BytePredicate after) {
        Objects.requireNonNull(after);
        return (value) -> after.test(applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongToByteFunction} that first applies this function to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code LongToByteFunction} that first applies this function to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default LongToByteFunction andThenToByte(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsByte(applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default LongToCharFunction andThenToChar(@Nonnull final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsChar(applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongToDoubleFunction2} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToDoubleFunction2} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default LongToDoubleFunction2 andThenToDouble(@Nonnull final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsDouble(applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default LongToFloatFunction andThenToFloat(@Nonnull final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsFloat(applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongToIntFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToIntFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default LongToIntFunction2 andThenToInt(@Nonnull final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsInt(applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongUnaryOperator2} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongUnaryOperator2} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongUnaryOperator2 andThenToLong(@Nonnull final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsLong(applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default LongToShortFunction andThenToShort(@Nonnull final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsShort(applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongConsumer2} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code LongConsumer2} that first applies this function to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default LongConsumer2 consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.accept(applyAsByte(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link LongToByteFunction}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code LongToByteFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default LongToByteFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Long, Byte> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (LongToByteFunction & Memoized) (value) -> {
                final byte returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::applyAsByte);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Function2} which represents this {@link LongToByteFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this
     * {@code LongToByteFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Function2} which represents this {@code LongToByteFunction}.
     */
    @Nonnull
    default Function2<Long, Byte> boxed() {
        return this::applyAsByte;
    }

}