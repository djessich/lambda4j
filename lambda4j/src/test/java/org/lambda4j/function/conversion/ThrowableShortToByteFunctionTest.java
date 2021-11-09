package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortToByteFunction<Throwable> function = ThrowableShortToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortToByteFunction<Throwable> function = ThrowableShortToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
