package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiCharToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiCharToLongFunction<Throwable> function = ThrowableBiCharToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiCharToLongFunction<Throwable> function = ThrowableBiCharToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
