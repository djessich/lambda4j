package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongToByteFunction<Throwable> function = ThrowableLongToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongToByteFunction<Throwable> function = ThrowableLongToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
