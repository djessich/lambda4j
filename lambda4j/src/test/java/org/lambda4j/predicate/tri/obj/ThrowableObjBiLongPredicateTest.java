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

class ThrowableObjBiLongPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate =
                ThrowableObjBiLongPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate = ThrowableObjBiLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBiLongPredicate.call((t, value1, value2) -> false, "", 0L, 0L));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBiLongPredicate.call((t, value1, value2) -> false, null, 0L, 0L));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableObjBiLongPredicate.call(null, "", 0L, 0L));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableObjBiLongPredicate<String, Throwable> predicate = ThrowableObjBiLongPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", 0L, 0L));
            Assertions.assertFalse(predicate.testThrows("", 0L, 0L));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate = ThrowableObjBiLongPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", 0L, 0L)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate = ThrowableObjBiLongPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 0L, 0L)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate = ThrowableObjBiLongPredicate.isEqual("", 0L, 0L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", 0L, 0L)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate = ThrowableObjBiLongPredicate.isEqual("first", 0L, 0L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 0L, 0L)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate = ThrowableObjBiLongPredicate.isEqual("", 1L, 0L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 0L, 0L)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate = ThrowableObjBiLongPredicate.isEqual("", 0L, 1L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 0L, 0L)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableObjBiLongPredicate<String, Throwable> predicate = ThrowableObjBiLongPredicate.isEqual("other", 1L, 1L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 0L, 0L)));
    }
}
