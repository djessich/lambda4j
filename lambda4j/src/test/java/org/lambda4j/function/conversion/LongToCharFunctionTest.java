package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongToCharFunction function = LongToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongToCharFunction function = LongToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
