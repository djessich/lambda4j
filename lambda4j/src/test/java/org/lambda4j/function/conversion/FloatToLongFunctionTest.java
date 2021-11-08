package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatToLongFunction function = FloatToLongFunction.of(value -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatToLongFunction function = FloatToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
