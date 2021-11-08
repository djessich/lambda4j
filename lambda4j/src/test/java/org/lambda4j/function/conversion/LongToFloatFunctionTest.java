package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongToFloatFunction function = LongToFloatFunction.of(value -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongToFloatFunction function = LongToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
