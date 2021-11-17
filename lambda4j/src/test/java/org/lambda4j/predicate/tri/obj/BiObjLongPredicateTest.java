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

class BiObjLongPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjLongPredicate.call((t, u, value) -> false, "", "", 0L));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjLongPredicate.call((t, u, value) -> false, null, "", 0L));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjLongPredicate.call((t, u, value) -> false, "", null, 0L));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiObjLongPredicate.call(null, "", "", 0L));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", "", 0L));
        Assertions.assertFalse(predicate.test("", "", 0L));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", "", 0L));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", "", 0L));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.isEqual("", "", 0L);
        Assertions.assertTrue(predicate.test("", "", 0L));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.isEqual("first", "", 0L);
        Assertions.assertFalse(predicate.test("", "", 0L));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.isEqual("", "second", 0L);
        Assertions.assertFalse(predicate.test("", "", 0L));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.isEqual("", "", 1L);
        Assertions.assertFalse(predicate.test("", "", 0L));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiObjLongPredicate<String, String> predicate = BiObjLongPredicate.isEqual("other1", "other2", 1L);
        Assertions.assertFalse(predicate.test("", "", 0L));
    }
}
