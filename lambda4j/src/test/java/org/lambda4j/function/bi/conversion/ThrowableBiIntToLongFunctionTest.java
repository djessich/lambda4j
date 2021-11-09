package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiIntToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiIntToLongFunction<Throwable> function = ThrowableBiIntToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiIntToLongFunction<Throwable> function = ThrowableBiIntToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
