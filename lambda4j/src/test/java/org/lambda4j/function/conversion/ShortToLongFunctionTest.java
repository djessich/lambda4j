package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortToLongFunction function = ShortToLongFunction.of(value -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortToLongFunction function = ShortToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
