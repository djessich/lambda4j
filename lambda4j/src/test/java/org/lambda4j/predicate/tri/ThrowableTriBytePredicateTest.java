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

class ThrowableTriBytePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriBytePredicate<Throwable> predicate =
                ThrowableTriBytePredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriBytePredicate<Throwable> predicate = ThrowableTriBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(
                ThrowableTriBytePredicate.call((value1, value2, value3) -> false, (byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableTriBytePredicate.call(null, (byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableTriBytePredicate<Throwable> predicate = ThrowableTriBytePredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows((byte) 0, (byte) 0, (byte) 0));
            Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0, (byte) 0));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableTriBytePredicate<Throwable> predicate = ThrowableTriBytePredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows((byte) 0, (byte) 0, (byte) 0)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableTriBytePredicate<Throwable> predicate = ThrowableTriBytePredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableTriBytePredicate<Throwable> predicate =
                ThrowableTriBytePredicate.isEqual((byte) 0, (byte) 0, (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows((byte) 0, (byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableTriBytePredicate<Throwable> predicate =
                ThrowableTriBytePredicate.isEqual((byte) 1, (byte) 0, (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableTriBytePredicate<Throwable> predicate =
                ThrowableTriBytePredicate.isEqual((byte) 0, (byte) 1, (byte) 0);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableTriBytePredicate<Throwable> predicate =
                ThrowableTriBytePredicate.isEqual((byte) 0, (byte) 0, (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0, (byte) 0)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableTriBytePredicate<Throwable> predicate =
                ThrowableTriBytePredicate.isEqual((byte) 1, (byte) 1, (byte) 1);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows((byte) 0, (byte) 0, (byte) 0)));
    }
}
