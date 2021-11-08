package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriShortToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriShortToFloatFunction function = TriShortToFloatFunction.of((value1, value2, value3) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriShortToFloatFunction function = TriShortToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
