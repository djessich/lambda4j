package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiCharToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiCharToLongFunction function = BiCharToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiCharToLongFunction function = BiCharToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
