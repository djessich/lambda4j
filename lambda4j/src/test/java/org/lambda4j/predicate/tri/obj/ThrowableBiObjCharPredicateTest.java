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

class ThrowableBiObjCharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate =
                ThrowableBiObjCharPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate = ThrowableBiObjCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjCharPredicate.call((t, u, value) -> false, "", "", 'c'));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjCharPredicate.call((t, u, value) -> false, null, "", 'c'));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjCharPredicate.call((t, u, value) -> false, "", null, 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableBiObjCharPredicate.call(null, "", "", 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate = ThrowableBiObjCharPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", "", 'c'));
            Assertions.assertFalse(predicate.testThrows("", "", 'c'));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate = ThrowableBiObjCharPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", 'c')));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate = ThrowableBiObjCharPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 'c')));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate =
                ThrowableBiObjCharPredicate.isEqual("", "", 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", 'c')));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate =
                ThrowableBiObjCharPredicate.isEqual("first", "", 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 'c')));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate =
                ThrowableBiObjCharPredicate.isEqual("", "second", 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 'c')));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate =
                ThrowableBiObjCharPredicate.isEqual("", "", 'd');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 'c')));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableBiObjCharPredicate<String, String, Throwable> predicate =
                ThrowableBiObjCharPredicate.isEqual("other1", "other2", 'd');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 'c')));
    }
}
