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
package at.gridtec.lambda4j.predicate;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.BooleanConsumer;
import at.gridtec.lambda4j.consumer.CharConsumer;
import at.gridtec.lambda4j.function.BooleanFunction;
import at.gridtec.lambda4j.function.CharFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.conversion.CharToByteFunction;
import at.gridtec.lambda4j.function.conversion.CharToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.CharToFloatFunction;
import at.gridtec.lambda4j.function.conversion.CharToIntFunction;
import at.gridtec.lambda4j.function.conversion.CharToLongFunction;
import at.gridtec.lambda4j.function.conversion.CharToShortFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToCharFunction;
import at.gridtec.lambda4j.function.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.function.conversion.IntToCharFunction;
import at.gridtec.lambda4j.function.conversion.LongToCharFunction;
import at.gridtec.lambda4j.function.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.function.to.ToCharFunction;
import at.gridtec.lambda4j.operator.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.operator.unary.CharUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * Represents an predicate (boolean-valued function) of one {@code char}-valued input argument. This is a primitive
 * specialization of {@link Predicate2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(char)}.
 *
 * @see Predicate2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharPredicate extends Lambda {

    /**
     * Constructs a {@link CharPredicate} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code CharPredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static CharPredicate of(@Nullable final CharPredicate expression) {
        return expression;
    }

    /**
     * Calls the given {@link CharPredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code CharPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static boolean call(@Nonnull final CharPredicate predicate, char value) {
        Objects.requireNonNull(predicate);
        return predicate.test(value);
    }

    /**
     * Creates a {@link CharPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharPredicate} which always returns a given value.
     */
    @Nonnull
    static CharPredicate constant(boolean ret) {
        return (value) -> ret;
    }

    /**
     * Returns a {@link CharPredicate} that always returns {@code true}.
     *
     * @return A {@link CharPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static CharPredicate alwaysTrue() {
        return (value) -> true;
    }

    /**
     * Returns a {@link CharPredicate} that always returns {@code false}.
     *
     * @return A {@link CharPredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static CharPredicate alwaysFalse() {
        return (value) -> false;
    }

    /**
     * Returns a {@link CharPredicate} that tests if the given argument are <b>equal</b> to the one of this predicate.
     *
     * @param target The reference with which to compare for equality, which may be {@code null}
     * @return A {@code CharPredicate} that tests if the given argument are <b>equal</b> to the one of this predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static CharPredicate isEqual(char target) {
        return (value) -> (value == target);
    }

    /**
     * Applies this predicate to the given argument.
     *
     * @param value The argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(char value);

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
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> Predicate2<A> compose(@Nonnull final ToCharFunction<? super A> before) {
        Objects.requireNonNull(before);
        return (a) -> test(before.applyAsChar(a));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanUnaryOperator composeFromBoolean(@Nonnull final BooleanToCharFunction before) {
        Objects.requireNonNull(before);
        return (value) -> test(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link BytePredicate} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive predicate is executed.
     *
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code BytePredicate} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BytePredicate composeFromByte(@Nonnull final ByteToCharFunction before) {
        Objects.requireNonNull(before);
        return (value) -> test(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharPredicate} that first applies the {@code before} operator to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive predicate is executed.
     *
     * @param before The operator to apply before this predicate is applied
     * @return A composed {@code CharPredicate} that first applies the {@code before} operator to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharPredicate composeFromChar(@Nonnull final CharUnaryOperator before) {
        Objects.requireNonNull(before);
        return (value) -> test(before.applyAsChar(value));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoublePredicate2 composeFromDouble(@Nonnull final DoubleToCharFunction before) {
        Objects.requireNonNull(before);
        return (value) -> test(before.applyAsChar(value));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatPredicate composeFromFloat(@Nonnull final FloatToCharFunction before) {
        Objects.requireNonNull(before);
        return (value) -> test(before.applyAsChar(value));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntPredicate2 composeFromInt(@Nonnull final IntToCharFunction before) {
        Objects.requireNonNull(before);
        return (value) -> test(before.applyAsChar(value));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongPredicate2 composeFromLong(@Nonnull final LongToCharFunction before) {
        Objects.requireNonNull(before);
        return (value) -> test(before.applyAsChar(value));
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
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortPredicate composeFromShort(@Nonnull final ShortToCharFunction before) {
        Objects.requireNonNull(before);
        return (value) -> test(before.applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code CharFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> CharFunction<S> andThen(@Nonnull final BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value) -> after.apply(test(value));
    }

    /**
     * Returns a composed {@link CharPredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code CharPredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default CharPredicate andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsBoolean(test(value));
    }

    /**
     * Returns a composed {@link CharToByteFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code CharToByteFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default CharToByteFunction andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsByte(test(value));
    }

    /**
     * Returns a composed {@link CharUnaryOperator} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code CharUnaryOperator} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharUnaryOperator andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsChar(test(value));
    }

    /**
     * Returns a composed {@link CharToDoubleFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code CharToDoubleFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default CharToDoubleFunction andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsDouble(test(value));
    }

    /**
     * Returns a composed {@link CharToFloatFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code CharToFloatFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default CharToFloatFunction andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsFloat(test(value));
    }

    /**
     * Returns a composed {@link CharToIntFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code CharToIntFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default CharToIntFunction andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsInt(test(value));
    }

    /**
     * Returns a composed {@link CharToLongFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code CharToLongFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default CharToLongFunction andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsLong(test(value));
    }

    /**
     * Returns a composed {@link CharToShortFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code CharToShortFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default CharToShortFunction andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsShort(test(value));
    }

    /**
     * Returns a composed {@link CharConsumer} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code CharConsumer} that first applies this predicate to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharConsumer consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.accept(test(value));
    }

    /**
     * Returns a {@link CharPredicate} that represents the logical negation of this one.
     *
     * @return A {@code CharPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default CharPredicate negate() {
        return (value) -> !test(value);
    }

    /**
     * Returns a composed {@link CharPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code CharPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code CharPredicate} that will be logically-ANDed with this one
     * @return A composed {@code CharPredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(CharPredicate)
     * @see #xor(CharPredicate)
     */
    @Nonnull
    default CharPredicate and(@Nonnull final CharPredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link CharPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code CharPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code CharPredicate} that will be logically-ORed with this one
     * @return A composed {@code CharPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(CharPredicate)
     * @see #xor(CharPredicate)
     */
    @Nonnull
    default CharPredicate or(@Nonnull final CharPredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) || other.test(value);
    }

    /**
     * Returns a composed {@link CharPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of
     * this {@code CharPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code CharPredicate} that will be logically-XORed with this one
     * @return A composed {@code CharPredicate} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(CharPredicate)
     * @see #or(CharPredicate)
     */
    @Nonnull
    default CharPredicate xor(@Nonnull final CharPredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) ^ other.test(value);
    }

    /**
     * Returns a memoized (caching) version of this {@link CharPredicate}. Whenever it is called, the mapping between
     * the input parameter and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code CharPredicate}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default CharPredicate memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Character, Boolean> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (CharPredicate & Memoized) (value) -> {
                final boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::test);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Predicate} which represents this {@link CharPredicate}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code CharPredicate} with JDK specific methods, only accepting {@code Predicate}.
     *
     * @return A composed {@code Predicate} which represents this {@code CharPredicate}.
     */
    @Nonnull
    default Predicate<Character> boxed() {
        return this::test;
    }

}