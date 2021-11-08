package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiIntToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiIntToShortFunction function = BiIntToShortFunction.of((value1, value2) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiIntToShortFunction function = BiIntToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
