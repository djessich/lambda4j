package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriByteToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriByteToShortFunction function = TriByteToShortFunction.of((value1, value2, value3) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriByteToShortFunction function = TriByteToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
