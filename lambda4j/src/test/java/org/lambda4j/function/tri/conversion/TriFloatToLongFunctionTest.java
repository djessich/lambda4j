package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriFloatToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriFloatToLongFunction function = TriFloatToLongFunction.of((value1, value2, value3) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriFloatToLongFunction function = TriFloatToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
