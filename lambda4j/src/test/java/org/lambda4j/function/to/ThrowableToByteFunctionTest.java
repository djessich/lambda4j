package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToByteFunction<String, Throwable> function = ThrowableToByteFunction.of(Byte::parseByte);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToByteFunction<String, Throwable> function = ThrowableToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
