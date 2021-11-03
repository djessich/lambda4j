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

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.Consumer2;

/**
 * Represents a supplier of results.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #get()}.
 *
 * @param <R> The type of return value from the supplier
 * @apiNote This is a JDK lambda.
 * @see Supplier2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface Supplier2<R> extends Lambda, Supplier<R> {

    /**
     * Constructs a {@link Supplier2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <R> The type of return value from the supplier
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code Supplier2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <R> Supplier2<R> of(@Nullable Supplier2<R> expression) {
        return expression;
    }

    /**
     * Lifts a partial {@link Supplier} into a total {@link Supplier2} that returns an {@link Optional} result.
     *
     * @param <R> The type of return value from the supplier
     * @param partial A function that is only defined for some values in its domain
     * @return A partial {@code Supplier} lifted into a total {@code Supplier2} that returns an {@code Optional} result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> Supplier2<Optional<R>> lift(@Nonnull Supplier<? extends R> partial) {
        Objects.requireNonNull(partial);
        return () -> Optional.ofNullable(partial.get());
    }

    /**
     * Calls the given {@link Supplier} with the given argument and returns its result.
     *
     * @param <R> The type of return value from the supplier
     * @param supplier The supplier to be called
     * @return The result from the given {@code Supplier2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <R> R call(@Nonnull Supplier<? extends R> supplier) {
        Objects.requireNonNull(supplier);
        return supplier.get();
    }

    /**
     * Creates a {@link Supplier2} which always returns a given value.
     *
     * @param <R> The type of return value from the supplier
     * @param ret The return value for the constant
     * @return A {@code Supplier2} which always returns a given value.
     */
    @Nonnull
    static <R> Supplier2<R> constant(R ret) {
        return () -> ret;
    }

    /**
     * Applies this supplier to the given argument.
     *
     * @return The return value from the supplier, which is its result.
     */
    @Override
    R get();

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
     * Returns a composed {@link Supplier2} that first applies this supplier to its input, and then applies the {@code
     * after} function to the result. If evaluation of either operation throws an exception, it is relayed to the caller
     * of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed supplier
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code Supplier2} that first applies this supplier to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> Supplier2<S> andThen(@Nonnull Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return () -> after.apply(get());
    }

    /**
     * Returns a composed {@link Consumer2} that first gets the result from this supplier, and then consumes the result
     * using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer2} that first gets the result from this supplier, and then consumes the result
     * using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote Due to the fact that a {@link Supplier2} receives no input, we do not need to pass an argument of a
     * particular type to the resulting {@code Consumer2}. As a result, this method returns a {@code Consumer2} of
     * {@link Void}, whose argument is ignored. Therefore, the input parameter will always be {@code null} when the
     * resulting consumer is called with {@code Consumer#accept(Object)}.
     */
    @Nonnull
    default Consumer2<Void> consume(@Nonnull Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return ignored -> consumer.accept(get());
    }

    /**
     * Converts this supplier to an equal supplier, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this supplier.
     *
     * @return An equal supplier, which ensures that its result is not {@code null}.
     * @deprecated Use {@code lift} method for lifting this function.
     */
    @Deprecated
    @Nonnull
    default Supplier2<Optional<R>> nonNull() {
        return () -> Optional.ofNullable(get());
    }

}
