package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleToShortFunction function = DoubleToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleToShortFunction function = DoubleToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
