package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriIntToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriIntToByteFunction function = TriIntToByteFunction.of((value1, value2, value3) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriIntToByteFunction function = TriIntToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
