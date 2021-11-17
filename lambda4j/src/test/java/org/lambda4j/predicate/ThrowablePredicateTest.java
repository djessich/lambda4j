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

class ThrowablePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowablePredicate<String, Throwable> predicate = ThrowablePredicate.of(t -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowablePredicate<String, Throwable> predicate = ThrowablePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowablePredicate.call(t -> false, ""));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowablePredicate.call(t -> false, null));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowablePredicate.call(null, ""));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowablePredicate<String, Throwable> predicate = ThrowablePredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows(""));
            Assertions.assertFalse(predicate.testThrows(""));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowablePredicate<String, Throwable> predicate = ThrowablePredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("")));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowablePredicate<String, Throwable> predicate = ThrowablePredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("")));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowablePredicate<String, Throwable> predicate = ThrowablePredicate.isEqual("");
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("")));
    }

    @Test
    void isEqual_givenDifferent_returnsFalse() {
        ThrowablePredicate<String, Throwable> predicate = ThrowablePredicate.isEqual("other");
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("")));
    }
}
