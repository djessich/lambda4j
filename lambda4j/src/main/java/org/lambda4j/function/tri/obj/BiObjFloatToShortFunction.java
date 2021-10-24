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

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ShortConsumer;
import org.lambda4j.consumer.tri.obj.BiObjFloatConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.obj.ObjFloatToShortFunction;
import org.lambda4j.function.bi.to.ToShortBiFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.ByteToFloatFunction;
import org.lambda4j.function.conversion.CharToFloatFunction;
import org.lambda4j.function.conversion.DoubleToFloatFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.conversion.IntToFloatFunction;
import org.lambda4j.function.conversion.LongToFloatFunction;
import org.lambda4j.function.conversion.ShortToByteFunction;
import org.lambda4j.function.conversion.ShortToCharFunction;
import org.lambda4j.function.conversion.ShortToDoubleFunction;
import org.lambda4j.function.conversion.ShortToFloatFunction;
import org.lambda4j.function.conversion.ShortToIntFunction;
import org.lambda4j.function.conversion.ShortToLongFunction;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.function.to.ToShortFunction;
import org.lambda4j.function.tri.TriFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToShortFunction;
import org.lambda4j.function.tri.conversion.TriByteToShortFunction;
import org.lambda4j.function.tri.conversion.TriCharToShortFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToShortFunction;
import org.lambda4j.function.tri.conversion.TriFloatToShortFunction;
import org.lambda4j.function.tri.conversion.TriIntToShortFunction;
import org.lambda4j.function.tri.conversion.TriLongToShortFunction;
import org.lambda4j.function.tri.to.ToShortTriFunction;
import org.lambda4j.operator.ternary.ShortTernaryOperator;
import org.lambda4j.operator.unary.FloatUnaryOperator;
import org.lambda4j.operator.unary.ShortUnaryOperator;
import org.lambda4j.predicate.ShortPredicate;
import org.lambda4j.predicate.tri.obj.BiObjFloatPredicate;

/**
 * Represents an operation that accepts two object-valued and one {@code float}-valued input argument and produces a
 * {@code short}-valued result. This is a (reference, reference, float) specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(Object, Object, float)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjFloatToShortFunction<T, U> extends Lambda {

    /**
     * Constructs a {@link BiObjFloatToShortFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiObjFloatToShortFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U> BiObjFloatToShortFunction<T, U> of(@Nullable BiObjFloatToShortFunction<T, U> expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiObjFloatToShortFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjFloatToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U> short call(@Nonnull BiObjFloatToShortFunction<? super T, ? super U> function, T t, U u,
            float value) {
        Objects.requireNonNull(function);
        return function.applyAsShort(t, u, value);
    }

    /**
     * Creates a {@link BiObjFloatToShortFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@link ToShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjFloatToShortFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjFloatToShortFunction<T, U> onlyFirst(@Nonnull ToShortFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsShort(t);
    }

    /**
     * Creates a {@link BiObjFloatToShortFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ToShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjFloatToShortFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjFloatToShortFunction<T, U> onlySecond(@Nonnull ToShortFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsShort(u);
    }

    /**
     * Creates a {@link BiObjFloatToShortFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@link FloatToShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjFloatToShortFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code FloatToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjFloatToShortFunction<T, U> onlyThird(@Nonnull FloatToShortFunction function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsShort(value);
    }

    /**
     * Creates a {@link BiObjFloatToShortFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code BiObjFloatToShortFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U> BiObjFloatToShortFunction<T, U> constant(short ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    short applyAsShort(T t, U u, float value);

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @param value The primitive value to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default short applyAsShort(@Nonnull Pair<T, U> tuple, float value) {
        Objects.requireNonNull(tuple);
        return applyAsShort(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ObjFloatToShortFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ObjFloatToShortFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ObjFloatToShortFunction<U> applyAsShortPartially(T t) {
        return (u, value) -> applyAsShort(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link FloatToShortFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param u The second argument to this function used to partially apply this function
     * @return A {@code FloatToShortFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default FloatToShortFunction applyAsShortPartially(T t, U u) {
        return value -> applyAsShort(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToShortBiFunction} as result.
     *
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code ToShortBiFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToShortBiFunction<T, U> applyAsShortPartially(float value) {
        return (t, u) -> applyAsShort(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToShortFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code ToShortFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToShortFunction<U> applyAsShortPartially(T t, float value) {
        return u -> applyAsShort(t, u, value);
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
     * Returns a composed {@link ToShortTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToShortTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToShortTriFunction<A, B, C> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull Function<? super B, ? extends U> before2,
            @Nonnull ToFloatFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsShort(before1.apply(a), before2.apply(b), before3.applyAsFloat(c));
    }

    /**
     * Returns a composed {@link TriBooleanToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToShortFunction composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanFunction<? extends U> before2, @Nonnull BooleanToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.apply(value1), before2.apply(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriByteToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToShortFunction composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteFunction<? extends U> before2, @Nonnull ByteToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.apply(value1), before2.apply(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriCharToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriCharToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToShortFunction composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharFunction<? extends U> before2, @Nonnull CharToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.apply(value1), before2.apply(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriDoubleToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToShortFunction composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleFunction<? extends U> before2, @Nonnull DoubleToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.apply(value1), before2.apply(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriFloatToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriFloatToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToShortFunction composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatFunction<? extends U> before2, @Nonnull FloatUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.apply(value1), before2.apply(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriIntToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToShortFunction composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntFunction<? extends U> before2, @Nonnull IntToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.apply(value1), before2.apply(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriLongToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToShortFunction composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongFunction<? extends U> before2, @Nonnull LongToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.apply(value1), before2.apply(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link ShortTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ShortTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortTernaryOperator composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortFunction<? extends U> before2, @Nonnull ShortToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.apply(value1), before2.apply(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link BiObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiObjFloatFunction<T, U, S> andThen(@Nonnull ShortFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsShort(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code BiObjFloatPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiObjFloatPredicate<T, U> andThenToBoolean(@Nonnull ShortPredicate after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.test(applyAsShort(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjFloatToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiObjFloatToByteFunction<T, U> andThenToByte(@Nonnull ShortToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsByte(applyAsShort(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjFloatToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiObjFloatToCharFunction<T, U> andThenToChar(@Nonnull ShortToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsChar(applyAsShort(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiObjFloatToDoubleFunction<T, U> andThenToDouble(@Nonnull ShortToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsDouble(applyAsShort(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjFloatToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiObjFloatToFloatFunction<T, U> andThenToFloat(@Nonnull ShortToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsFloat(applyAsShort(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjFloatToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiObjFloatToIntFunction<T, U> andThenToInt(@Nonnull ShortToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsInt(applyAsShort(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjFloatToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiObjFloatToLongFunction<T, U> andThenToLong(@Nonnull ShortToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsLong(applyAsShort(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code BiObjFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiObjFloatToShortFunction<T, U> andThenToShort(@Nonnull ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsShort(applyAsShort(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjFloatConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ShortConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjFloatConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjFloatConsumer<T, U> consume(@Nonnull ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsShort(t, u, value));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ObjFloatToShortFunction<Pair<T, U>> tupled() {
        return this::applyAsShort;
    }

    /**
     * Returns a memoized (caching) version of this {@link BiObjFloatToShortFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiObjFloatToShortFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiObjFloatToShortFunction<T, U> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, U, Float>, Short> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (BiObjFloatToShortFunction<T, U> & Memoized) (t, u, value) -> {
                short returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, u, value),
                            key -> applyAsShort(key.getLeft(), key.getMiddle(),
                                    key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link BiObjFloatToShortFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * BiObjFloatToShortFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code BiObjFloatToShortFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Float, Short> boxed() {
        return this::applyAsShort;
    }

}
