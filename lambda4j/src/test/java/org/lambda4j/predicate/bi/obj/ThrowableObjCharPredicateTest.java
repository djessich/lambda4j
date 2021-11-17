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

class ThrowableObjCharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjCharPredicate.call((t, value) -> false, "", 'c'));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableObjCharPredicate.call((t, value) -> false, null, 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableObjCharPredicate.call(null, "", 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", 'c'));
            Assertions.assertFalse(predicate.testThrows("", 'c'));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", 'c')));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 'c')));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.isEqual("", 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", 'c')));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.isEqual("first", 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 'c')));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.isEqual("", 'd');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 'c')));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableObjCharPredicate<String, Throwable> predicate = ThrowableObjCharPredicate.isEqual("other", 'd');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", 'c')));
    }
}
