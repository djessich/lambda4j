package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriByteToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriByteToFloatFunction<Exception> function =
                ThrowableTriByteToFloatFunction.of((value1, value2, value3) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriByteToFloatFunction<Exception> function = ThrowableTriByteToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
