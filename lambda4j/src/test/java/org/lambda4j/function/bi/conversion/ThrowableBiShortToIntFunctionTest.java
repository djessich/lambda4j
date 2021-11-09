package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortToIntFunction<Throwable> function = ThrowableBiShortToIntFunction.of((value1, value2) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortToIntFunction<Throwable> function = ThrowableBiShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
