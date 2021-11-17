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

class ThrowableTriDoublePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriDoublePredicate<Throwable> predicate =
                ThrowableTriDoublePredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriDoublePredicate<Throwable> predicate = ThrowableTriDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableTriDoublePredicate.call((value1, value2, value3) -> false, 0.0d, 0.0d, 0.0d));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableTriDoublePredicate.call(null, 0.0d, 0.0d, 0.0d));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableTriDoublePredicate<Throwable> predicate = ThrowableTriDoublePredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows(0.0d, 0.0d, 0.0d));
            Assertions.assertFalse(predicate.testThrows(0.0d, 0.0d, 0.0d));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableTriDoublePredicate<Throwable> predicate = ThrowableTriDoublePredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0.0d, 0.0d, 0.0d)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableTriDoublePredicate<Throwable> predicate = ThrowableTriDoublePredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0d, 0.0d, 0.0d)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableTriDoublePredicate<Throwable> predicate = ThrowableTriDoublePredicate.isEqual(0.0d, 0.0d, 0.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0.0d, 0.0d, 0.0d)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableTriDoublePredicate<Throwable> predicate = ThrowableTriDoublePredicate.isEqual(1.0d, 0.0d, 0.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0d, 0.0d, 0.0d)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableTriDoublePredicate<Throwable> predicate = ThrowableTriDoublePredicate.isEqual(0.0d, 1.0d, 0.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0d, 0.0d, 0.0d)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableTriDoublePredicate<Throwable> predicate = ThrowableTriDoublePredicate.isEqual(0.0d, 0.0d, 1.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0d, 0.0d, 0.0d)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableTriDoublePredicate<Throwable> predicate = ThrowableTriDoublePredicate.isEqual(1.0d, 1.0d, 1.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0.0d, 0.0d, 0.0d)));
    }
}
