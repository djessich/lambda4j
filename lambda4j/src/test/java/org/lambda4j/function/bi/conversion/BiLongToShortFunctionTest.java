package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiLongToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiLongToShortFunction function = BiLongToShortFunction.of((value1, value2) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiLongToShortFunction function = BiLongToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
