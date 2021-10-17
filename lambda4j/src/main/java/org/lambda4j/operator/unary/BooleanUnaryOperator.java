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
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.DoublePredicate2;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.IntPredicate2;
import org.lambda4j.predicate.LongPredicate2;
import org.lambda4j.predicate.Predicate2;
import org.lambda4j.predicate.ShortPredicate;

/**
 * Represents an operation that accepts one {@code boolean}-valued input argument and produces a {@code boolean}-valued
 * result. This is a primitive specialization of {@link UnaryOperator2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(boolean)}.
 *
 * @see UnaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanUnaryOperator extends Lambda {

    /**
     * Constructs a {@link BooleanUnaryOperator} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BooleanUnaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static BooleanUnaryOperator of(@Nullable BooleanUnaryOperator expression) {
        return expression;
    }

    /**
     * Calls the given {@link BooleanUnaryOperator} with the given argument and returns its result.
     *
     * @param operator The operator to be called
     * @param value The argument to the operator
     * @return The result from the given {@code BooleanUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static boolean call(@Nonnull BooleanUnaryOperator operator, boolean value) {
        Objects.requireNonNull(operator);
        return operator.applyAsBoolean(value);
    }

    /**
     * Returns a {@link BooleanUnaryOperator} that always returns its input argument.
     *
     * @return A {@code BooleanUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static BooleanUnaryOperator identity() {
        return value -> value;
    }

    /**
     * Creates a {@link BooleanUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static BooleanUnaryOperator constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param value The argument to the operator
     * @return The return value from the operator, which is its result.
     */
    boolean applyAsBoolean(boolean value);

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
     * Returns a composed {@link Predicate2} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given predicate, and of composed predicate
     * @param before The predicate to apply before this operator is applied
     * @return A composed {@code Predicate2} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> Predicate2<A> compose(@Nonnull Predicate<? super A> before) {
        Objects.requireNonNull(before);
        return a -> applyAsBoolean(before.test(a));
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanUnaryOperator composeFromBoolean(@Nonnull BooleanUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsBoolean(before.applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BytePredicate} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before The predicate to apply before this operator is applied
     * @return A composed {@code BytePredicate} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BytePredicate composeFromByte(@Nonnull BytePredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsBoolean(before.test(value));
    }

    /**
     * Returns a composed {@link CharPredicate} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before The predicate to apply before this operator is applied
     * @return A composed {@code CharPredicate} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharPredicate composeFromChar(@Nonnull CharPredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsBoolean(before.test(value));
    }

    /**
     * Returns a composed {@link DoublePredicate2} that first applies the {@code before} predicate to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before The predicate to apply before this operator is applied
     * @return A composed {@code DoublePredicate2} that first applies the {@code before} predicate to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoublePredicate2 composeFromDouble(@Nonnull DoublePredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsBoolean(before.test(value));
    }

    /**
     * Returns a composed {@link FloatPredicate} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before The predicate to apply before this operator is applied
     * @return A composed {@code FloatPredicate} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatPredicate composeFromFloat(@Nonnull FloatPredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsBoolean(before.test(value));
    }

    /**
     * Returns a composed {@link IntPredicate2} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before The predicate to apply before this operator is applied
     * @return A composed {@code IntPredicate2} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntPredicate2 composeFromInt(@Nonnull IntPredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsBoolean(before.test(value));
    }

    /**
     * Returns a composed {@link LongPredicate2} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before The predicate to apply before this operator is applied
     * @return A composed {@code LongPredicate2} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongPredicate2 composeFromLong(@Nonnull LongPredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsBoolean(before.test(value));
    }

    /**
     * Returns a composed {@link ShortPredicate} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before The predicate to apply before this operator is applied
     * @return A composed {@code ShortPredicate} that first applies the {@code before} predicate to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortPredicate composeFromShort(@Nonnull ShortPredicate before) {
        Objects.requireNonNull(before);
        return value -> applyAsBoolean(before.test(value));
    }

    /**
     * Returns a composed {@link BooleanFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BooleanFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BooleanFunction<S> andThen(@Nonnull BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanUnaryOperator andThenToBoolean(@Nonnull BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BooleanToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BooleanToByteFunction andThenToByte(@Nonnull BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BooleanToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BooleanToCharFunction andThenToChar(@Nonnull BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BooleanToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BooleanToDoubleFunction andThenToDouble(@Nonnull BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BooleanToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BooleanToFloatFunction andThenToFloat(@Nonnull BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BooleanToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BooleanToIntFunction andThenToInt(@Nonnull BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BooleanToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BooleanToLongFunction andThenToLong(@Nonnull BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BooleanToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BooleanToShortFunction andThenToShort(@Nonnull BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BooleanConsumer} that first applies this operator to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanConsumer consume(@Nonnull BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsBoolean(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link BooleanUnaryOperator}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BooleanUnaryOperator}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BooleanUnaryOperator memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Boolean, Boolean> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (BooleanUnaryOperator & Memoized) value -> {
                boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::applyAsBoolean);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link UnaryOperator2} which represents this {@link BooleanUnaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed. This method provides the possibility to use this {@code
     * BooleanUnaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code UnaryOperator2} which represents this {@code BooleanUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator2<Boolean> boxed() {
        return this::applyAsBoolean;
    }

}
