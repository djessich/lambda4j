package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiByteToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiByteToLongFunction function = BiByteToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiByteToLongFunction function = BiByteToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
