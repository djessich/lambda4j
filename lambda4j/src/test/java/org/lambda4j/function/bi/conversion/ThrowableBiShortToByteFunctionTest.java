package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiShortToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortToByteFunction<Throwable> function =
                ThrowableBiShortToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortToByteFunction<Throwable> function = ThrowableBiShortToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
