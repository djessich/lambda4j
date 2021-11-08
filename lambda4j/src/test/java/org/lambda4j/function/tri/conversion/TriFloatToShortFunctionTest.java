package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriFloatToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriFloatToShortFunction function = TriFloatToShortFunction.of((value1, value2, value3) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriFloatToShortFunction function = TriFloatToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
