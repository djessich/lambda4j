/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.functions.supplier;

import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.consumer.ThrowableBooleanConsumer;
import at.gridtec.lambda4j.functions.consumer.ThrowableConsumer;
import at.gridtec.lambda4j.functions.function.ThrowableBooleanFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToDoubleFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToLongFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToShortFunction;
import at.gridtec.lambda4j.functions.operator.unary.ThrowableBooleanUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * Represents a supplier of {@code boolean}-valued results which is able to throw any {@link Throwable}. This is a
 * primitive specialization of {@link ThrowableSupplier}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsBooleanThrows()}.
 *
 * @param <X> The type of the throwable to be thrown by this supplier
 * @apiNote This is a throwable JDK lambda.
 * @see ThrowableSupplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBooleanSupplier<X extends Throwable> extends Lambda, BooleanSupplier {

    /**
     * Constructs a {@link ThrowableBooleanSupplier} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this supplier
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBooleanSupplier} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <X extends Throwable> ThrowableBooleanSupplier<X> of(@Nonnull final ThrowableBooleanSupplier<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBooleanSupplier} with the given argument and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this supplier
     * @param supplier The supplier to be called
     * @return The result from the given {@code ThrowableBooleanSupplier}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this suppliers action
     */
    static <X extends Throwable> boolean call(@Nonnull final ThrowableBooleanSupplier<? extends X> supplier) throws X {
        Objects.requireNonNull(supplier);
        return supplier.getAsBooleanThrows();
    }

    /**
     * Creates a {@link ThrowableBooleanSupplier} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this supplier
     * @param ret The return value for the constant
     * @return A {@code ThrowableBooleanSupplier} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableBooleanSupplier<X> constant(boolean ret) {
        return () -> ret;
    }

    /**
     * Applies this supplier to the given argument.
     *
     * @return The return value from the supplier, which is its result.
     * @throws X Any throwable from this suppliers action
     */
    boolean getAsBooleanThrows() throws X;

    /**
     * Applies this supplier to the given argument.
     *
     * @return The return value from the supplier, which is its result.
     * @apiNote This method mainly exists to use this {@link ThrowableBooleanSupplier} in JRE specific methods only
     * accepting {@link BooleanSupplier}. If this supplier should be applied, then the {@link #getAsBooleanThrows()}
     * method should be used.
     * @implSpec Overrides the {@link BooleanSupplier#getAsBoolean()} method by using a redefinition as default method.
     * This implementation calls the {@link #getAsBooleanThrows()} method of this function and catches the eventually
     * thrown {@link Throwable} from it. If it is of type {@link RuntimeException} or {@link Error} it is rethrown as
     * is. Other {@code Throwable} types are wrapped in a {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default boolean getAsBoolean() {
        // TODO: Remove commented code below
    /*try {
         return this.getAsBooleanThrows();
    } catch (RuntimeException | Error e) {
        throw e;
    } catch (Throwable throwable) {
        throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
    }*/
        return nest().getAsBoolean();
    }

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
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableSupplier<S, X> andThen(
            @Nonnull final ThrowableBooleanFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyThrows(getAsBooleanThrows());
    }

    /**
     * Returns a composed {@link ThrowableBooleanSupplier} that first applies this supplier to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive supplier to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this supplier is applied
     * @return A composed {@code ThrowableBooleanSupplier} that first applies this supplier to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanSupplier<X> andThenToBoolean(
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsBooleanThrows(getAsBooleanThrows());
    }

    /**
     * Returns a composed {@link ThrowableByteSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result. This method is just convenience, to provide the ability to transform
     * this primitive supplier to an operation returning {@code byte}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ThrowableByteSupplier} that first applies this supplier to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteSupplier<X> andThenToByte(@Nonnull final ThrowableBooleanToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsByteThrows(getAsBooleanThrows());
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
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharSupplier<X> andThenToChar(@Nonnull final ThrowableBooleanToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsCharThrows(getAsBooleanThrows());
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
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleSupplier<X> andThenToDouble(
            @Nonnull final ThrowableBooleanToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsDoubleThrows(getAsBooleanThrows());
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
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatSupplier<X> andThenToFloat(
            @Nonnull final ThrowableBooleanToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsFloatThrows(getAsBooleanThrows());
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
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntSupplier<X> andThenToInt(@Nonnull final ThrowableBooleanToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsIntThrows(getAsBooleanThrows());
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
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongSupplier<X> andThenToLong(@Nonnull final ThrowableBooleanToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsLongThrows(getAsBooleanThrows());
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
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortSupplier<X> andThenToShort(
            @Nonnull final ThrowableBooleanToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsShortThrows(getAsBooleanThrows());
    }

    /**
     * Returns a composed {@link ThrowableConsumer} that first gets the result from this supplier, and then consumes the
     * result using the given {@link ThrowableBooleanConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableConsumer} that first gets the result from this supplier, and then consumes the
     * result using the given {@code ThrowableBooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote Due to the fact that a {@link ThrowableBooleanSupplier} receives no input, we do not need to pass an
     * argument of a particular type to the resulting {@code ThrowableConsumer}. As a result, this method returns a
     * {@code ThrowableConsumer} of {@link Void}, whose argument is ignored. Therefore, the input parameter will always
     * be {@code null} when the resulting consumer is called with {@code Consumer#accept(Object)}.
     */
    @Nonnull
    default ThrowableConsumer<Void, X> consume(@Nonnull final ThrowableBooleanConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return ignored -> consumer.acceptThrows(getAsBooleanThrows());
    }

    /**
     * Returns a composed {@link ThrowableSupplier} which represents this {@link ThrowableBooleanSupplier}. Thereby the
     * primitive input argument for this supplier is autoboxed.
     *
     * @return A composed {@code ThrowableSupplier} which represents this {@code ThrowableBooleanSupplier}.
     */
    @Nonnull
    default ThrowableSupplier<Boolean, X> boxed() {
        return this::getAsBooleanThrows;
    }

    /**
     * Returns a composed {@link BooleanSupplier2} that applies this supplier to its input and nests the thrown {@link
     * Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is nested
     * (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown throwables
     * message and the thrown throwable itself.
     *
     * @return A composed {@code BooleanSupplier2} that applies this supplier to its input and nests the thrown {@code
     * {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default BooleanSupplier2 nest() {
        return () -> {
            try {
                return this.getAsBooleanThrows();
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link BooleanSupplier2} that applies this supplier to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed supplier behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this supplier in the returned composed
     * supplier by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing supplier.
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
     *         final Class<?> sneakyThrowingFunctionalInterface("some illegal class name");
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link BooleanSupplier2} that applies this supplier to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default BooleanSupplier2 sneakyThrow() {
        return () -> {
            try {
                return this.getAsBooleanThrows();
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}