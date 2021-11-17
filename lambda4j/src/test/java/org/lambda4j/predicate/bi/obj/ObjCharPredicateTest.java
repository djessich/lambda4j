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

class ObjCharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjCharPredicate<String> predicate = ObjCharPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjCharPredicate<String> predicate = ObjCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ObjCharPredicate.call((t, value) -> false, "", 'c'));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ObjCharPredicate.call((t, value) -> false, null, 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ObjCharPredicate.call(null, "", 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ObjCharPredicate<String> predicate = ObjCharPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", 'c'));
        Assertions.assertFalse(predicate.test("", 'c'));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ObjCharPredicate<String> predicate = ObjCharPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", 'c'));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ObjCharPredicate<String> predicate = ObjCharPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", 'c'));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ObjCharPredicate<String> predicate = ObjCharPredicate.isEqual("", 'c');
        Assertions.assertTrue(predicate.test("", 'c'));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ObjCharPredicate<String> predicate = ObjCharPredicate.isEqual("first", 'c');
        Assertions.assertFalse(predicate.test("", 'c'));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ObjCharPredicate<String> predicate = ObjCharPredicate.isEqual("", 'd');
        Assertions.assertFalse(predicate.test("", 'c'));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ObjCharPredicate<String> predicate = ObjCharPredicate.isEqual("other", 'd');
        Assertions.assertFalse(predicate.test("", 'c'));
    }
}
