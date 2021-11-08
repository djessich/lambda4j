package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriLongToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriLongToCharFunction function = TriLongToCharFunction.of((value1, value2, value3) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriLongToCharFunction function = TriLongToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
