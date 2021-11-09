package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiShortToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortToLongFunction<Throwable> function = ThrowableBiShortToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortToLongFunction<Throwable> function = ThrowableBiShortToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
