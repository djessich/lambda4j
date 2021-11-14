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
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import org.lambda4j.consumer.ThrowableConsumer;
import org.lambda4j.exception.ThrownByFunctionalInterfaceException;

class ThrowableSupplierTest {

    private static Stream<Arguments> generateSourcesForGetThrowsMethodTest() {
        return Stream.of(
                Arguments.arguments(new Error("error to be thrown in getThrows()")),
                Arguments.arguments(new Exception("exception to be thrown in getThrows()")),
                Arguments.arguments(new RuntimeException("runtime exception to be thrown in getThrows()"))
        );
    }

    private static Stream<Arguments> generateSourcesForGetMethodTest() {
        return Stream.of(
                Arguments.arguments(new Error("error to be thrown in get()")),
                Arguments.arguments(new Exception("exception to be thrown in get()")),
                Arguments.arguments(new RuntimeException("runtime exception to be thrown in get()"))
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
        ThrowableSupplier<String, Throwable> supplier = ThrowableSupplier.of(() -> "");
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableSupplier<String, Throwable> supplier = ThrowableSupplier.of(null);
        Assertions.assertNull(supplier);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void lift_givenExpression_returnsFunctionalInterface(String ret) {
        ThrowableSupplier<Optional<String>, Throwable> supplier = ThrowableSupplier.lift(() -> ret);
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
        Assertions.assertEquals("", ThrowableSupplier.call(() -> ""));
    }

    @Test
    void call_givenNull_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableSupplier.call(null));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        Object ret = new Object();
        ThrowableSupplier<Object, Throwable> supplier = ThrowableSupplier.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertSame(ret, supplier.getThrows());
            Assertions.assertEquals(ret, supplier.getThrows());
            Assertions.assertEquals(ret.hashCode(), supplier.getThrows().hashCode());
        });
    }

    @Test
    void constant_givenNull_returnsNull() {
        ThrowableSupplier<Object, Throwable> supplier = ThrowableSupplier.constant(null);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertNull(supplier.getThrows());
        });
    }

    @Test
    void getThrows_givenNothing_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals("", supplier.getThrows()));
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForGetThrowsMethodTest")
    void getThrows_givenNothing_throwsThrowable(Throwable expected) {
        ThrowableSupplier<String, Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        Throwable thrown = Assertions.assertThrows(expected.getClass(), supplier::getThrows);
        Assertions.assertEquals(expected.getClass(), thrown.getClass());
        Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
    }

    @Test
    void get_givenNothing_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals("", supplier.get()));
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForGetMethodTest")
    void get_givenNothing_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableSupplier<String, Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        if (expected instanceof Error) {
            Throwable thrown = Assertions.assertThrows(expected.getClass(), supplier::get);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            Throwable thrown = Assertions.assertThrows(ThrownByFunctionalInterfaceException.class, supplier::get);
            Assertions.assertEquals(ThrownByFunctionalInterfaceException.class, thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
            Assertions.assertEquals(expected.getClass(), thrown.getCause().getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getCause().getMessage());
        }
    }

    @Test
    void arity_givenNothing_returnsArity() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertEquals(0, supplier.arity());
    }

    @Test
    void andThen_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableSupplier<String, Throwable> composed = supplier.andThen(str -> str);
        Assertions.assertNotNull(composed);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void andThen_givenExpression_executesFunctionalInterface(String ret) {
        ThrowableSupplier<String, Throwable> supplier = () -> ret;
        Assertions.assertNotNull(supplier);
        ThrowableSupplier<String, Throwable> composed = supplier.andThen(str -> str);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> {
            if (ret == null) {
                Assertions.assertNull(composed.get());
            } else {
                Assertions.assertNotNull(composed.get());
                Assertions.assertEquals("", composed.getThrows());
            }
        });
    }

    @Test
    void andThen_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThen(null));
    }

    @Test
    void andThenToBoolean_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableBooleanSupplier<Throwable> composed = supplier.andThenToBoolean(str -> false);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToBoolean_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableBooleanSupplier<Throwable> composed = supplier.andThenToBoolean(str -> false);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(composed.getAsBooleanThrows()));
    }

    @Test
    void andThenToBoolean_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToBoolean(null));
    }

    @Test
    void andThenToByte_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableByteSupplier<Throwable> composed = supplier.andThenToByte(str -> (byte) 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToByte_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableByteSupplier<Throwable> composed = supplier.andThenToByte(str -> (byte) 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((byte) 0, composed.getAsByteThrows()));
    }

    @Test
    void andThenToByte_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToByte(null));
    }

    @Test
    void andThenToChar_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableCharSupplier<Throwable> composed = supplier.andThenToChar(str -> 'c');
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToChar_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableCharSupplier<Throwable> composed = supplier.andThenToChar(str -> 'c');
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals('c', composed.getAsCharThrows()));
    }

    @Test
    void andThenToChar_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToChar(null));
    }

    @Test
    void andThenToDouble_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableDoubleSupplier<Throwable> composed = supplier.andThenToDouble(str -> 0.0d);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToDouble_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableDoubleSupplier<Throwable> composed = supplier.andThenToDouble(str -> 0.0d);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0.0d, composed.getAsDoubleThrows()));
    }

    @Test
    void andThenToDouble_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToDouble(null));
    }

    @Test
    void andThenToFloat_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableFloatSupplier<Throwable> composed = supplier.andThenToFloat(str -> 0.0f);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToFloat_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableFloatSupplier<Throwable> composed = supplier.andThenToFloat(str -> 0.0f);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0.0f, composed.getAsFloatThrows()));
    }

    @Test
    void andThenToFloat_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToFloat(null));
    }

    @Test
    void andThenToInt_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableIntSupplier<Throwable> composed = supplier.andThenToInt(str -> 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToInt_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableIntSupplier<Throwable> composed = supplier.andThenToInt(str -> 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0, composed.getAsIntThrows()));
    }

    @Test
    void andThenToInt_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToInt(null));
    }

    @Test
    void andThenToLong_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableLongSupplier<Throwable> composed = supplier.andThenToLong(str -> 0L);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToLong_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableLongSupplier<Throwable> composed = supplier.andThenToLong(str -> 0L);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(0L, composed.getAsLongThrows()));
    }

    @Test
    void andThenToLong_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToLong(null));
    }

    @Test
    void andThenToShort_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableShortSupplier<Throwable> composed = supplier.andThenToShort(str -> (short) 0);
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThenToShort_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableShortSupplier<Throwable> composed = supplier.andThenToShort(str -> (short) 0);
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals((short) 0, composed.getAsShortThrows()));
    }

    @Test
    void andThenToShort_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.andThenToShort(null));
    }

    @Test
    void consume_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableConsumer<Void, Throwable> consumer = supplier.consume(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void consume_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        ThrowableConsumer<Void, Throwable> consumer = supplier.consume(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
        Assertions.assertDoesNotThrow(() -> consumer.accept(null));
    }

    @Test
    void consume_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.consume(null));
    }

    @Test
    void nest_givenNothing_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Supplier2<String> nested = supplier.nest();
        Assertions.assertNotNull(nested);
    }

    @Test
    void nest_givenNothing_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Supplier2<String> nested = supplier.nest();
        Assertions.assertNotNull(nested);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals("", nested.get()));
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForNestNoArgMethodTest")
    void nest_givenNothing_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableSupplier<String, Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        Supplier2<String> nested = supplier.nest();
        Assertions.assertNotNull(nested);
        if (expected instanceof Error) {
            Throwable thrown = Assertions.assertThrows(expected.getClass(), nested::get);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            Throwable thrown = Assertions.assertThrows(ThrownByFunctionalInterfaceException.class, nested::get);
            Assertions.assertEquals(ThrownByFunctionalInterfaceException.class, thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
            Assertions.assertEquals(expected.getClass(), thrown.getCause().getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getCause().getMessage());
        }
    }

    @Test
    void nest_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Supplier2<String> nested = supplier.nest(throwable -> new RuntimeException(throwable.getMessage(), throwable));
        Assertions.assertNotNull(nested);
    }

    @Test
    void nest_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Supplier2<String> nested = supplier.nest(throwable -> new RuntimeException(throwable.getMessage(), throwable));
        Assertions.assertNotNull(nested);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals("", nested.get()));
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForNestWithArgumentMethodTest")
    void nest_givenExpression_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableSupplier<String, Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        Supplier2<String> nested = supplier.nest(throwable -> new RuntimeException(throwable.getMessage(), throwable));
        Assertions.assertNotNull(nested);
        if (expected instanceof Error) {
            Throwable thrown = Assertions.assertThrows(expected.getClass(), nested::get);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            Throwable thrown = Assertions.assertThrows(RuntimeException.class, nested::get);
            Assertions.assertEquals(RuntimeException.class, thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
            Assertions.assertEquals(expected.getClass(), thrown.getCause().getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getCause().getMessage());
        }
    }

    @Test
    void nest_givenExpressionReturningNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> {
            throw new RuntimeException();
        };
        Assertions.assertNotNull(supplier);
        Supplier2<String> nested = supplier.nest(throwable -> null);
        Assertions.assertNotNull(nested);
        Assertions.assertThrows(NullPointerException.class, nested::get);
    }

    @Test
    void nest_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.nest(null));
    }

    @Test
    void recover_givenExpression_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Supplier2<String> recovered = supplier.recover(throwable -> () -> {
            Assertions.assertNotNull(throwable);
            return "";
        });
        Assertions.assertNotNull(recovered);
    }

    @Test
    void recover_givenExpression_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Supplier2<String> recovered = supplier.recover(throwable -> () -> {
            Assertions.assertNotNull(throwable);
            return "";
        });
        Assertions.assertNotNull(recovered);
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals("", recovered.get()));
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForRecoverMethodTest")
    void recover_givenExpression_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableSupplier<String, Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        if (expected instanceof Error) {
            Supplier2<String> recovered = supplier.recover(throwable -> () -> Assertions.fail("recover was executed"));
            Assertions.assertNotNull(recovered);
            Throwable thrown = Assertions.assertThrows(expected.getClass(), recovered::get);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            Supplier2<String> recovered = supplier.recover(throwable -> () -> {
                Assertions.assertNotNull(throwable);
                Assertions.assertEquals(expected.getClass(), throwable.getClass());
                Assertions.assertEquals(expected.getMessage(), throwable.getMessage());
                return "";
            });
            Assertions.assertNotNull(recovered);
            Assertions.assertDoesNotThrow(() -> Assertions.assertEquals("", recovered.get()));
        }
    }

    @Test
    void recover_givenExpressionReturningNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> {
            throw new RuntimeException();
        };
        Assertions.assertNotNull(supplier);
        Supplier2<String> recovered = supplier.recover(throwable -> null);
        Assertions.assertNotNull(recovered);
        Throwable thrown = Assertions.assertThrows(NullPointerException.class, recovered::get);
        Assertions.assertEquals(NullPointerException.class, thrown.getClass());
        Assertions.assertEquals("recover returned null for class java.lang.RuntimeException: null",
                thrown.getMessage());
    }

    @Test
    void recover_givenNull_throwsException() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Assertions.assertThrows(NullPointerException.class, () -> supplier.recover(null));
    }

    @Test
    void sneakyThrow_givenNothing_returnsFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Supplier2<String> sneakyThrowable = supplier.sneakyThrow();
        Assertions.assertNotNull(sneakyThrowable);
    }

    @Test
    void sneakyThrow_givenNothing_executesFunctionalInterface() {
        ThrowableSupplier<String, Throwable> supplier = () -> "";
        Assertions.assertNotNull(supplier);
        Supplier2<String> sneakyThrowable = supplier.sneakyThrow();
        Assertions.assertNotNull(sneakyThrowable);
        Assertions.assertDoesNotThrow(sneakyThrowable::get);
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForSneakyThrowMethodTest")
    void sneakyThrow_givenNothing_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableSupplier<String, Throwable> supplier = () -> {
            throw expected;
        };
        Assertions.assertNotNull(supplier);
        Supplier2<String> sneakyThrowable = supplier.sneakyThrow();
        Assertions.assertNotNull(sneakyThrowable);
        Throwable thrown = Assertions.assertThrows(expected.getClass(), sneakyThrowable::get);
        Assertions.assertEquals(expected.getClass(), thrown.getClass());
        Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
    }
}
