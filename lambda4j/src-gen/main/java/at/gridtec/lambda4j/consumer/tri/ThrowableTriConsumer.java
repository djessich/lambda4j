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
package at.gridtec.lambda4j.consumer.tri;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableFunction;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Represents an operation that accepts three input arguments and returns no result which is able to throw any {@link
 * Throwable}. Unlike most other functional interfaces, {@code ThrowableTriConsumer} is expected to operate via
 * side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #acceptThrows(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the consumer
 * @param <U> The type of the second argument to the consumer
 * @param <V> The type of the third argument to the consumer
 * @param <X> The type of the throwable to be thrown by this consumer
 * @see ThrowableTriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableTriConsumer<T, U, V, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableTriConsumer} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <V> The type of the third argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableTriConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableTriConsumer<T, U, V, X> of(
            @Nonnull final ThrowableTriConsumer<T, U, V, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableTriConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <V> The type of the third argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer to be called
     * @param t The first argument to the consumer
     * @param u The second argument to the consumer
     * @param v The third argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this consumers action
     */
    static <T, U, V, X extends Throwable> void call(
            @Nonnull final ThrowableTriConsumer<? super T, ? super U, ? super V, ? extends X> consumer, T t, U u,
            V v) throws X {
        Objects.requireNonNull(consumer);
        consumer.acceptThrows(t, u, v);
    }

    /**
     * Creates a {@link ThrowableTriConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <V> The type of the third argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableTriConsumer} which uses the {@code first} parameter of this one as argument for
     * the given {@code ThrowableConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableTriConsumer<T, U, V, X> onlyFirst(
            @Nonnull final ThrowableConsumer<? super T, ? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.acceptThrows(t);
    }

    /**
     * Creates a {@link ThrowableTriConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <V> The type of the third argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableTriConsumer} which uses the {@code second} parameter of this one as argument
     * for the given {@code ThrowableConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableTriConsumer<T, U, V, X> onlySecond(
            @Nonnull final ThrowableConsumer<? super U, ? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.acceptThrows(u);
    }

    /**
     * Creates a {@link ThrowableTriConsumer} which uses the {@code third} parameter of this one as argument for the
     * given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <V> The type of the third argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableTriConsumer} which uses the {@code third} parameter of this one as argument for
     * the given {@code ThrowableConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V, X extends Throwable> ThrowableTriConsumer<T, U, V, X> onlyThird(
            @Nonnull final ThrowableConsumer<? super V, ? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.acceptThrows(v);
    }

    /**
     * Applies this consumer to the given arguments.
     *
     * @param t The first argument to the consumer
     * @param u The second argument to the consumer
     * @param v The third argument to the consumer
     * @throws X Any throwable from this consumers action
     */
    void acceptThrows(T t, U u, V v) throws X;

    /**
     * Applies this consumer to the given tuple.
     *
     * @param tuple The tuple to be applied to the consumer
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this consumers action
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default void acceptThrows(@Nonnull Triple<T, U, V> tuple) throws X {
        Objects.requireNonNull(tuple);
        acceptThrows(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

    /**
     * Returns the number of arguments for this consumer.
     *
     * @return The number of arguments for this consumer.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ThrowableTriConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed consumer
     * @param <B> The type of the argument to the second given function, and of composed consumer
     * @param <C> The type of the argument to the third given function, and of composed consumer
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code ThrowableTriConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableTriConsumer<A, B, C, X> compose(
            @Nonnull final ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull final ThrowableFunction<? super B, ? extends U, ? extends X> before2,
            @Nonnull final ThrowableFunction<? super C, ? extends V, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> acceptThrows(before1.applyThrows(a), before2.applyThrows(b), before3.applyThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer. If performing this consumer throws an exception, the {@code after} consumer will not be
     * performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link ThrowableTriConsumer} that performs, in sequence, this consumer followed by the {@code
     * after} consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriConsumer<T, U, V, X> andThen(
            @Nonnull final ThrowableTriConsumer<? super T, ? super U, ? super V, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> {
            acceptThrows(t, u, v);
            after.acceptThrows(t, u, v);
        };
    }

    /**
     * Returns a tupled version of this consumer.
     *
     * @return A tupled version of this consumer.
     */
    @Nonnull
    default ThrowableConsumer<Triple<T, U, V>, X> tupled() {
        return this::acceptThrows;
    }

    /**
     * Returns a reversed version of this consumer. This may be useful in recursive context.
     *
     * @return A reversed version of this consumer.
     */
    @Nonnull
    default ThrowableTriConsumer<V, U, T, X> reversed() {
        return (v, u, t) -> acceptThrows(t, u, v);
    }

    /**
     * Returns a composed {@link TriConsumer} that applies this consumer to its input and nests the thrown {@link
     * Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is nested
     * (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown throwables
     * message and the thrown throwable itself.
     *
     * @return A composed {@code TriConsumer} that applies this consumer to its input and nests the thrown {@code
     * Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default TriConsumer<T, U, V> nest() {
        return (t, u, v) -> {
            try {
                this.acceptThrows(t, u, v);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link TriConsumer} that applies this consumer to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that each
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
     *         final Class<?> sneakyThrowingFunctionalInterface("some illegal class name");
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link TriConsumer} that applies this consumer to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default TriConsumer<T, U, V> sneakyThrow() {
        return (t, u, v) -> {
            try {
                this.acceptThrows(t, u, v);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
