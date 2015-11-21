/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */
package at.gridtec.lambda4j;

import at.gridtec.lambda4j.predicates.TriPredicate;
import at.gridtec.lambda4j.predicates.primitives.BytePredicate;
import at.gridtec.lambda4j.predicates.primitives.CharPredicate;
import at.gridtec.lambda4j.predicates.primitives.FloatPredicate;
import at.gridtec.lambda4j.predicates.primitives.ShortPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.ByteBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.CharBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.DoubleBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.FloatBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.IntBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.LongBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.ShortBiPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjBooleanPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjBytePredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjCharPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjDoublePredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjFloatPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjIntPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjLongPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.BiObjShortPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjBooleanPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjBytePredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjCharPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjDoublePredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjFloatPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjIntPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjLongPredicate;
import at.gridtec.lambda4j.predicates.primitives.obj.ObjShortPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.ByteTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.CharTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.DoubleTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.FloatTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.IntTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.LongTriPredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.ShortTriPredicate;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This class provides utility methods, which pertain to all {@link FunctionalInterface}s representing a predicate.
 */
@SuppressWarnings("unused")
public final class Predicates {

    /**
     * Private Constructor to prevent instantiation.
     */
    private Predicates() {

    }

    /**
     * Calls the given {@link Predicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @return The result from the given {@code Predicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    public static <T> boolean call(@Nonnull final Predicate<? super T> predicate, T t) {
        Objects.requireNonNull(predicate);
        return predicate.test(t);
    }

    /**
     * Calls the given {@link BiPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @return The result from the given {@code BiPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    public static <T, U> boolean call(@Nonnull final BiPredicate<? super T, ? super U> predicate, T t, U u) {
        Objects.requireNonNull(predicate);
        return predicate.test(t, u);
    }

    /**
     * Calls the given {@link TriPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param v The third argument to the predicate
     * @return The result from the given {@code TriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U, V> boolean call(@Nonnull final TriPredicate<? super T, ? super U, ? super V> predicate, T t,
            U u, V v) {
        return TriPredicate.call(predicate, t, u, v);
    }

    /**
     * Calls the given {@link BytePredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code BytePredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final BytePredicate predicate, byte value) {
        return BytePredicate.call(predicate, value);
    }

    /**
     * Calls the given {@link CharPredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code CharPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final CharPredicate predicate, char value) {
        return CharPredicate.call(predicate, value);
    }

    /**
     * Calls the given {@link DoublePredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code DoublePredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    public static boolean call(@Nonnull final DoublePredicate predicate, double value) {
        Objects.requireNonNull(predicate);
        return predicate.test(value);
    }

    /**
     * Calls the given {@link FloatPredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code FloatPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final FloatPredicate predicate, float value) {
        return FloatPredicate.call(predicate, value);
    }

    /**
     * Calls the given {@link IntPredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code IntPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    public static boolean call(@Nonnull final IntPredicate predicate, int value) {
        Objects.requireNonNull(predicate);
        return predicate.test(value);
    }

    /**
     * Calls the given {@link LongPredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code LongPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    public static boolean call(@Nonnull final LongPredicate predicate, long value) {
        Objects.requireNonNull(predicate);
        return predicate.test(value);
    }

    /**
     * Calls the given {@link ShortPredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code ShortPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final ShortPredicate predicate, short value) {
        return ShortPredicate.call(predicate, value);
    }

    /**
     * Calls the given {@link ByteBiPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code ByteBiPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final ByteBiPredicate predicate, byte value1, byte value2) {
        return ByteBiPredicate.call(predicate, value1, value2);
    }

    /**
     * Calls the given {@link CharBiPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code CharBiPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final CharBiPredicate predicate, char value1, char value2) {
        return CharBiPredicate.call(predicate, value1, value2);
    }

    /**
     * Calls the given {@link DoubleBiPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code DoubleBiPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final DoubleBiPredicate predicate, double value1, double value2) {
        return DoubleBiPredicate.call(predicate, value1, value2);
    }

    /**
     * Calls the given {@link FloatBiPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code FloatBiPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final FloatBiPredicate predicate, float value1, float value2) {
        return FloatBiPredicate.call(predicate, value1, value2);
    }

    /**
     * Calls the given {@link IntBiPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code IntBiPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final IntBiPredicate predicate, int value1, int value2) {
        return IntBiPredicate.call(predicate, value1, value2);
    }

    /**
     * Calls the given {@link LongBiPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code LongBiPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final LongBiPredicate predicate, long value1, long value2) {
        return LongBiPredicate.call(predicate, value1, value2);
    }

    /**
     * Calls the given {@link ShortBiPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code ShortBiPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final ShortBiPredicate predicate, short value1, short value2) {
        return ShortBiPredicate.call(predicate, value1, value2);
    }

    /**
     * Calls the given {@link ByteTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code ByteTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final ByteTriPredicate predicate, byte value1, byte value2, byte value3) {
        return ByteTriPredicate.call(predicate, value1, value2, value3);
    }

    /**
     * Calls the given {@link CharTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code CharTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final CharTriPredicate predicate, char value1, char value2, char value3) {
        return CharTriPredicate.call(predicate, value1, value2, value3);
    }

    /**
     * Calls the given {@link DoubleTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code DoubleTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final DoubleTriPredicate predicate, double value1, double value2,
            double value3) {
        return DoubleTriPredicate.call(predicate, value1, value2, value3);
    }

    /**
     * Calls the given {@link FloatTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code FloatTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final FloatTriPredicate predicate, float value1, float value2, float value3) {
        return FloatTriPredicate.call(predicate, value1, value2, value3);
    }

    /**
     * Calls the given {@link IntTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code IntTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final IntTriPredicate predicate, int value1, int value2, int value3) {
        return IntTriPredicate.call(predicate, value1, value2, value3);
    }

    /**
     * Calls the given {@link LongTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code LongTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final LongTriPredicate predicate, long value1, long value2, long value3) {
        return LongTriPredicate.call(predicate, value1, value2, value3);
    }

    /**
     * Calls the given {@link ShortTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code ShortTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static boolean call(@Nonnull final ShortTriPredicate predicate, short value1, short value2, short value3) {
        return ShortTriPredicate.call(predicate, value1, value2, value3);
    }

    /**
     * Calls the given {@link ObjBooleanPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The result from the given {@code ObjBooleanPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> boolean call(@Nonnull final ObjBooleanPredicate<? super T> predicate, T t, boolean value) {
        return ObjBooleanPredicate.call(predicate, t, value);
    }

    /**
     * Calls the given {@link ObjBytePredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The result from the given {@code ObjBytePredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> boolean call(@Nonnull final ObjBytePredicate<? super T> predicate, T t, byte value) {
        return ObjBytePredicate.call(predicate, t, value);
    }

    /**
     * Calls the given {@link ObjCharPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The result from the given {@code ObjCharPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> boolean call(@Nonnull final ObjCharPredicate<? super T> predicate, T t, char value) {
        return ObjCharPredicate.call(predicate, t, value);
    }

    /**
     * Calls the given {@link ObjDoublePredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The result from the given {@code ObjDoublePredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> boolean call(@Nonnull final ObjDoublePredicate<? super T> predicate, T t, double value) {
        return ObjDoublePredicate.call(predicate, t, value);
    }

    /**
     * Calls the given {@link ObjFloatPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The result from the given {@code ObjFloatPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> boolean call(@Nonnull final ObjFloatPredicate<? super T> predicate, T t, float value) {
        return ObjFloatPredicate.call(predicate, t, value);
    }

    /**
     * Calls the given {@link ObjIntPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The result from the given {@code ObjIntPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> boolean call(@Nonnull final ObjIntPredicate<? super T> predicate, T t, int value) {
        return ObjIntPredicate.call(predicate, t, value);
    }

    /**
     * Calls the given {@link ObjLongPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The result from the given {@code ObjLongPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> boolean call(@Nonnull final ObjLongPredicate<? super T> predicate, T t, long value) {
        return ObjLongPredicate.call(predicate, t, value);
    }

    /**
     * Calls the given {@link ObjShortPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return The result from the given {@code ObjShortPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T> boolean call(@Nonnull final ObjShortPredicate<? super T> predicate, T t, short value) {
        return ObjShortPredicate.call(predicate, t, value);
    }

    /**
     * Calls the given {@link BiObjBooleanPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code BiObjBooleanPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> boolean call(@Nonnull final BiObjBooleanPredicate<? super T, ? super U> predicate, T t, U u,
            boolean value) {
        return BiObjBooleanPredicate.call(predicate, t, u, value);
    }

    /**
     * Calls the given {@link BiObjBytePredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code BiObjBytePredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> boolean call(@Nonnull final BiObjBytePredicate<? super T, ? super U> predicate, T t, U u,
            byte value) {
        return BiObjBytePredicate.call(predicate, t, u, value);
    }

    /**
     * Calls the given {@link BiObjCharPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code BiObjCharPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> boolean call(@Nonnull final BiObjCharPredicate<? super T, ? super U> predicate, T t, U u,
            char value) {
        return BiObjCharPredicate.call(predicate, t, u, value);
    }

    /**
     * Calls the given {@link BiObjDoublePredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code BiObjDoublePredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> boolean call(@Nonnull final BiObjDoublePredicate<? super T, ? super U> predicate, T t, U u,
            double value) {
        return BiObjDoublePredicate.call(predicate, t, u, value);
    }

    /**
     * Calls the given {@link BiObjFloatPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code BiObjFloatPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> boolean call(@Nonnull final BiObjFloatPredicate<? super T, ? super U> predicate, T t, U u,
            float value) {
        return BiObjFloatPredicate.call(predicate, t, u, value);
    }

    /**
     * Calls the given {@link BiObjIntPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code BiObjIntPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> boolean call(@Nonnull final BiObjIntPredicate<? super T, ? super U> predicate, T t, U u,
            int value) {
        return BiObjIntPredicate.call(predicate, t, u, value);
    }

    /**
     * Calls the given {@link BiObjLongPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code BiObjLongPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> boolean call(@Nonnull final BiObjLongPredicate<? super T, ? super U> predicate, T t, U u,
            long value) {
        return BiObjLongPredicate.call(predicate, t, u, value);
    }

    /**
     * Calls the given {@link BiObjShortPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code BiObjShortPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @apiNote This function mainly exists as a convenience helper. Each {@link FunctionalInterface} of this library
     * provides an identical public static {@code call()} method to this one, depending on its type.
     */
    public static <T, U> boolean call(@Nonnull final BiObjShortPredicate<? super T, ? super U> predicate, T t, U u,
            short value) {
        return BiObjShortPredicate.call(predicate, t, u, value);
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the argument
     * @param t The argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U, V> boolean alwaysTrue(T t) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U, V> boolean alwaysTrue(T t, U u) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param <V> The type of the third argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param v The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U, V> boolean alwaysTrue(T t, U u, V v) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(byte value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(char value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(double value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(float value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(int value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(long value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(short value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(byte value1, byte value2) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(char value1, char value2) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(double value1, double value2) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(float value1, float value2) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(int value1, int value2) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(long value1, long value2) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(short value1, short value2) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(byte value1, byte value2, byte value3) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(char value1, char value2, char value3) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(double value1, double value2, double value3) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(float value1, float value2, float value3) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(int value1, int value2, int value3) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(long value1, long value2, long value3) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static boolean alwaysTrue(short value1, short value2, short value3) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T> boolean alwaysTrue(T t, boolean value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T> boolean alwaysTrue(T t, byte value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T> boolean alwaysTrue(T t, char value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T> boolean alwaysTrue(T t, double value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T> boolean alwaysTrue(T t, float value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T> boolean alwaysTrue(T t, int value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T> boolean alwaysTrue(T t, long value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T> boolean alwaysTrue(T t, short value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U> boolean alwaysTrue(T t, U u, boolean value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U> boolean alwaysTrue(T t, U u, byte value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U> boolean alwaysTrue(T t, U u, char value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U> boolean alwaysTrue(T t, U u, double value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U> boolean alwaysTrue(T t, U u, float value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U> boolean alwaysTrue(T t, U u, int value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U> boolean alwaysTrue(T t, U u, long value) {
        return true;
    }

    /**
     * Always returns {@code true}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code true}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * true}. It should be called using a method reference such as {@code Predicates::alwaysTrue}.
     */
    public static <T, U> boolean alwaysTrue(T t, U u, short value) {
        return true;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the argument
     * @param t The argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U, V> boolean alwaysFalse(T t) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U, V> boolean alwaysFalse(T t, U u) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param <V> The type of the third argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param v The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U, V> boolean alwaysFalse(T t, U u, V v) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(byte value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(char value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(double value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(float value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(int value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(long value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value The argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(short value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(byte value1, byte value2) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(char value1, char value2) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(double value1, double value2) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(float value1, float value2) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(int value1, int value2) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(long value1, long value2) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(short value1, short value2) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(byte value1, byte value2, byte value3) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(char value1, char value2, char value3) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(double value1, double value2, double value3) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(float value1, float value2, float value3) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(int value1, int value2, int value3) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(long value1, long value2, long value3) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param value1 The first argument to the resulting predicate
     * @param value2 The second argument to the resulting predicate
     * @param value3 The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static boolean alwaysFalse(short value1, short value2, short value3) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T> boolean alwaysFalse(T t, boolean value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T> boolean alwaysFalse(T t, byte value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T> boolean alwaysFalse(T t, char value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T> boolean alwaysFalse(T t, double value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T> boolean alwaysFalse(T t, float value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T> boolean alwaysFalse(T t, int value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T> boolean alwaysFalse(T t, long value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param t The first argument to the resulting predicate
     * @param value The second argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T> boolean alwaysFalse(T t, short value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U> boolean alwaysFalse(T t, U u, boolean value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U> boolean alwaysFalse(T t, U u, byte value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U> boolean alwaysFalse(T t, U u, char value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U> boolean alwaysFalse(T t, U u, double value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U> boolean alwaysFalse(T t, U u, float value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U> boolean alwaysFalse(T t, U u, int value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U> boolean alwaysFalse(T t, U u, long value) {
        return false;
    }

    /**
     * Always returns {@code false}. It should be used wherever a predicate is necessary.
     *
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param t The first argument to the resulting predicate
     * @param u The second argument to the resulting predicate
     * @param value The third argument to the resulting predicate
     * @return Always returns {@code false}.
     * @apiNote This method mainly exists to provide the ability to generate a predicate which always returns {@code
     * false}. It should be called using a method reference such as {@code Predicates::alwaysFalse}.
     */
    public static <T, U> boolean alwaysFalse(T t, U u, short value) {
        return false;
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if the object reference being tested is {@code null}.
     *
     * @param <T> The type of the argument to the predicate
     * @return A {@code Predicate} that evaluates to {@code true} if the object reference being tested is {@code null}.
     */
    public static <T> Predicate<T> isNull() {
        return Objects::isNull;
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if the object reference being tested is not {@code
     * null}.
     *
     * @param <T> The type of the argument to the predicate
     * @return A {@code Predicate} that evaluates to {@code true} if the object reference being tested is not {@code
     * null}.
     */
    public static <T> Predicate<T> nonNull() {
        return Objects::nonNull;
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if both of its components evaluate to {@code true}.
     * The components are evaluated in order, and evaluation will be short-circuited as soon as a {@code false}
     * predicate is found.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of the
     * composed {@code Predicate} throws an exception, its components will not be further evaluated.
     *
     * @param <T> The type of the argument to the predicate
     * @param first The first predicate to be logically-ANDed
     * @param second The second predicate to be logically-ANDed
     * @return A {@code Predicate} that evaluates to {@code true} if each of its components evaluates to {@code true}.
     * @throws NullPointerException If one of the given arguments is {@code null}
     */
    @Nonnull
    public static <T> Predicate<T> and(Predicate<? super T> first, Predicate<? super T> second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return new AndPredicate<>(Arrays.<Predicate<? super T>>asList(first, second));
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if each of its components evaluates to {@code true}.
     * The components are evaluated in order, and evaluation will be short-circuited as soon as a {@code false}
     * predicate is found. It defensively copies the array passed in, so future changes to it won't alter the behavior
     * of this predicate. If {@code predicates} is empty, the returned predicate will always evaluate to {@code true}.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of the
     * composed {@code Predicate} throws an exception, its components will not be further evaluated.
     *
     * @param <T> The type of the argument to the predicate
     * @param predicates The predicates to be logically-ANDed together
     * @return A {@code Predicate} that evaluates to {@code true} if each of its components evaluates to {@code true}.
     * @throws NullPointerException If the given argument is {@code null}
     * @implNote This implementation filters out {@code null} values from given array.
     */
    @Nonnull
    @SafeVarargs
    public static <T> Predicate<T> and(@Nonnull final Predicate<? super T>... predicates) {
        Objects.requireNonNull(predicates);
        final List<? extends Predicate<? super T>> list = Arrays.asList(predicates)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new AndPredicate<>(list);
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if each of its components evaluates to {@code true}.
     * The components are evaluated in order, and evaluation will be short-circuited as soon as a {@code false}
     * predicate is found. It defensively copies the {@link Iterable} passed in, so future changes to it won't alter the
     * behavior of this predicate. If {@code predicates} is empty, the returned predicate will always evaluate to {@code
     * true}.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of the
     * composed {@code Predicate} throws an exception, its components will not be further evaluated.
     *
     * @param <T> The type of the argument to the predicate
     * @param predicates The predicates to be logically-ANDed together
     * @return A {@code Predicate} that evaluates to {@code true} if each of its components evaluates to {@code true}.
     * @throws NullPointerException If the given argument is {@code null}
     * @implNote This implementation filters out {@code null} values from given {@code Iterable}.
     */
    @Nonnull
    public static <T> Predicate<T> and(@Nonnull final Iterable<? extends Predicate<? super T>> predicates) {
        Objects.requireNonNull(predicates);
        final List<? extends Predicate<? super T>> list = StreamSupport.stream(predicates.spliterator(), false)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new AndPredicate<>(list);
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if either of its components evaluates to {@code true}.
     * The components are evaluated in order, and evaluation will be short-circuited as soon as a {@code true} predicate
     * is found.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of the
     * composed {@code Predicate} throws an exception, its components will not be further evaluated.
     *
     * @param <T> The type of the argument to the predicate
     * @param first The first predicate to be logically-ORed
     * @param second The second predicate to be logically-ORed
     * @return A {@code Predicate} that evaluates to {@code true} if each of its components evaluates to {@code true}.
     * @throws NullPointerException If one of the given arguments is {@code null}
     */
    @Nonnull
    public static <T> Predicate<T> or(Predicate<? super T> first, Predicate<? super T> second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return new OrPredicate<>(Arrays.<Predicate<? super T>>asList(first, second));
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if any one of its components evaluates to {@code
     * true}. The components are evaluated in order, and evaluation will be short-circuited as soon as a {@code true}
     * predicate is found. It defensively copies the iterable passed in, so future changes to it won't alter the
     * behavior of this predicate. If {@code predicates} is empty, the returned predicate will always evaluate to {@code
     * false}.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of the
     * composed {@code Predicate} throws an exception, its components will not be further evaluated.
     *
     * @param <T> The type of the argument to the predicate
     * @param predicates The predicates to be logically-ORed together
     * @return A {@code Predicate} that evaluates to {@code true} if each of its components evaluates to {@code true}.
     * @throws NullPointerException If the given argument is {@code null}
     * @implNote This implementation filters out {@code null} values from given array.
     */
    @Nonnull
    @SafeVarargs
    public static <T> Predicate<T> or(Predicate<? super T>... predicates) {
        Objects.requireNonNull(predicates);
        final List<? extends Predicate<? super T>> list = Arrays.asList(predicates)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new OrPredicate<>(list);
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if any one of its components evaluates to {@code
     * true}. The components are evaluated in order, and evaluation will be "short-circuited" as soon as a {@code true}
     * predicate is found. It defensively copies the array passed in, so future changes to it won't alter the behavior
     * of this predicate. If {@code predicates} is empty, the returned predicate will always evaluate to {@code false}.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of the
     * composed {@code Predicate} throws an exception, its components will not be further evaluated.
     *
     * @param <T> The type of the argument to the predicate
     * @param predicates The predicates to be logically-ORed together
     * @return A {@code Predicate} that evaluates to {@code true} if each of its components evaluates to {@code true}.
     * @throws NullPointerException If the given argument is {@code null}
     * @implNote This implementation filters out {@code null} values from given {@code Iterable}.
     */
    @Nonnull
    public static <T> Predicate<T> or(Iterable<? extends Predicate<? super T>> predicates) {
        Objects.requireNonNull(predicates);
        final List<? extends Predicate<? super T>> list = StreamSupport.stream(predicates.spliterator(), false)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new OrPredicate<>(list);
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if the object being tested is an instance of the given
     * class. If the object being tested is {@code null} this predicate evaluates to {@code false}.
     *
     * @param clazz The clazz to be evaluated if instance of the object being tested
     * @return A {@link Predicate} that evaluates to {@code true} if the object being tested is an instance of the given
     * class
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static Predicate<Object> instanceOf(@Nonnull final Class<?> clazz) {
        Objects.requireNonNull(clazz);
        return clazz::isInstance;
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if the class being tested is assignable from the given
     * class.
     *
     * @param clazz The class to be evaluated if assignable from class being tested
     * @return A {@code Predicate} that evaluates to {@code true} if the class being tested is assignable from the given
     * class.
     * @throws NullPointerException If the given argument is {@code null}
     * @implSpec The returned {@code Predicate} does not allow {@code null} inputs. If given null, a {@link
     * NullPointerException} will be thrown. See {@link Class#isAssignableFrom(Class)} for more information.
     */
    @Nonnull
    public static Predicate<Class<?>> assignableFrom(@Nonnull final Class<?> clazz) {
        Objects.requireNonNull(clazz);
        return clazz::isAssignableFrom;
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if the object reference being tested is a member of
     * the given {@link Collection}. It does not defensively copy the collection passed in, so future changes to it will
     * alter the behavior of the predicate.
     * <p>
     * This method can technically accept any {@code Collection<?>}, but using a typed collection helps prevent bugs.
     * This approach doesn't block any potential users since it is always possible to use {@code
     * Predicates.<Object>in()}.
     *
     * @param <T> The type of the argument to the predicate
     * @param target The collection that may contain the predicates input
     * @throws NullPointerException If the given argument is {@code null}
     * @implNote This implementation does not filter out {@code null} values from given {@code Collection}.
     */
    public static <T> Predicate<T> contains(@Nonnull final Collection<? extends T> target) {
        Objects.requireNonNull(target);
        return t -> {
            try {
                return target.contains(t);
            } catch (ClassCastException | NullPointerException e) {
                return false;
            }
        };
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if the {@code CharSequence} being tested contains any
     * match for the given regular expression pattern. The test used is equivalent to {@code
     * Pattern.compile(pattern).matcher(arg).find()}.
     *
     * @param pattern The regular expression pattern to be matched against the {@code CharSequence} being tested
     * @return A {@code Predicate} that evaluates to {@code true} if the {@code CharSequence} being tested contains any
     * match for the given regular expression pattern.
     * @throws NullPointerException If the given argument is {@code null}
     * @throws PatternSyntaxException If the pattern is invalid
     */
    @Nonnull
    public static Predicate<CharSequence> find(@Nonnull final String pattern) {
        Objects.requireNonNull(pattern);
        return Predicates.find(Pattern.compile(pattern));
    }

    /**
     * Returns a {@link Predicate} that evaluates to {@code true} if the {@code CharSequence} being tested contains any
     * match for the given regular expression pattern. The test used is equivalent to {@code
     * pattern.matcher(arg).find()}.
     *
     * @param pattern The regular expression pattern to be matched against the {@code CharSequence} being tested
     * @return A {@link Predicate} that evaluates to {@code true} if the {@code CharSequence} being tested contains any
     * match for the given regular expression pattern.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    public static Predicate<CharSequence> find(@Nonnull final Pattern pattern) {
        Objects.requireNonNull(pattern);
        return seq -> pattern.matcher(seq).find();
    }

    /**
     * A {@code Predicate} which logically ANDs its components together.
     *
     * @param <T> The type of the argument to the predicate
     */
    @SuppressWarnings("ForLoopReplaceableByForEach")
    private static class AndPredicate<T> implements Predicate<T> {
        private final List<? extends Predicate<? super T>> components;

        private AndPredicate(List<? extends Predicate<? super T>> components) {
            this.components = components;
        }

        @Override
        public boolean test(T t) {
            for (int i = 0; i < components.size(); i++) {
                if (!components.get(i).test(t)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * A {@code Predicate} which logically ORs its components together.
     *
     * @param <T> The type of the argument to the predicate
     * @see #or(Predicate, Predicate)
     * @see #or(Predicate[])
     * @see #or(Iterable)
     */
    @SuppressWarnings("ForLoopReplaceableByForEach")
    private static class OrPredicate<T> implements Predicate<T> {
        private final List<? extends Predicate<? super T>> components;

        private OrPredicate(List<? extends Predicate<? super T>> components) {
            this.components = components;
        }

        @Override
        public boolean test(T t) {
            for (int i = 0; i < components.size(); i++) {
                if (components.get(i).test(t)) {
                    return true;
                }
            }
            return false;
        }
    }

}
