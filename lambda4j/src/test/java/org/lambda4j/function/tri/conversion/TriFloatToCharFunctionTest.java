package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriFloatToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriFloatToCharFunction function = TriFloatToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriFloatToCharFunction function = TriFloatToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
