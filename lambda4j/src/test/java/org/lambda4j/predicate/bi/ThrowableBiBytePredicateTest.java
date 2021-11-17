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

class ThrowableBiBytePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiBytePredicate<Throwable> predicate = ThrowableBiBytePredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiBytePredicate<Throwable> predicate = ThrowableBiBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiBytePredicate.call((value1, value2) -> false, (byte) 0, (byte) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableBiBytePredicate.call(null, (byte) 0, (byte) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableBiBytePredicate<Throwable> predicate = ThrowableBiBytePredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows((byte) 0, (byte) 0));
            Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableBiBytePredicate<Throwable> predicate = ThrowableBiBytePredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows((byte) 0, (byte) 0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableBiBytePredicate<Throwable> predicate = ThrowableBiBytePredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableBiBytePredicate<Throwable> predicate = ThrowableBiBytePredicate.isEqual((byte) 0, (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows((byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableBiBytePredicate<Throwable> predicate = ThrowableBiBytePredicate.isEqual((byte) 1, (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableBiBytePredicate<Throwable> predicate = ThrowableBiBytePredicate.isEqual((byte) 0, (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableBiBytePredicate<Throwable> predicate = ThrowableBiBytePredicate.isEqual((byte) 1, (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0)));
    }
}
