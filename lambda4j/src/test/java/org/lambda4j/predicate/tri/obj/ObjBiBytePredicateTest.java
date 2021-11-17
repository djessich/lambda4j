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

class ObjBiBytePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ObjBiBytePredicate.call((t, value1, value2) -> false, "", (byte) 0, (byte) 0));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ObjBiBytePredicate.call((t, value1, value2) -> false, null, (byte) 0, (byte) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ObjBiBytePredicate.call(null, "", (byte) 0, (byte) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", (byte) 0, (byte) 0));
        Assertions.assertFalse(predicate.test("", (byte) 0, (byte) 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", (byte) 0, (byte) 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", (byte) 0, (byte) 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.isEqual("", (byte) 0, (byte) 0);
        Assertions.assertTrue(predicate.test("", (byte) 0, (byte) 0));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.isEqual("first", (byte) 0, (byte) 0);
        Assertions.assertFalse(predicate.test("", (byte) 0, (byte) 0));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.isEqual("", (byte) 1, (byte) 0);
        Assertions.assertFalse(predicate.test("", (byte) 0, (byte) 0));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.isEqual("", (byte) 0, (byte) 1);
        Assertions.assertFalse(predicate.test("", (byte) 0, (byte) 0));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ObjBiBytePredicate<String> predicate = ObjBiBytePredicate.isEqual("other", (byte) 1, (byte) 1);
        Assertions.assertFalse(predicate.test("", (byte) 0, (byte) 0));
    }
}
