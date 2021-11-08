package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToByteBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToByteBiFunction<String, String> function = ToByteBiFunction.of((t, u) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToByteBiFunction<String, String> function = ToByteBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
