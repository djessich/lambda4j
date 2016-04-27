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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by domin on 07.04.2016.
 */
public interface ThrowableHandlerDeclaration<H extends ThrowableHandlerDeclaration<H, X>, X extends Throwable> {

    @Nonnull
    <Y extends Throwable> H throwIf(@Nonnull final Class<Y> clazz) throws Y;

    @Nonnull
    <Y extends Throwable> H replace(@Nonnull final Supplier<? extends Y> factory) throws Y;

    @Nonnull
    <Y extends Throwable> H replace(@Nonnull final Function<? super String, ? extends Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y;

    @Nonnull
    <Y extends Throwable> H replaceIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull final Supplier<? extends Y> factory) throws Y;

    @Nonnull
    <Y extends Throwable> H replaceIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull final Function<? super String, ? extends Y> factory, @Nonnull String newMessage,
            @Nullable Object... messageParams) throws Y;

    @Nonnull
    <Y extends Throwable> H wrap(@Nonnull final Function<? super X, ? extends Y> factory) throws Y;

    @Nonnull
    <Y extends Throwable> H wrap(@Nonnull BiFunction<? super String, ? super X, ? extends Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y;

    @Nonnull
    <Y extends Throwable> H wrapIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull Function<? super X, ? extends Y> factory) throws Y;

    @Nonnull
    <Y extends Throwable> H wrapIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull BiFunction<? super String, ? super X, ? extends Y> factory, @Nonnull String newMessage,
            @Nullable Object... messageParams) throws Y;

    @Nonnull
    <Y extends Throwable> H addSuppressed(@Nonnull final Supplier<? extends Y> factory);

    @Nonnull
    <Y extends Throwable> H addSuppressed(@Nonnull final Function<? super String, ? extends Y> factory,
            @Nonnull String message, @Nullable Object... messageParams);

    @Nonnull
    <Y extends Throwable> H addSuppressedIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull final Supplier<? extends Y> factory);

    @Nonnull
    <Y extends Throwable> H addSuppressedIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull final Function<? super String, ? extends Y> factory, @Nonnull String message,
            @Nullable Object... messageParams);

    @Nonnull
    <Y extends Throwable> H addAsSuppressed(@Nonnull final Supplier<? extends Y> factory) throws Y;

    @Nonnull
    <Y extends Throwable> H addAsSuppressed(@Nonnull final Function<? super String, ? extends Y> factory,
            @Nonnull String message, @Nullable Object... messageParams) throws Y;

    @Nonnull
    <Y extends Throwable> H addAsSuppressedIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull final Supplier<? extends Y> factory) throws Y;

    @Nonnull
    <Y extends Throwable> H addAsSuppressedIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull final Function<? super String, ? extends Y> factory, @Nonnull String message,
            @Nullable Object... messageParams) throws Y;
}
