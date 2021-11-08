package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToLongTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToLongTriFunction<String, String, String, Exception> function =
                ThrowableToLongTriFunction.of((t, u, v) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToLongTriFunction<String, String, String, Exception> function = ThrowableToLongTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
