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

class ObjIntPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjIntPredicate<String> predicate = ObjIntPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjIntPredicate<String> predicate = ObjIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ObjIntPredicate.call((t, value) -> false, "", 0));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ObjIntPredicate.call((t, value) -> false, null, 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ObjIntPredicate.call(null, "", 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ObjIntPredicate<String> predicate = ObjIntPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", 0));
        Assertions.assertFalse(predicate.test("", 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ObjIntPredicate<String> predicate = ObjIntPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ObjIntPredicate<String> predicate = ObjIntPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ObjIntPredicate<String> predicate = ObjIntPredicate.isEqual("", 0);
        Assertions.assertTrue(predicate.test("", 0));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ObjIntPredicate<String> predicate = ObjIntPredicate.isEqual("first", 0);
        Assertions.assertFalse(predicate.test("", 0));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ObjIntPredicate<String> predicate = ObjIntPredicate.isEqual("", 1);
        Assertions.assertFalse(predicate.test("", 0));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ObjIntPredicate<String> predicate = ObjIntPredicate.isEqual("other", 1);
        Assertions.assertFalse(predicate.test("", 0));
    }
}
