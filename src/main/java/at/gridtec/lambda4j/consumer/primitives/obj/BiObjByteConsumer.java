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
package at.gridtec.lambda4j.consumer.primitives.obj;

import at.gridtec.lambda4j.consumer.TriConsumer;

/**
 * Represents an operation that accepts two object-valued and a {@code byte}-valued argument, and returns no result.
 * This is the {@code (reference, reference, byte)} specialization of {@link TriConsumer}. Unlike most other functional
 * interfaces, {@code BiObjByteConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(Object, Object, byte)}.
 *
 * @param <T> The type of the first argument to the operation
 * @param <U> The type of the second argument to the operation
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjByteConsumer<T, U> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t The first argument to the operation
     * @param u The second argument to the operation
     * @param value The third argument to the operation
     */
    void accept(T t, U u, byte value);
}
