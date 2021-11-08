package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongToByteFunction function = LongToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongToByteFunction function = LongToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
