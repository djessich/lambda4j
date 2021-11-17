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

class BiObjFloatPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjFloatPredicate.call((t, u, value) -> false, "", "", 0.0f));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjFloatPredicate.call((t, u, value) -> false, null, "", 0.0f));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjFloatPredicate.call((t, u, value) -> false, "", null, 0.0f));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiObjFloatPredicate.call(null, "", "", 0.0f));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", "", 0.0f));
        Assertions.assertFalse(predicate.test("", "", 0.0f));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", "", 0.0f));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", "", 0.0f));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.isEqual("", "", 0.0f);
        Assertions.assertTrue(predicate.test("", "", 0.0f));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.isEqual("first", "", 0.0f);
        Assertions.assertFalse(predicate.test("", "", 0.0f));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.isEqual("", "second", 0.0f);
        Assertions.assertFalse(predicate.test("", "", 0.0f));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.isEqual("", "", 1.0f);
        Assertions.assertFalse(predicate.test("", "", 0.0f));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiObjFloatPredicate<String, String> predicate = BiObjFloatPredicate.isEqual("other1", "other2", 1.0f);
        Assertions.assertFalse(predicate.test("", "", 0.0f));
    }
}
