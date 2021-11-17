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

package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongPredicate<Throwable> predicate = ThrowableLongPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongPredicate<Throwable> predicate = ThrowableLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableLongPredicate.call(value -> false, 0L));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableLongPredicate.call(null, 0L));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableLongPredicate<Throwable> predicate = ThrowableLongPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows(0L));
            Assertions.assertFalse(predicate.testThrows(0L));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableLongPredicate<Throwable> predicate = ThrowableLongPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0L)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableLongPredicate<Throwable> predicate = ThrowableLongPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0L)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableLongPredicate<Throwable> predicate = ThrowableLongPredicate.isEqual(0L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0L)));
    }

    @Test
    void isEqual_givenDifferent_returnsFalse() {
        ThrowableLongPredicate<Throwable> predicate = ThrowableLongPredicate.isEqual(1L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0L)));
    }
}
