package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriFloatToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriFloatToByteFunction<Exception> function =
                ThrowableTriFloatToByteFunction.of((value1, value2, value3) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriFloatToByteFunction<Exception> function = ThrowableTriFloatToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
