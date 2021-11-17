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

class ThrowableTriFloatPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriFloatPredicate<Throwable> predicate =
                ThrowableTriFloatPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriFloatPredicate<Throwable> predicate = ThrowableTriFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableTriFloatPredicate.call((value1, value2, value3) -> false, 0.0f, 0.0f, 0.0f));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableTriFloatPredicate.call(null, 0.0f, 0.0f, 0.0f));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableTriFloatPredicate<Throwable> predicate = ThrowableTriFloatPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows(0.0f, 0.0f, 0.0f));
            Assertions.assertFalse(predicate.testThrows(0.0f, 0.0f, 0.0f));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableTriFloatPredicate<Throwable> predicate = ThrowableTriFloatPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0.0f, 0.0f, 0.0f)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableTriFloatPredicate<Throwable> predicate = ThrowableTriFloatPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0f, 0.0f, 0.0f)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableTriFloatPredicate<Throwable> predicate = ThrowableTriFloatPredicate.isEqual(0.0f, 0.0f, 0.0f);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0.0f, 0.0f, 0.0f)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableTriFloatPredicate<Throwable> predicate = ThrowableTriFloatPredicate.isEqual(1.0f, 0.0f, 0.0f);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0f, 0.0f, 0.0f)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableTriFloatPredicate<Throwable> predicate = ThrowableTriFloatPredicate.isEqual(0.0f, 1.0f, 0.0f);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0f, 0.0f, 0.0f)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableTriFloatPredicate<Throwable> predicate = ThrowableTriFloatPredicate.isEqual(0.0f, 0.0f, 1.0f);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0f, 0.0f, 0.0f)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableTriFloatPredicate<Throwable> predicate = ThrowableTriFloatPredicate.isEqual(1.0f, 1.0f, 1.0f);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0f, 0.0f, 0.0f)));
    }
}
