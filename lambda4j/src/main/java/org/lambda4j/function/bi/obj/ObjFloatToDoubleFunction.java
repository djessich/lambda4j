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

package org.lambda4j.function.bi.obj;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.ToDoubleFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.bi.obj.ObjFloatConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.BiFunction2;
import org.lambda4j.function.bi.conversion.BiBooleanToDoubleFunction;
import org.lambda4j.function.bi.conversion.BiByteToDoubleFunction;
import org.lambda4j.function.bi.conversion.BiCharToDoubleFunction;
import org.lambda4j.function.bi.conversion.BiFloatToDoubleFunction;
import org.lambda4j.function.bi.conversion.BiIntToDoubleFunction;
import org.lambda4j.function.bi.conversion.BiLongToDoubleFunction;
import org.lambda4j.function.bi.conversion.BiShortToDoubleFunction;
import org.lambda4j.function.bi.to.ToDoubleBiFunction2;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.ByteToFloatFunction;
import org.lambda4j.function.conversion.CharToFloatFunction;
import org.lambda4j.function.conversion.DoubleToByteFunction;
import org.lambda4j.function.conversion.DoubleToCharFunction;
import org.lambda4j.function.conversion.DoubleToFloatFunction;
import org.lambda4j.function.conversion.DoubleToShortFunction;
import org.lambda4j.function.conversion.FloatToDoubleFunction;
import org.lambda4j.function.conversion.IntToFloatFunction;
import org.lambda4j.function.conversion.LongToFloatFunction;
import org.lambda4j.function.conversion.ShortToFloatFunction;
import org.lambda4j.function.to.ToDoubleFunction2;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.operator.binary.DoubleBinaryOperator2;
import org.lambda4j.operator.unary.FloatUnaryOperator;
import org.lambda4j.predicate.bi.obj.ObjFloatPredicate;

/**
 * Represents an operation that accepts one object-valued and one {@code float}-valued input argument and produces a
 * {@code double}-valued result. This is a (reference, float) specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(Object, float)}.
 *
 * @param <T> The type of the first argument to the function
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjFloatToDoubleFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjFloatToDoubleFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjFloatToDoubleFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T> ObjFloatToDoubleFunction<T> of(@Nullable ObjFloatToDoubleFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjFloatToDoubleFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjFloatToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> double call(@Nonnull ObjFloatToDoubleFunction<? super T> function, T t, float value) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(t, value);
    }

    /**
     * Creates a {@link ObjFloatToDoubleFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjFloatToDoubleFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjFloatToDoubleFunction<T> onlyFirst(@Nonnull ToDoubleFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsDouble(t);
    }

    /**
     * Creates a {@link ObjFloatToDoubleFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link FloatToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjFloatToDoubleFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code FloatToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjFloatToDoubleFunction<T> onlySecond(@Nonnull FloatToDoubleFunction function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsDouble(value);
    }

    /**
     * Creates a {@link ObjFloatToDoubleFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjFloatToDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjFloatToDoubleFunction<T> constant(double ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    double applyAsDouble(T t, float value);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link FloatToDoubleFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code FloatToDoubleFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default FloatToDoubleFunction applyAsDoublePartially(T t) {
        return value -> applyAsDouble(t, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToDoubleFunction2} as result.
     *
     * @param value The second argument to this function used to partially apply this function
     * @return A {@code ToDoubleFunction2} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToDoubleFunction2<T> applyAsDoublePartially(float value) {
        return t -> applyAsDouble(t, value);
    }

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
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToDoubleBiFunction2<A, B> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull ToFloatFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsDouble(before1.apply(a), before2.applyAsFloat(b));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToDoubleFunction composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.apply(value1), before2.applyAsFloat(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteToDoubleFunction composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.apply(value1), before2.applyAsFloat(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToDoubleFunction composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.apply(value1), before2.applyAsFloat(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleBinaryOperator2 composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiFloatToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiFloatToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatToDoubleFunction composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.apply(value1), before2.applyAsFloat(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntToDoubleFunction composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.apply(value1), before2.applyAsFloat(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongToDoubleFunction composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.apply(value1), before2.applyAsFloat(value2));
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
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToDoubleFunction composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link ObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjFloatFunction<T, S> andThen(@Nonnull DoubleFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjFloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjFloatPredicate<T> andThenToBoolean(@Nonnull DoublePredicate after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.test(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjFloatToByteFunction<T> andThenToByte(@Nonnull DoubleToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsByte(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjFloatToCharFunction<T> andThenToChar(@Nonnull DoubleToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsChar(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjFloatToDoubleFunction<T> andThenToDouble(@Nonnull DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsDouble(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjFloatToFloatFunction<T> andThenToFloat(@Nonnull DoubleToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsFloat(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjFloatToIntFunction<T> andThenToInt(@Nonnull DoubleToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsInt(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjFloatToLongFunction<T> andThenToLong(@Nonnull DoubleToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsLong(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjFloatToShortFunction<T> andThenToShort(@Nonnull DoubleToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsShort(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link DoubleConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjFloatConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjFloatConsumer<T> consume(@Nonnull DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsDouble(t, value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjFloatToDoubleFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjFloatToDoubleFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjFloatToDoubleFunction<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<T, Float>, Double> cache = new ConcurrentHashMap<>();
            return (ObjFloatToDoubleFunction<T> & Memoized) (t, value) -> {
                return cache.computeIfAbsent(Pair.of(t, value), key -> applyAsDouble(key.getLeft(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link BiFunction2} which represents this {@link ObjFloatToDoubleFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * ObjFloatToDoubleFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BiFunction2} which represents this {@code ObjFloatToDoubleFunction}.
     */
    @Nonnull
    default BiFunction2<T, Float, Double> boxed() {
        return this::applyAsDouble;
    }
}
