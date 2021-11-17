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

class BiDoublePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiDoublePredicate predicate = BiDoublePredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiDoublePredicate predicate = BiDoublePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiDoublePredicate.call((value1, value2) -> false, 0.0d, 0.0d));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiDoublePredicate.call(null, 0.0d, 0.0d));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiDoublePredicate predicate = BiDoublePredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test(0.0d, 0.0d));
        Assertions.assertFalse(predicate.test(0.0d, 0.0d));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiDoublePredicate predicate = BiDoublePredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test(0.0d, 0.0d));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiDoublePredicate predicate = BiDoublePredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test(0.0d, 0.0d));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiDoublePredicate predicate = BiDoublePredicate.isEqual(0.0d, 0.0d);
        Assertions.assertTrue(predicate.test(0.0d, 0.0d));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiDoublePredicate predicate = BiDoublePredicate.isEqual(1.0d, 0.0d);
        Assertions.assertFalse(predicate.test(0.0d, 0.0d));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiDoublePredicate predicate = BiDoublePredicate.isEqual(0.0d, 1.0d);
        Assertions.assertFalse(predicate.test(0.0d, 0.0d));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiDoublePredicate predicate = BiDoublePredicate.isEqual(1.0d, 1.0d);
        Assertions.assertFalse(predicate.test(0.0d, 0.0d));
    }
}
