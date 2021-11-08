package org.lambda4j.function.tri.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToDoubleTriFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToDoubleTriFunction<String, String, String> function =
                ToDoubleTriFunction.of((t, u, v) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToDoubleTriFunction<String, String, String> function = ToDoubleTriFunction.of(null);
        Assertions.assertNull(function);
    }
}
