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
package at.gridtec.lambda4j.functions;

import at.gridtec.lambda4j.functions.consumer.BooleanConsumer;
import at.gridtec.lambda4j.functions.consumer.ByteConsumer;
import at.gridtec.lambda4j.functions.consumer.CharConsumer;
import at.gridtec.lambda4j.functions.consumer.FloatConsumer;
import at.gridtec.lambda4j.functions.consumer.ShortConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.BiBooleanConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.BiByteConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.BiCharConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.BiDoubleConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.BiFloatConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.BiIntConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.BiLongConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.BiShortConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.obj.ObjBooleanConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.obj.ObjByteConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.obj.ObjCharConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.obj.ObjFloatConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.obj.ObjShortConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.TriBooleanConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.TriByteConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.TriCharConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.TriConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.TriDoubleConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.TriFloatConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.TriIntConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.TriLongConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.TriShortConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.obj.BiObjBooleanConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.obj.BiObjByteConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.obj.BiObjCharConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.obj.BiObjDoubleConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.obj.BiObjFloatConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.obj.BiObjIntConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.obj.BiObjLongConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.obj.BiObjShortConsumer;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;

/**
 * This class provides utility methods, which pertain to all {@link FunctionalInterface}s representing a consumer.
 */
@SuppressWarnings("unused")
public final class Consumers {

    /**
     * Calls the given {@link Consumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    public static <T> void call(@Nonnull final Consumer<? super T> consumer, T t) {
        Objects.requireNonNull(consumer);
        consumer.accept(t);
    }

    /**
     * Calls the given {@link BiConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    public static <T, U> void call(@Nonnull final BiConsumer<? super T, ? super U> consumer, T t, U u) {
        Objects.requireNonNull(consumer);
        consumer.accept(t, u);
    }

    /**
     * Calls the given {@link TriConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param <V> The type of the third argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param v The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, V> void call(@Nonnull final TriConsumer<? super T, ? super U, ? super V> consumer, T t, U u,
            V v) {
        TriConsumer.call(consumer, t, u, v);
    }

    /**
     * Calls the given {@link BooleanConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value The argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final BooleanConsumer consumer, boolean value) {
        BooleanConsumer.call(consumer, value);
    }

    /**
     * Calls the given {@link ByteConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value The argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final ByteConsumer consumer, byte value) {
        ByteConsumer.call(consumer, value);
    }

    /**
     * Calls the given {@link CharConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value The argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final CharConsumer consumer, char value) {
        CharConsumer.call(consumer, value);
    }

    /**
     * Calls the given {@link DoubleConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value The argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    public static void call(@Nonnull final DoubleConsumer consumer, double value) {
        Objects.requireNonNull(consumer);
        consumer.accept(value);
    }

    /**
     * Calls the given {@link FloatConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value The argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final FloatConsumer consumer, float value) {
        FloatConsumer.call(consumer, value);
    }

    /**
     * Calls the given {@link IntConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value The argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    public static void call(@Nonnull final IntConsumer consumer, int value) {
        Objects.requireNonNull(consumer);
        consumer.accept(value);
    }

    /**
     * Calls the given {@link LongConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value The argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    public static void call(@Nonnull final LongConsumer consumer, long value) {
        Objects.requireNonNull(consumer);
        consumer.accept(value);
    }

    /**
     * Calls the given {@link ShortConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value The argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final ShortConsumer consumer, short value) {
        ShortConsumer.call(consumer, value);
    }

    /**
     * Calls the given {@link BiBooleanConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final BiBooleanConsumer consumer, boolean value1, boolean value2) {
        BiBooleanConsumer.call(consumer, value1, value2);
    }

    /**
     * Calls the given {@link BiByteConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final BiByteConsumer consumer, byte value1, byte value2) {
        BiByteConsumer.call(consumer, value1, value2);
    }

    /**
     * Calls the given {@link BiCharConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final BiCharConsumer consumer, char value1, char value2) {
        BiCharConsumer.call(consumer, value1, value2);
    }

    /**
     * Calls the given {@link BiDoubleConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final BiDoubleConsumer consumer, double value1, double value2) {
        BiDoubleConsumer.call(consumer, value1, value2);
    }

    /**
     * Calls the given {@link BiFloatConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final BiFloatConsumer consumer, float value1, float value2) {
        BiFloatConsumer.call(consumer, value1, value2);
    }

    /**
     * Calls the given {@link BiIntConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final BiIntConsumer consumer, int value1, int value2) {
        BiIntConsumer.call(consumer, value1, value2);
    }

    /**
     * Calls the given {@link BiLongConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final BiLongConsumer consumer, long value1, long value2) {
        BiLongConsumer.call(consumer, value1, value2);
    }

    /**
     * Calls the given {@link BiShortConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final BiShortConsumer consumer, short value1, short value2) {
        BiShortConsumer.call(consumer, value1, value2);
    }

    /**
     * Calls the given {@link TriBooleanConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final TriBooleanConsumer consumer, boolean value1, boolean value2,
            boolean value3) {
        TriBooleanConsumer.call(consumer, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriByteConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final TriByteConsumer consumer, byte value1, byte value2, byte value3) {
        TriByteConsumer.call(consumer, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriCharConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final TriCharConsumer consumer, char value1, char value2, char value3) {
        TriCharConsumer.call(consumer, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriDoubleConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final TriDoubleConsumer consumer, double value1, double value2, double value3) {
        TriDoubleConsumer.call(consumer, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriFloatConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final TriFloatConsumer consumer, float value1, float value2, float value3) {
        TriFloatConsumer.call(consumer, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriIntConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final TriIntConsumer consumer, int value1, int value2, int value3) {
        TriIntConsumer.call(consumer, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriLongConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final TriLongConsumer consumer, long value1, long value2, long value3) {
        TriLongConsumer.call(consumer, value1, value2, value3);
    }

    /**
     * Calls the given {@link TriShortConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static void call(@Nonnull final TriShortConsumer consumer, short value1, short value2, short value3) {
        TriShortConsumer.call(consumer, value1, value2, value3);
    }

    /**
     * Calls the given {@link ObjBooleanConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> void call(@Nonnull final ObjBooleanConsumer<? super T> consumer, T t, boolean value) {
        ObjBooleanConsumer.call(consumer, t, value);
    }

    /**
     * Calls the given {@link ObjByteConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> void call(@Nonnull final ObjByteConsumer<? super T> consumer, T t, byte value) {
        ObjByteConsumer.call(consumer, t, value);
    }

    /**
     * Calls the given {@link ObjCharConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> void call(@Nonnull final ObjCharConsumer<? super T> consumer, T t, char value) {
        ObjCharConsumer.call(consumer, t, value);
    }

    /**
     * Calls the given {@link ObjDoubleConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    public static <T> void call(@Nonnull final ObjDoubleConsumer<? super T> consumer, T t, char value) {
        Objects.requireNonNull(consumer);
        consumer.accept(t, value);
    }

    /**
     * Calls the given {@link ObjFloatConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> void call(@Nonnull final ObjFloatConsumer<? super T> consumer, T t, float value) {
        ObjFloatConsumer.call(consumer, t, value);
    }

    /**
     * Calls the given {@link ObjIntConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    public static <T> void call(@Nonnull final ObjIntConsumer<? super T> consumer, T t, int value) {
        Objects.requireNonNull(consumer);
        consumer.accept(t, value);
    }

    /**
     * Calls the given {@link ObjLongConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     */
    public static <T> void call(@Nonnull final ObjLongConsumer<? super T> consumer, T t, long value) {
        Objects.requireNonNull(consumer);
        consumer.accept(t, value);
    }

    /**
     * Calls the given {@link ObjShortConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param value The second argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> void call(@Nonnull final ObjShortConsumer<? super T> consumer, T t, short value) {
        ObjShortConsumer.call(consumer, t, value);
    }

    /**
     * Calls the given {@link BiObjBooleanConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> void call(@Nonnull final BiObjBooleanConsumer<? super T, ? super U> consumer, T t, U u,
            boolean value) {
        BiObjBooleanConsumer.call(consumer, t, u, value);
    }

    /**
     * Calls the given {@link BiObjByteConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> void call(@Nonnull final BiObjByteConsumer<? super T, ? super U> consumer, T t, U u,
            byte value) {
        BiObjByteConsumer.call(consumer, t, u, value);
    }

    /**
     * Calls the given {@link BiObjCharConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> void call(@Nonnull final BiObjCharConsumer<? super T, ? super U> consumer, T t, U u,
            char value) {
        BiObjCharConsumer.call(consumer, t, u, value);
    }

    /**
     * Calls the given {@link BiObjDoubleConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> void call(@Nonnull final BiObjDoubleConsumer<? super T, ? super U> consumer, T t, U u,
            double value) {
        BiObjDoubleConsumer.call(consumer, t, u, value);
    }

    /**
     * Calls the given {@link BiObjFloatConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> void call(@Nonnull final BiObjFloatConsumer<? super T, ? super U> consumer, T t, U u,
            float value) {
        BiObjFloatConsumer.call(consumer, t, u, value);
    }

    /**
     * Calls the given {@link BiObjIntConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> void call(@Nonnull final BiObjIntConsumer<? super T, ? super U> consumer, T t, U u,
            int value) {
        BiObjIntConsumer.call(consumer, t, u, value);
    }

    /**
     * Calls the given {@link BiObjLongConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> void call(@Nonnull final BiObjLongConsumer<? super T, ? super U> consumer, T t, U u,
            long value) {
        BiObjLongConsumer.call(consumer, t, u, value);
    }

    /**
     * Calls the given {@link BiObjShortConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the operation to be consumed
     * @param <U> The type of the second argument to the operation to be consumed
     * @param consumer The consumer to be called
     * @param t The first argument to the operation to be consumed
     * @param u The second argument to the operation to be consumed
     * @param value The third argument to the operation to be consumed
     * @throws NullPointerException If the given consumer is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> void call(@Nonnull final BiObjShortConsumer<? super T, ? super U> consumer, T t, U u,
            short value) {
        BiObjShortConsumer.call(consumer, t, u, value);
    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T> void doNothing(T t) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting consumer
     * @param u The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T, U> void doNothing(T t, U u) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param <V> The type of the third argument
     * @param t The first argument to the resulting consumer
     * @param u The second argument to the resulting consumer
     * @param v The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T, U, V> void doNothing(T t, U u, V v) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value The argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(boolean value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value The argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(byte value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value The argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(char value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value The argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(double value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value The argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(float value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value The argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(int value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value The argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(long value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value The argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(short value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(boolean value1, boolean value2) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(byte value1, byte value2) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(char value1, char value2) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(double value1, double value2) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(float value1, float value2) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(int value1, int value2) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(long value1, long value2) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(short value1, short value2) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @param value3 The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(boolean value1, boolean value2, boolean value3) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @param value3 The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(byte value1, byte value2, byte value3) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @param value3 The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(char value1, char value2, char value3) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @param value3 The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(double value1, double value2, double value3) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @param value3 The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(float value1, float value2, float value3) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @param value3 The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(int value1, int value2, int value3) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @param value3 The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(long value1, long value2, long value3) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param value1 The first argument to the resulting consumer
     * @param value2 The second argument to the resulting consumer
     * @param value3 The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static void doNothing(short value1, short value2, short value3) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting consumer
     * @param value The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T> void doNothing(T t, boolean value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting consumer
     * @param value The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T> void doNothing(T t, byte value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting consumer
     * @param value The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T> void doNothing(T t, char value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting consumer
     * @param value The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T> void doNothing(T t, double value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting consumer
     * @param value The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T> void doNothing(T t, float value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting consumer
     * @param value The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T> void doNothing(T t, int value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting consumer
     * @param value The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T> void doNothing(T t, long value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting consumer
     * @param value The second argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T> void doNothing(T t, short value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting consumer
     * @param u The second argument to the resulting consumer
     * @param value The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T, U> void doNothing(T t, U u, boolean value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting consumer
     * @param u The second argument to the resulting consumer
     * @param value The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T, U> void doNothing(T t, U u, byte value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting consumer
     * @param u The second argument to the resulting consumer
     * @param value The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T, U> void doNothing(T t, U u, char value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting consumer
     * @param u The second argument to the resulting consumer
     * @param value The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T, U> void doNothing(T t, U u, double value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting consumer
     * @param u The second argument to the resulting consumer
     * @param value The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T, U> void doNothing(T t, U u, float value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting consumer
     * @param u The second argument to the resulting consumer
     * @param value The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T, U> void doNothing(T t, U u, int value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting consumer
     * @param u The second argument to the resulting consumer
     * @param value The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T, U> void doNothing(T t, U u, long value) {

    }

    /**
     * This method does nothing. It should be used wherever a consumer is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting consumer
     * @param u The second argument to the resulting consumer
     * @param value The third argument to the resulting consumer
     * @apiNote This method mainly exists to provide the ability to generate a consumer which does nothing. It should be
     * called using a method reference such as {@code Consumers::doNothing}.
     */
    public static <T, U> void doNothing(T t, U u, short value) {

    }
}
