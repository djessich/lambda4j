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

import at.gridtec.lambda4j.consumer.primitives.obj.BiObjDoubleConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToDoubleTriFunction;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

/**
 * Represents a function that accepts two object-valued and a {@code double}-valued argument, and produces a {@code
 * double}-valued result. This is the {@code (reference, reference, double)}, {@code double}-producing primitive
 * specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(Object, Object, double)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToDoubleBiObjDoubleFunction<T, U> {

    /**
     * Creates a {@link ToDoubleBiObjDoubleFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToDoubleBiObjDoubleFunction} which always returns a given value.
     */
    static <T, U> ToDoubleBiObjDoubleFunction<T, U> constant(double ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Creates a {@link ToDoubleBiObjDoubleFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@link ToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToDoubleBiObjDoubleFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToDoubleBiObjDoubleFunction<T, U> onlyFirst(final ToDoubleFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsDouble(t);
    }

    /**
     * Creates a {@link ToDoubleBiObjDoubleFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ToDoubleFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToDoubleBiObjDoubleFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToDoubleBiObjDoubleFunction<T, U> onlySecond(final ToDoubleFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsDouble(u);
    }

    /**
     * Creates a {@link ToDoubleBiObjDoubleFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@link DoubleUnaryOperator}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToDoubleBiObjDoubleFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code DoubleUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U> ToDoubleBiObjDoubleFunction<T, U> onlyThird(final DoubleUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsDouble(value);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    double applyAsDouble(T t, U u, double value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToDoubleBiObjDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code Function} to apply before this operation is applied
     * @param before3 The third {@code DoubleUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToDoubleBiObjDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleUnaryOperator)
     * @see #andThen(DoubleFunction)
     */
    default <A, B> ToDoubleBiObjDoubleFunction<A, B> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final DoubleUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> applyAsDouble(before1.apply(a), before2.apply(b), before3.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link ToDoubleTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToDoubleFunction} to apply before this operation is applied
     * @return A composed {@code ToDoubleTriFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(DoubleUnaryOperator)
     * @see #andThen(DoubleFunction)
     */
    default <A, B, C> ToDoubleTriFunction<A, B, C> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final ToDoubleFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsDouble(before1.apply(a), before2.apply(b), before3.applyAsDouble(c));
    }

    /**
     * Returns a composed {@link ToDoubleBiObjDoubleFunction} that first applies this operation to its input, and then
     * applies the {@code after} operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param after The {@code DoubleUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToDoubleBiObjDoubleFunction} that first applies this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, DoubleUnaryOperator)
     * @see #compose(Function, Function, ToDoubleFunction)
     */
    default ToDoubleBiObjDoubleFunction<T, U> andThen(final DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsDouble(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoubleFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code DoubleFunction} to apply after this operation is applied
     * @return A composed {@code BiObjDoubleFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, DoubleUnaryOperator)
     * @see #compose(Function, Function, ToDoubleFunction)
     */
    default <S> BiObjDoubleFunction<T, U, S> andThen(final DoubleFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoubleConsumer} that fist applies this operation to its input, and then consumes
     * the result using the given {@code DoubleConsumer}. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The {@code DoubleConsumer} which consumes the result from this operation
     * @return A composed {@code BiObjDoubleConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code DoubleConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BiObjDoubleConsumer<T, U> consume(DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsDouble(t, u, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjDoubleFunction}. Thereby the primitive
     * input argument for this operation is autoboxed. This method is just convenience to provide the ability to use
     * this {@code ObjDoubleFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjDoubleFunction}.
     */
    default TriFunction<T, U, Double, Double> boxed() {
        return this::applyAsDouble;
    }
}
