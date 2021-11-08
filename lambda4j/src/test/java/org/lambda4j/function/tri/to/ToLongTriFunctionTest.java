package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToLongTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToLongTriFunction<String, String, String> function = ToLongTriFunction.of((t, u, v) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToLongTriFunction<String, String, String> function = ToLongTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
