package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortToByteFunction function = ShortToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortToByteFunction function = ShortToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
