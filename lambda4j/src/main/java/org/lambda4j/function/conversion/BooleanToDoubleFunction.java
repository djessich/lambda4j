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
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.Function2;
import org.lambda4j.function.to.ToDoubleFunction2;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.operator.unary.DoubleUnaryOperator2;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.ShortPredicate;

/**
 * Represents an operation that accepts one {@code boolean}-valued input argument and produces a {@code double}-valued
 * result. This is a primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(boolean)}.
 *
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanToDoubleFunction extends Lambda {

    /**
     * Constructs a {@link BooleanToDoubleFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BooleanToDoubleFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static BooleanToDoubleFunction of(@Nullable BooleanToDoubleFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link BooleanToDoubleFunction} with the given argument and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static double call(@Nonnull BooleanToDoubleFunction function, boolean value) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(value);
    }

    /**
     * Creates a {@link BooleanToDoubleFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanToDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static BooleanToDoubleFunction constant(double ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    double applyAsDouble(boolean value);

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
     * Returns a composed {@link ToDoubleFunction2} that first applies the {@code before} predicate to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the given predicate, and of composed function
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ToDoubleFunction2} that first applies the {@code before} predicate to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToDoubleFunction2<A> compose(@Nonnull Predicate<? super A> before) {
        Objects.requireNonNull(before);
        return a -> applyAsDouble(before.test(a));
    }

    /**
     * Returns a composed {@link BooleanToDoubleFunction} that first applies the {@code before} operator to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code BooleanToDoubleFunction} that first applies the {@code before} operator to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToDoubleFunction composeFromBoolean(@Nonnull BooleanUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsDouble(before.applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link ByteToDoubleFunction} that first applies the {@code before} predicate to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ByteToDoubleFunction} that first applies the {@code before} predicate to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteToDoubleFunction composeFromByte(@Nonnull BytePredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsDouble(before.test(value));
    }

    /**
     * Returns a composed {@link CharToDoubleFunction} that first applies the {@code before} predicate to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code CharToDoubleFunction} that first applies the {@code before} predicate to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharToDoubleFunction composeFromChar(@Nonnull CharPredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsDouble(before.test(value));
    }

    /**
     * Returns a composed {@link DoubleUnaryOperator2} that first applies the {@code before} predicate to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code DoubleUnaryOperator2} that first applies the {@code before} predicate to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleUnaryOperator2 composeFromDouble(@Nonnull DoublePredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsDouble(before.test(value));
    }

    /**
     * Returns a composed {@link FloatToDoubleFunction} that first applies the {@code before} predicate to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code FloatToDoubleFunction} that first applies the {@code before} predicate to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatToDoubleFunction composeFromFloat(@Nonnull FloatPredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsDouble(before.test(value));
    }

    /**
     * Returns a composed {@link IntToDoubleFunction2} that first applies the {@code before} predicate to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code IntToDoubleFunction2} that first applies the {@code before} predicate to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntToDoubleFunction2 composeFromInt(@Nonnull IntPredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsDouble(before.test(value));
    }

    /**
     * Returns a composed {@link LongToDoubleFunction2} that first applies the {@code before} predicate to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code LongToDoubleFunction2} that first applies the {@code before} predicate to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongToDoubleFunction2 composeFromLong(@Nonnull LongPredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsDouble(before.test(value));
    }

    /**
     * Returns a composed {@link ShortToDoubleFunction} that first applies the {@code before} predicate to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ShortToDoubleFunction} that first applies the {@code before} predicate to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToDoubleFunction composeFromShort(@Nonnull ShortPredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsDouble(before.test(value));
    }

    /**
     * Returns a composed {@link BooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BooleanFunction<S> andThen(@Nonnull DoubleFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanUnaryOperator andThenToBoolean(@Nonnull DoublePredicate after) {
        Objects.requireNonNull(after);
        return value -> after.test(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BooleanToByteFunction andThenToByte(@Nonnull DoubleToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BooleanToCharFunction andThenToChar(@Nonnull DoubleToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code BooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BooleanToDoubleFunction andThenToDouble(@Nonnull DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BooleanToFloatFunction andThenToFloat(@Nonnull DoubleToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BooleanToIntFunction andThenToInt(@Nonnull DoubleToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BooleanToLongFunction andThenToLong(@Nonnull DoubleToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BooleanToShortFunction andThenToShort(@Nonnull DoubleToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsDouble(value));
    }

    /**
     * Returns a composed {@link BooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link DoubleConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BooleanConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanConsumer consume(@Nonnull DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsDouble(value));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default BooleanToDoubleFunction reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link BooleanToDoubleFunction}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BooleanToDoubleFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BooleanToDoubleFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Boolean, Double> cache = new ConcurrentHashMap<>();
            return (BooleanToDoubleFunction & Memoized) value -> {
                return cache.computeIfAbsent(value, this::applyAsDouble);
            };
        }
    }

    /**
     * Returns a composed {@link Function2} which represents this {@link BooleanToDoubleFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * BooleanToDoubleFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Function2} which represents this {@code BooleanToDoubleFunction}.
     */
    @Nonnull
    default Function2<Boolean, Double> boxed() {
        return this::applyAsDouble;
    }
}
