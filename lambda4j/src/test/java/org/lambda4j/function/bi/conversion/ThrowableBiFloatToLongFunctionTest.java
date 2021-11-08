package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiFloatToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiFloatToLongFunction<Exception> function = ThrowableBiFloatToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiFloatToLongFunction<Exception> function = ThrowableBiFloatToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
