package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortToFloatFunction<Throwable> function = ThrowableShortToFloatFunction.of(value -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortToFloatFunction<Throwable> function = ThrowableShortToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
