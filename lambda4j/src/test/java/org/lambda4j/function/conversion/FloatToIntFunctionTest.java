package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatToIntFunction function = FloatToIntFunction.of(value -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatToIntFunction function = FloatToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
