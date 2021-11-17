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

class ObjBiBooleanPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ObjBiBooleanPredicate.call((t, value1, value2) -> false, "", true, true));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ObjBiBooleanPredicate.call((t, value1, value2) -> false, null, true, true));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ObjBiBooleanPredicate.call(null, "", true, true));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", false, false));
        Assertions.assertFalse(predicate.test("", false, false));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", false, false));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", false, false));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.isEqual("", false, false);
        Assertions.assertTrue(predicate.test("", false, false));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.isEqual("first", false, false);
        Assertions.assertFalse(predicate.test("", false, false));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.isEqual("", true, false);
        Assertions.assertFalse(predicate.test("", false, false));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.isEqual("", false, true);
        Assertions.assertFalse(predicate.test("", false, false));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ObjBiBooleanPredicate<String> predicate = ObjBiBooleanPredicate.isEqual("other", true, true);
        Assertions.assertFalse(predicate.test("", false, false));
    }
}
