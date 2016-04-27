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
import java.util.Objects;

/**
 * Created by domin on 10.04.2016.
 */
public final class ThrowableHandler<X extends Throwable>
        extends ThrowableHandlerDeclarationImpl<ThrowableHandler<X>, X> {

    /**
     * Creates this {@link ThrowableHandler} from given {@link Throwable}.
     *
     * @param throwable The throwable to be handled
     */
    public ThrowableHandler(@Nonnull X throwable) {
        super(throwable);
    }

    /**
     * Statically creates this {@link ThrowableHandler} from given {@link Throwable}.
     *
     * @param <X> The type of the throwable to be handled by the returned handler
     * @param throwable The throwable to be handled
     * @return A newly created {@code ThrowableHandler} instance
     * @throws NullPointerException If given argument is {@code null}
     */
    public static <X extends Throwable> ThrowableHandler<X> of(@Nonnull final X throwable) {
        Objects.requireNonNull(throwable);
        return new ThrowableHandler<>(throwable);
    }
}
