package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriShortToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriShortToByteFunction<Throwable> function =
                ThrowableTriShortToByteFunction.of((value1, value2, value3) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriShortToByteFunction<Throwable> function = ThrowableTriShortToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
