package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToByteTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToByteTriFunction<String, String, String, Exception> function =
                ThrowableToByteTriFunction.of((t, u, v) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToByteTriFunction<String, String, String, Exception> function = ThrowableToByteTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
