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

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

/**
 * Represents a function that accepts an object-valued and a {@code double}-valued argument, and produces a {@code
 * double}-valued result. This is the {@code (reference, double)}, {@code double}-producing primitive specialization for
 * {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsDouble(Object, double)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToDoubleObjDoubleFunction<T> {

    /**
     * Creates a {@link ToDoubleObjDoubleFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToDoubleObjDoubleFunction} which always returns a given value.
     */
    static <T> ToDoubleObjDoubleFunction<T> constant(double ret) {
        return (t, value) -> ret;
    }

    /**
     * Creates a {@link ToDoubleObjDoubleFunction} which uses the first parameter of this one as argument for the given
     * {@link ToDoubleFunction}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToDoubleObjDoubleFunction} which uses the first parameter of this one as argument for
     * the given {@code ToDoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ToDoubleObjDoubleFunction<T> onlyFirst(final ToDoubleFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsDouble(t);
    }

    /**
     * Creates a {@link ToDoubleObjDoubleFunction} which uses the second parameter of this one as argument for the given
     * {@link DoubleUnaryOperator}.
     *
     * @param <T> The type of argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToDoubleObjDoubleFunction} which uses the second parameter of this one as argument for
     * the given {@code DoubleUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ToDoubleObjDoubleFunction<T> onlySecond(final DoubleUnaryOperator function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsDouble(value);
    }

    /**
     * Applies this {@link ToDoubleObjDoubleFunction} to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    double applyAsDouble(T t, double value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToDoubleObjDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code DoubleUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToDoubleObjDoubleFunction} that first applies the {@code before} functions to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(DoubleUnaryOperator)
     * @see #andThen(DoubleFunction)
     */
    default <U> ToDoubleObjDoubleFunction<U> compose(final Function<? super U, ? extends T> before1,
            final DoubleUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, value) -> applyAsDouble(before1.apply(u), before2.applyAsDouble(value));
    }

    /**
     * Returns a composed {@link ToDoubleBiFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToDoubleFunction} to apply before this operation is applied
     * @return A composed {@code ToDoubleBiFunction} that applies the given {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(DoubleUnaryOperator)
     * @see #andThen(DoubleFunction)
     */
    default <U, V> ToDoubleBiFunction<U, V> compose(final Function<? super U, ? extends T> before1,
            final ToDoubleFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> applyAsDouble(before1.apply(u), before2.applyAsDouble(v));
    }

    /**
     * Returns a composed {@link DoubleBinaryOperator} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToDoubleFunction} to apply after this operation is applied
     * @return A composed {@code DoubleBinaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, DoubleUnaryOperator)
     * @see #compose(Function, ToDoubleFunction)
     */
    default ToDoubleObjDoubleFunction<T> andThen(final DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsDouble(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjDoubleFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code DoubleFunction} to apply after this operation is applied
     * @return A composed {@code ObjDoubleFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, DoubleUnaryOperator)
     * @see #compose(Function, ToDoubleFunction)
     */
    default <S> ObjDoubleFunction<T, S> andThen(final DoubleFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link ObjDoubleConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code ObjDoubleConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ObjDoubleConsumer<T> consume(DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsDouble(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjDoubleFunction}. Thereby the primitive
     * input argument for this operation is autoboxed. This method is just convenience to provide the ability to use
     * this {@code ObjDoubleFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjDoubleFunction}.
     */
    default BiFunction<T, Double, Double> boxed() {
        return this::applyAsDouble;
    }
}
