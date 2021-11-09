package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiDoubleToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiDoubleToIntFunction<Throwable> function = ThrowableBiDoubleToIntFunction.of((value1, value2) -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiDoubleToIntFunction<Throwable> function = ThrowableBiDoubleToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
