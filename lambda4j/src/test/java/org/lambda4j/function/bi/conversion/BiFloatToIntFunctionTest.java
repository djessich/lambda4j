package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiFloatToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiFloatToIntFunction function = BiFloatToIntFunction.of((value1, value2) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiFloatToIntFunction function = BiFloatToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
