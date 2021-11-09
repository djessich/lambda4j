package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFloatToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFloatToByteFunction<Throwable> function = ThrowableFloatToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFloatToByteFunction<Throwable> function = ThrowableFloatToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
