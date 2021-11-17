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

class BiFloatPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiFloatPredicate predicate = BiFloatPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiFloatPredicate predicate = BiFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiFloatPredicate.call((value1, value2) -> false, 0.0f, 0.0f));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiFloatPredicate.call(null, 0.0f, 0.0f));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiFloatPredicate predicate = BiFloatPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test(0.0f, 0.0f));
        Assertions.assertFalse(predicate.test(0.0f, 0.0f));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiFloatPredicate predicate = BiFloatPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test(0.0f, 0.0f));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiFloatPredicate predicate = BiFloatPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test(0.0f, 0.0f));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiFloatPredicate predicate = BiFloatPredicate.isEqual(0.0f, 0.0f);
        Assertions.assertTrue(predicate.test(0.0f, 0.0f));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiFloatPredicate predicate = BiFloatPredicate.isEqual(1.0f, 0.0f);
        Assertions.assertFalse(predicate.test(0.0f, 0.0f));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiFloatPredicate predicate = BiFloatPredicate.isEqual(0.0f, 1.0f);
        Assertions.assertFalse(predicate.test(0.0f, 0.0f));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiFloatPredicate predicate = BiFloatPredicate.isEqual(1.0f, 1.0f);
        Assertions.assertFalse(predicate.test(0.0f, 0.0f));
    }
}
