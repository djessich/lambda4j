package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiByteToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiByteToIntFunction<Throwable> function = ThrowableBiByteToIntFunction.of((value1, value2) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiByteToIntFunction<Throwable> function = ThrowableBiByteToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
