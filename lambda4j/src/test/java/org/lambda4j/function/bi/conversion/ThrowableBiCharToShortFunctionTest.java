package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiCharToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiCharToShortFunction<Throwable> function =
                ThrowableBiCharToShortFunction.of((value1, value2) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiCharToShortFunction<Throwable> function = ThrowableBiCharToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
