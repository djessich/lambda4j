package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanToByteFunction function = BooleanToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanToByteFunction function = BooleanToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
