package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiFloatToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiFloatToIntFunction<Throwable> function = ThrowableBiFloatToIntFunction.of((value1, value2) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiFloatToIntFunction<Throwable> function = ThrowableBiFloatToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
