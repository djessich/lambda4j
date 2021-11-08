package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiByteToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiByteToFloatFunction function = BiByteToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiByteToFloatFunction function = BiByteToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
