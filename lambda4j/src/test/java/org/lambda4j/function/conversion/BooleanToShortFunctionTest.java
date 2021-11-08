package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanToShortFunction function = BooleanToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanToShortFunction function = BooleanToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
