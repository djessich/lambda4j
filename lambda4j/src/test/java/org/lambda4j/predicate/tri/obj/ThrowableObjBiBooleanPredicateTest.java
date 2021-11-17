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

class ThrowableObjBiBooleanPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate =
                ThrowableObjBiBooleanPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate = ThrowableObjBiBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBiBooleanPredicate.call((t, value1, value2) -> false, "", true, true));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjBiBooleanPredicate.call((t, value1, value2) -> false, null, true, true));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableObjBiBooleanPredicate.call(null, "", true, true));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate = ThrowableObjBiBooleanPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", false, false));
            Assertions.assertFalse(predicate.testThrows("", false, false));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate = ThrowableObjBiBooleanPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", false, false)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate = ThrowableObjBiBooleanPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", false, false)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate =
                ThrowableObjBiBooleanPredicate.isEqual("", false, false);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", false, false)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate =
                ThrowableObjBiBooleanPredicate.isEqual("first", false, false);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", false, false)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate =
                ThrowableObjBiBooleanPredicate.isEqual("", true, false);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", false, false)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate =
                ThrowableObjBiBooleanPredicate.isEqual("", false, true);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", false, false)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableObjBiBooleanPredicate<String, Throwable> predicate =
                ThrowableObjBiBooleanPredicate.isEqual("other", true, true);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", false, false)));
    }
}
