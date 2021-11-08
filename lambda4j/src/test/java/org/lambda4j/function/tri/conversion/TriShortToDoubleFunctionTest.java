package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriShortToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriShortToDoubleFunction function = TriShortToDoubleFunction.of((value1, value2, value3) -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriShortToDoubleFunction function = TriShortToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
