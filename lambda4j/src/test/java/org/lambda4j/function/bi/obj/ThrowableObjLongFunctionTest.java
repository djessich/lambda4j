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

package org.lambda4j.function.bi.obj;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class ThrowableObjLongFunctionTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjLongFunction<String, String, Throwable> function = ThrowableObjLongFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjLongFunction<String, String, Throwable> function = ThrowableObjLongFunction.of(null);
        Assertions.assertNull(function);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void lift_givenExpression_returnsFunctionalInterface(String ret) {
        ThrowableObjLongFunction<String, Optional<String>, Throwable> function =
                ThrowableObjLongFunction.lift((t, value) -> ret);
        Assertions.assertNotNull(function);
        Optional<String> optional = Assertions.assertDoesNotThrow(() -> function.applyThrows("", 0L));
        Assertions.assertNotNull(optional);
        if (ret == null) {
            Assertions.assertFalse(optional.isPresent());
            Assertions.assertThrows(NoSuchElementException.class, optional::get);
        } else {
            Assertions.assertTrue(optional.isPresent());
            Assertions.assertEquals(ret, optional.get());
        }
    }

    @Test
    void lift_givenNull_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableObjLongFunction.lift(null));
    }
}
