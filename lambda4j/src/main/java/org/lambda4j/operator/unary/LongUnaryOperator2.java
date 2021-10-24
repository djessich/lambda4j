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
import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.LongConsumer2;
import org.lambda4j.function.LongFunction2;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.ByteToLongFunction;
import org.lambda4j.function.conversion.CharToLongFunction;
import org.lambda4j.function.conversion.DoubleToLongFunction2;
import org.lambda4j.function.conversion.FloatToLongFunction;
import org.lambda4j.function.conversion.IntToLongFunction2;
import org.lambda4j.function.conversion.LongToByteFunction;
import org.lambda4j.function.conversion.LongToCharFunction;
import org.lambda4j.function.conversion.LongToDoubleFunction2;
import org.lambda4j.function.conversion.LongToFloatFunction;
import org.lambda4j.function.conversion.LongToIntFunction2;
import org.lambda4j.function.conversion.LongToShortFunction;
import org.lambda4j.function.conversion.ShortToLongFunction;
import org.lambda4j.function.to.ToLongFunction2;
import org.lambda4j.predicate.LongPredicate2;

/**
 * Represents an operation that accepts one {@code long}-valued input argument and produces a {@code long}-valued
 * result. This is a primitive specialization of {@link UnaryOperator2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(long)}.
 *
 * @apiNote This is a JDK lambda.
 * @see UnaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface LongUnaryOperator2 extends Lambda, LongUnaryOperator {

    /**
     * Constructs a {@link LongUnaryOperator2} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code LongUnaryOperator2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static LongUnaryOperator2 of(@Nullable LongUnaryOperator2 expression) {
        return expression;
    }

    /**
     * Calls the given {@link LongUnaryOperator} with the given argument and returns its result.
     *
     * @param operator The operator to be called
     * @param value The argument to the operator
     * @return The result from the given {@code LongUnaryOperator2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static long call(@Nonnull LongUnaryOperator operator, long value) {
        Objects.requireNonNull(operator);
        return operator.applyAsLong(value);
    }

    /**
     * Returns a {@link LongUnaryOperator2} that always returns its input argument.
     *
     * @return A {@code LongUnaryOperator2} that always returns its input argument
     */
    @Nonnull
    static LongUnaryOperator2 identity() {
        return value -> value;
    }

    /**
     * Creates a {@link LongUnaryOperator2} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code LongUnaryOperator2} which always returns a given value.
     */
    @Nonnull
    static LongUnaryOperator2 constant(long ret) {
        return value -> ret;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param value The argument to the operator
     * @return The return value from the operator, which is its result.
     */
    @Override
    long applyAsLong(long value);

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
     * Returns a composed {@link ToLongFunction2} that first applies the {@code before} function to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ToLongFunction2} that first applies the {@code before} function to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToLongFunction2<A> compose(@Nonnull ToLongFunction<? super A> before) {
        Objects.requireNonNull(before);
        return a -> applyAsLong(before.applyAsLong(a));
    }

    /**
     * Returns a composed {@link BooleanToLongFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code BooleanToLongFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToLongFunction composeFromBoolean(@Nonnull BooleanToLongFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsLong(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link ByteToLongFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ByteToLongFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteToLongFunction composeFromByte(@Nonnull ByteToLongFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsLong(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link CharToLongFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code CharToLongFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharToLongFunction composeFromChar(@Nonnull CharToLongFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsLong(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link DoubleToLongFunction2} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code DoubleToLongFunction2} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleToLongFunction2 composeFromDouble(@Nonnull DoubleToLongFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsLong(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link FloatToLongFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code FloatToLongFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatToLongFunction composeFromFloat(@Nonnull FloatToLongFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsLong(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link IntToLongFunction2} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code IntToLongFunction2} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntToLongFunction2 composeFromInt(@Nonnull IntToLongFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsLong(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongUnaryOperator2} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code LongUnaryOperator2} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongUnaryOperator2 composeFromLong(@Nonnull LongUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsLong(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link ShortToLongFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ShortToLongFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToLongFunction composeFromShort(@Nonnull ShortToLongFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsLong(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongFunction2} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code LongFunction2} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> LongFunction2<S> andThen(@Nonnull LongFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongPredicate2} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code LongPredicate2} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default LongPredicate2 andThenToBoolean(@Nonnull LongPredicate after) {
        Objects.requireNonNull(after);
        return value -> after.test(applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongToByteFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code LongToByteFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default LongToByteFunction andThenToByte(@Nonnull LongToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongToCharFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code LongToCharFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default LongToCharFunction andThenToChar(@Nonnull LongToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongToDoubleFunction2} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code LongToDoubleFunction2} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default LongToDoubleFunction2 andThenToDouble(@Nonnull LongToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code LongToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default LongToFloatFunction andThenToFloat(@Nonnull LongToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongToIntFunction2} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code LongToIntFunction2} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default LongToIntFunction2 andThenToInt(@Nonnull LongToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongUnaryOperator2} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code long}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code LongUnaryOperator2} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongUnaryOperator2 andThenToLong(@Nonnull LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code LongToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default LongToShortFunction andThenToShort(@Nonnull LongToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsLong(value));
    }

    /**
     * Returns a composed {@link LongConsumer2} that fist applies this operator to its input, and then consumes the
     * result using the given {@link LongConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code LongConsumer2} that first applies this operator to its input, and then consumes the
     * result using the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default LongConsumer2 consume(@Nonnull LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsLong(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link LongUnaryOperator2}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code LongUnaryOperator2}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default LongUnaryOperator2 memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Long, Long> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (LongUnaryOperator2 & Memoized) value -> {
                long returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::applyAsLong);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link UnaryOperator2} which represents this {@link LongUnaryOperator2}. Thereby the primitive
     * input argument for this operator is autoboxed. This method provides the possibility to use this {@code
     * LongUnaryOperator2} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code UnaryOperator2} which represents this {@code LongUnaryOperator2}.
     */
    @Nonnull
    default UnaryOperator2<Long> boxed() {
        return this::applyAsLong;
    }

}
