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

class ThrowableObjBytePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBytePredicate<String, Throwable> predicate = ThrowableObjBytePredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBytePredicate<String, Throwable> predicate = ThrowableObjBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBytePredicate.call((t, value) -> false, "", (byte) 0));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBytePredicate.call((t, value) -> false, null, (byte) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableObjBytePredicate.call(null, "", (byte) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableObjBytePredicate<String, Throwable> predicate = ThrowableObjBytePredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", (byte) 0));
            Assertions.assertFalse(predicate.testThrows("", (byte) 0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableObjBytePredicate<String, Throwable> predicate = ThrowableObjBytePredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", (byte) 0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableObjBytePredicate<String, Throwable> predicate = ThrowableObjBytePredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (byte) 0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableObjBytePredicate<String, Throwable> predicate = ThrowableObjBytePredicate.isEqual("", (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableObjBytePredicate<String, Throwable> predicate = ThrowableObjBytePredicate.isEqual("first", (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableObjBytePredicate<String, Throwable> predicate = ThrowableObjBytePredicate.isEqual("", (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableObjBytePredicate<String, Throwable> predicate = ThrowableObjBytePredicate.isEqual("other", (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", (byte) 0)));
    }
}
