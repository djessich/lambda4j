package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntToFloatFunction<Throwable> function = ThrowableIntToFloatFunction.of(value -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntToFloatFunction<Throwable> function = ThrowableIntToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
