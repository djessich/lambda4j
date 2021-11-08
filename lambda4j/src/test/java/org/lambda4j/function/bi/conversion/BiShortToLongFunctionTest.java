package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiShortToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiShortToLongFunction function = BiShortToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiShortToLongFunction function = BiShortToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
