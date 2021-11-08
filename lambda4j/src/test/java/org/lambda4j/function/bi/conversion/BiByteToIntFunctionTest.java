package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiByteToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiByteToIntFunction function = BiByteToIntFunction.of((value1, value2) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiByteToIntFunction function = BiByteToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
