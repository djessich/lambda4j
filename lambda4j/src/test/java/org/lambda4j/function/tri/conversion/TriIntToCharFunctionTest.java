package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriIntToCharFunction function = TriIntToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriIntToCharFunction function = TriIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
