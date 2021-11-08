package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToCharTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToCharTriFunction<String, String, String> function = ToCharTriFunction.of((t, u, v) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToCharTriFunction<String, String, String> function = ToCharTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
