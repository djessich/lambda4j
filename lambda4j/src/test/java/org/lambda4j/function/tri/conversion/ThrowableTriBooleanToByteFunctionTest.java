package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriBooleanToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriBooleanToByteFunction<Throwable> function =
                ThrowableTriBooleanToByteFunction.of((value1, value2, value3) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriBooleanToByteFunction<Throwable> function = ThrowableTriBooleanToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
