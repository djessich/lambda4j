package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriShortToIntFunction function = TriShortToIntFunction.of((value1, value2, value3) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriShortToIntFunction function = TriShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
