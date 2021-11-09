package org.lambda4j.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFloatFunction<String, Throwable> function = ThrowableFloatFunction.of(Float::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFloatFunction<String, Throwable> function = ThrowableFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
