package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanToLongFunction function = BooleanToLongFunction.of(value -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanToLongFunction function = BooleanToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
