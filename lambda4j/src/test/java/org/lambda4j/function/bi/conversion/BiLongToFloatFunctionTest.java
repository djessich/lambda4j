package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiLongToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiLongToFloatFunction function = BiLongToFloatFunction.of((value1, value2) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiLongToFloatFunction function = BiLongToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
