package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriDoubleToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriDoubleToIntFunction<Exception> function =
                ThrowableTriDoubleToIntFunction.of((value1, value2, value3) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriDoubleToIntFunction<Exception> function = ThrowableTriDoubleToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}