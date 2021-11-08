package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanToCharFunction function = BooleanToCharFunction.of(value -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanToCharFunction function = BooleanToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
