package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToFloatFunction<String> function = ToFloatFunction.of(Float::parseFloat);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToFloatFunction<String> function = ToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
