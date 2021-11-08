package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiLongToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiLongToIntFunction function = BiLongToIntFunction.of((value1, value2) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiLongToIntFunction function = BiLongToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
