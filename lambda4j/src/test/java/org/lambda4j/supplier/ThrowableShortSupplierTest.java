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

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.lambda4j.consumer.ThrowableConsumer;
import org.lambda4j.exception.ThrownByFunctionalInterfaceException;

class ThrowableShortSupplierTest {

    private static Stream<Arguments> generateSourcesForGetThrowsMethodTest() {
        return Stream.of(
                Arguments.arguments(new Error("error to be thrown in getThrows()")),
                Arguments.arguments(new Exception("exception to be thrown in getThrows()")),
                Arguments.arguments(new RuntimeException("runtime exception to be thrown in getThrows()"))
        );
    }

    private static Stream<Arguments> generateSourcesForNestNoArgMethodTest() {
        return Stream.of(
                Arguments.arguments(new Error("error to be thrown in nest()")),
                Arguments.arguments(new Exception("exception to be thrown in nest()")),
                Arguments.arguments(new RuntimeException("runtime exception to be thrown in nest()"))
        );
    }

    private static Stream<Arguments> generateSourcesForNestWithArgumentMethodTest() {
        return Stream.of(
                Arguments.arguments(new Error("error to be thrown in nest(Function)")),
                Arguments.arguments(new Exception("exception to be thrown in nest(Function)")),
                Arguments.arguments(new RuntimeException("runtime exception to be thrown in nest(Function)"))
        );
    }

    private static Stream<Arguments> generateSourcesForRecoverMethodTest() {
        return Stream.of(
                Arguments.arguments(new Error("error to be thrown in recover(Function)")),
                Arguments.arguments(new Exception("exception to be thrown in recover(Function)")),
                Arguments.arguments(new RuntimeException("runtime exception to be thrown in recover(Function)"))
        );
    }

    private static Stream<Arguments> generateSourcesForSneakyThrowMethodTest() {
        return Stream.of(
                Arguments.arguments(new Error("error to be thrown in sneakyThrow()")),
                Arguments.arguments(new Exception("exception to be thrown in sneakyThrow()")),
                Arguments.arguments(new RuntimeException("runtime exception to be thrown in sneakyThrow()"))
        );
    }

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = ThrowableShortSupplier.of(() -> Short.MIN_VALUE);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortSupplier<Throwable> supplier = ThrowableShortSupplier.of(null);
        Assertions.assertNull(supplier);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertEquals((short) 0, ThrowableShortSupplier.call(() -> (short) 0));
    }

    @Test
    void call_givenNull_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableShortSupplier.call(null));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        short ret = 0;
        ThrowableShortSupplier<Throwable> supplier = ThrowableShortSupplier.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, supplier.getAsShortThrows());
            Assertions.assertEquals((short) 0, supplier.getAsShortThrows());
        });
    }

    @Test
    void getAsShortThrows_givenNothing_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((short) 0, supplier.getAsShortThrows()));
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForGetThrowsMethodTest")
    void getAsShortThrows_givenNothing_throwsThrowable(Throwable expected) {
        ThrowableShortSupplier<Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        Throwable thrown = Assertions.assertThrows(expected.getClass(), supplier::getAsShortThrows);
        Assertions.assertEquals(expected.getClass(), thrown.getClass());
        Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
    }

    @Test
    void arity_givenNothing_returnsArity() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertEquals(0, supplier.arity());
    }

    @Test
    void andThen_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableSupplier<String, Throwable> composed = supplier.andThen(Short::toString);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThen_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableSupplier<String, Throwable> composed = supplier.andThen(Short::toString);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertNotNull(composed.getThrows());
            Assertions.assertEquals("0", composed.getThrows());
        });
    }

    @Test
    void andThen_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThen(null));
    }

    @Test
    void andThenToBoolean_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableBooleanSupplier<Throwable> composed = supplier.andThenToBoolean(value -> false);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToBoolean_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableBooleanSupplier<Throwable> composed = supplier.andThenToBoolean(value -> false);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(composed.getAsBooleanThrows()));
    }

    @Test
    void andThenToBoolean_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToBoolean(null));
    }

    @Test
    void andThenToByte_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableByteSupplier<Throwable> composed = supplier.andThenToByte(value -> (byte) 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToByte_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableByteSupplier<Throwable> composed = supplier.andThenToByte(value -> (byte) 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((byte) 0, composed.getAsByteThrows()));
    }

    @Test
    void andThenToByte_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToByte(null));
    }

    @Test
    void andThenToChar_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableCharSupplier<Throwable> composed = supplier.andThenToChar(value -> 'c');
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToChar_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableCharSupplier<Throwable> composed = supplier.andThenToChar(value -> 'c');
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals('c', composed.getAsCharThrows()));
    }

    @Test
    void andThenToChar_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToChar(null));
    }

    @Test
    void andThenToDouble_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableDoubleSupplier<Throwable> composed = supplier.andThenToDouble(value -> 0.0d);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToDouble_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableDoubleSupplier<Throwable> composed = supplier.andThenToDouble(value -> 0.0d);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0.0d, composed.getAsDoubleThrows()));
    }

    @Test
    void andThenToDouble_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToDouble(null));
    }

    @Test
    void andThenToFloat_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableFloatSupplier<Throwable> composed = supplier.andThenToFloat(value -> 0.0f);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToFloat_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableFloatSupplier<Throwable> composed = supplier.andThenToFloat(value -> 0.0f);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0.0f, composed.getAsFloatThrows()));
    }

    @Test
    void andThenToFloat_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToFloat(null));
    }

    @Test
    void andThenToInt_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableIntSupplier<Throwable> composed = supplier.andThenToInt(value -> 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToInt_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableIntSupplier<Throwable> composed = supplier.andThenToInt(value -> 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0, composed.getAsIntThrows()));
    }

    @Test
    void andThenToInt_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToInt(null));
    }

    @Test
    void andThenToLong_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableLongSupplier<Throwable> composed = supplier.andThenToLong(value -> 0L);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToLong_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableLongSupplier<Throwable> composed = supplier.andThenToLong(value -> 0L);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0L, composed.getAsLongThrows()));
    }

    @Test
    void andThenToLong_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToLong(null));
    }

    @Test
    void andThenToShort_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableShortSupplier<Throwable> composed = supplier.andThenToShort(value -> value);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToShort_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableShortSupplier<Throwable> composed = supplier.andThenToShort(value -> value);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((short) 0, composed.getAsShortThrows()));
    }

    @Test
    void andThenToShort_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToShort(null));
    }

    @Test
    void consume_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableConsumer<Void, Throwable> consumer =
                supplier.consume(value -> Assertions.assertEquals((short) 0, value));
        Assertions.assertNotNull(consumer);
    }

    @Test
    void consume_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableConsumer<Void, Throwable> consumer =
                supplier.consume(value -> Assertions.assertEquals((short) 0, value));
        Assertions.assertNotNull(consumer);
        Assertions.assertDoesNotThrow(() -> consumer.acceptThrows(null));
    }

    @Test
    void consume_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.consume(null));
    }

    @Test
    void boxed_givenNothing_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableSupplier<Short, Throwable> boxed = supplier.boxed();
        Assertions.assertNotNull(boxed);
    }

    @Test
    void boxed_givenNothing_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ThrowableSupplier<Short, Throwable> boxed = supplier.boxed();
        Assertions.assertNotNull(boxed);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertNotNull(boxed.getThrows());
            Assertions.assertEquals(Short.valueOf((short) 0), boxed.getThrows());
        });
    }

    @Test
    void nest_givenNothing_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ShortSupplier nested = supplier.nest();
        Assertions.assertNotNull(nested);
    }

    @Test
    void nest_givenNothing_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ShortSupplier nested = supplier.nest();
        Assertions.assertNotNull(nested);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((short) 0, nested.getAsShort()));
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForNestNoArgMethodTest")
    void nest_givenNothing_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableShortSupplier<Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        ShortSupplier nested = supplier.nest();
        Assertions.assertNotNull(nested);
        if (expected instanceof Error) {
            Throwable thrown = Assertions.assertThrows(expected.getClass(), nested::getAsShort);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            Throwable thrown = Assertions.assertThrows(ThrownByFunctionalInterfaceException.class, nested::getAsShort);
            Assertions.assertEquals(ThrownByFunctionalInterfaceException.class, thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
            Assertions.assertEquals(expected.getClass(), thrown.getCause().getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getCause().getMessage());
        }
    }

    @Test
    void nest_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ShortSupplier nested = supplier.nest(throwable -> new RuntimeException(throwable.getMessage(), throwable));
        Assertions.assertNotNull(nested);
    }

    @Test
    void nest_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ShortSupplier nested = supplier.nest(throwable -> new RuntimeException(throwable.getMessage(), throwable));
        Assertions.assertNotNull(nested);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((short) 0, nested.getAsShort()));
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForNestWithArgumentMethodTest")
    void nest_givenExpression_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableShortSupplier<Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        ShortSupplier nested = supplier.nest(throwable -> new RuntimeException(throwable.getMessage(), throwable));
        Assertions.assertNotNull(nested);
        if (expected instanceof Error) {
            Throwable thrown = Assertions.assertThrows(expected.getClass(), nested::getAsShort);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            Throwable thrown = Assertions.assertThrows(RuntimeException.class, nested::getAsShort);
            Assertions.assertEquals(RuntimeException.class, thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
            Assertions.assertEquals(expected.getClass(), thrown.getCause().getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getCause().getMessage());
        }
    }

    @Test
    void nest_givenExpressionReturningNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> {
            throw new RuntimeException();
        };
        Assertions.assertNotNull(supplier);
        ShortSupplier nested = supplier.nest(throwable -> null);
        Assertions.assertNotNull(nested);
        Assertions.assertThrows(NullPointerException.class, nested::getAsShort);
    }

    @Test
    void nest_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.nest(null));
    }

    @Test
    void recover_givenExpression_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ShortSupplier recovered = supplier.recover(throwable -> () -> {
            Assertions.assertNotNull(throwable);
            return (short) 0;
        });
        Assertions.assertNotNull(recovered);
    }

    @Test
    void recover_givenExpression_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ShortSupplier recovered = supplier.recover(throwable -> () -> {
            Assertions.assertNotNull(throwable);
            return (short) 0;
        });
        Assertions.assertNotNull(recovered);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((short) 0, recovered.getAsShort()));
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForRecoverMethodTest")
    void recover_givenExpression_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableShortSupplier<Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        if (expected instanceof Error) {
            ShortSupplier recovered = supplier.recover(throwable -> () -> Assertions.fail("recover was executed"));
            Assertions.assertNotNull(recovered);
            Throwable thrown = Assertions.assertThrows(expected.getClass(), recovered::getAsShort);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            ShortSupplier recovered = supplier.recover(throwable -> () -> {
                Assertions.assertNotNull(throwable);
                Assertions.assertEquals(expected.getClass(), throwable.getClass());
                Assertions.assertEquals(expected.getMessage(), throwable.getMessage());
                return (short) 0;
            });
            Assertions.assertNotNull(recovered);
            Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((short) 0, recovered.getAsShort()));
        }
    }

    @Test
    void recover_givenExpressionReturningNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> {
            throw new RuntimeException();
        };
        Assertions.assertNotNull(supplier);
        ShortSupplier recovered = supplier.recover(throwable -> null);
        Assertions.assertNotNull(recovered);
        Throwable thrown = Assertions.assertThrows(NullPointerException.class, recovered::getAsShort);
        Assertions.assertEquals(NullPointerException.class, thrown.getClass());
        Assertions.assertEquals("recover returned null for class java.lang.RuntimeException: null",
                thrown.getMessage());
    }

    @Test
    void recover_givenNull_throwsException() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.recover(null));
    }

    @Test
    void sneakyThrow_givenNothing_returnsFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ShortSupplier sneakyThrowable = supplier.sneakyThrow();
        Assertions.assertNotNull(sneakyThrowable);
    }

    @Test
    void sneakyThrow_givenNothing_executesFunctionalInterface() {
        ThrowableShortSupplier<Throwable> supplier = () -> (short) 0;
        Assertions.assertNotNull(supplier);
        ShortSupplier sneakyThrowable = supplier.sneakyThrow();
        Assertions.assertNotNull(sneakyThrowable);
        Assertions.assertDoesNotThrow(sneakyThrowable::getAsShort);
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForSneakyThrowMethodTest")
    void sneakyThrow_givenNothing_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableShortSupplier<Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        ShortSupplier sneakyThrowable = supplier.sneakyThrow();
        Assertions.assertNotNull(sneakyThrowable);
        Throwable thrown = Assertions.assertThrows(expected.getClass(), sneakyThrowable::getAsShort);
        Assertions.assertEquals(expected.getClass(), thrown.getClass());
        Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
    }
}
