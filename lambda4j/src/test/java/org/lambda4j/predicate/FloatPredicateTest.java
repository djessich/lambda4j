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

package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatPredicate predicate = FloatPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatPredicate predicate = FloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(FloatPredicate.call(value -> false, 0.0f));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> FloatPredicate.call(null, 0.0f));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        FloatPredicate predicate = FloatPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test(0.0f));
        Assertions.assertFalse(predicate.test(0.0f));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        FloatPredicate predicate = FloatPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test(0.0f));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        FloatPredicate predicate = FloatPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test(0.0f));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        FloatPredicate predicate = FloatPredicate.isEqual(0.0f);
        Assertions.assertTrue(predicate.test(0.0f));
    }

    @Test
    void isEqual_givenDifferent_returnsFalse() {
        FloatPredicate predicate = FloatPredicate.isEqual(1.0f);
        Assertions.assertFalse(predicate.test(0.0f));
    }
}
