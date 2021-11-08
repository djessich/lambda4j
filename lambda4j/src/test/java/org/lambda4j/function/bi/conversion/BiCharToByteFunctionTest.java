package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiCharToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiCharToByteFunction function = BiCharToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiCharToByteFunction function = BiCharToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
