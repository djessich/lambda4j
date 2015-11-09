/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.function.primitives.tri;

import at.gridtec.lambda4j.consumer.primitives.tri.ByteTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.bi.ByteBiFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.tri.TriByteToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.ternary.ByteTernaryOperator;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts three byte-valued argument and produces a result. This is the {@code
 * byte}-consuming primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(byte, byte, byte)}.
 *
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteTriFunction<R> {

    /**
     * Calls the given {@link ByteTriFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code ByteTriFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static <R> R call(@Nonnull final ByteTriFunction<? extends R> function, byte value1, byte value2, byte value3) {
        Objects.requireNonNull(function);
        return function.apply(value1, value2, value3);
    }

    /**
     * Creates a {@link ByteTriFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link ByteFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ByteTriFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code ByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <R> ByteTriFunction<R> onlyFirst(@Nonnull final ByteFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value1);
    }

    /**
     * Creates a {@link ByteTriFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link ByteFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ByteTriFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@code ByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <R> ByteTriFunction<R> onlySecond(@Nonnull final ByteFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value2);
    }

    /**
     * Creates a {@link ByteTriFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@link ByteFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ByteTriFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@code ByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <R> ByteTriFunction<R> onlyThird(@Nonnull final ByteFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value3);
    }

    /**
     * Creates a {@link ByteTriFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ByteTriFunction} which always returns a given value.
     */
    @Nonnull
    static <R> ByteTriFunction<R> constant(R r) {
        return (value1, value2, value3) -> r;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(byte value1, byte value2, byte value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ByteTriFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code ByteTriFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code byte}.
     * @see #andThen(Function)
     */
    @Nonnull
    default ByteTriFunction<R> compose(@Nonnull final ByteUnaryOperator before1,
            @Nonnull final ByteUnaryOperator before2, @Nonnull final ByteUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2, value3) -> apply(before1.applyAsByte(value1), before2.applyAsByte(value2),
                                                 before3.applyAsByte(value3));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param <V> The type of the argument to the third before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(Function)
     */
    @Nonnull
    default <T, U, V> TriFunction<T, U, V, R> compose(@Nonnull final ToByteFunction<? super T> before1,
            @Nonnull final ToByteFunction<? super U> before2, @Nonnull final ToByteFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u, v) -> apply(before1.applyAsByte(t), before2.applyAsByte(u), before3.applyAsByte(v));
    }

    /**
     * Returns a composed {@link ByteTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ByteUnaryOperator, ByteUnaryOperator, ByteUnaryOperator)
     * @see #compose(ToByteFunction, ToByteFunction, ToByteFunction)
     */
    @Nonnull
    default <S> ByteTriFunction<S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default TriByteToBooleanFunction andThenToBoolean(@Nonnull final Predicate<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default ByteTernaryOperator andThenToByte(@Nonnull final ToByteFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default TriByteToCharFunction andThenToChar(@Nonnull final ToCharFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default TriByteToDoubleFunction andThenToDouble(@Nonnull final ToDoubleFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default TriByteToFloatFunction andThenToFloat(@Nonnull final ToFloatFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default TriByteToIntFunction andThenToInt(@Nonnull final ToIntFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default TriByteToLongFunction andThenToLong(@Nonnull final ToLongFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriByteToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriByteToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default TriByteToShortFunction andThenToShort(@Nonnull final ToShortFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ByteTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ByteTriConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteTriConsumer consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(apply(value1, value2, value3));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ByteBiFunction<R> partial(byte value1) {
        return (value2, value3) -> apply(value1, value2, value3);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ByteFunction<R> partial(byte value1, byte value2) {
        return value3 -> apply(value1, value2, value3);
    }

    /**
     * Applies this function partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @param value3 The third argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default Supplier<R> partial(byte value1, byte value2, byte value3) {
        return () -> apply(value1, value2, value3);
    }

    /**
     * Returns a curried version of this function.
     *
     * @return A curried version of this function.
     */
    @Nonnull
    default ByteFunction<ByteFunction<ByteFunction<R>>> curried() {
        return value1 -> value2 -> value3 -> apply(value1, value2, value3);
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     */
    @Nonnull
    default ByteTriFunction<Optional<R>> nonNull() {
        return (value1, value2, value3) -> Optional.ofNullable(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ByteTriFunction}. Thereby the primitive input
     * argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ByteTriFunction}.
     */
    @Nonnull
    default TriFunction<Byte, Byte, Byte, R> boxed() {
        return this::apply;
    }
}
