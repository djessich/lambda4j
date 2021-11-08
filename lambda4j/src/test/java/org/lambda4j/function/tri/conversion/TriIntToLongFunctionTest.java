package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriIntToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriIntToLongFunction function = TriIntToLongFunction.of((value1, value2, value3) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriIntToLongFunction function = TriIntToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
