package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableToByteBiFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableToByteBiFunction<String, String, Exception> function =
                ThrowableToByteBiFunction.of((t, u) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableToByteBiFunction<String, String, Exception> function = ThrowableToByteBiFunction.of(null);
        Assertions.assertNull(function);
    }
}
