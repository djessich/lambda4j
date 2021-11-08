package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntToShortFunction function = IntToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntToShortFunction function = IntToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
