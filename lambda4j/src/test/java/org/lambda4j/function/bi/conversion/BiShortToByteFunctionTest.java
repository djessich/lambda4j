package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiShortToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiShortToByteFunction function = BiShortToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiShortToByteFunction function = BiShortToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
