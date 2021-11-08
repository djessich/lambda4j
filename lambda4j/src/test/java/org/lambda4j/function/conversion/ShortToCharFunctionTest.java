package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortToCharFunction function = ShortToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortToCharFunction function = ShortToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
