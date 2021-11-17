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

package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiPredicate2Test {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiPredicate2<String, String> predicate = BiPredicate2.of((t, u) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiPredicate2<String, String> predicate = BiPredicate2.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiPredicate2.call((t, u) -> false, "", ""));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiPredicate2.call((t, u) -> false, null, ""));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiPredicate2.call((t, u) -> false, "", null));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiPredicate2.call(null, "", ""));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiPredicate2<String, String> predicate = BiPredicate2.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", ""));
        Assertions.assertFalse(predicate.test("", ""));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiPredicate2<String, String> predicate = BiPredicate2.alwaysTrue();
        Assertions.assertTrue(predicate.test("", ""));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiPredicate2<String, String> predicate = BiPredicate2.alwaysFalse();
        Assertions.assertFalse(predicate.test("", ""));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiPredicate2<String, String> predicate = BiPredicate2.isEqual("", "");
        Assertions.assertTrue(predicate.test("", ""));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiPredicate2<String, String> predicate = BiPredicate2.isEqual("first", "");
        Assertions.assertFalse(predicate.test("", ""));
    }

    @Test
    void isEqual_givenDifferentSecondlue_returnsFalse() {
        BiPredicate2<String, String> predicate = BiPredicate2.isEqual("", "second");
        Assertions.assertFalse(predicate.test("", ""));
    }

    @Test
    void isEqual_givenDifferentValueAll_returnsFalse() {
        BiPredicate2<String, String> predicate = BiPredicate2.isEqual("other1", "other2");
        Assertions.assertFalse(predicate.test("", ""));
    }
}
