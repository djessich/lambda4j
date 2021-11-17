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

class BiObjBytePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjBytePredicate.call((t, u, value) -> false, "", "", (byte) 0));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjBytePredicate.call((t, u, value) -> false, null, "", (byte) 0));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjBytePredicate.call((t, u, value) -> false, "", null, (byte) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiObjBytePredicate.call(null, "", "", (byte) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", "", (byte) 0));
        Assertions.assertFalse(predicate.test("", "", (byte) 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", "", (byte) 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", "", (byte) 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.isEqual("", "", (byte) 0);
        Assertions.assertTrue(predicate.test("", "", (byte) 0));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.isEqual("first", "", (byte) 0);
        Assertions.assertFalse(predicate.test("", "", (byte) 0));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.isEqual("", "second", (byte) 0);
        Assertions.assertFalse(predicate.test("", "", (byte) 0));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.isEqual("", "", (byte) 1);
        Assertions.assertFalse(predicate.test("", "", (byte) 0));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiObjBytePredicate<String, String> predicate = BiObjBytePredicate.isEqual("other1", "other2", (byte) 1);
        Assertions.assertFalse(predicate.test("", "", (byte) 0));
    }
}
