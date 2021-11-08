package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiBooleanToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiBooleanToFloatFunction function = BiBooleanToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiBooleanToFloatFunction function = BiBooleanToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
