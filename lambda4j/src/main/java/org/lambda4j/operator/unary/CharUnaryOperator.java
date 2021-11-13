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

package org.lambda4j.operator.unary;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.CharConsumer;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.ByteToCharFunction;
import org.lambda4j.function.conversion.CharToByteFunction;
import org.lambda4j.function.conversion.CharToDoubleFunction;
import org.lambda4j.function.conversion.CharToFloatFunction;
import org.lambda4j.function.conversion.CharToIntFunction;
import org.lambda4j.function.conversion.CharToLongFunction;
import org.lambda4j.function.conversion.CharToShortFunction;
import org.lambda4j.function.conversion.DoubleToCharFunction;
import org.lambda4j.function.conversion.FloatToCharFunction;
import org.lambda4j.function.conversion.IntToCharFunction;
import org.lambda4j.function.conversion.LongToCharFunction;
import org.lambda4j.function.conversion.ShortToCharFunction;
import org.lambda4j.function.to.ToCharFunction;
import org.lambda4j.predicate.CharPredicate;

/**
 * Represents an operation that accepts one {@code char}-valued input argument and produces a {@code char}-valued
 * result. This is a primitive specialization of {@link UnaryOperator2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(char)}.
 *
 * @see UnaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharUnaryOperator extends Lambda {

    /**
     * Constructs a {@link CharUnaryOperator} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code CharUnaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static CharUnaryOperator of(@Nullable CharUnaryOperator expression) {
        return expression;
    }

    /**
     * Calls the given {@link CharUnaryOperator} with the given argument and returns its result.
     *
     * @param operator The operator to be called
     * @param value The argument to the operator
     * @return The result from the given {@code CharUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static char call(@Nonnull CharUnaryOperator operator, char value) {
        Objects.requireNonNull(operator);
        return operator.applyAsChar(value);
    }

    /**
     * Returns a {@link CharUnaryOperator} that always returns its input argument.
     *
     * @return A {@code CharUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static CharUnaryOperator identity() {
        return value -> value;
    }

    /**
     * Creates a {@link CharUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static CharUnaryOperator constant(char ret) {
        return value -> ret;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param value The argument to the operator
     * @return The return value from the operator, which is its result.
     */
    char applyAsChar(char value);

    /**
     * Returns the number of arguments for this operator.
     *
     * @return The number of arguments for this operator.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ToCharFunction} that first applies the {@code before} function to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ToCharFunction} that first applies the {@code before} function to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToCharFunction<A> compose(@Nonnull ToCharFunction<? super A> before) {
        Objects.requireNonNull(before);
        return a -> applyAsChar(before.applyAsChar(a));
    }

    /**
     * Returns a composed {@link BooleanToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code BooleanToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToCharFunction composeFromBoolean(@Nonnull BooleanToCharFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsChar(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link ByteToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ByteToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteToCharFunction composeFromByte(@Nonnull ByteToCharFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsChar(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code CharUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharUnaryOperator composeFromChar(@Nonnull CharUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsChar(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link DoubleToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code DoubleToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleToCharFunction composeFromDouble(@Nonnull DoubleToCharFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsChar(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link FloatToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code FloatToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatToCharFunction composeFromFloat(@Nonnull FloatToCharFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsChar(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link IntToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code IntToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntToCharFunction composeFromInt(@Nonnull IntToCharFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsChar(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link LongToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code LongToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongToCharFunction composeFromLong(@Nonnull LongToCharFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsChar(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link ShortToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ShortToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToCharFunction composeFromShort(@Nonnull ShortToCharFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsChar(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code CharFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> CharFunction<S> andThen(@Nonnull CharFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharPredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code CharPredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default CharPredicate andThenToBoolean(@Nonnull CharPredicate after) {
        Objects.requireNonNull(after);
        return value -> after.test(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToByteFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code CharToByteFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default CharToByteFunction andThenToByte(@Nonnull CharToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code char}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code CharUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharUnaryOperator andThenToChar(@Nonnull CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code CharToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default CharToDoubleFunction andThenToDouble(@Nonnull CharToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code CharToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default CharToFloatFunction andThenToFloat(@Nonnull CharToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code CharToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default CharToIntFunction andThenToInt(@Nonnull CharToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToLongFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code CharToLongFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default CharToLongFunction andThenToLong(@Nonnull CharToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code CharToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default CharToShortFunction andThenToShort(@Nonnull CharToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@link CharConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code CharConsumer} that first applies this operator to its input, and then consumes the
     * result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharConsumer consume(@Nonnull CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsChar(value));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default CharUnaryOperator reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link CharUnaryOperator}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code CharUnaryOperator}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default CharUnaryOperator memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Character, Character> cache = new ConcurrentHashMap<>();
            return (CharUnaryOperator & Memoized) value -> {
                return cache.computeIfAbsent(value, this::applyAsChar);
            };
        }
    }

    /**
     * Returns a composed {@link UnaryOperator2} which represents this {@link CharUnaryOperator}. Thereby the primitive
     * input argument for this operator is autoboxed. This method provides the possibility to use this {@code
     * CharUnaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code UnaryOperator2} which represents this {@code CharUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator2<Character> boxed() {
        return this::applyAsChar;
    }
}
