package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiDoubleToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiDoubleToLongFunction<Throwable> function =
                ThrowableBiDoubleToLongFunction.of((value1, value2) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiDoubleToLongFunction<Throwable> function = ThrowableBiDoubleToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
