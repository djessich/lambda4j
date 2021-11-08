package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriIntToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriIntToShortFunction<Exception> function =
                ThrowableTriIntToShortFunction.of((value1, value2, value3) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriIntToShortFunction<Exception> function = ThrowableTriIntToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
