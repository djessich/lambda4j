package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiCharToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiCharToIntFunction function = BiCharToIntFunction.of((value1, value2) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiCharToIntFunction function = BiCharToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
