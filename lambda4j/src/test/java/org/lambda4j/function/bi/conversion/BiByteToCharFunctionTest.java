package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiByteToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiByteToCharFunction function = BiByteToCharFunction.of((value1, value2) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiByteToCharFunction function = BiByteToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
