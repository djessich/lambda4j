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

class BytePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BytePredicate predicate = BytePredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BytePredicate predicate = BytePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BytePredicate.call(value -> false, (byte) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BytePredicate.call(null, (byte) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BytePredicate predicate = BytePredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test((byte) 0));
        Assertions.assertFalse(predicate.test((byte) 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BytePredicate predicate = BytePredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test((byte) 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BytePredicate predicate = BytePredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test((byte) 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BytePredicate predicate = BytePredicate.isEqual((byte) 0);
        Assertions.assertTrue(predicate.test((byte) 0));
    }

    @Test
    void isEqual_givenDifferent_returnsFalse() {
        BytePredicate predicate = BytePredicate.isEqual((byte) 1);
        Assertions.assertFalse(predicate.test((byte) 0));
    }
}
