package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharToDoubleFunction function = CharToDoubleFunction.of(value -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharToDoubleFunction function = CharToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
