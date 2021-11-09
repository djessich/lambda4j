package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriCharToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriCharToByteFunction<Throwable> function =
                ThrowableTriCharToByteFunction.of((value1, value2, value3) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriCharToByteFunction<Throwable> function = ThrowableTriCharToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
