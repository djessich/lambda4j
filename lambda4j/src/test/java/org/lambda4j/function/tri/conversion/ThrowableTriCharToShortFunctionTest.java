package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriCharToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriCharToShortFunction<Throwable> function =
                ThrowableTriCharToShortFunction.of((value1, value2, value3) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriCharToShortFunction<Throwable> function = ThrowableTriCharToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
