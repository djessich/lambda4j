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

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Dominik Jessich on 24.11.2015.
 */
@SuppressWarnings({ "unused", "ThrowableResultOfMethodCallIgnored" })
public class HandlerImpl<T extends Throwable, S extends HandlerImpl<T, S>> implements Handler<T, S> {

    private final T throwable;

    public HandlerImpl(@Nonnull T throwable) {
        Objects.requireNonNull(throwable);
        this.throwable = throwable;
    }

    @Override
    public <X extends Throwable> void wrap(@Nonnull Function<? super Throwable, ? extends X> wrapper) throws X {
        Objects.requireNonNull(wrapper);
        throw wrapper.apply(throwable);
    }

    @Override
    public <X extends Throwable> S wrapIf(@Nonnull Predicate<? super T> predicate,
            @Nonnull Function<? super Throwable, ? extends X> wrapper) throws X {
        Objects.requireNonNull(wrapper);
        if (predicate.test(throwable)) {
            throw wrapper.apply(throwable);
        }
        return getThis();
    }

    @Override
    public <X extends Throwable> void replace(@Nonnull Supplier<? extends X> supplier) throws X {
        Objects.requireNonNull(supplier);
        throw supplier.get();
    }
}
