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
package at.gridtec.lambda4j.function.bi.conversion;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.bi.BiFloatConsumer;
import at.gridtec.lambda4j.function.bi.BiFloatFunction;
import at.gridtec.lambda4j.function.bi.BiFunction2;
import at.gridtec.lambda4j.function.bi.to.ToDoubleBiFunction2;
import at.gridtec.lambda4j.function.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.conversion.CharToFloatFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToByteFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToCharFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToFloatFunction;
import at.gridtec.lambda4j.function.conversion.DoubleToShortFunction;
import at.gridtec.lambda4j.function.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.IntToFloatFunction;
import at.gridtec.lambda4j.function.conversion.LongToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.function.to.ToFloatFunction;
import at.gridtec.lambda4j.operator.binary.DoubleBinaryOperator2;
import at.gridtec.lambda4j.operator.binary.FloatBinaryOperator;
import at.gridtec.lambda4j.operator.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.predicate.bi.BiFloatPredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;

/**
 * Represents an operation that accepts two {@code float}-valued input arguments and produces a {@code double}-valued
 * result. This is a primitive specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(float, float)}.
 *
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiFloatToDoubleFunction extends Lambda {

    /**
     * Constructs a {@link BiFloatToDoubleFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiFloatToDoubleFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static BiFloatToDoubleFunction of(@Nonnull final BiFloatToDoubleFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiFloatToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiFloatToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static double call(@Nonnull final BiFloatToDoubleFunction function, float value1, float value2) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(value1, value2);
    }

    /**
     * Creates a {@link BiFloatToDoubleFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link FloatToDoubleFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiFloatToDoubleFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code FloatToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BiFloatToDoubleFunction onlyFirst(@Nonnull final FloatToDoubleFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsDouble(value1);
    }

    /**
     * Creates a {@link BiFloatToDoubleFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link FloatToDoubleFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiFloatToDoubleFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code FloatToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BiFloatToDoubleFunction onlySecond(@Nonnull final FloatToDoubleFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsDouble(value2);
    }

    /**
     * Creates a {@link BiFloatToDoubleFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiFloatToDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static BiFloatToDoubleFunction constant(double ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    double applyAsDouble(float value1, float value2);

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToDoubleBiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToDoubleBiFunction2<A, B> compose(@Nonnull final ToFloatFunction<? super A> before1,
            @Nonnull final ToFloatFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsDouble(before1.applyAsFloat(a), before2.applyAsFloat(b));
    }

    /**
     * Returns a composed {@link BiBooleanToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiBooleanToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToDoubleFunction composeFromBoolean(@Nonnull final BooleanToFloatFunction before1,
            @Nonnull final BooleanToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiByteToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiByteToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteToDoubleFunction composeFromByte(@Nonnull final ByteToFloatFunction before1,
            @Nonnull final ByteToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiCharToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiCharToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToDoubleFunction composeFromChar(@Nonnull final CharToFloatFunction before1,
            @Nonnull final CharToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link DoubleBinaryOperator2} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code DoubleBinaryOperator2} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleBinaryOperator2 composeFromDouble(@Nonnull final DoubleToFloatFunction before1,
            @Nonnull final DoubleToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiFloatToDoubleFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiFloatToDoubleFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatToDoubleFunction composeFromFloat(@Nonnull final FloatUnaryOperator before1,
            @Nonnull final FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiIntToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiIntToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntToDoubleFunction composeFromInt(@Nonnull final IntToFloatFunction before1,
            @Nonnull final IntToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiLongToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiLongToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongToDoubleFunction composeFromLong(@Nonnull final LongToFloatFunction before1,
            @Nonnull final LongToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiShortToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiShortToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToDoubleFunction composeFromShort(@Nonnull final ShortToFloatFunction before1,
            @Nonnull final ShortToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiFloatFunction<S> andThen(@Nonnull final DoubleFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code BiFloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiFloatPredicate andThenToBoolean(@Nonnull final DoublePredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.test(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiFloatToByteFunction andThenToByte(@Nonnull final DoubleToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiFloatToCharFunction andThenToChar(@Nonnull final DoubleToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code BiFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiFloatToDoubleFunction andThenToDouble(@Nonnull final DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatBinaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatBinaryOperator andThenToFloat(@Nonnull final DoubleToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiFloatToIntFunction andThenToInt(@Nonnull final DoubleToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiFloatToLongFunction andThenToLong(@Nonnull final DoubleToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiFloatToShortFunction andThenToShort(@Nonnull final DoubleToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link DoubleConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiFloatConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiFloatConsumer consume(@Nonnull final DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsDouble(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link BiFloatToDoubleFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiFloatToDoubleFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiFloatToDoubleFunction memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Pair<Float, Float>, Double> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (BiFloatToDoubleFunction & Memoized) (value1, value2) -> {
                final double returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2),
                                                        key -> applyAsDouble(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link BiFloatToDoubleFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method is just convenience to provide the ability
     * to use this {@code BiFloatToDoubleFunction} with JDK specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code BiFloatToDoubleFunction}.
     */
    @Nonnull
    default BiFunction<Float, Float, Double> boxed() {
        return this::applyAsDouble;
    }

}