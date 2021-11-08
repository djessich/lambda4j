package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToFloatTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToFloatTriFunction<String, String, String> function = ToFloatTriFunction.of((t, u, v) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToFloatTriFunction<String, String, String> function = ToFloatTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
