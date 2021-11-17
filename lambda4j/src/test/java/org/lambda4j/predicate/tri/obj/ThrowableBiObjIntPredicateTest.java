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

class ThrowableBiObjIntPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate =
                ThrowableBiObjIntPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate = ThrowableBiObjIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjIntPredicate.call((t, u, value) -> false, "", "", 0));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjIntPredicate.call((t, u, value) -> false, null, "", 0));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjIntPredicate.call((t, u, value) -> false, "", null, 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableBiObjIntPredicate.call(null, "", "", 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate = ThrowableBiObjIntPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", "", 0));
            Assertions.assertFalse(predicate.testThrows("", "", 0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate = ThrowableBiObjIntPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", 0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate = ThrowableBiObjIntPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate = ThrowableBiObjIntPredicate.isEqual("", "", 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", 0)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate =
                ThrowableBiObjIntPredicate.isEqual("first", "", 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate =
                ThrowableBiObjIntPredicate.isEqual("", "second", 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate = ThrowableBiObjIntPredicate.isEqual("", "", 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableBiObjIntPredicate<String, String, Throwable> predicate =
                ThrowableBiObjIntPredicate.isEqual("other1", "other2", 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0)));
    }
}
