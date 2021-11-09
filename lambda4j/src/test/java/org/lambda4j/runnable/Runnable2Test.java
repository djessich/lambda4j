package org.lambda4j.runnable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Runnable2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        Runnable2 runnable = Runnable2.of(System.out::println);
        Assertions.assertNotNull(runnable);
    }

    @Test
    void of_givenNull_returnsNull() {
        Runnable2 runnable = Runnable2.of(null);
        Assertions.assertNull(runnable);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Runnable2.call(() -> Assertions.assertNull(null));
    }

    @Test
    void call_givenNull_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> Runnable2.call(null));
    }

    @Test
    void run_givenNothing_executesFunctionalInterface() {
        Runnable2 runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Assertions.assertDoesNotThrow(runnable::run);
    }

    @Test
    void arity_givenNothing_returnsArity() {
        Runnable2 runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Assertions.assertEquals(0, runnable.arity());
    }

    @Test
    void andThen_givenExpression_returnsFunctionalInterface() {
        Runnable2 runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Runnable2 composed = runnable.andThen(() -> Assertions.assertNull(null));
        Assertions.assertNotNull(composed);
    }

    @Test
    void andThen_givenExpression_executesFunctionalInterface() {
        Runnable2 runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Runnable2 composed = runnable.andThen(() -> Assertions.assertNull(null));
        Assertions.assertNotNull(composed);
        Assertions.assertDoesNotThrow(composed::run);
    }

    @Test
    void andThen_givenNull_throwsException() {
        Runnable2 runnable = () -> Assertions.assertNull(null);
        Assertions.assertNotNull(runnable);
        Assertions.assertThrows(NullPointerException.class, () -> runnable.andThen(null));
    }
}
