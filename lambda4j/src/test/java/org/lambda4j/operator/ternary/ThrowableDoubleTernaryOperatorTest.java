/*
 * Copyright (c) 2021 The lambda4j authors.
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

package org.lambda4j.operator.ternary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleTernaryOperatorTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleTernaryOperator<Throwable> operator =
                ThrowableDoubleTernaryOperator.of((value1, value2, value3) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleTernaryOperator<Throwable> operator = ThrowableDoubleTernaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}
