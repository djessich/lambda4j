package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiShortToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortToDoubleFunction<Throwable> function =
                ThrowableBiShortToDoubleFunction.of((value1, value2) -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortToDoubleFunction<Throwable> function = ThrowableBiShortToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
