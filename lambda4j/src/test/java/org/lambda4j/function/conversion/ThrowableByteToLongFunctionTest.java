package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteToLongFunction<Throwable> function = ThrowableByteToLongFunction.of(value -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteToLongFunction<Throwable> function = ThrowableByteToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
