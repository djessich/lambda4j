package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToShortTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToShortTriFunction<String, String, String, Exception> function =
                ThrowableToShortTriFunction.of((t, u, v) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToShortTriFunction<String, String, String, Exception> function = ThrowableToShortTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
