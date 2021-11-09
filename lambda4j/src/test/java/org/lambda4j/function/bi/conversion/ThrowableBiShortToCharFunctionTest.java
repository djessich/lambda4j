package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiShortToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortToCharFunction<Throwable> function = ThrowableBiShortToCharFunction.of((value1, value2) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortToCharFunction<Throwable> function = ThrowableBiShortToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
