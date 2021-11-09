package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharToFloatFunction<Throwable> function = ThrowableCharToFloatFunction.of(value -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharToFloatFunction<Throwable> function = ThrowableCharToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
