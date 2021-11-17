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

class ThrowableObjShortPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjShortPredicate<String, Throwable> predicate = ThrowableObjShortPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjShortPredicate<String, Throwable> predicate = ThrowableObjShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjShortPredicate.call((t, value) -> false, "", (short) 0));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjShortPredicate.call((t, value) -> false, null, (short) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableObjShortPredicate.call(null, "", (short) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableObjShortPredicate<String, Throwable> predicate = ThrowableObjShortPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", (short) 0));
            Assertions.assertFalse(predicate.testThrows("", (short) 0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableObjShortPredicate<String, Throwable> predicate = ThrowableObjShortPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", (short) 0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableObjShortPredicate<String, Throwable> predicate = ThrowableObjShortPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (short) 0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableObjShortPredicate<String, Throwable> predicate = ThrowableObjShortPredicate.isEqual("", (short) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", (short) 0)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableObjShortPredicate<String, Throwable> predicate =
                ThrowableObjShortPredicate.isEqual("first", (short) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (short) 0)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableObjShortPredicate<String, Throwable> predicate = ThrowableObjShortPredicate.isEqual("", (short) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (short) 0)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableObjShortPredicate<String, Throwable> predicate =
                ThrowableObjShortPredicate.isEqual("other", (short) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (short) 0)));
    }
}
