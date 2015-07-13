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

package at.gridtec.lambda4j.function.primitives.to;

import java.util.function.Function;

/**
 * Represents a function that produces a char-valued result from one argument. This is the {@code char}-producing
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(Object)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToCharFunction<T> {

    /**
     * Applies this {@link ToCharFunction} to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     */
    char applyAsChar(T t);
}
