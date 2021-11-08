package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriCharToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriCharToLongFunction function = TriCharToLongFunction.of((value1, value2, value3) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriCharToLongFunction function = TriCharToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
