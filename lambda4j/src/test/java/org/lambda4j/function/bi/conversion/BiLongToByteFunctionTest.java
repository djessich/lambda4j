package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiLongToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiLongToByteFunction function = BiLongToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiLongToByteFunction function = BiLongToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
