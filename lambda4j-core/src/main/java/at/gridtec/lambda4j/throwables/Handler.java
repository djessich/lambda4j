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
package at.gridtec.lambda4j.throwables;

import at.gridtec.lambda4j.meta.Selfable;

import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface Handler<T extends Throwable, S extends Handler<T, S>> extends Selfable<S> {

    static <T extends Throwable> Handler.The<T> of(@Nonnull final T t) {
        return new The<T>(t);
    }

    /**
     * Wraps a {@link Throwable} to another {@code Throwable} using the given {@link Function}. Thereby the exception is
     * rethrown after the given {@code wrapper} is applied.
     *
     * @param <X> The type of the wrapped throwable
     * @param wrapper The wrapper function to be used to wrap a throwable
     * @throws NullPointerException If the given argument is {@code null}
     * @throws X The throwable to be always thrown after wrapping function is applied
     */
    <X extends Throwable> void wrap(@Nonnull final Function<? super Throwable, ? extends X> wrapper) throws X;

    //    <X extends Throwable> void wrap(@Nonnull final BiFunction<? super Throwable, ? super String, ? extends X> wrapper,
    //            @Nonnull final String newMessage, @Nullable Object... messageParams) throws X;
    //
    //    <X extends Throwable> void wrap(@Nonnull final BiFunction<? super Throwable, ? super String, ? extends X> wrapper,
    //            @Nonnull final Supplier<? super String> newMessage, @Nullable Object... messageParams) throws X;

    <X extends Throwable> S wrapIf(@Nonnull final Predicate<? super T> predicate,
            @Nonnull final Function<? super Throwable, ? extends X> wrapper) throws X;

    //    <X extends Throwable> S wrapIf(@Nonnull final Predicate<? super T> predicate,
    //            @Nonnull final Function<? super Throwable, ? extends X> wrapper, @Nonnull final String newMessage,
    //            @Nullable Object... messageParams) throws X;
    //
    //    <X extends Throwable> S wrapIf(@Nonnull final Predicate<? super T> predicate,
    //            @Nonnull final Function<? super Throwable, ? extends X> wrapper,
    //            @Nonnull final Supplier<? super String> newMessage, @Nullable Object... messageParams) throws X;

    <X extends Throwable> void replace(@Nonnull final Supplier<? extends X> supplier) throws X;

    //    void throwAsIs() throws T;
    //
    //    <X extends Throwable> S throwIf(@Nonnull final Class<X> exClazz) throws X;
    //
    //    <X extends Throwable> S replaceIf(@Nonnull final Predicate<? super T> predicate,
    //            final @Nonnull Supplier<? extends X> supplier) throws X;
    //
    //    <X extends Throwable> S replaceIf(@Nonnull final Predicate<? super T> predicate,
    //            final @Nonnull Supplier<? extends X> supplier, @Nonnull final String message) throws X;
    //
    //    <X extends Throwable> S replaceIf(@Nonnull final Predicate<? super T> predicate,
    //            final @Nonnull Supplier<? extends X> supplier,
    //            @Nonnull final Supplier<? extends String> messageSupplier) throws X;
    //
    //    <X extends Throwable> S replaceIf(@Nonnull final Predicate<? super T> predicate,
    //            final @Nonnull Supplier<? extends X> supplier, @Nonnull final String message,
    //            Object... messageParams) throws X;
    //
    //    <X extends Throwable> S replaceIf(@Nonnull final Predicate<? super T> predicate,
    //            final @Nonnull Supplier<? extends X> supplier, @Nonnull final Supplier<? extends String> messageSupplier,
    //            Object... messageParams) throws X;

    final class The<T extends Throwable> extends HandlerImpl<T, The<T>> {
        public The(@Nonnull T throwable) {
            super(throwable);
        }
    }

}
