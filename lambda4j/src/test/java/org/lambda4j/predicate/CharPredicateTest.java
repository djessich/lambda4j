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

class CharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharPredicate predicate = CharPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharPredicate predicate = CharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(CharPredicate.call(value -> false, 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> CharPredicate.call(null, 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        CharPredicate predicate = CharPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test('c'));
        Assertions.assertFalse(predicate.test('c'));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        CharPredicate predicate = CharPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test('c'));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        CharPredicate predicate = CharPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test('c'));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        CharPredicate predicate = CharPredicate.isEqual('c');
        Assertions.assertTrue(predicate.test('c'));
    }

    @Test
    void isEqual_givenDifferent_returnsFalse() {
        CharPredicate predicate = CharPredicate.isEqual('d');
        Assertions.assertFalse(predicate.test('c'));
    }
}
