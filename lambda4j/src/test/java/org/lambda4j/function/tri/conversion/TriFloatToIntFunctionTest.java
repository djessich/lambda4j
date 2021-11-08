package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriFloatToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriFloatToIntFunction function = TriFloatToIntFunction.of((value1, value2, value3) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriFloatToIntFunction function = TriFloatToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
