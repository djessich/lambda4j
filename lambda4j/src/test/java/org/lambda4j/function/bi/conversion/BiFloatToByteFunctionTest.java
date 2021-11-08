package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiFloatToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiFloatToByteFunction function = BiFloatToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiFloatToByteFunction function = BiFloatToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
