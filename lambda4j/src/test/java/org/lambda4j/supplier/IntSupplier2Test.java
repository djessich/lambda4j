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

package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.lambda4j.consumer.Consumer2;

class IntSupplier2Test {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = IntSupplier2.of(() -> Integer.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntSupplier2 supplier = IntSupplier2.of(null);
        Assertions.assertNull(supplier);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertEquals(0, IntSupplier2.call(() -> 0));
    }

    @Test
    void call_givenNull_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> IntSupplier2.call(null));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        int ret = 0;
        IntSupplier2 supplier = IntSupplier2.constant(ret);
        Assertions.assertEquals(ret, supplier.getAsInt());
        Assertions.assertEquals(0, supplier.getAsInt());
    }

    @Test
    void getAsInt_givenNothing_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0, supplier.getAsInt()));
    }

    @Test
    void arity_givenNothing_returnsArity() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertEquals(0, supplier.arity());
    }

    @Test
    void andThen_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Supplier2<String> composed = supplier.andThen(Integer::toString);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThen_givenExpression_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Supplier2<String> composed = supplier.andThen(Integer::toString);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertNotNull(composed.get());
            Assertions.assertEquals("0", composed.get());
        });
    }

    @Test
    void andThen_givenNull_throwsException() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThen(null));
    }

    @Test
    void andThenToBoolean_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        BooleanSupplier2 composed = supplier.andThenToBoolean(value -> false);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToBoolean_givenExpression_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        BooleanSupplier2 composed = supplier.andThenToBoolean(value -> false);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(composed.getAsBoolean()));
    }

    @Test
    void andThenToBoolean_givenNull_throwsException() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToBoolean(null));
    }

    @Test
    void andThenToByte_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        ByteSupplier composed = supplier.andThenToByte(value -> (byte) 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToByte_givenExpression_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        ByteSupplier composed = supplier.andThenToByte(value -> (byte) 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((byte) 0, composed.getAsByte()));
    }

    @Test
    void andThenToByte_givenNull_throwsException() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToByte(null));
    }

    @Test
    void andThenToChar_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        CharSupplier composed = supplier.andThenToChar(value -> 'c');
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToChar_givenExpression_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        CharSupplier composed = supplier.andThenToChar(value -> 'c');
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals('c', composed.getAsChar()));
    }

    @Test
    void andThenToChar_givenNull_throwsException() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToChar(null));
    }

    @Test
    void andThenToDouble_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        DoubleSupplier2 composed = supplier.andThenToDouble(value -> 0.0d);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToDouble_givenExpression_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        DoubleSupplier2 composed = supplier.andThenToDouble(value -> 0.0d);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0.0d, composed.getAsDouble()));
    }

    @Test
    void andThenToDouble_givenNull_throwsException() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToDouble(null));
    }

    @Test
    void andThenToFloat_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        FloatSupplier composed = supplier.andThenToFloat(value -> 0.0f);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToFloat_givenExpression_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        FloatSupplier composed = supplier.andThenToFloat(value -> 0.0f);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0.0f, composed.getAsFloat()));
    }

    @Test
    void andThenToFloat_givenNull_throwsException() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToFloat(null));
    }

    @Test
    void andThenToInt_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        IntSupplier2 composed = supplier.andThenToInt(value -> 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToInt_givenExpression_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        IntSupplier2 composed = supplier.andThenToInt(value -> 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0, composed.getAsInt()));
    }

    @Test
    void andThenToInt_givenNull_throwsException() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToInt(null));
    }

    @Test
    void andThenToLong_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        LongSupplier2 composed = supplier.andThenToLong(value -> 0L);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToLong_givenExpression_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        LongSupplier2 composed = supplier.andThenToLong(value -> 0L);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0L, composed.getAsLong()));
    }

    @Test
    void andThenToLong_givenNull_throwsException() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToLong(null));
    }

    @Test
    void andThenToShort_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        ShortSupplier composed = supplier.andThenToShort(value -> (short) 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToShort_givenExpression_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        ShortSupplier composed = supplier.andThenToShort(value -> (short) 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((short) 0, composed.getAsShort()));
    }

    @Test
    void andThenToShort_givenNull_throwsException() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToShort(null));
    }

    @Test
    void consume_givenExpression_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Consumer2<Void> consumer = supplier.consume(value -> Assertions.assertEquals(0, value));
        Assertions.assertNotNull(consumer);
    }

    @Test
    void consume_givenExpression_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Consumer2<Void> consumer = supplier.consume(value -> Assertions.assertEquals(0, value));
        Assertions.assertNotNull(consumer);
        Assertions.assertDoesNotThrow(() -> consumer.accept(null));
    }

    @Test
    void consume_givenNull_throwsException() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.consume(null));
    }

    @Test
    void boxed_givenNothing_returnsFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Supplier2<Integer> boxed = supplier.boxed();
        Assertions.assertNotNull(boxed);
    }

    @Test
    void boxed_givenNothing_executesFunctionalInterface() {
        IntSupplier2 supplier = () -> 0;
        Assertions.assertNotNull(supplier);
        Supplier2<Integer> boxed = supplier.boxed();
        Assertions.assertNotNull(boxed);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertNotNull(boxed.get());
            Assertions.assertEquals(Integer.valueOf(0), boxed.get());
        });
    }
}
