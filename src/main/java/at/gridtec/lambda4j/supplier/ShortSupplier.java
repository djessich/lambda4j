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

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ShortToLongFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

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
 * Represents a supplier of {@code short}-valued results. This is the {@code short}-producing primitive specialization
 * of {@link Supplier}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsShort()}.
 *
 * @see java.util.function.Supplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortSupplier {

    /**
     * Calls the given {@link ShortSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code ShortSupplier}.
     */
    static short call(@Nonnull final ShortSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsShort();
    }

    /**
     * Creates a {@link ShortSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortSupplier} which always returns a given value.
     */
    @Nonnull
    static ShortSupplier constant(short ret) {
        return () -> ret;
    }

    /**
     * Gets the supplied result from this supplier.
     *
     * @return The supplied result.
     */
    short getAsShort();

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
    default <R> Supplier<R> andThen(@Nonnull final ShortFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return () -> after.apply(getAsShort());
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
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default BooleanSupplier andThenToBoolean(@Nonnull final ShortToBooleanFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsBoolean(getAsShort());
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
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ByteSupplier andThenToByte(@Nonnull final ShortToByteFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsByte(getAsShort());
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
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default CharSupplier andThenToChar(@Nonnull final ShortToCharFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsChar(getAsShort());
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
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default DoubleSupplier andThenToDouble(@Nonnull final ShortToDoubleFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsDouble(getAsShort());
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
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default FloatSupplier andThenToFloat(@Nonnull final ShortToFloatFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsFloat(getAsShort());
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
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default IntSupplier andThenToInt(@Nonnull final ShortToIntFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsInt(getAsShort());
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
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default LongSupplier andThenToLong(@Nonnull final ShortToLongFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsLong(getAsShort());

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
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default ShortSupplier andThenToShort(@Nonnull final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsShort(getAsShort());
    }

    /**
     * Returns a composed {@link Consumer} that first gets the result from this operation, and then consumes the result
     * using the given {@link ShortConsumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer} that first gets the result from this operation, and then consumes the result
     * using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote Due to the fact that a {@link Supplier} receives no input, we do not need to pass an argument of a
     * particular type to the resulting {@code Consumer}. As a result, this method returns a {@code Consumer} of {@link
     * Void}, whose argument is ignored. Therefore, the input parameter will always be {@code null} when the resulting
     * consumer is called with {@code Consumer#accept(Object)}.
     */
    @Nonnull
    default Consumer<Void> consume(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return ignored -> consumer.accept(getAsShort());
    }

    /**
     * Returns a composed {@link Supplier} which represents this {@link ShortSupplier}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortSupplier} with JRE specific methods, only accepting {@code Supplier}.
     *
     * @return A composed {@code Supplier} which represents this {@code ShortSupplier}.
     */
    @Nonnull
    default Supplier<Short> boxed() {
        return this::getAsShort;
    }
}
