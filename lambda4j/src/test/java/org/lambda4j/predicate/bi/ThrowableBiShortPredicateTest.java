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

class ThrowableBiShortPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortPredicate<Throwable> predicate = ThrowableBiShortPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortPredicate<Throwable> predicate = ThrowableBiShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiShortPredicate.call((value1, value2) -> false, (short) 0, (short) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableBiShortPredicate.call(null, (short) 0, (short) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableBiShortPredicate<Throwable> predicate = ThrowableBiShortPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows((short) 0, (short) 0));
            Assertions.assertFalse(predicate.testThrows((short) 0, (short) 0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableBiShortPredicate<Throwable> predicate = ThrowableBiShortPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows((short) 0, (short) 0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableBiShortPredicate<Throwable> predicate = ThrowableBiShortPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((short) 0, (short) 0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableBiShortPredicate<Throwable> predicate = ThrowableBiShortPredicate.isEqual((short) 0, (short) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows((short) 0, (short) 0)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableBiShortPredicate<Throwable> predicate = ThrowableBiShortPredicate.isEqual((short) 1, (short) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((short) 0, (short) 0)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableBiShortPredicate<Throwable> predicate = ThrowableBiShortPredicate.isEqual((short) 0, (short) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((short) 0, (short) 0)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableBiShortPredicate<Throwable> predicate = ThrowableBiShortPredicate.isEqual((byte) 1, (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((short) 0, (short) 0)));
    }
}
