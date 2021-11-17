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

package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.of((t, u) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiPredicate.call((t, u) -> false, "", ""));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiPredicate.call((t, u) -> false, null, ""));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiPredicate.call((t, u) -> false, "", null));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableBiPredicate.call(null, "", ""));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", ""));
            Assertions.assertFalse(predicate.testThrows("", ""));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "")));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "")));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.isEqual("", "");
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "")));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.isEqual("first", "");
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.test("", "")));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.isEqual("", "second");
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.test("", "")));
    }

    @Test
    void isEqual_givenDifferentValueAll_returnsFalse() {
        ThrowableBiPredicate<String, String, Throwable> predicate = ThrowableBiPredicate.isEqual((byte) 1, (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "")));
    }
}
