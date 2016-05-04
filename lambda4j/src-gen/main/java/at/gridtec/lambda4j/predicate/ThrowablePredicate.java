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
package at.gridtec.lambda4j.predicate;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableBooleanConsumer;
import at.gridtec.lambda4j.consumer.ThrowableConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableBooleanFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToIntFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
import at.gridtec.lambda4j.function.to.ThrowableToByteFunction;
import at.gridtec.lambda4j.function.to.ThrowableToCharFunction;
import at.gridtec.lambda4j.function.to.ThrowableToDoubleFunction;
import at.gridtec.lambda4j.function.to.ThrowableToFloatFunction;
import at.gridtec.lambda4j.function.to.ThrowableToIntFunction;
import at.gridtec.lambda4j.function.to.ThrowableToLongFunction;
import at.gridtec.lambda4j.function.to.ThrowableToShortFunction;
import at.gridtec.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * Represents an predicate (boolean-valued function) of one input argument which is able to throw any {@link
 * Throwable}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(Object)}.
 *
 * @param <T> The type of the argument to the predicate
 * @param <X> The type of the throwable to be thrown by this predicate
 * @apiNote This is a throwable JDK lambda.
 * @see ThrowablePredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowablePredicate<T, X extends Throwable> extends Lambda, Predicate<T> {

    /**
     * Constructs a {@link ThrowablePredicate} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowablePredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T, X extends Throwable> ThrowablePredicate<T, X> of(@Nonnull final ThrowablePredicate<T, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowablePredicate} with the given argument and returns its result.
     *
     * @param <T> The type of the argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate to be called
     * @param t The argument to the predicate
     * @return The result from the given {@code ThrowablePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this predicates action
     */
    static <T, X extends Throwable> boolean call(@Nonnull final ThrowablePredicate<? super T, ? extends X> predicate,
            T t) throws X {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(t);
    }

    /**
     * Creates a {@link ThrowablePredicate} which always returns a given value.
     *
     * @param <T> The type of the argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowablePredicate} which always returns a given value.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowablePredicate<T, X> constant(boolean ret) {
        return (t) -> ret;
    }

    /**
     * Returns a {@link ThrowablePredicate} that always returns {@code true}.
     *
     * @param <T> The type of the argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowablePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T, X extends Throwable> ThrowablePredicate<T, X> alwaysTrue() {
        return (t) -> true;
    }

    /**
     * Returns a {@link ThrowablePredicate} that always returns {@code false}.
     *
     * @param <T> The type of the argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowablePredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T, X extends Throwable> ThrowablePredicate<T, X> alwaysFalse() {
        return (t) -> false;
    }

    /**
     * Returns a {@link ThrowablePredicate} that tests if the given argument are <b>equal</b> to the one of this
     * predicate.
     *
     * @param <T> The type of the argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param target The reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowablePredicate} that tests if the given argument are <b>equal</b> to the one of this
     * predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowablePredicate<T, X> isEqual(@Nullable Object target) {
        return (t) -> (t == null ? target == null : t.equals(target));
    }

    /**
     * Applies this predicate to the given argument.
     *
     * @param t The argument to the predicate
     * @return The return value from the predicate, which is its result.
     * @throws X Any throwable from this predicates action
     */
    boolean testThrows(T t) throws X;

    /**
     * Applies this predicate to the given argument.
     *
     * @param t The argument to the predicate
     * @return The return value from the predicate, which is its result.
     * @apiNote This method mainly exists to use this {@link ThrowablePredicate} in JRE specific methods only accepting
     * {@link Predicate}. If this predicate should be applied, then the {@link #testThrows(Object)} method should be
     * used.
     * @implSpec Overrides the {@link Predicate#test(Object)} method by using a redefinition as default method. This
     * implementation calls the {@link #testThrows(Object)} method of this function and catches the eventually thrown
     * {@link Throwable} from it. If it is of type {@link RuntimeException} or {@link Error} it is rethrown as is. Other
     * {@code Throwable} types are wrapped in a {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default boolean test(T t) {
        // TODO: Remove commented code below
    /*try {
         return this.testThrows(t);
    } catch (RuntimeException | Error e) {
        throw e;
    } catch (Throwable throwable) {
        throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
    }*/
        return nest().test(t);
    }

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ThrowablePredicate} that first applies the {@code before} function to its input, and
     * then applies this predicate to the result.
     *
     * @param <A> The type of the argument to the given function, and of composed predicate
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code ThrowablePredicate} that first applies the {@code before} function to its input, and
     * then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ThrowablePredicate<A, X> compose(
            @Nonnull final ThrowableFunction<? super A, ? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return (a) -> testThrows(before.applyThrows(a));
    }

    /**
     * Returns a composed {@link ThrowableFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableFunction<T, S, X> andThen(
            @Nonnull final ThrowableBooleanFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyThrows(testThrows(t));
    }

    /**
     * Returns a composed {@link ThrowablePredicate} that first applies this predicate to its input, and then applies
     * the {@code after} operator to the result. This method is just convenience, to provide the ability to transform
     * this primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code ThrowablePredicate} that first applies this predicate to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowablePredicate<T, X> andThenToBoolean(@Nonnull final ThrowableBooleanUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsBooleanThrows(testThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableToByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableToByteFunction<T, X> andThenToByte(
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsByteThrows(testThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableToCharFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableToCharFunction<T, X> andThenToChar(
            @Nonnull final ThrowableBooleanToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsCharThrows(testThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableToDoubleFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableToDoubleFunction<T, X> andThenToDouble(
            @Nonnull final ThrowableBooleanToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsDoubleThrows(testThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableToFloatFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableToFloatFunction<T, X> andThenToFloat(
            @Nonnull final ThrowableBooleanToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsFloatThrows(testThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableToIntFunction<T, X> andThenToInt(@Nonnull final ThrowableBooleanToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsIntThrows(testThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableToLongFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableToLongFunction<T, X> andThenToLong(
            @Nonnull final ThrowableBooleanToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsLongThrows(testThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableToShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableToShortFunction<T, X> andThenToShort(
            @Nonnull final ThrowableBooleanToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsShortThrows(testThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableConsumer} that fist applies this predicate to its input, and then consumes the
     * result using the given {@link ThrowableBooleanConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableConsumer} that first applies this predicate to its input, and then consumes
     * the result using the given {@code ThrowableBooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableConsumer<T, X> consume(@Nonnull final ThrowableBooleanConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t) -> consumer.acceptThrows(testThrows(t));
    }

    /**
     * Returns a {@link ThrowablePredicate} that represents the logical negation of this one.
     *
     * @return A {@code ThrowablePredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default ThrowablePredicate<T, X> negate() {
        return (t) -> !testThrows(t);
    }

    /**
     * Returns a composed {@link ThrowablePredicate} that represents a short-circuiting logical AND of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowablePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowablePredicate} that will be logically-ANDed with this one
     * @return A composed {@code ThrowablePredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(ThrowablePredicate)
     * @see #xor(ThrowablePredicate)
     */
    @Nonnull
    default ThrowablePredicate<T, X> and(@Nonnull final ThrowablePredicate<? super T, ? extends X> other) {
        Objects.requireNonNull(other);
        return (t) -> testThrows(t) && other.testThrows(t);
    }

    /**
     * Returns a composed {@link ThrowablePredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowablePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowablePredicate} that will be logically-ORed with this one
     * @return A composed {@code ThrowablePredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowablePredicate)
     * @see #xor(ThrowablePredicate)
     */
    @Nonnull
    default ThrowablePredicate<T, X> or(@Nonnull final ThrowablePredicate<? super T, ? extends X> other) {
        Objects.requireNonNull(other);
        return (t) -> testThrows(t) || other.testThrows(t);
    }

    /**
     * Returns a composed {@link ThrowablePredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation
     * of this {@code ThrowablePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowablePredicate} that will be logically-XORed with this one
     * @return A composed {@code ThrowablePredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowablePredicate)
     * @see #or(ThrowablePredicate)
     */
    @Nonnull
    default ThrowablePredicate<T, X> xor(@Nonnull final ThrowablePredicate<? super T, ? extends X> other) {
        Objects.requireNonNull(other);
        return (t) -> testThrows(t) ^ other.testThrows(t);
    }

    /**
     * Returns a reversed version of this predicate. This may be useful in recursive context.
     *
     * @return A reversed version of this predicate.
     */
    @Nonnull
    default ThrowablePredicate<T, X> reversed() {
        return (t) -> testThrows(t);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowablePredicate}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowablePredicate}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowablePredicate<T, X> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<T, Boolean> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowablePredicate<T, X> & Memoized) (t) -> {
                final boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(t, ThrowableFunction.of(this::testThrows));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Predicate2} that applies this predicate to its input and nests the thrown {@link
     * Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is nested
     * (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown throwables
     * message and the thrown throwable itself.
     *
     * @return A composed {@code Predicate2} that applies this predicate to its input and nests the thrown {@code
     * Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default Predicate2<T> nest() {
        return (t) -> {
            try {
                return this.testThrows(t);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link Predicate2} that applies this predicate to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that each
     * throwable thrown from the returned composed predicate behaves exactly the same as an <em>unchecked</em> throwable
     * does. As a result, there is no need to handle the throwable of this predicate in the returned composed predicate
     * by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause, as it
     * would be done in a non sneaky throwing predicate.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing predicate variant of this throwable predicate, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed predicate. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed predicate. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed predicate is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed predicate, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed predicate.
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
     * @return A composed {@link Predicate2} that applies this predicate to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default Predicate2<T> sneakyThrow() {
        return (t) -> {
            try {
                return this.testThrows(t);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
