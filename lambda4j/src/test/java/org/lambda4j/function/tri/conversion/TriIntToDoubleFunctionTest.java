package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriIntToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriIntToDoubleFunction function = TriIntToDoubleFunction.of((value1, value2, value3) -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriIntToDoubleFunction function = TriIntToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
