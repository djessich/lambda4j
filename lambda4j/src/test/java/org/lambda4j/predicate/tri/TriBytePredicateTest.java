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

class TriBytePredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriBytePredicate predicate = TriBytePredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriBytePredicate predicate = TriBytePredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(TriBytePredicate.call((value1, value2, value3) -> false, (byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> TriBytePredicate.call(null, (byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        TriBytePredicate predicate = TriBytePredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test((byte) 0, (byte) 0, (byte) 0));
        Assertions.assertFalse(predicate.test((byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        TriBytePredicate predicate = TriBytePredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test((byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        TriBytePredicate predicate = TriBytePredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test((byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        TriBytePredicate predicate = TriBytePredicate.isEqual((byte) 0, (byte) 0, (byte) 0);
        Assertions.assertTrue(predicate.test((byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        TriBytePredicate predicate = TriBytePredicate.isEqual((byte) 1, (byte) 0, (byte) 0);
        Assertions.assertFalse(predicate.test((byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        TriBytePredicate predicate = TriBytePredicate.isEqual((byte) 0, (byte) 1, (byte) 0);
        Assertions.assertFalse(predicate.test((byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        TriBytePredicate predicate = TriBytePredicate.isEqual((byte) 0, (byte) 0, (byte) 1);
        Assertions.assertFalse(predicate.test((byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        TriBytePredicate predicate = TriBytePredicate.isEqual((byte) 1, (byte) 1, (byte) 1);
        Assertions.assertFalse(predicate.test((byte) 0, (byte) 0, (byte) 0));
    }
}
