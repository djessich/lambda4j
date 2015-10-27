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
package at.gridtec.lambda4j.function.primitives.conversion.bi;

import at.gridtec.lambda4j.consumer.primitives.bi.ByteBiConsumer;
import at.gridtec.lambda4j.function.primitives.bi.ByteBiFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.ToDoubleBiFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two byte-valued arguments and produces a double-valued result. This is the {@code
 * byte}-to-{@code double} primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(byte, byte)}.
 *
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiByteToDoubleFunction {

    /**
     * Calls the given {@link BiByteToDoubleFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiByteToDoubleFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static double call(@Nonnull final BiByteToDoubleFunction function, byte value1, byte value2) {
        Objects.requireNonNull(function);
        return function.applyAsDouble(value1, value2);
    }

    /**
     * Creates a {@link BiByteToDoubleFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ByteToDoubleFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiByteToDoubleFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ByteToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiByteToDoubleFunction onlyFirst(@Nonnull final ByteToDoubleFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsDouble(value1);
    }

    /**
     * Creates a {@link BiByteToDoubleFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ByteToDoubleFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiByteToDoubleFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ByteToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiByteToDoubleFunction onlySecond(@Nonnull final ByteToDoubleFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsDouble(value2);
    }

    /**
     * Creates a {@link BiByteToDoubleFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiByteToDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static BiByteToDoubleFunction constant(double ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    double applyAsDouble(byte value1, byte value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiByteToDoubleFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code BiByteToDoubleFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code byte}.
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default BiByteToDoubleFunction compose(@Nonnull final ByteUnaryOperator before1,
            @Nonnull final ByteUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsDouble(before1.applyAsByte(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToDoubleBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(DoubleFunction)
     */
    @Nonnull
    default <T, U> ToDoubleBiFunction<T, U> compose(@Nonnull final ToByteFunction<? super T> before1,
            @Nonnull final ToByteFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsDouble(before1.applyAsByte(t), before2.applyAsByte(u));
    }

    /**
     * Returns a composed {@link ByteBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ByteBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(ByteUnaryOperator, ByteUnaryOperator)
     * @see #compose(ToByteFunction, ToByteFunction)
     */
    @Nonnull
    default <R> ByteBiFunction<R> andThen(@Nonnull final DoubleFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link ByteBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link DoubleConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ByteBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteBiConsumer consume(@Nonnull final DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsDouble(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link BiByteToDoubleFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BiByteToDoubleFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code BiByteToDoubleFunction}.
     */
    @Nonnull
    default BiFunction<Byte, Byte, Double> boxed() {
        return this::applyAsDouble;
    }
}
