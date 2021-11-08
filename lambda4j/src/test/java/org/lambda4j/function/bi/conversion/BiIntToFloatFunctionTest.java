package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiIntToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiIntToFloatFunction function = BiIntToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiIntToFloatFunction function = BiIntToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
