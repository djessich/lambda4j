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
package at.gridtec.lambda4j.function.conversion;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ByteConsumer;
import at.gridtec.lambda4j.consumer.DoubleConsumer2;
import at.gridtec.lambda4j.function.ByteFunction;
import at.gridtec.lambda4j.function.DoubleFunction2;
import at.gridtec.lambda4j.function.Function2;
import at.gridtec.lambda4j.function.to.ToByteFunction;
import at.gridtec.lambda4j.operator.unary.ByteUnaryOperator;
import at.gridtec.lambda4j.operator.unary.DoubleUnaryOperator2;
import at.gridtec.lambda4j.predicate.BytePredicate;
import at.gridtec.lambda4j.predicate.DoublePredicate2;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

/**
 * Represents an operation that accepts one {@code double}-valued input argument and produces a {@code byte}-valued
 * result. This is a primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(double)}.
 *
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleToByteFunction extends Lambda {

    /**
     * Constructs a {@link DoubleToByteFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code DoubleToByteFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static DoubleToByteFunction of(@Nullable final DoubleToByteFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link DoubleToByteFunction} with the given argument and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code DoubleToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static byte call(@Nonnull final DoubleToByteFunction function, double value) {
        Objects.requireNonNull(function);
        return function.applyAsByte(value);
    }

    /**
     * Creates a {@link DoubleToByteFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code DoubleToByteFunction} which always returns a given value.
     */
    @Nonnull
    static DoubleToByteFunction constant(byte ret) {
        return (value) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(double value);

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
     * Returns a composed {@link ToByteFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToByteFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToByteFunction<A> compose(@Nonnull final ToDoubleFunction<? super A> before) {
        Objects.requireNonNull(before);
        return (a) -> applyAsByte(before.applyAsDouble(a));
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code BooleanToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToByteFunction composeFromBoolean(@Nonnull final BooleanToDoubleFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ByteUnaryOperator} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteUnaryOperator composeFromByte(@Nonnull final ByteToDoubleFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link CharToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code CharToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharToByteFunction composeFromChar(@Nonnull final CharToDoubleFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link DoubleToByteFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code DoubleToByteFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleToByteFunction composeFromDouble(@Nonnull final DoubleUnaryOperator before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link FloatToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code FloatToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatToByteFunction composeFromFloat(@Nonnull final FloatToDoubleFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link IntToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code IntToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntToByteFunction composeFromInt(@Nonnull final IntToDoubleFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link LongToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code LongToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongToByteFunction composeFromLong(@Nonnull final LongToDoubleFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link ShortToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ShortToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToByteFunction composeFromShort(@Nonnull final ShortToDoubleFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsByte(before.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link DoubleFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleFunction2} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> DoubleFunction2<S> andThen(@Nonnull final ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value) -> after.apply(applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoublePredicate2} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code DoublePredicate2} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default DoublePredicate2 andThenToBoolean(@Nonnull final BytePredicate after) {
        Objects.requireNonNull(after);
        return (value) -> after.test(applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoubleToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code DoubleToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default DoubleToByteFunction andThenToByte(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsByte(applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoubleToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default DoubleToCharFunction andThenToChar(@Nonnull final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsChar(applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoubleUnaryOperator2} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleUnaryOperator2} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleUnaryOperator2 andThenToDouble(@Nonnull final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsDouble(applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoubleToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default DoubleToFloatFunction andThenToFloat(@Nonnull final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsFloat(applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoubleToIntFunction2} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToIntFunction2} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default DoubleToIntFunction2 andThenToInt(@Nonnull final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsInt(applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoubleToLongFunction2} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToLongFunction2} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default DoubleToLongFunction2 andThenToLong(@Nonnull final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsLong(applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoubleToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code DoubleToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default DoubleToShortFunction andThenToShort(@Nonnull final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsShort(applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoubleConsumer2} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code DoubleConsumer2} that first applies this function to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default DoubleConsumer2 consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.accept(applyAsByte(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link DoubleToByteFunction}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code DoubleToByteFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default DoubleToByteFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Double, Byte> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (DoubleToByteFunction & Memoized) (value) -> {
                final byte returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::applyAsByte);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Function} which represents this {@link DoubleToByteFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code DoubleToByteFunction} with JDK specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code DoubleToByteFunction}.
     */
    @Nonnull
    default Function<Double, Byte> boxed() {
        return this::applyAsByte;
    }

}