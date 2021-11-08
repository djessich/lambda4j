package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiByteToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiByteToShortFunction function = BiByteToShortFunction.of((value1, value2) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiByteToShortFunction function = BiByteToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
