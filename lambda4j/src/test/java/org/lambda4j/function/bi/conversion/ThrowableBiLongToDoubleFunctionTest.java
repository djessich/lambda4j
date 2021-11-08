package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiLongToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiLongToDoubleFunction<Exception> function =
                ThrowableBiLongToDoubleFunction.of((value1, value2) -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiLongToDoubleFunction<Exception> function = ThrowableBiLongToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
