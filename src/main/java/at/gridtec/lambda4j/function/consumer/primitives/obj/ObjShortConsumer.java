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

package at.gridtec.lambda4j.function.consumer.primitives.obj;

import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts an object-valued and a {@code short}-valued argument, and returns no result.
 * This is the {@code (reference, short)} specialization of {@link BiConsumer}. Unlike most other functional
 * interfaces, {@code ObjShortConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, short)}.
 *
 * @param <T> The type of argument to the operation
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjShortConsumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t The first argument to the operation
     * @param value The second argument to the operation
     */
    void accept(T t, short value);
}
