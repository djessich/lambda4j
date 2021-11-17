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

class BiObjBooleanPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjBooleanPredicate.call((t, u, value) -> false, "", "", true));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjBooleanPredicate.call((t, u, value) -> false, null, "", true));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjBooleanPredicate.call((t, u, value) -> false, "", null, true));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiObjBooleanPredicate.call(null, "", "", true));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", "", false));
        Assertions.assertFalse(predicate.test("", "", false));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", "", false));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", "", false));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.isEqual("", "", false);
        Assertions.assertTrue(predicate.test("", "", false));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.isEqual("first", "", false);
        Assertions.assertFalse(predicate.test("", "", false));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.isEqual("", "second", false);
        Assertions.assertFalse(predicate.test("", "", false));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.isEqual("", "", true);
        Assertions.assertFalse(predicate.test("", "", false));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiObjBooleanPredicate<String, String> predicate = BiObjBooleanPredicate.isEqual("other1", "other2", true);
        Assertions.assertFalse(predicate.test("", "", false));
    }
}
