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
import org.lambda4j.consumer.FloatConsumer;
import org.lambda4j.consumer.ShortConsumer;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.Function2;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.function.to.ToShortFunction;
import org.lambda4j.operator.unary.FloatUnaryOperator;
import org.lambda4j.operator.unary.ShortUnaryOperator;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.ShortPredicate;

/**
 * Represents an operation that accepts one {@code short}-valued input argument and produces a {@code float}-valued
 * result. This is a primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(short)}.
 *
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortToFloatFunction extends Lambda {

    /**
     * Constructs a {@link ShortToFloatFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ShortToFloatFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static ShortToFloatFunction of(@Nullable ShortToFloatFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link ShortToFloatFunction} with the given argument and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ShortToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static float call(@Nonnull ShortToFloatFunction function, short value) {
        Objects.requireNonNull(function);
        return function.applyAsFloat(value);
    }

    /**
     * Creates a {@link ShortToFloatFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortToFloatFunction} which always returns a given value.
     */
    @Nonnull
    static ShortToFloatFunction constant(float ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(short value);

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
    default <A> ToFloatFunction<A> compose(@Nonnull ToShortFunction<? super A> before) {
        Objects.requireNonNull(before);
        return a -> applyAsFloat(before.applyAsShort(a));
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
    default BooleanToFloatFunction composeFromBoolean(@Nonnull BooleanToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ByteToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ByteToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteToFloatFunction composeFromByte(@Nonnull ByteToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsShort(value));
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
    default CharToFloatFunction composeFromChar(@Nonnull CharToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsShort(value));
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
    default DoubleToFloatFunction composeFromDouble(@Nonnull DoubleToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsShort(value));
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
    default FloatUnaryOperator composeFromFloat(@Nonnull FloatToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsShort(value));
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
    default IntToFloatFunction composeFromInt(@Nonnull IntToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsShort(value));
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
    default LongToFloatFunction composeFromLong(@Nonnull LongToShortFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortToFloatFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code ShortToFloatFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToFloatFunction composeFromShort(@Nonnull ShortUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsFloat(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ShortFunction<S> andThen(@Nonnull FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ShortPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ShortPredicate andThenToBoolean(@Nonnull FloatPredicate after) {
        Objects.requireNonNull(after);
        return value -> after.test(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ShortToByteFunction andThenToByte(@Nonnull FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ShortToCharFunction andThenToChar(@Nonnull FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ShortToDoubleFunction andThenToDouble(@Nonnull FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ShortToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ShortToFloatFunction andThenToFloat(@Nonnull FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ShortToIntFunction andThenToInt(@Nonnull FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ShortToLongFunction andThenToLong(@Nonnull FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortUnaryOperator andThenToShort(@Nonnull FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ShortConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortConsumer consume(@Nonnull FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsFloat(value));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ShortToFloatFunction reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link ShortToFloatFunction}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ShortToFloatFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ShortToFloatFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Short, Float> cache = new ConcurrentHashMap<>();
            return (ShortToFloatFunction & Memoized) value -> {
                return cache.computeIfAbsent(value, this::applyAsFloat);
            };
        }
    }

    /**
     * Returns a composed {@link Function2} which represents this {@link ShortToFloatFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * ShortToFloatFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Function2} which represents this {@code ShortToFloatFunction}.
     */
    @Nonnull
    default Function2<Short, Float> boxed() {
        return this::applyAsFloat;
    }
}
