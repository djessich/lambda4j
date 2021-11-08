package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriFloatToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriFloatToByteFunction function = TriFloatToByteFunction.of((value1, value2, value3) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriFloatToByteFunction function = TriFloatToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
