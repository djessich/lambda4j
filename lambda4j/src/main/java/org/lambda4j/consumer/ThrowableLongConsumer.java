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

package org.lambda4j.consumer;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.LongConsumer;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import org.lambda4j.function.conversion.ThrowableByteToLongFunction;
import org.lambda4j.function.conversion.ThrowableCharToLongFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToLongFunction;
import org.lambda4j.function.conversion.ThrowableFloatToLongFunction;
import org.lambda4j.function.conversion.ThrowableIntToLongFunction;
import org.lambda4j.function.conversion.ThrowableShortToLongFunction;
import org.lambda4j.function.to.ThrowableToLongFunction;
import org.lambda4j.operator.unary.ThrowableLongUnaryOperator;
import org.lambda4j.util.ThrowableUtils;

/**
 * Represents an operation that accepts one {@code long}-valued input argument and returns no result which is able to
 * throw any {@link Throwable}. This is a primitive specialization of {@link ThrowableConsumer}. Unlike most other
 * functional interfaces, {@code ThrowableLongConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #acceptThrows(long)}.
 *
 * @param <X> The type of the throwable to be thrown by this consumer
 * @apiNote This is a throwable JDK lambda.
 * @see ThrowableConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableLongConsumer<X extends Throwable> extends Lambda, LongConsumer {

    /**
     * Constructs a {@link ThrowableLongConsumer} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableLongConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <X extends Throwable> ThrowableLongConsumer<X> of(@Nullable ThrowableLongConsumer<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableLongConsumer} with the given argument and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer to be called
     * @param value The argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this consumers action
     */
    static <X extends Throwable> void call(@Nonnull ThrowableLongConsumer<? extends X> consumer,
            long value) throws X {
        Objects.requireNonNull(consumer);
        consumer.acceptThrows(value);
    }

    /**
     * Applies this consumer to the given argument.
     *
     * @param value The argument to the consumer
     * @throws X Any throwable from this consumers action
     */
    void acceptThrows(long value) throws X;

    /**
     * Applies this consumer to the given argument.
     *
     * @param value The argument to the consumer
     * @apiNote This method mainly exists to use this {@link ThrowableLongConsumer} in JRE specific methods only
     * accepting {@link LongConsumer}. If this consumer should be applied, then the {@link #acceptThrows(long)} method
     * should be used.
     * @apiNote Overrides the {@link LongConsumer#accept(long)} method by using a redefinition as default method. This
     * implementation calls the {@link #acceptThrows(long)} method of this function and catches the eventually thrown
     * {@link Throwable} from it. If it is of type {@link RuntimeException} or {@link Error} it is rethrown as is. Other
     * {@code Throwable} types are wrapped in a {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default void accept(long value) {
        nest().accept(value);
    }

    /**
     * Returns the number of arguments for this consumer.
     *
     * @return The number of arguments for this consumer.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ThrowableConsumer} that first applies the {@code before} function to its input, and
     * then applies this consumer to the result.
     *
     * @param <A> The type of the argument to the given function, and of composed consumer
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ThrowableConsumer} that first applies the {@code before} function to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ThrowableConsumer<A, X> compose(@Nonnull ThrowableToLongFunction<? super A, ? extends X> before) {
        Objects.requireNonNull(before);
        return a -> acceptThrows(before.applyAsLongThrows(a));
    }

    /**
     * Returns a composed {@link ThrowableBooleanConsumer} that first applies the {@code before} function to its input,
     * and then applies this consumer to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code boolean} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ThrowableBooleanConsumer} that first applies the {@code before} function to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanConsumer<X> composeFromBoolean(
            @Nonnull ThrowableBooleanToLongFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> acceptThrows(before.applyAsLongThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableByteConsumer} that first applies the {@code before} function to its input, and
     * then applies this consumer to the result. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ThrowableByteConsumer} that first applies the {@code before} function to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteConsumer<X> composeFromByte(@Nonnull ThrowableByteToLongFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> acceptThrows(before.applyAsLongThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableCharConsumer} that first applies the {@code before} function to its input, and
     * then applies this consumer to the result. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ThrowableCharConsumer} that first applies the {@code before} function to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharConsumer<X> composeFromChar(@Nonnull ThrowableCharToLongFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> acceptThrows(before.applyAsLongThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleConsumer} that first applies the {@code before} function to its input,
     * and then applies this consumer to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code double} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ThrowableDoubleConsumer} that first applies the {@code before} function to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleConsumer<X> composeFromDouble(
            @Nonnull ThrowableDoubleToLongFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> acceptThrows(before.applyAsLongThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableFloatConsumer} that first applies the {@code before} function to its input,
     * and then applies this consumer to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code float} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ThrowableFloatConsumer} that first applies the {@code before} function to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatConsumer<X> composeFromFloat(
            @Nonnull ThrowableFloatToLongFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> acceptThrows(before.applyAsLongThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntConsumer} that first applies the {@code before} function to its input, and
     * then applies this consumer to the result. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ThrowableIntConsumer} that first applies the {@code before} function to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntConsumer<X> composeFromInt(@Nonnull ThrowableIntToLongFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> acceptThrows(before.applyAsLongThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableLongConsumer} that first applies the {@code before} operator to its input, and
     * then applies this consumer to the result. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive consumer is executed.
     *
     * @param before The operator to apply before this consumer is applied
     * @return A composed {@code ThrowableLongConsumer} that first applies the {@code before} operator to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongConsumer<X> composeFromLong(@Nonnull ThrowableLongUnaryOperator<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> acceptThrows(before.applyAsLongThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableShortConsumer} that first applies the {@code before} function to its input,
     * and then applies this consumer to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code short} input, before this primitive consumer is executed.
     *
     * @param before The function to apply before this consumer is applied
     * @return A composed {@code ThrowableShortConsumer} that first applies the {@code before} function to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortConsumer<X> composeFromShort(
            @Nonnull ThrowableShortToLongFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return value -> acceptThrows(before.applyAsLongThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableLongConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer. If performing this consumer throws an exception, the {@code after} consumer will not be
     * performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link ThrowableLongConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableLongConsumer<X> andThen(@Nonnull ThrowableLongConsumer<? extends X> after) {
        Objects.requireNonNull(after);
        return value -> {
            acceptThrows(value);
            after.acceptThrows(value);
        };
    }

    /**
     * Returns a reversed version of this consumer. This may be useful in recursive context.
     *
     * @return A reversed version of this consumer.
     */
    @Nonnull
    default ThrowableLongConsumer<X> reversed() {
        return this;
    }

    /**
     * Returns a composed {@link ThrowableConsumer} which represents this {@link ThrowableLongConsumer}. Thereby the
     * primitive input argument for this consumer is autoboxed. This method provides the possibility to use this {@code
     * ThrowableLongConsumer} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableConsumer} which represents this {@code ThrowableLongConsumer}.
     */
    @Nonnull
    default ThrowableConsumer<Long, X> boxed() {
        return this::acceptThrows;
    }

    /**
     * Returns a composed {@link LongConsumer2} that applies this consumer to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link LongConsumer2} that applies this consumer to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default LongConsumer2 nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link LongConsumer2} that applies this consumer to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link LongConsumer2} that applies this consumer to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default LongConsumer2 nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        Objects.requireNonNull(mapper);
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link LongConsumer2} that first applies this consumer to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same argument of this
     * consumer.
     *
     * @param recover The operation to apply if this consumer throws a {@code Throwable}
     * @return A composed {@link LongConsumer2} that first applies this consumer to its input, and then applies the
     * {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing consumer is {@code null}
     * @implSpec The implementation checks that the returned enclosing consumer from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default LongConsumer2 recover(@Nonnull Function<? super Throwable, ? extends LongConsumer> recover) {
        Objects.requireNonNull(recover);
        return value -> {
            try {
                acceptThrows(value);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                LongConsumer consumer = recover.apply(throwable);
                Objects.requireNonNull(consumer, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                consumer.accept(value);
            }
        };
    }

    /**
     * Returns a composed {@link LongConsumer2} that applies this consumer to its input and sneakily throws the thrown
     * {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that each
     * throwable thrown from the returned composed consumer behaves exactly the same as an <em>unchecked</em> throwable
     * does. As a result, there is no need to handle the throwable of this consumer in the returned composed consumer by
     * either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause, as it would
     * be done in a non sneaky throwing consumer.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing consumer variant of this throwable consumer, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed consumer. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed consumer. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed consumer is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed consumer, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed consumer.
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
     * @return A composed {@link LongConsumer2} that applies this consumer to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default LongConsumer2 sneakyThrow() {
        return value -> {
            try {
                acceptThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
