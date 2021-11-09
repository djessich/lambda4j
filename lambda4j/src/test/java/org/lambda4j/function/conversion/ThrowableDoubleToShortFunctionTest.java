package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleToShortFunction<Throwable> function = ThrowableDoubleToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleToShortFunction<Throwable> function = ThrowableDoubleToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
