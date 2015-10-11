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
package at.gridtec.lambda4j.function.primitives.conversion;

import java.util.function.Function;

/**
 * Represents a function that accepts a int-valued argument and produces a char-valued result. This is the {@code
 * int}-to-{@code char} primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(int)}.
 *
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface IntToCharFunction {

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to this function
     * @return The result from this function, which is its result.
     */
    char applyAsChar(int value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 1;
    }
}
