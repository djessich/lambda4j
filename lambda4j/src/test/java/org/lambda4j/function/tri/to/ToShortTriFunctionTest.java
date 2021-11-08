package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToShortTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToShortTriFunction<String, String, String> function = ToShortTriFunction.of((t, u, v) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToShortTriFunction<String, String, String> function = ToShortTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
