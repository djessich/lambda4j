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

package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriIntPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableTriIntPredicate.call((value1, value2, value3) -> false, 0, 0, 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableTriIntPredicate.call(null, 0, 0, 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows(0, 0, 0));
            Assertions.assertFalse(predicate.testThrows(0, 0, 0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0, 0, 0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0, 0, 0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.isEqual(0, 0, 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0, 0, 0)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.isEqual(1, 0, 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0, 0, 0)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.isEqual(0, 1, 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0, 0, 0)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.isEqual(0, 0, 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0, 0, 0)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableTriIntPredicate<Throwable> predicate = ThrowableTriIntPredicate.isEqual(1, 1, 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0, 0, 0)));
    }
}
