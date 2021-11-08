package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriCharToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriCharToByteFunction function = TriCharToByteFunction.of((value1, value2, value3) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriCharToByteFunction function = TriCharToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
