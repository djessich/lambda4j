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

class ThrowableBiObjBooleanPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate =
                ThrowableBiObjBooleanPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate = ThrowableBiObjBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjBooleanPredicate.call((t, u, value) -> false, "", "", true));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjBooleanPredicate.call((t, u, value) -> false, null, "", true));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjBooleanPredicate.call((t, u, value) -> false, "", null, true));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableBiObjBooleanPredicate.call(null, "", "", true));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate =
                ThrowableBiObjBooleanPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", "", false));
            Assertions.assertFalse(predicate.testThrows("", "", false));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate =
                ThrowableBiObjBooleanPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", false)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate =
                ThrowableBiObjBooleanPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", false)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate =
                ThrowableBiObjBooleanPredicate.isEqual("", "", false);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", false)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate =
                ThrowableBiObjBooleanPredicate.isEqual("first", "", false);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", false)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate =
                ThrowableBiObjBooleanPredicate.isEqual("", "second", false);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", false)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate =
                ThrowableBiObjBooleanPredicate.isEqual("", "", true);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", false)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableBiObjBooleanPredicate<String, String, Throwable> predicate =
                ThrowableBiObjBooleanPredicate.isEqual("other1", "other2", true);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", false)));
    }
}
