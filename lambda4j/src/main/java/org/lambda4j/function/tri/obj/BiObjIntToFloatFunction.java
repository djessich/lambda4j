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
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.FloatConsumer;
import org.lambda4j.consumer.tri.obj.BiObjIntConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.obj.ObjIntToFloatFunction;
import org.lambda4j.function.bi.to.ToFloatBiFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.ByteToIntFunction;
import org.lambda4j.function.conversion.CharToIntFunction;
import org.lambda4j.function.conversion.FloatToByteFunction;
import org.lambda4j.function.conversion.FloatToCharFunction;
import org.lambda4j.function.conversion.FloatToDoubleFunction;
import org.lambda4j.function.conversion.FloatToIntFunction;
import org.lambda4j.function.conversion.FloatToLongFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.conversion.IntToFloatFunction;
import org.lambda4j.function.conversion.ShortToIntFunction;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.function.tri.TriFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToFloatFunction;
import org.lambda4j.function.tri.conversion.TriByteToFloatFunction;
import org.lambda4j.function.tri.conversion.TriCharToFloatFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToFloatFunction;
import org.lambda4j.function.tri.conversion.TriIntToFloatFunction;
import org.lambda4j.function.tri.conversion.TriLongToFloatFunction;
import org.lambda4j.function.tri.conversion.TriShortToFloatFunction;
import org.lambda4j.function.tri.to.ToFloatTriFunction;
import org.lambda4j.operator.ternary.FloatTernaryOperator;
import org.lambda4j.operator.unary.FloatUnaryOperator;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.tri.obj.BiObjIntPredicate;

/**
 * Represents an operation that accepts two object-valued and one {@code int}-valued input argument and produces a
 * {@code float}-valued result. This is a (reference, reference, int) specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(Object, Object, int)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjIntToFloatFunction<T, U> extends Lambda {

    /**
     * Constructs a {@link BiObjIntToFloatFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiObjIntToFloatFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U> BiObjIntToFloatFunction<T, U> of(@Nullable BiObjIntToFloatFunction<T, U> expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiObjIntToFloatFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjIntToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U> float call(@Nonnull BiObjIntToFloatFunction<? super T, ? super U> function, T t, U u,
            int value) {
        Objects.requireNonNull(function);
        return function.applyAsFloat(t, u, value);
    }

    /**
     * Creates a {@link BiObjIntToFloatFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjIntToFloatFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjIntToFloatFunction<T, U> onlyFirst(@Nonnull ToFloatFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsFloat(t);
    }

    /**
     * Creates a {@link BiObjIntToFloatFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjIntToFloatFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjIntToFloatFunction<T, U> onlySecond(@Nonnull ToFloatFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsFloat(u);
    }

    /**
     * Creates a {@link BiObjIntToFloatFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link IntToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjIntToFloatFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code IntToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjIntToFloatFunction<T, U> onlyThird(@Nonnull IntToFloatFunction function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsFloat(value);
    }

    /**
     * Creates a {@link BiObjIntToFloatFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code BiObjIntToFloatFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U> BiObjIntToFloatFunction<T, U> constant(float ret) {
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
    float applyAsFloat(T t, U u, int value);

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @param value The primitive value to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default float applyAsFloat(@Nonnull Pair<T, U> tuple, int value) {
        Objects.requireNonNull(tuple);
        return applyAsFloat(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ObjIntToFloatFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ObjIntToFloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ObjIntToFloatFunction<U> applyAsFloatPartially(T t) {
        return (u, value) -> applyAsFloat(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link IntToFloatFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param u The second argument to this function used to partially apply this function
     * @return A {@code IntToFloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default IntToFloatFunction applyAsFloatPartially(T t, U u) {
        return value -> applyAsFloat(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToFloatBiFunction} as result.
     *
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code ToFloatBiFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToFloatBiFunction<T, U> applyAsFloatPartially(int value) {
        return (t, u) -> applyAsFloat(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToFloatFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code ToFloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToFloatFunction<U> applyAsFloatPartially(T t, int value) {
        return u -> applyAsFloat(t, u, value);
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
     * Returns a composed {@link ToFloatTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToFloatTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToFloatTriFunction<A, B, C> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull Function<? super B, ? extends U> before2, @Nonnull ToIntFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsFloat(before1.apply(a), before2.apply(b), before3.applyAsInt(c));
    }

    /**
     * Returns a composed {@link TriBooleanToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToFloatFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToFloatFunction composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanFunction<? extends U> before2, @Nonnull BooleanToIntFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.apply(value1), before2.apply(value2),
                before3.applyAsInt(value3));
    }

    /**
     * Returns a composed {@link TriByteToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToFloatFunction composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteFunction<? extends U> before2, @Nonnull ByteToIntFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.apply(value1), before2.apply(value2),
                before3.applyAsInt(value3));
    }

    /**
     * Returns a composed {@link TriCharToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriCharToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToFloatFunction composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharFunction<? extends U> before2, @Nonnull CharToIntFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.apply(value1), before2.apply(value2),
                before3.applyAsInt(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriDoubleToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToFloatFunction composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleFunction<? extends U> before2, @Nonnull DoubleToIntFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.apply(value1), before2.apply(value2),
                before3.applyAsInt(value3));
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code FloatTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatTernaryOperator composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatFunction<? extends U> before2, @Nonnull FloatToIntFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.apply(value1), before2.apply(value2),
                before3.applyAsInt(value3));
    }

    /**
     * Returns a composed {@link TriIntToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriIntToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToFloatFunction composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntFunction<? extends U> before2, @Nonnull IntUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.apply(value1), before2.apply(value2),
                before3.applyAsInt(value3));
    }

    /**
     * Returns a composed {@link TriLongToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToFloatFunction composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongFunction<? extends U> before2, @Nonnull LongToIntFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.apply(value1), before2.apply(value2),
                before3.applyAsInt(value3));
    }

    /**
     * Returns a composed {@link TriShortToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToFloatFunction composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortFunction<? extends U> before2, @Nonnull ShortToIntFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsFloat(before1.apply(value1), before2.apply(value2),
                before3.applyAsInt(value3));
    }

    /**
     * Returns a composed {@link BiObjIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiObjIntFunction<T, U, S> andThen(@Nonnull FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code BiObjIntPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiObjIntPredicate<T, U> andThenToBoolean(@Nonnull FloatPredicate after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.test(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjIntToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiObjIntToByteFunction<T, U> andThenToByte(@Nonnull FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsByte(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjIntToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiObjIntToCharFunction<T, U> andThenToChar(@Nonnull FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsChar(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjIntToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiObjIntToDoubleFunction<T, U> andThenToDouble(@Nonnull FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsDouble(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code BiObjIntToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiObjIntToFloatFunction<T, U> andThenToFloat(@Nonnull FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsFloat(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjIntToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiObjIntToIntFunction<T, U> andThenToInt(@Nonnull FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsInt(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjIntToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiObjIntToLongFunction<T, U> andThenToLong(@Nonnull FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsLong(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjIntToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiObjIntToShortFunction<T, U> andThenToShort(@Nonnull FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsShort(applyAsFloat(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjIntConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjIntConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjIntConsumer<T, U> consume(@Nonnull FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsFloat(t, u, value));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ObjIntToFloatFunction<Pair<T, U>> tupled() {
        return this::applyAsFloat;
    }

    /**
     * Returns a memoized (caching) version of this {@link BiObjIntToFloatFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiObjIntToFloatFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiObjIntToFloatFunction<T, U> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, U, Integer>, Float> cache = new ConcurrentHashMap<>();
            return (BiObjIntToFloatFunction<T, U> & Memoized) (t, u, value) -> {
                return cache.computeIfAbsent(Triple.of(t, u, value),
                        key -> applyAsFloat(key.getLeft(), key.getMiddle(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link BiObjIntToFloatFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * BiObjIntToFloatFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code BiObjIntToFloatFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Integer, Float> boxed() {
        return this::applyAsFloat;
    }
}
