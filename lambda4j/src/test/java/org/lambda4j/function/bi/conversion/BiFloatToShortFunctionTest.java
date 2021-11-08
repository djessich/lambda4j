package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiFloatToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiFloatToShortFunction function = BiFloatToShortFunction.of((value1, value2) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiFloatToShortFunction function = BiFloatToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
