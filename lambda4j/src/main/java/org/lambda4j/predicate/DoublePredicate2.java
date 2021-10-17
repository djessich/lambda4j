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
import java.util.function.DoublePredicate;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.consumer.DoubleConsumer2;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.DoubleFunction2;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.conversion.ByteToDoubleFunction;
import org.lambda4j.function.conversion.CharToDoubleFunction;
import org.lambda4j.function.conversion.DoubleToByteFunction;
import org.lambda4j.function.conversion.DoubleToCharFunction;
import org.lambda4j.function.conversion.DoubleToFloatFunction;
import org.lambda4j.function.conversion.DoubleToIntFunction2;
import org.lambda4j.function.conversion.DoubleToLongFunction2;
import org.lambda4j.function.conversion.DoubleToShortFunction;
import org.lambda4j.function.conversion.FloatToDoubleFunction;
import org.lambda4j.function.conversion.ShortToDoubleFunction;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.operator.unary.DoubleUnaryOperator2;

/**
 * Represents an predicate (boolean-valued function) of one {@code double}-valued input argument. This is a primitive
 * specialization of {@link Predicate2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(double)}.
 *
 * @apiNote This is a JDK lambda.
 * @see Predicate2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoublePredicate2 extends Lambda, DoublePredicate {

    /**
     * Constructs a {@link DoublePredicate2} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code DoublePredicate2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static DoublePredicate2 of(@Nullable DoublePredicate2 expression) {
        return expression;
    }

    /**
     * Calls the given {@link DoublePredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code DoublePredicate2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static boolean call(@Nonnull DoublePredicate predicate, double value) {
        Objects.requireNonNull(predicate);
        return predicate.test(value);
    }

    /**
     * Creates a {@link DoublePredicate2} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code DoublePredicate2} which always returns a given value.
     */
    @Nonnull
    static DoublePredicate2 constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Returns a {@link DoublePredicate2} that always returns {@code true}.
     *
     * @return A {@link DoublePredicate2} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static DoublePredicate2 alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link DoublePredicate2} that always returns {@code false}.
     *
     * @return A {@link DoublePredicate2} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static DoublePredicate2 alwaysFalse() {
        return value -> false;
    }

    /**
     * Returns a {@link DoublePredicate2} that tests if the given argument are <b>equal</b> to the one of this
     * predicate.
     *
     * @param target The reference with which to compare for equality, which may be {@code null}
     * @return A {@code DoublePredicate2} that tests if the given argument are <b>equal</b> to the one of this
     * predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static DoublePredicate2 isEqual(double target) {
        return value -> value == target;
    }

    /**
     * Applies this predicate to the given argument.
     *
     * @param value The argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    @Override
    boolean test(double value);

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
    default <A> Predicate2<A> compose(@Nonnull ToDoubleFunction<? super A> before) {
        Objects.requireNonNull(before);
        return a -> test(before.applyAsDouble(a));
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
    default BooleanUnaryOperator composeFromBoolean(@Nonnull BooleanToDoubleFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsDouble(value));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BytePredicate composeFromByte(@Nonnull ByteToDoubleFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsDouble(value));
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
    default CharPredicate composeFromChar(@Nonnull CharToDoubleFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link DoublePredicate2} that first applies the {@code before} operator to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before The operator to apply before this predicate is applied
     * @return A composed {@code DoublePredicate2} that first applies the {@code before} operator to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoublePredicate2 composeFromDouble(@Nonnull DoubleUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsDouble(value));
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
    default FloatPredicate composeFromFloat(@Nonnull FloatToDoubleFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsDouble(value));
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
    default IntPredicate2 composeFromInt(@Nonnull IntToDoubleFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsDouble(value));
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
    default LongPredicate2 composeFromLong(@Nonnull LongToDoubleFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsDouble(value));
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
    default ShortPredicate composeFromShort(@Nonnull ShortToDoubleFunction before) {
        Objects.requireNonNull(before);
        return value -> test(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link DoubleFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code DoubleFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> DoubleFunction2<S> andThen(@Nonnull BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(test(value));
    }

    /**
     * Returns a composed {@link DoublePredicate2} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code DoublePredicate2} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default DoublePredicate2 andThenToBoolean(@Nonnull BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(test(value));
    }

    /**
     * Returns a composed {@link DoubleToByteFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code DoubleToByteFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default DoubleToByteFunction andThenToByte(@Nonnull BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(test(value));
    }

    /**
     * Returns a composed {@link DoubleToCharFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code DoubleToCharFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default DoubleToCharFunction andThenToChar(@Nonnull BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(test(value));
    }

    /**
     * Returns a composed {@link DoubleUnaryOperator2} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code DoubleUnaryOperator2} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleUnaryOperator2 andThenToDouble(@Nonnull BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(test(value));
    }

    /**
     * Returns a composed {@link DoubleToFloatFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code DoubleToFloatFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default DoubleToFloatFunction andThenToFloat(@Nonnull BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(test(value));
    }

    /**
     * Returns a composed {@link DoubleToIntFunction2} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code DoubleToIntFunction2} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default DoubleToIntFunction2 andThenToInt(@Nonnull BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(test(value));
    }

    /**
     * Returns a composed {@link DoubleToLongFunction2} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code DoubleToLongFunction2} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default DoubleToLongFunction2 andThenToLong(@Nonnull BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(test(value));
    }

    /**
     * Returns a composed {@link DoubleToShortFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code DoubleToShortFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default DoubleToShortFunction andThenToShort(@Nonnull BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(test(value));
    }

    /**
     * Returns a composed {@link DoubleConsumer2} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code DoubleConsumer2} that first applies this predicate to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default DoubleConsumer2 consume(@Nonnull BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(test(value));
    }

    /**
     * Returns a {@link DoublePredicate2} that represents the logical negation of this one.
     *
     * @return A {@code DoublePredicate2} that represents the logical negation of this one.
     */
    @Override
    @Nonnull
    default DoublePredicate2 negate() {
        return value -> !test(value);
    }

    /**
     * Returns a composed {@link DoublePredicate2} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code DoublePredicate2} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code DoublePredicate2} that will be logically-ANDed with this one
     * @return A composed {@code DoublePredicate2} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(DoublePredicate)
     * @see #xor(DoublePredicate)
     */
    @Override
    @Nonnull
    default DoublePredicate2 and(@Nonnull DoublePredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link DoublePredicate2} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code DoublePredicate2} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code DoublePredicate2} that will be logically-ORed with this one
     * @return A composed {@code DoublePredicate2} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(DoublePredicate)
     * @see #xor(DoublePredicate)
     */
    @Override
    @Nonnull
    default DoublePredicate2 or(@Nonnull DoublePredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) || other.test(value);
    }

    /**
     * Returns a composed {@link DoublePredicate2} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of
     * this {@code DoublePredicate2} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code DoublePredicate2} that will be logically-XORed with this one
     * @return A composed {@code DoublePredicate2} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(DoublePredicate)
     * @see #or(DoublePredicate)
     */
    @Nonnull
    default DoublePredicate2 xor(@Nonnull DoublePredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) ^ other.test(value);
    }

    /**
     * Returns a memoized (caching) version of this {@link DoublePredicate2}. Whenever it is called, the mapping between
     * the input parameter and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code DoublePredicate2}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default DoublePredicate2 memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Double, Boolean> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (DoublePredicate2 & Memoized) value -> {
                boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::test);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Predicate2} which represents this {@link DoublePredicate2}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method provides the possibility to use this {@code
     * DoublePredicate2} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Predicate2} which represents this {@code DoublePredicate2}.
     */
    @Nonnull
    default Predicate2<Double> boxed() {
        return this::test;
    }

}
