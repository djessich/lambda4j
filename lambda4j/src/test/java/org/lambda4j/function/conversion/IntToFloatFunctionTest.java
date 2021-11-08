package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntToFloatFunction function = IntToFloatFunction.of(value -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntToFloatFunction function = IntToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
