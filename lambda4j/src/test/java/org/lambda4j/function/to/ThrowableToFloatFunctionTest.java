package org.lambda4j.function.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToFloatFunction<String, Throwable> function = ThrowableToFloatFunction.of(Float::parseFloat);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToFloatFunction<String, Throwable> function = ThrowableToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
