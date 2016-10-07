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
package at.gridtec.lambda4j.operator.unary;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.FloatConsumer;
import at.gridtec.lambda4j.function.FloatFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.conversion.CharToFloatFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToFloatFunction;
import at.gridtec.lambda4j.function.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.function.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.function.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.function.conversion.FloatToLongFunction;
import at.gridtec.lambda4j.function.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.function.conversion.IntToFloatFunction;
import at.gridtec.lambda4j.function.conversion.LongToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.function.to.ToFloatFunction;
import at.gridtec.lambda4j.predicate.FloatPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts one {@code float}-valued input argument and produces a
 * {@code float}-valued result.
 * This is a primitive specialization of {@link UnaryOperator2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(float)}.
 *
 * @see UnaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatUnaryOperator extends Lambda {

    /**
     * Constructs a {@link FloatUnaryOperator} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code FloatUnaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static FloatUnaryOperator of(@Nullable final FloatUnaryOperator expression) {
        return expression;
    }

    /**
     * Calls the given {@link FloatUnaryOperator} with the given argument and returns its result.
     *
     * @param operator The operator to be called
     * @param value The argument to the operator
     * @return The result from the given {@code FloatUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static float call(@Nonnull final FloatUnaryOperator operator, float value) {
        Objects.requireNonNull(operator);
        return operator.applyAsFloat(value);
    }

    /**
     * Returns a {@link FloatUnaryOperator} that always returns its input argument.
     *
     * @return A {@code  FloatUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static FloatUnaryOperator identity() {
        return (value) -> value;
    }

    /**
     * Creates a {@link FloatUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static FloatUnaryOperator constant(float ret) {
        return (value) -> ret;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param value The argument to the operator
     * @return The return value from the operator, which is its result.
     */
    float applyAsFloat(float value);

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
     * Returns a composed {@link ToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ToFloatFunction} that first applies the {@code before} function to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToFloatFunction<A> compose(@Nonnull final ToFloatFunction<? super A> before) {
        Objects.requireNonNull(before);
        return (a) -> applyAsFloat(before.applyAsFloat(a));
    }

    /**
     * Returns a composed {@link BooleanToFloatFunction} that first applies the {@code before} function to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code BooleanToFloatFunction} that first applies the {@code before} function to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToFloatFunction composeFromBoolean(@Nonnull final BooleanToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsFloat(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteToFloatFunction} that first applies the {@code before} function to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ByteToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteToFloatFunction composeFromByte(@Nonnull final ByteToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsFloat(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link CharToFloatFunction} that first applies the {@code before} function to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code CharToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharToFloatFunction composeFromChar(@Nonnull final CharToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsFloat(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link DoubleToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code DoubleToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleToFloatFunction composeFromDouble(@Nonnull final DoubleToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsFloat(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code FloatUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatUnaryOperator composeFromFloat(@Nonnull final FloatUnaryOperator before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsFloat(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link IntToFloatFunction} that first applies the {@code before} function to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code IntToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntToFloatFunction composeFromInt(@Nonnull final IntToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsFloat(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link LongToFloatFunction} that first applies the {@code before} function to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code LongToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongToFloatFunction composeFromLong(@Nonnull final LongToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsFloat(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ShortToFloatFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToFloatFunction composeFromShort(@Nonnull final ShortToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsFloat(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code FloatFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> FloatFunction<S> andThen(@Nonnull final FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value) -> after.apply(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatPredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code FloatPredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default FloatPredicate andThenToBoolean(@Nonnull final FloatPredicate after) {
        Objects.requireNonNull(after);
        return (value) -> after.test(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code FloatToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default FloatToByteFunction andThenToByte(@Nonnull final FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsByte(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code FloatToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default FloatToCharFunction andThenToChar(@Nonnull final FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsChar(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code FloatToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default FloatToDoubleFunction andThenToDouble(@Nonnull final FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsDouble(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code float}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code FloatUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatUnaryOperator andThenToFloat(@Nonnull final FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsFloat(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code FloatToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default FloatToIntFunction andThenToInt(@Nonnull final FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsInt(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code FloatToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default FloatToLongFunction andThenToLong(@Nonnull final FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsLong(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code FloatToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default FloatToShortFunction andThenToShort(@Nonnull final FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsShort(applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@link FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code FloatConsumer} that first applies this operator to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatConsumer consume(@Nonnull final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.accept(applyAsFloat(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link FloatUnaryOperator}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code FloatUnaryOperator}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default FloatUnaryOperator memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Float, Float> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (FloatUnaryOperator & Memoized) (value) -> {
                final float returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::applyAsFloat);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link UnaryOperator} which represents this {@link FloatUnaryOperator}. Thereby the primitive
     * input argument for this operator is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatUnaryOperator} with JDK specific methods, only accepting {@code UnaryOperator}.
     *
     * @return A composed {@code UnaryOperator} which represents this {@code FloatUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator<Float> boxed() {
        return this::applyAsFloat;
    }

}