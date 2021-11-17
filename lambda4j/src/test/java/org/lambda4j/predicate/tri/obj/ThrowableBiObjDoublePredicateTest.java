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

package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoublePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate =
                ThrowableBiObjDoublePredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate = ThrowableBiObjDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjDoublePredicate.call((t, u, value) -> false, "", "", 0.0d));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjDoublePredicate.call((t, u, value) -> false, null, "", 0.0d));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjDoublePredicate.call((t, u, value) -> false, "", null, 0.0d));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableBiObjDoublePredicate.call(null, "", "", 0.0d));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate =
                ThrowableBiObjDoublePredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", "", 0.0d));
            Assertions.assertFalse(predicate.testThrows("", "", 0.0d));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate = ThrowableBiObjDoublePredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", 0.0d)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate =
                ThrowableBiObjDoublePredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0.0d)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate =
                ThrowableBiObjDoublePredicate.isEqual("", "", 0.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", 0.0d)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate =
                ThrowableBiObjDoublePredicate.isEqual("first", "", 0.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0.0d)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate =
                ThrowableBiObjDoublePredicate.isEqual("", "second", 0.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0.0d)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate =
                ThrowableBiObjDoublePredicate.isEqual("", "", 1.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0.0d)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableBiObjDoublePredicate<String, String, Throwable> predicate =
                ThrowableBiObjDoublePredicate.isEqual("other1", "other2", 1.0d);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0.0d)));
    }
}
