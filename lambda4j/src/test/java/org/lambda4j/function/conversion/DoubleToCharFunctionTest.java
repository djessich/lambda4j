package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleToCharFunction function = DoubleToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleToCharFunction function = DoubleToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
