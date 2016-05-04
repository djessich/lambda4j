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
import at.gridtec.lambda4j.consumer.BooleanConsumer;
import at.gridtec.lambda4j.consumer.Consumer2;
import at.gridtec.lambda4j.function.BooleanFunction;
import at.gridtec.lambda4j.function.Function2;
import at.gridtec.lambda4j.function.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.to.ToByteFunction;
import at.gridtec.lambda4j.function.to.ToCharFunction;
import at.gridtec.lambda4j.function.to.ToDoubleFunction2;
import at.gridtec.lambda4j.function.to.ToFloatFunction;
import at.gridtec.lambda4j.function.to.ToIntFunction2;
import at.gridtec.lambda4j.function.to.ToLongFunction2;
import at.gridtec.lambda4j.function.to.ToShortFunction;
import at.gridtec.lambda4j.operator.unary.BooleanUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents an predicate (boolean-valued function) of one input argument.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object)}.
 *
 * @param <T> The type of the argument to the predicate
 * @apiNote This is a JDK lambda.
 * @see Predicate2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface Predicate2<T> extends Lambda, Predicate<T> {

    /**
     * Constructs a {@link Predicate2} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the argument to the predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code Predicate2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T> Predicate2<T> of(@Nonnull final Predicate2<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link Predicate} with the given argument and returns its result.
     *
     * @param <T> The type of the argument to the predicate
     * @param predicate The predicate to be called
     * @param t The argument to the predicate
     * @return The result from the given {@code Predicate2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> boolean call(@Nonnull final Predicate<? super T> predicate, T t) {
        Objects.requireNonNull(predicate);
        return predicate.test(t);
    }

    /**
     * Creates a {@link Predicate2} which always returns a given value.
     *
     * @param <T> The type of the argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code Predicate2} which always returns a given value.
     */
    @Nonnull
    static <T> Predicate2<T> constant(boolean ret) {
        return (t) -> ret;
    }

    /**
     * Returns a {@link Predicate2} that always returns {@code true}.
     *
     * @param <T> The type of the argument to the predicate
     * @return A {@link Predicate2} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T> Predicate2<T> alwaysTrue() {
        return (t) -> true;
    }

    /**
     * Returns a {@link Predicate2} that always returns {@code false}.
     *
     * @param <T> The type of the argument to the predicate
     * @return A {@link Predicate2} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T> Predicate2<T> alwaysFalse() {
        return (t) -> false;
    }

    /**
     * Returns a {@link Predicate2} that tests if the given argument are <b>equal</b> to the one of this predicate.
     *
     * @param <T> The type of the argument to the predicate
     * @param target The reference with which to compare for equality, which may be {@code null}
     * @return A {@code Predicate2} that tests if the given argument are <b>equal</b> to the one of this predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <T> Predicate2<T> isEqual(@Nullable Object target) {
        return (t) -> (t == null ? target == null : t.equals(target));
    }

    /**
     * Applies this predicate to the given argument.
     *
     * @param t The argument to the predicate
     * @return The return value from the predicate, which is its result.
     */
    boolean test(T t);

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
     * Returns a composed {@link Predicate2} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed predicate
     * @param before The function to apply before this predicate is applied
     * @return A composed {@code Predicate2} that first applies the {@code before} function to its input, and then
     * applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> Predicate2<A> compose(@Nonnull final Function<? super A, ? extends T> before) {
        Objects.requireNonNull(before);
        return (a) -> test(before.apply(a));
    }

    /**
     * Returns a composed {@link Function2} that first applies this predicate to its input, and then applies the {@code
     * after} function to the result. If evaluation of either operation throws an exception, it is relayed to the caller
     * of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code Function2} that first applies this predicate to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> Function2<T, S> andThen(@Nonnull final BooleanFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t) -> after.apply(test(t));
    }

    /**
     * Returns a composed {@link Predicate2} that first applies this predicate to its input, and then applies the {@code
     * after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the caller
     * of the composed operation. This method is just convenience, to provide the ability to transform this primitive
     * predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code Predicate2} that first applies this predicate to its input, and then applies the {@code
     * after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default Predicate2<T> andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsBoolean(test(t));
    }

    /**
     * Returns a composed {@link ToByteFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToByteFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ToByteFunction<T> andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsByte(test(t));
    }

    /**
     * Returns a composed {@link ToCharFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToCharFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ToCharFunction<T> andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsChar(test(t));
    }

    /**
     * Returns a composed {@link ToDoubleFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToDoubleFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ToDoubleFunction2<T> andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsDouble(test(t));
    }

    /**
     * Returns a composed {@link ToFloatFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToFloatFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ToFloatFunction<T> andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsFloat(test(t));
    }

    /**
     * Returns a composed {@link ToIntFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToIntFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ToIntFunction2<T> andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsInt(test(t));
    }

    /**
     * Returns a composed {@link ToLongFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToLongFunction2} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ToLongFunction2<T> andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsLong(test(t));
    }

    /**
     * Returns a composed {@link ToShortFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ToShortFunction} that first applies this predicate to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ToShortFunction<T> andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (t) -> after.applyAsShort(test(t));
    }

    /**
     * Returns a composed {@link Consumer2} that fist applies this predicate to its input, and then consumes the result
     * using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer2} that first applies this predicate to its input, and then consumes the result
     * using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default Consumer2<T> consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t) -> consumer.accept(test(t));
    }

    /**
     * Returns a {@link Predicate2} that represents the logical negation of this one.
     *
     * @return A {@code Predicate2} that represents the logical negation of this one.
     */
    @Nonnull
    default Predicate2<T> negate() {
        return (t) -> !test(t);
    }

    /**
     * Returns a composed {@link Predicate2} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code Predicate2} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code Predicate2} that will be logically-ANDed with this one
     * @return A composed {@code Predicate2} that represents the short-circuiting logical AND of this predicate and the
     * {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(Predicate)
     * @see #xor(Predicate)
     */
    @Nonnull
    default Predicate2<T> and(@Nonnull final Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    /**
     * Returns a composed {@link Predicate2} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code Predicate2} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code Predicate2} that will be logically-ORed with this one
     * @return A composed {@code Predicate2} that represents the short-circuiting logical OR of this predicate and the
     * {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(Predicate)
     * @see #xor(Predicate)
     */
    @Nonnull
    default Predicate2<T> or(@Nonnull final Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    /**
     * Returns a composed {@link Predicate2} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of
     * this {@code Predicate2} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code Predicate2} that will be logically-XORed with this one
     * @return A composed {@code Predicate2} that represents the short-circuiting logical XOR of this predicate and the
     * {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(Predicate)
     * @see #or(Predicate)
     */
    @Nonnull
    default Predicate2<T> xor(@Nonnull final Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) ^ other.test(t);
    }

    /**
     * Returns a reversed version of this predicate. This may be useful in recursive context.
     *
     * @return A reversed version of this predicate.
     */
    @Nonnull
    default Predicate2<T> reversed() {
        return (t) -> test(t);
    }

    /**
     * Returns a memoized (caching) version of this {@link Predicate2}. Whenever it is called, the mapping between the
     * input parameter and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code Predicate2}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default Predicate2<T> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<T, Boolean> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (Predicate2<T> & Memoized) (t) -> {
                final boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(t, this::test);
                }
                return returnValue;
            };
        }
    }

}
