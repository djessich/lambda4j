package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiFloatToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiFloatToLongFunction function = BiFloatToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiFloatToLongFunction function = BiFloatToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
