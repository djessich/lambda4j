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

class TriLongPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriLongPredicate predicate = TriLongPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriLongPredicate predicate = TriLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(TriLongPredicate.call((value1, value2, value3) -> false, 0L, 0L, 0L));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> TriLongPredicate.call(null, 0L, 0L, 0L));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        TriLongPredicate predicate = TriLongPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test(0L, 0L, 0L));
        Assertions.assertFalse(predicate.test(0L, 0L, 0L));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        TriLongPredicate predicate = TriLongPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test(0L, 0L, 0L));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        TriLongPredicate predicate = TriLongPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test(0L, 0L, 0L));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        TriLongPredicate predicate = TriLongPredicate.isEqual(0L, 0L, 0L);
        Assertions.assertTrue(predicate.test(0L, 0L, 0L));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        TriLongPredicate predicate = TriLongPredicate.isEqual(1L, 0L, 0L);
        Assertions.assertFalse(predicate.test(0L, 0L, 0L));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        TriLongPredicate predicate = TriLongPredicate.isEqual(0L, 1L, 0L);
        Assertions.assertFalse(predicate.test(0L, 0L, 0L));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        TriLongPredicate predicate = TriLongPredicate.isEqual(0L, 0L, 1L);
        Assertions.assertFalse(predicate.test(0L, 0L, 0L));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        TriLongPredicate predicate = TriLongPredicate.isEqual(1L, 1L, 1L);
        Assertions.assertFalse(predicate.test(0L, 0L, 0L));
    }
}
