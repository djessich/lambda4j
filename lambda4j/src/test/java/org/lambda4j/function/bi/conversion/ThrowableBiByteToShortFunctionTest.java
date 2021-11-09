package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiByteToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiByteToShortFunction<Throwable> function =
                ThrowableBiByteToShortFunction.of((value1, value2) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiByteToShortFunction<Throwable> function = ThrowableBiByteToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
