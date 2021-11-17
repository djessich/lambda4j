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

class ObjDoublePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ObjDoublePredicate.call((t, value) -> false, "", 0.0d));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ObjDoublePredicate.call((t, value) -> false, null, 0.0d));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ObjDoublePredicate.call(null, "", 0.0d));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", 0.0d));
        Assertions.assertFalse(predicate.test("", 0.0d));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", 0.0d));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", 0.0d));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.isEqual("", 0.0d);
        Assertions.assertTrue(predicate.test("", 0.0d));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.isEqual("first", 0.0d);
        Assertions.assertFalse(predicate.test("", 0.0d));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.isEqual("", 1.0d);
        Assertions.assertFalse(predicate.test("", 0.0d));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ObjDoublePredicate<String> predicate = ObjDoublePredicate.isEqual("other", 1.0d);
        Assertions.assertFalse(predicate.test("", 0.0d));
    }
}
