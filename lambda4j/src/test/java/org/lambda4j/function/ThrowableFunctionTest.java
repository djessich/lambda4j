package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFunction<String, String, Throwable> function = ThrowableFunction.of(t -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFunction<String, String, Throwable> function = ThrowableFunction.of(null);
        Assertions.assertNull(function);
    }
}
