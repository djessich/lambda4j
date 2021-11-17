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

class ThrowableBiCharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiCharPredicate<Throwable> predicate = ThrowableBiCharPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiCharPredicate<Throwable> predicate = ThrowableBiCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiCharPredicate.call((value1, value2) -> false, 'c', 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableBiCharPredicate.call(null, 'c', 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableBiCharPredicate<Throwable> predicate = ThrowableBiCharPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows('c', 'c'));
            Assertions.assertFalse(predicate.testThrows('c', 'c'));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableBiCharPredicate<Throwable> predicate = ThrowableBiCharPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows('c', 'c')));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableBiCharPredicate<Throwable> predicate = ThrowableBiCharPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c', 'c')));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableBiCharPredicate<Throwable> predicate = ThrowableBiCharPredicate.isEqual('c', 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows('c', 'c')));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableBiCharPredicate<Throwable> predicate = ThrowableBiCharPredicate.isEqual('d', 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c', 'c')));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableBiCharPredicate<Throwable> predicate = ThrowableBiCharPredicate.isEqual('c', 'd');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c', 'c')));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableBiCharPredicate<Throwable> predicate = ThrowableBiCharPredicate.isEqual('d', 'd');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c', 'c')));
    }
}
