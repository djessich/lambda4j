package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToCharTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToCharTriFunction<String, String, String, Throwable> function =
                ThrowableToCharTriFunction.of((t, u, v) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToCharTriFunction<String, String, String, Throwable> function = ThrowableToCharTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
