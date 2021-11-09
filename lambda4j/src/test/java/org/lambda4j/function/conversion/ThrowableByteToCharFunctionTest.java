package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteToCharFunction<Throwable> function = ThrowableByteToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteToCharFunction<Throwable> function = ThrowableByteToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
