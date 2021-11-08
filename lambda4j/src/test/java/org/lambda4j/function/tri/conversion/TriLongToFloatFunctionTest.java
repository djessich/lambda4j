package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriLongToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriLongToFloatFunction function = TriLongToFloatFunction.of((value1, value2, value3) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriLongToFloatFunction function = TriLongToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
