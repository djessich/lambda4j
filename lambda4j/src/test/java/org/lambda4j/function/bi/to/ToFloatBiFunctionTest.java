package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToFloatBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToFloatBiFunction<String, String> function = ToFloatBiFunction.of((t, u) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToFloatBiFunction<String, String> function = ToFloatBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
