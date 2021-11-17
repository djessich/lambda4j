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

package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjLongPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjLongPredicate<String, Throwable> predicate = ThrowableObjLongPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjLongPredicate<String, Throwable> predicate = ThrowableObjLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjLongPredicate.call((t, value) -> false, "", 0L));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjLongPredicate.call((t, value) -> false, null, 0L));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableObjLongPredicate.call(null, "", 0L));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableObjLongPredicate<String, Throwable> predicate = ThrowableObjLongPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", 0L));
            Assertions.assertFalse(predicate.testThrows("", 0L));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableObjLongPredicate<String, Throwable> predicate = ThrowableObjLongPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", 0L)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableObjLongPredicate<String, Throwable> predicate = ThrowableObjLongPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 0L)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableObjLongPredicate<String, Throwable> predicate = ThrowableObjLongPredicate.isEqual("", 0L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", 0L)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableObjLongPredicate<String, Throwable> predicate = ThrowableObjLongPredicate.isEqual("first", 0L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 0L)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableObjLongPredicate<String, Throwable> predicate = ThrowableObjLongPredicate.isEqual("", 1L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 0L)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableObjLongPredicate<String, Throwable> predicate = ThrowableObjLongPredicate.isEqual("other", 1L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 0L)));
    }
}
