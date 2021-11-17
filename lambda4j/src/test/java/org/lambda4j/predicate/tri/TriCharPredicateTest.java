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

class TriCharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriCharPredicate predicate = TriCharPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriCharPredicate predicate = TriCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(TriCharPredicate.call((value1, value2, value3) -> false, 'c', 'c', 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> TriCharPredicate.call(null, 'c', 'c', 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        TriCharPredicate predicate = TriCharPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test('c', 'c', 'c'));
        Assertions.assertFalse(predicate.test('c', 'c', 'c'));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        TriCharPredicate predicate = TriCharPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test('c', 'c', 'c'));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        TriCharPredicate predicate = TriCharPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test('c', 'c', 'c'));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        TriCharPredicate predicate = TriCharPredicate.isEqual('c', 'c', 'c');
        Assertions.assertTrue(predicate.test('c', 'c', 'c'));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        TriCharPredicate predicate = TriCharPredicate.isEqual('d', 'c', 'c');
        Assertions.assertFalse(predicate.test('c', 'c', 'c'));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        TriCharPredicate predicate = TriCharPredicate.isEqual('c', 'd', 'c');
        Assertions.assertFalse(predicate.test('c', 'c', 'c'));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        TriCharPredicate predicate = TriCharPredicate.isEqual('c', 'c', 'd');
        Assertions.assertFalse(predicate.test('c', 'c', 'c'));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        TriCharPredicate predicate = TriCharPredicate.isEqual('d', 'd', 'd');
        Assertions.assertFalse(predicate.test('c', 'c', 'c'));
    }
}
