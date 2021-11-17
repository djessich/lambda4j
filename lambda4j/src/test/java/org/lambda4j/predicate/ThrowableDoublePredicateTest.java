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

class ThrowableDoublePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoublePredicate<Throwable> predicate = ThrowableDoublePredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoublePredicate<Throwable> predicate = ThrowableDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableDoublePredicate.call(value -> false, 0.0d));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableDoublePredicate.call(null, 0.0d));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableDoublePredicate<Throwable> predicate = ThrowableDoublePredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows(0.0d));
            Assertions.assertFalse(predicate.testThrows(0.0d));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableDoublePredicate<Throwable> predicate = ThrowableDoublePredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0.0d)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableDoublePredicate<Throwable> predicate = ThrowableDoublePredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0d)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableDoublePredicate<Throwable> predicate = ThrowableDoublePredicate.isEqual(0.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0.0d)));
    }

    @Test
    void isEqual_givenDifferent_returnsFalse() {
        ThrowableDoublePredicate<Throwable> predicate = ThrowableDoublePredicate.isEqual(1.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0d)));
    }
}
