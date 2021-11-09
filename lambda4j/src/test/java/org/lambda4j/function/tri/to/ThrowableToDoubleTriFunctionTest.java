package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToDoubleTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToDoubleTriFunction<String, String, String, Throwable> function =
                ThrowableToDoubleTriFunction.of((t, u, v) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToDoubleTriFunction<String, String, String, Throwable> function =
                ThrowableToDoubleTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
