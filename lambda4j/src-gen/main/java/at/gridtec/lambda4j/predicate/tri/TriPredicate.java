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
package at.gridtec.lambda4j.predicate.tri;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.BooleanConsumer;
import at.gridtec.lambda4j.consumer.tri.TriConsumer;
import at.gridtec.lambda4j.function.BooleanFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.tri.TriFunction;
import at.gridtec.lambda4j.function.tri.to.ToByteTriFunction;
import at.gridtec.lambda4j.function.tri.to.ToCharTriFunction;
import at.gridtec.lambda4j.function.tri.to.ToDoubleTriFunction;
import at.gridtec.lambda4j.function.tri.to.ToFloatTriFunction;
import at.gridtec.lambda4j.function.tri.to.ToIntTriFunction;
import at.gridtec.lambda4j.function.tri.to.ToLongTriFunction;
import at.gridtec.lambda4j.function.tri.to.ToShortTriFunction;
import at.gridtec.lambda4j.operator.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.predicate.Predicate2;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents an predicate (boolean-valued function) of three input arguments.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @param <V> The type of the third argument to the predicate
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriPredicate<T, U, V> extends Lambda {

    /**
     * Constructs a {@link TriPredicate} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriPredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, U, V> TriPredicate<T, U, V> of(@Nullable final TriPredicate<T, U, V> expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param v The third argument to the predicate
     * @return The result from the given {@code TriPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U, V> boolean call(@Nonnull final TriPredicate<? super T, ? super U, ? super V> predicate, T t, U u,
            V v) {
        Objects.requireNonNull(predicate);
        return predicate.test(t, u, v);
    }

    /**
     * Creates a {@link TriPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriPredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@code Predicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> TriPredicate<T, U, V> onlyFirst(@Nonnull final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, v) -> predicate.test(t);
    }

    /**
     * Creates a {@link TriPredicate} which uses the {@code second} parameter of this one as argument for the given
     * {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriPredicate} which uses the {@code second} parameter of this one as argument for the
     * given {@code Predicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> TriPredicate<T, U, V> onlySecond(@Nonnull final Predicate<? super U> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, v) -> predicate.test(u);
    }

    /**
     * Creates a {@link TriPredicate} which uses the {@code third} parameter of this one as argument for the given
     * {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriPredicate} which uses the {@code third} parameter of this one as argument for the
     * given {@code Predicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> TriPredicate<T, U, V> onlyThird(@Nonnull final Predicate<? super V> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, v) -> predicate.test(v);
    }

    /**
     * Creates a {@link TriPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code TriPredicate} which always returns a given value.
     */
    @Nonnull
    static <T, U, V> TriPredicate<T, U, V> constant(boolean ret) {
        return (t, u, v) -> ret;
    }

    /**
     * Returns a {@link TriPredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @return A {@link TriPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T, U, V> TriPredicate<T, U, V> alwaysTrue() {
        return (t, u, v) -> true;
    }

    /**
     * Returns a {@link TriPredicate} that always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @return A {@link TriPredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T, U, V> TriPredicate<T, U, V> alwaysFalse() {
        return (t, u, v) -> false;
    }

    /**
     * Returns a {@link TriPredicate} that tests if the given arguments are <b>equal</b> to the ones of this predicate.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @param target3 The third reference with which to compare for equality, which may be {@code null}
     * @return A {@code TriPredicate} that tests if the given arguments are <b>equal</b> to the ones of this predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <T, U, V> TriPredicate<T, U, V> isEqual(@Nullable Object target1, @Nullable Object target2,
            @Nullable Object target3) {
        return (t, u, v) -> (t == null ? target1 == null : t.equals(target1)) && (u == null
                ? target2 == null
                : u.equals(target2)) && (v == null ? target3 == null : v.equals(target3));
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param v The third argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(T t, U u, V v);

    /**
     * Applies this predicate to the given tuple.
     *
     * @param tuple The tuple to be applied to the predicate
     * @return The return value from the predicate, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default boolean test(@Nonnull Triple<T, U, V> tuple) {
        Objects.requireNonNull(tuple);
        return test(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed predicate
     * @param <B> The type of the argument to the second given function, and of composed predicate
     * @param <C> The type of the argument to the third given function, and of composed predicate
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code TriPredicate} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> TriPredicate<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2,
            @Nonnull final Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> test(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code TriFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriFunction<T, U, V, S> andThen(@Nonnull final BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(test(t, u, v));
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code TriPredicate} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriPredicate<T, U, V> andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsBoolean(test(t, u, v));
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToByteTriFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ToByteTriFunction<T, U, V> andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsByte(test(t, u, v));
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToCharTriFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ToCharTriFunction<T, U, V> andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsChar(test(t, u, v));
    }

    /**
     * Returns a composed {@link ToDoubleTriFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToDoubleTriFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ToDoubleTriFunction<T, U, V> andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsDouble(test(t, u, v));
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToFloatTriFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ToFloatTriFunction<T, U, V> andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsFloat(test(t, u, v));
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToIntTriFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ToIntTriFunction<T, U, V> andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsInt(test(t, u, v));
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToLongTriFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ToLongTriFunction<T, U, V> andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsLong(test(t, u, v));
    }

    /**
     * Returns a composed {@link ToShortTriFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToShortTriFunction} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ToShortTriFunction<T, U, V> andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsShort(test(t, u, v));
    }

    /**
     * Returns a composed {@link TriConsumer} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriConsumer} that first applies this predicate to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriConsumer<T, U, V> consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(test(t, u, v));
    }

    /**
     * Returns a {@link TriPredicate} that represents the logical negation of this one.
     *
     * @return A {@code TriPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default TriPredicate<T, U, V> negate() {
        return (t, u, v) -> !test(t, u, v);
    }

    /**
     * Returns a composed {@link TriPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code TriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code TriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code TriPredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(TriPredicate)
     * @see #xor(TriPredicate)
     */
    @Nonnull
    default TriPredicate<T, U, V> and(@Nonnull final TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) && other.test(t, u, v);
    }

    /**
     * Returns a composed {@link TriPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code TriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code TriPredicate} that will be logically-ORed with this one
     * @return A composed {@code TriPredicate} that represents the short-circuiting logical OR of this predicate and the
     * {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(TriPredicate)
     * @see #xor(TriPredicate)
     */
    @Nonnull
    default TriPredicate<T, U, V> or(@Nonnull final TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) || other.test(t, u, v);
    }

    /**
     * Returns a composed {@link TriPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of
     * this {@code TriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code TriPredicate} that will be logically-XORed with this one
     * @return A composed {@code TriPredicate} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(TriPredicate)
     * @see #or(TriPredicate)
     */
    @Nonnull
    default TriPredicate<T, U, V> xor(@Nonnull final TriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> test(t, u, v) ^ other.test(t, u, v);
    }

    /**
     * Returns a tupled version of this predicate.
     *
     * @return A tupled version of this predicate.
     */
    @Nonnull
    default Predicate2<Triple<T, U, V>> tupled() {
        return this::test;
    }

    /**
     * Returns a reversed version of this predicate. This may be useful in recursive context.
     *
     * @return A reversed version of this predicate.
     */
    @Nonnull
    default TriPredicate<V, U, T> reversed() {
        return (v, u, t) -> test(t, u, v);
    }

    /**
     * Returns a memoized (caching) version of this {@link TriPredicate}. Whenever it is called, the mapping between the
     * input parameters and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriPredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriPredicate<T, U, V> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Triple<T, U, V>, Boolean> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (TriPredicate<T, U, V> & Memoized) (t, u, v) -> {
                final boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, u, v),
                                                        key -> test(key.getLeft(), key.getMiddle(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

}