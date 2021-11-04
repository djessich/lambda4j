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

package org.lambda4j.predicate;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.consumer.ByteConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.conversion.ByteToCharFunction;
import org.lambda4j.function.conversion.ByteToDoubleFunction;
import org.lambda4j.function.conversion.ByteToFloatFunction;
import org.lambda4j.function.conversion.ByteToIntFunction;
import org.lambda4j.function.conversion.ByteToLongFunction;
import org.lambda4j.function.conversion.ByteToShortFunction;
import org.lambda4j.function.conversion.CharToByteFunction;
import org.lambda4j.function.conversion.DoubleToByteFunction;
import org.lambda4j.function.conversion.FloatToByteFunction;
import org.lambda4j.function.conversion.IntToByteFunction;
import org.lambda4j.function.conversion.LongToByteFunction;
import org.lambda4j.function.conversion.ShortToByteFunction;
import org.lambda4j.function.to.ToByteFunction;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.operator.unary.ByteUnaryOperator;

/**
 * Represents an predicate (boolean-valued function) of one {@code byte}-valued input argument. This is a primitive
 * specialization of {@link Predicate2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(byte)}.
 *
 * @see Predicate2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BytePredicate extends Lambda {

    /**
     * Constructs a {@link BytePredicate} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BytePredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static BytePredicate of(@Nullable BytePredicate expression) {
        return expression;
    }

    /**
     * Calls the given {@link BytePredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code BytePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static boolean call(@Nonnull BytePredicate predicate, byte value) {
        Objects.requireNonNull(predicate);
        return predicate.test(value);
    }

    /**
     * Creates a {@link BytePredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BytePredicate} which always returns a given value.
     */
    @Nonnull
    static BytePredicate constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Returns a {@link BytePredicate} that always returns {@code true}.
     *
     * @return A {@link BytePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static BytePredicate alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link BytePredicate} that always returns {@code false}.
     *
     * @return A {@link BytePredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static BytePredicate alwaysFalse() {
        return value -> false;
    }

    /**
     * Returns a {@link BytePredicate} that tests if the given argument are <b>equal</b> to the one of this predicate.
     *
     * @param target The reference with which to compare for equality, which may be {@code null}
     * @return A {@code BytePredicate} that tests if the given argument are <b>equal</b> to the one of this predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static BytePredicate isEqual(byte target) {
        return value -> value == target;
    }

    /**
     * Applies this predicate to the given argument.
     *
     * @param value The argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(byte value);

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link Predicate2} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed predicate
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code Predicate2} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> Predicate2<A> compose(@Nonnull ToByteFunction<? super A> before) {
        Objects.requireNonNull(before);
        return a -> test(before.applyAsByte(a));
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies the {@code before} function to its input, and
     * then applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies the {@code before} function to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanUnaryOperator composeFromBoolean(@Nonnull BooleanToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link BytePredicate} that first applies the {@code before} operator to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive predicate is executed.
     *
     * @param before The operator to apply before this predicate is applied
     * @return A composed {@code BytePredicate} that first applies the {@code before} operator to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BytePredicate composeFromByte(@Nonnull ByteUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link CharPredicate} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code CharPredicate} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharPredicate composeFromChar(@Nonnull CharToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoublePredicate2} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code DoublePredicate2} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoublePredicate2 composeFromDouble(@Nonnull DoubleToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link FloatPredicate} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code FloatPredicate} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatPredicate composeFromFloat(@Nonnull FloatToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link IntPredicate2} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code IntPredicate2} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntPredicate2 composeFromInt(@Nonnull IntToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongPredicate2} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code LongPredicate2} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongPredicate2 composeFromLong(@Nonnull LongToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ShortPredicate} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code ShortPredicate} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortPredicate composeFromShort(@Nonnull ShortToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ByteFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ByteFunction<S> andThen(@Nonnull BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(test(value));
    }

    /**
     * Returns a composed {@link BytePredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code BytePredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BytePredicate andThenToBoolean(@Nonnull BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(test(value));
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ByteUnaryOperator} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteUnaryOperator andThenToByte(@Nonnull BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(test(value));
    }

    /**
     * Returns a composed {@link ByteToCharFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ByteToCharFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ByteToCharFunction andThenToChar(@Nonnull BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(test(value));
    }

    /**
     * Returns a composed {@link ByteToDoubleFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ByteToDoubleFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ByteToDoubleFunction andThenToDouble(@Nonnull BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(test(value));
    }

    /**
     * Returns a composed {@link ByteToFloatFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ByteToFloatFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ByteToFloatFunction andThenToFloat(@Nonnull BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(test(value));
    }

    /**
     * Returns a composed {@link ByteToIntFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ByteToIntFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ByteToIntFunction andThenToInt(@Nonnull BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(test(value));
    }

    /**
     * Returns a composed {@link ByteToLongFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ByteToLongFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ByteToLongFunction andThenToLong(@Nonnull BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(test(value));
    }

    /**
     * Returns a composed {@link ByteToShortFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ByteToShortFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ByteToShortFunction andThenToShort(@Nonnull BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(test(value));
    }

    /**
     * Returns a composed {@link ByteConsumer} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ByteConsumer} that first applies this predicate to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteConsumer consume(@Nonnull BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(test(value));
    }

    /**
     * Returns a {@link BytePredicate} that represents the logical negation of this one.
     *
     * @return A {@code BytePredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default BytePredicate negate() {
        return value -> !test(value);
    }

    /**
     * Returns a composed {@link BytePredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code BytePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BytePredicate} that will be logically-ANDed with this one
     * @return A composed {@code BytePredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(BytePredicate)
     * @see #xor(BytePredicate)
     */
    @Nonnull
    default BytePredicate and(@Nonnull BytePredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link BytePredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code BytePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BytePredicate} that will be logically-ORed with this one
     * @return A composed {@code BytePredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(BytePredicate)
     * @see #xor(BytePredicate)
     */
    @Nonnull
    default BytePredicate or(@Nonnull BytePredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) || other.test(value);
    }

    /**
     * Returns a composed {@link BytePredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of
     * this {@code BytePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BytePredicate} that will be logically-XORed with this one
     * @return A composed {@code BytePredicate} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(BytePredicate)
     * @see #or(BytePredicate)
     */
    @Nonnull
    default BytePredicate xor(@Nonnull BytePredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) ^ other.test(value);
    }

    /**
     * Returns a reversed version of this predicate. This may be useful in recursive context.
     *
     * @return A reversed version of this predicate.
     */
    @Nonnull
    default BytePredicate reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link BytePredicate}. Whenever it is called, the mapping between
     * the input parameter and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BytePredicate}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BytePredicate memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Byte, Boolean> cache = new ConcurrentHashMap<>();
            return (BytePredicate & Memoized) value -> {
                return cache.computeIfAbsent(value, this::test);
            };
        }
    }

    /**
     * Returns a composed {@link Predicate2} which represents this {@link BytePredicate}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method provides the possibility to use this {@code BytePredicate}
     * with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Predicate2} which represents this {@code BytePredicate}.
     */
    @Nonnull
    default Predicate2<Byte> boxed() {
        return this::test;
    }

}
