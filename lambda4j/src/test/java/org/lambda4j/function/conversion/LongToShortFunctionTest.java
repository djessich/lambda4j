package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongToShortFunction function = LongToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongToShortFunction function = LongToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}