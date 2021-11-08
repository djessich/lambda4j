package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriShortToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriShortToByteFunction function = TriShortToByteFunction.of((value1, value2, value3) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriShortToByteFunction function = TriShortToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
