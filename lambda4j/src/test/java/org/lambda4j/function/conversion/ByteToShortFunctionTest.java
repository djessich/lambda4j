package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ByteToShortFunction function = ByteToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ByteToShortFunction function = ByteToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
