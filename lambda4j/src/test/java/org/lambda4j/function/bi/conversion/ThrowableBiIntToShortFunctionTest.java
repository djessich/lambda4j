package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiIntToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiIntToShortFunction<Throwable> function =
                ThrowableBiIntToShortFunction.of((value1, value2) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiIntToShortFunction<Throwable> function = ThrowableBiIntToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
