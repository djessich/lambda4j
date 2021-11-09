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
import java.util.function.Function;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableByteConsumer;
import org.lambda4j.consumer.ThrowableConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableByteFunction;
import org.lambda4j.function.conversion.ThrowableByteToCharFunction;
import org.lambda4j.function.conversion.ThrowableByteToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableByteToFloatFunction;
import org.lambda4j.function.conversion.ThrowableByteToIntFunction;
import org.lambda4j.function.conversion.ThrowableByteToLongFunction;
import org.lambda4j.function.conversion.ThrowableByteToShortFunction;
import org.lambda4j.operator.unary.ThrowableByteUnaryOperator;
import org.lambda4j.predicate.ThrowableBytePredicate;

/**
 * Represents a supplier of {@code byte}-valued results which is able to throw any {@link Throwable}. This is a
 * primitive specialization of {@link ThrowableSupplier}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsByteThrows()}.
 *
 * @param <X> The type of the throwable to be thrown by this supplier
 * @see ThrowableSupplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableByteSupplier<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableByteSupplier} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <X> The type of the throwable to be thrown by this supplier
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableByteSupplier} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <X extends Throwable> ThrowableByteSupplier<X> of(@Nullable ThrowableByteSupplier<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableByteSupplier} with the given argument and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this supplier
     * @param supplier The supplier to be called
     * @return The result from the given {@code ThrowableByteSupplier}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this suppliers action
     */
    static <X extends Throwable> byte call(@Nonnull ThrowableByteSupplier<? extends X> supplier) throws X {
        Objects.requireNonNull(supplier);
        return supplier.getAsByteThrows();
    }

    /**
     * Creates a {@link ThrowableByteSupplier} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this supplier
     * @param ret The return value for the constant
     * @return A {@code ThrowableByteSupplier} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableByteSupplier<X> constant(byte ret) {
        return () -> ret;
    }

    /**
     * Applies this supplier to the given argument.
     *
     * @return The return value from the supplier, which is its result.
     * @throws X Any throwable from this suppliers action
     */
    byte getAsByteThrows() throws X;

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
     * Returns a composed {@link ThrowableSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed supplier
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ThrowableSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableSupplier<S, X> andThen(@Nonnull ThrowableByteFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyThrows(getAsByteThrows());
    }

    /**
     * Returns a composed {@link ThrowableBooleanSupplier} that first applies this supplier to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive supplier to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this supplier is applied
     * @return A composed {@code ThrowableBooleanSupplier} that first applies this supplier to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanSupplier<X> andThenToBoolean(@Nonnull ThrowableBytePredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.testThrows(getAsByteThrows());
    }

    /**
     * Returns a composed {@link ThrowableByteSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} operator to the result. This method is just convenience, to provide the ability to transform
     * this primitive supplier to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this supplier is applied
     * @return A composed {@code ThrowableByteSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteSupplier<X> andThenToByte(@Nonnull ThrowableByteUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsByteThrows(getAsByteThrows());
    }

    /**
     * Returns a composed {@link ThrowableCharSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result. This method is just convenience, to provide the ability to transform
     * this primitive supplier to an operation returning {@code char}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ThrowableCharSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharSupplier<X> andThenToChar(@Nonnull ThrowableByteToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsCharThrows(getAsByteThrows());
    }

    /**
     * Returns a composed {@link ThrowableDoubleSupplier} that first applies this supplier to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive supplier to an operation returning {@code double}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ThrowableDoubleSupplier} that first applies this supplier to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleSupplier<X> andThenToDouble(
            @Nonnull ThrowableByteToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsDoubleThrows(getAsByteThrows());
    }

    /**
     * Returns a composed {@link ThrowableFloatSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result. This method is just convenience, to provide the ability to transform
     * this primitive supplier to an operation returning {@code float}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ThrowableFloatSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatSupplier<X> andThenToFloat(@Nonnull ThrowableByteToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsFloatThrows(getAsByteThrows());
    }

    /**
     * Returns a composed {@link ThrowableIntSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result. This method is just convenience, to provide the ability to transform
     * this primitive supplier to an operation returning {@code int}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ThrowableIntSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntSupplier<X> andThenToInt(@Nonnull ThrowableByteToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsIntThrows(getAsByteThrows());
    }

    /**
     * Returns a composed {@link ThrowableLongSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result. This method is just convenience, to provide the ability to transform
     * this primitive supplier to an operation returning {@code long}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ThrowableLongSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongSupplier<X> andThenToLong(@Nonnull ThrowableByteToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsLongThrows(getAsByteThrows());
    }

    /**
     * Returns a composed {@link ThrowableShortSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result. This method is just convenience, to provide the ability to transform
     * this primitive supplier to an operation returning {@code short}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ThrowableShortSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortSupplier<X> andThenToShort(@Nonnull ThrowableByteToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsShortThrows(getAsByteThrows());
    }

    /**
     * Returns a composed {@link ThrowableConsumer} that first gets the result from this supplier, and then consumes the
     * result using the given {@link ThrowableByteConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableConsumer} that first gets the result from this supplier, and then consumes the
     * result using the given {@code ThrowableByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote Due to the fact that a {@link ThrowableByteSupplier} receives no input, we do not need to pass an
     * argument of a particular type to the resulting {@code ThrowableConsumer}. As a result, this method returns a
     * {@code ThrowableConsumer} of {@link Void}, whose argument is ignored. Therefore, the input parameter will always
     * be {@code null} when the resulting consumer is called with {@code Consumer#accept(Object)}.
     */
    @Nonnull
    default ThrowableConsumer<Void, X> consume(@Nonnull ThrowableByteConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return ignored -> consumer.acceptThrows(getAsByteThrows());
    }

    /**
     * Returns a composed {@link ThrowableSupplier} which represents this {@link ThrowableByteSupplier}. Thereby the
     * primitive input argument for this supplier is autoboxed. This method provides the possibility to use this {@code
     * ThrowableByteSupplier} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableSupplier} which represents this {@code ThrowableByteSupplier}.
     */
    @Nonnull
    default ThrowableSupplier<Byte, X> boxed() {
        return this::getAsByteThrows;
    }

    /**
     * Returns a composed {@link ByteSupplier} that applies this supplier to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link ByteSupplier} that applies this supplier to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ByteSupplier nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ByteSupplier} that applies this supplier to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ByteSupplier} that applies this supplier to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ByteSupplier nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        Objects.requireNonNull(mapper);
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ByteSupplier} that first applies this supplier to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same argument of this
     * supplier.
     *
     * @param recover The operation to apply if this supplier throws a {@code Throwable}
     * @return A composed {@link ByteSupplier} that first applies this supplier to its input, and then applies the
     * {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing supplier is {@code null}
     * @implSpec The implementation checks that the returned enclosing supplier from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ByteSupplier recover(@Nonnull Function<? super Throwable, ? extends ByteSupplier> recover) {
        Objects.requireNonNull(recover);
        return () -> {
            try {
                return getAsByteThrows();
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                ByteSupplier supplier = recover.apply(throwable);
                Objects.requireNonNull(supplier, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return supplier.getAsByte();
            }
        };
    }

    /**
     * Returns a composed {@link ByteSupplier} that applies this supplier to its input and sneakily throws the thrown
     * {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that each
     * throwable thrown from the returned composed supplier behaves exactly the same as an <em>unchecked</em> throwable
     * does. As a result, there is no need to handle the throwable of this supplier in the returned composed supplier by
     * either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause, as it would
     * be done in a non sneaky throwing supplier.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing supplier variant of this throwable supplier, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed supplier. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed supplier. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed supplier is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed supplier, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed supplier.
     * <p>
     * With all that mentioned, the following example will demonstrate this methods correct use:
     * <pre>{@code
     * // when called with illegal value ClassNotFoundException is thrown
     * public Class<?> sneakyThrowingFunctionalInterface(final String className) throws ClassNotFoundException {
     *     return ThrowableFunction.of(Class::forName) // create the correct throwable functional interface
     *                .sneakyThrow() // create a non-throwable variant which is able to sneaky throw (this method)
     *                .apply(className); // apply non-throwable variant -> may sneaky throw a throwable
     * }
     *
     * // call the the method which surround the sneaky throwing functional interface
     * public void callingMethod() {
     *     try {
     *         final Class<?> clazz = sneakyThrowingFunctionalInterface("some illegal class name");
     *         // ... do something with clazz ...
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link ByteSupplier} that applies this supplier to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ByteSupplier sneakyThrow() {
        return () -> {
            try {
                return getAsByteThrows();
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}