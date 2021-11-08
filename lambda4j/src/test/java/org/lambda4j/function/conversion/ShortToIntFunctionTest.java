package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortToIntFunction function = ShortToIntFunction.of(value -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortToIntFunction function = ShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
