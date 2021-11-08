package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiIntToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiIntToLongFunction function = BiIntToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiIntToLongFunction function = BiIntToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
