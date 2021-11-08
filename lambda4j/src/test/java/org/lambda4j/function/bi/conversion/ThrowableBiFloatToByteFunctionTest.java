package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiFloatToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiFloatToByteFunction<Exception> function =
                ThrowableBiFloatToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiFloatToByteFunction<Exception> function = ThrowableBiFloatToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
