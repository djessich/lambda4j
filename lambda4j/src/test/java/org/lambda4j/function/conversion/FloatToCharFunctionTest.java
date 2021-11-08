package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatToCharFunction function = FloatToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatToCharFunction function = FloatToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
