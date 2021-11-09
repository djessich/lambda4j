package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriCharToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriCharToFloatFunction<Throwable> function =
                ThrowableTriCharToFloatFunction.of((value1, value2, value3) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriCharToFloatFunction<Throwable> function = ThrowableTriCharToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
