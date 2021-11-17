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

class BiShortPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiShortPredicate predicate = BiShortPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiShortPredicate predicate = BiShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiShortPredicate.call((value1, value2) -> false, (short) 0, (short) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiShortPredicate.call(null, (short) 0, (short) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiShortPredicate predicate = BiShortPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test((short) 0, (short) 0));
        Assertions.assertFalse(predicate.test((short) 0, (short) 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiShortPredicate predicate = BiShortPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test((short) 0, (short) 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiShortPredicate predicate = BiShortPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test((short) 0, (short) 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiShortPredicate predicate = BiShortPredicate.isEqual((short) 0, (short) 0);
        Assertions.assertTrue(predicate.test((short) 0, (short) 0));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiShortPredicate predicate = BiShortPredicate.isEqual((short) 1, (short) 0);
        Assertions.assertFalse(predicate.test((short) 0, (short) 0));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiShortPredicate predicate = BiShortPredicate.isEqual((short) 0, (short) 1);
        Assertions.assertFalse(predicate.test((short) 0, (short) 0));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiShortPredicate predicate = BiShortPredicate.isEqual((byte) 1, (byte) 1);
        Assertions.assertFalse(predicate.test((short) 0, (short) 0));
    }
}
