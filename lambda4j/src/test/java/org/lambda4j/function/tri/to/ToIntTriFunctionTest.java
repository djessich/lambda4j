package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToIntTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToIntTriFunction<String, String, String> function = ToIntTriFunction.of((t, u, v) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToIntTriFunction<String, String, String> function = ToIntTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
