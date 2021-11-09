package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToFloatTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToFloatTriFunction<String, String, String, Throwable> function =
                ThrowableToFloatTriFunction.of((t, u, v) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToFloatTriFunction<String, String, String, Throwable> function = ThrowableToFloatTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
