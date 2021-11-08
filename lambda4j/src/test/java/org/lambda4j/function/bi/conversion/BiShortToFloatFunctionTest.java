package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiShortToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiShortToFloatFunction function = BiShortToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiShortToFloatFunction function = BiShortToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
