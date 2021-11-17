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

class ShortPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortPredicate predicate = ShortPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortPredicate predicate = ShortPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ShortPredicate.call(value -> false, (short) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ShortPredicate.call(null, (short) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ShortPredicate predicate = ShortPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test((short) 0));
        Assertions.assertFalse(predicate.test((short) 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ShortPredicate predicate = ShortPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test((short) 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ShortPredicate predicate = ShortPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test((short) 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ShortPredicate predicate = ShortPredicate.isEqual((short) 0);
        Assertions.assertTrue(predicate.test((short) 0));
    }

    @Test
    void isEqual_givenDifferent_returnsFalse() {
        ShortPredicate predicate = ShortPredicate.isEqual((short) 1);
        Assertions.assertFalse(predicate.test((short) 0));
    }
}
