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

class ThrowableBiObjBytePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate =
                ThrowableBiObjBytePredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate = ThrowableBiObjBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjBytePredicate.call((t, u, value) -> false, "", "", (byte) 0));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjBytePredicate.call((t, u, value) -> false, null, "", (byte) 0));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjBytePredicate.call((t, u, value) -> false, "", null, (byte) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableBiObjBytePredicate.call(null, "", "", (byte) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate = ThrowableBiObjBytePredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", "", (byte) 0));
            Assertions.assertFalse(predicate.testThrows("", "", (byte) 0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate = ThrowableBiObjBytePredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", (byte) 0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate = ThrowableBiObjBytePredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", (byte) 0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate =
                ThrowableBiObjBytePredicate.isEqual("", "", (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate =
                ThrowableBiObjBytePredicate.isEqual("first", "", (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate =
                ThrowableBiObjBytePredicate.isEqual("", "second", (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate =
                ThrowableBiObjBytePredicate.isEqual("", "", (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableBiObjBytePredicate<String, String, Throwable> predicate =
                ThrowableBiObjBytePredicate.isEqual("other1", "other2", (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", (byte) 0)));
    }
}
