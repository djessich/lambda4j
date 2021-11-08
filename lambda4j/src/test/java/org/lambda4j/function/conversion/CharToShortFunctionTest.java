package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharToShortFunction function = CharToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharToShortFunction function = CharToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
