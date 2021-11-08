package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToShortBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToShortBiFunction<String, String> function = ToShortBiFunction.of((t, u) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToShortBiFunction<String, String> function = ToShortBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
