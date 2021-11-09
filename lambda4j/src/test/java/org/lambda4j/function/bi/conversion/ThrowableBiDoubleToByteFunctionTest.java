package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiDoubleToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiDoubleToByteFunction<Throwable> function =
                ThrowableBiDoubleToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiDoubleToByteFunction<Throwable> function = ThrowableBiDoubleToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
