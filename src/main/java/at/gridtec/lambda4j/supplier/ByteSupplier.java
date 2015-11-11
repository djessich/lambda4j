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
package at.gridtec.lambda4j.supplier;

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

/**
 * Represents a supplier of {@code byte}-valued results. This is the {@code byte}-producing primitive specialization of
 * {@link Supplier}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsByte()}.
 *
 * @see ByteSupplier
 * @see Supplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteSupplier {

    /**
     * Calls the given {@link ByteSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code ByteSupplier}.
     */
    static byte call(@Nonnull final ByteSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsByte();
    }

    /**
     * Creates a {@link ByteSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteSupplier} which always returns a given value.
     */
    @Nonnull
    static ByteSupplier constant(byte ret) {
        return () -> ret;
    }

    /**
     * Gets the supplied result from this {@link ByteSupplier}.
     *
     * @return The supplied result.
     */
    byte getAsByte();

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 0}.
     */
    @Nonnegative
    default int arity() {
        return 0;
    }

    /**
     * Returns a composed {@link Supplier} that first gets the result from this operation, and then applies the {@code
     * after} operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code Supplier} that first gets the result from this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned operation is able to handle every type.
     */
    @Nonnull
    default <R> Supplier<R> andThen(@Nonnull final ByteFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return () -> after.apply(getAsByte());
    }

    /**
     * Returns a composed {@link BooleanSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code BooleanSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default BooleanSupplier andThenToBoolean(@Nonnull final ByteToBooleanFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsBoolean(getAsByte());
    }

    /**
     * Returns a composed {@link ByteSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ByteSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ByteSupplier andThenToByte(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsByte(getAsByte());
    }

    /**
     * Returns a composed {@link CharSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code CharSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default CharSupplier andThenToChar(@Nonnull final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsChar(getAsByte());
    }

    /**
     * Returns a composed {@link DoubleSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code DoubleSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default DoubleSupplier andThenToDouble(@Nonnull final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsDouble(getAsByte());
    }

    /**
     * Returns a composed {@link FloatSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code FloatSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default FloatSupplier andThenToFloat(@Nonnull final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsFloat(getAsByte());
    }

    /**
     * Returns a composed {@link IntSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code IntSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default IntSupplier andThenToInt(@Nonnull final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsInt(getAsByte());
    }

    /**
     * Returns a composed {@link LongSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code LongSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default LongSupplier andThenToLong(@Nonnull final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsLong(getAsByte());

    }

    /**
     * Returns a composed {@link ShortSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ShortSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ShortSupplier andThenToShort(@Nonnull final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsShort(getAsByte());
    }

    /**
     * Returns a composed {@link Consumer} that first gets the result from this operation, and then consumes the result
     * using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer} that first gets the result from this operation, and then consumes the result
     * using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote Due to the fact that a {@link Supplier} receives no input, we do not need to pass an argument of a
     * particular type to the resulting {@code Consumer}. As a result, this method returns a {@code Consumer} of {@link
     * Void}, whose argument is ignored. Therefore, the input parameter will always be {@code null} when the resulting
     * consumer is called with {@code Consumer#accept(Object)}.
     */
    @Nonnull
    default Consumer<Void> consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return ignored -> consumer.accept(getAsByte());
    }

    /**
     * Returns a composed {@link Supplier} which represents this {@link ByteSupplier}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatSupplier} with JRE specific methods, only accepting {@code Supplier}.
     *
     * @return A composed {@code Supplier} which represents this {@code ByteSupplier}.
     */
    @Nonnull
    default Supplier<Byte> boxed() {
        return this::getAsByte;
    }
}
