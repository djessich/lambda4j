package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntToShortFunction<Throwable> function = ThrowableIntToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntToShortFunction<Throwable> function = ThrowableIntToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
