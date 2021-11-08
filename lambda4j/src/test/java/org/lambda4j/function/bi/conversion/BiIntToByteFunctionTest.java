package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiIntToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiIntToByteFunction function = BiIntToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiIntToByteFunction function = BiIntToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
