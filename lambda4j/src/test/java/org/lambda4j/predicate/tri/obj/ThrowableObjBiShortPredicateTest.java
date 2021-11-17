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

class ThrowableObjBiShortPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate =
                ThrowableObjBiShortPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate = ThrowableObjBiShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(
                ThrowableObjBiShortPredicate.call((t, value1, value2) -> false, "", (short) 0, (short) 0));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(
                ThrowableObjBiShortPredicate.call((t, value1, value2) -> false, null, (short) 0, (short) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableObjBiShortPredicate.call(null, "", (short) 0, (short) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableObjBiShortPredicate<String, Throwable> predicate = ThrowableObjBiShortPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", (short) 0, (short) 0));
            Assertions.assertFalse(predicate.testThrows("", (short) 0, (short) 0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate = ThrowableObjBiShortPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", (short) 0, (short) 0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate = ThrowableObjBiShortPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (short) 0, (short) 0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate =
                ThrowableObjBiShortPredicate.isEqual("", (short) 0, (short) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", (short) 0, (short) 0)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate =
                ThrowableObjBiShortPredicate.isEqual("first", (short) 0, (short) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (short) 0, (short) 0)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate =
                ThrowableObjBiShortPredicate.isEqual("", (short) 1, (short) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (short) 0, (short) 0)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate =
                ThrowableObjBiShortPredicate.isEqual("", (short) 0, (short) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (short) 0, (short) 0)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableObjBiShortPredicate<String, Throwable> predicate =
                ThrowableObjBiShortPredicate.isEqual("other", (short) 1, (short) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (short) 0, (short) 0)));
    }
}
