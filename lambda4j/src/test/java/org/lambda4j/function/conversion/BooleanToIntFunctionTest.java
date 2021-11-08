package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanToIntFunction function = BooleanToIntFunction.of(value -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanToIntFunction function = BooleanToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
