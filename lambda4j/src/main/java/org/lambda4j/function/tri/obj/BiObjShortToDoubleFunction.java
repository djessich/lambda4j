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
import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.tri.obj.BiObjShortConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.obj.ObjShortToDoubleFunction;
import org.lambda4j.function.bi.to.ToDoubleBiFunction2;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.conversion.ByteToShortFunction;
import org.lambda4j.function.conversion.CharToShortFunction;
import org.lambda4j.function.conversion.DoubleToByteFunction;
import org.lambda4j.function.conversion.DoubleToCharFunction;
import org.lambda4j.function.conversion.DoubleToFloatFunction;
import org.lambda4j.function.conversion.DoubleToShortFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.conversion.IntToShortFunction;
import org.lambda4j.function.conversion.LongToShortFunction;
import org.lambda4j.function.conversion.ShortToDoubleFunction;
import org.lambda4j.function.to.ToDoubleFunction2;
import org.lambda4j.function.to.ToShortFunction;
import org.lambda4j.function.tri.TriFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriByteToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriCharToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriFloatToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriIntToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriLongToDoubleFunction;
import org.lambda4j.function.tri.conversion.TriShortToDoubleFunction;
import org.lambda4j.function.tri.to.ToDoubleTriFunction;
import org.lambda4j.operator.ternary.DoubleTernaryOperator;
import org.lambda4j.operator.unary.ShortUnaryOperator;
import org.lambda4j.predicate.tri.obj.BiObjShortPredicate;

/**
 * Represents an operation that accepts two object-valued and one {@code short}-valued input argument and produces a
 * {@code double}-valued result. This is a (reference, reference, short) specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(Object, Object, short)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjShortToDoubleFunction<T, U> extends Lambda {

    /**
     * Constructs a {@link BiObjShortToDoubleFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiObjShortToDoubleFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U> BiObjShortToDoubleFunction<T, U> of(@Nullable BiObjShortToDoubleFunction<T, U> expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiObjShortToDoubleFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjShortToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U> double call(@Nonnull BiObjShortToDoubleFunction<? super T, ? super U> function, T t, U u,
            short value) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(t, u, value);
    }

    /**
     * Creates a {@link BiObjShortToDoubleFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@link ToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjShortToDoubleFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjShortToDoubleFunction<T, U> onlyFirst(@Nonnull ToDoubleFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsDouble(t);
    }

    /**
     * Creates a {@link BiObjShortToDoubleFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjShortToDoubleFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjShortToDoubleFunction<T, U> onlySecond(@Nonnull ToDoubleFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsDouble(u);
    }

    /**
     * Creates a {@link BiObjShortToDoubleFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@link ShortToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjShortToDoubleFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code ShortToDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjShortToDoubleFunction<T, U> onlyThird(@Nonnull ShortToDoubleFunction function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsDouble(value);
    }

    /**
     * Creates a {@link BiObjShortToDoubleFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code BiObjShortToDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U> BiObjShortToDoubleFunction<T, U> constant(double ret) {
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
    double applyAsDouble(T t, U u, short value);

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @param value The primitive value to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default double applyAsDouble(@Nonnull Pair<T, U> tuple, short value) {
        Objects.requireNonNull(tuple);
        return applyAsDouble(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ObjShortToDoubleFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ObjShortToDoubleFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ObjShortToDoubleFunction<U> applyAsDoublePartially(T t) {
        return (u, value) -> applyAsDouble(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ShortToDoubleFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param u The second argument to this function used to partially apply this function
     * @return A {@code ShortToDoubleFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ShortToDoubleFunction applyAsDoublePartially(T t, U u) {
        return value -> applyAsDouble(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToDoubleBiFunction2} as
     * result.
     *
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code ToDoubleBiFunction2} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToDoubleBiFunction2<T, U> applyAsDoublePartially(short value) {
        return (t, u) -> applyAsDouble(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToDoubleFunction2} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code ToDoubleFunction2} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToDoubleFunction2<U> applyAsDoublePartially(T t, short value) {
        return u -> applyAsDouble(t, u, value);
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
     * Returns a composed {@link ToDoubleTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToDoubleTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToDoubleTriFunction<A, B, C> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull Function<? super B, ? extends U> before2,
            @Nonnull ToShortFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsDouble(before1.apply(a), before2.apply(b), before3.applyAsShort(c));
    }

    /**
     * Returns a composed {@link TriBooleanToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToDoubleFunction composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanFunction<? extends U> before2, @Nonnull BooleanToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.apply(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriByteToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToDoubleFunction composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteFunction<? extends U> before2, @Nonnull ByteToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.apply(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriCharToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriCharToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToDoubleFunction composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharFunction<? extends U> before2, @Nonnull CharToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.apply(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link DoubleTernaryOperator} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code DoubleTernaryOperator} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleTernaryOperator composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleFunction<? extends U> before2, @Nonnull DoubleToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.apply(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriFloatToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFloatToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToDoubleFunction composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatFunction<? extends U> before2, @Nonnull FloatToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.apply(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriIntToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToDoubleFunction composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntFunction<? extends U> before2, @Nonnull IntToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.apply(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriLongToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToDoubleFunction composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongFunction<? extends U> before2, @Nonnull LongToShortFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.apply(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link TriShortToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriShortToDoubleFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToDoubleFunction composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortFunction<? extends U> before2, @Nonnull ShortUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsDouble(before1.apply(value1), before2.apply(value2),
                before3.applyAsShort(value3));
    }

    /**
     * Returns a composed {@link BiObjShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiObjShortFunction<T, U, S> andThen(@Nonnull DoubleFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code BiObjShortPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiObjShortPredicate<T, U> andThenToBoolean(@Nonnull DoublePredicate after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.test(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjShortToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiObjShortToByteFunction<T, U> andThenToByte(@Nonnull DoubleToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsByte(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjShortToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiObjShortToCharFunction<T, U> andThenToChar(@Nonnull DoubleToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsChar(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code BiObjShortToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiObjShortToDoubleFunction<T, U> andThenToDouble(@Nonnull DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsDouble(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjShortToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiObjShortToFloatFunction<T, U> andThenToFloat(@Nonnull DoubleToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsFloat(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjShortToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiObjShortToIntFunction<T, U> andThenToInt(@Nonnull DoubleToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsInt(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjShortToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiObjShortToLongFunction<T, U> andThenToLong(@Nonnull DoubleToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsLong(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjShortToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiObjShortToShortFunction<T, U> andThenToShort(@Nonnull DoubleToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsShort(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link DoubleConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjShortConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjShortConsumer<T, U> consume(@Nonnull DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsDouble(t, u, value));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ObjShortToDoubleFunction<Pair<T, U>> tupled() {
        return this::applyAsDouble;
    }

    /**
     * Returns a memoized (caching) version of this {@link BiObjShortToDoubleFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiObjShortToDoubleFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiObjShortToDoubleFunction<T, U> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, U, Short>, Double> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (BiObjShortToDoubleFunction<T, U> & Memoized) (t, u, value) -> {
                double returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, u, value),
                            key -> applyAsDouble(key.getLeft(), key.getMiddle(),
                                    key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link BiObjShortToDoubleFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * BiObjShortToDoubleFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code BiObjShortToDoubleFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Short, Double> boxed() {
        return this::applyAsDouble;
    }

}
