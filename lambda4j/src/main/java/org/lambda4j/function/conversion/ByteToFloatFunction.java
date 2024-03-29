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

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ByteConsumer;
import org.lambda4j.consumer.FloatConsumer;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.Function2;
import org.lambda4j.function.to.ToByteFunction;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.operator.unary.ByteUnaryOperator;
import org.lambda4j.operator.unary.FloatUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.FloatPredicate;

/**
 * Represents an operation that accepts one {@code byte}-valued input argument and produces a {@code float}-valued
 * result. This is a primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(byte)}.
 *
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteToFloatFunction extends Lambda {

    /**
     * Constructs a {@link ByteToFloatFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ByteToFloatFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static ByteToFloatFunction of(@Nullable ByteToFloatFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link ByteToFloatFunction} with the given argument and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ByteToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static float call(@Nonnull ByteToFloatFunction function, byte value) {
        Objects.requireNonNull(function);
        return function.applyAsFloat(value);
    }

    /**
     * Creates a {@link ByteToFloatFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteToFloatFunction} which always returns a given value.
     */
    @Nonnull
    static ByteToFloatFunction constant(float ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(byte value);

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
     * Returns a composed {@link ToFloatFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToFloatFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToFloatFunction<A> compose(@Nonnull ToByteFunction<? super A> before) {
        Objects.requireNonNull(before);
        return a -> applyAsFloat(before.applyAsByte(a));
    }

    /**
     * Returns a composed {@link BooleanToFloatFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code BooleanToFloatFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToFloatFunction composeFromBoolean(@Nonnull BooleanToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToFloatFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code ByteToFloatFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteToFloatFunction composeFromByte(@Nonnull ByteUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link CharToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code CharToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharToFloatFunction composeFromChar(@Nonnull CharToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoubleToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code DoubleToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleToFloatFunction composeFromDouble(@Nonnull DoubleToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code FloatUnaryOperator} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatUnaryOperator composeFromFloat(@Nonnull FloatToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link IntToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code IntToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntToFloatFunction composeFromInt(@Nonnull IntToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code LongToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongToFloatFunction composeFromLong(@Nonnull LongToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ShortToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ShortToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToFloatFunction composeFromShort(@Nonnull ShortToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsByte(value));
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
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ByteFunction<S> andThen(@Nonnull FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link BytePredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code BytePredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BytePredicate andThenToBoolean(@Nonnull FloatPredicate after) {
        Objects.requireNonNull(after);
        return value -> after.test(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteUnaryOperator andThenToByte(@Nonnull FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ByteToCharFunction andThenToChar(@Nonnull FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ByteToDoubleFunction andThenToDouble(@Nonnull FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ByteToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ByteToFloatFunction andThenToFloat(@Nonnull FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ByteToIntFunction andThenToInt(@Nonnull FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteToLongFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToLongFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ByteToLongFunction andThenToLong(@Nonnull FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ByteToShortFunction andThenToShort(@Nonnull FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ByteConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteConsumer consume(@Nonnull FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsFloat(value));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ByteToFloatFunction reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link ByteToFloatFunction}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ByteToFloatFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ByteToFloatFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Byte, Float> cache = new ConcurrentHashMap<>();
            return (ByteToFloatFunction & Memoized) value -> {
                return cache.computeIfAbsent(value, this::applyAsFloat);
            };
        }
    }

    /**
     * Returns a composed {@link Function2} which represents this {@link ByteToFloatFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * ByteToFloatFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Function2} which represents this {@code ByteToFloatFunction}.
     */
    @Nonnull
    default Function2<Byte, Float> boxed() {
        return this::applyAsFloat;
    }
}
