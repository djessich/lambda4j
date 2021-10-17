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
package org.lambda4j.predicate.bi;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.consumer.bi.BiConsumer2;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.bi.BiFunction2;
import org.lambda4j.function.bi.to.ToByteBiFunction;
import org.lambda4j.function.bi.to.ToCharBiFunction;
import org.lambda4j.function.bi.to.ToDoubleBiFunction2;
import org.lambda4j.function.bi.to.ToFloatBiFunction;
import org.lambda4j.function.bi.to.ToIntBiFunction2;
import org.lambda4j.function.bi.to.ToLongBiFunction2;
import org.lambda4j.function.bi.to.ToShortBiFunction;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.Predicate2;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents an predicate (boolean-valued function) of two input arguments.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, Object)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @apiNote This is a JDK lambda.
 * @see BiPredicate2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiPredicate2<T, U> extends Lambda, BiPredicate<T, U> {

    /**
     * Constructs a {@link BiPredicate2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiPredicate2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, U> BiPredicate2<T, U> of(@Nullable final BiPredicate2<T, U> expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @return The result from the given {@code BiPredicate2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U> boolean call(@Nonnull final BiPredicate<? super T, ? super U> predicate, T t, U u) {
        Objects.requireNonNull(predicate);
        return predicate.test(t, u);
    }

    /**
     * Creates a {@link BiPredicate2} which uses the {@code first} parameter of this one as argument for the given
     * {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiPredicate2} which uses the {@code first} parameter of this one as argument for the
     * given {@code Predicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiPredicate2<T, U> onlyFirst(@Nonnull final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u) -> predicate.test(t);
    }

    /**
     * Creates a {@link BiPredicate2} which uses the {@code second} parameter of this one as argument for the given
     * {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiPredicate2} which uses the {@code second} parameter of this one as argument for the
     * given {@code Predicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiPredicate2<T, U> onlySecond(@Nonnull final Predicate<? super U> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u) -> predicate.test(u);
    }

    /**
     * Creates a {@link BiPredicate2} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code BiPredicate2} which always returns a given value.
     */
    @Nonnull
    static <T, U> BiPredicate2<T, U> constant(boolean ret) {
        return (t, u) -> ret;
    }

    /**
     * Returns a {@link BiPredicate2} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiPredicate2} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T, U> BiPredicate2<T, U> alwaysTrue() {
        return (t, u) -> true;
    }

    /**
     * Returns a {@link BiPredicate2} that always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiPredicate2} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T, U> BiPredicate2<T, U> alwaysFalse() {
        return (t, u) -> false;
    }

    /**
     * Returns a {@link BiPredicate2} that tests if the given arguments are <b>equal</b> to the ones of this predicate.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @return A {@code BiPredicate2} that tests if the given arguments are <b>equal</b> to the ones of this predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <T, U> BiPredicate2<T, U> isEqual(@Nullable Object target1, @Nullable Object target2) {
        return (t, u) -> (t == null ? target1 == null : t.equals(target1)) && (u == null
                ? target2 == null
                : u.equals(target2));
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(T t, U u);

    /**
     * Applies this predicate to the given tuple.
     *
     * @param tuple The tuple to be applied to the predicate
     * @return The return value from the predicate, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default boolean test(@Nonnull Pair<T, U> tuple) {
        Objects.requireNonNull(tuple);
        return test(tuple.getLeft(), tuple.getRight());
    }

    /**
     * Applies this predicate partially to some arguments of this one, producing a {@link Predicate2} as result.
     *
     * @param t The first argument to this predicate used to partially apply this function
     * @return A {@code Predicate2} that represents this predicate partially applied the some arguments.
     */
    @Nonnull
    default Predicate2<U> ptest(T t) {
        return (u) -> this.test(t, u);
    }

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiPredicate2} that first applies the {@code before} functions to its input, and
     * then applies this predicate to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed predicate
     * @param <B> The type of the argument to the second given function, and of composed predicate
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @return A composed {@code BiPredicate2} that first applies the {@code before} functions to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiPredicate2<A, B> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> test(before1.apply(a), before2.apply(b));
    }

    /**
     * Returns a composed {@link BiFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code BiFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiFunction2<T, U, S> andThen(@Nonnull final BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.apply(test(t, u));
    }

    /**
     * Returns a composed {@link BiPredicate2} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code BiPredicate2} that first applies this predicate to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiPredicate2<T, U> andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsBoolean(test(t, u));
    }

    /**
     * Returns a composed {@link ToByteBiFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToByteBiFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ToByteBiFunction<T, U> andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsByte(test(t, u));
    }

    /**
     * Returns a composed {@link ToCharBiFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToCharBiFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ToCharBiFunction<T, U> andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsChar(test(t, u));
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction2} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToDoubleBiFunction2} that first applies this predicate to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ToDoubleBiFunction2<T, U> andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsDouble(test(t, u));
    }

    /**
     * Returns a composed {@link ToFloatBiFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToFloatBiFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ToFloatBiFunction<T, U> andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsFloat(test(t, u));
    }

    /**
     * Returns a composed {@link ToIntBiFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToIntBiFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ToIntBiFunction2<T, U> andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsInt(test(t, u));
    }

    /**
     * Returns a composed {@link ToLongBiFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToLongBiFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ToLongBiFunction2<T, U> andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsLong(test(t, u));
    }

    /**
     * Returns a composed {@link ToShortBiFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToShortBiFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ToShortBiFunction<T, U> andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsShort(test(t, u));
    }

    /**
     * Returns a composed {@link BiConsumer2} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiConsumer2} that first applies this predicate to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiConsumer2<T, U> consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.accept(test(t, u));
    }

    /**
     * Returns a {@link BiPredicate2} that represents the logical negation of this one.
     *
     * @return A {@code BiPredicate2} that represents the logical negation of this one.
     */
    @Nonnull
    default BiPredicate2<T, U> negate() {
        return (t, u) -> !test(t, u);
    }

    /**
     * Returns a composed {@link BiPredicate2} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code BiPredicate2} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiPredicate2} that will be logically-ANDed with this one
     * @return A composed {@code BiPredicate2} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(BiPredicate)
     * @see #xor(BiPredicate)
     */
    @Nonnull
    default BiPredicate2<T, U> and(@Nonnull final BiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u) -> test(t, u) && other.test(t, u);
    }

    /**
     * Returns a composed {@link BiPredicate2} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code BiPredicate2} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiPredicate2} that will be logically-ORed with this one
     * @return A composed {@code BiPredicate2} that represents the short-circuiting logical OR of this predicate and the
     * {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(BiPredicate)
     * @see #xor(BiPredicate)
     */
    @Nonnull
    default BiPredicate2<T, U> or(@Nonnull final BiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u) -> test(t, u) || other.test(t, u);
    }

    /**
     * Returns a composed {@link BiPredicate2} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of
     * this {@code BiPredicate2} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiPredicate2} that will be logically-XORed with this one
     * @return A composed {@code BiPredicate2} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(BiPredicate)
     * @see #or(BiPredicate)
     */
    @Nonnull
    default BiPredicate2<T, U> xor(@Nonnull final BiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u) -> test(t, u) ^ other.test(t, u);
    }

    /**
     * Returns a tupled version of this predicate.
     *
     * @return A tupled version of this predicate.
     */
    @Nonnull
    default Predicate2<Pair<T, U>> tupled() {
        return this::test;
    }

    /**
     * Returns a reversed version of this predicate. This may be useful in recursive context.
     *
     * @return A reversed version of this predicate.
     */
    @Nonnull
    default BiPredicate2<U, T> reversed() {
        return (u, t) -> test(t, u);
    }

    /**
     * Returns a memoized (caching) version of this {@link BiPredicate2}. Whenever it is called, the mapping between the
     * input parameters and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiPredicate2}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiPredicate2<T, U> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<T, U>, Boolean> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (BiPredicate2<T, U> & Memoized) (t, u) -> {
                final boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(t, u), key -> test(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

}