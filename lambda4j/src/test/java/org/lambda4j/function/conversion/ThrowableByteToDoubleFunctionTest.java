package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteToDoubleFunction<Throwable> function = ThrowableByteToDoubleFunction.of(value -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteToDoubleFunction<Throwable> function = ThrowableByteToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
