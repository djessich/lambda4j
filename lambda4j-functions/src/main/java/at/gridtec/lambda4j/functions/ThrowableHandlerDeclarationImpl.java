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
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by domin on 07.04.2016.
 */
public class ThrowableHandlerDeclarationImpl<H extends ThrowableHandlerDeclarationImpl<H, X>, X extends Throwable>
        implements ThrowableHandlerDeclaration<H, X> {

    private final X throwable;

    public ThrowableHandlerDeclarationImpl(@Nonnull final X throwable) {
        this.throwable = throwable;
    }

    private static <T extends Throwable> void throwIt(@Nonnull final T throwable) throws T {
        throw throwable;
    }

    @Override
    @Nonnull
    public <Y extends Throwable> H throwIf(@Nonnull Class<Y> clazz) throws Y {
        Objects.requireNonNull(clazz);
        if (clazz.isInstance(throwable)) {
            throwIt(clazz.cast(throwable));
        }
        return self();
    }

    @Nonnull
    @Override
    public <Y extends Throwable> H replace(@Nonnull Supplier<? extends Y> factory) throws Y {
        Objects.requireNonNull(factory);
        throwIt(factory.get());
        return self();
    }

    @Nonnull
    @Override
    public <Y extends Throwable> H replace(@Nonnull Function<? super String, ? extends Y> factory,
            @Nonnull String message, @Nullable Object... messageParams) throws Y {
        Objects.requireNonNull(factory);
        Objects.requireNonNull(message);
        throwIt(factory.apply(String.format(message, messageParams)));
        return self();
    }

    @Nonnull
    @Override
    public <Y extends Throwable> H replaceIf(@Nonnull Predicate<? super X> condition,
            @Nonnull Supplier<? extends Y> factory) throws Y {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(factory);
        if (condition.test(throwable)) {
            throwIt(factory.get());
        }
        return self();
    }

    @Nonnull
    @Override
    public <Y extends Throwable> H replaceIf(@Nonnull Predicate<? super X> condition,
            @Nonnull Function<? super String, ? extends Y> factory, @Nonnull String message,
            @Nullable Object... messageParams) throws Y {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(factory);
        Objects.requireNonNull(message);
        if (condition.test(throwable)) {
            throwIt(factory.apply(String.format(message, messageParams)));
        }
        return self();
    }

    @Override
    @Nonnull
    public <Y extends Throwable> H wrap(@Nonnull final Function<? super X, ? extends Y> factory) throws Y {
        Objects.requireNonNull(factory);
        throwIt(factory.apply(throwable));
        return self();
    }

    @Override
    @Nonnull
    public <Y extends Throwable> H wrap(@Nonnull BiFunction<? super String, ? super X, ? extends Y> factory,
            @Nonnull String message, @Nullable Object... messageParams) throws Y {
        Objects.requireNonNull(factory);
        Objects.requireNonNull(message);
        throwIt(factory.apply(String.format(message, messageParams), throwable));
        return self();
    }

    @Override
    @Nonnull
    public <Y extends Throwable> H wrapIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull Function<? super X, ? extends Y> factory) throws Y {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(factory);
        if (condition.test(throwable)) {
            throwIt(factory.apply(throwable));
        }
        return self();
    }

    @Override
    @Nonnull
    public <Y extends Throwable> H wrapIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull BiFunction<? super String, ? super X, ? extends Y> factory, @Nonnull String message,
            @Nullable Object... messageParams) throws Y {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(factory);
        Objects.requireNonNull(message);
        if (condition.test(throwable)) {
            throwIt(factory.apply(String.format(message, messageParams), throwable));
        }
        return self();
    }

    @Nonnull
    public <Y extends Throwable> H addSuppressed(@Nonnull final Supplier<? extends Y> factory) {
        Objects.requireNonNull(factory);
        throwable.addSuppressed(factory.get());
        return self();
    }

    @Nonnull
    public <Y extends Throwable> H addSuppressed(@Nonnull final Function<? super String, ? extends Y> factory,
            @Nonnull String message, @Nullable Object... messageParams) {
        Objects.requireNonNull(factory);
        Objects.requireNonNull(message);
        throwable.addSuppressed(factory.apply(String.format(message, messageParams)));
        return self();
    }

    @Nonnull
    public <Y extends Throwable> H addSuppressedIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull final Supplier<? extends Y> factory) {
        Objects.requireNonNull(factory);
        if (condition.test(throwable)) {
            throwable.addSuppressed(factory.get());
        }
        return self();
    }

    @Nonnull
    public <Y extends Throwable> H addSuppressedIf(@Nonnull final Predicate<? super X> condition,
            @Nonnull final Function<? super String, ? extends Y> factory, @Nonnull String message,
            @Nullable Object... messageParams) {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(factory);
        Objects.requireNonNull(message);
        if (condition.test(throwable)) {
            throwable.addSuppressed(factory.apply(String.format(message, messageParams)));
        }
        return self();
    }

    @Nonnull
    @Override
    public <Y extends Throwable> H addAsSuppressed(@Nonnull Supplier<? extends Y> factory) throws Y {
        Objects.requireNonNull(factory);
        final Y newThrowable = factory.get();
        newThrowable.addSuppressed(throwable);
        throwIt(newThrowable);
        return self();
    }

    @Nonnull
    @Override
    public <Y extends Throwable> H addAsSuppressed(@Nonnull Function<? super String, ? extends Y> factory,
            @Nonnull String message, @Nullable Object... messageParams) throws Y {
        Objects.requireNonNull(factory);
        Objects.requireNonNull(message);
        final Y newThrowable = factory.apply(String.format(message, messageParams));
        newThrowable.addSuppressed(throwable);
        throwIt(newThrowable);
        return self();
    }

    @Nonnull
    @Override
    public <Y extends Throwable> H addAsSuppressedIf(@Nonnull Predicate<? super X> condition,
            @Nonnull Supplier<? extends Y> factory) throws Y {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(factory);
        if (condition.test(throwable)) {
            final Y newThrowable = factory.get();
            newThrowable.addSuppressed(throwable);
            throwIt(newThrowable);
        }
        return self();
    }

    @Nonnull
    @Override
    public <Y extends Throwable> H addAsSuppressedIf(@Nonnull Predicate<? super X> condition,
            @Nonnull Function<? super String, ? extends Y> factory, @Nonnull String message,
            @Nullable Object... messageParams) throws Y {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(factory);
        Objects.requireNonNull(message);
        if (condition.test(throwable)) {
            final Y newThrowable = factory.apply(String.format(message, messageParams));
            newThrowable.addSuppressed(throwable);
            throwIt(newThrowable);
        }
        return self();
    }

    public void throwAsIs() throws X {
        throwIt(throwable);
    }

    @SuppressWarnings("unchecked")
    private H self() {
        return (H) this;
    }
}
