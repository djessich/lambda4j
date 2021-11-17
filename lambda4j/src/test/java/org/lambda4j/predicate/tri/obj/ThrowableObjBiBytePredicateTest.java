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

package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiBytePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate =
                ThrowableObjBiBytePredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate = ThrowableObjBiBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBiBytePredicate.call((t, value1, value2) -> false, "", (byte) 0, (byte) 0));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(
                ThrowableObjBiBytePredicate.call((t, value1, value2) -> false, null, (byte) 0, (byte) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableObjBiBytePredicate.call(null, "", (byte) 0, (byte) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableObjBiBytePredicate<String, Throwable> predicate = ThrowableObjBiBytePredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", (byte) 0, (byte) 0));
            Assertions.assertFalse(predicate.testThrows("", (byte) 0, (byte) 0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate = ThrowableObjBiBytePredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", (byte) 0, (byte) 0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate = ThrowableObjBiBytePredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate =
                ThrowableObjBiBytePredicate.isEqual("", (byte) 0, (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", (byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate =
                ThrowableObjBiBytePredicate.isEqual("first", (byte) 0, (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate =
                ThrowableObjBiBytePredicate.isEqual("", (byte) 1, (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate =
                ThrowableObjBiBytePredicate.isEqual("", (byte) 0, (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableObjBiBytePredicate<String, Throwable> predicate =
                ThrowableObjBiBytePredicate.isEqual("other", (byte) 1, (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (byte) 0, (byte) 0)));
    }
}
