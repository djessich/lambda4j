package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriShortToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriShortToCharFunction function = TriShortToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriShortToCharFunction function = TriShortToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
