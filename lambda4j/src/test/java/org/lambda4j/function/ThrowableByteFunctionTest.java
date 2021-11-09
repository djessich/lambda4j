package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteFunction<String, Throwable> function = ThrowableByteFunction.of(Byte::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteFunction<String, Throwable> function = ThrowableByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
