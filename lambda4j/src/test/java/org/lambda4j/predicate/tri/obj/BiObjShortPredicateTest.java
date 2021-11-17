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

class BiObjShortPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjShortPredicate.call((t, u, value) -> false, "", "", (short) 0));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjShortPredicate.call((t, u, value) -> false, null, "", (short) 0));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjShortPredicate.call((t, u, value) -> false, "", null, (short) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiObjShortPredicate.call(null, "", "", (short) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", "", (short) 0));
        Assertions.assertFalse(predicate.test("", "", (short) 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", "", (short) 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", "", (short) 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.isEqual("", "", (short) 0);
        Assertions.assertTrue(predicate.test("", "", (short) 0));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.isEqual("first", "", (short) 0);
        Assertions.assertFalse(predicate.test("", "", (short) 0));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.isEqual("", "second", (short) 0);
        Assertions.assertFalse(predicate.test("", "", (short) 0));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.isEqual("", "", (short) 1);
        Assertions.assertFalse(predicate.test("", "", (short) 0));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiObjShortPredicate<String, String> predicate = BiObjShortPredicate.isEqual("other1", "other2", (short) 1);
        Assertions.assertFalse(predicate.test("", "", (short) 0));
    }
}
