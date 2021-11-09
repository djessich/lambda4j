package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiIntToCharFunction<Throwable> function = ThrowableBiIntToCharFunction.of((value1, value2) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiIntToCharFunction<Throwable> function = ThrowableBiIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
