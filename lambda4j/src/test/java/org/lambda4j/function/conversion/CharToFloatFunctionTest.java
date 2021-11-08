package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharToFloatFunction function = CharToFloatFunction.of(value -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharToFloatFunction function = CharToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
