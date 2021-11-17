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

class BiObjIntPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjIntPredicate.call((t, u, value) -> false, "", "", 0));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjIntPredicate.call((t, u, value) -> false, null, "", 0));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjIntPredicate.call((t, u, value) -> false, "", null, 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiObjIntPredicate.call(null, "", "", 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", "", 0));
        Assertions.assertFalse(predicate.test("", "", 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", "", 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", "", 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.isEqual("", "", 0);
        Assertions.assertTrue(predicate.test("", "", 0));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.isEqual("first", "", 0);
        Assertions.assertFalse(predicate.test("", "", 0));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.isEqual("", "second", 0);
        Assertions.assertFalse(predicate.test("", "", 0));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.isEqual("", "", 1);
        Assertions.assertFalse(predicate.test("", "", 0));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiObjIntPredicate<String, String> predicate = BiObjIntPredicate.isEqual("other1", "other2", 1);
        Assertions.assertFalse(predicate.test("", "", 0));
    }
}
