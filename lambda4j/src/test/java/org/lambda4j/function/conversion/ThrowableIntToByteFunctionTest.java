package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntToByteFunction<Throwable> function = ThrowableIntToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntToByteFunction<Throwable> function = ThrowableIntToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
