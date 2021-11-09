package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriByteToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriByteToCharFunction function = TriByteToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriByteToCharFunction function = TriByteToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}