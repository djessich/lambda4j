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
package at.gridtec.lambda4j.consumer.primitives.bi;

import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two {@code double}-valued arguments and returns no result. This is the
 * primitive type specialization of {@link BiConsumer} for {@code double}. Unlike most other functional interfaces,
 * {@code DoubleBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(double, double)}.
 *
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleBiConsumer {

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument for the operation to be consumed
     * @param value2 The second argument for the operation to be consumed
     */
    void accept(double value1, double value2);
}
