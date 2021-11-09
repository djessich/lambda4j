package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToIntTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToIntTriFunction<String, String, String, Throwable> function =
                ThrowableToIntTriFunction.of((t, u, v) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToIntTriFunction<String, String, String, Throwable> function = ThrowableToIntTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
