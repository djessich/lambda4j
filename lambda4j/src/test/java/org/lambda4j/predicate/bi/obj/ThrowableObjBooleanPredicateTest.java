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

package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBooleanPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanPredicate<String, Throwable> predicate =
                ThrowableObjBooleanPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanPredicate<String, Throwable> predicate = ThrowableObjBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBooleanPredicate.call((t, value) -> false, "", true));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBooleanPredicate.call((t, value) -> false, null, true));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableObjBooleanPredicate.call(null, "", true));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableObjBooleanPredicate<String, Throwable> predicate = ThrowableObjBooleanPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", false));
            Assertions.assertFalse(predicate.testThrows("", false));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableObjBooleanPredicate<String, Throwable> predicate = ThrowableObjBooleanPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", false)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableObjBooleanPredicate<String, Throwable> predicate = ThrowableObjBooleanPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", false)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableObjBooleanPredicate<String, Throwable> predicate = ThrowableObjBooleanPredicate.isEqual("", false);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", false)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableObjBooleanPredicate<String, Throwable> predicate =
                ThrowableObjBooleanPredicate.isEqual("first", false);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", false)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableObjBooleanPredicate<String, Throwable> predicate = ThrowableObjBooleanPredicate.isEqual("", true);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", false)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableObjBooleanPredicate<String, Throwable> predicate = ThrowableObjBooleanPredicate.isEqual("other", true);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", false)));
    }
}
