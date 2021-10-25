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
 * Represents an operation that accepts one {@code float}-valued input argument and produces a {@code short}-valued
 * result. This is a primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(float)}.
 *
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatToShortFunction extends Lambda {

    /**
     * Constructs a {@link FloatToShortFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code FloatToShortFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static FloatToShortFunction of(@Nullable FloatToShortFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link FloatToShortFunction} with the given argument and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code FloatToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static short call(@Nonnull FloatToShortFunction function, float value) {
        Objects.requireNonNull(function);
        return function.applyAsShort(value);
    }

    /**
     * Creates a {@link FloatToShortFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatToShortFunction} which always returns a given value.
     */
    @Nonnull
    static FloatToShortFunction constant(short ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    short applyAsShort(float value);

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
     * Returns a composed {@link ToShortFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToShortFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToShortFunction<A> compose(@Nonnull ToFloatFunction<? super A> before) {
        Objects.requireNonNull(before);
        return a -> applyAsShort(before.applyAsFloat(a));
    }

    /**
     * Returns a composed {@link BooleanToShortFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code BooleanToShortFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToShortFunction composeFromBoolean(@Nonnull BooleanToFloatFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsShort(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteToShortFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ByteToShortFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteToShortFunction composeFromByte(@Nonnull ByteToFloatFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsShort(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link CharToShortFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code CharToShortFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharToShortFunction composeFromChar(@Nonnull CharToFloatFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsShort(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link DoubleToShortFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code DoubleToShortFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleToShortFunction composeFromDouble(@Nonnull DoubleToFloatFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsShort(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToShortFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code FloatToShortFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatToShortFunction composeFromFloat(@Nonnull FloatUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsShort(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link IntToShortFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code IntToShortFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntToShortFunction composeFromInt(@Nonnull IntToFloatFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsShort(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link LongToShortFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code LongToShortFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongToShortFunction composeFromLong(@Nonnull LongToFloatFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsShort(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortUnaryOperator} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ShortUnaryOperator} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortUnaryOperator composeFromShort(@Nonnull ShortToFloatFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsShort(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> FloatFunction<S> andThen(@Nonnull ShortFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code FloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default FloatPredicate andThenToBoolean(@Nonnull ShortPredicate after) {
        Objects.requireNonNull(after);
        return value -> after.test(applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default FloatToByteFunction andThenToByte(@Nonnull ShortToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default FloatToCharFunction andThenToChar(@Nonnull ShortToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default FloatToDoubleFunction andThenToDouble(@Nonnull ShortToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatUnaryOperator andThenToFloat(@Nonnull ShortToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default FloatToIntFunction andThenToInt(@Nonnull ShortToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default FloatToLongFunction andThenToLong(@Nonnull ShortToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code FloatToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default FloatToShortFunction andThenToShort(@Nonnull ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ShortConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code FloatConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatConsumer consume(@Nonnull ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsShort(value));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default FloatToShortFunction reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link FloatToShortFunction}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code FloatToShortFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default FloatToShortFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Float, Short> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (FloatToShortFunction & Memoized) value -> {
                short returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::applyAsShort);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Function2} which represents this {@link FloatToShortFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * FloatToShortFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Function2} which represents this {@code FloatToShortFunction}.
     */
    @Nonnull
    default Function2<Float, Short> boxed() {
        return this::applyAsShort;
    }

}
