package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleToByteFunction function = DoubleToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleToByteFunction function = DoubleToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
