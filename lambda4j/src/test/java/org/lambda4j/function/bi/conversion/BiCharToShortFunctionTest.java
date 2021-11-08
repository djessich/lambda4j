package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiCharToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiCharToShortFunction function = BiCharToShortFunction.of((value1, value2) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiCharToShortFunction function = BiCharToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
