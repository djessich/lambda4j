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

package org.lambda4j.function.tri;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.tri.TriCharConsumer;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.bi.BiCharFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.ByteToCharFunction;
import org.lambda4j.function.conversion.DoubleToCharFunction;
import org.lambda4j.function.conversion.FloatToCharFunction;
import org.lambda4j.function.conversion.IntToCharFunction;
import org.lambda4j.function.conversion.LongToCharFunction;
import org.lambda4j.function.conversion.ShortToCharFunction;
import org.lambda4j.function.to.ToCharFunction;
import org.lambda4j.operator.unary.CharUnaryOperator;

/**
 * Represents an operation that accepts three {@code char}-valued input arguments and produces a result. This is a
 * primitive specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(char, char, char)}.
 *
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriCharFunction<R> extends Lambda {

    /**
     * Constructs a {@link TriCharFunction} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriCharFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <R> TriCharFunction<R> of(@Nullable TriCharFunction<R> expression) {
        return expression;
    }

    /**
     * Constructs a {@link TriCharFunction} based on a curried lambda expression. Thereby the given curried lambda
     * expression is converted to the desired uncurried type of same arity. With this method, it is possible to uncurry
     * a curried lambda expression.
     *
     * @param <R> The type of return value from the function
     * @param curried A curried lambda expression, e.g. {@code value1 -> value2 -> value3 -> method(value1, value2,
     * value3)}
     * @return A {@code TriCharFunction} from given curried lambda expression.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <R> TriCharFunction<R> of(@Nullable CharFunction<CharFunction<CharFunction<R>>> curried) {
        if (Objects.isNull(curried)) {
            return null;
        }
        return (value1, value2, value3) -> curried.apply(value1).apply(value2).apply(value3);
    }

    /**
     * Lifts a partial {@link TriCharFunction} into a total {@link TriCharFunction} that returns an {@link Optional}
     * result.
     *
     * @param <R> The type of return value from the function
     * @param partial A function that is only defined for some values in its domain
     * @return A partial {@code TriCharFunction} lifted into a total {@code TriCharFunction} that returns an {@code
     * Optional} result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> TriCharFunction<Optional<R>> lift(@Nonnull TriCharFunction<? extends R> partial) {
        Objects.requireNonNull(partial);
        return (value1, value2, value3) -> Optional.ofNullable(partial.apply(value1, value2, value3));
    }

    /**
     * Calls the given {@link TriCharFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <R> R call(@Nonnull TriCharFunction<? extends R> function, char value1, char value2, char value3) {
        Objects.requireNonNull(function);
        return function.apply(value1, value2, value3);
    }

    /**
     * Creates a {@link TriCharFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link CharFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriCharFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code CharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> TriCharFunction<R> onlyFirst(@Nonnull CharFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value1);
    }

    /**
     * Creates a {@link TriCharFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link CharFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriCharFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@code CharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> TriCharFunction<R> onlySecond(@Nonnull CharFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value2);
    }

    /**
     * Creates a {@link TriCharFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@link CharFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriCharFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@code CharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> TriCharFunction<R> onlyThird(@Nonnull CharFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value3);
    }

    /**
     * Creates a {@link TriCharFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code TriCharFunction} which always returns a given value.
     */
    @Nonnull
    static <R> TriCharFunction<R> constant(R ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(char value1, char value2, char value3);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BiCharFunction} as result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @return A {@code BiCharFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BiCharFunction<R> applyPartially(char value1) {
        return (value2, value3) -> apply(value1, value2, value3);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link CharFunction} as result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @param value2 The second argument to this function used to partially apply this function
     * @return A {@code CharFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default CharFunction<R> applyPartially(char value1, char value2) {
        return value3 -> apply(value1, value2, value3);
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
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> TriFunction<A, B, C, R> compose(@Nonnull ToCharFunction<? super A> before1,
            @Nonnull ToCharFunction<? super B> before2, @Nonnull ToCharFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> apply(before1.applyAsChar(a), before2.applyAsChar(b), before3.applyAsChar(c));
    }

    /**
     * Returns a composed {@link TriBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanFunction<R> composeFromBoolean(@Nonnull BooleanToCharFunction before1,
            @Nonnull BooleanToCharFunction before2, @Nonnull BooleanToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsChar(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteFunction<R> composeFromByte(@Nonnull ByteToCharFunction before1,
            @Nonnull ByteToCharFunction before2, @Nonnull ByteToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsChar(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriCharFunction} that first applies the {@code before} operators to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriCharFunction} that first applies the {@code before} operators to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharFunction<R> composeFromChar(@Nonnull CharUnaryOperator before1,
            @Nonnull CharUnaryOperator before2, @Nonnull CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsChar(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriDoubleFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriDoubleFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleFunction<R> composeFromDouble(@Nonnull DoubleToCharFunction before1,
            @Nonnull DoubleToCharFunction before2, @Nonnull DoubleToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsChar(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatFunction<R> composeFromFloat(@Nonnull FloatToCharFunction before1,
            @Nonnull FloatToCharFunction before2, @Nonnull FloatToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsChar(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntFunction<R> composeFromInt(@Nonnull IntToCharFunction before1,
            @Nonnull IntToCharFunction before2, @Nonnull IntToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsChar(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongFunction<R> composeFromLong(@Nonnull LongToCharFunction before1,
            @Nonnull LongToCharFunction before2, @Nonnull LongToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsChar(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortFunction<R> composeFromShort(@Nonnull ShortToCharFunction before1,
            @Nonnull ShortToCharFunction before2, @Nonnull ShortToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsChar(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriCharFunction<S> andThen(@Nonnull Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriCharConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriCharConsumer consume(@Nonnull Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(apply(value1, value2, value3));
    }

    /**
     * Returns a curried version of this function.
     *
     * @return A curried version of this function.
     */
    @Nonnull
    default CharFunction<CharFunction<CharFunction<R>>> curried() {
        return value1 -> value2 -> value3 -> apply(value1, value2, value3);
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default TriCharFunction<R> reversed() {
        return (value3, value2, value1) -> apply(value1, value2, value3);
    }

    /**
     * Returns a memoized (caching) version of this {@link TriCharFunction}. Whenever it is called, the mapping between
     * the input parameters and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriCharFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriCharFunction<R> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Character, Character, Character>, R> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (TriCharFunction<R> & Memoized) (value1, value2, value3) -> {
                R returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3),
                            key -> apply(key.getLeft(), key.getMiddle(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     * @deprecated Use {@code lift} method for lifting this function.
     */
    @Deprecated
    @Nonnull
    default TriCharFunction<Optional<R>> nonNull() {
        return (value1, value2, value3) -> Optional.ofNullable(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriCharFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method provides the possibility to use this {@code TriCharFunction}
     * with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriCharFunction}.
     */
    @Nonnull
    default TriFunction<Character, Character, Character, R> boxed() {
        return this::apply;
    }

}
