package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatToShortFunction function = FloatToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatToShortFunction function = FloatToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
