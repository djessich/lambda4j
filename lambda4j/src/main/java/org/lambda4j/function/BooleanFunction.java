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

package org.lambda4j.function;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.to.ToByteFunction;
import org.lambda4j.function.to.ToCharFunction;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.function.to.ToShortFunction;
import org.lambda4j.operator.unary.BooleanUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.ShortPredicate;

/**
 * Represents an operation that accepts one {@code boolean}-valued input argument and produces a result. This is a
 * primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(boolean)}.
 *
 * @param <R> The type of return value from the function
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanFunction<R> extends Lambda {

    /**
     * Constructs a {@link BooleanFunction} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BooleanFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <R> BooleanFunction<R> of(@Nullable BooleanFunction<R> expression) {
        return expression;
    }

    /**
     * Lifts a partial {@link BooleanFunction} into a total {@link BooleanFunction} that returns an {@link Optional}
     * result.
     *
     * @param <R> The type of return value from the function
     * @param partial A function that is only defined for some values in its domain
     * @return A partial {@code BooleanFunction} lifted into a total {@code BooleanFunction} that returns an {@code
     * Optional} result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> BooleanFunction<Optional<R>> lift(@Nonnull BooleanFunction<? extends R> partial) {
        Objects.requireNonNull(partial);
        return value -> Optional.ofNullable(partial.apply(value));
    }

    /**
     * Calls the given {@link BooleanFunction} with the given argument and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <R> R call(@Nonnull BooleanFunction<? extends R> function, boolean value) {
        Objects.requireNonNull(function);
        return function.apply(value);
    }

    /**
     * Creates a {@link BooleanFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code BooleanFunction} which always returns a given value.
     */
    @Nonnull
    static <R> BooleanFunction<R> constant(R ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(boolean value);

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
     * Returns a composed {@link Function2} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given predicate, and of composed function
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code Function2} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> Function2<A, R> compose(@Nonnull Predicate<? super A> before) {
        Objects.requireNonNull(before);
        return a -> apply(before.test(a));
    }

    /**
     * Returns a composed {@link BooleanFunction} that first applies the {@code before} operator to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code BooleanFunction} that first applies the {@code before} operator to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanFunction<R> composeFromBoolean(@Nonnull BooleanUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link ByteFunction} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ByteFunction} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteFunction<R> composeFromByte(@Nonnull BytePredicate before) {
        Objects.requireNonNull(before);
        return value -> apply(before.test(value));
    }

    /**
     * Returns a composed {@link CharFunction} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code CharFunction} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharFunction<R> composeFromChar(@Nonnull CharPredicate before) {
        Objects.requireNonNull(before);
        return value -> apply(before.test(value));
    }

    /**
     * Returns a composed {@link DoubleFunction2} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code DoubleFunction2} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleFunction2<R> composeFromDouble(@Nonnull DoublePredicate before) {
        Objects.requireNonNull(before);
        return value -> apply(before.test(value));
    }

    /**
     * Returns a composed {@link FloatFunction} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code FloatFunction} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatFunction<R> composeFromFloat(@Nonnull FloatPredicate before) {
        Objects.requireNonNull(before);
        return value -> apply(before.test(value));
    }

    /**
     * Returns a composed {@link IntFunction2} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code IntFunction2} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntFunction2<R> composeFromInt(@Nonnull IntPredicate before) {
        Objects.requireNonNull(before);
        return value -> apply(before.test(value));
    }

    /**
     * Returns a composed {@link LongFunction2} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code LongFunction2} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongFunction2<R> composeFromLong(@Nonnull LongPredicate before) {
        Objects.requireNonNull(before);
        return value -> apply(before.test(value));
    }

    /**
     * Returns a composed {@link ShortFunction} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The predicate to apply before this function is applied
     * @return A composed {@code ShortFunction} that first applies the {@code before} predicate to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortFunction<R> composeFromShort(@Nonnull ShortPredicate before) {
        Objects.requireNonNull(before);
        return value -> apply(before.test(value));
    }

    /**
     * Returns a composed {@link BooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BooleanFunction<S> andThen(@Nonnull Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(apply(value));
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanUnaryOperator andThenToBoolean(@Nonnull Predicate<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.test(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BooleanToByteFunction andThenToByte(@Nonnull ToByteFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BooleanToCharFunction andThenToChar(@Nonnull ToCharFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BooleanToDoubleFunction andThenToDouble(@Nonnull ToDoubleFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BooleanToFloatFunction andThenToFloat(@Nonnull ToFloatFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BooleanToIntFunction andThenToInt(@Nonnull ToIntFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BooleanToLongFunction andThenToLong(@Nonnull ToLongFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(apply(value));
    }

    /**
     * Returns a composed {@link BooleanToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BooleanToShortFunction andThenToShort(@Nonnull ToShortFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(apply(value));
    }

    /**
     * Returns a composed {@link BooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BooleanConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanConsumer consume(@Nonnull Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(apply(value));
    }

    /**
     * Returns a curried version of this function.
     *
     * @return A curried version of this function.
     */
    @Nonnull
    default BooleanFunction<R> curried() {
        return this;
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default BooleanFunction<R> reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link BooleanFunction}. Whenever it is called, the mapping between
     * the input parameter and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BooleanFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BooleanFunction<R> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Boolean, R> cache = new ConcurrentHashMap<>();
            return (BooleanFunction<R> & Memoized) value -> {
                return cache.computeIfAbsent(value, this::apply);
            };
        }
    }

    /**
     * Returns a composed {@link Function2} which represents this {@link BooleanFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method provides the possibility to use this {@code BooleanFunction}
     * with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Function2} which represents this {@code BooleanFunction}.
     */
    @Nonnull
    default Function2<Boolean, R> boxed() {
        return this::apply;
    }
}
