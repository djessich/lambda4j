package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharToLongFunction<Throwable> function = ThrowableCharToLongFunction.of(value -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharToLongFunction<Throwable> function = ThrowableCharToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
