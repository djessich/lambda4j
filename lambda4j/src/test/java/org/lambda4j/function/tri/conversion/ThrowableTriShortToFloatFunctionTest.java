package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriShortToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriShortToFloatFunction<Throwable> function =
                ThrowableTriShortToFloatFunction.of((value1, value2, value3) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriShortToFloatFunction<Throwable> function = ThrowableTriShortToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
