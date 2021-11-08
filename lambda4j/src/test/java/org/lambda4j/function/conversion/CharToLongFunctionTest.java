package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharToLongFunction function = CharToLongFunction.of(value -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharToLongFunction function = CharToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
