package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatToByteFunction function = FloatToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatToByteFunction function = FloatToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}