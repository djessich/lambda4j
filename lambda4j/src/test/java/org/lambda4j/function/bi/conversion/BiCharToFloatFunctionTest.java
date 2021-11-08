package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiCharToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiCharToFloatFunction function = BiCharToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiCharToFloatFunction function = BiCharToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
