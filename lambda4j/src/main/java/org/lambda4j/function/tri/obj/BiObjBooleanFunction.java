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
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.tri.obj.BiObjBooleanConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.Function2;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.BiFunction2;
import org.lambda4j.function.bi.obj.ObjBooleanFunction;
import org.lambda4j.function.tri.TriBooleanFunction;
import org.lambda4j.function.tri.TriByteFunction;
import org.lambda4j.function.tri.TriCharFunction;
import org.lambda4j.function.tri.TriDoubleFunction;
import org.lambda4j.function.tri.TriFloatFunction;
import org.lambda4j.function.tri.TriFunction;
import org.lambda4j.function.tri.TriIntFunction;
import org.lambda4j.function.tri.TriLongFunction;
import org.lambda4j.function.tri.TriShortFunction;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.ShortPredicate;

/**
 * Represents an operation that accepts two object-valued and one {@code boolean}-valued input argument and produces a
 * result. This is a (reference, reference, boolean) specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, Object, boolean)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjBooleanFunction<T, U, R> extends Lambda {

    /**
     * Constructs a {@link BiObjBooleanFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiObjBooleanFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U, R> BiObjBooleanFunction<T, U, R> of(@Nullable BiObjBooleanFunction<T, U, R> expression) {
        return expression;
    }

    /**
     * Lifts a partial {@link BiObjBooleanFunction} into a total {@link BiObjBooleanFunction} that returns an {@link
     * Optional} result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param partial A function that is only defined for some values in its domain
     * @return A partial {@code BiObjBooleanFunction} lifted into a total {@code BiObjBooleanFunction} that returns an
     * {@code Optional} result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjBooleanFunction<T, U, Optional<R>> lift(
            @Nonnull BiObjBooleanFunction<? super T, ? super U, ? extends R> partial) {
        Objects.requireNonNull(partial);
        return (t, u, value) -> Optional.ofNullable(partial.apply(t, u, value));
    }

    /**
     * Calls the given {@link BiObjBooleanFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjBooleanFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U, R> R call(@Nonnull BiObjBooleanFunction<? super T, ? super U, ? extends R> function, T t, U u,
            boolean value) {
        Objects.requireNonNull(function);
        return function.apply(t, u, value);
    }

    /**
     * Creates a {@link BiObjBooleanFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjBooleanFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code Function}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjBooleanFunction<T, U, R> onlyFirst(@Nonnull Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(t);
    }

    /**
     * Creates a {@link BiObjBooleanFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjBooleanFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code Function}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjBooleanFunction<T, U, R> onlySecond(
            @Nonnull Function<? super U, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(u);
    }

    /**
     * Creates a {@link BiObjBooleanFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link BooleanFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjBooleanFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@code BooleanFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjBooleanFunction<T, U, R> onlyThird(@Nonnull BooleanFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(value);
    }

    /**
     * Creates a {@link BiObjBooleanFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code BiObjBooleanFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, R> BiObjBooleanFunction<T, U, R> constant(R ret) {
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
    R apply(T t, U u, boolean value);

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @param value The primitive value to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default R apply(@Nonnull Pair<T, U> tuple, boolean value) {
        Objects.requireNonNull(tuple);
        return apply(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ObjBooleanFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ObjBooleanFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ObjBooleanFunction<U, R> applyPartially(T t) {
        return (u, value) -> apply(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BooleanFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param u The second argument to this function used to partially apply this function
     * @return A {@code BooleanFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BooleanFunction<R> applyPartially(T t, U u) {
        return value -> apply(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BiFunction2} as result.
     *
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code BiFunction2} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BiFunction2<T, U, R> applyPartially(boolean value) {
        return (t, u) -> apply(t, u, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link Function2} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value The third argument to this function used to partially apply this function
     * @return A {@code Function2} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default Function2<U, R> applyPartially(T t, boolean value) {
        return u -> apply(t, u, value);
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
     * Returns a composed {@link TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given predicate, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> TriFunction<A, B, C, R> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull Function<? super B, ? extends U> before2, @Nonnull Predicate<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> apply(before1.apply(a), before2.apply(b), before3.test(c));
    }

    /**
     * Returns a composed {@link TriBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanFunction<R> composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanFunction<? extends U> before2, @Nonnull BooleanUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2),
                before3.applyAsBoolean(value3));
    }

    /**
     * Returns a composed {@link TriByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteFunction<R> composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteFunction<? extends U> before2, @Nonnull BytePredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharFunction<R> composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharFunction<? extends U> before2, @Nonnull CharPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriDoubleFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriDoubleFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleFunction<R> composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleFunction<? extends U> before2, @Nonnull DoublePredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatFunction<R> composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatFunction<? extends U> before2, @Nonnull FloatPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntFunction<R> composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntFunction<? extends U> before2, @Nonnull IntPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongFunction<R> composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongFunction<? extends U> before2, @Nonnull LongPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link TriShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third predicate to apply before this function is applied
     * @return A composed {@code TriShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortFunction<R> composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortFunction<? extends U> before2, @Nonnull ShortPredicate before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.apply(value1), before2.apply(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link BiObjBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiObjBooleanFunction<T, U, S> andThen(@Nonnull Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(apply(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjBooleanConsumer} that fist applies this function to its input, and then consumes
     * the result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjBooleanConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjBooleanConsumer<T, U> consume(@Nonnull Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(apply(t, u, value));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ObjBooleanFunction<Pair<T, U>, R> tupled() {
        return this::apply;
    }

    /**
     * Returns a memoized (caching) version of this {@link BiObjBooleanFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiObjBooleanFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiObjBooleanFunction<T, U, R> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, U, Boolean>, R> cache = new ConcurrentHashMap<>();
            return (BiObjBooleanFunction<T, U, R> & Memoized) (t, u, value) -> {
                return cache.computeIfAbsent(Triple.of(t, u, value),
                        key -> apply(key.getLeft(), key.getMiddle(), key.getRight()));
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link BiObjBooleanFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * BiObjBooleanFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code BiObjBooleanFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Boolean, R> boxed() {
        return this::apply;
    }
}
