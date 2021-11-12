package org.lambda4j.runnable;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.lambda4j.exception.ThrownByFunctionalInterfaceException;

class ThrowableRunnableTest {
    private static Stream<Arguments> generateSourcesForRunThrowsMethodTest() {
        return Stream.of(
                Arguments.arguments(new Error("error to be thrown in runThrows()")),
                Arguments.arguments(new Exception("exception to be thrown in runThrows()")),
                Arguments.arguments(new RuntimeException("runtime exception to be thrown in runThrows()"))
        );
    }

    private static Stream<Arguments> generateSourcesForRunMethodTest() {
        return Stream.of(
                Arguments.arguments(new Error("error to be thrown in run()")),
                Arguments.arguments(new Exception("exception to be thrown in run()")),
                Arguments.arguments(new RuntimeException("runtime exception to be thrown in run()()"))
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
        ThrowableRunnable<Throwable> runnable = ThrowableRunnable.of(System.out::println);
        Assertions.assertNotNull(runnable);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableRunnable<Throwable> runnable = ThrowableRunnable.of(null);
        Assertions.assertNull(runnable);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        ThrowableRunnable.call(() -> Assertions.assertNull(null));
    }

    @Test
    void call_givenNull_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableRunnable.call(null));
    }

    @Test
    void runThrows_givenNothing_executesFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Assertions.assertDoesNotThrow(runnable::runThrows);
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForRunThrowsMethodTest")
    void runThrows_givenNothing_throwsThrowable(Throwable expected) {
        ThrowableRunnable<Throwable> runnable = () -> {
            throw expected;
        };
        Assertions.assertNotNull(runnable);
        Throwable thrown = Assertions.assertThrows(expected.getClass(), runnable::runThrows);
        Assertions.assertEquals(expected.getClass(), thrown.getClass());
        Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
    }

    @Test
    void run_givenNothing_executesFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Assertions.assertDoesNotThrow(runnable::run);
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForRunMethodTest")
    void run_givenNothing_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableRunnable<Throwable> runnable = () -> {
            throw expected;
        };
        Assertions.assertNotNull(runnable);
        if (expected instanceof Error) {
            Throwable thrown = Assertions.assertThrows(expected.getClass(), runnable::run);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            Throwable thrown = Assertions.assertThrows(ThrownByFunctionalInterfaceException.class, runnable::run);
            Assertions.assertEquals(ThrownByFunctionalInterfaceException.class, thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
            Assertions.assertEquals(expected.getClass(), thrown.getCause().getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getCause().getMessage());
        }
    }

    @Test
    void arity_givenNothing_returnsArity() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Assertions.assertEquals(0, runnable.arity());
    }

    @Test
    void andThen_givenExpression_returnsFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        ThrowableRunnable<Throwable> composed = runnable.andThen(() -> Assertions.assertNull(null));
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThen_givenExpression_executesFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        ThrowableRunnable<Throwable> composed = runnable.andThen(() -> Assertions.assertNull(null));
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(composed::runThrows);
    }

    @Test
    void andThen_givenNull_throwsException() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Assertions.assertThrows(NullPointerException.class, () -> runnable.andThen(null));
    }

    @Test
    void nest_givenNothing_returnsFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Runnable2 nested = runnable.nest();
        Assertions.assertNotNull(nested);
    }

    @Test
    void nest_givenNothing_executesFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Runnable2 nested = runnable.nest();
        Assertions.assertNotNull(nested);
        Assertions.assertDoesNotThrow(nested::run);
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForNestNoArgMethodTest")
    void nest_givenNothing_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableRunnable<Throwable> runnable = () -> {
            throw expected;
        };
        Assertions.assertNotNull(runnable);
        Runnable2 nested = runnable.nest();
        Assertions.assertNotNull(nested);
        if (expected instanceof Error) {
            Throwable thrown = Assertions.assertThrows(expected.getClass(), nested::run);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            Throwable thrown = Assertions.assertThrows(ThrownByFunctionalInterfaceException.class, nested::run);
            Assertions.assertEquals(ThrownByFunctionalInterfaceException.class, thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
            Assertions.assertEquals(expected.getClass(), thrown.getCause().getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getCause().getMessage());
        }
    }

    @Test
    void nest_givenExpression_returnsFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Runnable2 nested = runnable.nest(throwable -> new RuntimeException(throwable.getMessage(), throwable));
        Assertions.assertNotNull(nested);
    }

    @Test
    void nest_givenExpression_executesFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Runnable2 nested = runnable.nest(throwable -> new RuntimeException(throwable.getMessage(), throwable));
        Assertions.assertNotNull(nested);
        Assertions.assertDoesNotThrow(nested::run);
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForNestWithArgumentMethodTest")
    void nest_givenExpression_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableRunnable<Throwable> runnable = () -> {
            throw expected;
        };
        Assertions.assertNotNull(runnable);
        Runnable2 nested = runnable.nest(throwable -> new RuntimeException(throwable.getMessage(), throwable));
        Assertions.assertNotNull(nested);
        if (expected instanceof Error) {
            Throwable thrown = Assertions.assertThrows(expected.getClass(), nested::run);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            Throwable thrown = Assertions.assertThrows(RuntimeException.class, nested::run);
            Assertions.assertEquals(RuntimeException.class, thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
            Assertions.assertEquals(expected.getClass(), thrown.getCause().getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getCause().getMessage());
        }
    }

    @Test
    void nest_givenExpressionReturningNull_throwsException() {
        ThrowableRunnable<Throwable> runnable = () -> {
            throw new RuntimeException();
        };
        Assertions.assertNotNull(runnable);
        Runnable2 nested = runnable.nest(throwable -> null);
        Assertions.assertNotNull(nested);
        Assertions.assertThrows(NullPointerException.class, nested::run);
    }

    @Test
    void nest_givenNull_throwsException() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Assertions.assertThrows(NullPointerException.class, () -> runnable.nest(null));
    }

    @Test
    void recover_givenExpression_returnsFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Runnable2 recovered = runnable.recover(throwable -> () -> Assertions.assertNotNull(throwable));
        Assertions.assertNotNull(recovered);
    }

    @Test
    void recover_givenExpression_executesFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Runnable2 recovered = runnable.recover(throwable -> () -> Assertions.assertNotNull(throwable));
        Assertions.assertNotNull(recovered);
        Assertions.assertDoesNotThrow(recovered::run);
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForRecoverMethodTest")
    void recover_givenExpression_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableRunnable<Throwable> runnable = () -> {
            throw expected;
        };
        Assertions.assertNotNull(runnable);
        if (expected instanceof Error) {
            Runnable2 recovered = runnable.recover(throwable -> () -> Assertions.fail("recover was executed"));
            Assertions.assertNotNull(recovered);
            Throwable thrown = Assertions.assertThrows(expected.getClass(), recovered::run);
            Assertions.assertEquals(expected.getClass(), thrown.getClass());
            Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
        } else {
            Runnable2 recovered = runnable.recover(throwable -> () -> {
                Assertions.assertNotNull(throwable);
                Assertions.assertEquals(expected.getClass(), throwable.getClass());
                Assertions.assertEquals(expected.getMessage(), throwable.getMessage());
            });
            Assertions.assertNotNull(recovered);
            Assertions.assertDoesNotThrow(recovered::run);
        }
    }

    @Test
    void recover_givenExpressionReturningNull_throwsException() {
        ThrowableRunnable<Throwable> runnable = () -> {
            throw new RuntimeException();
        };
        Assertions.assertNotNull(runnable);
        Runnable2 recovered = runnable.recover(throwable -> null);
        Assertions.assertNotNull(recovered);
        Throwable thrown = Assertions.assertThrows(NullPointerException.class, recovered::run);
        Assertions.assertEquals(NullPointerException.class, thrown.getClass());
        Assertions.assertEquals("recover returned null for class java.lang.RuntimeException: null",
                thrown.getMessage());
    }

    @Test
    void recover_givenNull_throwsException() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Assertions.assertThrows(NullPointerException.class, () -> runnable.recover(null));
    }

    @Test
    void sneakyThrow_givenNothing_returnsFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Runnable2 sneakyThrowable = runnable.sneakyThrow();
        Assertions.assertNotNull(sneakyThrowable);
    }

    @Test
    void sneakyThrow_givenNothing_executesFunctionalInterface() {
        ThrowableRunnable<Throwable> runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Runnable2 sneakyThrowable = runnable.sneakyThrow();
        Assertions.assertNotNull(sneakyThrowable);
        Assertions.assertDoesNotThrow(sneakyThrowable::run);
    }

    @ParameterizedTest
    @MethodSource("generateSourcesForSneakyThrowMethodTest")
    void sneakyThrow_givenNothing_executesFunctionalInterfaceThrowing(Throwable expected) {
        ThrowableRunnable<Throwable> runnable = () -> {
            throw expected;
        };
        Assertions.assertNotNull(runnable);
        Runnable2 sneakyThrowable = runnable.sneakyThrow();
        Assertions.assertNotNull(sneakyThrowable);
        Throwable thrown = Assertions.assertThrows(expected.getClass(), sneakyThrowable::run);
        Assertions.assertEquals(expected.getClass(), thrown.getClass());
        Assertions.assertEquals(expected.getMessage(), thrown.getMessage());
    }
}
