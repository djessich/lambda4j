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

class ThrowableIntPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntPredicate<Throwable> predicate = ThrowableIntPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntPredicate<Throwable> predicate = ThrowableIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableIntPredicate.call(value -> false, 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableIntPredicate.call(null, 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableIntPredicate<Throwable> predicate = ThrowableIntPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows(0));
            Assertions.assertFalse(predicate.testThrows(0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableIntPredicate<Throwable> predicate = ThrowableIntPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableIntPredicate<Throwable> predicate = ThrowableIntPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableIntPredicate<Throwable> predicate = ThrowableIntPredicate.isEqual(0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0)));
    }

    @Test
    void isEqual_givenDifferent_returnsFalse() {
        ThrowableIntPredicate<Throwable> predicate = ThrowableIntPredicate.isEqual(1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0)));
    }
}
