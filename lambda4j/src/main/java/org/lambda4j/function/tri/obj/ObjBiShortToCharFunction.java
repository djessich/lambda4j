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

package org.lambda4j.function.tri.obj;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.CharConsumer;
import org.lambda4j.consumer.tri.obj.ObjBiShortConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.conversion.BiShortToCharFunction;
import org.lambda4j.function.bi.obj.ObjShortToCharFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.conversion.ByteToShortFunction;
import org.lambda4j.function.conversion.CharToByteFunction;
import org.lambda4j.function.conversion.CharToDoubleFunction;
import org.lambda4j.function.conversion.CharToFloatFunction;
import org.lambda4j.function.conversion.CharToIntFunction;
import org.lambda4j.function.conversion.CharToLongFunction;
import org.lambda4j.function.conversion.CharToShortFunction;
import org.lambda4j.function.conversion.DoubleToShortFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.conversion.IntToShortFunction;
import org.lambda4j.function.conversion.LongToShortFunction;
import org.lambda4j.function.conversion.ShortToCharFunction;
import org.lambda4j.function.to.ToCharFunction;
import org.lambda4j.function.to.ToShortFunction;
import org.lambda4j.function.tri.TriFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToCharFunction;
import org.lambda4j.function.tri.conversion.TriByteToCharFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToCharFunction;
import org.lambda4j.function.tri.conversion.TriFloatToCharFunction;
import org.lambda4j.function.tri.conversion.TriIntToCharFunction;
import org.lambda4j.function.tri.conversion.TriLongToCharFunction;
import org.lambda4j.function.tri.conversion.TriShortToCharFunction;
import org.lambda4j.function.tri.to.ToCharTriFunction;
import org.lambda4j.operator.ternary.CharTernaryOperator;
import org.lambda4j.operator.unary.CharUnaryOperator;
import org.lambda4j.operator.unary.ShortUnaryOperator;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.tri.obj.ObjBiShortPredicate;

/**
 * Represents an operation that accepts one object-valued and two {@code short}-valued input arguments and produces a
 * {@code char}-valued result. This is a (reference, short, short) specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(Object, short, short)}.
 *
 * @param <T> The type of the first argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjBiShortToCharFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjBiShortToCharFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjBiShortToCharFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T> ObjBiShortToCharFunction<T> of(@Nullable ObjBiShortToCharFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjBiShortToCharFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The result from the given {@code ObjBiShortToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> char call(@Nonnull ObjBiShortToCharFunction<? super T> function, T t, short value1, short value2) {
        Objects.requireNonNull(function);
        return function.applyAsChar(t, value1, value2);
    }

    /**
     * Creates a {@link ObjBiShortToCharFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjBiShortToCharFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiShortToCharFunction<T> onlyFirst(@Nonnull ToCharFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsChar(t);
    }

    /**
     * Creates a {@link ObjBiShortToCharFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ShortToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjBiShortToCharFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ShortToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiShortToCharFunction<T> onlySecond(@Nonnull ShortToCharFunction function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsChar(value1);
    }

    /**
     * Creates a {@link ObjBiShortToCharFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link ShortToCharFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ObjBiShortToCharFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code ShortToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiShortToCharFunction<T> onlyThird(@Nonnull ShortToCharFunction function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsChar(value2);
    }

    /**
     * Creates a {@link ObjBiShortToCharFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjBiShortToCharFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjBiShortToCharFunction<T> constant(char ret) {
        return (t, value1, value2) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    char applyAsChar(T t, short value1, short value2);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BiShortToCharFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code BiShortToCharFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BiShortToCharFunction applyAsCharPartially(T t) {
        return (value1, value2) -> applyAsChar(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ShortToCharFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value1 The second argument to this function used to partially apply this function
     * @return A {@code ShortToCharFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ShortToCharFunction applyAsCharPartially(T t, short value1) {
        return value2 -> applyAsChar(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ObjShortToCharFunction} as
     * result.
     *
     * @param value1 The second argument to this function used to partially apply this function
     * @return A {@code ObjShortToCharFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ObjShortToCharFunction<T> applyAsCharPartially(short value1) {
        return (t, value2) -> applyAsChar(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToCharFunction} as result.
     *
     * @param value1 The second argument to this function used to partially apply this function
     * @param value2 The third argument to this function used to partially apply this function
     * @return A {@code ToCharFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToCharFunction<T> applyAsCharPartially(short value1, short value2) {
        return t -> applyAsChar(t, value1, value2);
    }

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToCharTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToCharTriFunction<A, B, C> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull ToShortFunction<? super B> before2, @Nonnull ToShortFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsChar(before1.apply(a), before2.applyAsShort(b), before3.applyAsShort(c));
    }

    /**
     * Returns a composed {@link TriBooleanToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToCharFunction composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanToShortFunction before2, @Nonnull BooleanToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.apply(value1), before2.applyAsShort(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriByteToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToCharFunction composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteToShortFunction before2, @Nonnull ByteToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.apply(value1), before2.applyAsShort(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link CharTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code CharTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharTernaryOperator composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharToShortFunction before2, @Nonnull CharToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.apply(value1), before2.applyAsShort(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriDoubleToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToCharFunction composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleToShortFunction before2, @Nonnull DoubleToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.apply(value1), before2.applyAsShort(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriFloatToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFloatToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToCharFunction composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatToShortFunction before2, @Nonnull FloatToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.apply(value1), before2.applyAsShort(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriIntToCharFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntToCharFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToCharFunction composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntToShortFunction before2, @Nonnull IntToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.apply(value1), before2.applyAsShort(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriLongToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToCharFunction composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongToShortFunction before2, @Nonnull LongToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.apply(value1), before2.applyAsShort(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriShortToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriShortToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToCharFunction composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortUnaryOperator before2, @Nonnull ShortUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.apply(value1), before2.applyAsShort(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link ObjBiShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjBiShortFunction<T, S> andThen(@Nonnull CharFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.apply(applyAsChar(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiShortPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjBiShortPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjBiShortPredicate<T> andThenToBoolean(@Nonnull CharPredicate after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.test(applyAsChar(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiShortToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiShortToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjBiShortToByteFunction<T> andThenToByte(@Nonnull CharToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsByte(applyAsChar(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiShortToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjBiShortToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjBiShortToCharFunction<T> andThenToChar(@Nonnull CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsChar(applyAsChar(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiShortToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiShortToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjBiShortToDoubleFunction<T> andThenToDouble(@Nonnull CharToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsDouble(applyAsChar(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiShortToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiShortToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjBiShortToFloatFunction<T> andThenToFloat(@Nonnull CharToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsFloat(applyAsChar(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiShortToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiShortToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjBiShortToIntFunction<T> andThenToInt(@Nonnull CharToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsInt(applyAsChar(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiShortToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiShortToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjBiShortToLongFunction<T> andThenToLong(@Nonnull CharToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsLong(applyAsChar(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiShortToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiShortToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjBiShortToShortFunction<T> andThenToShort(@Nonnull CharToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsShort(applyAsChar(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link CharConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjBiShortConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjBiShortConsumer<T> consume(@Nonnull CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.accept(applyAsChar(t, value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjBiShortToCharFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjBiShortToCharFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjBiShortToCharFunction<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, Short, Short>, Character> cache = new ConcurrentHashMap<>();
            return (ObjBiShortToCharFunction<T> & Memoized) (t, value1, value2) -> {
                return cache.computeIfAbsent(Triple.of(t, value1, value2),
                        key -> applyAsChar(key.getLeft(), key.getMiddle(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjBiShortToCharFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * ObjBiShortToCharFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjBiShortToCharFunction}.
     */
    @Nonnull
    default TriFunction<T, Short, Short, Character> boxed() {
        return this::applyAsChar;
    }
}
