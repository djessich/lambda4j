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
package at.gridtec.lambda4j.function.primitives.obj;

import at.gridtec.lambda4j.consumer.primitives.obj.BiObjBooleanConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjBooleanPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two object-valued and a {@code boolean}-valued argument, and produces a result.
 * This is the {@code (reference, reference, boolean)} specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, Object, boolean)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjBooleanFunction<T, U, R> {

    /**
     * Creates a {@link BiObjBooleanFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code BiObjBooleanFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, R> BiObjBooleanFunction<T, U, R> constant(R r) {
        return (t, u, value) -> r;
    }

    /**
     * Creates a {@link BiObjBooleanFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjBooleanFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjBooleanFunction<T, U, R> onlyFirst(@Nonnull final Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(t);
    }

    /**
     * Creates a {@link BiObjBooleanFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjBooleanFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjBooleanFunction<T, U, R> onlySecond(
            @Nonnull final Function<? super U, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(u);
    }

    /**
     * Creates a {@link BiObjBooleanFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link BooleanFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjBooleanFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@code BooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjBooleanFunction<T, U, R> onlyThird(@Nonnull final BooleanFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(value);
    }

    /**
     * Performs this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(T t, U u, boolean value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link BiObjBooleanFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code BiObjBooleanFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code byte}.
     * @see #andThen(Predicate)
     * @see #andThen(Function)
     */
    @Nonnull
    default <A, B> BiObjBooleanFunction<A, B, R> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final BooleanUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> apply(before1.apply(a), before2.apply(b), before3.applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code TriFunction} that first applies the {@code before} operations to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(Predicate)
     * @see #andThen(Function)
     */
    @Nonnull
    default <A, B, C> TriFunction<A, B, C, R> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final Predicate<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> apply(before1.apply(a), before2.apply(b), before3.test(c));
    }

    /**
     * Returns a composed {@link BiObjBooleanPredicate} that first applies this function to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the operation function.
     *
     * @param after The operation to apply after this function is applied
     * @return A composed {@code BiObjBooleanPredicate} that first applies this function to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is a primitive specialization of {@link TriFunction}. Therefore the returned
     * operation handles primitive types. In this case this is {@code byte}.
     * @see #compose(Function, Function, BooleanUnaryOperator)
     * @see #compose(Function, Function, Predicate)
     */
    @Nonnull
    default BiObjBooleanPredicate<T, U> andThen(@Nonnull final Predicate<? super R> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.test(apply(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned function is able to handle every type.
     * @see #compose(Function, Function, BooleanUnaryOperator)
     * @see #compose(Function, Function, Predicate)
     */
    @Nonnull
    default <S> BiObjBooleanFunction<T, U, S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(apply(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjBooleanConsumer} that fist applies this function to its input, and then consumes
     * the result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjBooleanConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjBooleanConsumer<T, U> consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(apply(t, u, value));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjBooleanFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjBooleanFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Boolean, R> boxed() {
        return this::apply;
    }
}
