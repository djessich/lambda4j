package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriCharToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriCharToDoubleFunction function = TriCharToDoubleFunction.of((value1, value2, value3) -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriCharToDoubleFunction function = TriCharToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
