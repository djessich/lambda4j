package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiLongToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiLongToIntFunction<Throwable> function = ThrowableBiLongToIntFunction.of((value1, value2) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiLongToIntFunction<Throwable> function = ThrowableBiLongToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
