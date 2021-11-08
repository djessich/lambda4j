package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriByteToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriByteToLongFunction function = TriByteToLongFunction.of((value1, value2, value3) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriByteToLongFunction function = TriByteToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
