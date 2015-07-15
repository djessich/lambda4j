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
package at.gridtec.lambda4j.function.primitives;

import java.util.function.Function;

/**
 * Represents a function that accepts a char-valued argument and produces a result. This is the {@code char}-consuming
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(char)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharFunction<R> {

    /**
     * Applies this {@link CharFunction} to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(char value);
}