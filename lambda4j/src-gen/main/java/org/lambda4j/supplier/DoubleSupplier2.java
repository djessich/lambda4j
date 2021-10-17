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
import org.lambda4j.consumer.Consumer2;
import org.lambda4j.function.conversion.DoubleToByteFunction;
import org.lambda4j.function.conversion.DoubleToCharFunction;
import org.lambda4j.function.conversion.DoubleToFloatFunction;
import org.lambda4j.function.conversion.DoubleToShortFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;

/**
 * Represents a supplier of {@code double}-valued results.
 * This is a primitive specialization of {@link Supplier2}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsDouble()}.
 *
 * @apiNote This is a JDK lambda.
 * @see Supplier2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleSupplier2 extends Lambda, DoubleSupplier {

    /**
     * Constructs a {@link DoubleSupplier2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code DoubleSupplier2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static DoubleSupplier2 of(@Nullable final DoubleSupplier2 expression) {
        return expression;
    }

    /**
     * Calls the given {@link DoubleSupplier} with the given argument and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result from the given {@code DoubleSupplier2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static double call(@Nonnull final DoubleSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsDouble();
    }

    /**
     * Creates a {@link DoubleSupplier2} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code DoubleSupplier2} which always returns a given value.
     */
    @Nonnull
    static DoubleSupplier2 constant(double ret) {
        return () -> ret;
    }

    /**
     * Applies this supplier to the given argument.
     *
     * @return The return value from the supplier, which is its result.
     */
    double getAsDouble();

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
    default <S> Supplier2<S> andThen(@Nonnull final DoubleFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return () -> after.apply(getAsDouble());
    }

    /**
     * Returns a composed {@link BooleanSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this supplier is applied
     * @return A composed {@code BooleanSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanSupplier2 andThenToBoolean(@Nonnull final DoublePredicate after) {
        Objects.requireNonNull(after);
        return () -> after.test(getAsDouble());
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
    default ByteSupplier andThenToByte(@Nonnull final DoubleToByteFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsByte(getAsDouble());
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
    default CharSupplier andThenToChar(@Nonnull final DoubleToCharFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsChar(getAsDouble());
    }

    /**
     * Returns a composed {@link DoubleSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code double}.
     *
     * @param after The operator to apply after this supplier is applied
     * @return A composed {@code DoubleSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleSupplier2 andThenToDouble(@Nonnull final DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsDouble(getAsDouble());
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
    default FloatSupplier andThenToFloat(@Nonnull final DoubleToFloatFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsFloat(getAsDouble());
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
    default IntSupplier2 andThenToInt(@Nonnull final DoubleToIntFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsInt(getAsDouble());
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
    default LongSupplier2 andThenToLong(@Nonnull final DoubleToLongFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsLong(getAsDouble());
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
    default ShortSupplier andThenToShort(@Nonnull final DoubleToShortFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsShort(getAsDouble());
    }

    /**
     * Returns a composed {@link Consumer2} that first gets the result from this supplier, and then consumes
     * the result using the given {@link DoubleConsumer}.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer2} that first gets the result from this supplier, and then consumes the result
     * using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote Due to the fact that a {@link DoubleSupplier2} receives no input, we do not need to pass an argument of
     * a particular type to the resulting {@code Consumer2}. As a result, this method returns a {@code Consumer2} of
     * {@link Void}, whose argument is ignored. Therefore, the input parameter will always be {@code null} when the
     * resulting consumer is called with {@code Consumer#accept(Object)}.
     */
    @Nonnull
    default Consumer2<Void> consume(@Nonnull final DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return ignored -> consumer.accept(getAsDouble());
    }

    /**
     * Returns a memoized (caching) version of this {@link DoubleSupplier2}. Whenever it is called, the return value is
     * preserved in a cache, making subsequent calls returning the memoized value instead of computing the return value
     * again.
     * <p>
     * Unless the supplier and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code DoubleSupplier2}.
     * @implSpec This implementation does not allow the return value to be {@code null} for the resulting memoized
     * supplier, as the cache used internally does not permit {@code null} values.
     * @implNote The returned memoized supplier can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default DoubleSupplier2 memoized() {
        if (isMemoized()) {
            return this;
        } else {
            AtomicReference<Double> cache = new AtomicReference<>();
            return (DoubleSupplier2 & Memoized) () -> {
                Double returnValue = cache.get();
                if (returnValue == null) {
                    synchronized (cache) {
                        returnValue = cache.get();
                        if (returnValue == null) {
                            returnValue = this.getAsDouble();
                            cache.set(returnValue);
                        }
                    }
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Supplier2} which represents this {@link DoubleSupplier2}. Thereby the primitive
     * input argument for this supplier is autoboxed. This method provides the possibility to use this
     * {@code DoubleSupplier2} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code Supplier2} which represents this {@code DoubleSupplier2}.
     */
    @Nonnull
    default Supplier2<Double> boxed() {
        return this::getAsDouble;
    }

}