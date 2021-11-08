package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriLongToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriLongToIntFunction function = TriLongToIntFunction.of((value1, value2, value3) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriLongToIntFunction function = TriLongToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
