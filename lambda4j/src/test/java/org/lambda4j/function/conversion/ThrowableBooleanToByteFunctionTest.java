package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanToByteFunction<Throwable> function = ThrowableBooleanToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanToByteFunction<Throwable> function = ThrowableBooleanToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
