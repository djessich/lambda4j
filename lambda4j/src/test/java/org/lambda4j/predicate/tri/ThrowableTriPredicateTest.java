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

class ThrowableTriPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate =
                ThrowableTriPredicate.of((t, u, v) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate = ThrowableTriPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableTriPredicate.call((t, u, v) -> false, "", "", ""));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableTriPredicate.call((t, u, v) -> false, null, "", ""));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableTriPredicate.call((t, u, v) -> false, "", null, ""));
    }

    @Test
    void call_givenNullThirdValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableTriPredicate.call((t, u, v) -> false, "", "", null));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableTriPredicate.call(null, "", "", ""));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableTriPredicate<String, String, String, Throwable> predicate = ThrowableTriPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", "", ""));
            Assertions.assertFalse(predicate.testThrows("", "", ""));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate = ThrowableTriPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", "")));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate = ThrowableTriPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", "")));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate = ThrowableTriPredicate.isEqual("", "", "");
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", "")));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate =
                ThrowableTriPredicate.isEqual("first", "", "");
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", "")));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate =
                ThrowableTriPredicate.isEqual("", "second", "");
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", "")));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate =
                ThrowableTriPredicate.isEqual("", "", "third");
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", "")));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableTriPredicate<String, String, String, Throwable> predicate =
                ThrowableTriPredicate.isEqual("other1", "other2", "other3");
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", "")));
    }
}
