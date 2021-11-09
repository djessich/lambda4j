package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriDoubleToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriDoubleToByteFunction<Throwable> function =
                ThrowableTriDoubleToByteFunction.of((value1, value2, value3) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriDoubleToByteFunction<Throwable> function = ThrowableTriDoubleToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
