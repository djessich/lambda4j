package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntToCharFunction function = IntToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntToCharFunction function = IntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
