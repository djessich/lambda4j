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
package org.lambda4j.supplier;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.BooleanConsumer;
import org.lambda4j.consumer.Consumer2;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.BooleanToDoubleFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.BooleanToIntFunction;
import org.lambda4j.function.conversion.BooleanToLongFunction;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.operator.unary.BooleanUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;

/**
 * Represents a supplier of {@code boolean}-valued results.
 * This is a primitive specialization of {@link Supplier2}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsBoolean()}.
 *
 * @apiNote This is a JDK lambda.
 * @see Supplier2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanSupplier2 extends Lambda, BooleanSupplier {

    /**
     * Constructs a {@link BooleanSupplier2} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BooleanSupplier2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static BooleanSupplier2 of(@Nullable final BooleanSupplier2 expression) {
        return expression;
    }

    /**
     * Calls the given {@link BooleanSupplier} with the given argument and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result from the given {@code BooleanSupplier2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static boolean call(@Nonnull final BooleanSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsBoolean();
    }

    /**
     * Creates a {@link BooleanSupplier2} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanSupplier2} which always returns a given value.
     */
    @Nonnull
    static BooleanSupplier2 constant(boolean ret) {
        return () -> ret;
    }

    /**
     * Returns a {@link BooleanSupplier2} that always returns {@code true}.
     *
     * @return A {@link BooleanSupplier2} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static BooleanSupplier2 alwaysTrue() {
        return () -> true;
    }

    /**
     * Returns a {@link BooleanSupplier2} that always returns {@code false}.
     *
     * @return A {@link BooleanSupplier2} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static BooleanSupplier2 alwaysFalse() {
        return () -> false;
    }

    /**
     * Applies this supplier to the given argument.
     *
     * @return The return value from the supplier, which is its result.
     */
    boolean getAsBoolean();

    /**
     * Returns the number of arguments for this supplier.
     *
     * @return The number of arguments for this supplier.
     * @implSpec The default implementation always returns {@code 0}.
     */
    @Nonnegative
    default int arity() {
        return 0;
    }

    /**
     * Returns a composed {@link Supplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed supplier
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code Supplier2} that first applies this supplier to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> Supplier2<S> andThen(@Nonnull final BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return () -> after.apply(getAsBoolean());
    }

    /**
     * Returns a composed {@link BooleanSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this supplier is applied
     * @return A composed {@code BooleanSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanSupplier2 andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsBoolean(getAsBoolean());
    }

    /**
     * Returns a composed {@link ByteSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code byte}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ByteSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteSupplier andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsByte(getAsBoolean());
    }

    /**
     * Returns a composed {@link CharSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code char}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code CharSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharSupplier andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsChar(getAsBoolean());
    }

    /**
     * Returns a composed {@link DoubleSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code double}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code DoubleSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleSupplier2 andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsDouble(getAsBoolean());
    }

    /**
     * Returns a composed {@link FloatSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code float}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code FloatSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatSupplier andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsFloat(getAsBoolean());
    }

    /**
     * Returns a composed {@link IntSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code int}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code IntSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntSupplier2 andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsInt(getAsBoolean());
    }

    /**
     * Returns a composed {@link LongSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code long}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code LongSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongSupplier2 andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsLong(getAsBoolean());
    }

    /**
     * Returns a composed {@link ShortSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code short}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ShortSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortSupplier andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsShort(getAsBoolean());
    }

    /**
     * Returns a composed {@link Consumer2} that first gets the result from this supplier, and then consumes
     * the result using the given {@link BooleanConsumer}.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer2} that first gets the result from this supplier, and then consumes the result
     * using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote Due to the fact that a {@link BooleanSupplier2} receives no input, we do not need to pass an argument
     * of a particular type to the resulting {@code Consumer2}. As a result, this method returns a {@code Consumer2} of
     * {@link Void}, whose argument is ignored. Therefore, the input parameter will always be {@code null} when the
     * resulting consumer is called with {@code Consumer#accept(Object)}.
     */
    @Nonnull
    default Consumer2<Void> consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return ignored -> consumer.accept(getAsBoolean());
    }

    /**
     * Returns a memoized (caching) version of this {@link BooleanSupplier2}. Whenever it is called, the return value is
     * preserved in a cache, making subsequent calls returning the memoized value instead of computing the return value
     * again.
     * <p>
     * Unless the supplier and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BooleanSupplier2}.
     * @implSpec This implementation does not allow the return value to be {@code null} for the resulting memoized
     * supplier, as the cache used internally does not permit {@code null} values.
     * @implNote The returned memoized supplier can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BooleanSupplier2 memoized() {
        if (isMemoized()) {
            return this;
        } else {
            AtomicReference<Boolean> cache = new AtomicReference<>();
            return (BooleanSupplier2 & Memoized) () -> {
                Boolean returnValue = cache.get();
                if (returnValue == null) {
                    synchronized (this) {
                        returnValue = cache.get();
                        if (returnValue == null) {
                            returnValue = this.getAsBoolean();
                            cache.set(returnValue);
                        }
                    }
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Supplier2} which represents this {@link BooleanSupplier2}. Thereby the primitive
     * input argument for this supplier is autoboxed. This method provides the possibility to use this
     * {@code BooleanSupplier2} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Supplier2} which represents this {@code BooleanSupplier2}.
     */
    @Nonnull
    default Supplier2<Boolean> boxed() {
        return this::getAsBoolean;
    }

}
