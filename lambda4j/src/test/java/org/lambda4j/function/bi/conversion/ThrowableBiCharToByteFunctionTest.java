package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiCharToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiCharToByteFunction<Throwable> function =
                ThrowableBiCharToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiCharToByteFunction<Throwable> function = ThrowableBiCharToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
