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

class ObjFloatPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ObjFloatPredicate.call((t, value) -> false, "", 0.0f));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ObjFloatPredicate.call((t, value) -> false, null, 0.0f));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ObjFloatPredicate.call(null, "", 0.0f));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", 0.0f));
        Assertions.assertFalse(predicate.test("", 0.0f));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", 0.0f));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", 0.0f));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.isEqual("", 0.0f);
        Assertions.assertTrue(predicate.test("", 0.0f));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.isEqual("first", 0.0f);
        Assertions.assertFalse(predicate.test("", 0.0f));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.isEqual("", 1.0f);
        Assertions.assertFalse(predicate.test("", 0.0f));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ObjFloatPredicate<String> predicate = ObjFloatPredicate.isEqual("other", 1.0f);
        Assertions.assertFalse(predicate.test("", 0.0f));
    }
}
