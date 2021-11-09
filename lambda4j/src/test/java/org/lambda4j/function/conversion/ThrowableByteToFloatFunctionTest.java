package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteToFloatFunction<Throwable> function = ThrowableByteToFloatFunction.of(value -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteToFloatFunction<Throwable> function = ThrowableByteToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
