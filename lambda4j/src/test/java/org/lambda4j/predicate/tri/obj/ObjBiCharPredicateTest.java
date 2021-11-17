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

class ObjBiCharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ObjBiCharPredicate.call((t, value1, value2) -> false, "", 'c', 'c'));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ObjBiCharPredicate.call((t, value1, value2) -> false, null, 'c', 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ObjBiCharPredicate.call(null, "", 'c', 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", 'c', 'c'));
        Assertions.assertFalse(predicate.test("", 'c', 'c'));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", 'c', 'c'));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", 'c', 'c'));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.isEqual("", 'c', 'c');
        Assertions.assertTrue(predicate.test("", 'c', 'c'));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.isEqual("first", 'c', 'c');
        Assertions.assertFalse(predicate.test("", 'c', 'c'));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.isEqual("", 'd', 'c');
        Assertions.assertFalse(predicate.test("", 'c', 'c'));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.isEqual("", 'c', 'd');
        Assertions.assertFalse(predicate.test("", 'c', 'c'));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ObjBiCharPredicate<String> predicate = ObjBiCharPredicate.isEqual("other", 'd', 'd');
        Assertions.assertFalse(predicate.test("", 'c', 'c'));
    }
}
