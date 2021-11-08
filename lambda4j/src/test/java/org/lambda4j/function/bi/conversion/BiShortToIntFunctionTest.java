package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiShortToIntFunction function = BiShortToIntFunction.of((value1, value2) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiShortToIntFunction function = BiShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
