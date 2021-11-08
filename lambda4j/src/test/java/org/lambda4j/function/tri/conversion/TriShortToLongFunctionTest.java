package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriShortToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriShortToLongFunction function = TriShortToLongFunction.of((value1, value2, value3) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriShortToLongFunction function = TriShortToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
