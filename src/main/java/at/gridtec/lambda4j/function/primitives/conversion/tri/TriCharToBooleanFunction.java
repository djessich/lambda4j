/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */
package at.gridtec.lambda4j.function.primitives.conversion.tri;

import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.CharTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiCharToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.tri.CharTriFunction;
import at.gridtec.lambda4j.operators.ternary.CharTernaryOperator;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;
import at.gridtec.lambda4j.predicates.TriPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts three char-valued arguments and produces a boolean-valued result. This is the
 * {@code char}-to-{@code boolean} primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(char, char, char)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriCharToBooleanFunction {

    /**
     * Calls the given {@link TriCharToBooleanFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriCharToBooleanFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static boolean call(@Nonnull final TriCharToBooleanFunction function, char value1, char value2, char value3) {
        Objects.requireNonNull(function);
        return function.applyAsBoolean(value1, value2, value3);
    }

    /**
     * Creates a {@link TriCharToBooleanFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link CharToBooleanFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriCharToBooleanFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code CharToBooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriCharToBooleanFunction onlyFirst(@Nonnull final CharToBooleanFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsBoolean(value1);
    }

    /**
     * Creates a {@link TriCharToBooleanFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link CharToBooleanFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriCharToBooleanFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code CharToBooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriCharToBooleanFunction onlySecond(@Nonnull final CharToBooleanFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsBoolean(value2);
    }

    /**
     * Creates a {@link TriCharToBooleanFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link CharToBooleanFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriCharToBooleanFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code CharToBooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriCharToBooleanFunction onlyThird(@Nonnull final CharToBooleanFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsBoolean(value3);
    }

    /**
     * Creates a {@link TriCharToBooleanFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriCharToBooleanFunction} which always returns a given value.
     */
    @Nonnull
    static TriCharToBooleanFunction constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    boolean applyAsBoolean(char value1, char value2, char value3);

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
     * Returns a composed {@link TriCharToBooleanFunction} that first applies the {@code before} operations to its
     * input, and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code TriCharToBooleanFunction} that first applies the {@code before} operations to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code char}.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriCharToBooleanFunction compose(@Nonnull final CharUnaryOperator before1,
            @Nonnull final CharUnaryOperator before2, @Nonnull final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsBoolean(before1.applyAsChar(value1), before2.applyAsChar(value2),
                                                          before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param <V> The type of the argument to the third before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriPredicate} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default <T, U, V> TriPredicate<T, U, V> compose(@Nonnull final ToCharFunction<? super T> before1,
            @Nonnull final ToCharFunction<? super U> before2, @Nonnull final ToCharFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u, v) -> applyAsBoolean(before1.applyAsChar(t), before2.applyAsChar(u), before3.applyAsChar(v));
    }

    /**
     * Returns a composed {@link CharTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code CharTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator, CharUnaryOperator)
     * @see #compose(ToCharFunction, ToCharFunction, ToCharFunction)
     */
    @Nonnull
    default <R> CharTriFunction<R> andThen(@Nonnull final BooleanFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriCharToBooleanFunction andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsBoolean(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriCharToByteFunction andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link CharTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code CharTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default CharTernaryOperator andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriCharToDoubleFunction andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriCharToFloatFunction andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriCharToIntFunction andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriCharToLongFunction andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriCharToShortFunction andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link CharTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code CharTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharTriConsumer consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default BiCharToBooleanFunction partial(char value1) {
        return (value2, value3) -> applyAsBoolean(value1, value2, value3);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default CharToBooleanFunction partial(char value1, char value2) {
        return value3 -> applyAsBoolean(value1, value2, value3);
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
    default BooleanSupplier partial(char value1, char value2, char value3) {
        return () -> applyAsBoolean(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriCharToBooleanFunction}. Thereby the
     * primitive input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriCharToBooleanFunction}.
     */
    @Nonnull
    default TriFunction<Character, Character, Character, Boolean> boxed() {
        return this::applyAsBoolean;
    }
}
