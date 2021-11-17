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

class ThrowableObjBiCharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate =
                ThrowableObjBiCharPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate = ThrowableObjBiCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBiCharPredicate.call((t, value1, value2) -> false, "", 'c', 'c'));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBiCharPredicate.call((t, value1, value2) -> false, null, 'c', 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableObjBiCharPredicate.call(null, "", 'c', 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableObjBiCharPredicate<String, Throwable> predicate = ThrowableObjBiCharPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", 'c', 'c'));
            Assertions.assertFalse(predicate.testThrows("", 'c', 'c'));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate = ThrowableObjBiCharPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", 'c', 'c')));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate = ThrowableObjBiCharPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 'c', 'c')));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate = ThrowableObjBiCharPredicate.isEqual("", 'c', 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", 'c', 'c')));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate =
                ThrowableObjBiCharPredicate.isEqual("first", 'c', 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 'c', 'c')));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate = ThrowableObjBiCharPredicate.isEqual("", 'd', 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 'c', 'c')));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate = ThrowableObjBiCharPredicate.isEqual("", 'c', 'd');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 'c', 'c')));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableObjBiCharPredicate<String, Throwable> predicate =
                ThrowableObjBiCharPredicate.isEqual("other", 'd', 'd');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 'c', 'c')));
    }
}
