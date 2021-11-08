package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriCharToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriCharToIntFunction function = TriCharToIntFunction.of((value1, value2, value3) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriCharToIntFunction function = TriCharToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
