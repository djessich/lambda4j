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

package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriPredicate<String, String, String> predicate = TriPredicate.of((t, u, v) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriPredicate<String, String, String> predicate = TriPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(TriPredicate.call((t, u, v) -> false, "", "", ""));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(TriPredicate.call((t, u, v) -> false, null, "", ""));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(TriPredicate.call((t, u, v) -> false, "", null, ""));
    }

    @Test
    void call_givenNullThirdValue_executesFunctionalInterface() {
        Assertions.assertFalse(TriPredicate.call((t, u, v) -> false, "", "", null));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> TriPredicate.call(null, "", "", ""));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        TriPredicate<String, String, String> predicate = TriPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", "", ""));
        Assertions.assertFalse(predicate.test("", "", ""));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        TriPredicate<String, String, String> predicate = TriPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", "", ""));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        TriPredicate<String, String, String> predicate = TriPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", "", ""));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        TriPredicate<String, String, String> predicate = TriPredicate.isEqual("", "", "");
        Assertions.assertTrue(predicate.test("", "", ""));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        TriPredicate<String, String, String> predicate = TriPredicate.isEqual("first", "", "");
        Assertions.assertFalse(predicate.test("", "", ""));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        TriPredicate<String, String, String> predicate = TriPredicate.isEqual("", "second", "");
        Assertions.assertFalse(predicate.test("", "", ""));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        TriPredicate<String, String, String> predicate = TriPredicate.isEqual("", "", "third");
        Assertions.assertFalse(predicate.test("", "", ""));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        TriPredicate<String, String, String> predicate = TriPredicate.isEqual("other1", "other2", "other3");
        Assertions.assertFalse(predicate.test("", "", ""));
    }
}
