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

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import org.lambda4j.consumer.Consumer2;

class Supplier2Test {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = Supplier2.of(() -> "");
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        Supplier2<String> supplier = Supplier2.of(null);
        Assertions.assertNull(supplier);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void lift_givenExpression_returnsFunctionalInterface(String ret) {
        Supplier2<Optional<String>> supplier = Supplier2.lift(() -> ret);
        Assertions.assertNotNull(supplier);
        Optional<String> optional = Assertions.assertDoesNotThrow(supplier::get);
        Assertions.assertNotNull(optional);
        if (ret == null) {
            Assertions.assertFalse(optional.isPresent());
            Assertions.assertThrows(NoSuchElementException.class, optional::get);
        } else {
            Assertions.assertTrue(optional.isPresent());
            Assertions.assertEquals(ret, optional.get());
        }
    }

    @Test
    void lift_givenNull_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> Supplier2.lift(null));
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertEquals("", Supplier2.call(() -> ""));
    }

    @Test
    void call_givenNull_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> Supplier2.call(null));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        Object ret = new Object();
        Supplier2<Object> supplier = Supplier2.constant(ret);
        Assertions.assertSame(ret, supplier.get());
        Assertions.assertEquals(ret, supplier.get());
        Assertions.assertEquals(ret.hashCode(), supplier.get().hashCode());
    }

    @Test
    void constant_givenNull_returnsNull() {
        Supplier2<Object> supplier = Supplier2.constant(null);
        Assertions.assertNull(supplier.get());
    }

    @Test
    void get_givenNothing_executesFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals("", supplier.get()));
    }

    @Test
    void arity_givenNothing_returnsArity() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertEquals(0, supplier.arity());
    }

    @Test
    void andThen_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Supplier2<String> composed = supplier.andThen(str -> str);
        Assertions.assertNotNull(composed);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void andThen_givenExpression_executesFunctionalInterface(String ret) {
        Supplier2<String> supplier = () -> ret;
        Assertions.assertNotNull(supplier);
        Supplier2<String> composed = supplier.andThen(str -> str);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> {
            if (ret == null) {
                Assertions.assertNull(composed.get());
            } else {
                Assertions.assertNotNull(composed.get());
                Assertions.assertEquals("", composed.get());
            }
        });
    }

    @Test
    void andThen_givenNull_throwsException() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThen(null));
    }

    @Test
    void andThenToBoolean_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        BooleanSupplier2 composed = supplier.andThenToBoolean(str -> false);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToBoolean_givenExpression_executesFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        BooleanSupplier2 composed = supplier.andThenToBoolean(str -> false);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(composed.getAsBoolean()));
    }

    @Test
    void andThenToBoolean_givenNull_throwsException() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToBoolean(null));
    }

    @Test
    void andThenToByte_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ByteSupplier composed = supplier.andThenToByte(str -> (byte) 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToByte_givenExpression_executesFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ByteSupplier composed = supplier.andThenToByte(str -> (byte) 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((byte) 0, composed.getAsByte()));
    }

    @Test
    void andThenToByte_givenNull_throwsException() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToByte(null));
    }

    @Test
    void andThenToChar_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        CharSupplier composed = supplier.andThenToChar(str -> 'c');
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToChar_givenExpression_executesFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        CharSupplier composed = supplier.andThenToChar(str -> 'c');
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals('c', composed.getAsChar()));
    }

    @Test
    void andThenToChar_givenNull_throwsException() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToChar(null));
    }

    @Test
    void andThenToDouble_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        DoubleSupplier2 composed = supplier.andThenToDouble(str -> 0.0d);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToDouble_givenExpression_executesFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        DoubleSupplier2 composed = supplier.andThenToDouble(str -> 0.0d);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0.0d, composed.getAsDouble()));
    }

    @Test
    void andThenToDouble_givenNull_throwsException() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToDouble(null));
    }

    @Test
    void andThenToFloat_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        FloatSupplier composed = supplier.andThenToFloat(str -> 0.0f);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToFloat_givenExpression_executesFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        FloatSupplier composed = supplier.andThenToFloat(str -> 0.0f);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0.0f, composed.getAsFloat()));
    }

    @Test
    void andThenToFloat_givenNull_throwsException() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToFloat(null));
    }

    @Test
    void andThenToInt_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        IntSupplier2 composed = supplier.andThenToInt(str -> 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToInt_givenExpression_executesFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        IntSupplier2 composed = supplier.andThenToInt(str -> 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0, composed.getAsInt()));
    }

    @Test
    void andThenToInt_givenNull_throwsException() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToInt(null));
    }

    @Test
    void andThenToLong_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        LongSupplier2 composed = supplier.andThenToLong(str -> 0L);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToLong_givenExpression_executesFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        LongSupplier2 composed = supplier.andThenToLong(str -> 0L);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0L, composed.getAsLong()));
    }

    @Test
    void andThenToLong_givenNull_throwsException() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToLong(null));
    }

    @Test
    void andThenToShort_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ShortSupplier composed = supplier.andThenToShort(str -> (short) 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToShort_givenExpression_executesFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ShortSupplier composed = supplier.andThenToShort(str -> (short) 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((short) 0, composed.getAsShort()));
    }

    @Test
    void andThenToShort_givenNull_throwsException() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToShort(null));
    }

    @Test
    void consume_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Consumer2<Void> consumer = supplier.consume(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void consume_givenExpression_executesFunctionalInterface() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Consumer2<Void> consumer = supplier.consume(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
        Assertions.assertDoesNotThrow(() -> consumer.accept(null));
    }

    @Test
    void consume_givenNull_throwsException() {
        Supplier2<String> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.consume(null));
    }
}
